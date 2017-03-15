/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import work.model.dto.Member;

/**
 * @author DB
 *
 */
public class MemberDAO {
	private FactoryDAO factory = FactoryDAO.getInstance();

	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws SQLException {
		return factory.getConnection();
	}

	/** Member 전체 조회 */
	public ArrayList<Member> selectList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from dw_member";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int empNo = rs.getInt("EMP_NO");
				String userPw = rs.getString("USERPW");
				String userName = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String mobile = rs.getString("MOBILE");
				String dept = rs.getString("DEPT");
				String position = rs.getString("POSITION");
				String isAdmin = rs.getString("IS_ADMIN");
				list.add(new Member(empNo, userPw, userName, email, mobile, dept, position, isAdmin));
			}
		} catch (SQLException e) {
			System.out.println("회원 전체 조회 오류: " + e.getMessage());
			e.getStackTrace();
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}

	/** 특정회원 상세조회 */
	public Member selectOne(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member dto = null;

		try {
			conn = getConnection();
			String sql = "select * from dw_member where emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new Member(rs.getInt("EMP_NO"), rs.getString("USERPW"), rs.getString("USERNAME"),
						rs.getString("EMAIL"), rs.getString("MOBILE"), rs.getString("DEPT"), rs.getString("POSITION"),
						rs.getString("IS_ADMIN"));
			}
		} catch (SQLException e) {
			System.out.println("회원 상세 조회 오류: " + e.getMessage());
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return dto;
	}

	/** 로그인 기능 */
	public HashMap<String, String> login(int empNo, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> map = null;
		try {
			conn = getConnection();
			String sql = "select username, is_admin from dw_member where emp_no = ? and userpw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				map = new HashMap<String, String>();
				map.put("userName", rs.getString("username"));
				map.put("isAdmin", rs.getString("is_admin"));
			}

		} catch (SQLException e) {
			System.out.println("로그인 오류: " + e.getMessage());
			e.printStackTrace();

		} finally {
			factory.close(rs, pstmt, conn);
		}
		return map;
	}

	/** 비밀번호 찾기 */
	public String findUserPw(String param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String email = null;

		try {
			conn = getConnection();
			String sql = "select email from dw_member where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return email;
	}

	/** 관리자의 회원 등록 */
	public int insert(Member dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "insert into dw_member " + "values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpNo());
			pstmt.setString(2, dto.getUserPw());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(4, dto.getMobile());
			pstmt.setString(6, dto.getDept());
			pstmt.setString(7, dto.getPosition());
			pstmt.setString(8, dto.getIsAdmin());
			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("회원등록 오류" + e.getMessage());
			e.printStackTrace();

		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

	/** 회원 정보변경 */
	public int update(Member dto) {
		String sql = "update dw_member set userpw = ?, username = ?, email = ?, "
				+ "mobile = ?, dept = ?, position = ? where emp_no = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserPw());
			pstmt.setString(2, dto.getUserName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getMobile());
			pstmt.setString(5, dto.getDept());
			pstmt.setString(6, dto.getPosition());
			pstmt.setInt(7, dto.getEmpNo());

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

	
	/** 회원 삭제 */
	public int deleteMember(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "delete dw_member where emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원 탈퇴 오류" + e.getMessage());
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}
}

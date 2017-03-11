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

import javax.sql.DataSource;

import work.model.dto.Member;

/**
 * @author DB
 *
 */
public class MemberDAO {
	private static final String TABLE_NAME_MEMBER = "MEMBER";

	private static MemberDAO mInstance = new MemberDAO();
	private DataSource ds;
	
	/**
	 * MemberDAO의 instance를 얻어옴
	 * @return
	 */
	public static MemberDAO getInstance() {
		return mInstance;
	}
	
//	public static MemberDAO getInstance() {
//		if (mInstance == null) {
//			mInstance = new MemberDAO();
//		}
//
//		return mInstance;
//	}
	
	/**
	 * 
	 */
	private MemberDAO() {
		/*try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
			ds.get
		} catch (NamingException e) {
			e.printStackTrace();
		}*/
	}

	/*
	 * =========== Query ===========
	 */

	/**
	 * 멤버 전체 조회
	 * 
	 * @return
	 */
	public ArrayList<Member> selectList() {
		String sql = "select * from " + TABLE_NAME_MEMBER;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Member> list = new ArrayList<Member>();

		try {
			conn = FactoryDAO.getInstance().getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			String userId;
			String userPw;
			String username;
			String mobile;
			String email;
			String entryDate;
			String grade;
			int mileage;
			String manager;

			while (rs.next()) {
				userId = rs.getString("user_id");
				userPw = rs.getString("user_pw");
				username = rs.getString("username");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				mileage = rs.getInt("mileage");
				manager = rs.getString("manager");

				list.add(new Member(userId, userPw, username, mobile, email, entryDate, grade, mileage, manager));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(rs, stmt, conn);
		}

		return list;
	}

	/**
	 * 멤버 상세 조회
	 * 
	 * @return
	 */
	public Member selectOne(String userId) {
		String sql = "select * from " + TABLE_NAME_MEMBER + " where user_Id = '" + userId + "'";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Member dto = null;

		try {
			conn = FactoryDAO.getInstance().getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			String userPw;
			String username;
			String mobile;
			String email;
			String entryDate;
			String grade;
			int mileage;
			String manager;

			if (rs.next()) {
				userPw = rs.getString("user_pw");
				username = rs.getString("username");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				mileage = rs.getInt("mileage");
				manager = rs.getString("manager");
				dto = new Member(userId, userPw, username, mobile, email, entryDate, grade, mileage, manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(rs, stmt, conn);
		}

		return dto;
	}

	/**
	 * 신규 멤버 등록
	 * 
	 * @param dto
	 * @return
	 */
	public int create(Member dto) {
		String sql = String.format("insert into %s values (?, ?, ?, ?, sysdate, ?, ?, ?)", TABLE_NAME_MEMBER);

		Connection conn = null;
		PreparedStatement pstmt = null;

		int resultRow = 0;

		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPw());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getGrade());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getManager());

			resultRow = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(pstmt, conn);
		}

		return resultRow;
	}

	/**
	 * 멤버 정보 수정
	 * 
	 * @param dto
	 * @return
	 */
	public int update(Member dto) {
		String sql = String.format(
				"update %s set user_pw = ?, username = ?, mobile = ?, email = ?, grade = ?, mileage = ?, manager = ? where user_id = ?", TABLE_NAME_MEMBER);

		Connection conn = null;
		PreparedStatement pstmt = null;

		int resultRow = 0;

		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUserPw());
			pstmt.setString(2, dto.getUsername());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getGrade());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getManager());
			pstmt.setString(8, dto.getUserId());

			resultRow = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(pstmt, conn);
		}

		return resultRow;
	}

	/**
	 * 멤버 삭제
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteOne(String userId) {
		String sql = String.format("delete from %s where user_id = ?", TABLE_NAME_MEMBER);

		Connection conn = null;
		PreparedStatement pstmt = null;

		int resultRow = 0;

		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			resultRow = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(pstmt, conn);
		}

		return resultRow;
	}
	
	/**
	 * login 
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public HashMap<String, String> login(String userId, String userPw) {
		String sql = String.format("select username, grade from %s where user_id = ? and user_pw = ?", TABLE_NAME_MEMBER); 

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		HashMap<String, String> hashMap = null;
		
		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				hashMap = new HashMap<String, String>();
				hashMap.put("username", rs.getString("username"));
				hashMap.put("grade", rs.getString("grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FactoryDAO.getInstance().close(rs, pstmt, conn);
		}

		return hashMap;
	}
	
	//id search
	public String findId(String username, String param) {
		//param : regExp 1. mobile, 2. email
		return null;
	}
	
	//pw search

}

package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.Board;

public class BoardDAO {

	private FactoryDAO factory = FactoryDAO.getInstance();

	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws SQLException {
		return factory.getConnection();
	}

	/** 공지 테이블 전체 조회 */
	public ArrayList<Board> selectList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<Board>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from board";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int articleNo = rs.getInt("article_no");
				String title = rs.getString("title");
				int empNo = rs.getInt("emp_no");
				String regDate = rs.getString("reg_date");
				String content = rs.getString("content");

				Board dto = new Board(articleNo, title, empNo, regDate, content);
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("Error : 전체 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}

	/** 글번호로 글 정보 조회 */
	public Board SelectOne(int articleNo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Board dto = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from board where article_no = " + articleNo;
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String title = rs.getString("title");
				int empNo = rs.getInt("emp_no");
				String regiDate = rs.getString("regi_date");
				String content = rs.getString("content");
				dto = new Board(articleNo, title, empNo, regiDate, content);
			}

		} catch (SQLException e) {
			System.out.println("Error : 상세 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(rs, stmt, conn);
		}
		return dto;
	}

	/** 글 등록 */
	public int insert(Board dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "insert into board values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getArticleNo());
			pstmt.setString(2, dto.getTitle());
			pstmt.setInt(3, dto.getEmpNo());
			pstmt.setString(4, dto.getRegDate());
			pstmt.setString(5, dto.getContent());

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error : 글 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

	/** 본인 글 수정 */
	public int update(int articleNo, String title, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "update board set title = ?, content = ? where article_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, articleNo);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error : 글 수정 오류");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return 0;
	}

	/** 본인 글 삭제 */
	public int delete(int articleNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "delete board where article_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, articleNo);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error :  글 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

}

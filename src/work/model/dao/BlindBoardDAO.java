package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.BlindBoard;

public class BlindBoardDAO {
	private String TABLE_NAME = " dw_blind_board ";

	private FactoryDAO factory = FactoryDAO.getInstance();

	private BlindBoardDAO() {

	}

	public void setTableName(String tableName) {
		this.TABLE_NAME = tableName;
	}

	private static BlindBoardDAO instance = new BlindBoardDAO();

	public static BlindBoardDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws SQLException {
		return factory.getConnection();
	}

	/** �Խ��� �� ��ü ��ȸ */
	public ArrayList<BlindBoard> selectList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BlindBoard> list = new ArrayList<BlindBoard>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from " + TABLE_NAME;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int articleNo = rs.getInt("article_no");
				String title = rs.getString("title");
				int empNo = rs.getInt("emp_no");
				String regDate = rs.getString("reg_date");
				String content = rs.getString("content");
				int hits = rs.getInt("hits");
				String isNotice = rs.getString("is_notice");

				BlindBoard dto = new BlindBoard(articleNo, title, empNo, regDate, content, hits, isNotice);
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("Error : ��ü ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}

	/** �� ��� */
	public int insert(BlindBoard dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "insert into " + TABLE_NAME + " values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getArticleNo());
			pstmt.setString(2, dto.getTitle());
			pstmt.setInt(3, dto.getEmpNo());
			pstmt.setString(4, dto.getRegDate());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, dto.getHits());
			pstmt.setString(7, dto.getIsNotice());

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error : �� ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

	/**
	 * �Խ��� �� �˻�
	 * 
	 * @param columnName
	 *            �˻��� �÷���
	 * @param keyword
	 *            �˻� Ű����
	 * @return
	 */
	public ArrayList<BlindBoard> selectListByColumn(String columnName, String keyword) {
		String sql = String.format("select * from %s where %s = '%s'", TABLE_NAME, columnName, keyword);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<BlindBoard> list = new ArrayList<BlindBoard>();

		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);

			int articleNo;
			String title;
			int empNo;
			String regDate;
			String content;
			int hits;
			String isNotice;

			while (rs.next()) {
				articleNo = rs.getInt(1);
				title = rs.getString(2);
				empNo = rs.getInt(3);
				regDate = rs.getString(4);
				content = rs.getString(5);
				hits = rs.getInt(6);
				isNotice = rs.getString(7);

				list.add(new BlindBoard(articleNo, title, empNo, regDate, content, hits, isNotice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return list;
	}

	/** ȸ�� ���� �� ���� */
	public int update(int articleNo, String title, String content) {
		return update(articleNo, title, content, "N");
	}

	/** ������ �� ���� */
	public int update(int articleNo, String title, String content, String isNotice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "update " + TABLE_NAME + "set title = ?, content = ?, is_notice = ? where article_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, isNotice);
			pstmt.setInt(4, articleNo);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error : �� ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

	/** ���� �� ���� */
	public int delete(int articleNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = getConnection();
			String sql = "delete " + TABLE_NAME + " where article_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, articleNo);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error :  �� ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(pstmt, conn);
		}
		return row;
	}

}

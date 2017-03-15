package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import work.model.dto.Board;
import work.model.dto.Restaurant;

/**
 * 
 * @author DB
 *
 */
public class RestaurantDAO {
	private String TABLE_NAME = "dw_restaurant";

	private FactoryDAO factory = FactoryDAO.getInstance();

	private RestaurantDAO() {

	}

	/**
	 * 
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.TABLE_NAME = tableName;
	}

	private static RestaurantDAO instance = new RestaurantDAO();

	/**
	 * 
	 * @return
	 */
	public static RestaurantDAO getInstance() {
		return instance;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return factory.getConnection();
	}

	/**
	 * 
	 * @param map ������ �÷���, ���� ��
	 * @return
	 */
	public ArrayList<Restaurant> selectRestaurantList(HashMap<String, String> map) {
		String sql = String.format("select * from %s where", TABLE_NAME);
		
		int cnt = 0;
		for (String key : map.keySet()) {
			sql += String.format(" %s = '%s' ", key, map.get(key));
			cnt++;
			
			if (cnt < map.size()) {
				sql += "and";
			}
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Restaurant> aryRestaurant = null;

		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				if (aryRestaurant == null) {
					aryRestaurant = new ArrayList<Restaurant>();
				}
				
				aryRestaurant.add(new Restaurant(
						rs.getInt("article_no"),
						rs.getString("restaurant"),
						rs.getString("title"),
						rs.getInt("emp_no"),
						rs.getString("menu_type"),
						rs.getString("price"),
						rs.getInt("rate"),
						rs.getString("address"),
						rs.getString("reg_date"),
						rs.getString("content"),
						rs.getString("image1"),
						rs.getString("image2"),
						rs.getString("image3"),
						rs.getString("image4"),
						rs.getString("image5"),
						rs.getInt("take_min"),
						rs.getString("coords")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return aryRestaurant;
	}
	
	/**
	 * 
	 * @param query where ��
	 * @return
	 */
	public ArrayList<Restaurant> selectRestaurantListUsingWhere(String query) {
		String sql = String.format("select * from %s %s ", TABLE_NAME, query);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Restaurant> aryRestaurant = null;

		System.out.println(sql);
		
		try {
			conn = FactoryDAO.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				if (aryRestaurant == null) {
					aryRestaurant = new ArrayList<Restaurant>();
				}
				
				aryRestaurant.add(new Restaurant(
						rs.getInt("article_no"),
						rs.getString("restaurant"),
						rs.getString("title"),
						rs.getInt("emp_no"),
						rs.getString("menu_type"),
						rs.getString("price"),
						rs.getInt("rate"),
						rs.getString("address"),
						rs.getString("reg_date"),
						rs.getString("content"),
						rs.getString("image1"),
						rs.getString("image2"),
						rs.getString("image3"),
						rs.getString("image4"),
						rs.getString("image5"),
						rs.getInt("take_min"),
						rs.getString("coords")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return aryRestaurant;
	}
	
	/** �Խ��� �� ��ü ��ȸ */
	public ArrayList<Board> selectList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<Board>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select " + TABLE_NAME + ".*, member.username from " + TABLE_NAME + ", member where "
					+ TABLE_NAME + ".emp_no=member.emp_no";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int articleNo = rs.getInt("article_no");
				String title = rs.getString("title");
				int empNo = rs.getInt("emp_no");
				String regDate = rs.getString("reg_date");
				String content = rs.getString("content");
				int hits = rs.getInt("hits");
				String isNotice = rs.getString("is_notice");

				Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice);
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

	/** ȸ���� �� ��� */
	public int insert(int articleNo, String title, int empNo, String regDate, String content, int hits) {
		return insert(new Board(articleNo, title, empNo, regDate, content, hits, "N"));
	}

	/** �������� �� ��� */
	public int insert(Board dto) {
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
	public ArrayList<Board> selectListByColumn(String columnName, String keyword) {
		String sql = String.format("select * from %s where %s = '%s'", TABLE_NAME, columnName, keyword);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Board> list = new ArrayList<Board>();

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

				list.add(new Board(articleNo, title, empNo, regDate, content, hits, isNotice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return list;
	}

	/** �۹�ȣ �ִ�+1 �������� */
	public int selectMaxNo() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int no = 1;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select Max(article_no) from " + TABLE_NAME;
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				no = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("Error : ��ü ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(rs, stmt, conn);
		}
		return no;
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

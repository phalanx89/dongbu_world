package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import work.model.dto.Restaurant;
import work.util.Utility;

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
   * ��� ������ ���� ������ ������
   * 
   * @return
   */
  public ArrayList<Restaurant> selectRestaurantList() {
    String sql = String.format("select * from %s", TABLE_NAME);
    
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
        
        aryRestaurant.add(new Restaurant(rs.getInt("article_no"), rs.getString("restaurant"), rs.getString("title"), rs.getInt("emp_no"), rs.getString("menu_type"), rs.getString("price"), rs.getInt("rate"), rs.getString("address"), rs.getString("reg_date"), rs.getString("content"), rs.getString("image1"), rs.getString("image2"), rs.getString("image3"), rs.getString("image4"), rs.getString("image5"), rs.getInt("take_min"), rs.getString("coords")));
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
   * @param map
   *          ������ �÷���, ���� ��
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
        
        aryRestaurant.add(new Restaurant(rs.getInt("article_no"), rs.getString("restaurant"), rs.getString("title"), rs.getInt("emp_no"), rs.getString("menu_type"), rs.getString("price"), rs.getInt("rate"), rs.getString("address"), rs.getString("reg_date"), rs.getString("content"), rs.getString("image1"), rs.getString("image2"), rs.getString("image3"), rs.getString("image4"), rs.getString("image5"), rs.getInt("take_min"), rs.getString("coords")));
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
   * @param query
   *          where ��
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
        
        aryRestaurant.add(new Restaurant(rs.getInt("article_no"), rs.getString("restaurant"), rs.getString("title"), rs.getInt("emp_no"), rs.getString("menu_type"), rs.getString("price"), rs.getInt("rate"), rs.getString("address"), rs.getString("reg_date"), rs.getString("content"), rs.getString("image1"), rs.getString("image2"), rs.getString("image3"), rs.getString("image4"), rs.getString("image5"), rs.getInt("take_min"), rs.getString("coords")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      factory.close(rs, pstmt, conn);
    }
    
    return aryRestaurant;
  }
  
  /** �Խ��� �� ��ü ��ȸ */
  /*
   * public ArrayList<Restaurant> selectList() { Connection conn = null; Statement stmt = null; ResultSet rs = null; ArrayList<Restaurant> list = new ArrayList<Restaurant>();
   * 
   * try { conn = getConnection(); stmt = conn.createStatement(); String sql = "select " + TABLE_NAME + ".*, member.username from " + TABLE_NAME + ", member where " + TABLE_NAME + ".emp_no=member.emp_no"; rs = stmt.executeQuery(sql);
   * 
   * while (rs.next()) { int articleNo = rs.getInt("article_no"); String title = rs.getString("title"); int empNo = rs.getInt("emp_no"); String regDate = rs.getString("reg_date"); String content = rs.getString("content"); int hits = rs.getInt("hits"); String isNotice = rs.getString("is_notice");
   * 
   * Restaurant dto = new Restaurant(articleNo, title, empNo, regDate, content, hits, isNotice); list.add(dto); }
   * 
   * } catch (SQLException e) { System.out.println("Error : ��ü ��ȸ ����"); e.printStackTrace(); } finally { factory.close(rs, stmt, conn); } return list; }
   */
  
  /**
   * ������ �� ��ȸ
   * 
   * @return
   */
  public Restaurant selectOne(int articleNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Restaurant dto = new Restaurant();
    
    try {
      conn = getConnection();
      String sql = "select * from " + TABLE_NAME + " where article_no = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, articleNo);
      rs = pstmt.executeQuery();
      
      if (rs.next()) {
        dto = new Restaurant(rs.getInt("article_no"), rs.getString("restaurant"), rs.getString("title"), rs.getInt("emp_no"), rs.getString("menu_type"), rs.getString("price"), rs.getInt("rate"), rs.getString("address"), rs.getString("reg_date"), rs.getString("content"), rs.getString("image1"), rs.getString("image2"), rs.getString("image3"), rs.getString("image4"), rs.getString("image5"), rs.getInt("take_min"), rs.getString("coords"));
      }
    } catch (SQLException e) {
      System.out.println("�� ��ȸ ����: " + e.getMessage());
      e.printStackTrace();
    } finally {
      factory.close(rs, pstmt, conn);
    }
    return dto;
  }
  
  /**
   * restaurant ���
   * 
   * @param dto
   * @return
   */
  public int insert(Restaurant dto) {
    String sql = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      sql = "insert into " + TABLE_NAME + " values(?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?)";
      pstmt = conn.prepareStatement(sql);
      
      dto.setArticle_no(selectMaxNo());
      
      pstmt.setInt(1, dto.getArticle_no());
      pstmt.setString(2, dto.getRestaurant());
      pstmt.setString(3, dto.getTitle());
      pstmt.setInt(4, dto.getEmp_no());
      pstmt.setString(5, dto.getMenuType());
      pstmt.setString(6, dto.getPrice());
      pstmt.setInt(7, dto.getRate());
      pstmt.setString(8, dto.getAddress());
      pstmt.setString(9, dto.getContent());
      pstmt.setString(10, dto.getImage1());
      pstmt.setString(11, dto.getImage2());
      pstmt.setString(12, dto.getImage3());
      pstmt.setString(13, dto.getImage4());
      pstmt.setString(14, dto.getImage5());
      pstmt.setInt(15, dto.getTakeMin());
      pstmt.setString(16, dto.getCoords());
      
      row = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      System.out.println("Error : insert..  sql : " + sql);
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
  /**
   * ������ �˻�
   * 
   * @param columnName
   *          �˻��� �÷���
   * @param keyword
   *          �˻� Ű����
   * @return
   */
  public ArrayList<Restaurant> selectListByColumn(String columnName, String keyword) {
    String sql = String.format("select * from %s where %s like '%%%s%%'", TABLE_NAME, columnName, keyword);
    
    System.out.println(sql);
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    ArrayList<Restaurant> list = new ArrayList<Restaurant>();
    
    try {
      conn = FactoryDAO.getInstance().getConnection();
      pstmt = conn.prepareStatement(sql);
      
      rs = pstmt.executeQuery(sql);
      
      int articleNo = 0;
      String restaurant = null;
      String title = null;
      int empNo = 0;
      String menuType = null;
      String price = null;
      int rate = 0;
      String address = null;
      String regDate = null;
      String content = null;
      String image1 = null;
      String image2 = null;
      String image3 = null;
      String image4 = null;
      String image5 = null;
      int takeMin = 0;
      String coords = null;
      
      while (rs.next()) {
        articleNo = rs.getInt(1);
        restaurant = rs.getString(2);
        title = rs.getString(3);
        empNo = rs.getInt(4);
        menuType = rs.getString(5);
        price = rs.getString(6);
        rate = rs.getInt(7);
        address = rs.getString(8);
        regDate = rs.getString(9);
        content = rs.getString(10);
        image1 = rs.getString(11);
        image2 = rs.getString(12);
        image3 = rs.getString(13);
        image4 = rs.getString(14);
        image5 = rs.getString(15);
        takeMin = rs.getInt(16);
        coords = rs.getString(17);
        
        list.add(new Restaurant(articleNo, restaurant, title, empNo, menuType, price, rate, address, regDate, content, image1, image2, image3, image4, image5, takeMin, coords));
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
      System.out.println("Error : �۹�ȣ ���� ����");
      e.printStackTrace();
    } finally {
      factory.close(rs, stmt, conn);
    }
    return no;
  }
  
  /** ������ ���� */
  public int update(Restaurant dto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      String sql = "update " + TABLE_NAME + "set restaurant = ?, title = ?, menuType = ?, price = ?, rate = ?, " + "address = ?, regDate = ?, content = ?, image1 = ?, image2 = ?, image3 = ?, image4 = ?, image5 = ?, takeMin = ?, coords = ? where article_no = ?";
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setString(1, dto.getRestaurant());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getMenuType());
      pstmt.setString(4, dto.getPrice());
      pstmt.setInt(5, dto.getRate());
      pstmt.setString(6, dto.getAddress());
      pstmt.setString(7, dto.getRegDate());
      pstmt.setString(8, dto.getContent());
      pstmt.setString(9, dto.getImage1());
      pstmt.setString(10, dto.getImage2());
      pstmt.setString(11, dto.getImage3());
      pstmt.setString(12, dto.getImage4());
      pstmt.setString(13, dto.getImage5());
      pstmt.setInt(14, dto.getTakeMin());
      pstmt.setString(15, dto.getCoords());
      pstmt.setInt(16, dto.getArticle_no());
      
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
  
  /**
   * DB�� ����� ����Ʈ�� ���� ��ȯ
   * @return
   */
  public int getListSize() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int size = 0;
    
    try {
      conn = getConnection();
      String sql = "select count(*) from dw_restaurant";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        size = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      factory.close(rs, pstmt, conn);
    }
    
    return size;
  }
}

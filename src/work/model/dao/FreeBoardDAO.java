package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.FreeBoard;
import work.model.dto.Member;

public class FreeBoardDAO {
  private String TABLE_NAME = "dw_free_board";
  
  private FactoryDAO factory = FactoryDAO.getInstance();
  
  private FreeBoardDAO() {
    
  }
  
  public void setTableName(String tableName) {
    this.TABLE_NAME = tableName;
  }
  
  private static FreeBoardDAO instance = new FreeBoardDAO();
  
  public static FreeBoardDAO getInstance() {
    return instance;
  }
  
  private Connection getConnection() throws SQLException {
    return factory.getConnection();
  }
  
  /**
   * 게시판 글 전체 조회
   * 
   * @return
   */
  public ArrayList<FreeBoard> selectList() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
    
    try {
      conn = getConnection();
      stmt = conn.createStatement();
      String sql = "select * from " + TABLE_NAME + " order by is_notice desc, reg_date desc";
      
      rs = stmt.executeQuery(sql);
      FreeBoard dto = null;
      while (rs.next()) {
        int articleNo = rs.getInt("article_no");
        String title = rs.getString("title");
        int empNo = rs.getInt("emp_no");
        String regDate = rs.getString("reg_date");
        String content = rs.getString("content");
        int hits = rs.getInt("hits");
        String isNotice = rs.getString("is_notice");
        String userName = rs.getString("username");
        
        dto = new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
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
  
  /**
   * 게시판 글 상세 조회
   * @return
   */
//  public FreeBoard selectOne(int articleNo) {
//    Connection conn = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    FreeBoard dto = new FreeBoard();
//    
//    try {
//      conn = getConnection();
//      String sql = "select * from " + TABLE_NAME + " where emp_no = ?";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setInt(1, empNo);
//      rs = pstmt.executeQuery();
//
//      if (rs.next()) {
//        dto = new Member(rs.getInt("EMP_NO"), rs.getString("USERPW"), rs.getString("USERNAME"),
//            rs.getString("EMAIL"), rs.getString("MOBILE"), rs.getString("DEPT"), rs.getString("POSITION"),
//            rs.getString("IS_ADMIN"));
//      }
//    } catch (SQLException e) {
//      System.out.println("회원 상세 조회 오류: " + e.getMessage());
//      e.printStackTrace();
//    } finally {
//      factory.close(rs, pstmt, conn);
//    }
//    return dto;
//  }
  
  /** 회원의 글 등록 */
  public int insert(int articleNo, String title, int empNo, String regDate, String content, int hits, String userName) {
    return insert(new FreeBoard(articleNo, title, empNo, regDate, content, hits, "N", userName));
  }
  
  /** 관리자의 글 등록 */
  public int insert(FreeBoard dto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      String sql = "insert into " + TABLE_NAME + " values(?, ?, ?, sysdate, ?, ?, ?, ?)";
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setInt(1, dto.getArticleNo());
      pstmt.setString(2, dto.getTitle());
      pstmt.setInt(3, dto.getEmpNo());
      pstmt.setString(4, dto.getContent());
      pstmt.setInt(5, dto.getHits());
      pstmt.setString(6, dto.getIsNotice());
      pstmt.setString(7, dto.getUserName());
      
      row = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      System.out.println("Error : 글 등록 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
  /**
   * 게시판 글 검색
   * 
   * @param columnName
   *          검색할 컬럼명
   * @param keyword
   *          검색 키워드
   * @return
   */
  public ArrayList<FreeBoard> selectListByColumn(String columnName, String keyword) {
    String sql = String.format("select * from %s where %s like '%%%s%%'", TABLE_NAME, columnName, keyword);
    
    System.out.println(sql);
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
    
    try {
      conn = FactoryDAO.getInstance().getConnection();
      pstmt = conn.prepareStatement(sql);
      
      rs = pstmt.executeQuery(sql);
      
      int articleNo = 0;
      String title = null;
      int empNo = 0;
      String regDate = null;
      String content = null;
      int hits = 0;
      String isNotice = null;
      String userName = null;
      
      while (rs.next()) {
        articleNo = rs.getInt(1);
        title = rs.getString(2);
        empNo = rs.getInt(3);
        regDate = rs.getString(4);
        content = rs.getString(5);
        hits = rs.getInt(6);
        isNotice = rs.getString(7);
        userName = rs.getString(8);
        
        list.add(new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      factory.close(rs, pstmt, conn);
    }
    return list;
  }
  
  /** 글번호 최댓값+1 가져오기 */
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
      System.out.println("Error : 전체 조회 오류");
      e.printStackTrace();
    } finally {
      factory.close(rs, stmt, conn);
    }
    return no;
  }
  
  /** 회원 본인 글 수정 */
  public int update(int articleNo, String title, String content) {
    return update(articleNo, title, content, "N");
  }
  
  /** 관리자 글 수정 */
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
      System.out.println("Error : 글 수정 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
  /** 본인 글 삭제 */
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
      System.out.println("Error :  글 삭제 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
}

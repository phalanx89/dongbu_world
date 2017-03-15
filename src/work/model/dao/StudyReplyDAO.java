package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.FreeReply;

public class StudyReplyDAO {
  private String TABLE_NAME = "dw_study_reply";
  
  private FactoryDAO factory = FactoryDAO.getInstance();
  
  private StudyReplyDAO() {
    
  }
  
  public void setTableName(String tableName) {
    this.TABLE_NAME = tableName;
  }
  
  private static StudyReplyDAO instance = new StudyReplyDAO();
  
  public static StudyReplyDAO getInstance() {
    return instance;
  }
  
  private Connection getConnection() throws SQLException {
    return factory.getConnection();
  }
  
  
  /**
   * 댓글 전체 조회
   * 
   * @return
   */
  public ArrayList<FreeReply> selectList() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<FreeReply> list = new ArrayList<FreeReply>();
    
    try {
      conn = getConnection();
      stmt = conn.createStatement();
      String sql = "select * from " + TABLE_NAME + " order by reg_date desc";
      
      rs = stmt.executeQuery(sql);
      FreeReply dto = null;
      while (rs.next()) {
        int replyNo = rs.getInt("reply_no");
        int articleNo = rs.getInt("article_no");
        int empNo = rs.getInt("emp_no");
        String regDate = rs.getString("reg_date");
        String reply = rs.getString("reply");
        
        dto = new FreeReply(replyNo, articleNo, empNo, regDate, reply);
        list.add(dto);
      }
      
    } catch (SQLException e) {
      System.out.println("Error : 댓글 조회 오류");
      e.printStackTrace();
    } finally {
      factory.close(rs, stmt, conn);
    }
    return list;
  }
  
  /** 글 등록 */
  public int insert(FreeReply dto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      String sql = "insert into " + TABLE_NAME + " values(?, ?, ?, sysdate, ?)";
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setInt(1, dto.getReplyNo());
      pstmt.setInt(2, dto.getArticleNo());
      pstmt.setInt(3, dto.getEmpNo());
      pstmt.setString(4, dto.getReply());
      
      row = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      System.out.println("Error : 댓글 등록 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
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
      String sql = "select Max(reply_no) from " + TABLE_NAME;
      rs = stmt.executeQuery(sql);
      
      if (rs.next()) {
        no = rs.getInt(1) + 1;
      }
    } catch (SQLException e) {
      System.out.println("Error : reply_no 설정 오류");
      e.printStackTrace();
    } finally {
      factory.close(rs, stmt, conn);
    }
    return no;
  }
  
  /** 댓글 수정 */
  public int update(FreeReply dto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      String sql = "update " + TABLE_NAME + " set reply = ? where reply_no = ?";
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setString(1, dto.getReply());
      pstmt.setInt(2, dto.getReplyNo());
      
      row = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      System.out.println("Error : 댓글 수정 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
  /** 댓글 삭제 */
  public int delete(int replyNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int row = 0;
    
    try {
      conn = getConnection();
      String sql = "delete " + TABLE_NAME + " where reply_no = ?";
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setInt(1, replyNo);
      
      row = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      System.out.println("Error : 댓글 삭제 오류");
      e.printStackTrace();
    } finally {
      factory.close(pstmt, conn);
    }
    return row;
  }
  
  /**
   * 댓글 수 세기
   * @param articleNo
   * @return
   */
  public int countReply(int articleNo) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    int count = 0;
    
    try {
      conn = getConnection();
      stmt = conn.createStatement();
      String sql = "select count(reply_no) from " + TABLE_NAME + " where article_no = " + articleNo;
      rs = stmt.executeQuery(sql);
      
      if (rs.next()) {
        count = rs.getInt(1);
      }
    } catch (SQLException e) {
      System.out.println("Error : 댓글 수 카운트 오류");
      e.printStackTrace();
    } finally {
      factory.close(rs, stmt, conn);
    }
    return count;
  }
}

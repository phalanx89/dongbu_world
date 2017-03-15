/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class FreeReply {
  private int replyNo;
  private int articleNo;
  private int empNo;
  private String regDate;
  private String reply;
  private String userName;
  
  /**
   * 
   */
  public FreeReply() {
    super();
  }
  
  /**
   * @param replyNo
   * @param articleNo
   * @param empNo
   * @param regDate
   */
  public FreeReply(int replyNo, int articleNo, int empNo, String regDate) {
    super();
    this.replyNo = replyNo;
    this.articleNo = articleNo;
    this.empNo = empNo;
    this.regDate = regDate;
  }
  
  /**
   * @param replyNo
   * @param articleNo
   * @param empNo
   * @param regDate
   * @param reply
   */
  public FreeReply(int replyNo, int articleNo, int empNo, String regDate, String reply) {
    super();
    this.replyNo = replyNo;
    this.articleNo = articleNo;
    this.empNo = empNo;
    this.regDate = regDate;
    this.reply = reply;
  }
  
  /**
   * @return the replyNo
   */
  public int getReplyNo() {
    return replyNo;
  }
  
  /**
   * @param replyNo
   *          the replyNo to set
   */
  public void setReplyNo(int replyNo) {
    this.replyNo = replyNo;
  }
  
  /**
   * @return the articleNo
   */
  public int getArticleNo() {
    return articleNo;
  }
  
  /**
   * @param articleNo
   *          the articleNo to set
   */
  public void setArticleNo(int articleNo) {
    this.articleNo = articleNo;
  }
  
  /**
   * @return the empNo
   */
  public int getEmpNo() {
    return empNo;
  }
  
  /**
   * @param empNo
   *          the empNo to set
   */
  public void setEmpNo(int empNo) {
    this.empNo = empNo;
  }
  
  /**
   * @return the regDate
   */
  public String getRegDate() {
    return regDate;
  }
  
  /**
   * @param regDate
   *          the regDate to set
   */
  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }
  
  /**
   * @return the reply
   */
  public String getReply() {
    return reply;
  }
  
  /**
   * @param reply
   *          the reply to set
   */
  public void setReply(String reply) {
    this.reply = reply;
  }
  
  
  
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(replyNo);
    builder.append(", ");
    builder.append(articleNo);
    builder.append(", ");
    builder.append(empNo);
    builder.append(", ");
    builder.append(regDate);
    builder.append(", ");
    builder.append(reply);
    return builder.toString();
  }
}

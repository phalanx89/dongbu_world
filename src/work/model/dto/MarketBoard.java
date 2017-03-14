/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class MarketBoard {
  private int articleNo;
  private String title;
  private int empNo;
  private String regDate;
  private String content;
  private int hits;
  private String isNotice;
  private String userName;
  
  /**
   * 
   */
  public MarketBoard() {
    super();
  }
  
  /**
   * @param articleNo
   * @param title
   * @param empNo
   * @param regDate
   * @param hits
   * @param isNotice
   */
  public MarketBoard(int articleNo, String title, int empNo, String regDate, int hits, String isNotice, String userName) {
    super();
    this.articleNo = articleNo;
    this.title = title;
    this.empNo = empNo;
    this.regDate = regDate;
    this.hits = hits;
    this.isNotice = isNotice;
    this.userName = userName;
  }
  
  /**
   * @param articleNo
   * @param title
   * @param empNo
   * 
   * @param regDate
   * @param content
   * @param hits
   * @param isNotice
   */
  public MarketBoard(int articleNo, String title, int empNo, String regDate, String content, int hits, String isNotice, String userName) {
    super();
    this.articleNo = articleNo;
    this.title = title;
    this.empNo = empNo;
    this.regDate = regDate;
    this.content = content;
    this.hits = hits;
    this.isNotice = isNotice;
    this.userName = userName;
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
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
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
   * @return the content
   */
  public String getContent() {
    return content;
  }
  
  /**
   * @param content
   *          the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }
  
  /**
   * @return the hits
   */
  public int getHits() {
    return hits;
  }
  
  /**
   * @param hits
   *          the hits to set
   */
  public void setHits(int hits) {
    this.hits = hits;
  }
  
  /**
   * @return the isNotice
   */
  public String getIsNotice() {
    return isNotice;
  }
  
  /**
   * @param isNotice
   *          the isNotice to set
   */
  public void setIsNotice(String isNotice) {
    this.isNotice = isNotice;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("MarketBoard [articleNo=");
    builder.append(articleNo);
    builder.append(", title=");
    builder.append(title);
    builder.append(", empNo=");
    builder.append(empNo);
    builder.append(", regDate=");
    builder.append(regDate);
    builder.append(", content=");
    builder.append(content);
    builder.append(", hits=");
    builder.append(hits);
    builder.append(", isNotice=");
    builder.append(isNotice);
    builder.append(", userName=");
    builder.append(userName);
    builder.append("]");
    return builder.toString();
  }
  
}

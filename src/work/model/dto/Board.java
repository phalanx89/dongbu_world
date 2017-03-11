/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class Board {

	private int articleNo;
	private String title;
	private int empNo;
	private String regDate;
	private String content;

	/**
	 * 
	 */
	public Board() {
		super();
	}

	/**
	 * @param articleNo
	 * @param title
	 * @param empNo
	 * @param regDate
	 */
	public Board(int articleNo, String title, int empNo, String regDate) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.empNo = empNo;
		this.regDate = regDate;
	}

	/**
	 * @param articleNo
	 * @param title
	 * @param empNo
	 * @param regDate
	 * @param content
	 */
	public Board(int articleNo, String title, int empNo, String regDate, String content) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.empNo = empNo;
		this.regDate = regDate;
		this.content = content;
	}

	/**
	 * @return the articleNo
	 */
	public int getArticleNo() {
		return articleNo;
	}

	/**
	 * @param articleNo
	 *            the articleNo to set
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
	 *            the title to set
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
	 *            the empNo to set
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
	 *            the regDate to set
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
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Board [articleNo=" + articleNo + ", title=" + title + ", empNo=" + empNo + ", regDate=" + regDate
				+ ", content=" + content + "]";
	}
}

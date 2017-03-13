/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class FreeBoard {
	private int articleNo;
	private String title;
	private int empNo;
	private String regDate;
	private String content;
	private int hits;
	private String isNotice;

	/**
	 * 
	 */
	public FreeBoard() {
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
	public FreeBoard(int articleNo, String title, int empNo, String regDate, int hits, String isNotice) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.empNo = empNo;
		this.regDate = regDate;
		this.hits = hits;
		this.isNotice = isNotice;
	}

	/**
	 * @param articleNo
	 * @param title
	 * @param empNo
	 * @param regDate
	 * @param content
	 * @param hits
	 * @param isNotice
	 */
	public FreeBoard(int articleNo, String title, int empNo, String regDate, String content, int hits,
			String isNotice) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.empNo = empNo;
		this.regDate = regDate;
		this.content = content;
		this.hits = hits;
		this.isNotice = isNotice;
	}

	/**
	 * @return the articleNo
	 */
	public int getArticleNo() {
		return articleNo;
	}

	/**
	 * @param articleNo the articleNo to set
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
	 * @param title the title to set
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
	 * @param empNo the empNo to set
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
	 * @param regDate the regDate to set
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
	 * @param content the content to set
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
	 * @param hits the hits to set
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
	 * @param isNotice the isNotice to set
	 */
	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FreeBoard [articleNo=" + articleNo + ", title=" + title + ", empNo=" + empNo + ", regDate=" + regDate
				+ ", content=" + content + ", hits=" + hits + ", isNotice=" + isNotice + "]";
	}

	
}

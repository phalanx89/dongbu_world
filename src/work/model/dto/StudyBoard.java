/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class StudyBoard extends Board {
	private int hits;
	private String isNotice;

	/**
	 * 
	 */
	public StudyBoard() {
		super();
	}

	/**
	 * @param hits
	 * @param isNotice
	 */
	public StudyBoard(int hits, String isNotice) {
		super();
		this.hits = hits;
		this.isNotice = isNotice;
	}

	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits
	 *            the hits to set
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
	 *            the isNotice to set
	 */
	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudyBoard [hits=" + hits + ", isNotice=" + isNotice + "]";
	}

}

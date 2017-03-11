/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class RestaurantReply extends Reply {
	private int rate;

	/**
	 * 
	 */
	public RestaurantReply() {
		super();
	}

	/**
	 * @param rate
	 */
	public RestaurantReply(int rate) {
		super();
		this.rate = rate;
	}

	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RestaurantReply [rate=" + rate + "]";
	}
	
}

package work.model.dto;

/**
 * 
 * @author DB
 *
 */
public class Restaurant extends Board {
	private String restaurant;
	private String menuType;
	private String price;
	private int rate;
	private String address;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private int takeMin;

	/**
	 * 
	 */
	public Restaurant() {
		super();
	}

	/**
	 * @param restaurant
	 * @param menuType
	 * @param rate
	 * @param address
	 */
	public Restaurant(String restaurant, String menuType, int rate, String address) {
		super();
		this.restaurant = restaurant;
		this.menuType = menuType;
		this.rate = rate;
		this.address = address;
	}

	/**
	 * @param restaurant
	 * @param menuType
	 * @param price
	 * @param rate
	 * @param address
	 * @param image1
	 * @param image2
	 * @param image3
	 * @param image4
	 * @param image5
	 * @param takeMin
	 */
	public Restaurant(String restaurant, String menuType, String price, int rate, String address, String image1,
			String image2, String image3, String image4, String image5, int takeMin) {
		super();
		this.restaurant = restaurant;
		this.menuType = menuType;
		this.price = price;
		this.rate = rate;
		this.address = address;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		this.takeMin = takeMin;
	}

	/**
	 * @return the restaurant
	 */
	public String getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant
	 *            the restaurant to set
	 */
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType
	 *            the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the image1
	 */
	public String getImage1() {
		return image1;
	}

	/**
	 * @param image1
	 *            the image1 to set
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/**
	 * @return the image2
	 */
	public String getImage2() {
		return image2;
	}

	/**
	 * @param image2
	 *            the image2 to set
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	/**
	 * @return the image3
	 */
	public String getImage3() {
		return image3;
	}

	/**
	 * @param image3
	 *            the image3 to set
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}

	/**
	 * @return the image4
	 */
	public String getImage4() {
		return image4;
	}

	/**
	 * @param image4
	 *            the image4 to set
	 */
	public void setImage4(String image4) {
		this.image4 = image4;
	}

	/**
	 * @return the image5
	 */
	public String getImage5() {
		return image5;
	}

	/**
	 * @param image5
	 *            the image5 to set
	 */
	public void setImage5(String image5) {
		this.image5 = image5;
	}

	/**
	 * @return the takeMin
	 */
	public int getTakeMin() {
		return takeMin;
	}

	/**
	 * @param takeMin
	 *            the takeMin to set
	 */
	public void setTakeMin(int takeMin) {
		this.takeMin = takeMin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Restaurant [restaurant=" + restaurant + ", menuType=" + menuType + ", price=" + price + ", rate=" + rate
				+ ", address=" + address + ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3
				+ ", image4=" + image4 + ", image5=" + image5 + ", takeMin=" + takeMin + "]";
	}

}

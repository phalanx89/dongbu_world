package work.model.dto;

/**
 * 
 * @author ¿Ã¡ˆæ∆
 *
 */
public class Restaurant {
	private int article_no;
	private String restaurant;
	private String title;
	private int emp_no;
	private String menuType;
	private String price;
	private int rate;
	private String address;
	private String regDate;
	private String content;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private int takeMin;
	private String coords;

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
	 * @param article_no
	 * @param restaurant
	 * @param title
	 * @param emp_no
	 * @param menuType
	 * @param price
	 * @param rate
	 * @param address
	 * @param regDate
	 * @param image1
	 * @param image2
	 * @param image3
	 * @param image4
	 * @param image5
	 * @param takeMin
	 * @param coords
	 */
	public Restaurant(int article_no, String restaurant, String title, int emp_no, String menuType, String price,
			int rate, String address, String regDate, String content, String image1, String image2, String image3, String image4,
			String image5, int takeMin, String coords) {
		super();
		this.article_no = article_no;
		this.restaurant = restaurant;
		this.title = title;
		this.emp_no = emp_no;
		this.menuType = menuType;
		this.price = price;
		this.rate = rate;
		this.address = address;
		this.regDate = regDate;
		this.content = content;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		this.takeMin = takeMin;
		this.coords = coords;
	}

	/**
	 * @return the article_no
	 */
	public int getArticle_no() {
		return article_no;
	}

	/**
	 * @param article_no the article_no to set
	 */
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	/**
	 * @return the restaurant
	 */
	public String getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
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
	 * @return the emp_no
	 */
	public int getEmp_no() {
		return emp_no;
	}

	/**
	 * @param emp_no the emp_no to set
	 */
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType the menuType to set
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
	 * @param price the price to set
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
	 * @param rate the rate to set
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
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	public String geContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the image1
	 */
	public String getImage1() {
		return image1;
	}

	/**
	 * @param image1 the image1 to set
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
	 * @param image2 the image2 to set
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
	 * @param image3 the image3 to set
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
	 * @param image4 the image4 to set
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
	 * @param image5 the image5 to set
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
	 * @param takeMin the takeMin to set
	 */
	public void setTakeMin(int takeMin) {
		this.takeMin = takeMin;
	}

	/**
	 * @return the coords
	 */
	public String getCoords() {
		return coords;
	}

	/**
	 * @param coords the coords to set
	 */
	public void setCoords(String coords) {
		this.coords = coords;
	}

	

}

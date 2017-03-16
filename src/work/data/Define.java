/**
 * 
 */
package work.data;

/**
 * 상수 정의 클래스
 * 
 * @author DB
 *
 */
public class Define {
	public static final double COORD_DONGBU_LAT = 37.5093347;
	public static final double COORD_DONGBU_LNG = 127.057700;
	public static final String[] COORDS_DONGBU = {"37.5093347", "127.057700"}; 
	
	public static final String[] ARY_MENUS = {"한식", "일식", "분식", "중식", "양식", "아시안", "술집", "퓨전음식", "치킨", "족발/보쌈", "피자/버거"};
	public static final String[] ARY_PRICES = {"1", "2", "3"};
	public static final String[] ARY_DISTANCES = {"1", "3", "5", "10"};
	public static final String[] ARY_RATES = {"1", "2", "3", "4", "5"};
	
	public static final String ACTION_SEARCH_RESTAURANT = "searchRestaurant";
	public static final String ACTION_COLUMNSEARCH_RESTAURANT = "searchRestaurantByColumn";
	public static final String ACTION_REGISTER_RESTAURANT = "registerRestaurant";
	public static final String ACTION_SELECT_RESTAURANT_LIST = "selectRestaurantList";
	public static final String ACTION_DELETE_RESTAURANT = "deleteRestaurant";
	public static final String ACTION_RECOMMEND_RESTAURANT = "recommendRestaurant";
}

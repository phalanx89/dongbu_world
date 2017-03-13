/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.RestaurantDAO;
import work.model.dto.Restaurant;

/**
 * @author DB
 *
 */
public class RestaurantService {
	private RestaurantDAO dao = RestaurantDAO.getInstance();
	
	public RestaurantService() {
		
	}

	public ArrayList<Restaurant> selectRestaurantList(String[] params) {
		return dao.selectRestaurantListUsingWhere(getWhereQuery("menu_type", params, "or"));
	}

	/**
	 * where 절 반환
	 * 
	 * @param columnName
	 *            컬럼명
	 * @param params
	 *            비교할 값
	 * @param infix
	 *            'and' 인지 'or' 인지
	 * @return
	 */
	public String getWhereQuery(String columnName, String[] params, String infix) {
		String query = "where ";

		for (int i = 0; i < params.length; i++) {
			query += String.format("%s = '%s'", columnName, params[i]);
			
			if (i < params.length - 1) {
				query += " " + infix + " ";
			}
		}

		return query;
	}
}

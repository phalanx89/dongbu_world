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
  
  public Restaurant selectOne(int articleNo) {
    return dao.selectOne(articleNo);
  }
  
  public ArrayList<Restaurant> selectRestaurantList() {
    return dao.selectRestaurantList();
  }
  
  public ArrayList<Restaurant> selectRestaurantList(String[] aryMenus, String[] aryPrices, String[] aryDistances, String[] aryRates) {
    return dao.selectRestaurantListUsingWhere("where " + getEqualQuery("menu_type", aryMenus, "or") 
    + (aryPrices != null ? " and " : "") + getLessThanQuery("price", aryPrices, "or") 
    + (aryDistances != null ? " and " : "") + getLessThanQuery("take_min", aryDistances, "or") 
    + (aryRates != null ? " and " : "") + getLessThanQuery("rate", aryRates, "or"));
  }
  
	public ArrayList<Restaurant> selectListByColumn(String columnName, String keyword) {
	  return dao.selectListByColumn(columnName, keyword);
	}
	
  public int deleteRestaurant(int articleNo) {
    return dao.delete(articleNo);
  }
  
  public ArrayList<String> getRankList() {
    return dao.getRankList();
  }
  
  /**
   * where 절 반환
   * 
   * @param columnName
   *          컬럼명
   * @param params
   *          비교할 값
   * @param infix
   *          'and' 인지 'or' 인지
   * @return
   */
  public String getEqualQuery(String columnName, String[] params, String infix) {
    String query = "";
    
    if (params != null) {
      query += "(";
      
      for (int i = 0; i < params.length; i++) {
        query += String.format("%s = '%s'", columnName, params[i]);
        
        if (i < params.length - 1) {
          query += " " + infix + " ";
        }
      }
      
      query += ")";
    }
    
    return query;
  }
  
  public String getLessThanQuery(String columnName, String[] params, String infix) {
    String query = "";
    
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        query += String.format("%s <= '%s'", columnName, params[i]);
        
        if (i < params.length - 1) {
          query += " " + infix + " ";
        }
      }
    }
    
    return query;
  }
  
  public int insert(Restaurant dto) {
    return dao.insert(dto);
  }
}

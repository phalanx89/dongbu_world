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
  
  public int deleteRestaurant(int articleNo) {
    return dao.delete(articleNo);
  }
  
  public int getListSize() {
    return dao.getListSize();
  }
  
  /**
   * where �� ��ȯ
   * 
   * @param columnName
   *          �÷���
   * @param params
   *          ���� ��
   * @param infix
   *          'and' ���� 'or' ����
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

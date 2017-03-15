/**
 * 
 */
package work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.data.Define;
import work.model.dto.Restaurant;
import work.model.service.RestaurantService;
import work.util.Utility;

/**
 * ���� ���� ��û ó�� Controller
 * 
 * @author DB
 *
 */
public class FoodController extends HttpServlet {
  private RestaurantService mRestaurantService = new RestaurantService();
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    process(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // post ��û�� ���� �ѱ� ���ڵ� ����
    request.setCharacterEncoding("euc-kr");
    process(request, response);
  }
  
  /**
   * ���� ��� ��û�� ����ϴ� ���� �޼���
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    System.out.println("\n###action : " + action);
    
    if (action != null) {
      switch (action) {
        case Define.ACTION_SEARCH_RESTAURANT:
          searchRestaurant(request, response);
          break;
        case Define.ACTION_REGISTER_RESTAURANT:
          registerRestaurant(request, response);
          break;
        case Define.ACTION_SELECT_RESTAURANT_LIST:
          selectRestaurantList(request, response);
          break;
        default:
          
          break;
      }
    } else {
      System.out.println("�������� �ʴ� ��û�Դϴ�.");
    }
  }
  
  /**
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  private void searchRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] aryMenus = request.getParameterValues("cbxMenus");
    
    if (aryMenus == null) {
      System.out.println("'cbxMenus' is not exist in request");
      // forwardPage("error.jsp", request, response);
      return;
    }
    
    if (aryMenus.length == 0) {
      System.out.println("cbxMenus.length == 0");
      forwardPage("restaurant_main.jsp", request, response);
      return;
    }
    
    ArrayList<Restaurant> aryRestaurants = mRestaurantService.selectRestaurantList(aryMenus);
    request.setAttribute("aryRestaurants", aryRestaurants);
    forwardPage("restaurant_main.jsp", request, response);
    return;
  }
  
  private void registerRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    Restaurant dto = new Restaurant();
    
    if (session == null) {
      System.out.println("session is expired. Please login.");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    dto.setRestaurant(request.getParameter("restaurant"));
    dto.setTitle(request.getParameter("title"));
    dto.setEmp_no(Integer.valueOf((session.getAttribute("empNo").toString())));
    dto.setMenuType(request.getParameter("menuType"));
    dto.setPrice(request.getParameter("price"));
    dto.setRate(0);
    dto.setRegDate("");
    dto.setAddress(request.getParameter("address"));
    dto.setContent(request.getParameter("content"));
    dto.setCoords(request.getParameter("coords"));
    String[] latlng = Utility.getLatLng(dto.getCoords());
    int takeMin = Utility.getTimeWithCoords(Define.COORDS_DONGBU, latlng);
    dto.setTakeMin(takeMin);
    
    mRestaurantService.insert(dto);
    
    System.out.println(dto.toString());
    
    selectRestaurantList(request, response);
  }
  
  private void selectRestaurantList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    ArrayList<Restaurant> list = null;
    
    if (session == null) {
      System.out.println("session is expired. Please login.");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    list = mRestaurantService.selectRestaurantList();
    
    request.setAttribute("list", list);
    forwardPage("restaurant_main.jsp", request, response);
  }
  
  private void forwardPage(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher nextView = request.getRequestDispatcher(url);
    nextView.forward(request, response);
  }
}

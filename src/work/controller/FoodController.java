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
import work.model.dto.Board;
import work.model.dto.Restaurant;
import work.model.service.RestaurantService;
import work.util.Utility;

/**
 * 맛집 관련 요청 처리 Controller
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
    // post 요청에 대한 한글 인코딩 설정
    request.setCharacterEncoding("euc-kr");
    process(request, response);
  }
  
  /**
   * 웹의 모든 요청을 담당하는 서비스 메서드
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
        case Define.ACTION_DELETE_RESTAURANT:
          deleteRestaurant(request, response);
          break;
        case Define.ACTION_COLUMNSEARCH_RESTAURANT:
          searchRestaurantByColumn(request, response);
          break;
      }
    } else {
      System.out.println("지원하지 않는 요청입니다.");
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
    String[] aryPrices = request.getParameterValues("cbxPrices");
    String[] aryDistances = request.getParameterValues("cbxDistances");
    String[] aryRates = request.getParameterValues("cbxRates");
    
    if (aryMenus == null && aryPrices == null && aryDistances == null && aryRates == null) {
      System.out.println("parameter is not exist in request");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    ArrayList<Restaurant> aryRestaurants = mRestaurantService.selectRestaurantList(aryMenus, aryPrices, aryDistances, aryRates);
    request.setAttribute("list", aryRestaurants);
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
    dto.setRate(Integer.valueOf(request.getParameter("rate")));
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
  
  private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    
    if (session == null) {
      System.out.println("session is expired. Please login.");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    int articleNo = Integer.valueOf(request.getParameter("articleNo").toString());
    mRestaurantService.deleteRestaurant(articleNo);
    
    selectRestaurantList(request, response);
  }
  
  /**
   * 컬럼명으로 검색하기
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  private void searchRestaurantByColumn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    
    if (session == null) {
      System.out.println("session is expired. Please login.");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    String columnName = request.getParameter("columnName");
    String keyword = request.getParameter("keyword");
    
    if (keyword == null || keyword.trim().length() == 0) {
      request.setAttribute("message", "검색할 내용을 입력하세요");
      forwardPage("fail.jsp", request, response);
      return;
    }
    
    ArrayList<Restaurant> list = mRestaurantService.selectListByColumn(columnName, keyword);
    request.setAttribute("list", list);
    forwardPage("restaurant_main.jsp", request, response);
  }
  

  
  private void forwardPage(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher nextView = request.getRequestDispatcher(url);
    nextView.forward(request, response);
  }
}

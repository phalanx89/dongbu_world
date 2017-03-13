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

import work.data.Define;
import work.model.dto.Restaurant;
import work.model.service.RestaurantService;

/**
 * 맛집 관련 요청 처리 Controller
 * @author DB
 *
 */
public class FoodController extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("\n###action : " + action);
		
		if (action != null) {
			switch (action) {
			case Define.ACTION_SEARCH_RESTAURANT:
				searchRestaurant(request, response);
				break;
			default:
				
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
	private void searchRestaurant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] aryMenus = request.getParameterValues("cbxMenus");
		 
		if (aryMenus == null) {
			System.out.println("'cbxMenus' is not exist in request");
			//forwardPage("error.jsp", request, response);
			return;
		}
		
		if (aryMenus.length == 0) {
			System.out.println("cbxMenus.length == 0");
			forwardPage("restuarant_main.jsp", request, response);
			return;
		}
		
		RestaurantService service = new RestaurantService();
		ArrayList<Restaurant> aryRestaurants = service.selectRestaurantList(aryMenus);
		request.setAttribute("aryRestaurants", aryRestaurants);
		forwardPage("restuarant_main.jsp", request, response);
		return;
	}
	
	private void forwardPage(String url, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher nextView = request.getRequestDispatcher(url);
		nextView.forward(request, response);
	}
}

package work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * Servlet implementation class FrontController
 * 
 * ## web controller 클래스 - servlet - 역할 1. 요청파악 2. 요청데이터 추출 3. 요청데이터 검증 4.
 * Model에게 요청 처리 의뢰 5. 요청결과 수신 후 응답을 위한 설정 작업 6. 응답페이지 이동 - 성공 : success.jsp -
 * 실패 : fail.jsp
 */
public class FrontController extends HttpServlet {
	// private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

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
		request.setCharacterEncoding("euc-kr");
		process(request, response);
	}

	/**
	 * doGet(), doPost() 처리
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action=" + action);

		if (action == null) {

		}

		switch (action) {
		case "login":
			login(request, response);
			break;
		case "joinSave":
			joinSave(request, response);
			break;
		case "findUserId":
			findUserId(request, response);
			break;
		case "findUserPw":
			findUserPw(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "showMyInfo":
			showMyInfo(request, response);
			break;
		case "selectAll":
			selectAll(request, response);
			break;
		case "updateOne":
			updateOne(request, response);
			break;
		case "updateSave":
			updateSave(request, response);
			break;
		case "deleteOne":
			deleteOne(request, response);
			break;
		default:

			break;
		}
	}

	/**
	 * login 요청 페이지 : login.jsp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		if (userId == null || userId.trim().length() < 6) {
			request.setAttribute("message", "userId는 6자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPw == null || userPw.trim().length() < 6) {
			request.setAttribute("message", "userPw는 6자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		HashMap<String, String> map = memberService.login(userId, userPw);

		if (map != null) {
			//로그인 성공
			//session 객체 얻어옴
			HttpSession session = request.getSession(true);
			//session 객체에 필요한 상태정보 설정, userId, username, grade
			session.setAttribute("userId", userId);
			session.setAttribute("username", map.get("username"));
			session.setAttribute("grade",  map.get("grade"));
			
			//응답페이지로 이동
			response.sendRedirect("loginComplete.jsp");
			//request.getRequestDispatcher("success.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "일치하는 정보가 없음");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			session.removeAttribute(name);
		}
		
		session.invalidate();
		
		//페이지 이동
		response.sendRedirect("index.jsp");
	}
	
	private void showMyInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		Member dto = memberService.selectOneInfo(session.getAttribute("userId").toString());
		
		if (dto != null) {
			session.setAttribute("dto", dto);
			
			response.sendRedirect("showMyInfo.jsp");
		} else {
			
		}
	}
	
	private void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Member> list = memberService.selectList();
		
		if (list.size() > 0) {
			HttpSession session = request.getSession(false);
			
			session.removeAttribute("list");
			session.setAttribute("list", list);
			
			response.sendRedirect("showAllInfo2.jsp");
		} else {
			
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		HttpSession session = request.getSession(false);
		Member dto = memberService.selectOneInfo(userId);
		
		session.setAttribute("dto", dto);		
		
		response.sendRedirect("updateInfo.jsp");
	}
	
	private void updateSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String entryDate = request.getParameter("entryDate");
		String grade = request.getParameter("grade");
		int mileage = Integer.valueOf(request.getParameter("mileage"));
		String manager = request.getParameter("manager");
		
		Member dto = new Member(	userId, userPw, username, mobile, email, entryDate, grade, mileage, manager);
		System.out.println(String.format("%d rows", memberService.updateOne(dto)));
		
		selectAll(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println(String.format("delete %d rows", memberService.deleteOne(userId)));
		
		selectAll(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void joinSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userPwConfirm = request.getParameter("userPwConfirm");
		String username = request.getParameter("username");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");

		if (userId == null || userId.trim().length() < 6) {
			request.setAttribute("message", "userId는 6자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPw == null || userPw.trim().length() < 6) {
			request.setAttribute("message", "userPw는 6자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPwConfirm == null || !userPw.equals(userPwConfirm)) {
			request.setAttribute("message", "비밀번호 재입력 오류");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (username == null || username.trim().length() < 2) {
			request.setAttribute("message", "username는 2자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if ((mobile1 == null || mobile1.trim().length() < 3) || (mobile2 == null || mobile2.trim().length() < 4)
				|| (mobile3 == null || mobile3.trim().length() < 4)) {
			request.setAttribute("message", "전화번호 오류");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email1 == null || email2 == null) {
			request.setAttribute("message", "이메일 주소 오류");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		// test
		// 서블릿으로 뷰제작
		// response.setContentType("text/html;charset=euc-kr");
		// PrintWriter out = response.getWriter();
		// out.println("<html><head><title></title></head><body>"
		// + "회원가입성공 !짠</body></html>");
		// test

		request.setAttribute("message", userId + "님 회원가입 성공");
		System.out.println(userId + ", " + username);
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		if (username == null || username.trim().length() < 2) {
			request.setAttribute("message", "username는 2자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email == null || email.trim().length() > 20) {
			request.setAttribute("message", "email는 20자리 이하");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("message", username + "님 아이디 찾기 성공?");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findUserPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");

		if (userId == null || userId.trim().length() < 6) {
			request.setAttribute("message", "username는 2자리 이상");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email == null || email.trim().length() > 20) {
			request.setAttribute("message", "email는 20자리 이하");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("message", userId + "님 비밀번호 찾기 성공?");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void selectOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		// 3. 요청 데이터 검증 : null, trim, length
		// 아이디: 필수, 6자리 이상
		if (userId == null || userId.trim().length() < 6) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "아이디는 6자리 이상입니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// 요청데이터 검증 성공 : success.jsp
		request.setAttribute("message", userId + "님 상세 정보 조회 성공");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void updateUserPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String newPw = request.getParameter("newPw");

		// 아이디: 필수, 6자리 이상
		if (userId == null || userId.trim().length() < 6) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "아이디는 6자리 이상입니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		// 비밀번호 : 필수, 6자리 이상
		if (userPw == null || userPw.trim().length() < 6) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "비밀번호는 6자리 이상입니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		if (newPw != userPw) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "비밀번호가 일치하지 않습니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// 요청데이터 검증 성공 : success.jsp
		request.setAttribute("message", userId + "님 비밀번호 변경 성공");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		// 3. 요청 데이터 검증 : null, trim, length
		// 아이디: 필수, 6자리 이상
		if (userId == null || userId.trim().length() < 6) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "아이디는 6자리 이상입니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// 요청데이터 검증 성공 : success.jsp
		request.setAttribute("message", userId + "님 정보 삭제 성공");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String entryDate = request.getParameter("entryDate");
		String grade = request.getParameter("grade");
		String mileage = request.getParameter("mileage");
		String manager = request.getParameter("manager");

		// 3. 요청 데이터 검증 : null, trim, length
		// 아이디: 필수, 6자리 이상
		if (userId == null || userId.trim().length() < 6 || userPw == null || userPw.trim().length() < 6
				|| username == null || username.trim().length() == 0 || mobile == null || mobile.trim().length() == 0
				|| email == null || email.trim().length() == 0 || entryDate == null || entryDate.trim().length() == 0
				|| grade == null || grade.trim().length() == 0) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "필수 입력항목을 입력하세요");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
		} else {
			System.out.println(userId + ", " + userPw + ", " + username + ", " + mobile + ", " + email + ", "
					+ entryDate + ", " + grade + ", " + mileage + ", " + manager);
			// servlet으로 view 페이지
			// 자바코드안에 html 태그 => 분리설계
			// 서블릿 => controller
			// jsp = html tag 기반의 view 담당
			// 응답위한 mime-type 및 한글인코딩 설정
			response.setContentType("text/html;charset=euc-kr");
			// 응답위한 출력스트림 생성
			// PrintWriter out = response.getWriter();
			//
			// out.println("<html><head><title></title></head><body>");
			// out.println("<h3>회원가입성공</h3>");
			// out.println("로그인후 회원전용 서비스를 이용하시기 바랍니다.");
			// out.println("</body></html>");
		}

		// 요청데이터 검증 성공 : success.jsp
		request.setAttribute("message", userId + "님 회원가입 성공");
		request.getRequestDispatcher("success.jsp").forward(request, response);

	}

}

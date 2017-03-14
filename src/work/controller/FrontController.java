package work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.FreeBoard;
import work.model.dto.Member;
import work.model.service.FreeBoardService;
import work.model.service.MemberService;
import work.util.Utility;

/**
 * 1. 요청 파악 => action=00000 2. 요청 데이터 추출 3. 요청 데이터 검증 4. Model에게 요청 처리 의뢰 5.
 * 요청결과 받아서 응답위한 설정 6. 응답페이지 이동 => 성공 => 실패
 */
public class FrontController extends HttpServlet {

	public MemberService mservice = new MemberService();
	public FreeBoardService fbservice = new FreeBoardService();

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
			case "selectFreeList":
				selectFreeList(request, response);
				break;
			case "registerFreeByAdmin":
				registerFreeByAdmin(request, response);
				break;
			case "selectListByColumn":
				selectListByColumn(request, response);
				break;
			case "login":
				login(request, response);
				break;
			case "findUserPw":
				findUserPw(request, response);
				break;
			case "selectList":
				selectList(request, response);
				break;
			case "selectOne":
				selectOne(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "logout":
				logout(request, response);
				break;
			case "myInfo":
				myInfo(request, response);
				break;
			// case "update":
			// update(request, response);
			// break;
			}
		} else {
			System.out.println("지원하지 않는 요청입니다.");
		}
	}

	/**
	 * 로그인 회원 체크
	 * 
	 * @param request
	 * @param response
	 * @return true 로그인 사용자 false 미 로그인
	 */
	public boolean isAuth(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
			return true;
		} else {
			return false;
		}
	}

	/** 자유게시판 글 목록 조회 */
	protected void selectFreeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (isAuth(request, response)) { // 로그인 사용자 권한 체크
			ArrayList<FreeBoard> list = fbservice.selectList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("boardMain.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	}

	/** 관리자의 자유게시판 글 등록 */
	protected void registerFreeByAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 하지 않은 사용자 오류 처리
		if (!isAuth(request, response)) {
			request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		// 관리자가 아니면 회원의 글 등록 메서드로 보내기
		if (!((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
			registerFreeByMember(request, response);
			return;
		}

		int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
		String userName = (String) request.getSession(false).getAttribute("userName");

		int articleNo = fbservice.selectMaxNo();
		String title = request.getParameter("title");
		String regDate = "sysdate";// = Utility.getTodayDate();
		String content = request.getParameter("content");
		int hits = 0;
		String isNotice = request.getParameter("isNotice");

		if (title == null || title.trim().length() == 0) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "제목을 입력하세요");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		if (isNotice == null || isNotice.trim().length() == 0) {
			request.setAttribute("message", "공지여부를 입력하세요");

			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		fbservice.register(new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName));
		request.setAttribute("message", userName + "님 글이 등록되었습니다.");
		request.setAttribute("ArticleNo", articleNo);
		request.setAttribute("title", title);
		request.setAttribute("empNo", empNo);
		request.setAttribute("regDate", regDate);
		request.setAttribute("content", content);
		request.setAttribute("hits", hits);
		request.setAttribute("isNotice", isNotice);
		request.setAttribute("userName", userName);
		request.getRequestDispatcher("articleReference.jsp").forward(request, response);
	}

	/** 회원의 자유게시판 글 등록 */
	protected void registerFreeByMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 하지 않은 사용자 오류 처리
		if (!isAuth(request, response)) {
			request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
		String userName = (String) request.getSession(false).getAttribute("userName");

		int articleNo = fbservice.selectMaxNo();
		String title = request.getParameter("title");
		String regDate = "sysdate";// = Utility.getTodayDate();
		String content = request.getParameter("content");
		int hits = 0;
		String isNotice = "N";

		if (title == null || title.trim().length() == 0) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "제목을 입력하세요");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		fbservice.register(new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName));
		request.setAttribute("message", userName + "님 글이 등록되었습니다.");
		request.setAttribute("ArticleNo", articleNo);
		request.setAttribute("title", title);
		request.setAttribute("empNo", empNo);
		request.setAttribute("regDate", regDate);
		request.setAttribute("content", content);
		request.setAttribute("hits", hits);
		request.setAttribute("isNotice", isNotice);
		request.setAttribute("userName", userName);
		request.getRequestDispatcher("articleReference.jsp").forward(request, response);
	}

	protected void selectListByColumn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 하지 않은 사용자 오류 처리
		if (!isAuth(request, response)) {
			request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		String column = request.getParameter("column");
		String keyword = request.getParameter("keyword");
		
//		if (column == null || column.trim().length() == 0) {
//			request.setAttribute("message", "검색할 키워드의 종류를 입력하세요");
//
//			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
//			nextView.forward(request, response);
//			return;
//		}
		
		if (keyword == null || keyword.trim().length() == 0) {
			request.setAttribute("message", "검색할 내용을 입력하세요");

			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		
		ArrayList<FreeBoard> list = fbservice.search(column, keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("boardMain.jsp").forward(request, response);

	}

	/**
	 * 내 정보 조회
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void myInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member dto = mservice.myInfo((int) session.getAttribute("empNo"));
		session.setAttribute("empNo", dto.getEmpNo());
		session.setAttribute("userPw", dto.getUserPw());
		session.setAttribute("userName", dto.getUserName());
		session.setAttribute("email", dto.getEmail());
		session.setAttribute("mobile", dto.getMobile());
		session.setAttribute("dept", dto.getDept());
		session.setAttribute("position", dto.getPosition());
		session.setAttribute("isAdmin", dto.getIsAdmin());

		response.sendRedirect("myInfo.jsp"); // jsp 페이지 안만들었음
	}

	/**
	 * 로그아웃
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("empNo");
		session.removeAttribute("userName");
		session.removeAttribute("isAdmin");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * 로그인 요청 서비스 메서드 요청페이지 : login.jsp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 요청 데이터 추출 => 요청페이지 : login.jsp
		String empNo = request.getParameter("empNo");
		String userPw = request.getParameter("userPw");

		// 3. 요청 데이터 검증 : null, trim, length
		// 아이디: 필수, 6자리 이상
		if (empNo == null || empNo.trim().length() < 6) {
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
		// 요청데이터 검증 성공 : success.jsp
		HashMap<String, String> map = mservice.login(Integer.valueOf(empNo), userPw);

		if (map != null) {
			// 로그인 요청 성공
			// 1. 세션객체 새로이 생성
			HttpSession session = request.getSession(); // true
			// 2. 세션객체 필요한 상태정보 설정 : 아이디, 이름, 등급
			session.setAttribute("empNo", empNo);
			session.setAttribute("userName", map.get("userName"));
			session.setAttribute("isAdmin", map.get("isAdmin"));

			// 3. 로그인 성공 응답페이지 이동 (loginSuccess.jsp)
			// 새로운 요청으로 이동 = session에 설정해놓았으므로 가능
			response.sendRedirect("index.jsp");

		} else {
			// 로그인 요청 실패
			request.setAttribute("message", "로그인 정보를 올바르게 입력하시기 바랍니다");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}

	}

	/**
	 * 회원가입
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	// protected void join(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // 2. 요청 데이터 추출 => 요청페이지 : join.jsp
	// String userId = request.getParameter("userId");
	// String userPw = request.getParameter("userPw");
	// String username = request.getParameter("username");
	// String mobile = request.getParameter("mobile");
	// String email = request.getParameter("email");
	//
	// // 3. 요청 데이터 검증 : null, trim, length
	// // 아이디: 필수, 6자리 이상
	// if (userId == null || userId.trim().length() < 6 || userPw == null ||
	// userPw.trim().length() < 6
	// || username == null || username.trim().length() == 0 || mobile == null ||
	// mobile.trim().length() == 0
	// || email == null || email.trim().length() == 0) {
	// // 실패 페이지 이동전에 오류메세지 속성 설정
	// request.setAttribute("message", "아이디는 6자리 이상으로 입력하세요");
	//
	// // 설정정보를 가지고 페이지 포워드(이동)
	// RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
	// nextView.forward(request, response);
	// }
	//
	// service.join(userId, userPw, username, mobile, email);
	//
	// HttpSession session = request.getSession(); //true
	// //2. 세션객체 필요한 상태정보 설정 : 아이디, 이름, 등급
	// session.setAttribute("userId", userId);
	// session.setAttribute("username", username);
	// session.setAttribute("mobile", mobile);
	// session.setAttribute("email", email);
	//
	// response.sendRedirect("joinSuccess.jsp");
	// // else {
	// // System.out.println(userId + ", " + userPw + ", " + username + ", " +
	// mobile + ", " + email);
	// // servlet으로 view 페이지
	// // 자바코드안에 html 태그 => 분리설계
	// // 서블릿 => controller
	// // jsp = html tag 기반의 view 담당
	// // 응답위한 mime-type 및 한글인코딩 설정
	// // response.setContentType("text/html;charset=euc-kr");
	// // 응답위한 출력스트림 생성
	// // PrintWriter out = response.getWriter();
	// //
	// // out.println("<html><head><title></title></head><body>");
	// // out.println("<h3>회원가입성공</h3>");
	// // out.println("로그인후 회원전용 서비스를 이용하시기 바랍니다.");
	// // out.println("</body></html>");
	// // }
	//
	// //요청데이터 검증 성공 : success.jsp
	// // request.setAttribute("message", userId + "님 회원가입 성공");
	// // request.getRequestDispatcher("success.jsp").forward(request,
	// response);
	//
	// }

	/**
	 * 비밀번호 찾기
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findUserPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 요청 데이터 추출 => 요청페이지 : login.jsp
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");

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

		// 이름 : 필수
		if (username == null || username.trim().length() == 0) {
			request.setAttribute("message", "이름을 입력하세요");
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		// 전화번호 : 필수
		if (mobile == null || mobile.trim().length() == 0) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "전화번호를 바르게 입력하세요");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		// 요청데이터 검증 성공 : success.jsp
		request.setAttribute("message", userId + "님 비밀번호찾기 성공");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	/**
	 * 회원정보 전체조회
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Member> list = mservice.selectList();
		HttpSession session = request.getSession(); // true
		session.setAttribute("list", list);

		response.sendRedirect("showAll.jsp");
	}

	/**
	 * 회원정보 상세조회
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	// protected void update(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String userId = request.getParameter("userId");
	// String userPw = request.getParameter("userPw");
	// String username = request.getParameter("username");
	// String mobile = request.getParameter("mobile");
	// String email = request.getParameter("email");
	// String grade = request.getParameter("grade");
	// int mileage = Integer.valueOf(request.getParameter("mileage"));
	// String manager = request.getParameter("manager");
	//
	// Member dto = new Member(userId, userPw, username, mobile, email, grade,
	// mileage, manager);
	// System.out.println(service.update(dto) + "rows");
	// response.sendRedirect("update.jsp");
	//
	//
	// }

	/**
	 * 암호 변경
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateUserPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String newPw = request.getParameter("newPw");
		String newPwAgain = request.getParameter("newPwAgain");

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
		if (userPw == null || userPw.trim().length() < 6 || newPw == null || newPw.trim().length() < 6) {
			// 실패 페이지 이동전에 오류메세지 속성 설정
			request.setAttribute("message", "비밀번호는 6자리 이상입니다");

			// 설정정보를 가지고 페이지 포워드(이동)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		if (newPw != newPwAgain) {
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

	/**
	 * 회원정보 삭제
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 * 관리자의 회원등록
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	// protected void register(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String userId = request.getParameter("userId");
	// String userPw = request.getParameter("userPw");
	// String username = request.getParameter("username");
	// String mobile = request.getParameter("mobile");
	// String email = request.getParameter("email");
	// String entryDate = request.getParameter("entryDate");
	// String grade = request.getParameter("grade");
	// String mileage = request.getParameter("mileage");
	// String manager = request.getParameter("manager");
	//
	// // 3. 요청 데이터 검증 : null, trim, length
	// // 아이디: 필수, 6자리 이상
	// if (userId == null || userId.trim().length() < 6 || userPw == null
	// || userPw.trim().length() < 6 || username == null ||
	// username.trim().length() == 0
	// || mobile == null || mobile.trim().length() == 0 || email == null
	// || email.trim().length() == 0 || entryDate == null ||
	// entryDate.trim().length() == 0
	// || grade == null || grade.trim().length() == 0) {
	// // 실패 페이지 이동전에 오류메세지 속성 설정
	// request.setAttribute("message", "필수 입력항목을 입력하세요");
	//
	// // 설정정보를 가지고 페이지 포워드(이동)
	// RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
	// nextView.forward(request, response);
	// } else {
	// System.out.println(userId + ", " + userPw + ", " + username + ", " +
	// mobile + ", "
	// + email + ", " + entryDate + ", " + grade + ", " + mileage + ", " +
	// manager);
	// // servlet으로 view 페이지
	// // 자바코드안에 html 태그 => 분리설계
	// // 서블릿 => controller
	// // jsp = html tag 기반의 view 담당
	// // 응답위한 mime-type 및 한글인코딩 설정
	// response.setContentType("text/html;charset=euc-kr");
	// // 응답위한 출력스트림 생성
	// // PrintWriter out = response.getWriter();
	// //
	// // out.println("<html><head><title></title></head><body>");
	// // out.println("<h3>회원가입성공</h3>");
	// // out.println("로그인후 회원전용 서비스를 이용하시기 바랍니다.");
	// // out.println("</body></html>");
	// }
	//
	// //요청데이터 검증 성공 : success.jsp
	// request.setAttribute("message", userId + "님 회원가입 성공");
	// request.getRequestDispatcher("success.jsp").forward(request,
	// response);
	//
	// }

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

}

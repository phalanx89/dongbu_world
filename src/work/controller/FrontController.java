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
 * ## web controller Ŭ���� - servlet - ���� 1. ��û�ľ� 2. ��û������ ���� 3. ��û������ ���� 4.
 * Model���� ��û ó�� �Ƿ� 5. ��û��� ���� �� ������ ���� ���� �۾� 6. ���������� �̵� - ���� : success.jsp -
 * ���� : fail.jsp
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
	 * doGet(), doPost() ó��
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
	 * login ��û ������ : login.jsp
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
			request.setAttribute("message", "userId�� 6�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPw == null || userPw.trim().length() < 6) {
			request.setAttribute("message", "userPw�� 6�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		HashMap<String, String> map = memberService.login(userId, userPw);

		if (map != null) {
			//�α��� ����
			//session ��ü ����
			HttpSession session = request.getSession(true);
			//session ��ü�� �ʿ��� �������� ����, userId, username, grade
			session.setAttribute("userId", userId);
			session.setAttribute("username", map.get("username"));
			session.setAttribute("grade",  map.get("grade"));
			
			//������������ �̵�
			response.sendRedirect("loginComplete.jsp");
			//request.getRequestDispatcher("success.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "��ġ�ϴ� ������ ����");
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
		
		//������ �̵�
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
			request.setAttribute("message", "userId�� 6�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPw == null || userPw.trim().length() < 6) {
			request.setAttribute("message", "userPw�� 6�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (userPwConfirm == null || !userPw.equals(userPwConfirm)) {
			request.setAttribute("message", "��й�ȣ ���Է� ����");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (username == null || username.trim().length() < 2) {
			request.setAttribute("message", "username�� 2�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if ((mobile1 == null || mobile1.trim().length() < 3) || (mobile2 == null || mobile2.trim().length() < 4)
				|| (mobile3 == null || mobile3.trim().length() < 4)) {
			request.setAttribute("message", "��ȭ��ȣ ����");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email1 == null || email2 == null) {
			request.setAttribute("message", "�̸��� �ּ� ����");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		// test
		// �������� ������
		// response.setContentType("text/html;charset=euc-kr");
		// PrintWriter out = response.getWriter();
		// out.println("<html><head><title></title></head><body>"
		// + "ȸ�����Լ��� !§</body></html>");
		// test

		request.setAttribute("message", userId + "�� ȸ������ ����");
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
			request.setAttribute("message", "username�� 2�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email == null || email.trim().length() > 20) {
			request.setAttribute("message", "email�� 20�ڸ� ����");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("message", username + "�� ���̵� ã�� ����?");
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
			request.setAttribute("message", "username�� 2�ڸ� �̻�");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		if (email == null || email.trim().length() > 20) {
			request.setAttribute("message", "email�� 20�ڸ� ����");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("message", userId + "�� ��й�ȣ ã�� ����?");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void selectOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		// 3. ��û ������ ���� : null, trim, length
		// ���̵�: �ʼ�, 6�ڸ� �̻�
		if (userId == null || userId.trim().length() < 6) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "���̵�� 6�ڸ� �̻��Դϴ�");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// ��û������ ���� ���� : success.jsp
		request.setAttribute("message", userId + "�� �� ���� ��ȸ ����");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void updateUserPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String newPw = request.getParameter("newPw");

		// ���̵�: �ʼ�, 6�ڸ� �̻�
		if (userId == null || userId.trim().length() < 6) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "���̵�� 6�ڸ� �̻��Դϴ�");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		// ��й�ȣ : �ʼ�, 6�ڸ� �̻�
		if (userPw == null || userPw.trim().length() < 6) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "��й�ȣ�� 6�ڸ� �̻��Դϴ�");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}

		if (newPw != userPw) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// ��û������ ���� ���� : success.jsp
		request.setAttribute("message", userId + "�� ��й�ȣ ���� ����");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		// 3. ��û ������ ���� : null, trim, length
		// ���̵�: �ʼ�, 6�ڸ� �̻�
		if (userId == null || userId.trim().length() < 6) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "���̵�� 6�ڸ� �̻��Դϴ�");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
			return;
		}
		// ��û������ ���� ���� : success.jsp
		request.setAttribute("message", userId + "�� ���� ���� ����");
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

		// 3. ��û ������ ���� : null, trim, length
		// ���̵�: �ʼ�, 6�ڸ� �̻�
		if (userId == null || userId.trim().length() < 6 || userPw == null || userPw.trim().length() < 6
				|| username == null || username.trim().length() == 0 || mobile == null || mobile.trim().length() == 0
				|| email == null || email.trim().length() == 0 || entryDate == null || entryDate.trim().length() == 0
				|| grade == null || grade.trim().length() == 0) {
			// ���� ������ �̵����� �����޼��� �Ӽ� ����
			request.setAttribute("message", "�ʼ� �Է��׸��� �Է��ϼ���");

			// ���������� ������ ������ ������(�̵�)
			RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
			nextView.forward(request, response);
		} else {
			System.out.println(userId + ", " + userPw + ", " + username + ", " + mobile + ", " + email + ", "
					+ entryDate + ", " + grade + ", " + mileage + ", " + manager);
			// servlet���� view ������
			// �ڹ��ڵ�ȿ� html �±� => �и�����
			// ���� => controller
			// jsp = html tag ����� view ���
			// �������� mime-type �� �ѱ����ڵ� ����
			response.setContentType("text/html;charset=euc-kr");
			// �������� ��½�Ʈ�� ����
			// PrintWriter out = response.getWriter();
			//
			// out.println("<html><head><title></title></head><body>");
			// out.println("<h3>ȸ�����Լ���</h3>");
			// out.println("�α����� ȸ������ ���񽺸� �̿��Ͻñ� �ٶ��ϴ�.");
			// out.println("</body></html>");
		}

		// ��û������ ���� ���� : success.jsp
		request.setAttribute("message", userId + "�� ȸ������ ����");
		request.getRequestDispatcher("success.jsp").forward(request, response);

	}

}

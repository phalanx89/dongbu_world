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
 * 1. ��û �ľ� => action=00000 2. ��û ������ ���� 3. ��û ������ ���� 4. Model���� ��û ó�� �Ƿ� 5. ��û��� �޾Ƽ� �������� ���� 6. ���������� �̵� => ���� => ����
 */
public class FrontController extends HttpServlet {
  
  public MemberService mservice = new MemberService();
  public FreeBoardService fbservice = new FreeBoardService();
  
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
        case "selectFreeList":
          selectFreeList(request, response);
          break;
        case "deleteArticle":
          deleteArticle(request, response);
          break;
        case "articleReference":
          articleReference(request, response);
          break;
        case "registerFreeByAdmin":
          registerFreeByAdmin(request, response);
          break;
        case "selectListByColumn":
          selectListByColumn(request, response);
          break;
        case "correctPage":
          correctPage(request, response);
          break;
        case "updateBoard":
          updateBoard(request, response);
          break;
        case "login":
          login(request, response);
          break;
        case "correctInfo":
          correctInfo(request, response);
          break;
        case "findUserPw":
          findUserPw(request, response);
          break;
        case "selectList":
          selectList(request, response);
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
      System.out.println("�������� �ʴ� ��û�Դϴ�.");
    }
  }
  
  /**
   * �α��� ȸ�� üũ
   * 
   * @param request
   * @param response
   * @return true �α��� ����� false �� �α���
   */
  public boolean isAuth(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
      return true;
    } else {
      return false;
    }
  }
  
  /** �����Խ��� �� ��� ��ȸ */
  protected void selectFreeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      ArrayList<FreeBoard> list = fbservice.selectList();
      request.setAttribute("list", list);
      request.getRequestDispatcher("boardMain.jsp").forward(request, response);
    } else {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /**
   * �����Խ��� �� �� ��ȸ
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void articleReference(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      int articleNo = Integer.valueOf(request.getParameter("articleNo"));
      fbservice.plusHits(articleNo);
      FreeBoard dto = fbservice.selectOne(articleNo);
      request.setAttribute("dto", dto);
      request.getRequestDispatcher("articleReference.jsp").forward(request, response);
    } else {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /** �Խñ� ���� */
  protected void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // �����ڰ� �ƴϸ� ������ ������ ���� Ȯ��
    if (!((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
      if (Integer.valueOf((String) request.getSession(false).getAttribute("empNo")) != Integer.valueOf(request.getParameter("empNo"))) {
        request.setAttribute("message", "���� ������ �����ϴ�.");
        request.getRequestDispatcher("fail.jsp").forward(request, response);
        return;
      }
    }
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    fbservice.delete(articleNo);
    selectFreeList(request, response);
  }
  
  /** �������� �����Խ��� �� ��� */
  protected void registerFreeByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // �����ڰ� �ƴϸ� ȸ���� �� ��� �޼���� ������
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
      // ���� ������ �̵����� �����޼��� �Ӽ� ����
      request.setAttribute("message", "������ �Է��ϼ���");
      
      // ���������� ������ ������ ������(�̵�)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    if (isNotice == null || isNotice.trim().length() == 0) {
      request.setAttribute("message", "�������θ� �Է��ϼ���");
      
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    FreeBoard dto = new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    fbservice.register(dto);
    // request.setAttribute("message", userName + "�� ���� ��ϵǾ����ϴ�.");
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("articleReference.jsp").forward(request, response);
  }
  
  /** ȸ���� �����Խ��� �� ��� */
  protected void registerFreeByMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
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
      // ���� ������ �̵����� �����޼��� �Ӽ� ����
      request.setAttribute("message", "������ �Է��ϼ���");
      
      // ���������� ������ ������ ������(�̵�)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    FreeBoard dto = new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    fbservice.register(dto);
    // request.setAttribute("message", userName + "�� ���� ��ϵǾ����ϴ�.");
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("articleReference.jsp").forward(request, response);
  }
  
  /**
   * ���� �� ������ ��� ���������� ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void correctPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // session �˻�
    HttpSession session = request.getSession(false);
    
    if (session == null) {
      request.setAttribute("message", "������ ����Ǿ����ϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    // ������ ������ ���� Ȯ��
    int sessionEmpNo = Integer.valueOf((String) session.getAttribute("empNo"));
    int requestEmpNo = Integer.valueOf(request.getParameter("empNo"));
    if (sessionEmpNo != requestEmpNo) {
      request.setAttribute("message", "���� ������ �����ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    FreeBoard dto = fbservice.selectOne(articleNo);
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("correctFree.jsp").forward(request, response);
  }
  
  /**
   * �������� �����Խ��� �� ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // �����ڰ� �ƴϸ� ȸ���� �� ���� �޼���� ������
    if (!((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
      updateBoardByMember(request, response);
      return;
    }
    
    int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
    String userName = (String) request.getSession(false).getAttribute("userName");
    
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    String title = request.getParameter("title");
    String regDate = "sysdate";// = Utility.getTodayDate();
    String content = request.getParameter("content");
    int hits = Integer.valueOf(request.getParameter("hits"));
    String isNotice = request.getParameter("isNotice");
    
    if (title == null || title.trim().length() == 0) {
      // ���� ������ �̵����� �����޼��� �Ӽ� ����
      request.setAttribute("message", "������ �Է��ϼ���");
      
      // ���������� ������ ������ ������(�̵�)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    if (isNotice == null || isNotice.trim().length() == 0) {
      request.setAttribute("message", "�������θ� �Է��ϼ���");
      
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    FreeBoard dto = new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    fbservice.update(dto);
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("articleReference.jsp").forward(request, response);
  }
  
  /**
   * ȸ���� �����Խ��� �� ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateBoardByMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
    String userName = (String) request.getSession(false).getAttribute("userName");
    
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    String title = request.getParameter("title");
    String regDate = "sysdate";// = Utility.getTodayDate();
    String content = request.getParameter("content");
    int hits = Integer.valueOf(request.getParameter("hits"));
    String isNotice = "N";
    
    if (title == null || title.trim().length() == 0) {
      // ���� ������ �̵����� �����޼��� �Ӽ� ����
      request.setAttribute("message", "������ �Է��ϼ���");
      
      // ���������� ������ ������ ������(�̵�)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    FreeBoard dto = new FreeBoard(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    fbservice.update(dto);
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("articleReference.jsp").forward(request, response);
  }
  
  /**
   * �� �˻�(�۹�ȣ, ����, ����, �ۼ���)
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void selectListByColumn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    String column = request.getParameter("column");
    String keyword = request.getParameter("keyword");
    
    // if (column == null || column.trim().length() == 0) {
    // request.setAttribute("message", "�˻��� Ű������ ������ �Է��ϼ���");
    //
    // RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
    // nextView.forward(request, response);
    // return;
    // }
    
    if (keyword == null || keyword.trim().length() == 0) {
      request.setAttribute("message", "�˻��� ������ �Է��ϼ���");
      
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    ArrayList<FreeBoard> list = fbservice.search(column, keyword);
    request.setAttribute("list", list);
    request.getRequestDispatcher("boardMain.jsp").forward(request, response);
    
  }
  
  /**
   * �� ���� ��ȸ
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // HttpSession session = request.getSession(false);
    // Member dto = mservice.myInfo((int) session.getAttribute("empNo"));
    // session.setAttribute("empNo", dto.getEmpNo());
    // session.setAttribute("userPw", dto.getUserPw());
    // session.setAttribute("userName", dto.getUserName());
    // session.setAttribute("email", dto.getEmail());
    // session.setAttribute("mobile", dto.getMobile());
    // session.setAttribute("dept", dto.getDept());
    // session.setAttribute("position", dto.getPosition());
    // session.setAttribute("isAdmin", dto.getIsAdmin());
    //
    // response.sendRedirect("myInfo.jsp"); // jsp ������ �ȸ������
    
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
      // int empNo = Integer.valueOf(request.getParameter("empNo"));
      Member dto = mservice.myInfo(empNo);
      request.setAttribute("dto", dto);
      request.getRequestDispatcher("myInfo.jsp").forward(request, response);
    } else {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /**
   * �� ���� ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void correctInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int empNo = Integer.valueOf(request.getParameter("empNo"));
    String userPw = request.getParameter("userPw");
    String userName = request.getParameter("userName");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile");
    String dept = request.getParameter("dept");
    String position = request.getParameter("position");
    String isAdmin = (String) request.getSession(false).getAttribute("isAdmin");
    
    if (userPw == null || userPw.trim().length() < 6) {
      request.setAttribute("message", "��й�ȣ�� 6�ڸ� �̻��Դϴ�");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    if (userName == null || userName.trim().length() == 0 || email == null || email.trim().length() == 0 
     || mobile == null || mobile.trim().length() == 0 || dept == null || dept.trim().length() == 0 
     || position == null || position.trim().length() == 0) {
      request.setAttribute("message", "������ ��� �Է��ϼ���.");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    Member dto = new Member(empNo, userPw, userName, email, mobile, dept, position, isAdmin);
    mservice.update(dto);
    request.setAttribute("dto", dto);
    request.setAttribute("messageSuccess", "��������� ����Ǿ����ϴ�. ����ȭ������ �̵��մϴ�.");
    request.getRequestDispatcher("index.jsp").forward(request, response);
  }
  
  /**
   * �α׾ƿ�
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    session.removeAttribute("empNo");
    session.removeAttribute("userName");
    session.removeAttribute("isAdmin");
    session.invalidate();
    response.sendRedirect("index.jsp");
  }
  
  /**
   * �α���
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 2. ��û ������ ���� => ��û������ : login.jsp
    String empNo = request.getParameter("empNo");
    String userPw = request.getParameter("userPw");
    
    // 3. ��û ������ ���� : null, trim, length
    // ���̵�: �ʼ�, 6�ڸ� �̻�
    if (empNo == null || empNo.trim().length() < 6) {
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
    // ��û������ ���� ���� : success.jsp
    HashMap<String, String> map = mservice.login(Integer.valueOf(empNo), userPw);
    
    if (map != null) {
      // �α��� ��û ����
      // 1. ���ǰ�ü ������ ����
      HttpSession session = request.getSession(); // true
      // 2. ���ǰ�ü �ʿ��� �������� ���� : ���̵�, �̸�, ���
      session.setAttribute("empNo", empNo);
      session.setAttribute("userName", map.get("userName"));
      session.setAttribute("isAdmin", map.get("isAdmin"));
      
      // 3. �α��� ���� ���������� �̵� (loginSuccess.jsp)
      // ���ο� ��û���� �̵� = session�� �����س������Ƿ� ����
      response.sendRedirect("index.jsp");
      
    } else {
      // �α��� ��û ����
      request.setAttribute("message", "�α��� ������ �ùٸ��� �Է��Ͻñ� �ٶ��ϴ�");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
    
  }
  
  /**
   * ��й�ȣ ã��
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void findUserPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userPw = Utility.getSecurityCode(6);
    String email = request.getParameter("email");
    
    if (email == null || email.trim().length() == 0) {
      request.setAttribute("message", "�̸����� �Է��ϼ���");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    mservice.changePw(userPw, email);
    request.setAttribute("userPw", userPw);
    //request.setAttribute("message", userId + "�� ��й�ȣã�� ����");
    request.getRequestDispatcher("ShowTempPw.jsp").forward(request, response);
  }
  
  /**
   * ȸ������ ��ü��ȸ
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void selectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Member> list = mservice.selectList();
    HttpSession session = request.getSession(); // true
    session.setAttribute("list", list);
    
    response.sendRedirect("showAll.jsp");
  }
  
  /**
   * ��ȣ ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateUserPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userId = request.getParameter("userId");
    String userPw = request.getParameter("userPw");
    String newPw = request.getParameter("newPw");
    String newPwAgain = request.getParameter("newPwAgain");
    
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
    if (userPw == null || userPw.trim().length() < 6 || newPw == null || newPw.trim().length() < 6) {
      // ���� ������ �̵����� �����޼��� �Ӽ� ����
      request.setAttribute("message", "��й�ȣ�� 6�ڸ� �̻��Դϴ�");
      
      // ���������� ������ ������ ������(�̵�)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    if (newPw != newPwAgain) {
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
  
  /**
   * ȸ������ ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
  
  /**
   * �������� ȸ�����
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
  // // 3. ��û ������ ���� : null, trim, length
  // // ���̵�: �ʼ�, 6�ڸ� �̻�
  // if (userId == null || userId.trim().length() < 6 || userPw == null
  // || userPw.trim().length() < 6 || username == null ||
  // username.trim().length() == 0
  // || mobile == null || mobile.trim().length() == 0 || email == null
  // || email.trim().length() == 0 || entryDate == null ||
  // entryDate.trim().length() == 0
  // || grade == null || grade.trim().length() == 0) {
  // // ���� ������ �̵����� �����޼��� �Ӽ� ����
  // request.setAttribute("message", "�ʼ� �Է��׸��� �Է��ϼ���");
  //
  // // ���������� ������ ������ ������(�̵�)
  // RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
  // nextView.forward(request, response);
  // } else {
  // System.out.println(userId + ", " + userPw + ", " + username + ", " +
  // mobile + ", "
  // + email + ", " + entryDate + ", " + grade + ", " + mileage + ", " +
  // manager);
  // // servlet���� view ������
  // // �ڹ��ڵ�ȿ� html �±� => �и�����
  // // ���� => controller
  // // jsp = html tag ����� view ���
  // // �������� mime-type �� �ѱ����ڵ� ����
  // response.setContentType("text/html;charset=euc-kr");
  // // �������� ��½�Ʈ�� ����
  // // PrintWriter out = response.getWriter();
  // //
  // // out.println("<html><head><title></title></head><body>");
  // // out.println("<h3>ȸ�����Լ���</h3>");
  // // out.println("�α����� ȸ������ ���񽺸� �̿��Ͻñ� �ٶ��ϴ�.");
  // // out.println("</body></html>");
  // }
  //
  // //��û������ ���� ���� : success.jsp
  // request.setAttribute("message", userId + "�� ȸ������ ����");
  // request.getRequestDispatcher("success.jsp").forward(request,
  // response);
  //
  // }
  
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
  
}

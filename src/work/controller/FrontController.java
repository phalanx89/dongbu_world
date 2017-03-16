package work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Board;
import work.model.dto.FreeReply;
import work.model.dto.Member;
import work.model.service.BlindBoardService;
import work.model.service.BlindReplyService;
import work.model.service.BoardService;
import work.model.service.FreeBoardService;
import work.model.service.FreeReplyService;
import work.model.service.MarketBoardService;
import work.model.service.MarketReplyService;
import work.model.service.MemberService;
import work.model.service.ReplyService;
import work.model.service.StudyBoardService;
import work.model.service.StudyReplyService;
import work.util.Utility;

/**
 * 1. ��û �ľ� => action=00000 2. ��û ������ ���� 3. ��û ������ ���� 4. Model���� ��û ó�� �Ƿ� 5. ��û��� �޾Ƽ� �������� ���� 6. ���������� �̵� => ���� => ����
 */
public class FrontController extends HttpServlet {
  
  public MemberService mservice = new MemberService();
  public BoardService bservice;
  public FreeBoardService fbservice = new FreeBoardService();
  public BlindBoardService bbservice = new BlindBoardService();
  public MarketBoardService mbservice = new MarketBoardService();
  public StudyBoardService sbservice = new StudyBoardService();
  public ReplyService rservice;
  public FreeReplyService frservice = new FreeReplyService();
  public BlindReplyService brservice = new BlindReplyService();
  public MarketReplyService mrservice = new MarketReplyService();
  public StudyReplyService srservice = new StudyReplyService();
  private String prefix = "";
  
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
    
    if (action != null) {
      if (action.contains("_")) {
        prefix = action.split("_")[0];
        action = action.split("_")[1];
        
        System.out.println("\n### action : " + action + ", prefix : " + prefix);
        
        switch (prefix) {
          case "free":
            bservice = fbservice;
            rservice = frservice;
            break;
          case "blind":
            bservice = bbservice;
            rservice = brservice;
            break;
          case "market":
            bservice = mbservice;
            rservice = mrservice;
            break;
          case "study":
            bservice = sbservice;
            rservice = srservice;
            break;
          default:
            break;
        }
      } else {
        prefix = "";
        System.out.println("\n### action : " + action);
      }
      
      switch (action) {
        case "selectList":
          selectFreeList(request, response);
          break;
        case "deleteArticle":
          deleteArticle(request, response);
          break;
        case "articleReference":
          articleReference(request, response);
          break;
        case "registerByAdmin":
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
        case "registerReply":
          registerReply(request, response);
          break;
        case "deleteReply":
          deleteReply(request, response);
          break;
        case "logout":
          logout(request, response);
          break;
        case "myInfo":
          myInfo(request, response);
          break;
      }
    } else {
      System.out.println("Error>> action is null.");
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
  
  /** �Խ��� �� ��� ��ȸ */
  protected void selectFreeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      ArrayList<Board> list = bservice.selectList();
      for (Board fb : list) {
        fb.setCountReply(rservice.countReply(fb.getArticleNo()));
      }
      request.setAttribute("list", list);
      forwardPage("boardMain.jsp", request, response);
      
    } else {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /**
   * �Խ��� �� �� ��ȸ
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void articleReference(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      int articleNo = Integer.valueOf(request.getParameter("articleNo"));
      bservice.plusHits(articleNo);
      Board dto = bservice.selectOne(articleNo);
      ArrayList<FreeReply> list = rservice.selectList(articleNo);
      for (FreeReply fr : list) {
        fr.setUserName(mservice.getUserName(fr.getEmpNo()));
      }
      request.setAttribute("dto", dto);
      request.setAttribute("list", list);
      forwardPage("articleReference.jsp", request, response);
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
    bservice.delete(articleNo);
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
    
    int articleNo = bservice.selectMaxNo();
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
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.register(dto);
    // request.setAttribute("message", userName + "�� ���� ��ϵǾ����ϴ�.");
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
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
    
    int articleNo = bservice.selectMaxNo();
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
    
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.register(dto);
    // request.setAttribute("message", userName + "�� ���� ��ϵǾ����ϴ�.");
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
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
    Board dto = bservice.selectOne(articleNo);
    request.setAttribute("dto", dto);
    forwardPage("correctFree.jsp", request, response);
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
    
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.update(dto);
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
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
    
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.update(dto);
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
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
    
    ArrayList<Board> list = bservice.search(column, keyword);
    request.setAttribute("list", list);
    forwardPage("boardMain.jsp", request, response);
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
    if (isAuth(request, response)) { // �α��� ����� ���� üũ
      int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
      Member dto = mservice.myInfo(empNo);
      request.setAttribute("dto", dto);
      request.getRequestDispatcher("myInfo.jsp").forward(request, response);
      forwardPage("myInfo.jsp", request, response);
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
    
    if (userName == null || userName.trim().length() == 0 || email == null || email.trim().length() == 0 || mobile == null || mobile.trim().length() == 0 || dept == null || dept.trim().length() == 0 || position == null || position.trim().length() == 0) {
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
    forwardPage("index.jsp", request, response);
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
    forwardPage("ShowTempPw.jsp", request, response);
  }
  
  /**
   * ��� ���
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void registerReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // �α��� ���� ���� ����� ���� ó��
    if (!isAuth(request, response)) {
      request.setAttribute("message", "ȸ�����뼭���Դϴ�.<p>�α����� �̿��Ͻñ� �ٶ��ϴ�.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int replyNo = rservice.selectMaxNo();
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
    String regDate = "sysdate";// = Utility.getTodayDate();
    String reply = request.getParameter("reply");
    
    if (reply == null || reply.trim().length() == 0) {
      request.setAttribute("message", "��� ������ �Է��ϼ���");
      
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    FreeReply dto = new FreeReply(replyNo, articleNo, empNo, regDate, reply);
    rservice.register(dto);
    request.setAttribute("dto", dto);
    articleReference(request, response);
  }
  
  /**
   * ��� ����
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    int replyNo = Integer.valueOf(request.getParameter("replyNo"));
    rservice.delete(replyNo);
    articleReference(request, response);
  }
  
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
   * 
   * @param url
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  private void forwardPage(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher nextView = request.getRequestDispatcher(prefix + "_" + url);
    nextView.forward(request, response);
  }
}

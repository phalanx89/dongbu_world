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
 * 1. 요청 파악 => action=00000 2. 요청 데이터 추출 3. 요청 데이터 검증 4. Model에게 요청 처리 의뢰 5. 요청결과 받아서 응답위한 설정 6. 응답페이지 이동 => 성공 => 실패
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
   * 웹의 모든 요청을 담당하는 서비스 메서드
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
  
  /** 게시판 글 목록 조회 */
  protected void selectFreeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // 로그인 사용자 권한 체크
      ArrayList<Board> list = bservice.selectList();
      for (Board fb : list) {
        fb.setCountReply(rservice.countReply(fb.getArticleNo()));
      }
      request.setAttribute("list", list);
      forwardPage("boardMain.jsp", request, response);
      
    } else {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /**
   * 게시판 글 상세 조회
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void articleReference(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // 로그인 사용자 권한 체크
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
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /** 게시글 삭제 */
  protected void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // 관리자가 아니면 본인의 글인지 권한 확인
    if (!((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
      if (Integer.valueOf((String) request.getSession(false).getAttribute("empNo")) != Integer.valueOf(request.getParameter("empNo"))) {
        request.setAttribute("message", "삭제 권한이 없습니다.");
        request.getRequestDispatcher("fail.jsp").forward(request, response);
        return;
      }
    }
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    bservice.delete(articleNo);
    selectFreeList(request, response);
  }
  
  /** 관리자의 자유게시판 글 등록 */
  protected void registerFreeByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    
    int articleNo = bservice.selectMaxNo();
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
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.register(dto);
    // request.setAttribute("message", userName + "님 글이 등록되었습니다.");
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
  }
  
  /** 회원의 자유게시판 글 등록 */
  protected void registerFreeByMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
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
      // 실패 페이지 이동전에 오류메세지 속성 설정
      request.setAttribute("message", "제목을 입력하세요");
      
      // 설정정보를 가지고 페이지 포워드(이동)
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.register(dto);
    // request.setAttribute("message", userName + "님 글이 등록되었습니다.");
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
  }
  
  /**
   * 현재 글 정보가 담긴 수정페이지 띄우기
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void correctPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // session 검사
    HttpSession session = request.getSession(false);
    
    if (session == null) {
      request.setAttribute("message", "세션이 만료되었습니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    // 본인의 글인지 권한 확인
    int sessionEmpNo = Integer.valueOf((String) session.getAttribute("empNo"));
    int requestEmpNo = Integer.valueOf(request.getParameter("empNo"));
    if (sessionEmpNo != requestEmpNo) {
      request.setAttribute("message", "수정 권한이 없습니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    Board dto = bservice.selectOne(articleNo);
    request.setAttribute("dto", dto);
    forwardPage("correctFree.jsp", request, response);
  }
  
  /**
   * 관리자의 자유게시판 글 수정
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // 관리자가 아니면 회원의 글 수정 메서드로 보내기
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
    
    Board dto = new Board(articleNo, title, empNo, regDate, content, hits, isNotice, userName);
    bservice.update(dto);
    request.setAttribute("dto", dto);
    forwardPage("articleReference.jsp", request, response);
  }
  
  /**
   * 회원의 자유게시판 글 수정
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateBoardByMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
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
      // 실패 페이지 이동전에 오류메세지 속성 설정
      request.setAttribute("message", "제목을 입력하세요");
      
      // 설정정보를 가지고 페이지 포워드(이동)
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
   * 글 검색(글번호, 제목, 내용, 작성자)
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void selectListByColumn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    String column = request.getParameter("column");
    String keyword = request.getParameter("keyword");
    
    // if (column == null || column.trim().length() == 0) {
    // request.setAttribute("message", "검색할 키워드의 종류를 입력하세요");
    //
    // RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
    // nextView.forward(request, response);
    // return;
    // }
    
    if (keyword == null || keyword.trim().length() == 0) {
      request.setAttribute("message", "검색할 내용을 입력하세요");
      
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    ArrayList<Board> list = bservice.search(column, keyword);
    request.setAttribute("list", list);
    forwardPage("boardMain.jsp", request, response);
  }
  
  /**
   * 내 정보 조회
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (isAuth(request, response)) { // 로그인 사용자 권한 체크
      int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
      Member dto = mservice.myInfo(empNo);
      request.setAttribute("dto", dto);
      request.getRequestDispatcher("myInfo.jsp").forward(request, response);
      forwardPage("myInfo.jsp", request, response);
    } else {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
  }
  
  /**
   * 내 정보 수정
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void correctInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
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
      request.setAttribute("message", "비밀번호는 6자리 이상입니다");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    if (userName == null || userName.trim().length() == 0 || email == null || email.trim().length() == 0 || mobile == null || mobile.trim().length() == 0 || dept == null || dept.trim().length() == 0 || position == null || position.trim().length() == 0) {
      request.setAttribute("message", "정보를 모두 입력하세요.");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    
    Member dto = new Member(empNo, userPw, userName, email, mobile, dept, position, isAdmin);
    mservice.update(dto);
    request.setAttribute("dto", dto);
    request.setAttribute("messageSuccess", "변경사항이 저장되었습니다. 메인화면으로 이동합니다.");
    request.getRequestDispatcher("index.jsp").forward(request, response);
    forwardPage("index.jsp", request, response);
  }
  
  /**
   * 로그아웃
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
   * 로그인
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
   * 비밀번호 찾기
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
      request.setAttribute("message", "이메일을 입력하세요");
      RequestDispatcher nextView = request.getRequestDispatcher("fail.jsp");
      nextView.forward(request, response);
      return;
    }
    mservice.changePw(userPw, email);
    request.setAttribute("userPw", userPw);
    forwardPage("ShowTempPw.jsp", request, response);
  }
  
  /**
   * 댓글 등록
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void registerReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    int replyNo = rservice.selectMaxNo();
    int articleNo = Integer.valueOf(request.getParameter("articleNo"));
    int empNo = Integer.valueOf((request.getSession(false).getAttribute("empNo").toString()));
    String regDate = "sysdate";// = Utility.getTodayDate();
    String reply = request.getParameter("reply");
    
    if (reply == null || reply.trim().length() == 0) {
      request.setAttribute("message", "댓글 내용을 입력하세요");
      
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
   * 댓글 삭제
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 로그인 하지 않은 사용자 오류 처리
    if (!isAuth(request, response)) {
      request.setAttribute("message", "회원전용서비스입니다.<p>로그인후 이용하시기 바랍니다.");
      request.getRequestDispatcher("fail.jsp").forward(request, response);
      return;
    }
    
    // 관리자가 아니면 본인의 글인지 권한 확인
    if (!((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
      if (Integer.valueOf((String) request.getSession(false).getAttribute("empNo")) != Integer.valueOf(request.getParameter("empNo"))) {
        request.setAttribute("message", "삭제 권한이 없습니다.");
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
    // post 요청에 대한 한글 인코딩 설정
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

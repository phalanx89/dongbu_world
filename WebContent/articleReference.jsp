<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,work.model.dto.FreeBoard"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
<head>
<title><font face="조선일보명조">(주)동부 World 게시판 메인 페이지</title>
<meta charset="utf-8"></meta>
<style>
#nav {
		width: 10%;
		height: 500px;
		border: 1px solid black;
		float: left;
}

#content {
		width: 60%;
		height: 500px;
		border: 1px solid black;
		float: right right;
}

table {
		width: 70%;
		height: auto;
		empty_cells: hide;
}

#td1 {
		height: 40px;
		vertical-align: inherit;
}

#td2 {
		height: 40px;
		vertical-align: inherit;
}

#td3 {
		height: 150px;
		width: 70%;
		vertical-align: inherit;
}
</style>
</head>
<body>
		<header>
		<h2>
				<a href="index.jsp"> <font color="c#33B114" face="조선일보명조"><em>DONGBU</em></font><font color="c#1EB930" face="조선일보명조"><em> WORLD</em></font></a>
		</h2>
		</header>
		<div id="wrapper">
				<div id="nav">
						<br>
						<br>
						<p align="center">
								<a href='controller?action=selectFreeList'>자유게시판</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>익명게시판</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>동부장터</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>동부배움터</a>
				</div>
				<div id="content">
				<% FreeBoard dto = (FreeBoard) request.getAttribute("dto");%>
						<br>
						<br>
						<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 일반게시판 "><br>
						<br>
						<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="이전글" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="다음글" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="글수정" onclick="location.href='controller?action=correctPage&articleNo=<%= dto.getArticleNo() %>&empNo=<%= dto.getEmpNo() %>'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="글삭제" onclick="location.href='controller?action=deleteArticle&articleNo=<%= dto.getArticleNo() %>&empNo=<%= dto.getEmpNo() %>'"/><br>
						<table id="td1" border="1" align="center">
								<tr>
										<th>
												<%
												  if (((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
												%> 공지 <%
												  } else {
												%> 일반
												<% } %>
										</th>
										<th><%=dto.getTitle()%></th>
										<%
										  Date d = new Date();
										  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										%>
										<td align="right"><%=sdf.format(d)%>
								</tr>
								<table id="td2" border="1" align="center">
										<tr>
												<th><%= dto.getUserName() %></th>
												<td align="right">조회수 : <%= dto.getHits() %>
										</tr>
										<table id="td3" border="1" align="center">
												<br>
		 										<tr>
														<td align="center"><%=dto.getContent()%></td>
												</tr>
												<table id="td4" border="1" align="center">
														<br>
														<tr>
																<td align="center">댓글&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="등록"></td>
														</tr>
														<table id="td5" border="1" align="center">
																<br>
																<tr>
																		<td align="center">1빠요~ㅋㅋㅋㅋㅋㅋㅋㅋ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="수정">&nbsp;&nbsp;<input type="button" value="삭제"></td>
																</tr>
														</table>
														</div>
</body>
<!-- footer -->
<hr>
<div class="footer">
		<font face="조선일보명조"> 개인정보처리방침 | 사이트맵 | 이메일무단수집거부 | 공시정보관리규정<br> 서울시 강남구 삼성로 96길 23(삼성동 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br> 사업자 등록 번호 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>
</body>
</html>
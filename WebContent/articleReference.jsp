<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,work.model.dto.FreeBoard"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>template</title>
<style type="text/css">
#container {
		background-color: #ffffff;
		width: 100%;
		height: 100%;
		padding: 0px;
		margin: 0px;
}

/* #sideinfo { */
/* 		background-color: #cccccc; */
/* 		float: left; */
/* 		width: 300px; */

/* 		height: 500px; */
/* 		padding: 5px; */
/* } */
#content {
		background-color: #F5F5F5;
		float: left;
		width: 100%;
		height: 750px;
		padding: 3px;
}

#footer {
		background-color: #ffffff;
		clear: both;
		height: 100px;
}
#nav {
		width: 10%;
		height: 500px;
		border: 1px solid black;
		float: left;
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
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<div id="nav">
								<p align="center">
										<a href='controller?action=selectFreeList'>자유게시판</a>
								<p align="center">
										<a href='boardMain.jsp'>익명게시판</a>
								<p align="center">
										<a href='boardMain.jsp'>동부장터</a>
								<p align="center">
										<a href='boardMain.jsp'>동부배움터</a>
						</div>
						<div id="content_board">
								<%
								  FreeBoard dto = (FreeBoard) request.getAttribute("dto");
								%>
								<input type="button" value=" 일반게시판 "> <input type="button" value="이전글" /> 
								<input type="button" value="다음글" /> 
								<input type="button" value="글수정" onclick="location.href='controller?action=correctPage&articleNo=<%=dto.getArticleNo()%>&empNo=<%=dto.getEmpNo()%>'" /> 
								<input type="button" value="글삭제" onclick="location.href='controller?action=deleteArticle&articleNo=<%=dto.getArticleNo()%>&empNo=<%=dto.getEmpNo()%>'" />
								<table id="td1" border="1" align="center">
										<tr>
												<th>
														<%
														String isAdmin = ((String) request.getSession(false).getAttribute("isAdmin"));
														  if (isAdmin != null && isAdmin.equals("Y")) {
														%> 공지 <%
														  } else {
														%> 일반 <%
														  }
														%>
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
														<th><%=dto.getUserName()%></th>
														<td align="right">조회수 : <%=dto.getHits()%>
												</tr>
												<table id="td3" border="1" align="center">
														<tr>
																<td align="center"><%=dto.getContent()%></td>
														</tr>
														<table id="td4" border="1" align="center">
																<tr>
																		<td align="center">댓글 <input type="button" value="등록"></td>
																</tr>
																<table id="td5" border="1" align="center">
																		<tr>
																				<td align="center">1빠요~ㅋㅋㅋㅋㅋㅋㅋㅋ <input type="button" value="수정"> <input type="button" value="삭제"></td>
																		</tr>
																</table>
																</div>
						</div>
						<!-- 				<div id="sideinfo"></div> -->
						<div id="footer">
								<jsp:include page="footer.jsp"></jsp:include>
						</div>
				</div>
</body>
</html>
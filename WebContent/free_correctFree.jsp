<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Board" %>
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

#nav {
		width: 10%;
		height: 500px;
		border: 1px solid black;
		float: left;
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

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: #7071B2;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

input[type=submit], input[type=reset], input[type=button] {
    background-color: #7071B2;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

input[type=submit]:hover, input[type=reset]:hover, input[type=button]:hover {
    opacity: 0.8;
}
</style>
</head>
<body>
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<div id="wrapper">
								<div id="nav">
										<div align="center">
										<a href='controller?action=free_selectList'>자유게시판</a>
								</div>
								<div align="center">
										<a href='controller?action=blind_selectList'>익명게시판</a>
								</div>
								<div align="center">
										<a href='controller?action=market_selectList'>동부장터</a>
								</div>	
								<div align="center">
										<a href='controller?action=study_selectList'>동부배움터</a>
								</div>
								</div>
								<div id="content_right">
										<%
										  Board dto = (Board) request.getAttribute("dto");
										%>
										<br> <br> <br><input type="button" value=" 일반게시판 " style="width: auto;">
										<form method="post" action="controller?action=free_updateBoard&articleNo=<%=dto.getArticleNo()%>&hits=<%=dto.getHits()%>">
												<table id="td1" border="1" align="center">
														<br>
														<tr>
																<br>
																<td><font face="조선일보명조">글제목 &nbsp;: <input type="text" name="title" value="<%=dto.getTitle()%>">&nbsp; <select name="isNotice">
																						<option value="">=공지여부 선택=</option>
																						<%
																						  if (dto.getIsNotice().equals("Y")) {
																						    out.write("<option value='Y' selected='selected'>공지</option>");
																						    out.write("<option value='N'>일반</option>");
																						  } else {
																						    out.write("<option value='Y'>공지</option>");
																						    out.write("<option value='N' selected='selected'>일반</option>");
																						  }
																						%>
																		</select><br>
																				<form enctype="multipart/form-data">
																						파일첨부 : <input type="file" accept="image/jpeg" ,image/gif>
																				</form></td>
														</tr>
														<table id="td2" border="1" align="center">
																<br>
																<tr>
																		<td align="center"><TEXTAREA ROWS="16" COLS="100" name='content'><%=dto.getContent()%></TEXTAREA></td>
																</tr>
																<table id="td3" border="0" align="center">
																		<br>
																		<tr>
																				<td align="center"><input type="submit" value="등록" style="width: auto;"/>  <input type="button" value="취소" style="width: auto;" onclick="location.href='controller?action=free_selectList'" /></td>
																		</tr>
																</table>
																</form>
																</div>
								</div>
								
								<!-- 				<div id="sideinfo"></div> -->
								<div id="footer">
										<jsp:include page="footer.jsp"></jsp:include>
								</div>
						</div>
</body>
</html>
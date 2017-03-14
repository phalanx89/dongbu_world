<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
										<br> <br>
										<p align="center">
												<a href='controller?action=selectFreeList'>자유게시판</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>익명게시판</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>동부장터</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>동부배움터</a>
								</div>
								<div id="content_right">
										<br> <br> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 일반게시판 ">
										<form method="post" action="controller?action=registerFreeByAdmin">
												<table id="td1" border="1" align="center">
														<br>
														<tr>
																<br>
																<td><font face="조선일보명조">글제목 &nbsp;: <input type="text" name="title">&nbsp; <select name="isNotice">
																						<option value="">=공지여부 선택=</option>
																						<option value="Y">공지</option>
																						<option value="N">일반</option>
																		</select><br>
																				<form enctype="multipart/form-data">
																						파일첨부 : <input type="file" accept="image/jpeg" ,image/gif>
																				</form></td>
														</tr>
														<table id="td2" border="1" align="center">
																<br>
																<tr>
																		<td align="center"><TEXTAREA ROWS="16" COLS="100" name='content'></TEXTAREA></td>
																</tr>
																<table id="td3" border="0" align="center">
																		<br>
																		<tr>
																				<td align="center"><input type="submit" value="등록" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="취소"></td>
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
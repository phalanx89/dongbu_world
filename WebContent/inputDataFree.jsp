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
												<a href='controller?action=selectFreeList'>�����Խ���</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>�͸�Խ���</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>��������</a>
										</p>
										<br> <br> <br>
										<p align="center">
												<a href='boardMain.jsp'>���ι����</a>
								</div>
								<div id="content_right">
										<br> <br> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" �ϹݰԽ��� ">
										<form method="post" action="controller?action=registerFreeByAdmin">
												<table id="td1" border="1" align="center">
														<br>
														<tr>
																<br>
																<td><font face="�����Ϻ�����">������ &nbsp;: <input type="text" name="title">&nbsp; <%
   if (((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
 %> <select name="isNotice">
																						<option value="">=�������� ����=</option>
																						<option value="Y">����</option>
																						<option value="N">�Ϲ�</option>
																		</select> <%
   }
 %>
																				<br><form enctype="multipart/form-data">
																						����÷�� : <input type="file" accept="image/jpeg" ,image/gif>
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
																				<td align="center"><input type="submit" value="���" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="���" onclick="location.href='controller?action=selectFreeList'" /></td>
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
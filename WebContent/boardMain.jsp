<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="work.model.dto.FreeBoard"%>
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

#nav {
		width: 10%;
		height: 700px;
		border: 1px solid black;
		float: left;
}

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
<body >
		<div id="container">
				<div id="header">
					<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
				<div id="nav">
						<br>
						<br>
						<p align="center">
								<a href='controller?action=selectFreeList'>�����Խ���</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>�͸�Խ���</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>��������</a>
						</p>
						<br>
						<br>
						<br>
						<p align="center">
								<a href='boardMain.jsp'>���ι����</a>
				</div>
				<div id="content_board">
						<br>
						<br>
						<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" �ϹݰԽ��� " style="width: auto;"><br>
						<table border="1" align="center">
								<!-- ������ -->
								<tr align="center">
										<th><font face="�����Ϻ�����">�۹�ȣ </th>
										<th><font face="�����Ϻ�����">������ </th>
										<th><font face="�����Ϻ�����">�ۼ��� </th>
										<th><font face="�����Ϻ�����">��ȸ�� </th>
								</tr>
								<!-- ���� ����Ʈ ���� ũ�⸸ŭ �ݺ��� -->
								<%
								  ArrayList<FreeBoard> list = (ArrayList<FreeBoard>) request.getAttribute("list");
								  if (list != null) {
								    
								    for (FreeBoard dto : list) {
								%>
								<tr>
										<td><font face="�����Ϻ�����"><%=dto.getArticleNo()%></font></td>
										<td width="600px"><% if (dto.getIsNotice().equals("Y")) { %>
										���� <% } else { %>
										�Ϲ� <% } %>&nbsp;&nbsp;
										<a href='controller?action=articleReference&articleNo=<%=dto.getArticleNo()%>'><font face="�����Ϻ�����"><%=dto.getTitle()%></a></td>
										<td><font face="�����Ϻ�����"><%=dto.getUserName()%></td>
										<td><font face="�����Ϻ�����"><%=dto.getHits()%></td>
								</tr>
								<%
								  	}
								  }
								%>
						</table>
						<br>
						<br>  
						<form method='post' action="controller?action=selectListByColumn">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="column">
								<option value="title">����</option>
								<option value="userName">�ۼ���</option>
								<option value="content">����</option>
								<option value="articleNo">�۹�ȣ</option>
						</select>
						<input type="text" name="keyword" id="text1" style="width: auto;" /><input type="submit" value="�۰˻�" style="width: auto;" />
						<input type="button" value="�۵��" style="width: auto;" onclick="location.href='inputDataFree.jsp'" />
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
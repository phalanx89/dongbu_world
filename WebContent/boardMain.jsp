<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="work.model.dto.FreeBoard"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
<head>
<title><font face="�����Ϻ�����">(��)���� World �Խ��� ���� ������</title>
<meta charset="utf-8"></meta>
<style>
#nav {
		width: 10%;
		height: 800px;
		border: 1px solid black;
		float: left;
}

#content {
		width: 90%;
		height: 800px;
		border: 1px solid black;
		float: right right;
}
</style>
</head>
<body>
		<header>
		<h2>
				<a href="index.jsp"> <font color="c#33B114" face="�����Ϻ�����"><em>DONGBU</em></font><font color="c#1EB930" face="�����Ϻ�����"><em> WORLD</em></font></a>
		</h2>
		</header>
		<div id="wrapper">
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
				<div id="content">
						<br>
						<br>
						<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" �ϹݰԽ��� "><br>
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
						<input type="text" name="keyword" id="text1" /><input type="submit" value="�۰˻�" />
						<input type="button" value="�۵��" onclick="location.href='inputDataFree.jsp'" />
						</form>
				</div>
</body>
<!-- footer -->
<hr>
<div class="footer" align="center">
		<font face="�����Ϻ�����"> ��������ó����ħ | ����Ʈ�� | �̸��Ϲ��ܼ����ź� | ����������������<br> ����� ������ �Ｚ�� 96�� 23(�Ｚ�� 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br> ����� ��� ��ȣ : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>
</body>
</html>

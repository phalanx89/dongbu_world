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
<body >
		<div id="container">
				<div id="header">
					<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
				
				</div>
<!-- 				<div id="sideinfo"></div> -->
				<div id="footer">
				<jsp:include page="footer.jsp"></jsp:include>
				</div>
		</div>
</body>
</html>




<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page 
import="java.util.ArrayList"
import="work.model.dto.*"
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>내 정보 조회</title>
</head>
<body>
	<%
	session = request.getSession(false);
	if (session != null) {
		ArrayList<Member> list = (ArrayList<Member>) session.getAttribute("list"); 
		
		Member dto;
		
		for (int i = 0; i < list.size(); i++) {
			dto = list.get(i);
		%>
	<table border='1'>
		<tr>
			<td>사번</td>
			<td><%= dto.getEmp_no() %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%= dto.getUserpw() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%= dto.getUsername() %></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><%= dto.getEmail() %></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%= dto.getMobile() %></td>
		</tr>
		<tr>
			<td>부서</td>
			<td><%= dto.getDept() %></td>
		</tr>
		<tr>
			<td>직위</td>
			<td><%= dto.getPosition() %></td>
		</tr>
	</table>
	<hr>
	<%
		}
	} else {
		
	}
%>
<form method="post" action="controller?action=insertInfo">
	<input type="submit" value="신규회원 중 정보제공 동의자 추가">
</form>
</body>
</html>
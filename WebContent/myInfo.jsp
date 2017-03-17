<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DONGBU WORLD</title>
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
						<form method="post" action="controller?action=correctInfo">
								<fieldset>
										<legend>나의 정보</legend>
										<%
										  Member dto = (Member) request.getAttribute("dto");
										%>
										<label for="name">ID</label> <input type="text" name="empNo" id="text1" style="width: auto;" value="<%= dto.getEmpNo() %>" readonly="readonly" /><br /> 
										<label for="password">PW</label> <input type="password" name="userPw" style="width: auto;" value="<%= dto.getUserPw() %>"/><br />
										<label for="name">이름</label> <input type="text" name="userName" id="text1" style="width: auto;" value="<%= dto.getUserName() %>"/><br />
										<label for="name">EMAIL</label> <input type="text" name="email" id="text1" style="width: auto;" value="<%= dto.getEmail() %>"/><br />
										<label for="name">휴대폰</label> <input type="text" name="mobile" id="text1" style="width: auto;" value="<%= dto.getMobile() %>"/><br />
										<label for="name">부서</label> <input type="text" name="dept" id="text1" style="width: auto;" value="<%= dto.getDept() %>"/><br />
										<label for="name">직위</label> <input type="text" name="position" id="text1" style="width: auto;" value="<%= dto.getPosition() %>"/><br />
								</fieldset>
								<input type="submit" name="submit" value="수정" style="width: auto;"/>
						</form>
				</div>
				<!-- 				<div id="sideinfo"></div> -->
				<div id="footer">
						<jsp:include page="footer.jsp"></jsp:include>
				</div>
		</div>
</body>
</html>




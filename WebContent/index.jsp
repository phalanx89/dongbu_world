<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
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
/* 		float: left; */0
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
    padding: 5px 5px;
    margin: 0px 0;
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
				<div id="content" align="center">
						<%
						  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td align="center"> <button style="background-color:#7071B2; width:220px; height:230px;">맛집 랭킹</button></td>
								</tr>
								<tr>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='controller?action=selectFreeList'">게시판 메인</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='http://ep.dongbuinc.com/'">구글 포르테</button></td>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='index.jsp'">회원정보조회</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='restaurant_main.jsp'">맛집 메인</button></td>
								</tr>
								<tr>
										<td></td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="location.href='http://dbcni.benecafe.co.kr/'">Bene Cafe</button></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  } else {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="">맛집 랭킹</button></td>
								</tr>
								<tr>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='login.jsp'">게시판 메인</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='http://ep.dongbuinc.com/'">구글 포르테</button></td>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='login.jsp'">회원정보조회</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='login.jsp'">맛집 메인</button></td>
								</tr>
								<tr>
										<td></td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="location.href='http://dbcni.benecafe.co.kr/'">Bene Cafe</button></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  }
						%>
				</div>
				<!-- 				<div id="sideinfo"></div> -->
				<div id="footer">
						<jsp:include page="footer.jsp"></jsp:include>
				</div>
		</div>
</body>
</html>

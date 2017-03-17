<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="work.data.*, java.util.ArrayList, work.model.dto.Restaurant" %>
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
		background-color: #ffffff;
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
    font-weight: bold;
    font-size: 20px;
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

a:hover {
	color: #ff0000;
	font-style: italic;
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
							ArrayList<String> list = new ArrayList<String>();
							list = (ArrayList<String>) request.getAttribute("list");
							String message = (String) request.getAttribute("messageSuccess");
							if (message != null) {
							  %>
							  <script type="text/javascript">
							  	alert("<%= message%>");
							  </script>
							  <%
							}
						  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null && list != null) {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td align="center"> <button style="background-color:#7071B2; width:220px; height:230px; font-size:14px;">
										<font size="5">맛집 추천</font><p><%=list.get(0) %></p><p><%=list.get(1) %></p><p><%=list.get(2) %></p></button></td>
								</tr>
								<tr>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='controller?action=free_selectList'">게시판 메인</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='http://ep.dongbuinc.com/'">구글 포르테</button></td>
										<td align="center"><button style="background-color:#A2A1FA; width:220px; height:230px;" onclick="location.href='controller?action=myInfo'">회원정보조회</button></td>
										<td align="center"><button style="background-color:#B3B3B3; width:220px; height:230px;" onclick="location.href='food_controller?action=<%= Define.ACTION_SELECT_RESTAURANT_LIST%>'">맛집 메인</button></td>
								</tr>
								<tr>
										<td></td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="location.href='http://dbcni.benecafe.co.kr/'">Bene Cafe</button></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  } else if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null && list == null) {
						    %>
						    <script type="text/javascript">
						    	location.href= 'controller?action=<%=Define.ACTION_RECOMMEND_RESTAURANT%>';
						    </script>
						    <table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="location.href='login.jsp'">맛집 랭킹</button></td>
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
						  } else {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td align="center"><button style="background-color:#7071B2; width:220px; height:230px;" onclick="location.href='login.jsp'">맛집 랭킹</button></td>
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
						<br><br><br>
				</div>
				<!-- 				<div id="sideinfo"></div> -->
				<div id="footer">
						<jsp:include page="footer.jsp"></jsp:include>
				</div>
		</div>
</body>
</html>

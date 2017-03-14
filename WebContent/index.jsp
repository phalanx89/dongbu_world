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
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="250px" height="250px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="controller?action=selectFreeList"> <img src="images/icon_board.png" width="250px" height="250px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="250px" height="250px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="index.jsp"><img src="images/icon_member.png" width="250px" height="250px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="restaurant.jsp"><img src="images/icon_restaurant.png" width="250px" height="250px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="250px" height="250px"><br>Bene Cafe</a></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  } else {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="250px" height="250px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"> <img src="images/icon_board.png" width="250px" height="250px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="250px" height="250px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_member.png" width="250px" height="250px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_restaurant.png" width="250px" height="250px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="250px" height="250px"><br>Bene Cafe</a></td>
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

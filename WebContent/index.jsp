
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
		margin: 0 auto;
		padding: 0px;
		margin: 0 auto;
}s

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
		height: 100px; table { width : 50%;
		height: auto;
		empty_cells: hide;
}


<<<<<<< .mine
td {
=======
 </head>
>>>>>>> .theirs
td {
		width: 110px;
		height: 110px;
		padding: 5px;
}
}
</style>
</head>
<body>
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<%
						  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="100px" height="100px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="controller?action=selectFreeList"> <img src="images/icon_board.png" width="100px" height="100px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="100px" height="100px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="index.jsp"><img src="images/icon_member.png" width="100px" height="100px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="restaurant.jsp"><img src="images/icon_restaurant.png" width="100px" height="100px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="100px" height="100px"><br>Bene Cafe</a></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  } else {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="100px" height="100px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"> <img src="images/icon_board.png" width="100px" height="100px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="100px" height="100px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_member.png" width="100px" height="100px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_restaurant.png" width="100px" height="100px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="100px" height="100px"><br>Bene Cafe</a></td>
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
</body><<<<<<< .mine
		height: 110px;
		padding: 5px;
}
}
</style>
</head>
<body>
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<%
						  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="100px" height="100px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="controller?action=selectFreeList"> <img src="images/icon_board.png" width="100px" height="100px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="100px" height="100px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="index.jsp"><img src="images/icon_member.png" width="100px" height="100px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="restaurant.jsp"><img src="images/icon_restaurant.png" width="100px" height="100px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="100px" height="100px"><br>Bene Cafe</a></td>
										<td colspan="2"></td>
								</tr>
						</table>
						<%
						  } else {
						%>
						<table>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td bgcolor="#cccccc" align="center"><a href=""> <img src="images/icon_rank.png" width="100px" height="100px"><br>맛집 랭킹
										</a></td>
								</tr>
								<tr>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"> <img src="images/icon_board.png" width="100px" height="100px"><br>게시판 메인
										</a></td>
										<td bgcolor="#cccccc" align="center"><a href="http://ep.dongbuinc.com/"><img src="images/icon_epdongbu.png" width="100px" height="100px"><br>구글 포르테</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_member.png" width="100px" height="100px"><br>회원정보조회</a></td>
										<td bgcolor="#cccccc" align="center"><a href="login.jsp"><img src="images/icon_restaurant.png" width="100px" height="100px"><br>맛집 메인</a></td>
								</tr>
								<tr>
										<td></td>
										<td bgcolor="#cccccc" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="images/icon_bene.png" width="100px" height="100px"><br>Bene Cafe</a></td>
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




=======
 <header>
	 <h2> <a href="index.jsp"> <font color="c#33B114" face ="조선일보명조"><em>DONGBU</em></font><font color="c#1EB930" face ="조선일보명조"><em> WORLD</em></font></a></h2>
 </header>
<%
	if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) { 
%>	
	<a href='controller?action=logout'>로그아웃</a>
		<table>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td bgcolor="c#EFB2E7" align="center">
				<a href="">
				<img src="rank.png" width="60" height="60"><br><font face="조선일보명조">맛집 랭킹</face></a></td>
			</tr>
			<tr>
				<td bgcolor="c#E0282B" align="center">
					<a href="controller?action=selectFreeList">
					<img src="board.png" width="60" height="60"><br><font face="조선일보명조">게시판 메인</font></a>
				</td>
				
				<td bgcolor="#1FC8B6" align="center"><a href="http://ep.dongbuinc.com/"><img src="porte.png" width="60" height="60"><br><font face="조선일보명조">구글 포르테</font></a></td>
				<td bgcolor="c#DEE40D" align="center"><a href="index.jsp"><img src="memberinfo.png" width="60" height="60"><br><font face="조선일보명조">회원정보조회</font></a></td>
				<td bgcolor="c#E18586" align="center" ><a href="restaurant.jsp"><img src="res.png" width="60" height="60"><br><font face="조선일보명조">맛집 메인</face></a></td>
			</tr>
			<tr>
				<td></td>
				<td bgcolor="#EC9AC8" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="bene.png" width="60" height="60"><br><font face="조선일보명조">Bene Cafe</font></a></td>
				<td colspan="2"></td>
			</tr>
		</table><br><br><br><br></br>
<%	
	} else {
%>
		<table>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td bgcolor="c#EFB2E7" align="center">
				<a href="">
				<img src="rank.png" width="60" height="60"><br><font face="조선일보명조">맛집 랭킹</face></a></td>
			</tr>
			<tr>
				<td bgcolor="c#E0282B" align="center">
					<a href="login.jsp">
					<img src="board.png" width="60" height="60"><br><font face="조선일보명조">게시판 메인</font></a>
				</td>
				
				<td bgcolor="#1FC8B6" align="center"><a href="http://ep.dongbuinc.com/"><img src="porte.png" width="60" height="60"><br><font face="조선일보명조">구글 포르테</font></a></td>
				<td bgcolor="c#DEE40D" align="center"><a href="login.jsp"><img src="memberinfo.png" width="60" height="60"><br><font face="조선일보명조">회원정보조회</font></a></td>
				<td bgcolor="c#E18586" align="center" ><a href="login.jsp"><img src="res.png" width="60" height="60"><br><font face="조선일보명조">맛집 메인</face></a></td>
			</tr>
			<tr>
				<td></td>
				<td bgcolor="#EC9AC8" align="center"><a href="http://dbcni.benecafe.co.kr/"><img src="bene.png" width="60" height="60"><br><font face="조선일보명조">Bene Cafe</font></a></td>
				<td colspan="2"></td>
			</tr>
		</table><br><br><br><br></br>
<%
	}
%>

<!-- footer -->
<hr>
<div class="footer">
개인정보처리방침 | 사이트맵 | 이메일무단수집거부 | 공시정보관리규정<br> 
서울시 강남구 삼성로 96길 23(삼성동 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
사업자 등록 번호 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
>>>>>>> .theirs
</html>

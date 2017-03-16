<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="work.model.dto.*"%>
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
		height: 100%;
		background-color: #eeeeee;
		padding: 3px;
		float: left;
}

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

#myTable {
    border-collapse: collapse; /* Collapse borders */
    width: 1200px; /* Full-width */
    border: 1px solid #ddd; /* Add a grey border */
    font-size: 13px; /* Increase font-size */
}

#myTable th, #myTable td {
    text-align: left; /* Left-align text */
    padding: 12px; /* Add padding */
}

#myTable tr {
    /* Add a bottom border to all table rows */
    border-bottom: 1px solid #ddd; 
}

#myTable tr.header, #myTable tr:hover {
    /* Add a grey background color to the table header and on hover */
    background-color: #f1f1f1;
}
</style>
</head>
<body>
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<div id="nav">
								<div align="center">
										<a href='controller?action=free_selectList'>자유게시판</a>
								</div>
								<div align="center">
										<a href='controller?action=blind_selectList'>익명게시판</a>
								</div>
								<div align="center">
										<a href='controller?action=market_selectList'>동부장터</a>
								</div>	
								<div align="center">
										<a href='controller?action=study_selectList'>동부배움터</a>
								</div>
						</div>
						<div id="content_board">
							<div style="height:650px;overflow:auto">
								<table id="myTable">
										<!-- 제목행 -->
										<tr class="header">
												<th><font face="조선일보명조">글번호 </th>
												<th><font face="조선일보명조">글제목 </th>
												<th><font face="조선일보명조">작성자 </th>
												<th><font face="조선일보명조">조회수 </th>
										</tr>
										<!-- 공지 리스트 설정 크기만큼 반복행 -->
										<%
										  ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
										  if (list != null) {
										    
										    for (Board dto : list) {
										%>
										<tr>
												<td><font face="조선일보명조"><%=dto.getArticleNo()%></font></td>
												<td width="600px">
														<%
														  if (dto.getIsNotice().equals("Y")) {
														%> <b>공지</b> <%
														  } else {
														%> 일반 <%
														  }
														%>&nbsp;&nbsp; <a href='controller?action=market_articleReference&articleNo=<%=dto.getArticleNo()%>' style="text-decoration:none;"><font face="조선일보명조"><%=dto.getTitle()%></a>
														(<%=dto.getCountReply() %>)
												</td>
												<td><font face="조선일보명조"><%=dto.getUserName()%></td>
												<td><font face="조선일보명조"><%=dto.getHits()%></td>
										</tr>
										<%
										  }
										  }
										%>
								</table>
							</div>
								<br> <br>
								<form method='post' action="controller?action=market_selectListByColumn">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="column" style="width: 75px; height: 40px;">
												<option value="title">제목</option>
												<option value="userName">작성자</option>
												<option value="content">내용</option>
												<option value="articleNo">글번호</option>
										</select> <input type="text" name="keyword" id="text1" style="width: auto;" />&nbsp;<input type="submit" value="글검색" style="width: auto;" />&nbsp; <input type="button" value="글등록" style="width: auto;" onclick="location.href='market_inputData.jsp'" />
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
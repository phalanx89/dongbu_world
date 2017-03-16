<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,work.model.dto.*,java.util.ArrayList"%>
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

 #sideinfo { 
 		background-color: #ffffff; 
 		float: left; 
 		width: 250px; 
 		height: 740px; 
		padding: 5px; 
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

#myTable {
		border-collapse: collapse; /* Collapse borders */
		width: 1200px; /* Full-width */
		border: 1px solid #ddd; /* Add a grey border */
		font-size: 13px; /* Increase font-size */
}

#myTable th, #myTable td {
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
		padding: 0px 0px;
		margin: 0px 0px;
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
		padding: 10px 15px;
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
						<div id="sideinfo">
							<table style="border-collapse:collapse;">
								<tr>
										<td align="center"> <button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=free_selectList'"><font size="3">자유게시판</font></button></td>
								</tr>
								<tr>
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=blind_selectList'"><font size="3">익명게시판</font></button></td>
								</tr>
								<tr>		
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=market_selectList'"><font size="3">동부장터</font></button></td>
								</tr>
								<tr>		
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=study_selectList'"><font size="3">동부배움터</font></button></td>
								</tr>		
						</table>	
						</div>
						<div id="content_board">
								<%
								  Board dto = (Board) request.getAttribute("dto");
								%>
								&nbsp;<input type="button" value="목록" style="width: auto;" onclick="location.href='controller?action=market_selectList'" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="글수정" style="width: auto;" onclick="location.href='controller?action=market_correctPage&articleNo=<%=dto.getArticleNo()%>&empNo=<%=dto.getEmpNo()%>'" /> <input type="button" value="글삭제" style="width: auto;" onclick="location.href='controller?action=market_deleteArticle&articleNo=<%=dto.getArticleNo()%>&empNo=<%=dto.getEmpNo()%>'" />
								<table id="myTable">
										<tr>
												<th style="width: 8%;" align="left">
														<%
														  String isAdmin = ((String) request.getSession(false).getAttribute("isAdmin"));
														  if (isAdmin != null && isAdmin.equals("Y")) {
														%> |공지| <%
														  } else {
														%> 일반 <%
														  }
														%>
												</th>
												<th style="width: 62%;" align="left"><%=dto.getTitle()%></th>
												<%
												  Date d = new Date();
												  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
												%>
												<td style="width: 8%;" align="right"><%=sdf.format(d)%>
												<th style="width: 7%;" align="right"><%=dto.getUserName()%></th>
												<td style="width: 15%;" align="center">조회수 : <%=dto.getHits()%></td>
										</tr>
										<tr class="header" heigth="700px">
												<td></td>
												<td colspan="3"><br><%=dto.getContent()%><br>
												<br></td>
												<td></td>
										</tr>
										<tr>
												<td colspan="5" align="center"><form method='post' action="controller?action=market_registerReply&articleNo=<%=dto.getArticleNo()%>">
																<input type="text" name="reply" id="text1" style="width: 86%;">&nbsp;&nbsp;<input type="submit" value="댓글등록" style="width: auto;">
														</form></td>
										</tr>
										<%
										  ArrayList<FreeReply> list = (ArrayList<FreeReply>) request.getAttribute("list");
										  if (list != null) {
										    for (FreeReply fr : list) {
										%>
										<tr>
												<td align="center"><%=fr.getReplyNo()%></td>
												<td><%=fr.getReply()%></td>
												<td><%=fr.getRegDate()%></td>
												<th><%=fr.getUserName()%></th>
												<td align="right"><input type="button" value="수정" style="width: auto;"> <input type="button" value="삭제" style="width: auto;" onclick="location.href='controller?action=market_deleteReply&replyNo=<%=fr.getReplyNo()%>&articleNo=<%=fr.getArticleNo()%>'" /></td>
										</tr>
										<%
										  }
										  }
										%>
								</table>
						</div>
				</div>
				<!-- 				<div id="sideinfo"></div> -->
				<div id="footer">
						<jsp:include page="footer.jsp"></jsp:include>
				</div>
		</div>
</body>
</html>
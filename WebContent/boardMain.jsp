<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.ArrayList" %>  
<%@ page import="work.model.dto.FreeBoard" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title><font face="조선일보명조">(주)동부 World 게시판 메인 페이지</title>
  <meta charset="utf-8"></meta>
  <style>
	  #nav {
		  width: 10%;
		  height: 500px;
		  border: 1px solid black;
		  float: left;
	  }
	  #content {
	      width: 60%;
		  height: 500px;
		  border: 1px solid black;
	      float: right
right;
	  }

 </style>
 
 </head>

 <body>
   <header>
   <h2> <a href="index.html"> <font color="c#33B114" face ="조선일보명조"><em>DONGBU</em></font><font color="c#1EB930" face ="조선일보명조"><em> WORLD</em></font></a></h2>
 </header>
 <div id="wrapper">
 	<div id="nav"><br><br>
 	<p align="center"><input type="button" value=" 일반게시판 "></p><br><br><br>
	<p align="center"><input type="button" value=" 익명게시판 "></p><br><br><br>
    <p align="center"><input type="button" value="  동부장터  "></p><br><br><br>
    <p align="center"><input type="button" value=" 동부배움터 ">
			 			
				</div>
 	<div id="content"> 
 	<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 일반게시판 "><br>
 	<table border="1" align="center">
 					<!-- 제목행 -->
					<tr align="center">
						<td><font face="조선일보명조">글번호 </td>
						<td><font face="조선일보명조">글제목 </td>
						<td><font face="조선일보명조">작성자 </td>
					</tr>
					
					<!-- 공지 리스트 설정 크기만큼 반복행 -->
					<%
						ArrayList<FreeBoard> list = (ArrayList<FreeBoard>)request.getAttribute("list");
						for (FreeBoard dto : list) {
					%>
					<tr align="center">
						<td><font face="조선일보명조"><%= dto.getArticleNo() %></font></td>
						<td><font face="조선일보명조"><%= dto.getTitle() %></td>
						<td><font face="조선일보명조"><%= dto.getUserName() %></td>
					</tr>
					<%
						}
					%>
				</table>
				
				<br><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="name" id="text1" /><input type="button" value="글검색"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글등록" onclick="location.href=''" />
 </div>
	</body>

<!-- footer -->
<hr>
<div class="footer"><font face="조선일보명조">
개인정보처리방침 | 사이트맵 | 이메일무단수집거부 | 공시정보관리규정<br> 
서울시 강남구 삼성로 96길 23(삼성동 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
사업자 등록 번호 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>


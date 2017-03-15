<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<div style="height: 100px; width: 100%; position: static;">
		<header>
				<h2>
						<a href="index.jsp"><img src="images/top_logo.png" /></a>
				</h2>
				<%
				  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
				%>
				<div style="position:absolute; top: 35px; left: 1700px" >
					<%= request.getSession(false).getAttribute("userName") %>´Ô  
					<a href="controller?action=logout">·Î±×¾Æ¿ô</a>
				</div>
				<%
				  }
				%>
		</header>
</div>

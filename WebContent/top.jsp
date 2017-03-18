<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<div style="height: 100px; width: 100%; position: static;">
		<header>
				<h2>
						&nbsp;&nbsp;<a href="index.jsp"><img src="images/top_logo1.png" /></a>
				</h2>
				<%
				  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
				%>
				<div style="position: absolute; top: 35px; left: 1359px">
						<a href="controller?action=myInfo" style="text-decoration: none;"><%= request.getSession(false).getAttribute("userName") %>´Ô</a> &nbsp;<input type="button" name="logout" value="·Î±×¾Æ¿ô" style="width: 80px; height: 30px; padding: 1px;" onclick="location.href='controller?action=logout'" />
				</div>
				<%
				  }
				%>
		</header>
</div>

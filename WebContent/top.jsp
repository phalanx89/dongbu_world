<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<div style="height: 100px; width: 100%; position: static;">
		<header>
				<h2>
						<a href="index.jsp"><img src="images/top_logo.png" /></a>
				</h2>
				<%
				  if (session != null && session.getAttribute("empNo") != null && session.getAttribute("userName") != null) {
				%>
				<div style="position: absolute; top: 35px; left: 1359px">
						<a href="controller?action=myInfo" style="text-decoration: none;"><%= request.getSession(false).getAttribute("userName") %>��</a> &nbsp;<input type="button" name="logout" value="�α׾ƿ�" style="width: 80px; height: 30px; padding: 1px;" onclick="location.href='controller?action=logout'" />
				</div>
				<%
				  }
				%>
		</header>
</div>

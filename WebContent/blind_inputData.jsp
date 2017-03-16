<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
						<div id="wrapper">
								<div id="sideinfo">
							<table style="border-collapse:collapse;">
								<tr>
										<td align="center"> <button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=free_selectList'"><font size="3">�����Խ���</font></button></td>
								</tr>
								<tr>
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=blind_selectList'"><font size="3">�͸�Խ���</font></button></td>
								</tr>
								<tr>		
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=market_selectList'"><font size="3">��������</font></button></td>
								</tr>
								<tr>		
										<td align="center"><button style="background-color:#A2A1FA; width:250px; height:60px;" onclick="location.href='controller?action=study_selectList'"><font size="3">���ι����</font></button></td>
								</tr>		
						</table>	
						</div>
								<div id="content_right">
										<form method="post" action="controller?action=blind_registerByAdmin">
												<table id="myTable">
														<tr class="header">
															<th>�� ���</th>
														</tr>
														<tr align="center">
																<td>���� &nbsp; <input type="text" name="title" style="width:70%;">&nbsp; <%
																	   if (((String) request.getSession(false).getAttribute("isAdmin")).equals("Y")) {
																	 %> <select name="isNotice" style="width: 125px; height: 40px;">
																						<option value="Y" selected="selected">����</option>
																						<option value="N">�Ϲ�</option>
																		</select> <%
																	   }
																	 %>
														</tr>
														<tr>
																<td align="center"><TEXTAREA ROWS="30" COLS="140" name='content'></TEXTAREA></td>
														</tr>
														<tr>
																<td align="center"><input type="submit" value="���" style="width: auto;" /> <input type="button" value="���" style="width: auto;" onclick="location.href='controller?action=blind_selectList'" /></td>
														</tr>
														</table>
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
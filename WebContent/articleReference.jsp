<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title><font face="�����Ϻ�����">(��)���� World �Խ��� ���� ������</title>
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
	  	table {width:70%;
		   height:auto;
	       empty_cells:hide;          
	}
	#td1 { height:40px;
	    vertical-align: inherit;
	}
	#td2 { height:150px;
	    vertical-align: inherit;
	}
	 #td3 { height:40px;
		    width: 70%;
	    vertical-align: inherit;
	}

 </style>
 
 </head>

 <body>
   <header>
   <h2> <a href="index.html"> <font color="c#33B114" face ="�����Ϻ�����"><em>DONGBU</em></font><font color="c#1EB930" face ="�����Ϻ�����"><em> WORLD</em></font></a></h2>
 </header>
 <div id="wrapper">
 	<div id="nav"><br><br>
 	<p align="center"><input type="button" value=" �ϹݰԽ��� "></p><br><br><br>
	<p align="center"><input type="button" value=" �͸�Խ��� "></p><br><br><br>
    <p align="center"><input type="button" value="  ��������  "></p><br><br><br>
    <p align="center"><input type="button" value=" ���ι���� ">
			 			
				</div>
 	<div id="content"> 
 	<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" �ϹݰԽ��� "><br><br>
 	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="������"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="������"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="�ۼ���"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="�ۻ���"/><br>
 	
 	<table id="td1" border="1" align="center">
 		<tr>
 			<th><% 
 					if(request.getAttribute("isAdmin") == "Y") {
 				%> ����
 				<%
 					}	
 				%>
 			</th>
			<th><%= request.getAttribute("title") %></th>
			<td align="right"><%= request.getAttribute("regDate") %>
 		</tr>
 	<table id="td2" border="1" align="center">
 		<tr>
			<th><%= request.getAttribute("userName") %></th>
			<td align="right">��ȸ�� : <%= request.getAttribute("hits") %>
 		</tr>  	
 	<table id="td3" border="1" align="center">
 		<br><tr>
			<td align="center"><%= request.getAttribute("content") %></td>
 		</tr>
 	<table id="td4" border="1" align="center">
 		<br><tr>
			<td align="center">
			���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="���"></td>
 		</tr>
 		
 	 	<table id="td5" border="1" align="center">
 		<br><tr>
			<td align="center">
			1����~����������������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="����">&nbsp;&nbsp;<input type="button" value="����"></td>
 		</tr>
 	</table>
 </div>
	</body>

<!-- footer -->
<hr>
<div class="footer"><font face="�����Ϻ�����">
��������ó����ħ | ����Ʈ�� | �̸��Ϲ��ܼ����ź� | ����������������<br> 
����� ������ �Ｚ�� 96�� 23(�Ｚ�� 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
����� ��� ��ȣ : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>
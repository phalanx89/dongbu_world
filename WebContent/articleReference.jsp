<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title><font face="繕識析左誤繕">(爽)疑採 World 惟獣毒 五昔 凪戚走</title>
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
   <h2> <a href="index.html"> <font color="c#33B114" face ="繕識析左誤繕"><em>DONGBU</em></font><font color="c#1EB930" face ="繕識析左誤繕"><em> WORLD</em></font></a></h2>
 </header>
 <div id="wrapper">
 	<div id="nav"><br><br>
 	<p align="center"><input type="button" value=" 析鋼惟獣毒 "></p><br><br><br>
	<p align="center"><input type="button" value=" 斥誤惟獣毒 "></p><br><br><br>
    <p align="center"><input type="button" value="  疑採舌斗  "></p><br><br><br>
    <p align="center"><input type="button" value=" 疑採壕崇斗 ">
			 			
				</div>
 	<div id="content"> 
 	<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 析鋼惟獣毒 "><br><br>
 	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="戚穿越"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="陥製越"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="越呪舛"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="越肢薦"/><br>
 	
 	<table id="td1" border="1" align="center">
 		<tr>
 			<th><% 
 					if(request.getAttribute("isAdmin") == "Y") {
 				%> 因走
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
			<td align="right">繕噺呪 : <%= request.getAttribute("hits") %>
 		</tr>  	
 	<table id="td3" border="1" align="center">
 		<br><tr>
			<td align="center"><%= request.getAttribute("content") %></td>
 		</tr>
 	<table id="td4" border="1" align="center">
 		<br><tr>
			<td align="center">
			奇越&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="去系"></td>
 		</tr>
 		
 	 	<table id="td5" border="1" align="center">
 		<br><tr>
			<td align="center">
			1匙推~せせせせせせせせ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="呪舛">&nbsp;&nbsp;<input type="button" value="肢薦"></td>
 		</tr>
 	</table>
 </div>
	</body>

<!-- footer -->
<hr>
<div class="footer"><font face="繕識析左誤繕">
鯵昔舛左坦軒号徴 | 紫戚闘己 | 戚五析巷舘呪増暗採 | 因獣舛左淫軒鋭舛<br> 
辞随獣 悪害姥 誌失稽 96掩 23(誌失疑 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
紫穣切 去系 腰硲 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>
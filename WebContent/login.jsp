<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title>(주)동부 World 로그인 페이지</title>
  <meta charset="utf-8"></meta>
  <style>
	body {
		height:100%;
		background-color: #F3F1E9;
	}
	label {
		display: inline-block;
		width:120px;
	}
	input {
		display: inline-block;
		width:160px;
	}
 </style>
 </head>

 <body>
  <header>
	 <h2> <a href="index.html">(주) 동부 World</a></h2>
 </header>
	<div id="page-wrap">

		<form method="post" action="process.jsp">
			<fieldset>
				<legend>로그인 정보입력</legend>
				<label for="name">ID</label>
				<input type="text" name="name" id="text1" /><br />
				<label for="password">패스워드</label>
				<input type="password" name="password" /><br />
			</fieldset>
			<a href="index.html">
			<input type="submit" name="submit" value="로그인" /></a>
			<button>비밀번호 찾기</button>
		</form>
	</div>

<!-- footer -->
<hr>
<div class="footer">
개인정보처리방침 | 사이트맵 | 이메일무단수집거부 | 공시정보관리규정<br> 
서울시 강남구 삼성로 96길 23(삼성동 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
사업자 등록 번호 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>

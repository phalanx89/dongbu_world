<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title>DONGBU WORLD (주)동부미식회</title>
  <meta charset="utf-8"></meta>

  <style>
	table {width:70%;
	       empty_cells:hide;          
	}
	th {height:50px;
	    vertical-align: center;
	}
	td {height:100px;
	    vertical-align: inherit;
	}
  </style>

 </head>
 <body>
 <header>
	 <h2> <a href="index.html"> (주)동부미식회</a></h2>
 </header>

	<div id="page-wrap">

		<form method="post" action="process.jsp">
			<fieldset>
				
				<label for="menu">메뉴종류 : </label>
				<input type="checkbox" name="menu" valur="hansik" /> 한식
				<input type="checkbox" name="menu" valur="jungsik" /> 중식
				<input type="checkbox" name="menu" valur="ilsik" /> 일식
				<input type="checkbox" name="menu" valur="yangsik" /> 양식
				<input type="checkbox" name="menu" valur="sulzip" /> 술집<br />
				<label for="time">이동시간 : </label>
				<input type="checkbox" name="time" valur="5min" /> 5분 이내
				<input type="checkbox" name="time" valur="10min" /> 10분 이내
				<input type="checkbox" name="time" valur="15min" /> 15분 이내<br />
				<label for="menu">평&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 : </label>
				<input type="checkbox" name="rank" valur="ten" /> 10위 이내
				<input type="checkbox" name="rank" valur="twenty" /> 20위 이내
				<input type="checkbox" name="rank" valur="thirty" /> 30위 이내
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button>검색</button><button>후기 등록</button>
			</fieldset>
			
		</form>
	</div>

	<section>
	<s1>
			<br><img src="mapsample.jpg" width="800"></s1>
		</article>
	</section>
<!-- footer -->
<hr>
<div class="footer">
개인정보처리방침 | 사이트맵 | 이메일무단수집거부 | 공시정보관리규정<br> 
서울시 강남구 삼성로 96길 23(삼성동 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
사업자 등록 번호 : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>

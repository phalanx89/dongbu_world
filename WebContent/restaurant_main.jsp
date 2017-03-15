<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" import="work.data.*" import="work.model.dto.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>맛집 검색</title>
<style type="text/css">
#container {
		background-color: #ffffff;
		width: 100%;
		height: 100%;
		padding: 0px;
		margin: 0px;
}

/* #sideinfo { */
/* 		background-color: #cccccc; */
/* 		float: left; */
/* 		width: 300px; */
/* 		height: 500px; */
/* 		padding: 5px; */
/* } */
#content {
		background-color: #F5F5F5;
		float: left;
		width: 100%;
		height: 750px;
		padding: 3px;
}

#map {
		float: left;
		width: 1200px;
		height: 500px;
}

#side_map {
		float: left;
		width: 298px;
		height: 500px;
		padding: 3px;
		background-color: #cccccc;
}

#footer {
		background-color: #ffffff;
		clear: both;
		height: 100px;
}

/* Full-width input fields */
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
		padding: 14px 20px;
		margin: 8px 0;
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
		padding: 14px 20px;
		margin: 8px 0;
		border: none;
		cursor: pointer;
		width: 100%;
}

input[type=submit]:hover, input[type=reset]:hover, input[type=button]:hover {
		opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
		width: auto;
		padding: 10px 18px;
		background-color: #f44336;
}

/* Center the image and position the close button */
.container2 {
		padding: 16px;
}

span.psw {
		float: right;
		padding-top: 16px;
}

/* The Modal (background) */
.modal {
		display: none; /* Hidden by default */
		position: fixed; /* Stay in place */
		z-index: 1; /* Sit on top */
		left: 0;
		top: 0;
		width: 100%; /* Full width */
		height: 100%; /* Full height */
		overflow: auto; /* Enable scroll if needed */
		background-color: rgb(0, 0, 0); /* Fallback color */
		background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
		padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
		background-color: #fefefe;
		margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
		border: 1px solid #888;
		width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
		position: absolute;
		right: 25px;
		top: 0;
		color: #000;
		font-size: 35px;
		font-weight: bold;
}

.close:hover, .close:focus {
		color: red;
		cursor: pointer;
}

/* Add Zoom Animation */
.animate {
		-webkit-animation: animatezoom 0.6s;
		animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {
		from {-webkit-transform: scale(0)
}

to {
		-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {
		from {transform: scale(0)
}

to {
		transform: scale(1)
}

}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
		span.psw {
				display: block;
				float: none;
		}
		.cancelbtn {
				width: 100%;
		}
}
</style>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=fd96bb5ffbbe3c54d0416e62bee493f5&libraries=services "></script>
<script type="text/javascript">
  
</script>
</head>
<body>
		<div id="container">
				<div id="header">
						<jsp:include page="top.jsp"></jsp:include>
				</div>
				<div id="content">
						<%
						  ArrayList<Restaurant> aryRestaurants = (ArrayList<Restaurant>) request.getAttribute("aryRestaurants");
						%>
						<div id="map"></div>
						<script>
              var curLatLng;
              var curAddress;
              var container = document.getElementById('map');
              var options = {
              center : new daum.maps.LatLng(37.5093347, 127.057700),
              level : 3
              };

              var map = new daum.maps.Map(container, options);

              // 마커 (회사)
              var markerDongbu = new daum.maps.Marker({
              position : new daum.maps.LatLng(37.5093347, 127.057700),
              map : map
              });

              // 마커 (유저)
              var markerUser = new daum.maps.Marker({
              position : new daum.maps.LatLng(37.5093347, 127.057700),
              map : map
              });

              // 인포윈도우를 생성합니다
              var infowindow = new daum.maps.InfoWindow({
                content : '<div style="padding:5px;" align="center">짠!</div>'
              });

              // 리스너 등록
              daum.maps.event.addListener(map, 'click', function(mouseEvent) {
                // 클릭한 위도, 경도 정보를 가져옵니다 
                var latlng = mouseEvent.latLng;
                infowindow.close();
                markerUser.setPosition(latlng);
                curLatLng = latlng;

                searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
                  if (status === daum.maps.services.Status.OK) {
                    var detailAddr = !!result[0].roadAddress.name ? '<div>도로명주소 : ' + result[0].roadAddress.name + '</div>' : '';
                    detailAddr += '<div>지번 주소 : ' + result[0].jibunAddress.name + '</div>';

                    var content = '<div class="bAddr">' + detailAddr + '</div>';
					console.log(detailAddr);
                    curAddress = result[0].jibunAddress.name;
                  }
                });
              });

              daum.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
                // 마커에 우클릭 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                //infowindow.open(map, markerUser);
                
              });

              daum.maps.event.addListener(markerUser, 'rightclick', function(mouseEvent) {
                var content = '<div style="width:100%; padding:3px" align="center"> 여기에 새로운 맛집을 등록할래요? <br><button type="button" style="width: auto;" onclick="moveRegister()";>등록!</button>&nbsp;</div>';
                infowindow.setContent(content);
                infowindow.open(map, markerUser);
              });

              //set custom function
              function addMarker(location) {
                var markerPosition = new daum.maps.LatLng(location.getLat(), location.getLng()); // 마커가 표시될 위치입니다

                // 마커를 생성합니다
                var marker = new daum.maps.Marker({
                position : markerPosition,
                map : map
                });
              }

              function searchDetailAddrFromCoords(coords, callback) {
                // 주소-좌표 변환 객체를 생성합니다
                var geocoder = new daum.maps.services.Geocoder();
                // 좌표로 법정동 상세 주소 정보를 요청합니다
                geocoder.coord2detailaddr(coords, callback);
              }

              function moveRegister() {
                document.getElementById('registerRestaurant').style.display = 'block';

                document.getElementById('address').value = curAddress;
                document.getElementById('coords').value = curLatLng.getLat() + "/" + curLatLng.getLng();
              }

              /*new window for restaurant registration*/
              // Get the modal
              var modal = document.getElementById('registerRestaurant');

              // When the user clicks anywhere outside of the modal, close it
              window.onclick = function(event) {
                console.log("click!!");
                if (event.target == modal) {
                  modal.style.display = "none";
                }
              }
            </script>
						<div id="side_map">
								<form method="post" action="http://localhost:8090/dongbu_world/food_controller?action=<%=Define.ACTION_SEARCH_RESTAURANT%>">
										<div wdith="100%" align="center">검색 조건 설정</div>
										<hr>
										<div id="frm_search" align="center" >
												<select name="columnName" style="width:75px;height:42px;">
													<option value="restaurant">식당명</option>
													<option value="empNo">작성자</option>
												</select><input type="text" name="restaurant" style="width:220px;"/> <input type="button" name="btnRestaurant" value="검색" style="width: auto;" />
										</div>
										<hr>
										<div id="frm_cbx">
												<%
												  for (int i = 0; i < Define.ARY_MENUS.length; i++) {
												    out.write(" <input name='cbxMenus' type='checkbox'  class='checkbox' value='" + Define.ARY_MENUS[i] + "'>" + Define.ARY_MENUS[i]);
												  }
												%>
										</div>
										<hr>
										<div id="frm_cbx">
												<%
												  for (int i = 0; i < Define.ARY_PRICES.length; i++) {
												    out.write("<input name='cbxPrices' type='checkbox'  class='checkbox' value='" + Define.ARY_PRICES[i] + "'> " + Define.ARY_PRICES[i]);
												  }
												%>
										</div>
										<hr>
										<div id="frm_cbx">
												<%
												  for (int i = 0; i < Define.ARY_DISTANCES.length; i++) {
												    if (i == 2) {
												      out.write("<br>");
												    }
												    out.write("<input name='cbxDistances' type='checkbox'  class='checkbox' value='" + Define.ARY_DISTANCES[i] + "'> " + Define.ARY_DISTANCES[i]);
												  }
												%>
										</div>
										<hr>
										<div id="frm_cbx">
												<%
												  for (int i = 0; i < Define.ARY_RATES.length; i++) {
												    out.write("<input name='cbxRates' type='checkbox'  class='checkbox' value='" + Define.ARY_RATES[i] + "'> " + Define.ARY_RATES[i]);
												  }
												%>
										</div>
										<hr>
										<div id="frm_submit" align="center">
												<input type="submit" value="검색" style="width: 100px;"> <input type="reset" value="초기화" style="width: 100px;">
										</div>
										<hr>
								</form>
						</div>
						<div id="registerRestaurant" class="modal">
								<form class="modal-content animate" method="post" action="http://localhost:8090/dongbu_world/food_controller?action=<%=Define.ACTION_REGISTER_RESTAURANT%>">
										<div class="container2">
												<label><b>제목</b></label> <input type="text" placeholder="제목을 입력하세요!" name="title" required> 
												<label><b>음식점 이름</b></label> <input type="text" placeholder="음식점 이름을 입력하세요!" name="restaurant" required> 
												<label><b>음식종류</b></label><br> 
												<input type="radio" name="menuType" value="한식">한식</input> 
												<input type="radio" name="menuType" value="일식">일식</input> 
												<input type="radio" name="menuType" value="분식">분식</input> 
												<input type="radio" name="menuType" value="중식">중식</input> 
												<input type="radio" name="menuType" value="양식">양식</input> 
												<input type="radio" name="menuType" value="아시안">아시안</input> 
												<input type="radio" name="menuType" value="술집">술집</input> 
												<input type="radio" name="menuType" value="퓨전음식">퓨전음식</input> 
												<input type="radio" name="menuType" value="치킨">치킨</input> 
												<input type="radio" name="menuType" value="족발/보쌈">족발/보쌈</input> 
												<input type="radio" name="menuType" value="피자/버거">피자/버거</input><br> 
												<label><b>가격대</b></label><br> 
												<input type="radio" name="price" value="1">~1만원</input> 
												<input type="radio" name="price" value="2">~2만원</input> 
												<input type="radio" name="price" value="3">~3만원</input><br> 
												<label><b>주소</b></label> <input type="text" placeholder="" id="address" 	name="address" readonly="readonly" required> 
												<label><b>내용</b></label> <input type="text" placeholder="상세한 내용을 입력하세요!" name="content" required>
												<label><b>좌표</b></label> <input type="text" placeholder="" id="coords" 	name="coords" readonly="readonly" required>
												<button type="submit">등록!</button>
										</div>
										<div class="container2" style="background-color: #f1f1f1">
												<button type="button" onclick="document.getElementById('registerRestaurant').style.display='none'" class="cancelbtn">Cancel</button>
												<span class="psw">Hi? <a href="#">안녕?</a></span>
										</div>
								</form>
						</div>
						<!-- 				<div id="sideinfo"></div> -->
						<div id="footer">
								<jsp:include page="footer.jsp"></jsp:include>
						</div>
				</div>
</body>
</html>
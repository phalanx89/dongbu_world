<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" import="work.data.*" import="work.model.dto.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
#container {
		background-color: #F0F0F0;
		width: 95%;
		height: 100%;
		margin: 0 auto;
		padding: 10px;
		margin: 0 auto;
}

#header {
		background-color: #908886;
		height: 100px;
}

#sideinfo {
		background-color: #777777;
		float: left;
		width: 300px;
		height: 700px;
}

#frm_submit {
		width: 100%;
		clear: both;
}

#content {
		background-color: #F5F5F5;
		float: left;
		width: 660px;
		height: 700px;
		padding: 10px;
		width: 660px;
}

#map {
		width: 100%;
		height: 100%;
}

#footer {
		background-color: #555555;
		clear: both;
		height: 100px;
}
</style>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=fd96bb5ffbbe3c54d0416e62bee493f5"></script>
<script type="text/javascript">
  
</script>
<%!%>
</head>
<body>
		<%
		  ArrayList<Restaurant> aryRestaurants = (ArrayList<Restaurant>) request.getAttribute("aryRestaurants");
		%>
		<div id="container">
				<div id="header"></div>
				<div id="sideinfo">
						<form method="post" action="http://localhost:8090/dongbu_world/food_controller?action=<%=Define.ACTION_SEARCH_RESTAURANT%>">
								<div id="frm_cbx">
										<%
										  for (int i = 0; i < Define.ARY_MENUS.length; i++) {
														out.write("<input name='cbxMenus' type='checkbox' value='" + Define.ARY_MENUS[i] + "'> "
																+ Define.ARY_MENUS[i]);
													}
										%>
										<hr>
								</div>
								<div id="frm_submit" align="center">
										<input type="submit" value="검색"> <input type="reset" value="초기화">
								</div>
								<hr>
						</form>
				</div>
				<div id="content">
						<div id="map"></div>
						<p id="result"></p>
						<script>
              var container = document.getElementById('map');
              var options = {
              center : new daum.maps.LatLng(37.5093347, 127.057700),
              level : 3
              };

              var map = new daum.maps.Map(container, options);

              // 				var imageSrc = '//localhost:8090/dongbu_world/images/logo.png', // 마커이미지의 주소입니다    
              // 				imageSize = new daum.maps.Size(64, 69), // 마커이미지의 크기입니다
              // 				imageOption = {
              // 					offset : new daum.maps.Point(27, 69)
              // 				}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

              // 				// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
              // 				var markerImage = new daum.maps.MarkerImage(imageSrc,
              // 						imageSize, imageOption)
              var markerPosition = new daum.maps.LatLng(37.5093347, 127.057700); // 마커가 표시될 위치입니다

              // 마커를 생성합니다
              var marker = new daum.maps.Marker({
                position : markerPosition
              });

              // 마커가 지도 위에 표시되도록 설정합니다
              marker.setMap(map);

              // 지도에 클릭 이벤트를 등록합니다
              // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
              daum.maps.event.addListener(map, 'click', function(mouseEvent) {

                // 클릭한 위도, 경도 정보를 가져옵니다 
                var latlng = mouseEvent.latLng;

                var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
                message += '경도는 ' + latlng.getLng() + ' 입니다';

                marker.setPosition(latlng);

                var resultDiv = document.getElementById('result');
                resultDiv.innerHTML = message;

              });
            </script>
				</div>
				<div id="footer"></div>
		</div>
</body>
</html>
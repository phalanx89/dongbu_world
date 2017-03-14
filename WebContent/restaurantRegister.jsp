<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" import="work.data.*" import="work.model.dto.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.checkbox {
		width: 12px;
}

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
		background-color: #cccccc;
		float: left;
		width: 300px;
		height: 500px;
		padding: 5px;
}

#frm_cbx {
		width: 100%;
}

#frm_search {
		width: 100%;
}

#frm_submit {
		width: 100%;
		clear: both;
}

#content {
		background-color: #F5F5F5;
		float: left;
		width: 500px;
		height: 500px;
		padding: 5px;
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

              var markerPosition = new daum.maps.LatLng(37.5093347, 127.057700); // ��Ŀ�� ǥ�õ� ��ġ�Դϴ�

              // ��Ŀ�� �����մϴ�
              var marker = new daum.maps.Marker({
                position : markerPosition
              });

              // ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
              marker.setMap(map);

              // ������ ���
              // ������ Ŭ���ϸ� ������ �Ķ���ͷ� �Ѿ�� �Լ��� ȣ���մϴ�
              daum.maps.event.addListener(map, 'click', function(mouseEvent) {
                // Ŭ���� ����, �浵 ������ �����ɴϴ� 
                var latlng = mouseEvent.latLng;

                var message = 'Ŭ���� ��ġ�� ������ ' + latlng.getLat() + ' �̰�, ';
                message += '�浵�� ' + latlng.getLng() + ' �Դϴ�';

                marker.setPosition(latlng);

                var resultDiv = document.getElementById('result');
                resultDiv.innerHTML = message;

              });

              daum.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
                console.log('right click');

                var latlng = mouseEvent.latLng;
                addMarker(latlng);
              });

              //set custom function
              function addMarker(location) {
                var markerPosition = new daum.maps.LatLng(location.getLat(), location.getLng()); // ��Ŀ�� ǥ�õ� ��ġ�Դϴ�

                // ��Ŀ�� �����մϴ�
                var marker = new daum.maps.Marker({
                position : markerPosition,
                map : map
                });
              }
            </script>
				</div>
				<div id="sideinfo">
						<form method="post" action="http://localhost:8090/dongbu_world/food_controller?action=<%=Define.ACTION_SEARCH_RESTAURANT%>">
								<div wdith="100%" align="center">�˻� ���� ����</div>
								<hr>
								<div id="frm_search" align="center">
										<input type="text" name="restaurant" size="15" max /> <input type="button" name="btnRestaurant" value="�˻�" />
								</div>
								<hr>
								<div id="frm_cbx">
										<%
										  for (int i = 0; i < Define.ARY_MENUS.length; i++) {
										    out.write("<input name='cbxMenus' type='checkbox'  class='checkbox' value='" + Define.ARY_MENUS[i] + "'> " + Define.ARY_MENUS[i]);
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
										<input type="submit" value="�˻�"> <input type="reset" value="�ʱ�ȭ">
								</div>
								<hr>
						</form>
				</div>
				<div id="footer"></div>
		</div>
</body>
</html>
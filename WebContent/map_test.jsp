<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>네이버 지도 API 테스트 페이지</title>
<!-- include naver map api -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=xR22H5I3UVJVZ_m677sf"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.1.1.js"
	integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
	crossorigin="anonymous"></script>
</head>
<body>
	<div id="map" style="width: 100%; height: 400px;"></div>

	<script>
		//지도 옵션 설정
		var mapOptions = {
			center: new naver.maps.LatLng(37.5093347, 127.057700),
			zoom : 10,
			zoomControl : true, 		//줌 컨트롤의 표시 여부
			zoomControlOptions : {	//줌 컨트롤의 옵션
				position : naver.maps.Position.TOP_RIGHT
			}
		};

		var map = new naver.maps.Map('map', mapOptions);

		var infowindow = new naver.maps.InfoWindow();

		function onSuccessGeolocation(position) {
			var location = new naver.maps.LatLng(position.coords.latitude,
					position.coords.longitude);

			//map.setCenter(location);	// 얻은 좌표를 지도의 중심으로 설정합니다.
			map.setZoom(10);			// 지도의 줌 레벨을 변경합니다.

			infowindow.setContent('<div style="padding:20px;">' + 'latitude: '
					+ location.lat() + '<br />' + 'longitude: '
					+ location.lng() + '</div>');

			//infowindow.open(map, location);
			
			//setMarkerPosition(location);
		}

		function onErrorGeolocation() {
			var center = map.getCenter();

			infowindow
					.setContent('<div style="padding:20px;">'
							+ '<h5 style="margin-bottom:5px;color:#f00;">Geolocation failed!</h5>'
							+ "latitude: " + center.lat() + "<br />longitude: "
							+ center.lng() + '</div>');

			infowindow.open(map, center);
		}

		$(window)
				.on(
						"load",
						function() {
							if (navigator.geolocation) {
								navigator.geolocation.getCurrentPosition(
										onSuccessGeolocation,
										onErrorGeolocation);
							} else {
								var center = map.getCenter();

								infowindow
										.setContent('<div style="padding:20px;"><h5 style="margin-bottom:5px;color:#f00;">Geolocation not supported</h5>'
												+ "latitude: "
												+ center.lat()
												+ "<br />longitude: "
												+ center.lng() + '</div>');
								infowindow.open(map, center);
							}
						});
		

		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(37.5093347, 127.057700),
		    map: map
		});

		naver.maps.Event.addListener(map, 'click', function(e) {
		    marker.setPosition(e.latlng);
		});
		
		function setMarkerPosition(location) {
			marker.setPosition(location);
		}
	</script>
</body>
</body>
</html>
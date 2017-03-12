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
<title>���̹� ���� API �׽�Ʈ ������</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=xR22H5I3UVJVZ_m677sf"></script>
<script
src="https://code.jquery.com/jquery-3.1.1.js"
integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
crossorigin="anonymous"></script>
</head>
<body>
	<div id="map" style="width: 100%; height: 400px;"></div>

	<script>
		//���� �ɼ� ����
		var mapOptions = {
			center : new naver.maps.LatLng(37.3595704, 127.105399),
			zoom : 10,
			zoomControl : true, //�� ��Ʈ���� ǥ�� ����
			zoomControlOptions : { //�� ��Ʈ���� �ɼ�
				position : naver.maps.Position.TOP_RIGHT
			}
		};

		var map = new naver.maps.Map('map', mapOptions);

		var infowindow = new naver.maps.InfoWindow();

		function onSuccessGeolocation(position) {
			var location = new naver.maps.LatLng(position.coords.latitude,
					position.coords.longitude);

			map.setCenter(location); // ���� ��ǥ�� ������ �߽����� �����մϴ�.
			map.setZoom(10); // ������ �� ������ �����մϴ�.

			infowindow.setContent('<div style="padding:20px;">' + 'latitude: '
					+ location.lat() + '<br />' + 'longitude: '
					+ location.lng() + '</div>');

			infowindow.open(map, location);
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
	</script>
</body>
</body>
</html>
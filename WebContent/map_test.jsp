<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="work.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>���̹� ���� API �׽�Ʈ ������</title>
<!-- include naver map api -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=xR22H5I3UVJVZ_m677sf"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.1.1.js"
	integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	//// ���� ����
	// ȸ�� ��ġ
	var coordiDongbu = new naver.maps.LatLng(37.5093347, 127.057700);
	var markers = new Array();
	var infoWindows = new Array();
</script>
</head>
<body>
	<div id="map" style="width: 100%; height: 800px;"></div>

	<script>
	//���� �ɼ� ����
	var mapOptions = {
		center: coordiDongbu,
		zoom : 10,
		zoomControl : true, 		//�� ��Ʈ���� ǥ�� ����
		zoomControlOptions : {	//�� ��Ʈ���� �ɼ�
			position : naver.maps.Position.TOP_RIGHT
		}
	};

	var map = new naver.maps.Map('map', mapOptions);
	var infowindow = new naver.maps.InfoWindow();
		
		//function		
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
		
		function onSuccessGeolocation(position) {
			var location = new naver.maps.LatLng(position.coords.latitude,
					position.coords.longitude);
			
			for (var i = 0; i < 10; i++) {
				var lat = coordiDongbu.lat() - (i * 0.01);
				var lng = coordiDongbu.lng() - (i * 0.01);
				var location = new naver.maps.LatLng(lat, lng);
				addMarker(location);
				addInfoWindow(location);
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
			}
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

		naver.maps.Event.addListener(map, 'click', function(e) {
		    marker.setPosition(e.latlng);
		});
		
		// marker
		function addMarker(location) {
			var marker = new naver.maps.Marker({
			    position: location,
			    map: map
			});

			markers.push(marker);
		}
		
		function addInfoWindow(location) {
			var infoWindow = new naver.maps.InfoWindow({
		        content: '<div style="width:150px;text-align:center;padding:10px;">The Letter is <b>tt</b>.</div>'
		    });

			infoWindows.push(infoWindow);
		}
		
		function setMarkerPosition(location) {
			marker.setPosition(location);
		}
		
		// �ش� ��Ŀ�� �ε����� seq��� Ŭ���� ������ �����ϴ� �̺�Ʈ �ڵ鷯�� ��ȯ�մϴ�.
		function getClickHandler(seq) {
		    return function(e) {
		        var marker = markers[seq],
		        infoWindow = infoWindows[seq];

		        if (infoWindow.getMap()) {
		            infoWindow.close();
		        } else {
		            infoWindow.open(map, marker);
		        }
		    }
		}
		
		// ������ ��Ŭ�� �̺�Ʈ ���
		naver.maps.Event.addListener(map, 'rightclick', function(e) {
			console.log(e);
			
			infowindow.setContent('<div style="padding:20px;"><h5 style="margin-bottom:5px;color:#f00;">Geolocation not supported</h5>'+ "latitude: "+ e.coord._lat +"<br />longitude: "+ e.coord._lng +'</div>');
			infowindow.open(map, new naver.maps.LatLng(e.coord._lat, e.coord._lng));
		});
	</script>
</body>
</body>
</html>
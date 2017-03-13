<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Daum Map</title>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=fd96bb5ffbbe3c54d0416e62bee493f5"></script>
</head>
<body>
	<div id="map" style="width:500px;height:400px;"></div>
	<p id="result"></p>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new daum.maps.LatLng(37.5093347, 127.057700),
			level: 3
		};

		var map = new daum.maps.Map(container, options);

		var imageSrc = '//localhost:8090/dongbu_world/images/logo.png', // ��Ŀ�̹����� �ּ��Դϴ�    
		imageSize = new daum.maps.Size(64, 69), // ��Ŀ�̹����� ũ���Դϴ�
		imageOption = {
			offset : new daum.maps.Point(27, 69)
		}; // ��Ŀ�̹����� �ɼ��Դϴ�. ��Ŀ�� ��ǥ�� ��ġ��ų �̹��� �ȿ����� ��ǥ�� �����մϴ�.

		// ��Ŀ�� �̹��������� ������ �ִ� ��Ŀ�̹����� �����մϴ�
		var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption)
		var markerPosition = new daum.maps.LatLng(37.5093347, 127.057700); // ��Ŀ�� ǥ�õ� ��ġ�Դϴ�

		// ��Ŀ�� �����մϴ�
		var marker = new daum.maps.Marker({
			position : markerPosition,
			image : markerImage		// ��Ŀ�̹��� ���� 
		});

		// ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
		marker.setMap(map);

		// ������ Ŭ�� �̺�Ʈ�� ����մϴ�
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
	</script>
</body>
</html>
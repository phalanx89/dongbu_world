<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" import="work.data.*" import="work.model.dto.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� �˻�</title>
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
		background-color: #ffffff;
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
		height: 497px;
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

/* Center the image and position the close button */
.container3 {
		width: 200px;
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
						  ArrayList<Restaurant> list = (ArrayList<Restaurant>) request.getAttribute("list");
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

        
              // ��Ŀ (ȸ��)
              var imageSrc = 'images/marker_flag.png', // ��Ŀ�̹����� �ּ��Դϴ�    
              imageSize = new daum.maps.Size(50, 50), // ��Ŀ�̹����� ũ���Դϴ�
              imageOption = {
                offset : new daum.maps.Point(17, 50)
              }; // ��Ŀ�̹����� �ɼ��Դϴ�. ��Ŀ�� ��ǥ�� ��ġ��ų �̹��� �ȿ����� ��ǥ�� �����մϴ�.

              var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption);
              
              var markerDongbu = new daum.maps.Marker({
              position : new daum.maps.LatLng(37.5093347, 127.057700),
              image: markerImage,	
              map : map
              });

              // ��Ŀ (����)
              var markerUser = new daum.maps.Marker({
              position : new daum.maps.LatLng(37.5093347, 127.057700),
              });

              // ��Ŀ (���ϵ� ������)
              var markers = new Array();
              loadMarkers();

              // ���������츦 �����մϴ�
              var infowindow = new daum.maps.InfoWindow({
                content : '<div style="padding:5px;" align="center">§!</div>'
              });

              // ������ ���
              daum.maps.event.addListener(map, 'click', function(mouseEvent) {
                // Ŭ���� ����, �浵 ������ �����ɴϴ� 
                var latlng = mouseEvent.latLng;
                infowindow.close();
                markerUser.setPosition(latlng);
                markerUser.setMap(map);
                curLatLng = latlng;

                searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
                  if (status === daum.maps.services.Status.OK) {
                    var detailAddr = !!result[0].roadAddress.name ? '<div>���θ��ּ� : ' + result[0].roadAddress.name + '</div>' : '';
                    detailAddr += '<div>���� �ּ� : ' + result[0].jibunAddress.name + '</div>';

                    var content = '<div class="bAddr">' + detailAddr + '</div>';
                    console.log(detailAddr);
                    curAddress = result[0].jibunAddress.name;
                  }
                });
              });

              daum.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
                // ��Ŀ�� ��Ŭ�� �̺�Ʈ�� �߻��ϸ� ���������츦 ��Ŀ���� ǥ���մϴ�
                //infowindow.open(map, markerUser);

              });

              daum.maps.event.addListener(markerUser, 'rightclick', function(mouseEvent) {
                var content = '<div style="width:100%; padding:3px" align="center"> ���⿡ ���ο� ������ ����ҷ���? <br><button type="button" style="width: auto;" onclick="moveRegister()";>���!</button>&nbsp;</div>';
                infowindow.setContent(content);
                infowindow.open(map, markerUser);
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

              function searchDetailAddrFromCoords(coords, callback) {
                // �ּ�-��ǥ ��ȯ ��ü�� �����մϴ�
                var geocoder = new daum.maps.services.Geocoder();
                // ��ǥ�� ������ �� �ּ� ������ ��û�մϴ�
                geocoder.coord2detailaddr(coords, callback);
              }

              function moveRegister() {
                document.getElementById('registerRestaurant').style.display = 'block';

                document.getElementById('address').value = curAddress;
                document.getElementById('coords').value = curLatLng.getLat() + "/" + curLatLng.getLng();
              }
              
              function showRestaurantDetail(restaurant, menuType, price, articleNo, address, content, title, takeMin, rate) {
                document.getElementById('restaurantDetail').style.display = 'block';
				
                document.getElementById('detail_restaurant').value = restaurant;
                document.getElementById('detail_menuType').value = menuType;
                document.getElementById('detail_price').value = '~' + price + '����';
                document.getElementById('detail_address').value = address;
                document.getElementById('detail_content').value = content;
                document.getElementById('detail_title').value = title;
                document.getElementById('detail_takeMin').value = takeMin + '��';
                document.getElementById('detail_rate').value = rate;
                document.getElementById('hidden').value = articleNo;
              }

              function loadMarkers() {
                var imageSrc = 'images/marker_star.png', // ��Ŀ�̹����� �ּ��Դϴ�    
                imageSize = new daum.maps.Size(25, 50), // ��Ŀ�̹����� ũ���Դϴ�
                imageOption = {
                  offset : new daum.maps.Point(12, 50)
                }; // ��Ŀ�̹����� �ɼ��Դϴ�. ��Ŀ�� ��ǥ�� ��ġ��ų �̹��� �ȿ����� ��ǥ�� �����մϴ�.

                var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption);
                
                var aryTmp;
                var lat, lng;
            <%
            int articleNo = 0;
            String restaurant = "";
            String menuType = "";
            String price = "";
            String address = "";
            String content = "";
            String title = "";
            int takeMin = 0;
            int rate = 0;
            
			String[] coords;
			String lat = "";
			String lng = "";
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Restaurant dto = list.get(i);
					articleNo = dto.getArticle_no();
					restaurant = dto.getRestaurant();
					menuType = dto.getMenuType();
					price = dto.getPrice();
					address = dto.getAddress();
					content = dto.getContent();
					title = dto.getTitle();
					takeMin = dto.getTakeMin();
					rate = dto.getRate();
					
					coords = dto.getCoords().split("/");
					lat = coords[0];
					lng = coords[1];%>
              var marker = new daum.maps.Marker({
                position : new daum.maps.LatLng(
            <%=lat%>
              ,
            <%=lng%>
              ),
              image : markerImage,
                map : map
                });

                // ��Ŀ�� ǥ���� ���������츦 �����մϴ� 
                var infoHtml = getRestaurantHTML('<%=restaurant%>', '<%=menuType%>', '<%=price%>', '<%=articleNo%>', '<%=address%>', '<%=content%>', '<%=title%>', '<%=takeMin%>', '<%=rate%>');
                var infowindow = new daum.maps.InfoWindow({
                  content : '<div>' + infoHtml + '</div>',
                  removable : true
                });

                daum.maps.event.addListener(marker, 'click', makeRightClickListener(map, marker, infowindow));

                markers.push(marker);
            <%}
			}%>
              }
              
              function makeRightClickListener(map, marker, infowindow) {
                return function() {
                  infowindow.close();
                  infowindow.open(map, marker);
                }
              }
              
              function getRestaurantHTML(restaurant, menuType, price, articleNo, address, content, title, takeMin, rate) {
               	var html = '<div class="container3">';
               	var param = "'" + restaurant + "', '" + menuType + "', '" + price + "', '" + articleNo + "', '" + address + "', '" + content + "', '" + title + "', '" + takeMin + "', '" + rate + "'";
               	
               	console.log(param);
               	
               	price = '~' + price + '����';
               	
               	html += '<label><b>������ �̸�</b></label> <input type="text" value="' + restaurant +'" readonly="readonly"> ';
               	html += '<label><b>����</b></label> <input type="text" value="' + rate +'" readonly="readonly"> ';
               	html += '</div>';
                html += '<div class="container3" style="background-color: #f1f1f1" align="center"><button type="button" onclick="showRestaurantDetail(';
               	html += param;
               	html += ')">�󼼺���</button></div>';
               	
               	return html;
              }
             
              function deleteRestaurant(articleNo) {
                location.href= 'food_controller?action=<%=Define.ACTION_DELETE_RESTAURANT%>&articleNo=' + articleNo;
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
										<div wdith="100%" align="center"><label><b>������ �̸� �˻�</b></label></div>
										<hr>
										<div id="frm_search" align="center">
												<select name="columnName" style="width: 75px; height: 42px;">
														<option value="restaurant">�Ĵ��</option>
														<option value="empNo">�ۼ���</option>
												</select><input type="text" name="restaurant" style="width: 220px;" /> <input type="button" name="btnRestaurant" value="�˻�" style="width: 280px;" />
										</div>
										<hr>
										<div wdith="100%" align="center"><label><b>���� �˻�</b></label></div>
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
												<input type="submit" value="�˻�" style="width: 140px;"> <input type="reset" value="�ʱ�ȭ" style="width: 140px;">
										</div>
								</form>
						</div>
						<div id="registerRestaurant" class="modal">
								<form class="modal-content animate" method="post" action="http://localhost:8090/dongbu_world/food_controller?action=<%=Define.ACTION_REGISTER_RESTAURANT%>">
										<div class="container2">
												<label><b>������ �̸�</b></label> <input type="text" placeholder="������ �̸��� �Է��ϼ���!" name="restaurant" required>
												<label><b>����</b></label> <input type="text" placeholder="������ �Է��ϼ���!" name="title" required> 
												<label><b>����</b></label><br> <input type="radio" name="rate"value="5" required>5��</input><input type="radio" name="rate"value="4">4��</input><input type="radio" name="rate"value="3">3��</input><input type="radio" name="rate"value="2">2��</input><input type="radio" name="rate"value="1">1��</input><br>
												<label><b>��������</b></label><br> <input type="radio" name="menuType" value="�ѽ�" required>�ѽ�</input> <input type="radio" name="menuType" value="�Ͻ�">�Ͻ�</input> <input type="radio" name="menuType" value="�н�">�н�</input> <input type="radio" name="menuType" value="�߽�">�߽�</input> <input type="radio" name="menuType" value="���">���</input> <input type="radio" name="menuType" value="�ƽþ�">�ƽþ�</input> <input type="radio" name="menuType" value="����">����</input> <input type="radio" name="menuType" value="ǻ������">ǻ������</input> <input type="radio" name="menuType" value="ġŲ">ġŲ</input> <input type="radio" name="menuType" value="����/����">����/����</input> <input type="radio" name="menuType" value="ī��">ī��</input> <input type="radio" name="menuType" value="����/����">����/����</input><br> 
												<label><b>���ݴ�</b></label><br> <input type="radio" name="price"value="1">~1����</input> <input type="radio" name="price" value="2">~2����</input> <input type="radio" name="price" value="3">~3����</input><br> 
												<label><b>�ּ�</b></label> <input type="text" placeholder="" id="address" name="address" readonly="readonly" required> 
												<label><b>����</b></label> <input type="text" placeholder="���� ������ �Է��ϼ���!" name="content" required> 
												<label><b>��ǥ</b></label> <input type="text" placeholder="" id="coords" name="coords" readonly="readonly" required>
												<button type="submit">���!</button>
										</div>
										<div class="container2" style="background-color: #f1f1f1">
												<button type="button" onclick="document.getElementById('registerRestaurant').style.display='none'" class="cancelbtn">Cancel</button>
										</div>
								</form>
						</div>
						<div id="restaurantDetail" class="modal">
								<form class="modal-content animate" method="post" action="#">
										<div class="container2">
												<label><b>������ �̸�</b></label> <input type="text" id="detail_restaurant" name="detail_restaurant" required>
												<label><b>����</b></label> <input type="text" id="detail_title" name="detail_title" readonly="readonly" required> 
												<label><b>����</b></label> <input type="text" id="detail_rate" name="detail_rate" readonly="readonly" required>
												<label><b>��������</b></label><br> <input type="text" id="detail_menuType" name="detail_menuType" readonly="readonly"  required> 
												<label><b>���ݴ�</b></label><br> <input type="text" id="detail_price" name="detail_price" readonly="readonly" >  
												<label><b>�ּ�</b></label> <input type="text" id="detail_address" name="detail_address" readonly="readonly" required> 
												<label><b>����</b></label> <input type="text" id="detail_content" name="detail_content" readonly="readonly" > 
												<label><b>�Ÿ�</b></label> <input type="text" id="detail_takeMin" name="detail_takeMin" readonly="readonly">
												<input type="hidden" id="hidden" value="">
										</div>
										<div class="container2" style="background-color: #f1f1f1">
												<button type="button" onclick="document.getElementById('restaurantDetail').style.display='none'" >Ȯ��</button>
												<button type="button" onclick="deleteRestaurant(document.getElementById('hidden').value)" class="cancelbtn">����</button>
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
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
 <head>

  <title>(��)���� World �α��� ������</title>
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
	 <h2> <a href="index.jsp">(��) ���� World</a></h2>
 </header>
	<div id="page-wrap">

		<form method="post" action="controller?action=login">
			<fieldset>
				<legend>�α��� �����Է�</legend>
				<label for="name">ID</label>
				<input type="text" name="empNo" id="text1" /><br />
				<label for="password">�н�����</label>
				<input type="password" name="userPw" /><br />
			</fieldset>
			<a href="index.html">
			<input type="submit" name="submit" value="�α���" /></a>
			<button>��й�ȣ ã��</button>
		</form>
	</div>

<!-- footer -->
<hr>
<div class="footer">
��������ó����ħ | ����Ʈ�� | �̸��Ϲ��ܼ����ź� | ����������������<br> 
����� ������ �Ｚ�� 96�� 23(�Ｚ�� 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
����� ��� ��ȣ : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>

  
 </body>
</html>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
<head>

<title><font face="�����Ϻ�����">(��)���� World �Խ��� ���� ������</title>
<meta charset="utf-8"></meta>
<style>
#nav {
	width: 10%;
	height: 500px;
	border: 1px solid black;
	float: left;
}

#content {
	width: 60%;
	height: 550px;
	border: 1px solid black;
	float: right right;
}

table {
	width: 70%;
	height: auto;
	empty_cells: hide;
}

#td1 {
	height: 40px;
	vertical-align: inherit;
}

#td2 {
	height: 270px;
	vertical-align: inherit;
}
</style>

</head>

<body>
	<header>
	<h2>
		<a href="index.jsp"> <font color="c#33B114" face="�����Ϻ�����"><em>DONGBU</em></font><font
			color="c#1EB930" face="�����Ϻ�����"><em> WORLD</em></font></a>
	</h2>
	</header>
	<div id="wrapper">
		<div id="nav">
			<br>
			<br>
			<p align="center">
				<a href='controller?action=selectFreeList'>�����Խ���</a>
			</p>
			<br>
			<br>
			<br>
			<p align="center">
				<a href='boardMain.jsp'>�͸�Խ���</a>
			</p>
			<br>
			<br>
			<br>
			<p align="center">
				<a href='boardMain.jsp'>��������</a>
			</p>
			<br>
			<br>
			<br>
			<p align="center">
				<a href='boardMain.jsp'>���ι����</a>
		</div>
		<div id="content">
			<br>
			<br>
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="button" value=" �ϹݰԽ��� ">
			<form method="post" action="controller?action=registerFreeByAdmin">
				<table id="td1" border="1" align="center">
					<br>
					<tr>
						<br>
						<td><font face="�����Ϻ�����">������ &nbsp;: <input type="text" name="title">&nbsp;
							<select name="isNotice">
								<option value="">=�������� ����=</option>
								<option value="Y">����</option>
								<option value="N">�Ϲ�</option>
							</select><br>
							<form enctype="multipart/form-data">
									����÷�� : <input type="file" accept="image/jpeg" ,image/gif>
								</form></td>
					</tr>
					<table id="td2" border="1" align="center">
						<br>
						<tr>
							<td align="center"><TEXTAREA ROWS="16" COLS="100" name='content'></TEXTAREA></td>
						</tr>

						<table id="td3" border="0" align="center">
							<br>
							<tr>
								<td align="center"><input type="submit" value="���" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="���"></td>
							</tr>
						</table>
						</form>
						</div>
</body>

<!-- footer -->
<hr>
<div class="footer">
	<font face="�����Ϻ�����"> ��������ó����ħ | ����Ʈ�� | �̸��Ϲ��ܼ����ź� | ����������������<br>
		����� ������ �Ｚ�� 96�� 23(�Ｚ�� 154-17)TEL : 02-2136-6000FAX : 02-2136-6007<br>
		����� ��� ��ȣ : 211-81-19938 (C) 2015 Dongbu Inc. All Rights Reserved.<br>
</div>


</body>
</html>
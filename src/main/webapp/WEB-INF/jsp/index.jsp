<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>weChat</title>
</head>

<script type="text/javascript">

function mobileLogin(){
	window.location.href='${mobileCodeUrl}';
}

function pcLogin(){
	window.location.href='${pcCodeUrl}';
}

</script>

<body>
微信开发Demo

<br>
<br>
<br>
<a href="javascript:void(0);" onclick="mobileLogin()">手机登录</a>
<br><br><br>
<a href="javascript:void(0);" onclick="pcLogin()">电脑登录</a>
${test }
</body>
</html>
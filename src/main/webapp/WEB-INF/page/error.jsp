<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="homeUrlParam.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>404</title>

<link type="text/css" rel="stylesheet" href="<%=basePath%>css/404.css" />

<!--[if IE 6]>
<script src="<%=basePath%>js/png.js"></script>
<script>DD_belatedPNG.fix('*')</script>
<![endif]-->

</head>
<body>

<div id="wrap">
	<div>
		<img src="<%=basePath%>img/404.png" alt="404" />
	</div>
	<div id="text">
		<strong>
			<span></span>
			<a href="<%=basePath%>index/forwardMainUI.do">返回首页</a>
		</strong>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>

</body>
</html>

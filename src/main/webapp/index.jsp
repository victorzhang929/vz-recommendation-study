<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>登录-学习资源个性化推荐系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/matrix-login.css"/>
    <link href="<%=basePath%>plugins/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <script src="<%=basePath%>js/jquery-1.8.2.js"></script>
    <script src="<%=basePath%>js/matrix.login.js"></script>
    <link rel="stylesheet" href="<%=basePath%>plugins/dialog/ui-dialog.css">
    <script src="<%=basePath%>plugins/dialog/dialog-min.js" type="text/javascript"></script>

    <link rel="shortcut icon" href="<%=basePath%>images/favicon.ico"/>

    <script type="text/javascript">
        var basePath = "<%=basePath%>";
        var path = "<%=path%>";
    </script>

    <script src="<%=basePath%>js/jCookie.js"></script>
    <script src="<%=basePath%>js/global.js"></script>
    <script src="<%=basePath%>js/page/index.js"></script>
</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="javascript:;">
        <div class="control-group normal_text"><h3 style="font-family:'黑体';">学习资源个性化推荐系统</h3></div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"></i></span>
                    <input id="username" type="text" placeholder="用户名"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock"></i></span>
                    <input id="password" type="password" placeholder="密码"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><a class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
            <span class="pull-right">
                <button type="submit" id="loginSubmit" class="btn btn-success" onclick="login()">登录</button>
            </span>
        </div>
    </form>
    <form id="recoverform" class="form-vertical" action="javascript:;">
        <p class="normal_text">在下方输入您的邮箱地址，我们将会给您的邮箱发送找到密码的邮件，请注意查收！</p>

        <div class="controls">
            <div class="main_input_box">
                <span class="add-on bg_lo"><i class="icon-envelope"></i></span>
                <input id="email" type="text" placeholder="邮箱地址"/>
            </div>
        </div>

        <div class="form-actions">
            <span class="pull-left">
                <a class="flip-link btn btn-success" id="to-login"><i class=" icon-chevron-left"></i> 返回登陆页面</a>
            </span>
            <span class="pull-right"><a class="btn btn-info" onclick="sendMail()">发送邮件</a></span>
        </div>
    </form>
</div>
</body>
</html>

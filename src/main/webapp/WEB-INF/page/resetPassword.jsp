<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
    
	<head>
        <title>重置密码--学习资源个性化推荐系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=basePath%>css/matrix-login.css" />
        <link href="<%=basePath%>plugins/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
        <script src="<%=basePath%>js/jquery-1.8.2.js"></script>  
        <script src="<%=basePath%>js/matrix.login.js"></script>
        <script src="<%=basePath%>js/global.js"></script>
        <script src="<%=basePath%>js/page/resetPassword.js"></script>
        <script src="<%=basePath%>js/jquery.url.js"></script>
        
        <script type="text/javascript">
        	var basePath = "<%=basePath%>";
    		var path = "<%=path%>";
        </script>
        
    	<link rel="stylesheet" href="<%=basePath%>plugins/dialog/ui-dialog.css">
    	<script src="<%=basePath%>plugins/dialog/dialog-min.js" type="text/javascript"></script>
        
        <link rel="shortcut icon" href="<%=basePath%>images/favicon.ico" /> 
	</head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical">
				<div class="control-group normal_text"> <h3 style="font-family:'黑体';">重置密码</h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-lock"></i></span><input id="password" type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input id="rePassword" type="password" placeholder="重复密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a class="flip-link btn btn-info" onclick="cancel()">重置</a></span>
                    <span class="pull-right"><a type="submit" id="resetSubmit" class="btn btn-success" onclick="doResetPassword()">确定</a></span>
                </div>
            </form>
        </div>
    </body>
</html>

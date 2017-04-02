<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <%@ include file="common.jsp" %>
    <title>学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/jCookie.js"></script>
    <script src="<%=basePath%>js/page/main.js"></script>
</head>

<body>
<div id="header">
    <h1></h1>
</div>

<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">

        <li class="dropdown" id="menu-messages">
            <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
                <i class="icon icon-envelope"></i>
                <span class="text">消息</span>
                <span class="label label-important" id='unreadSum'></span> <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" id='myMsgUnread'>
            </ul>
        </li>

        <li class="dropdown" id="profile-messages">
            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                <i class="icon icon-user"></i> <span class="text" id='realname'></span><b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#modalDiv" data-toggle='modal' onclick="changePasswordUI()"><i class="icon-pencil"></i>
                    修改密码</a></li>
                <li class="divider"></li>
                <li><a title="注销登陆当前账户" href="javascript:void(0);" onclick="exit()"><i class="icon-signout"></i>注销</a>
                </li>
            </ul>
        </li>
        <li>
            <a title="退出" href="javascript:void(0);" onclick="exit()"><i class="icon-off"></i> 退出</a>
        </li>

    </ul>
</div>

<div id="sidebar">
    <a href="javascript:void(0);" class="visible-phone"
       onclick="clickNavMenu('graphicLinkUI','<%=basePath%>index/forwardMainPage.do')"><i
            class="icon icon-home"></i></a>
    <ul>
        <li class="submenu" id='ul0Parent'>
            <a href="javascript:void(0);">
                <i class="icon-home"></i> <span>快速导航</span>
                <i class="icon-chevron-right"></i>
            </a>
            <ul id="ul0">
                <li>
                    <a href="javascript:void(0);" id="graphicLinkUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>index/forwardMainPage.do')">快速导航</a>
                </li>
            </ul>
        </li>
        <li class="submenu" id='ul1Parent'>
            <a href="javascript:void(0);">
                <i class="icon-cogs"></i> <span>个人中心</span>
                <i class="icon-chevron-right"></i>
            </a>
            <ul id="ul1">
                <li><a href="javascript:void(0);" id="userInfoUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardUserInfoUI.do')">用户信息</a></li>
                <!-- admin -->
                <c:if test="${ sessionScope.roleid =='0'}">
                    <li><a href="javascript:void(0);" id="publishNoticeUI"
                           onclick="clickNavMenu(this.id,'<%=basePath%>message/forwardPublishNoticeUI.do')">发布公告</a>
                    </li>
                </c:if>
                <li><a href="javascript:void(0);" id="noticeBoardUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>message/forwardNoticeBoardUI.do')">公告栏</a></li>
            </ul>
        </li>
        <li class="submenu" id='ul2Parent'>
            <a href="javascript:void(0);">
                <i class="icon-envelope"></i> <span>资源中心</span>
                <i class="icon-chevron-right"></i>
            </a>
            <ul id="ul2">
                <li><a href="javascript:void(0);" id="userResourceUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardUserResourceUI.do')">用户资源</a></li>
                <li><a href="javascript:void(0);" id="uploadResourceUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardSysResourceUI.do')">系统资源</a></li>
                <li><a href="javascript:void(0);" id="recommendedResourceUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardRecommendedResourceUI.do')">个性化推荐</a>
                </li>
                <li><a href="javascript:void(0);" id="commentResourceUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardCommentResourceUI.do')">我的评论</a></li>
                <c:if test="${ sessionScope.roleid =='0'}">
                    <li><a href="javascript:void(0);" id="verigyResourceUI"
                           onclick="clickNavMenu(this.id,'<%=basePath%>user/forwardVerifyResourceUI.do')">资源审核</a></li>
                </c:if>
            </ul>
        </li>
        <li class="submenu" id='ul3Parent'>
            <a href="javascript:void(0);">
                <i class="icon-list"></i> <span>日志中心</span>
                <i class="icon-chevron-right"></i>
            </a>
            <ul id="ul3">
                <%--0 represent admin--%>
                <li><a href="javascript:void(0);" id="userLogUI"
                       onclick="clickNavMenu(this.id,'<%=basePath%>log/forwardUserLogUI.do')">用户日志</a></li>
                <c:if test="${ sessionScope.roleid =='0'}">
                    <li><a href="javascript:void(0);" id="syslogUI"
                           onclick="clickNavMenu(this.id,'<%=basePath%>log/forwardSysLogUI.do')">系统日志</a></li>
                </c:if>
            </ul>
        </li>
        <c:if test="${ sessionScope.roleid =='0'}">
            <li class="submenu" id='ul4Parent'>
                <a href="javascript:void(0);">
                    <i class="icon-list"></i> <span>管理中心</span>
                    <i class="icon-chevron-right"></i>
                </a>
                <ul id="ul4">
                        <%--0 represent admin--%>
                    <li><a href="javascript:void(0);" id="userManagementUI"
                           onclick="clickNavMenu(this.id,'<%=basePath%>log/forwardUserManagementUI.do')">用户管理</a></li>

                    <li><a href="javascript:void(0);" id="resourceManagementUI"
                           onclick="clickNavMenu(this.id,'<%=basePath%>log/forwardResourceManagementUI.do')">资源管理</a>
                    </li>

                </ul>
            </li>
        </c:if>
    </ul>
</div>

<div id="content">
    <iframe id="iframepage" marginheight="0" marginwidth="0"
            frameborder="0" scrolling="auto" height="100%" width="100%"
            src="<%=basePath%>index/forwardGraphicLinkUI.do"></iframe>
</div>

<div class="row-fluid">
    <div id="footer" class="span12"> 2017 &copy; 学习资源个性化推荐系统--章伟</div>
</div>


<div id="modalDiv" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>修改密码</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" method="post" name="basic_validate">
            <div class="control-group">
                <label class="control-label">原密码:</label>
                <div class="controls">
                    <input id="oldpassword" type="password" name="oldpassword" style="height:28px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">新密码:</label>
                <div class="controls">
                    <input id="password" type="password" name="password" style="height:28px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码:</label>
                <div class="controls">
                    <input id="repassword" type="password" name="repassword" style="height:28px;"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div id='msg'></div>
        <a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a>
        <a class="btn btn-success" href="javascript:void(0);" onclick="changePassword()"> <i class="icon-ok"></i>确定</a>
    </div>
</div>
</body>
</html>
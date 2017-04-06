<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--admin roleId : DEDD7D0EDED9445083518A35EC5940AB --%>
<%@ include file="homeUrlParam.jsp" %>
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
            <ul class="dropdown-menu" id='myMsgUnread'></ul>
        </li>

        <li class="dropdown" id="profile-messages">
            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                <i class="icon icon-user"></i> <span class="text" id='realname'></span><b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#modalDiv" data-toggle='modal' onclick="changePasswordUI()"><i class="icon-pencil"></i>修改密码</a></li>
                <li class="divider"></li>
                <li><a title="注销登陆当前账户" href="javascript:void(0);" onclick="exit()"><i class="icon-signout"></i>注销</a></li>
            </ul>
        </li>
        <li><a title="退出" href="javascript:void(0);" onclick="exit()"><i class="icon-off"></i> 退出</a></li>
    </ul>
</div>

<div id="sidebar">
    <a href="<%= basePath %>index/forwardMainUI.do" class="visible-phone"><i class="icon icon-home"></i></a>
    <ul>
        <li class="submenu" id='ul0Parent'>
            <a href="javascript:void(0);"><i class="icon-home"></i> <span>快速导航</span><i class="icon-chevron-right"></i></a>
            <ul id="ul0">
                <li><a href="<%= basePath %>index/forwardMainUI.do" id="graphicLink">快速导航</a></li>
            </ul>
        </li>
        <li class="submenu" id='ul1Parent'>
            <a href="javascript:void(0);"> <i class="icon-cogs"></i> <span>个人中心</span> <i class="icon-chevron-right"></i> </a>
            <ul id="ul1">
                <li><a href="<%= basePath %>user/forwardUserInfoUI.do" id="userInfo">用户信息</a></li>
                <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                    <li><a href="<%= basePath %>message/forwardPublishNoticeUI.do" id="publishNotice">发布公告</a></li>
                </c:if>
                <li><a href="<%= basePath %>message/forwardNoticeBoardUI.do" id="noticeBoard">公告栏</a></li>
            </ul>
        </li>
        <li class="submenu" id='ul2Parent'>
            <a href="javascript:void(0);"><i class="icon-envelope"></i> <span>资源中心</span><i class="icon-chevron-right"></i></a>
            <ul id="ul2">
                <li><a href="<%= basePath %>user/forwardUserResourceUI.do" id="userResource">用户资源</a></li>
                <li><a href="<%= basePath %>user/forwardSysResourceUI.do" id="uploadResource">系统资源</a></li>
                <li><a href="<%= basePath %>user/forwardRecommendedResourceUI.do" id="recommendedResource">个性化推荐</a></li>
                <li><a href="<%= basePath %>user/forwardCommentResourceUI.do" id="commentResource">我的评论</a></li>
                <c:if test="${ sessionScope.roleId =='DEDDPath7D0EDED9445083518A35EC5940AB'}">
                    <li><a href="<%= basePath %>user/forwardVerifyResourceUI.do" id="verigyResource">资源审核</a></li>
                </c:if>
            </ul>
        </li>
        <li class="submenu" id='ul3Parent'>
            <a href="javascript:void(0);"><i class="icon-list"></i> <span>日志中心</span><i class="icon-chevron-right"></i></a>
            <ul id="ul3">
                <li><a href="<%= basePath %>log/forwardUserLogUI.do" id="userLog">用户日志</a></li>
                <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                    <li><a href="<%= basePath %>log/forwardSystemLogUI.do" id="systemLog">系统日志</a></li>
                </c:if>
            </ul>
        </li>
        <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
            <li class="submenu" id='ul4Parent'>
                <a href="javascript:void(0);"><i class="icon-list"></i> <span>管理中心</span><i class="icon-chevron-right"></i></a>
                <ul id="ul4">
                    <li><a href="<%= basePath %>log/forwardUserManagementUI.do" id="userManagement">用户管理</a></li>
                    <li><a href="<%= basePath %>log/forwardResourceManagementUI.do" id="resourceManagement">资源管理</a>
                    </li>
                </ul>
            </li>
        </c:if>
    </ul>
</div>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--admin roleId : DEDD7D0EDED9445083518A35EC5940AB --%>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/main.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="box">
        <div id="content-header">
            <div id="breadcrumb">
                <a href="#" title="首页" class="tip-bottom"> <i class="icon-th"></i> 快速导航 </a>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span9">
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_ly span3"><a href="<%=basePath%>user/forwardUserInfoUI.do"> <i class="icon-user"></i> 用户信息 </a></li>
                            <li class="bg_ly span3"><a href="<%=basePath%>message/forwardUserMessageUI.do"> <i class="icon-bell"></i> 用户消息 </a></li>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_ls span3"><a href="<%=basePath%>resource/forwardUserResourceUI.do"> <i class="icon-bar-chart"></i> 用户资源 </a></li>
                            <li class="bg_ls span3"><a href="<%=basePath%>resource/forwardSystemResourceUI.do"> <i class="icon-barcode"></i> 系统资源 </a></li>
                            <li class="bg_ls span3"><a href="<%=basePath%>browseRecord/forwardResourceBrowseRecordUI.do"> <i class="icon-share"></i> 资源浏览记录 </a></li>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_ls span3"><a href="<%=basePath%>downloadRecord/forwardResourceDownloadRecordUI.do"> <i class="icon-download-alt"></i> 资源下载记录 </a></li>
                            <li class="bg_ls span3"><a href="<%=basePath%>resource/forwardRecommendedResourceUI.do"> <i class="icon-magic"></i> 个性化推荐 </a></li>
                            <li class="bg_ls span3"><a href="<%=basePath%>resource/forwardCommentResourceUI.do"> <i class="icon-comments"></i> 我的评论 </a></li>
                        </ul>
                    </div>
                    <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                        <div class="quick-actions_homepage">
                            <ul class="quick-actions">
                                <li class="bg_ls span3"><a href="<%=basePath%>resource/forwardVerifyResourceUI.do"> <i class="icon-eye-open"></i> 资源审核 </a></li>
                            </ul>
                        </div>
                    </c:if>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_lo span3"><a href="<%=basePath%>log/forwardUserLogUI.do"> <i class="icon-bookmark"></i> 用户日志 </a></li>
                            <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                                <li class="bg_lo span3"><a href="<%=basePath%>log/forwardSystemLogUI.do"> <i class="icon-book"></i> 系统日志 </a></li>
                            </c:if>
                        </ul>
                    </div>
                    <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                        <div class="quick-actions_homepage">
                            <ul class="quick-actions">
                                <li class="bg_lg span3"><a href="<%=basePath%>manage/forwardUserManagementUI.do"> <i class="icon-group"></i> 用户管理 </a></li>
                                <li class="bg_lg span3"><a href="<%=basePath%>manage/forwardResourceManagementUI.do"> <i class="icon-globe"></i> 资源管理 </a></li>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <div class="span3">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>最新学习资源</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body" style="display: block;">
                            <ol id = "newestResource">
                            </ol>
                        </div>
                    </div>
                    <div class="portlet box red">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>热门资源</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body" style="display: block;">
                            <ol id = "hotResource">
                            </ol>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="foot.jsp" %>
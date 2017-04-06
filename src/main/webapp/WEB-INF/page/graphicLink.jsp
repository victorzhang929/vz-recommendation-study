<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--admin roleId : DEDD7D0EDED9445083518A35EC5940AB --%>
<%@ include file="homeUrlParam.jsp" %>

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
                            <li class="bg_ly span3"> <a href="clickNavMenu(this.id,'<%=basePath%>user/forwardUserInfoUI.do"> <i class="icon-user"></i> 用户信息 </a> </li>
                            <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                                <li class="bg_ly span3"> <a href="<%=basePath%>message/forwardPublishNoticeUI.do"> <i class="icon-pencil"></i> 发布公告 </a> </li>
                            </c:if>
                            <li class="bg_ly span3"> <a href="<%=basePath%>message/forwardNoticeBoardUI.do"> <i class="icon-table"></i> 公告栏 </a> </li>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_ls span3"> <a href="<%=basePath%>user/forwardUserResourceUI.do"> <i class="icon-bar-chart"></i> 用户资源 </a> </li>
                            <li class="bg_ls span3"> <a href="<%=basePath%>user/forwardSysResourceUI.do"> <i class="icon-upload"></i> 系统资源 </a> </li>
                            <li class="bg_ls span3"> <a href="<%=basePath%>user/forwardRecommendedResourceUI.do"> <i class="icon-magic"></i> 个性化推荐 </a> </li>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_ls span3"> <a href="<%=basePath%>user/forwardCommentResourceUI.do"> <i class="icon-comments"></i> 我的评论 </a> </li>
                            <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                                <li class="bg_ls span3"> <a href="<%=basePath%>user/forwardVerifyResourceUI.do"> <i class="icon-eye-open"></i> 资源审核 </a> </li>
                            </c:if>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_lo span3"> <a href="<%=basePath%>log/forwardUserLogUI.do"> <i class="icon-book"></i> 用户日志 </a> </li>
                            <c:if test="${ sessionScope.roleId =='DEDD7D0EDED9445083518A35EC5940AB'}">
                                <li class="bg_lo span3"> <a href="<%=basePath%>log/forwardSystemLogUI.do"> <i class="icon-wrench"></i> 系统日志 </a> </li>
                            </c:if>
                        </ul>
                    </div>
                    <div class="quick-actions_homepage">
                        <ul class="quick-actions">
                            <li class="bg_lg span3"> <a href="<%=basePath%>user/forwardUserManagementUI.do"> <i class="icon-bar-chart"></i> 用户管理 </a> </li>
                            <li class="bg_lg span3"> <a href="<%=basePath%>user/forwardResourceManagementUI.do"> <i class="icon-upload"></i> 资源管理 </a> </li>
                        </ul>
                    </div>
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
                            <ol>
                                <li>教育技术学导论</li>
                                <li>计算机网络</li>
                                <li>数据结构</li>
                                <li>美国名校励志演讲</li>
                                <li>2017年英语四级模拟真题</li>
                                <li>2017年英语六级模拟真题</li>
                                <li>考研政治肖秀荣四套题</li>
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
                            <ol>
                                <li>教育技术学导论</li>
                                <li>2017年英语四级模拟真题</li>
                                <li>2017年英语六级模拟真题</li>
                                <li>考研政治肖秀荣四套题</li>
                                <li>考研英语模拟真题</li>
                                <li>C语言程序设计</li>
                                <li>易中天中华史</li>
                            </ol>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>资源详情--学习资源个性化推荐系统</title>
    <link rel="stylesheet" href="<%=basePath%>css/resourceDetail.css"/>
    <script src="<%=basePath%>js/jquery.url.js"></script>
    <link rel="stylesheet" href="../plugins/jquery-raty/jquery.raty.css">
    <script src="../plugins/jquery-raty/jquery.raty.js"></script>
    <script src="<%=basePath%>js/page/resourceDetail.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="资源中心" class="tip-bottom"> <i class="icon-shopping-cart"></i> 资源中心 </a>
            <a href="javascript:void(0);" title="资源详情" class="tip-right"> 资源详情 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon"> <i class="icon-align-justify"></i>
                        </span>
                        <h5>详情介绍</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <div class="portlet">
                            <div class="portlet-title line">
                                <div class="caption"><i class="icon-comments"></i>资源名称：<span id="resourceName"></span></div>
                                <div class="caption">浏览次数：<span id="resourceBrowseCount"></span></div>
                                <div class="caption">下载次数：<span id="resourceDownloadCount"></span></div>
                                <div class="caption">资源当前评分：<span id="resourceAverageScore"></span></div>
                                <div class="caption">我的评分：
                                    <div id="rate" style="display: inline;"></div>
                                </div>
                            </div>
                            <div class="portlet-body" id="chats">
                                <div class="slimScrollDiv" id="commentTable"></div>
                                <div class="chat-form">
                                    <div class="input-cont">
                                        <textarea id="commentContent" class="form-control" type="text" placeholder="请输入您的评论..."></textarea>
                                        <input class="btn" type="submit" id="submitCommentContent" onclick="submitCommentContent()">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="foot.jsp" %>

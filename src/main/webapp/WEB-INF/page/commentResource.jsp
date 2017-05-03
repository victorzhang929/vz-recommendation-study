<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>我的评论--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/commentResource.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="资源中心" class="tip-bottom"> <i class="icon-shopping-cart"></i> 资源中心 </a>
            <a href="javascript:void(0);" title="我的评论" class="tip-right"> 我的评论 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <div class="controls controls-row">
                            <input type="text" id="queryName" class="span2 m-wrap" placeholder="资源名称">
                            <select id="queryType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                                <option value="">资源类型</option>
                                <option value="3">视频</option>
                                <option value="2">音频</option>
                                <option value="1">文档</option>
                                <option value="0">图片</option>
                            </select>
                            <input type="text" id="queryStartDate" class="span2 m-wrap" placeholder="开始日期"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate\')||\'2099-12-31\'}'})">
                            <input type="text" id="queryEndDate" class="span2 m-wrap" placeholder="结束日期"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',maxDate:'2099-12-31'})">
                            <button type="submit" id="query" class="span2 btn btn-success" onclick="load()"><i class="icon-search"></i> 查询</button>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="widget-box">
                    <div class="widget-content ">
                        <div id="tableDiv"></div>
                        <div id="tableDivPage"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="foot.jsp" %>
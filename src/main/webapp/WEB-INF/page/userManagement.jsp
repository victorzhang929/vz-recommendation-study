<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>用户管理--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/userManagement.js"></script>
</head>

<c:import url="head.jsp" />
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="管理中心" class="tip-bottom"> <i class="icon-wrench"></i> 管理中心 </a>
            <a href="javascript:void(0);" title="用户管理" class="tip-right"> 用户管理 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <div class="controls controls-row">
                            <input type="text" id="username" class="span2 m-wrap" placeholder="用户名称">
                            <select id="queryType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                            </select>
                            <input type="text" id="queryStartDate" class="span2 m-wrap" placeholder="开始日期"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate\')||\'2099-12-31\'}'})">
                            <input type="text" id="queryEndDate" class="span2 m-wrap" placeholder="结束日期"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',maxDate:'2099-12-31'})">
                            <button type="submit" id="query" class="span1 btn btn-success" onclick="load()"> <i class="icon-search"></i> 查询 </button>
                            <button type="submit" id="add" class="span1 btn btn-info"> <i class="icon-plus"></i> 添加 </button>
                            <button type="submit" id="addBatch" class="span1 btn btn-primary"> <i class="icon-plus-sign"></i> 批量添加 </button>
                            <button type="submit" id="removeBatch" class="span1 btn btn-danger"> <i class="icon-remove-sign"></i> 批量删除 </button>
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
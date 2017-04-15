<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>系统资源--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/systemResource.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="资源中心" class="tip-bottom"> <i class="icon-shopping-cart"></i> 资源中心 </a>
            <a href="javascript:void(0);" title="系统资源" class="tip-right"> 系统资源 </a>
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
                            <button type="submit" id="query" class="span1 btn btn-success" onclick="load()"><i class="icon-search"></i> 查询</button>
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

<div id="getModal" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>资源详情</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" method="post" name="basic_validate">
            <div class="control-group">
                <label class="control-label">资源名称:</label>
                <div class="controls">
                    <input id="resourceNameDetail" type="text" name="resourceNameDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源描述:</label>
                <div class="controls">
                    <textarea id="resourceDescriptionDetail" type="text" rows="2" name="resourceDescriptionDetail" disabled="disabled"></textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源标签:</label>
                <div class="controls">
                    <input id="resourceTagDetail" type="text" name="resourceTagDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源类型:</label>
                <div class="controls">
                    <input id="resourceTypeDetail" type="text" name="resourceTypeDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input id="gmtCreateDetail" type="text" name="gmtCreateDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input id="gmtModifyDetail" type="text" name="gmtModifyDetail" disabled="disabled"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a data-dismiss="modal" class="btn btn-success" href="javascript:void(0);"> 确认 </a>
    </div>
</div>
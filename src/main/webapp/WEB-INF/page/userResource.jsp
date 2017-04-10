<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>用户资源--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/userResource.js"></script>
</head>

<c:import url="head.jsp" />
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="资源中心" class="tip-bottom"> <i class="icon-shopping-cart"></i> 资源中心 </a>
            <a href="javascript:void(0);" title="用户资源" class="tip-right"> 用户资源 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <div class="controls controls-row">
                            <input type="text" id="username" class="span2 m-wrap" placeholder="资源名称">
                            <select id="queryVerifyType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                                <option value="">资源类型</option>
                                <option value="3">视频</option>
                                <option value="2">音频</option>
                                <option value="1">文档</option>
                                <option value="0">图片</option>
                            </select>
                            <select id="queryType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                                <option value="">审核状态</option>
                                <option value="2">正在审核</option>
                                <option value="1">审核通过</option>
                                <option value="0">审核不通过</option>
                            </select>
                            <input type="text" id="queryStartDate" class="span2 m-wrap" placeholder="开始日期"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate\')||\'2099-12-31\'}'})">
                            <input type="text" id="queryEndDate" class="span2 m-wrap" placeholder="结束日期"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',maxDate:'2099-12-31'})">
                            <button type="submit" id="query" class="span1 btn btn-success" onclick="load()"> <i class="icon-search"></i> 查询 </button>
                            <button id="upload" href="#uploadModal" data-toggle='modal' class="span1 btn btn-primary"><i class="icon-upload"></i>上传</button>
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

<div id="uploadModal" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>上传资源</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" method="post">
            <div class="control-group">
                <label class="control-label">选择资源:</label>
                <div class="controls">
                    <input id="um_chooseResource" type="file" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源名称:</label>
                <div class="controls">
                    <input id="um_resourcename" type="text" style="height:28px;" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源描述:</label>
                <div class="controls">
                    <textarea id="um_resourcedescription" type="text" style="height:28px;" rows="5"></textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源标签（使用“，”分隔）:</label>
                <div class="controls">
                    <input id="um_resourcetag" type="text" style="height:28px;" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源类型:</label>
                <div class="controls">
                    <select id="um_resourcetype" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                        <option value="">请选择</option>
                        <option value="3">视频</option>
                        <option value="2">音频</option>
                        <option value="1">文档</option>
                        <option value="0">图片</option>
                    </select>
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <div id='msg2'></div>
        <a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a>
        <a class="btn btn-success" href="javascript:void(0);" onclick="uploadResource()"> <i class="icon-ok"></i>上传</a>
    </div>
</div>
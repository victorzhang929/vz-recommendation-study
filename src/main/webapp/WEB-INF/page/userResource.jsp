<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>用户资源--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/userResource.js"></script>
</head>

<c:import url="head.jsp"/>
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
                            <input type="text" id="queryName" class="span2 m-wrap" placeholder="资源名称">
                            <select id="queryType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                                <option value="">资源类型</option>
                                <option value="3">视频</option>
                                <option value="2">音频</option>
                                <option value="1">文档</option>
                                <option value="0">图片</option>
                            </select>
                            <select id="queryVerifyType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
                                <option value="">审核状态</option>
                                <option value="2">审核不通过</option>
                                <option value="1">审核通过</option>
                                <option value="0">正在审核</option>
                            </select>
                            <input type="text" id="queryStartDate" class="span2 m-wrap" placeholder="开始日期"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate\')||\'2099-12-31\'}'})">
                            <input type="text" id="queryEndDate" class="span2 m-wrap" placeholder="结束日期"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',maxDate:'2099-12-31'})">
                            <button type="submit" id="query" class="span1 btn btn-success" onclick="load()"><i class="icon-search"></i> 查询</button>
                            <button id="upload" href="#uploadModal" data-toggle='modal' class="span1 btn btn-primary" onclick="uploadResourceUI()"><i class="icon-upload"></i>上传
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

<div id="uploadModal" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>上传资源</h3>
    </div>
    <form class="form-horizontal" method="post" enctype="multipart/form-data" id="uploadResourceForm" action="<%= basePath%>resource/doUploadResource.do">
        <div class="modal-body">
            <div class="control-group">
                <label class="control-label">选择资源:</label>
                <div class="controls">
                    <input id="resourceFile" type="file" name="resourceFile"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源名称:</label>
                <div class="controls">
                    <input id="resourceName" type="text" name="resourceName"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源描述:</label>
                <div class="controls">
                    <textarea id="resourceDescription" type="text" rows="5" name="resourceDescription"></textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源标签（“，”分隔）:</label>
                <div class="controls">
                    <input id="resourceTag" type="text" name="resourceTag"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源类型:</label>
                <div class="controls">
                    <select id="resourceType" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open" name="resourceType">
                        <option value="" selected="selected">请选择</option>
                        <option value="3">视频</option>
                        <option value="2">音频</option>
                        <option value="1">文档</option>
                        <option value="0">图片</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <div id='uploadMsg'></div>
            <a data-dismiss="modal" class="btn btn-danger" href="javascript:void(0);"> 取消 </a>
            <input id="btnUploadResource" type="button" class="btn btn-primary" onclick="uploadResource()" value="上传">
        </div>
    </form>
</div>

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
                <label class="control-label">资源下载数:</label>
                <div class="controls">
                    <input id="resourceDownloadCountDetail" type="text" name="resourceTagDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源浏览数:</label>
                <div class="controls">
                    <input id="resourceBrowseCountDetail" type="text" name="resourceTagDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">资源类型:</label>
                <div class="controls">
                    <input id="resourceTypeDetail" type="text" name="resourceTypeDetail" disabled="disabled"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">审核状态:</label>
                <div class="controls">
                    <input id="verifyTypeDetail" type="text" name="verifyTypeDetail" disabled="disabled"/>
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
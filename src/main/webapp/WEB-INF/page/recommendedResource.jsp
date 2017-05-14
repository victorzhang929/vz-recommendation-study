<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>个性化推荐--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/recommendedResource.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="资源中心" class="tip-bottom"> <i class="icon-list"></i> 资源中心
            </a> <a href="javascript:void(0);" title="个性化推荐" class="tip-right"> 个性化推荐 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <c:if test="${ sessionScope.roleId =='3791532340293158876'}">
                    <div class="widget-box">
                        <div class="widget-content nopadding">
                            <div class="controls controls-row">
                                <button type="submit" id="listUserRecommendation" class="span2 btn btn-success" onclick="userBasedRecommendation()"><i class="icon-user"></i>
                                    基于用户个性化推荐
                                </button>
                                <button type="submit" id="listItemRecommendation" class="span2 btn btn-success" onclick="itemBasedRecommendation()" style="float: right"><i class="icon-list"></i>
                                    基于项目个性化推荐
                                </button>
                            </div>
                        </div>
                    </div>
                </c:if>

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
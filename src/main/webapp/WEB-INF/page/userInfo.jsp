<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="common.jsp" %>
    <title>用户信息--学习资源个性化推荐系统</title>
    <script src="<%=basePath%>js/page/userInfo.js"></script>
</head>

<c:import url="head.jsp"/>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="javascript:void(0);" title="个人中心" class="tip-bottom"> <i class="icon-cogs"></i> 个人中心
            </a> <a href="javascript:void(0);" title="用户信息" class="tip-right"> 用户信息 </a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon"> <i class="icon-align-justify"></i></span><h5>用户信息</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form method="post" class="form-horizontal" id="userInfoForm">
                            <div class="control-group">
                                <label class="control-label">用户名 :</label>
                                <div class="controls">
                                    <input type="text" id="username" class="span11" placeholder="用户名不能为空"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">真实姓名 :</label>
                                <div class="controls">
                                    <input type="text" id="realName" class="span11" placeholder="真实姓名不能为空"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">角色 :</label>
                                <div class="controls">
                                    <input type="text" id="roleName" class="span11" placeholder="角色" disabled="disabled"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">身份证号 :</label>
                                <div class="controls">
                                    <input type="text" id="userIdCard" class="span11" placeholder="请输入身份证号"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">手机号 :</label>
                                <div class="controls">
                                    <input type="text" id="userMobile" class="span11" placeholder="请输入正确手机号"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">邮箱地址 :</label>
                                <div class="controls">
                                    <input type="text" id="userEmail" class="required span11" placeholder="请输入邮箱地址"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">性别 :</label>
                                <div class="controls">
                                    <div class="radio-list">
                                        <label style=" display: inline-block;margin-right: 30px">
                                            <input type="radio" name="gender" value="0" id="male"> 男
                                        </label>
                                        <label style=" display: inline-block;">
                                            <input type="radio" name="gender" value="1" id="female"> 女
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">个人标签（以“，”分隔） :</label>
                                <div class="controls">
                                    <input type="text" id="tag" class="required span11" placeholder="个人标签（以“，”分隔）"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <a href="javascript:void(0);" class="btn btn-success" onclick="saveUserInfo()">保存</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

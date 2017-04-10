<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row-fluid">
    <div id="footer" class="span12"> 2017 &copy; 学习资源个性化推荐系统--章伟</div>
</div>
</body>
</html>

<%--update user password dialog--%>
<div id="modalDiv" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>修改密码</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" method="post" name="basic_validate">
            <div class="control-group">
                <label class="control-label">原密码:</label>
                <div class="controls">
                    <input id="oldPassword" type="password" name="oldPassword" style="height:28px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">新密码:</label>
                <div class="controls">
                    <input id="password" type="password" name="password" style="height:28px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码:</label>
                <div class="controls">
                    <input id="rePassword" type="password" name="rePassword" style="height:28px;"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div id='msg'></div>
        <a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a>
        <a class="btn btn-success" href="javascript:void(0);" onclick="changePassword()"> <i class="icon-ok"></i>确定</a>
    </div>
</div>

$(function(){
    enterProccess("resetSubmit");
    if (!window.applicationCache) {
        $("#password").attr('placeholder','');
        $("#rePassword").attr('placeholder','');
    }
});

function cancel(){
    $("#password").val('');
    $("#rePassword").val('');
}

function doResetPassword(){
    if($("#password").val()==""){tipDialog("请输入密码");return false;}
    if($("#rePassword").val()==""){tipDialog("请重复输入密码");return false;}
    if($("#password").val()!=$("#rePassword").val()){tipDialog("两次密码输入不一致");return false;}

    var _path = path;
    tipDialog("正在提交……");
    var param = {};
    param.username = jQuery.url.param('username');
    param.checkCode = jQuery.url.param('checkcode');
    param.password = $("#password").val();
    param.rePassword = $("#rePassword").val();

    $.ajax({
        type: "POST",
        url: _path+"/index/doResetPassword.index",
        data: param,
        dataType: "text",
        error: function() {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        },success: function(result){
            if(result != '' && result != undefined){
                confirmDialog(result,basePath);
            }
        }
    });
}
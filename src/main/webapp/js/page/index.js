$(function () {
    enterProccess("loginSubmit");
    if (!window.applicationCache) {
        $("#username").attr('placeholder', '');
        $("#password").attr('placeholder', '');
    }
});

function login() {
    if ($("#username").val() == "") {
        tipDialog("请输入用户名");
        return false;
    }
    if ($("#password").val() == "") {
        tipDialog("请输入密码");
        return false;
    }
    tipDialog("正在登录……");
    $.ajax({
        type: "POST",
        url: path + "/index/login.index",
        data: {username: $("#username").val(), password: $("#password").val()},
        dataType: "json",
        error: function () {
            tipDialog("登录失败，连接错误。请刷新页面重试。");
        }, success: function (request) {
            if (request != null && request.id != undefined) {
                $.cookie('realname', request.realname, {expires: 7, path: '/'});
                location.href = path + "/index/forwardMainUI.do";
            } else {
                tipDialog("登录失败，用户名或密码错误");
            }
        }
    });
}

function sendEmail() {
    tipDialog("正在发送……");
    $.ajax({
        type: "POST",
        url: path + "/index/sendEmail.index",
        data: {email: $("#email").val()},
        dataType: "text",
        error: function () {
            tipDialog("发送邮件失败，请刷新页面重试。");
        }, success: function (sendEmailMsg) {
            if (sendEmailMsg != "" && sendEmailMsg != undefined) {
                confirmDialog(sendEmailMsg, basePath);
            } else {
                tipDialog("您填写的邮箱地址不存在");
            }
        }
    });
}
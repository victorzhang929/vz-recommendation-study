$(function () {
    navicatActiveProccess('userInfo');
    initUserInfo();
});

function initUserInfo() {
    $.ajax({
        type: "POST",
        url: path + "/user/getUserInfo.do",
        dataType: "json",
        error: function () {
            tipDialog("连接错误，请刷新页面重试。");
        }, success: function (req) {
            $("#username").val(req.username);
            $("#realName").val(req.realname);
            $("#roleName").val(req.rolename);
            $("#userIdCard").val(req.user_idcard);
            $("#userMobile").val(req.user_mobile);
            $("#userEmail").val(req.user_email);
            if (req.gender == 1) {
                $("#female").parent().addClass("checked");
            } else if(req.gender == 0) {
                $("#male").parent().addClass("checked");
            }
            $("#tag").val(req.tag);
        }
    });
}


function saveUserInfo() {
    $("#userInfoForm div").removeClass("error");
    var param = {};
    param.username = $("#username").val();
    param.realname = $("#realName").val();
    param.userMobile = $("#userMobile").val();
    param.userIdCard = $("#userIdCard").val();
    param.userEmail = $("#userEmail").val();

    if($("#male").parent().attr('class') == "checked"){
        param.gender = 0;
    }else if ($("#female").parent().attr('class') == "checked"){
        param.gender = 1;
    }

    param.tag = $("#tag").val();

    if (param.username == "") {
        $("#username").parent().parent().addClass("error");
    }
    if (param.realName == "") {
        $("#realName").parent().parent().addClass("error");
    }
    var validateMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if (param.userMobile != "" && !validateMobile.test(param.userMobile)) {
        $("#userMobile").parent().parent().addClass("error");
    }
    var validateIdCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (param.userIdCard != "" && !validateIdCard.test(param.userIdCard)) {
        $("#userIdCard").parent().parent().addClass("error");
    }
    var validateEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    if (param.userEmail != "" && !validateEmail.test(param.userEmail)) {
        $("#userEmail").parent().parent().addClass("error");
    }

    if ($("#userInfoForm div").attr("class").indexOf("error") != -1) return;
    $.ajax({
        type: "POST",
        url: path + "/user/saveUserInfo.do",
        data: param,
        dataType: "text",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (msg) {
            if (msg != null && msg === '用户信息更新成功') {
                tipDialog(msg);
                location.href = path+"/user/forwardUserInfoUI.do";
            }
        }
    });
}
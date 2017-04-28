$(function () {
    $("#realname").html("<b>" + $.cookie('realname') + "</b>");
    initUnreadMsg();
});

function initUnreadMsg() {
    $.ajax({
        type: "POST",
        url: path + "/message/listUnreadMsg.do",
        dataType: "json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (re) {
            var msg = "";
            var datas = re.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    msg += "<li><a href='javascript:void(0);' title='' onclick=showNews('" + datas[i].id + "','" + datas[i].msgContent + "') ><i class='icon-envelope-alt'></i>" + datas[i].msgContent + "</a></li>";
                }
            }
            msg += "<li><a href= '" + path + "/message/forwardAllMsgUI.do' title='全部消息'><i class='icon-bell'></i> 全部消息 >></a></li>";
            $("#myMsgUnread").html(msg);
            $("#unreadSum").html(re.count);
        }
    });
}

function showNews(id, msg_content) {
    dialog({
        title: '详细消息内容',
        width: 320,
        fixed: true,
        content: "<textarea disabled style='width:100%'>" + msg_content + "</textarea>",
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            $.ajax({
                type: "POST",
                url: path + "/message/doReadMsg.do",
                data: {id: id},
                error: function () {
                    tipDialog("提交失败，连接错误。请刷新页面重试。");
                }, success: function () {
                    tipDialog("已阅读");
                    initUnreadMsg('myMsgUnread', 'unreadSum');
                }
            });
        }
    }).showModal();
}

function exit() {
    dialog({
        title: '提示',
        content: '确定退出系统吗?',
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            $.cookie("realname", null);
            location.href = basePath + "index/redirectExit.do";
        },
        cancelValue: '取消',
        cancel: function () {
        }
    }).showModal();
}

function changePasswordUI() {
    $("#msg").val("");
    $("#oldPassword").val("");
    $("#password").val("");
    $("#rePassword").val("");
}

function changePassword() {
    $("#msg").html("");

    if ($("#oldPassword").val() === "") {
        showDialogMsg('msg', '原密码不能为空');
        return false;
    }
    if ($("#password").val() === "") {
        showDialogMsg('msg', '新密码不能为空');
        return false;
    }
    if ($("#rePassword").val() === "") {
        showDialogMsg('msg', '重复密码不能为空');
        return false;
    }
    if ($("#password").val() !== $("#rePassword").val()) {
        showDialogMsg('msg', '两次密码不一致');
        return false;
    }

    var param = {};
    param.oldPassword = $("#oldPassword").val();
    param.password = $("#password").val();
    param.rePassword = $("#rePassword").val();
    $.ajax({
        type: "POST",
        url: path + "/index/doResetPassword.do",
        data: param,
        dataType: "text",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (result) {
            $("#msg").html("<span class='label label-important'>" + result + "</span>");
            if (result === '修改密码成功') {
                setTimeout(function () {
                    location.href = path + "/index/redirectExit.do";
                }, 2000);
            }
        }
    });
}

function publishNotice() {
    dialog({
        title: '发布公告',
        width: 320,
        fixed: true,
        content: "<textarea id = 'publicNoticeContent'></textarea>",
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            $.ajax({
                type: "POST",
                url: path + "/message/publicNotice.do",
                data: {msgContent: $("#publicNoticeContent").val()},
                error: function () {
                    tipDialog("提交失败，连接错误。请刷新页面重试。");
                }, success: function () {
                    tipDialog("发布成功");
                    initUnreadMsg('myMsgUnread', 'unreadSum');
                }
            });
        },
        cancelValue: '取消',
        cancel: function () {
        }
    }).showModal();
}
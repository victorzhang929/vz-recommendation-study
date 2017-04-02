$(function(){
    initUnreadMsg('myMsgUnread','unreadSum');
});
function initUnreadMsg(msgId,msgNum){
    $.ajax({
        type: "POST",
        url: path+"/message/getUnreadMsg.do",
        dataType: "json",
        error: function() {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        },success: function(re){
            var msg = "";
            var unreadSum;
            var datas = re.data;
            unreadSum = re.unreadNum;
            if (datas.length > 0) {
                for ( var i = 0; i < datas.length; i++) {
                    msg += "<li><a href='javascript:void(0);' title='' onclick=showNews('"+datas[i].messageid+"','"+datas[i].messagecontent+"') ><i class='icon-envelope-alt'></i>"+datas[i].messagecontent+"</a></li>";
                }
            }
            msg += "<li><a href='javascript:void(0);' title='全部消息' onclick=clickNavMenu('this.id','"+path+"/message/forwardAllMsgUI.do') ><i class='icon-bell'></i> 全部消息 >></a></li>";
            $("#"+msgId).html(msg);
            $("#"+msgNum).html(unreadSum);
        }
    });
}

function showNews(messageid,messagecontent){
    dialog({
        title:'详细消息内容',
        width:320,
        fixed: true,
        content: "<textarea disabled style='width:100%'>"+messagecontent+"</textarea>",
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            $.ajax({
                type: "POST",
                url: path+"/message/doReadMsg.do",
                data:{messageid:messageid},
                error: function(request) {
                    tipDialog("提交失败，连接错误。请刷新页面重试。");
                },success: function(res){
                    tipDialog("已阅读");
                    initUnreadMsg('myMsgUnread','unreadSum');
                }
            });
        }
    }).show();
}

jQuery(document).ready(function() {
    $("#realname").html("<b>"+$.cookie('realname')+"</b>");
});

function clickNavMenu(id,pageUrl){
    $("#user-nav li").removeClass("active");
    $("#sidebar ul li").removeClass("active");
    $("#"+id).parent().addClass("active");
    $("#iframepage").attr("src",pageUrl);
}

function exit(){
    confirmCancelDialog("确定退出系统吗？",basePath+"index/exit.do");
}

function changePasswordUI(){
    $("#oldpassword").val("");
    $("#password").val("");
    $("#repassword").val("");
}

function changePassword(){
    $("#msg").html("");

    if($("#oldpassword").val()==""){$("#msg").html("<span class='label label-important'>原密码不能为空</span>");return false;}
    if($("#password").val()==""){$("#msg").html("<span class='label label-important'>新密码不能为空</span>");return false;}
    if($("#repassword").val()==""){$("#msg").html("<span class='label label-important'>重复密码不能为空</span>");return false;}
    if($("#password").val()!=$("#repassword").val()){$("#msg").html("<span class='label label-important'>两次密码不一致</span>");return false;}

    var param = {};
    param.oldpassword = $("#oldpassword").val();
    param.password = $("#password").val();
    param.repassword = $("#repassword").val();
    $.ajax({
        type: "POST",
        url: path+"/user/doChangePassword.do",
        data: param,
        dataType: "json",
        error: function(request) {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        },success: function(re){
            $("#msg").html("<span class='label label-important'>"+re.msg+"</span>");
            if(re.msg=='修改密码成功'){
                setTimeout(function(){location.href=path+"/index/exit.do";},2000);
            }
        }
    });
}

function setFrameHeight(id){
    var iframe = $("#iframepage");
    var h1 = iframe.contents().find("#"+(id||"box")).height();//box高度
    var h2 = document.body.clientHeight-110;//body高度
    if(h1!=null){
        if(h1>h2){
            iframe.height(h1+25);
            $("#content").height(h1);
        }else{
            iframe.height(h2+25);
            $("#content").height(h2);
        }
    }else{
        iframe.height(h2+25);
        $("#content").height(h2);
    }
}
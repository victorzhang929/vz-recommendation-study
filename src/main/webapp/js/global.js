var RESOURCE_TYPE_PICTURE = "图片";
var RESOURCE_TYPE_DOCUMENT = "文档";
var RESOURCE_TYPE_AUDIO = "音频";
var RESOURCE_TYPE_VIDEO = "视频";
var RESOURCE_TYPE_UNKNOWN = "未知";

var RESOURCE_VERIFY_TYPE_REJECTION = "审核未通过";
var RESOURCE_VERIFY_TYPE_SUCCESS = "审核通过";
var RESOURCE_VERIFY_TYPE_DOING_NOW = "正在审核";
var RESOURCE_VERIFY_TYPE_UNKNOWN = "未知";

var activeEl = null;
function enterProccess(id) {
    activeEl = document.activeElement;
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && (e.keyCode == 13) && document.activeElement == activeEl) {
            setTimeout(function () {
                document.getElementById(id).click();
            }, 50);
        }
    };
}

function navicatActiveProccess(id) {
    $("#user-nav li").removeClass("active");
    $("#sidebar ul li").removeClass("active");
    $('#' + id).parent().addClass("active").parent().parent().addClass("open");
}

function tipDialog(message) {
    var d = dialog({
        content: message
    });
    d.showModal();
    setTimeout(function () {
        d.close().remove();
    }, 2000);
}

function promptDialog(title, message) {
    var d = dialog({
        title: title,
        content: message,
    });
    d.show();
}

function confirmDialog(message, url) {
    var d = dialog({
        title: '提示',
        content: message,
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            location.href = url;
        },
    });
    d.show();
}

function confirmCancelDialog(message, url) {
    var d = dialog({
        title: '提示',
        content: message,
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            location.href = url;
        },
        cancelValue: '取消',
        cancel: function () {
        }
    });
    d.showModal();
}

function listLogType(id) {
    $.ajax({
        url: path + "/log/listLogType.do",
        type: "POST",
        dataType: "json",
        success: function (res) {
            var mes = "<option value=''>操作类型</option>";
            if (res != null) {
                for (var i = 0; i < res.length; i++) {
                    mes = mes + "<option value=" + res[i].log_type + ">" + res[i].log_type + "</option>";
                }
            }
            $("#" + id).html(mes);
        }, error: function () {
            tipDialog("读取失败");
        }
    });
}

function showDialogMsg(id, content) {
    $("#" + id).html("<span class='label label-important'>" + content + "</span>");
}

function judgeResourceType(resourceTypeNumber) {
    var resourceType;
    switch (resourceTypeNumber) {
        case 0:
            resourceType = RESOURCE_TYPE_PICTURE;
            break;
        case 1:
            resourceType = RESOURCE_TYPE_DOCUMENT;
            break;
        case 2:
            resourceType = RESOURCE_TYPE_AUDIO;
            break;
        case 3:
            resourceType = RESOURCE_TYPE_VIDEO;
            break;
        default:
            resourceType = RESOURCE_TYPE_UNKNOWN;
            break
    }
    return resourceType;
}

function judgeResourceVerifyType(verifyTypeNumber){
    var verifyType;
    switch (verifyTypeNumber) {
        case 0:
            verifyType = RESOURCE_VERIFY_TYPE_DOING_NOW;
            break;
        case 1:
            verifyType = RESOURCE_VERIFY_TYPE_SUCCESS;
            break;
        case 2:
            verifyType = RESOURCE_VERIFY_TYPE_REJECTION;
            break;
        default:
            verifyType = RESOURCE_VERIFY_TYPE_UNKNOWN;
            break
    }
    return verifyType;
}
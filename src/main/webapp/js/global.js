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
    $('#'+ id).parent().addClass("active").parent().parent().addClass("open");
}

function listLogType(id){
    $.ajax({
        url: path+"/log/listLogType.do",
        type : "POST",
        dataType : "json",
        success : function(res) {
            var mes = "<option>操作类型</option>";
            if (res!= null) {
                for(var i = 0; i < res.length; i++){
                    mes = mes + "<option value="+res[i].log_type+">"+res[i].log_type+"</option>";
                }
            }
            $("#"+id).html(mes);
        },error : function() {
            tipDialog("读取失败");
        }
    });
}
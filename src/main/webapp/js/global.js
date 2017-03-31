function tipDialog(message){
    var d = dialog({
        content: message
    });
    d.showModal();
    setTimeout(function () {
        d.close().remove();
    }, 2000);
}

function promptDialog(title,message){
    var d = dialog({
        title: title,
        content: message,
    });
    d.show();
}

function confirmDialog(message,url){
    var d = dialog({
        title: '提示',
        content: message,
        okValue: '确定',
        ok: function () {
            this.title('提交中…');
            location.href=url;
        },
    });
    d.show();
}

function confirmCancelDialog(message,url){
    var d = dialog({
        title:'提示',
        content:message,
        okValue:'确定',
        ok:function(){
            this.title('提交中…');
            location.href = url;
        },
        cancelValue:'取消',
        cancel:function(){}
    });
    d.showModal();
}

var activeEl = null;
function enterProccess(id) {
    activeEl = document.activeElement;
    document.onkeydown = function(event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && (e.keyCode == 13) && document.activeElement == activeEl) {
            setTimeout(function() {
                document.getElementById(id).click();
            }, 50);
        }
    };
}
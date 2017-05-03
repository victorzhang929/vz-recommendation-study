$(function () {
    navicatActiveProccess('main');
    initNewestResource();
    initHotResource();
});

function initNewestResource(){
    $.ajax({
        type: "POST",
        url: path + "/resource/listNewestResource.do",
        dataType: "json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (re) {
            var msg = "";
            var datas = re.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    msg += "<li><a href='javascript:void(0);' title='' onclick=getResourceDetail('" + datas[i].id + "') >" + datas[i].resource_name + "</a></li>";
                }
            }
            $("#newestResource").html(msg);
        }
    });
}

function initHotResource(){
    $.ajax({
        type: "POST",
        url: path + "/resource/listHotResource.do",
        dataType: "json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (re) {
            var msg = "";
            var datas = re.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    msg += "<li><a href='javascript:void(0);' title='' onclick=getResourceDetail('" + datas[i].id + "') >" + datas[i].resource_name + "</a></li>";
                }
            }
            $("#hotResource").html(msg);
        }
    });
}

function getResourceDetail(resourceId){

}

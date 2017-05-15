$(function () {
    navicatActiveProccess('recommendedResource');
    tableDivPage();
    p_pageSelect();
    load();
});

function load(page) {
    if (isNaN(page)) {
        page = 1;
    }

    $.ajax({
        url: path + "/resource/listRecommendationResource.do",
        type: "POST",
        data: {"_page": page, "_pageSize": $("#p_pageSelect_id").val()},
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:10%'>编号</th>"
                + "<th style='width:15%'>资源名称</th>"
                + "<th style='width:20%'>资源描述</th>"
                + "<th style='width:15%'>标签</th>"
                + "<th style='width:10%'>类型</th>"
                + "<th style='width:15%'>推荐指数</th>"
                + "<th style='width:15%'>操作</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.resource.resourceName + "'>" + data.resource.resourceName + "</td>"
                        + "<td title='" + data.resource.resourceDescription + "'>" + data.resource.resourceDescription + "</td>"
                        + "<td title='" + data.resource.resourceTag + "'>" + data.resource.resourceTag + "</td>"
                        + "<td title='" + judgeResourceType(data.resource.resourceType) + "'>" + judgeResourceType(data.resource.resourceType) + "</td>"
                        + "<td title='" + data.score + "'>" + data.score + "</td>"
                        + "<td>" + handle(data.resource.id) + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='8'>暂无数据!</td></tr>";
            }
            mainTable += "</tbody></table>";
            p_countMsg(res.count);
            p_page(res.page, res.pageSum, res.count);
            $("#tableDiv").html(mainTable);
            t_bs("trs");
        }, beforeSend: function () {
            beforeSend();
        }, error: function () {
            tipDialog("读取失败");
        }
    });
}

function handle(id) {
    return "<a id='get" + id + "' class='bt bt-xs bt-success' onclick=getResourceDetail('" + id + "');>详情</a>"
        + "<a id='download" + id + "' class='bt bt-xs bt-info' onclick=downloadResource('" + id + "');>下载</a>";
}

function userBasedRecommendation() {
    updateUserItemFlag("0", "基于用户推荐");
}

function itemBasedRecommendation() {
    updateUserItemFlag("1", "基于项目推荐");
}

function updateUserItemFlag(flag, msg) {
    $.ajax({
        type: "POST",
        url: path + "/flag/updateFlag.do",
        data: {"flag": flag},
        dataType: "json",
        error: function () {
            tipDialog("登录失败，连接错误。请刷新页面重试。");
        }, success: function () {
            tipDialog(msg);
        }
    });
}
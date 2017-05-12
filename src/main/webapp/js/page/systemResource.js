$(function () {
    navicatActiveProccess('systemResource');
    tableDivPage();
    p_pageSelect();
    load();
});

function load(pge) {
    if (isNaN(pge)) {
        pge = 1;
    }

    var param = {};
    param._page = pge;
    param._pageSize = $("#p_pageSelect_id").val();
    param.resourceName = $("#queryName").val();
    param.resourceType = $("#queryType option:selected").val();
    param.verifyType = $("#queryVerifyType option:selected").val();
    param.startDate = $("#queryStartDate").val();
    param.endDate = $("#queryEndDate").val();

    $.ajax({
        url: path + "/resource/listSystemResourcePaging.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:10%'>编号</th>"
                + "<th style='width:20%'>资源名称</th>"
                + "<th style='width:10%'>类型</th>"
                + "<th style='width:20%'>上传时间</th>"
                + "<th style='width:20%'>上传人</th>"
                + "<th style='width:20%'>操作</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.resource_name + "'>" + data.resource_name + "</td>"
                        + "<td title='" + judgeResourceType(data.resource_type) + "'>" + judgeResourceType(data.resource_type) + "</td>"
                        + "<td title='" + data.gmt_create + "'>" + data.gmt_create + "</td>"
                        + "<td title='" + data.username + "'>" + data.username + "</td>"
                        + "<td>" + handle(data.id) + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='6'>暂无数据!</td></tr>";
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

function downloadResource(id) {
    location.href = path + "/resource/doDownloadResource.do?id=" + id;
}
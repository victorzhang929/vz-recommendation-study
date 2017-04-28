$(function () {
    navicatActiveProccess('resourceBrowseRecord');
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
    param.startDate = $("#queryStartDate").val();
    param.endDate = $("#queryEndDate").val();

    $.ajax({
        url: path + "/browseRecord/listPaging.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:10%'>编号</th>"
                + "<th style='width:20%'>资源名称</th>"
                + "<th style='width:30%'>描述</th>"
                + "<th style='width:20%'>标签</th>"
                + "<th style='width:20%'>浏览时间</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.resource_name + "'>" + data.resource_name + "</td>"
                        + "<td title='" + data.resource_description + "'>" + data.resource_description + "</td>"
                        + "<td title='" + data.resource_tag + "'>" + data.resource_tag + "</td>"
                        + "<td title='" + data.browse_time + "'>" + data.browse_time + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='5'>暂无数据!</td></tr>";
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
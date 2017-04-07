$(function () {
    navicatActiveProccess('systemLog');
    tableDivPage();
    listLogType('queryType');
    load();
});

function load(pge) {
    if (isNaN(pge)) {
        pge = 1;
    }

    var param = {};
    param._page = pge;
    param._pageSize = $("#p_pageSelect_id").val();
    param.logType = $("#queryType").val();
    param.startDate = $("#queryStartDate").val();
    param.endDate = $("#queryEndDate").val();

    $.ajax({
        url: path + "/log/listPaging.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:5%'>编号</th>"
                + "<th style='width:8%'>操作类型</th>"
                + "<th style='width:8%'>用户名</th>"
                + "<th style='width:8%'>真实姓名</th>"
                + "<th style='width:51%'>操作说明</th>"
                + "<th style='width:10%'>操作日期</th>"
                + "<th style='width:10%'>IP地址</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.log_type + "'>" + data.log_type + "</td>"
                        + "<td title='" + data.username + "'>" + data.username + "</td>"
                        + "<td title='" + data.realname + "'>" + data.realname + "</td>"
                        + "<td title='" + data.log_content + "'>" + data.log_content + "</td>"
                        + "<td title='" + data.user_date + "'>" + data.user_date + "</td>"
                        + "<td title='" + data.user_ip + "'>" + data.user_ip + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='7'>暂无数据!</td></tr>";
            }
            mainTable += "</tbody></table>";
            p_countMsg(res.count);
            p_page(res.page, res.pageSum, res.count);
            $("#tableDiv").html(mainTable);
            p_pageSelect();
            t_bs("trs");
        }, beforeSend: function () {
            beforeSend();
        }, error: function () {
            tipDialog("读取失败");
        }
    });
}
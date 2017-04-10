$(function () {
    navicatActiveProccess('messageManagement');
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
    param.username = $("#username").val();
    param.rolename = $("#queryType").val();
    param.startDate = $("#queryStartDate").val();
    param.endDate = $("#queryEndDate").val();

    $.ajax({
        url: path + "/user/listPaging.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:10%'>编号</th>"
                + "<th style='width:20%'>用户名</th>"
                + "<th style='width:20%'>真实姓名</th>"
                + "<th style='width:50%'>消息内容</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.username + "'>" + data.username + "</td>"
                        + "<td title='" + data.realname + "'>" + data.realname + "</td>"
                        + "<td title='" + data.rolename + "'>" + data.rolename + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='4'>暂无数据!</td></tr>";
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
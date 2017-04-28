$(function () {
    navicatActiveProccess('verifyResource');
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
        url: path + "/resource/listVerifyResource.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:5%'>编号</th>"
                + "<th style='width:15%'>资源名称</th>"
                + "<th style='width:25%'>资源描述</th>"
                + "<th style='width:20%'>资源标签</th>"
                + "<th style='width:10%'>类型</th>"
                + "<th style='width:15%'>上传者</th>"
                + "<th style='width:10%'>操作</th>"
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
                        + "<td title='" + judgeResourceVerifyType(data.verify_type) + "'>" + judgeResourceVerifyType(data.verify_type) + "</td>"
                        + "<td title='" + data.username + "'>" + data.username + "</td>"
                        + "<td>" + handle(data.id) + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='7'>暂无数据!</td></tr>";
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
    return "<a id='verification" + id + "' class='bt bt-xs bt-success' onclick=verify('" + id + "','1');>通过</a>"
        + "<a id='rejection" + id + "' class='bt bt-xs bt-danger' onclick=verify('" + id + "','2');>拒绝</a>";
}

function verify(resourceId, verifyType) {
    var d = dialog({
        title: '提示',
        content: '确定执行该操作？',
        okValue: '确定',
        ok: function () {
            var param = {};
            param.resourceId = resourceId;
            param.verifyType = verifyType;
            $.ajax({
                url: path + "/resource/doVerifyResource.do",
                type: "POST",
                data: param,
                dataType: "text",
                success: function (msg) {
                    tipDialog(msg);
                    load();
                }, error: function () {
                    tipDialog("操作失败");
                }
            });
        },
        cancelValue: '取消',
        cancel: function () {
        }
    });
    d.showModal();
}
$(function () {
    navicatActiveProccess('userResource');
    tableDivPage();
    p_pageSelect();
    //load();
});

function load(pge) {
    if (isNaN(pge)) {
        pge = 1;
    }

    var param = {};
    param._page = pge;
    param._pageSize = $("#p_pageSelect_id").val();

    param.verifyresource = $("#querytype option:selected").val();
    param.resourcename = $("#resourcename").val();
    param.stadate = $("#querystadate").val();
    param.enddate = $("#queryenddate").val();

    $.ajax({
        url: path + "/resource/queryUserResource.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:5%'>编号</th>"
                + "<th style='width:15%'>名称</th>"
                + "<th style='width:18%'>资源描述</th>"
                + "<th style='width:15%'>特属标签</th>"
                + "<th style='width:5%'>类型</th>"
                + "<th style='width:15%'>上传时间</th>"
                + "<th style='width:7%'>下载量</th>"
                + "<th style='width:7%'>阅读量</th>"
                + "<th style='width:13%'>操作</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.resourcename + "'>" + data.resourcename + "</td>"
                        + "<td title='" + data.resourcedescription + "'>" + data.resourcedescription + "</td>"
                        + "<td title='" + data.resourcetag + "'>" + data.resourcetag + "</td>"
                        + "<td title='" + data.resourcetype + "'>" + data.resourcetype + "</td>"
                        + "<td title='" + data.uploadtime + "'>" + data.uploadtime + "</td>"
                        + "<td title='" + data.resourceuploadcount + "'>" + data.resourceuploadcount + "</td>"
                        + "<td title='" + data.resourcebrowsecount + "'>" + data.resourcebrowsecount + "</td>"
                        + "<td>" + handle(data.resourceid) + "</td>"
                        + "</tr>";
                }
            } else {
                mainTable += "<tr><td colspan='9'>暂无数据!</td></tr>";
            }

            mainTable += "</tbody></table>";

            p_countMsg(res.count);
            p_page(res.page, res.pageSum, res.count);

            $("#tableDiv").html(mainTable);
            t_bs("trs");

            parent.setFrameHeight("box");
        }, beforeSend: function () {
            beforeSend();
        }, error: function () {
            tipDialog("读取失败");
        }
    });
}

function handle(resourceid) {
    return "<a id='classes" + resourceid + "' class='bt bt-xs bt-info' onclick=downloadReource('" + resourceid + "');>下载</a>" +
        "<a id='delete" + resourceid + "' class='bt bt-xs bt-success' onclick=getDetailResource('" + resourceid + "');>详情</a>" +
        "<a id='delete" + resourceid + "' class='bt bt-xs bt-danger' onclick=deleteResource('" + resourceid + "');>删除</a>";
}
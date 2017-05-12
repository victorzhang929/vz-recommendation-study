$(function () {
    navicatActiveProccess('userResource');
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
        url: path + "/resource/listPaging.do",
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
                + "<th style='width:20%'>审核状态</th>"
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
                        + "<td title='" + judgeResourceVerifyType(data.verify_type) + "'>" + judgeResourceVerifyType(data.verify_type) + "</td>"
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
    return "<a id='download" + id + "' class='bt bt-xs bt-info' onclick=downloadResource('" + id + "');>下载</a>"
        + "<a id='delete" + id + "' class='bt bt-xs bt-danger' onclick=deleteResource('" + id + "');>删除</a>";
}


function downloadResource(id) {
    location.href = path + "/resource/doDownloadResource.do?id=" + id;
}

function deleteResource(id) {
    var d = dialog({
        title: '提示',
        content: '确定删除该文件？',
        okValue: '确定',
        ok: function () {
            $.ajax({
                url: path + "/resource/removeResource.do",
                type: "POST",
                data: {"id": id},
                dataType: "json",
                success: function () {
                    tipDialog("删除成功");
                    load();
                }, error: function () {
                    tipDialog("删除失败");
                }
            });
        },
        cancelValue: '取消',
        cancel: function () {
        }
    });
    d.showModal();
}

function uploadResourceUI() {
    $("#uploadMsg").html("");
    $("#resourceFile").val("");
    $("#resourceName").val("");
    $("#resourceDescription").val("");
    $("#resourceTag").val("");
    $("#resourceType").val("");
}

function uploadResource() {
    showDialogMsg("uploadMsg", "正在上传中...");
    $('#uploadResourceForm').ajaxForm({
        dataType: "text",
        beforeSubmit: function () {
            $("#btnUploadResource").attr("disabled", "disabled");
            if ($("#resourceFile").val() === null || $("#resourceFile").val() === "") {
                showDialogMsg('uploadMsg', '请选择文件');
                return false;
            }
            if ($("#resourceName").val() === "") {
                showDialogMsg('uploadMsg', '资源名称不能为空');
                return false;
            }
            if ($("#resourceTag").val() === "") {
                showDialogMsg('uploadMsg', '资源标签不能为空');
                return false;
            }
            if ($("#resourceType option:selected").val() === "") {
                showDialogMsg('uploadMsg', '请选择资源类型');
                return false;
            }
        }, success: function () {
            showDialogMsg('uploadMsg', "上传成功");
            load();
            uploadResourceUI();
            $("#btnUploadResource").attr("disabled", false);
        }, error: function () {
            showDialogMsg('uploadMsg', '请选择正确的资源文件，重新操作');
            $("#btnUploadResource").attr("disabled", false);
        }
    }).submit();
}
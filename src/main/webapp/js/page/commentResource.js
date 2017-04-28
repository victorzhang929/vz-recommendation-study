$(function () {
    navicatActiveProccess('commentResource');
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
        url: path + "/comment/listPaging.do",
        type: "POST",
        data: param,
        dataType: "json",
        success: function (res) {
            var mainTable = "<table class='table table-bordered table-striped' >"
                + "<thead><tr>"
                + "<th style='width:10%'>编号</th>"
                + "<th style='width:20%'>资源名称</th>"
                + "<th style='width:35%'>评论内容</th>"
                + "<th style='width:20%'>评论时间</th>"
                + "<th style='width:15%'>操作</th>"
                + "</tr></thead>" + "<tbody id='trs'>";

            var datas = res.data;
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    mainTable += "<tr>"
                        + "<td>" + index(res.page, res.pageSize, i) + "</td>"
                        + "<td title='" + data.resource_name + "'>" + data.resource_name + "</td>"
                        + "<td title='" + data.comment_content + "'>" + data.comment_content + "</td>"
                        + "<td title='" + data.comment_time + "'>" + data.comment_time + "</td>"
                        + "<td>" + handle(data.id, data.comment_content) + "</td>"
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

function handle(id, commentContent) {
    return "<a id='editComment" + id + "' class='bt bt-xs bt-info' onclick=editComment('" + id + "','" + commentContent + "');>修改</a>"
        + "<a id='deleteComment" + id + "' class='bt bt-xs bt-danger' onclick=deleteComment('" + id + "');>删除</a>";
}

function editComment(commentId, commentContent) {
    var d = dialog({
        title: '评论内容',
        content: '<input autofocus id="inputCommentContent" value="'+commentContent+'"/>',
        okValue: '确定',
        ok: function () {
            var param = {};
            param.commentId = commentId;
            param.commentContent = $("#inputCommentContent").val();
            $.ajax({
                url: path + "/comment/updateComment.do",
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

function deleteComment(commentId) {
    var d = dialog({
        title: '提示',
        content: '确定执行该操作？',
        okValue: '确定',
        ok: function () {
            $.ajax({
                url: path + "/comment/deleteComment.do",
                type: "POST",
                data: {"commentId": commentId},
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
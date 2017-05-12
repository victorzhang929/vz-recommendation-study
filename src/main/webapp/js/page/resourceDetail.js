$(function () {
    var resourceId = jQuery.url.param('id');
    enterProccess("submitCommentContent");
    initResourceDetail(resourceId);
    initResourceBrowseCount(resourceId);
    initRateOperation(resourceId);
});

function initResourceBrowseCount(resourceId) {
    $.ajax({
        url: basePath + "/resource/updateResourceBrowseCount.do",
        type: "POST",
        data: {"resourceId": resourceId},
        dataType:"json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }
    })
}

//打分操作
function initRateOperation(resourceId) {
    //先判断该用户有没有打分，如果打分了则直接显示分数，如果没有可以进行打分
    $.ajax({
        url: basePath + "/scoreRecord/getRateScore.do",
        type: "POST",
        data: {"resourceId": resourceId},
        dataType: "json",
        success: function (result) {
            $.fn.raty.defaults.path = basePath + '/plugins/jquery-raty/images';
            if (result === 'undefined' || result === null) {
                $('#rate').raty({
                    click: function (score) {
                        $.ajax({
                            type: "POST",
                            url: basePath + "/scoreRecord/saveRate.do",
                            data: {"score": score, "resourceId": resourceId},
                            dataType: "json",
                            error: function () {
                                tipDialog("提交失败，连接错误。请刷新页面重试。");
                            }, success: function (result) {
                                initRateOperation();
                            }
                        });
                    }
                })
            } else {
                $('#rate').raty({readOnly: true, score: parseInt(result.rating)});
            }
        }, error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }
    });
}

function initResourceDetail(resourceId) {
    $.ajax({
        type: "POST",
        url: basePath + "/resource/getResourceDetail.do",
        data: {"id": resourceId},
        dataType: "json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function (result) {
            if (result != '' && result != undefined) {
                $("#resourceName").html(result.resource.resourceName);
                $("#resourceBrowseCount").html(result.resource.resourceBrowseCount);
                $("#resourceDownloadCount").html(result.resource.resourceDownloadCount);
                $("#resourceAverageScore").html(result.averageScore);
                var comments = result.comments;
                var commentTable = "<div class='scroller'><ul class='chats'>";
                if (comments.length > 0) {
                    for (var i = 0; i < comments.length; i++) {
                        var comment = comments[i];
                        commentTable += "<li class='in'><div class='message'>" +
                            "<span class='arrow'></span>" +
                            "<a href='#' class='name'><span>" + comment.realname + "</span></a> " +
                            "<span class='datetime'>" + comment.comment_time + "</span> " +
                            "<span class='body'>" + comment.comment_content + "</span> " +
                            "</div> </li>";
                    }
                } else {
                    commentTable += "<li class='no-records'>还没有任何评论！</li>";
                }
                commentTable += "</ul></div>";
                $("#commentTable").html(commentTable);
            }
        }
    });
}


//提交评论信息
function submitCommentContent() {
    var resourceId = jQuery.url.param('id');
    var commentContent = $("#commentContent").val();
    $.ajax({
        type: "POST",
        url: basePath + "/comment/saveComment.do",
        data: {"resourceId": resourceId, "commentContent": commentContent},
        dataType: "json",
        error: function () {
            tipDialog("提交失败，连接错误。请刷新页面重试。");
        }, success: function () {
            tipDialog("发布评论成功");
            $("#commentContent").val("");
            initResourceDetail(resourceId);
        }
    });
}
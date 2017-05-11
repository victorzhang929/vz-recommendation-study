$(function () {
    initResourceDetail();
});

function initResourceDetail() {
    $.ajax({
        type: "POST",
        url: path + "/resource/getResourceDetail.do",
        data: {"id": jQuery.url.param('id')},
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
                            "<a href='#' class='name'><span>"+comment.realname+"</span></a> " +
                            "<span class='datetime'>"+comment.comment_time+"</span> " +
                            "<span class='body'>"+comment.comment_content+"</span> " +
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
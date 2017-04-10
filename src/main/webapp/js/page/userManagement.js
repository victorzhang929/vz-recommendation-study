$(function () {
    navicatActiveProccess('userManagement');
    tableDivPage();
    listUserType('queryType');
    p_pageSelect();
    load();
});

function listUserType(id){
    $.ajax({
        url: path+"/manage/listUserType.do",
        type : "POST",
        dataType : "json",
        success : function(res) {
            var mes = "<option>用户类型</option>";
            if (res!= null) {
                for(var i = 0; i < res.length; i++){
                    mes = mes + "<option value="+res[i].rolename+">"+res[i].rolename+"</option>";
                }
            }
            $("#"+id).html(mes);
        },error : function() {
            tipDialog("读取失败");
        }
    });
}

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
                + "<th style='width:5%'>编号</th>"
                + "<th style='width:15%'>用户名</th>"
                + "<th style='width:20%'>真实姓名</th>"
                + "<th style='width:15%'>角色</th>"
                + "<th style='width:25%'>手机号码</th>"
                + "<th style='width:20%'>操作</th>"
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
                        + "<td title='" + data.user_mobile + "'>" + data.user_mobile + "</td>"
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
    return "<a id='detail" + id + "' class='bt bt-xs bt-success' onclick= get('" + id + "');>详情</a>" +
        "<a id='delete" + id + "' class='bt bt-xs bt-danger' onclick= remove('" + id + "');>删除</a>";
}
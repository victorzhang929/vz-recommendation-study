/* 创建分页div，必须 */
function tableDivPage() {
	$("#tableDivPage").html("<div class='dataTables_length' id='pageLength' style='float:left;margin-top:0px;width:60px;'></div>" 
			+ "<div id='pageSum' style='display:inline;float:left;padding:5px;'></div>" 
			+ "<div class='fg-toolbar ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix'>" 
			+ "<div class='dataTables_paginate fg-buttonset ui-buttonset fg-buttonset-multi ui-buttonset-multi paging_full_numbers' id='pageNum'></div>"
			+ "</div>");
}

/* 当前页显示条数选择，可选 */
function p_pageSelect(defRows, pages) {
	if(isNaN(defRows)){defRows = 10;}
	var res = "<select class='select2-container' id='p_pageSelect_id' onchange='load()'>";
	if (typeof (pages) == "undefined" || pages.length == 0) {
		pages = [ 5, 10, 15, 20, 25, 30 ];
	}
	for ( var a = 0; a < pages.length; a++) {
		res += "<option value='" + pages[a] + "'";
		if(defRows == pages[a]){ res += " selected=selected";}
		res += ">" + pages[a] + "</option>";
	}
	res += "</select>";
	$("#pageLength").html(res);
}

/* 总记录数显示 */
function p_countMsg(count) {
	$("#pageSum").html("共  " + count + " 条记录");
}

/* 页码翻页 */
function p_page(page, pageSum) {
	
	var res = "";
	if (page <= 1) {
		res+="<a tabindex='0' class='first ui-corner-tl ui-corner-bl fg-button ui-button ui-state-default ui-state-disabled'>首页</a>";
		res+="<a tabindex='0' class='previous fg-button ui-button ui-state-default ui-state-disabled'>上一页</a>";
	} else {
		res+="<a tabindex='0' class='first ui-corner-tl ui-corner-bl fg-button ui-button ui-state-default' onclick='load(1)'>首页</a>";
		res+="<a tabindex='0' class='previous fg-button ui-button ui-state-default' onclick='load("+(page - 1)+")'>上一页</a>";
	}

	var chang = 5;
	var begin = 0;
	var end = 0;

	if (pageSum <= chang) {
		begin = 1;
		end = pageSum;
	} else {
		if (page <= (chang+1)/2 ) {
			begin = 1;
			end = chang;
		} else if (page > (pageSum - (chang-1)/2)) {
			begin = pageSum - (chang-1);
			end = pageSum;
		} else {
			begin = page - (chang-1)/2;
			end = page + (chang-1)/2;
		}
	}
	res += "<span>";
	for ( var v = begin; v <= end; v++) {
		if (v == page) {
			res +="<a tabindex='0' class='fg-button ui-button ui-state-default ui-state-disabled'>"+v+"</a>";
		} else {
			res +="<a tabindex='0' class='fg-button ui-button ui-state-default' onclick='load(" + v + ")'>"+v+"</a>";
		}
	}
	res += "</span>";

	if (page >= pageSum) {
		res+="<a tabindex='0' class='next fg-button ui-button ui-state-default ui-state-disabled'>下一页</a>";
		res+="<a tabindex='0' class='last ui-corner-tr ui-corner-br fg-button ui-button ui-state-default ui-state-disabled'>末页</a>";
	} else {
		res+="<a tabindex='0' class='next fg-button ui-button ui-state-default' onclick='load("+(page+1)+")'>下一页</a>";
		res+="<a tabindex='0' class='last ui-corner-tr ui-corner-br fg-button ui-button ui-state-default' onclick='load("+(pageSum)+")'>末页</a>";
	}
	
	res +="</div>";
	$("#pageNum").html(res);
}

/* 全选按钮 */
function ckall(check) {
	var s = $("input[name='ck']");
	for ( var v = 0; v < s.length; v++) {
		ck(check, s[v].value);
		s[v].checked = check;
	}
}
/* 复选框 */
function ck(check, id, auto) {
	if (!auto) {
		tr_cbox(id);
	}
	if (check) {
		$("#tr" + id).css("background-color", "#dfe8f6");
	} else {
		$("#tr" + id).css("background-color", "");
	}
}
/* 复选框和行关联 */
function tr_cbox(id) {
	if(document.getElementById("ck" + id)!=null){
		if (document.getElementById("ck" + id).checked) {
			$("input[id='ck" + id + "']").attr("checked", false);
		} else {
			$("input[id='ck" + id + "']").attr("checked", true);
		}
		ck(document.getElementById("ck" + id).checked, id, true);
	}
}
/* 隔行变色 */
function t_bs(tbodyId) {
	var $trs = $("#"+tbodyId+">tr"); // 选择所有行
	$trs.filter(":odd").addClass("odd"); // 给奇数行添加odd样式
	$trs.filter(":even").addClass("even");// 给偶数行添加odd样式

	// 划过变色
	$trs.hover(function() {
		$(this).addClass("hover"); // 鼠标经过添加hover样式
	}, function() {
		$(this).removeClass("hover"); // 鼠标离开移除hover样式
	});

}
/* 表格ajax调用前显示读取中 */
function beforeSend(){
	var loadHei = 30;
	if($("#tableDiv").height() + $("#tableDivPage").height() >= loadHei){loadHei = $("#tableDiv").height() + $("#tableDivPage").height();}
	$("#tableDiv").html("<div style='height:40px;width:100%;text-align:center;padding-top:"+(loadHei/2-20)+"px;padding-bottom:"+(loadHei/2-20)+"px;'><img src='../images/tload.gif'>&nbsp;数据读取中……</div>");// 读取中的信息
}

/* 表格序号 */
function index(page,pageSize,i){
	var a = parseInt(page-1)*parseInt(pageSize) + 1;
	var b = parseInt(i);
	return (a+b);
}
$(function() {

	formValuerforstart();

});
function formValuerforstart(){
	for(var v =0 ; v < formValidate.length ; v++){
		var a = formValidate[v];
		if(typeof (a.id) != "undefined"){
			var typeLx = formValidate[v].type;
			if(typeLx != "radio" && typeLx != "checkbox"){
				textEleBind(v);
			}
		}
	}
}

function formValidateSub(){

	var i = 0;
	for(var v =0 ; v < formValidate.length ; v++){
		var a = formValidate[v];
		if(typeof (a.id) != "undefined"){
			if(!eleValue(a.id , v)){
				i++;
			}
		}
	}

	if (i > 0) {
		return false;
	}else{
		return true;
	}

}

function textEleBind(v){
	var id = formValidate[v].id;
	$("#"+id).keyup(function() {
		eleValue(id , v);
	});
	$("#"+id).blur(function() {
		eleValue(id , v);
	});
	$("#"+id).change(function() {
		eleValue(id , v);
	});
}

function eleValue(id , v) {

	var ele = $("#"+id);
	$("#" + id + "_msgFont").remove();

	var re = true;
	var defErrorMsg = "必填";	
//	alert(id);
//	alert(ele.val());
	var typeLx = formValidate[v].type;

	// 文本框、文本域
	if(typeLx=="radio"){
		if(typeof ($("input:radio[name="+id+"]:checked").val()) == "undefined"){
			defErrorMsg = "必选";
			re = false;
		}
	}else if(typeLx=="checkbox"){
		if($("input[name='"+id+"']:checked").length==0){
			defErrorMsg = "必选";
			re = false;
		}
	}else{
		if(ele.val()!=null){// 在此判断，是为了防止radio和checkbox在此报错
			// 验证类型处理
			var lx = formValidate[v].lx;
			var maxlength = formValidate[v].maxlength;
			var minlength = formValidate[v].minlength;
			if(lx == "int"){
				// 整数（非空）
				if(ele.val().replace(/(^\s*)|(\s*$)/g, "") == ""){
					defErrorMsg = "必填";
					re = false;
				}
				else if ( !new RegExp("^[0-9]*$").test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
					defErrorMsg = "必须为整数";
					re = false;
				}
			}else if(lx == "int2"){
				// 整数（可空）
				if (!new RegExp("^[0-9]*$").test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
					defErrorMsg = "整数";
					re = false;
				}
			}else if(lx == "sfz"){
				// 身份证验证
				/*if(ele.val().replace(/(^\s*)|(\s*$)/g, "") == "" ){
					defErrorMsg = "必填";
					re = false;
				}*/
				if(!ele.val().replace(/(^\s*)|(\s*$)/g, "") == "" ){
					if (!new RegExp("^[0-9]*$").test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))||!new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
						defErrorMsg = "身份证格式不正确";
						re = false;
					}
				}
			}else if(lx == "mobile"){
				// 身份证验证
				if (ele.val().replace(/(^\s*)|(\s*$)/g, "") == "" || !new RegExp("^[0-9]*$").test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))||!new RegExp(/^1\d{10}$/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
					defErrorMsg = "电话号码格式不正确";
					re = false;
				}
			}else if(lx == "email"){
				// 身份证验证
				/*if (ele.val().replace(/(^\s*)|(\s*$)/g, "") == "" ||!new RegExp(/^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {*/
				if (!ele.val().replace(/(^\s*)|(\s*$)/g, "") == ""){
					if (!new RegExp(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
						defErrorMsg = "邮箱格式不正确";
						re = false;
					}
				}
			}else if(lx == "date"){
				// 日期验证
				if(ele.val().replace(/(^\s*)|(\s*$)/g, "") == ""){
					defErrorMsg = "必填";
					re = false;
				}
				else if (!new RegExp(/[0-9]{4}-[0-9]{2}-[0-9]{2}/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
					defErrorMsg = "日期格式不正确";
					re = false;
				}
			}else if(lx == "memony"){
				// 身份证验证
				if(ele.val().replace(/(^\s*)|(\s*$)/g, "") == ""){
					defErrorMsg = "必填";
					re = false;
				}
				else if (!new RegExp(/^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/).test(ele.val().replace(/(^\s*)|(\s*$)/g, ""))) {
					defErrorMsg = "金钱格式不正确";
					re = false;
				}
			}else if(lx=="isnull"){//非必填，验证长度
				//判断最大长度
				if(maxlength!=null && maxlength!="" && maxlength!=undefined){
					var reg = /[^\u4e00-\u9fa5]+/g;
					var temp = ele.val();
					var l = temp.length + temp.replace(reg,'').length;
					if(l>maxlength){
						defErrorMsg ="超过最大长度";
						re = false;
					}
				}//判断最小长度
				else if(minlength!=null && minlength!="" && minlength!=undefined){
					var reg = /[^\u4e00-\u9fa5]+/g;
					var temp = ele.val();
					var l = temp.length + temp.replace(reg,'').length;
					if(l<minlength){
						defErrorMsg ="最小长度为"+minlength+"位（一个汉字占两位，字符占一位）;";
						re = false;
					}
				}
			}else{//必填，验证长度
				if (ele.val().replace(/(^\s*)|(\s*$)/g, "") == "") {
					defErrorMsg = "必填";
					re = false;
				}//判断最大长度
				else if(maxlength!=null && maxlength!="" && maxlength!=undefined){
					var reg = /[^\u4e00-\u9fa5]+/g;
					var temp = ele.val();
					var l = temp.length + temp.replace(reg,'').length;
					if(l>maxlength){
						defErrorMsg ="超过最大长度";
						re = false;
					}
				}//判断最小长度
				else if(minlength!=null && minlength!="" && minlength!=undefined){
					var reg = /[^\u4e00-\u9fa5]+/g;
					var temp = ele.val();
					var l = temp.length + temp.replace(reg,'').length;
					if(l<minlength){
						defErrorMsg ="最小长度为"+minlength+"位（一个汉字占两位，字符占一位）;";
						re = false;
					}
				}
			}
			
		} 
	}
	if (!re) {
		ele.removeClass("nnS").addClass("nnE");
		// 提示信息的处理
		var showMsg = "";
		if(typeof (formValidate[v].errMsg) != "undefined"){
			showMsg = formValidate[v].errMsg;
		}else{
			showMsg = "<font id = '" + id + "_msgFont' style='margin-left: 10px;font-size:12px;color:red;'>"+defErrorMsg+"</font>";
		}

		// 显示在哪的处理
		if(typeof (formValidate[v].msgDiv) != "undefined"){
			$("#" + formValidate[v].msgDiv).html(showMsg);
		}else{
			ele.after(showMsg);
		}

		return false;

	} else {
		ele.removeClass("nnE").addClass("nnS");
	}

	return true;
}

function removedivClass(id , v){

	var ele = $("#"+id);
	$("#" + id + "_msgFont").remove();
	ele.removeClass("nnE").addClass("nnS");

}

$(document).ready(function(){
	
	$('.data-table').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""l>t<"F"fp>'
	});
	
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	$('input[type=checkbox],input[type=radio]').uniform();
	
//	$('select').select2();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});	
});
function alertnew(divid,data,index){
	tipDialog(data);
//	alertclose();
//	var p = "<div id='gritter-notice-wrapper' onMouseOut='alertout(this)' onMouseOver='alertover(this)'>"
//		  +"<div id='gritter-item-2' class='gritter-item-wrapper'>"
//		  +"<div class='gritter-top'></div>"
//		  +"<div class='gritter-item'>" 
//		  +"<div class='gritter-close' id='gritter-close' style='display: none;' onclick='alertclose()'></div>"
//		  +"<div class='gritter-without-image'><span class='gritter-title'>"+data+"</span><p></p></div>"
//		  +"<div style='clear:both'></div>	</div><div class='gritter-bottom'></div></div></div>";
//	$(divid).prepend($(p));
//	if(index==1){
//		setTimeout("alertclose()",2000);
//	}

}
function alertout(src){
	 var r_TopMenu=document.getElementById("gritter-close");
	    r_TopMenu.style.display= "none";
//	$(".gritter-close").css({"display":" none"});
}
function alertover(src){
	var r_TopMenu=document.getElementById("gritter-close");
    r_TopMenu.style.display= "block";
//	$(".gritter-close").css({"display":" block"});
}
function alertclose(){
	$("#gritter-notice-wrapper").remove();
}

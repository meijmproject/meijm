<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title></title>
<%@ include file="/include/js_css_base_include.jsp"%>

<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body style="overflow:auto;">
<form id="gbPlanForm" onsubmit="return false;">
	<table class="institution_basic">
	    <tr class="border_top institution_basic_td_bg border_bottom">
	        <td colspan="2" class=" border_right">单位岗位总量</td>
	        <td colspan="3" class=" border_right">类别</td>
	        <td colspan="4" class=" border_right">岗位管理</td>
	        <td colspan="4" class=" border_right">专业技术岗</td>
	        <td colspan="4" class=" border_right">工勤技能岗位</td>
	    </tr>
	    <tr class="institution_basic_td_color">
	      <td colspan="2" class=" border_right" id="all"></td>
	        <td colspan="3" class="institution_basic_td_bg border_right">数量</td>
			        <td colspan="4" class=" border_right" id="YHRS0022_1"></td>
			        <td colspan="4" class=" border_right" id="YHRS0022_2"></td>
			        <td colspan="4" class=" border_right" id="YHRS0022_3"></td>
	    </tr>
	    <tr class="border_top institution_basic_td_bg border_bottom">
	        <td rowspan="2" colspan="1" class="border_right">管理岗位</td>
	        <td colspan="1" class="border_right">等级</td>
	        <td colspan="2" class="border_right">三</td>
	        <td colspan="2" class="border_right">四</td>
	        <td colspan="2" class="border_right">五</td>
	        <td colspan="2" class="border_right">六</td>
	        <td colspan="2" class="border_right">七</td>
	        <td colspan="2" class="border_right">八</td>
	        <td colspan="1" class="border_right">九</td>
	        <td colspan="1" class="border_right">十</td>
	        <td colspan="1" class="border_right">未定</td>
	    </tr>
	    <tr class="institution_basic_td_color">
	        <td colspan="1" class="border_right institution_basic_td_bg border_bottom">数量</td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010040"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010050"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010060"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010070"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010080"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010090"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010100"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010110"></td>
	        <td colspan="1" class="border_right"><input maxlength="10" class="num" type="text" name="YHRS0022_1_YHRS0023_A1010190"></td>
	    </tr>
	    <tr class="border_top institution_basic_td_bg border_bottom">
	        <td rowspan="4" colspan="1" class="border_right">专业技术岗位</td>
	        <td colspan="1" class="border_right">层级</td>
	        <td colspan="4" class="border_right">正高级</td>
	        <td colspan="3" class="border_right">副高级</td>
	        <td colspan="3" class="border_right">中级</td>
	        <td colspan="5" class="border_right">初级</td>
	    </tr>
	    <tr class="institution_basic_td_color">
	        <td colspan="1" class="institution_basic_td_bg border_right border_bottom">数量</td>
			        <td colspan="4" class="border_right" id="YHRS0022_2_YHRS0023_A10200100100"></td>
			        <td colspan="3" class="border_right" id="YHRS0022_2_YHRS0023_A10200100200"></td>
			        <td colspan="3" class="border_right" id="YHRS0022_2_YHRS0023_A10200100300"></td>
			        <td colspan="5" class="border_right" id="YHRS0022_2_YHRS0023_A10200100400"></td>
	    </tr>
	    <tr class="border_top institution_basic_td_bg border_bottom">
	        <td colspan="1" class="border_right">等级</td>
	        <td colspan="1" class="border_right">一</td>
	        <td colspan="1" class="border_right">二</td>
	        <td colspan="1" class="border_right">三</td>
	        <td colspan="1" class="border_right">四</td>
	        <td colspan="1" class="border_right">五</td>
	        <td colspan="1" class="border_right">六</td>
	        <td colspan="1" class="border_right">七</td>
	        <td colspan="1" class="border_right">八</td>
	        <td colspan="1" class="border_right">九</td>
	        <td colspan="1" class="border_right">十</td>
	        <td colspan="1" class="border_right">十一</td>
	        <td colspan="1" class="border_right">十二</td>
	        <td colspan="1" class="border_right">十三</td>
	        <td colspan="2" class="border_right">未定</td>
	    </tr>
	    <tr class="institution_basic_td_color">
	        <td colspan="1" class="border_right institution_basic_td_bg border_bottom">数量</td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010010010"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010010020"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010010030"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010010040"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010020010"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010020020"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020010020030"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020020010"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020020020"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020020030"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020030010"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020030020"></td>
	        <td colspan="1"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020030030"></td>
	        <td colspan="2" class="border_right"><input maxlength="10" class="num" type="text" name="YHRS0022_2_YHRS0023_A1020090"></td>
	    </tr>
	    <tr class="border_top institution_basic_td_bg border_bottom">
	        <td rowspan="4" colspan="1" class="border_right">工勤技能岗位</td>
	        <td rowspan="2" colspan="1" class="border_right">等级</td>
	        <td colspan="10" class="border_right">技术工</td>
	        <td rowspan="2" colspan="5" class="border_right">普通工</td>
	    </tr>
	    <tr class="institution_basic_td_bg border_bottom">
	        <td colspan="2" class="border_right">一</td>
	        <td colspan="2" class="border_right">二</td>
	        <td colspan="2" class="border_right">三</td>
	        <td colspan="2" class="border_right">四</td>
	        <td colspan="2" class="border_right">五</td>
	    </tr>
	    <tr class="institution_basic_td_color border_bottom">
	        <td colspan="1" class="border_right border_bottom institution_basic_td_bg">数量</td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030010010"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030010020"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030010030"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030010040"></td>
	        <td colspan="2"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030010050"></td>
	        <td colspan="5" class="border_right"><input maxlength="10" class="num" type="text" name="YHRS0022_3_YHRS0023_A1030020"></td>
	    </tr>
	</table>
	<div class="excel-btn">
	    <button class="btn-search btn-default" id="saveGBPlanForm">保存</button>
	</div>
</form>
<script>


var all = 0;
var YHRS0022_1 = 0;
var YHRS0022_2 = 0;
var YHRS0022_3 = 0;
var YHRS0022_2_YHRS0023_A10200100100 = 0;//正高级
var YHRS0022_2_YHRS0023_A10200100200 = 0;//副高级
var YHRS0022_2_YHRS0023_A10200100300 = 0;//中级
var YHRS0022_2_YHRS0023_A10200100400 = 0;//初级
$('.num').keyup(function(e) {
	$(this).val($(this).val().replace(/[^0-9]/g, ''));
	fingure();
}); 

function fingure() {
	var YHRS0022_1 = Number($('[name=YHRS0022_1_YHRS0023_A1010040]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010050]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010060]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010070]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010080]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010090]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010100]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010110]').val())
	+Number($('[name=YHRS0022_1_YHRS0023_A1010190]').val());
	
	var YHRS0022_2_YHRS0023_A10200100100 = Number($('[name=YHRS0022_2_YHRS0023_A1020010010010]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020010010020]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020010010030]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020010010040]').val());
	$('#YHRS0022_2_YHRS0023_A10200100100').html(YHRS0022_2_YHRS0023_A10200100100);
	
	var YHRS0022_2_YHRS0023_A10200100200 = Number($('[name=YHRS0022_2_YHRS0023_A1020010020010]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020010020020]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020010020030]').val());
	$('#YHRS0022_2_YHRS0023_A10200100200').html(YHRS0022_2_YHRS0023_A10200100200);
	
	var YHRS0022_2_YHRS0023_A10200100300 = Number($('[name=YHRS0022_2_YHRS0023_A1020020010]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020020020]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020020030]').val());
	$('#YHRS0022_2_YHRS0023_A10200100300').html(YHRS0022_2_YHRS0023_A10200100300);
	
	var YHRS0022_2_YHRS0023_A10200100400 = Number($('[name=YHRS0022_2_YHRS0023_A1020030010]').val())
    +Number($('[name=YHRS0022_2_YHRS0023_A1020030020]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020030030]').val())
	+Number($('[name=YHRS0022_2_YHRS0023_A1020090]').val());
	$('#YHRS0022_2_YHRS0023_A10200100400').html(YHRS0022_2_YHRS0023_A10200100400);
	
	var YHRS0022_2 = YHRS0022_2_YHRS0023_A10200100100
	+YHRS0022_2_YHRS0023_A10200100200
	+YHRS0022_2_YHRS0023_A10200100300
	+YHRS0022_2_YHRS0023_A10200100400;
    
	var YHRS0022_3 = Number($('[name=YHRS0022_3_YHRS0023_A1030010010]').val())
	+Number($('[name=YHRS0022_3_YHRS0023_A1030010020]').val())
	+Number($('[name=YHRS0022_3_YHRS0023_A1030010030]').val())
	+Number($('[name=YHRS0022_3_YHRS0023_A1030010040]').val())
	+Number($('[name=YHRS0022_3_YHRS0023_A1030010050]').val())
	+Number($('[name=YHRS0022_3_YHRS0023_A1030020]').val());
	
	$('#YHRS0022_1').html(YHRS0022_1);
	$('#YHRS0022_2').html(YHRS0022_2);
	$('#YHRS0022_3').html(YHRS0022_3);
	
	var all = Number($('#YHRS0022_1').html())
	+Number($('#YHRS0022_2').html())
	+Number($('#YHRS0022_3').html());
	$('#all').html(all);
	
	
}


$('#saveGBPlanForm').click(function() {
	var data = $('#gbPlanForm').serializeArray();
	var flag = false;
	data.forEach(function(v,i) {
		if(v.value) {
			flag = true;
		}
	});
	if(!flag) {
		MessageBox.alert('提示', "请至少填写一个数量后保存");
		return false;
	}
	$.ajax({
	    url:'createGBPlan.do?method=createGBPlan',
	    type:'POST',
	    data: $('#gbPlanForm').serializeArray(),
	    dataType:'json',
	    success:function(data){
	    	MessageBox.alert('提示', "保存成功");
	    	$('#nav-body').load('goToGBPlanWorkbeanch.do?method=goToGBPlanWorkbeanch');
	    	//window.location.href="";
	    }
	});
});
</script>
</body>
</html>
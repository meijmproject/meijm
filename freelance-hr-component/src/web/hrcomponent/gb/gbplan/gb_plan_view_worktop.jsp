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
<div class="infoshow-container">
    <div class="infoshow-container multi-info-group">
    	<h2 class="infoshow-title">事业单位岗位设置
            <button class="btn-modify-gray btn-icon-default" onclick="goToUpdatePBPlan()"></button>
        </h2>
        <div class="infoshow-table">
			<table class="institution_basic">
			    <tr class="border_top institution_basic_td_bg border_bottom">
			        <td colspan="2" class=" border_right">单位岗位总量</td>
			        <td colspan="3" class=" border_right">类别</td>
			        <td colspan="4" class=" border_right">管理岗位</td>
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
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010040"></td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010050"></td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010060"></td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010070"></td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010080"></td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010090"></td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010100"></td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010110"></td>
			        <td colspan="1" class="border_right" id="YHRS0022_1_YHRS0023_A1010190"></td>
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
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010010"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010020"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010030"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010040"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020010"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020020"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020030"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020010"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020020"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020030"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030010"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030020"></td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030030"></td>
			        <td colspan="2" class="border_right" id="YHRS0022_2_YHRS0023_A1020090"></td>
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
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010010"></td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010020"></td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010030"></td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010040"></td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010050"></td>
			        <td colspan="5" class="border_right" id="YHRS0022_3_YHRS0023_A1030020"></td>
			    </tr>
			</table>
		</div>
	</div>
</div>
<script>
var list = eval('('+'${list}'+')');
var all = 0;
var YHRS0022_1 = 0;
var YHRS0022_2 = 0;
var YHRS0022_3 = 0;
var YHRS0022_2_YHRS0023_A10200100100 = 0;//正高级
var YHRS0022_2_YHRS0023_A10200100200 = 0;//副高级
var YHRS0022_2_YHRS0023_A10200100300 = 0;//中级
var YHRS0022_2_YHRS0023_A10200100400 = 0;//初级
list.forEach(function(v,i) {
	count = v.postLevelCount;
	var name = 'YHRS0022_'+v.postType+'_YHRS0023_'+v.postLevel;
	$('#'+name).html(count);
});
fingure();

function fingure() {
	var YHRS0022_1 = Number($('#YHRS0022_1_YHRS0023_A1010040').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010050').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010060').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010070').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010080').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010090').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010100').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010110').html())
	+Number($('#YHRS0022_1_YHRS0023_A1010190').html());
	
	var YHRS0022_2_YHRS0023_A10200100100 = Number($('#YHRS0022_2_YHRS0023_A1020010010010').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020010010020').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020010010030').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020010010040').html());
	$('#YHRS0022_2_YHRS0023_A10200100100').html(YHRS0022_2_YHRS0023_A10200100100);
	
	var YHRS0022_2_YHRS0023_A10200100200 = Number($('#YHRS0022_2_YHRS0023_A1020010020010').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020010020020').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020010020030').html());
	$('#YHRS0022_2_YHRS0023_A10200100200').html(YHRS0022_2_YHRS0023_A10200100200);
	
	var YHRS0022_2_YHRS0023_A10200100300 = Number($('#YHRS0022_2_YHRS0023_A1020020010').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020020020').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020020030').html());
	$('#YHRS0022_2_YHRS0023_A10200100300').html(YHRS0022_2_YHRS0023_A10200100300);
	
	var YHRS0022_2_YHRS0023_A10200100400 = Number($('#YHRS0022_2_YHRS0023_A1020030010').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020030020').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020030030').html())
	+Number($('#YHRS0022_2_YHRS0023_A1020090').html());
	$('#YHRS0022_2_YHRS0023_A10200100400').html(YHRS0022_2_YHRS0023_A10200100400);
	
	var YHRS0022_2 = YHRS0022_2_YHRS0023_A10200100100
	+YHRS0022_2_YHRS0023_A10200100200
	+YHRS0022_2_YHRS0023_A10200100300
	+YHRS0022_2_YHRS0023_A10200100400;
    
	var YHRS0022_3 = Number($('#YHRS0022_3_YHRS0023_A1030010010').html())
	+Number($('#YHRS0022_3_YHRS0023_A1030010020').html())
	+Number($('#YHRS0022_3_YHRS0023_A1030010030').html())
	+Number($('#YHRS0022_3_YHRS0023_A1030010040').html())
	+Number($('#YHRS0022_3_YHRS0023_A1030010050').html())
	+Number($('#YHRS0022_3_YHRS0023_A1030020').html());
	
	$('#YHRS0022_1').html(YHRS0022_1);
	$('#YHRS0022_2').html(YHRS0022_2);
	$('#YHRS0022_3').html(YHRS0022_3);
	
	var all = Number($('#YHRS0022_1').html())
	+Number($('#YHRS0022_2').html())
	+Number($('#YHRS0022_3').html());
	$('#all').html(all);
	
	
}

function goToUpdatePBPlan() {
	Widget.openContent("goToUpdatePBPlan.do?method=goToUpdatePBPlan");
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title></title>
<%@ include file="/include/js_css_base_include.jsp"%>
<%
response.setHeader("Content-Disposition", "attachment; filename="+new String(("岗位使用情况表.xls").getBytes("GBK"), "ISO8859-1"));
%>
<link rel="stylesheet" href="hspszhphtml/css/common/reset.css">
<link rel="stylesheet" href="hspszhphtml/css/components/institution_basic_table.css">
<link rel="stylesheet" href="hspszhphtml/css/components/handle_btn.css">
<link rel="stylesheet" href="hspszhphtml/css/components/modal_dialog.css">
<style>
.institution_basic {
	border-collapse: collapse;
	text-align: center;
}

.institution_basic td,.institution_basic th {
	border: 1px solid #000;
}
</style>
<script type="text/javascript">
</script>
</head>
<body style="overflow:auto;">
            <table class="institution_basic">
                <!-- <tr class="border_top border_bottom">
                    <td colspan="1" class="institution_basic_td_bg border_right">单位名称</td>
                    <td colspan="16" class="border_right" style="text-align: left;padding-left: 20px;">深圳市儿童医院</td>
                </tr> -->
                <tr class="border_bottom">
                    <td colspan="1" class="institution_basic_td_bg border_right">岗位数</td>
                    <td colspan="8" id="all" class="institution_basic_td_color border_right">${all}</td>
                    <td colspan="2" class="institution_basic_td_bg border_right border_bottom">双肩挑人数</td>
                    <td colspan="6" class="institution_basic_td_color border_right">${listGBPlanTwoWorking}</td>
                </tr>
                <tr class="border_bottom">
                    <td colspan="2" class="institution_basic_td_bg border_right">现聘总人员</td>
                    <td colspan="3" class="institution_basic_td_bg border_right">类别</td>
                    <td colspan="4" class="institution_basic_td_bg border_right">管理岗位</td>
                    <td colspan="4" class="institution_basic_td_bg border_right">专业技术岗位</td>
                    <td colspan="4" class="institution_basic_td_bg border_right">工勤技能岗位</td>
                </tr>
                <tr class="border_bottom">
                    <td colspan="2" id="A" class="institution_basic_td_color border_right">${gBPlanWorkings}</td>
                    <td colspan="3" class="institution_basic_td_bg border_right">在聘人数</td>
                    <td colspan="4" id="A1" class="institution_basic_td_color border_right">${guanliGBPlanWorkings}</td>
                    <td colspan="4" id="A2" class="institution_basic_td_color border_right">${zhuanyeGBPlanWorkings}</td>
                    <td colspan="4" id="A3" class="institution_basic_td_color border_right">${gongqinGBPlanWorkings}</td>
                </tr>
                <tr class="border_top institution_basic_td_bg border_bottom">
                    <td rowspan="3" colspan="1" class="border_right">管理岗位</td>
                    <td colspan="1" class="border_right">等级</td>
                    <td colspan="1" class="border_right">一</td>
                    <td colspan="1" class="border_right">二</td>
                    <td colspan="1" class="border_right">三</td>
                    <td colspan="1" class="border_right">四</td>
                    <td colspan="2" class="border_right">五</td>
                    <td colspan="2" class="border_right">六</td>
                    <td colspan="2" class="border_right">七</td>
                    <td colspan="2" class="border_right">八</td>
                    <td colspan="1" class="border_right">九</td>
                    <td colspan="1" class="border_right">十</td>
                    <td colspan="1" class="border_right">未定</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="border_right institution_basic_td_bg border_bottom">核定数</td>
                    <td colspan="1" id="YHRS0022_1_YHRS0023_A1010010">${obj.A1010010}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010020">${obj.A1010020}</td>
                    <td colspan="1" id="YHRS0022_1_YHRS0023_A1010040">${obj.A1010040}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010050">${obj.A1010050}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010060">${obj.A1010060}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010070">${obj.A1010070}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010080">${obj.A1010080}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010090">${obj.A1010090}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010100">${obj.A1010010}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010110">${obj.A1010110}</td>
			        <td colspan="1" class="border_right" id="YHRS0022_1_YHRS0023_A1010190">${obj.A1010190}</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="border_right institution_basic_td_bg border_bottom">现聘人数</td>
                   	<td colspan="1" id="YHRS0022_1_YHRS0023_A1010010">${listGBPlanWorkings.A1010010}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010020">${listGBPlanWorkings.A1010020}</td>
                    <td colspan="1" id="YHRS0022_1_YHRS0023_A1010040">${listGBPlanWorkings.A1010040}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010050">${listGBPlanWorkings.A1010050}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010060">${listGBPlanWorkings.A1010060}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010070">${listGBPlanWorkings.A1010070}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010080">${listGBPlanWorkings.A1010080}</td>
			        <td colspan="2" id="YHRS0022_1_YHRS0023_A1010090">${listGBPlanWorkings.A1010090}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010100">${listGBPlanWorkings.A1010010}</td>
			        <td colspan="1" id="YHRS0022_1_YHRS0023_A1010110">${listGBPlanWorkings.A1010110}</td>
			        <td colspan="1" class="border_right" id="YHRS0022_1_YHRS0023_A1010190">${listGBPlanWorkings.A1010190}</td>
                </tr>
                <tr class="border_top institution_basic_td_bg border_bottom">
                    <td rowspan="6" colspan="1" class="border_right">专业技术岗位</td>
                    <td colspan="1" class="border_right">层级</td>
                    <td colspan="3" class="border_right">正高级</td>
                    <td colspan="4" class="border_right">副高级</td>
                    <td colspan="4" class="border_right">中级</td>
                    <td colspan="4" class="border_right">初级</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="institution_basic_td_bg border_right border_bottom">核定数</td>
                    <td colspan="3" id="YHRS0022_2_YHRS0023_A10200100100">${obj.A1020010010010+obj.A1020010010020+obj.A1020010010030}</td>
			        <td colspan="4"  id="YHRS0022_2_YHRS0023_A10200100200">${obj.A1020010010040+obj.A1020010020010+obj.A1020010020020+obj.A1020010020030}</td>
			        <td colspan="4"  id="YHRS0022_2_YHRS0023_A10200100300">${obj.A1020020010+obj.A1020020020+obj.A1020020030+obj.A1020030010}</td>
			        <td colspan="4" class="border_right" id="YHRS0022_2_YHRS0023_A10200100400">${obj.A1020030020+obj.A1020030030+obj.A1020090}</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="institution_basic_td_bg border_right border_bottom">现聘人数</td>
                    <td colspan="3" id="A1020010010">${listGBPlanWorkings.A1020010010}</td>
                    <td colspan="4" id="A1020010020">${listGBPlanWorkings.A1020010020}</td>
                    <td colspan="4" id="A1020020">${listGBPlanWorkings.A1020020}</td>
                    <td colspan="4" id="A1020030" class="border_right">${listGBPlanWorkings.A1020030}</td>
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
                    <td colspan="1" class="border_right institution_basic_td_bg border_bottom">核定数</td>
                   	<td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010010">${obj.A1020010010010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010020">${obj.A1020010010020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010030">${obj.A1020010010030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010040">${obj.A1020010010040}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020010">${obj.A1020010020010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020020">${obj.A1020010020020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020030">${obj.A1020010020030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020010">${obj.A1020020010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020020">${obj.A1020020020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020030">${obj.A1020020030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030010">${obj.A1020030010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030020">${obj.A1020030020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030030">${obj.A1020030030}</td>
			        <td colspan="2" class="border_right" id="YHRS0022_2_YHRS0023_A1020090">${obj.A1020090}</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="border_right institution_basic_td_bg border_bottom">现聘人数</td>
                   	<td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010010">${listGBPlanWorkings.A1020010010010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010020">${listGBPlanWorkings.A1020010010020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010030">${listGBPlanWorkings.A1020010010030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010010040">${listGBPlanWorkings.A1020010010040}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020010">${listGBPlanWorkings.A1020010020010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020020">${listGBPlanWorkings.A1020010020020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020010020030">${listGBPlanWorkings.A1020010020030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020010">${listGBPlanWorkings.A1020020010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020020">${listGBPlanWorkings.A1020020020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020020030">${listGBPlanWorkings.A1020020030}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030010">${listGBPlanWorkings.A1020030010}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030020">${listGBPlanWorkings.A1020030020}</td>
			        <td colspan="1" id="YHRS0022_2_YHRS0023_A1020030030">${listGBPlanWorkings.A1020030030}</td>
			        <td colspan="2" class="border_right" id="YHRS0022_2_YHRS0023_A1020090">${listGBPlanWorkings.A1020090}</td>
                </tr>
                <tr class="border_top institution_basic_td_bg border_bottom">
                    <td rowspan="4" colspan="1" class="border_right">工勤技能岗位</td>
                    <td rowspan="2" colspan="1" class="border_right">等级</td>
                    <td colspan="10" class="border_right">技术工</td>
                    <td rowspan="2" colspan="2" class="border_right">普通工</td>
                    <td rowspan="2" colspan="3" class="border_right">未定级</td>
                </tr>
                <tr class="institution_basic_td_bg border_bottom">
                    <td colspan="2" class="border_right">一</td>
                    <td colspan="2" class="border_right">二</td>
                    <td colspan="2" class="border_right">三</td>
                    <td colspan="2" class="border_right">四</td>
                    <td colspan="2" class="border_right">五</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="border_right border_bottom institution_basic_td_bg">核定数</td>
                    <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010010">${obj.A1030010010}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010020">${obj.A1030010020}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010030">${obj.A1030010030}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010040">${obj.A1030010040}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010050">${obj.A1030010050}</td>
			        <td colspan="2" class="border_right" id="YHRS0022_3_YHRS0023_A1030020">${obj.A1030020}</td>
			        <td colspan="3" class="border_right" id="YHRS0022_3_YHRS0023_A1030090">${obj.A1030090}</td>
                </tr>
                <tr class="institution_basic_td_color">
                    <td colspan="1" class="border_right border_bottom institution_basic_td_bg">现聘人数</td>
                    <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010010">${listGBPlanWorkings.A1030010010}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010020">${listGBPlanWorkings.A1030010020}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010030">${listGBPlanWorkings.A1030010030}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010040">${listGBPlanWorkings.A1030010040}</td>
			        <td colspan="2" id="YHRS0022_3_YHRS0023_A1030010050">${listGBPlanWorkings.A1030010050}</td>
			        <td colspan="2" class="border_right" id="YHRS0022_3_YHRS0023_A1030020">${listGBPlanWorkings.A1030020}</td>
			        <td colspan="3" class="border_right" id="YHRS0022_3_YHRS0023_A1030090">${listGBPlanWorkings.A1030090}</td>
                </tr>
            </table>
<script>
/* $(document).ready(function(){
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
	all += count;
	if(v.postType==1) YHRS0022_1 += count; 
	if(v.postType==2) {
		YHRS0022_2 += count;
		if(v.postLevel=='A1020010010010'||v.postLevel=='A1020010010020'||v.postLevel=='A1020010010030') {
			YHRS0022_2_YHRS0023_A10200100100 += count;
		}else if(v.postLevel=='A1020010010040'||v.postLevel=='A1020010020010'||v.postLevel=='A1020010020020'||v.postLevel=='A1020010020030') {
			YHRS0022_2_YHRS0023_A10200100200 += count;
		}else if(v.postLevel=='A1020020010'||v.postLevel=='A1020020020'||v.postLevel=='A1020020030'||v.postLevel=='A1020030010') {
			YHRS0022_2_YHRS0023_A10200100300 += count;
		}else if(v.postLevel=='A1020030020'||v.postLevel=='A1020030030'||v.postLevel=='A1020090') {
			YHRS0022_2_YHRS0023_A10200100400 += count;
		}
	}
	if(v.postType==3) YHRS0022_3 += count;
});
$('#all').html(all);
$('#YHRS0022_1').html(YHRS0022_1);
$('#YHRS0022_2').html(YHRS0022_2);
$('#YHRS0022_3').html(YHRS0022_3);
$('#YHRS0022_2_YHRS0023_A10200100100').html(YHRS0022_2_YHRS0023_A10200100100);
$('#YHRS0022_2_YHRS0023_A10200100200').html(YHRS0022_2_YHRS0023_A10200100200);
$('#YHRS0022_2_YHRS0023_A10200100300').html(YHRS0022_2_YHRS0023_A10200100300);
$('#YHRS0022_2_YHRS0023_A10200100400').html(YHRS0022_2_YHRS0023_A10200100400);

var A1Count = 0;
var A2Count = 0;
var A3Count = 0;
var listGBPlanWorking = eval('('+'${listGBPlanWorking}'+')');
listGBPlanWorking.forEach(function(v,i){
	$('#'+v.level).html(v.count);
	var level = v.level ;
	var count  = v.count;
	if(level.substring(3,4)=="1"){
		A1Count+=count;
	}else if(level.substring(3,4)=="2"){
		A2Count+=count;
	}else if(level.substring(3,4)=="3"){
		A3Count+=count;
	}
})
$('#A1').html(A1Count);
$('#A2').html(A2Count);
$('#A3').html(A3Count);
$('#A').html(A1Count+A2Count+A3Count);
} */
</script>
</body>
</html>
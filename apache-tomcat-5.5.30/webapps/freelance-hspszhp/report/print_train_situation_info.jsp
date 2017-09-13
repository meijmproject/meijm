<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>培训情况表格显示页</title>
    <style>
    #wardset_collection_table_html{border-collapse:collapse;text-align:center;margin:5px;}
    #wardset_collection_table_html td,#wardset_collection_table_html th{border:1pt solid #000; text-align: center;}
    #wardset_collection_title{font-size:18pt;font-weight:bold;}
    #wardset_collection_content{font-size:12pt;font-weight:bold;}
    </style>
</head>
<body>
	<div class="handel"><button class="btn_print" onclick="location.href='goviewTrainSituationPersonInfo.do?method=goviewTrainSituationPersonInfo&flag=excel';">导出</button></div>
	<table id="wardset_collection_table_html" cellspadding="0" cellspacing="0" align="center" >
		<thead>
			<tr><th id="wardset_collection_title" colspan="16" >培训情况统计报表</th></tr>
		</thead>
		<tbody id="wardset_collection_content">
			<tr>
				<td rowspan='2' width="20%">指标名称</td>
				<td colspan='3'>${year-4}</td><td colspan='3'>${ year-3}</td><td colspan='3'>${ year-2}</td><td colspan='3'>${ year-1}</td><td colspan='3'>${ year}</td>
			</tr>
			<tr>
			<%for(int i=0;i<5;i++){%>
				<td width="5%">高级</td><td width="5%">中级</td><td width="5%">初级</td>
				<%}%>
			</tr>
			<tr id="#">
				<td > 参加政府举办的岗位培训人次数</td>
				<c:forEach items="${list1}" var="list">
					<td><c:out value="${fn:length(list)}"></c:out></td>
				</c:forEach>
			</tr>
			<tr id="#">
				<td > 本院外出进修半年以上人数</td>
				<c:forEach items="${list3}" var="list">
					<td><c:out value="0"></c:out></td>
				</c:forEach>
			</tr>
				<tr id="#">
				<td >  接受其他医院进修人员数</td>
				<c:forEach items="${list3}" var="list">
					<td><c:out value="${fn:length(list)}"></c:out></td>
				</c:forEach>
			</tr>
			
		</tbody>
	</table>
</body>
<script>
/* var myDate = new Date();
var list = '${list}';
list = eval('('+list+')');
$('#wardset_collection_title').attr('colspan',list.length+2);
$('#wardset_collection_title').html('医院各病区卫技人员配备情况一览表（截止'+myDate.getFullYear()+'）');
list.forEach(function(v,i) {
	for(var k in v) {
		$('#'+k).append('<td>'+v[k]+'</td>');
	}
}); */
</script>
</html>
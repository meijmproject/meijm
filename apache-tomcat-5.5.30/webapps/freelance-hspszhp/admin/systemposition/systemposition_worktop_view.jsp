<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<%@ include file="/include/js_css_admin_include.jsp"%>
</head>
<body>
<div class="sitemap">
      <ul style="float: left">
        <li>当前位置：</li>
        <li><a href="#">系统管理&gt;</a></li>
        <li><a href="#">系统安全 &gt;</a></li>
        <li><a href="#">系统岗位维护</a></li>
    </ul>
    <div class="go_back"><a href="javascript:void(0);" onclick="goback();"><span style="font-family: sans-serif;"><< </span>返回</a></div>
    <div style="clear: both"></div>
</div>
<!--左导航-->
<div class="left_nav" id="left_nav">
<div class="sys_base">岗位信息: </div>
    <ul class="user_infor">
        <li class="user_front">系统岗位名称:</li>
        <li class="user_latter">${systemPositionForm.systemPositionName}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">系统岗位描述:</li>
        <li class="user_latter">${systemPositionForm.systemPositionDesc}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">数据权限角色ID:</li>
        <li class="user_latter">${systemPositionForm.dataRoleName}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">功能权限角色ID:</li>
        <li class="user_latter">${systemPositionForm.functionRoleName}</li>
    </ul>
    <div style="clear: both"></div>
</div>
<!--中间主体内容-->
<div class="right_content_sys main_bg">
    <div class="sys_box">
        <div class="sys_base"><span>岗位用户列表</span>
        </div>
        <table class="x-table sortable ellipsis striped hover"></table>
    </div>
</div>
</body>

<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'findUserBySpId.do?method=findUserBySpId&systemPositionOid=${systemPositionForm.systemPositionOid}',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			//checkbox: true,
			singleCheck: false,
			columns: [
				{header:'用户登陆ID', field:'userId'},
				{header:'用户姓名', field:'userName'},
				{header:'所属单位OID', field:'unitId'},
				{header:'所属单位名称', field:'unitName'},
				{header:'生效日期', field:'effectiveDt'},
				{header:'失效日期', field:'expiredDate'}
			]
		}
	]);
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.sys_base').outerHeight(true)
			- 58
			);//48(分页48)
	}).resize();
	
	worktop.grid.store.load({
		params: {start:0, limit: worktop.grid.page.limit}
	});
	
});
function goback(){
	HistoryRegister.go("goBackUrl");
}
</script>
</html>
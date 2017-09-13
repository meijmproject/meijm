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
</div> 
<!--中间主体内容-->
<div class="right_content_sys main_bg" >
    <div class="sys_box" style="height: auto; padding-bottom: 0px;"  >
        <div class="sys_base"><span>岗位用户列表</span>
        <a style="float:right;margin-left: 20px;" onclick="addUser()"><img src="img/DetailPages/icon_add_green.png" /></a>
        </div>
        <table class="x-table sortable ellipsis striped hover" id='systemPosition'></table>
    </div>
</div> 
</body>

<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	$("#left_nav").load('goUpdateSystemPoistionLeft.do?method=goViewPoistionRole',{"systemPositionOid":${systemPositionForm.systemPositionOid}});
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'findUserBySpId.do?method=findUserBySpId&systemPositionOid=${systemPositionForm.systemPositionOid}',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			el:'#systemPosition', 
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'用户登陆ID', field:'userId', width:150},
				{header:'用户姓名', field:'userName', width:150},
				/* {header:'所属单位OID', field:'unitId', width:150}, */
				{header:'所属单位名称', field:'unitName', width:150},
				{header:'生效日期', field:'effectiveDate', width:150},
				{header:'失效日期', field:'expiredDate', width:150},
				{header:'操作', css:'operation', type: 'html', width:50, render: 
					function(v,r){
						var userOid=r.data.userOid;
						var systemPositionOid = r.data.systemPositionOid;
						return $.format('<a  onclick="deleteUser('+userOid+','+systemPositionOid+')"><img src="img/DetailPages/icon_delete.png"/></a><a style="margin-left: 20px;" onclick="updateUser('+userOid+','+systemPositionOid+')"><img src="img/DetailPages/icon_revise.png" />', r.data);
					}
				}
			]
		}
	]);
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.sys_base').outerHeight(true)
			- 48
			);//48(分页48)
	}).resize();
	
	worktop.grid.store.load({
		params: {start:0, limit: worktop.grid.page.limit}
	});
	
});
function goback(){
	HistoryRegister.go("goBackUrl");
}
function updateUser(userOid,systemPositionOid){
	Widget.openContent("updatePositionUser.do?method=goViewPoistionUser&userOid="+userOid+"&systemPositionOid="+systemPositionOid,function(){
		worktop.grid.store.load({
			params: {start:0, limit: worktop.grid.page.limit}
		});
	});
}
function deleteUser(userOid,systemPositionOid){
	$.get("deletePositionUser.do?method=deletePositionUser&userOid="+userOid+"&systemPositionOid="+systemPositionOid,function(data){
		if (data.message) {
			MessageBox.alert('提示', data.message, function(){
				worktop.grid.store.load({
					params: {start:0, limit: worktop.grid.page.limit}
				});
			})
		}
	},'json')
}
function addUser(){
	Widget.openContent("goAddPositionUser.do?method=goAddSystemPoistion&systemPositionOid="+${systemPositionForm.systemPositionOid},function(){
		worktop.grid.store.load({
			params: {start:0, limit: worktop.grid.page.limit}
		});
	});
}
function update(){
	Widget.openContent("goToUpdateSystemPoistion.do?method=goViewSystemPoistion&systemPositionOid="+${systemPositionForm.systemPositionOid});
}
function findRoleList(roleType){
	Widget.openContent("goFindRoleList.do?method=goFindRoleList&roleType="+roleType+"&systemPositionOid="+${systemPositionForm.systemPositionOid},function(){
		$("#left_nav").load('goUpdateSystemPoistionLeft.do?method=goViewPoistionRole',{"systemPositionOid":${systemPositionForm.systemPositionOid}});
	});
}
</script>
</html>
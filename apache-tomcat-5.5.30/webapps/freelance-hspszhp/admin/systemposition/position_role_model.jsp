<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>

<body>

<div id="showmodal" class="modal-content">
    <div class="modal-header">
       <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
					<span aria-hidden="true">&times;</span>
				</button>
         <h4 class="modal-title">系统角色列表信息&gt;修改</h4>
    </div>
    <div class="search" id="query-condition">
	    <form id="verperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>角色名称：</dt>
	            <dd><label><input type="text" name="roleName" value="${param.roleName}"/></label></dd>
	        </dl>
	        <button style="float: left;" class="btn_search" onclick="worktop.form.goQuery();">查询</button>
        	<div style="clear: both"></div>
	       	<!-- 列表内容展示-->
	    	<table class="x-table sortable ellipsis striped hover" id="positionRole"></table>
	        </form>
	</div>
	<div style="clear: both"></div>
    
    <div class="modal-footer">
		<button type="submit" class="btn btn-primary" onclick="addPositionRole()">保存</button>
		<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
</div>
<script type="text/javascript">
var roleType=${param.roleType};
var systemPositionOid=${param.systemPositionOid};
var worktopRole=null;
$(document).ready(function() {
	 worktopRole = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'findRoleList.do?method=findRoleByType&roleType='+roleType,
			lengthMenu:[30,50,100],
			el:'#positionRole',
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: true,
			columns: [
				{header:'角色名称', field:'roleName'},
				{header:'角色描述', field:'roleDesc'}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#verperson_wsid'
		}
	]);
	$(window).resize(function(){
		worktopRole.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.sys_base').outerHeight(true)
			- 300
			);//48(分页48)
	}).resize();
	
	worktopRole.grid.store.load({
		params: {start:0, limit:worktopRole.grid.page.limit}
	});
});

function addPositionRole(){
	var selectRow = worktopRole.grid.selectModel.getSelectRows();
	if(selectRow.length==0) {
		MessageBox.alert('提示',"请选定记录再操作");
	}else if(selectRow.length!=1){
		MessageBox.alert('提示',"只能选择一条记录");
	}else{
	var roleId=selectRow[0].data.roleId;
 		$.get("updatePositionRole.do?method=updatePositionUser&systemPositionOid="+systemPositionOid+"&roleId="+roleId+"&roleType="+roleType,function(){
	 	Widget.close();
	  })
	}
	
}
</script>
</body>
</html>
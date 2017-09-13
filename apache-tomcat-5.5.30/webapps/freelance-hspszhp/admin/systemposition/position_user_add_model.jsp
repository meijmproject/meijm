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
         <h4 class="modal-title">系统岗位用户信息&gt;新增</h4>
    </div>
   <div class="search" id="query-condition">
     <form id="positionUser" action="#" method="post">
      <dl class="search-horizontal">
           <dt>用户登陆ID：</dt>
           <dd><label><input type="text" name="userId" value=""/></label></dd>
	   </dl>
	   <dl class="search-horizontal">
           <dt>用户姓名：</dt>
           <dd><label><input type="text" name="userName" value=""/></label></dd>
	   </dl>
       <button style="float: left;" class="btn_search" onclick="worktopModel.grid.store.load();">查询</button>
      	<div style="clear: both"></div>
     <table class="x-table sortable ellipsis striped hover" id='positionUserId' ></table>
    </form>
    </div>
	<div style="clear: both"></div>
    <div class="modal-footer">
		<button type="submit" class="btn btn-primary" onclick="addPositionUser()">保存</button>
		<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
</div>
<script type="text/javascript">
var systemPositionOid=${param.systemPositionOid};
var worktopModel = null;
$(document).ready(function() {
	worktopModel = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'goFindPositionUser.do?method=goFindPositionUser&systemPositionOid='+systemPositionOid,
			lengthMenu:[30,50,100],
		    el:'#positionUserId', 
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'用户登陆ID', field:'userId', width:180},
				{header:'用户姓名', field:'userName', width:80},
				{header:'所属单位OID', field:'unitId', width:120},
				{header:'所属单位名称', field:'unitName', width:80},
				{header:'生效日期', field:'effectiveDt', width:100},
				{header:'失效日期', field:'expiredDate', width:100}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#positionUser'
		}
	]);
	
	$(window).resize(function(){
		worktopModel.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- $('.sys_base').outerHeight(true)
			- 300
			);//48(分页48)
	}).resize();
	
	 worktopModel.grid.store.load({
		params: {start:0, limit: worktopModel.grid.page.limit}
	}); 
	/* worktop.form.goQuery(); */
});

function addPositionUser(){
	var userIds = new Array();
	var records = worktopModel.grid.selectModel.getSelectRows();
	for (var i=0; i<records.length; i++) {
		userIds[userIds.length] = records[i].data.userId;
	}
	if(records.length==0) {
		MessageBox.alert("提示","请选定记录再操作");
		
	}else{
		$.ajax({
       		url : 'addPositionUser.do?method=addPositionUser',
           	data : {systemPositionOid:systemPositionOid,userIds:userIds.join(',')},
           	dataType : 'json',
           	error : function(x,t) {
           		MessageBox.alert(t)
           	},
           	async : false,
           	success : function(data) {
               if (data.success) {
            	   Widget.close();
            	   worktop.form.goQuery();
               }
               else
               {
               	MessageBox.alert(data.message);
               }
           }
       });
	}
}
</script>
</body>
</html>
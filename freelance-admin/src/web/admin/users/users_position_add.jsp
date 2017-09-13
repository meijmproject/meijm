<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>用户管理页面</title>
<script type="text/javascript" >
//设定定高
$("#userPosition").css('max-height',document.documentElement.clientHeight-180);
</script> 
<script type="text/javascript">
$(document).ready(function() {  
	var worktop = null;
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'goUsersPositionAddPage.do?method=goUsersPositionAddPage&userId='+'${userId}'+'&userOid='+'${userOid}',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'岗位名称', field:'systemPositionName', width:120},
				{header:'岗位描述', field:'systemPositionDesc', width:80},
				{header:'功能角色权限', field:'functionRoleName', width:120},
				{header:'数据角色权限', field:'dataRoleName', width:80}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#verperson_wsid'
		}
	]);
	var tabInfoTips = $("#tabInfoTips").remove();
	worktop.grid.on('draw', function(){
		
		tabInfoTips.remove();
		
		tabInfoTips.click(function(){
			tabInfoTips.hide();
		});

		worktop.grid.tbody.find("tr").click(function(event){
				var rowIndex = $(this).attr('rowIndex');
				var record = worktop.grid.getRecordAt(rowIndex);
				tabInfoTips.attr('rowIndex', rowIndex);
				tabInfoTips.attr('userId', record.data.userId);
				tabInfoTips.attr('systemPositionOid', record.data.systemPositionOid);
				event.stopPropagation(); //停止冒泡到rowclick

		});
	}, worktop.grid);
	
	$(document.body).click(function(){
		$("#tabInfoTips").hide();
	});
	
	worktop.grid.wrap.find('.ct').height(
			$(window).height() - 
			$("#query-condition").outerHeight(true) - 
			$("#nav_tab").outerHeight(true) -
			$("#handel-button").outerHeight(true) - 
			130);//68+60+20(分页，路径，bottom)
	worktop.form.goQuery();

	$('button[button-click=add-usp]').click(function(){
		var systemPositionOids = new Array();
		var records = worktop.grid.selectModel.getSelectRows();
		for (var i=0; i<records.length; i++) {
			systemPositionOids[systemPositionOids.length] = records[i].data.systemPositionOid;
		}
		if(records.length==0) {
			MessageBox.alert("提示","请选定记录再操作");
		}else{
			$.ajax({
           		url : 'usersPositionAdd.do?method=usersPositionAdd',
               	data :  {systemPositionOids:systemPositionOids+'',userId:'${userId}',userOid:'${userOid}'},
               	dataType : 'json',
               	error : function(x,t) {
               		MessageBox.alert(t)
               	},
               	async : false,
               	success : function(data) {
                   if (data.success) {
                	   Widget.close();
                 	   Widget.load('#updateUSP');
                   }
                   else
                   {
                   	MessageBox.alert(data.message);
                   }
               }
           	});
			}
	});
} );

</script>
</head>
<body>
<div id="showmodal" class="modal-content">
	<div class="modal-header">
            <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
       </button>
            <h4 class="modal-title">用户岗位&gt;添加</h4>
    </div>
    
	    <!-- 查询条件-->
	  <div class="search" id="query-condition">
	    <form id="verperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>岗位名称：</dt>
	            <dd><label><input type="text" name="systemPositionName" value="${param.systemPositionName}"/></label></dd>
	        </dl>
	        <button style="float: left;" class="btn_search" onclick="worktop.form.goQuery();">查询</button>
        	<!-- <button style="float: left" class="btn_cancel" onclick="clearn()">取消</button> -->
        	<div style="clear: both"></div>
	       	<!-- 列表内容展示-->
	    	<table class="x-table sortable ellipsis striped hover" id="userPosition"></table>
	        </form>
	</div>
	<div style="clear: both"></div>
	<div class="modal-footer">
				<button type="button" class="btn btn-primary" button-click="add-usp">保存</button>
				<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
  </div>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>选择人员页面</title>
<style type="text/css">
.gray td{
   background:#c6c6c6!important;
   border:1px;
}
</style>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var selectPersonWorktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listSelectPerson.do?method=selectPseron',
			el:'#selectPersonGrid',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'单位名称', field:'unitName', width:200, rowCss: function(v,r){return r.data.remark ? "disabled gray":'' }},
				{header:'人员名称', field:'name', width:150},
				{header:'证件号码', field:'idNo', width:150},
				{header:'人员状态', field:'personStatus', width:150},
				{header:'职务层次', field:'positionLevel', width:150},
				{header:'备注', field:'remark', width:350}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#selectperson_wsid'
		}
	]);
	
	$(window).resize(function(){
		selectPersonWorktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('#selectPersonWorktop').outerHeight(true)
			- $('.modal-footer').outerHeight(true)
			- 260
			);//48(分页48)
	}).resize();

	$.copyProperties(selectPersonWorktop.grid.store.baseParams, Widget.getContent().settings.params);

	selectPersonWorktop.form.goQuery();

	$('button[button-click=add-selectperson]').click(function(){
		var selectRows = selectPersonWorktop.grid.selectModel.getSelectRows();
		if (selectRows.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		if(selectRows.length > 1){
			MessageBox.alert('提示', '每次只能操作一条记录！');
			return;
		}
			
		Widget.getContent().settings.data = selectPersonWorktop.grid.selectModel.getSelectRows();
		Widget.close();
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
            <h4 class="modal-title">业务信息&gt;添加</h4>
    </div>
    
	    <!-- 查询条件-->
	  <div class="search" id="query-condition">
	    <form id="selectperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>单位名称：</dt>
	            <dd><label><input type="text" name="unitName" /></label></dd>
	        </dl>
	        <dl class="search-horizontal">
	         <dt>人员名称：</dt>
	            <dd><label><input type="text" name="personName" /></label></dd>
	        </dl>
	        <button style="float: left;" class="btn_search" >查询</button>
        	<div style="clear: both"></div>
	       	<!-- 列表内容展示-->
	    	<table class="x-table sortable ellipsis striped hover" id="selectPersonGrid"></table>
	        </form>
	</div>
	<div style="clear: both"></div>
	<div class="modal-footer">
	<button type="button" class="btn btn-primary" button-click="add-selectperson">保存</button>
	<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
  </div>	
</body>
</html>
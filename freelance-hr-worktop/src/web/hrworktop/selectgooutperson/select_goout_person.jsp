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
				{header:'工号', field:'personCode', rowCss: function(v,r){return r.data.remark ? "disabled gray":'' }},
				{header:'姓名', field:'name'},
				{header:'科室', field:'orgName'},
				{header:'外出地点', field:'goOutAddress'},
				{header:'开始日期', field:'startDate',format:'yyyy-MM-dd'},
				{header:'结束日期', field:'endDate',format:'yyyy-MM-dd'},
				{header:'外出天数', field:'dayCount'},
				{header:'经费来源', field:'budgetFrom'}
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
<div id="transaction_modal_box" class="transaction_modal_box">
	<div class="modal-dialog-container">
		<div class="md-title">普通外出销假信息&gt;添加<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
 		<div class="md-main-content">
			<div class="infoshow-container md-infoshow-area">
				<div class="isc-title-box"><h3 class="md-isc-title">查询</h3></div>
				<div class="md-units clearfix">
					<form id="selectperson_wsid" action="#" method="post">
						<dl class='search-unit'>
							<dt title="创建起始时间">创建起始时间：</dt>
							<dd><label><input type='text'  name='goOutStartDate'  onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'}) /></label></dd>
						</dl>
						<dl class='search-unit'>
							<dt title="创建截止时间">创建截止时间：</dt>
							<dd><label><input type='text'  name='goOutEndDate'  onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'}) /></label></dd>
						</dl>
						<dl class="search-unit search-btns">
							<dt><button class="btn_search">查询</button></dt>
						</dl>
					</form>
				</div>
				<div class="isc-title-box">
            		<h3 class="md-isc-title">普通外出销假信息</h3>
				</div>
				<table id="selectPersonGrid" class="x-table sortable ellipsis striped hover personBycp"></table>
			</div>
		</div>
	</div>
	<div class="md-btn">
        <button class="md-btn-save close-popdown" button-click="add-selectperson">确 认</button>
        <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
      </div>
</div>


<!-- <div id="showmodal" class="modal-content">
	<div class="modal-header">
            <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
       </button>
            <h4 class="modal-title">业务信息&gt;添加</h4>
    </div>
    
	    查询条件
	  <div class="search" id="query-condition">
	    <form id="selectperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>外出开始日期：</dt>
	            <dd><label><input type='text'  name='goOutStartDate'  onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'}) /></label></dd>
	        </dl>
	         <dl class="search-horizontal">
	            <dt>外出结束日期：</dt>
	            <dd><label><input type='text'  name='goOutEndDate'  onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'}) /></label></dd>
	        </dl>
	        <button style="float: left;" class="btn_search" >查询</button>
        	<div style="clear: both"></div>
	       	列表内容展示
	    	<table class="x-table sortable ellipsis striped hover" id="selectPersonGrid"></table>
	        </form>
	</div>
	<div style="clear: both"></div>
	<div class="modal-footer">
	<button type="button" class="btn btn-primary" button-click="add-selectperson">保存</button>
	<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
  </div>	 -->
</body>
</html>
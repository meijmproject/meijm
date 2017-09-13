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
<script type="text/javascript">
$(document).ready(function() {
	var bizPersonOid="<%=request.getParameter("bizPersonOid")%>";
	var selectPostWorktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listSelectPost.do?method=selectPost&bizPersonOid='+bizPersonOid,
			el:'#selectPostGrid',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'岗位名称', field:'positionName', width:100},
				{header:'岗位类别', field:'positionType', width:100},
				{header:'岗位级别', field:'positionLevel', width:100},
				{header:'职务级别', field:'dutyLevel', width:100},
				{header:'任职开始日期', field:'beginDate', width:150},
				{header:'任职拟终止日期', field:'endDate', width:150},
				{header:'任职单位', field:'dutyUnitName', width:100},
				{header:'任职内设机构', field:'deptName', width:100}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#selectpost_wsid'
		}
	]);
	
	$(window).resize(function(){
		selectPostWorktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('#selectPostWorktop').outerHeight(true)
			- $('.modal-footer').outerHeight(true)
			- 260
			);//48(分页48)
	}).resize();

	$.copyProperties(selectPostWorktop.grid.store.baseParams, Widget.getContent().settings.params);

	selectPostWorktop.form.goQuery();

	$('button[button-click=add-selectpost]').click(function(){
		var bizPersonOid="<%=request.getParameter("bizPersonOid")%>";
		var selectRows = selectPostWorktop.grid.selectModel.getSelectRows();
		var postOid=selectRows[0].data.postOid;
		if (selectRows.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		if(selectRows.length > 1){
			MessageBox.alert('提示', '每次只能操作一条记录！');
			return;
		}
		$.ajax({
			url : "createBizPtPostInfoDis.do?method=createBizPtPostInfoDis&postOid="+postOid+"&bizPersonOid="+bizPersonOid,
			async : false,
			success : function(data) {
				try {
					data = $.parseJSON(data);

					if (data.success) {
						$.fn.close_popdown();
						var url_id=${param.id};
						var url_personoid=bizPersonOid;
						$('#'+url_id).load($('#'+url_id).attr('url'),{bizPersonOid:url_personoid,Id:url_id});
					}
					else
					{
						MessageBox.alert("提示",data.message);
					}
					return;
				} catch (e) {
				}
			}
		});
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
            <h4 class="modal-title">拟免岗位信息&gt;添加</h4>
    </div>
    
	    <!-- 查询条件-->
	  <div class="search" id="query-condition">
	  
	    <form id="selectpost_wsid" action="#" method="post">
	       	<!-- 列表内容展示-->
	    	<table class="x-table sortable ellipsis striped hover" id="selectPostGrid"></table>
	        </form>
	</div>
	<div style="clear: both"></div>
	<div class="modal-footer">
	<button type="button" class="btn btn-primary close-popdown" button-click="add-selectpost">保存</button>
	<button type="button" class="btn btn-default close-login close-popdown" button-click="widget-close">取消</button>
	</div>
  </div>	
</body>
</html>
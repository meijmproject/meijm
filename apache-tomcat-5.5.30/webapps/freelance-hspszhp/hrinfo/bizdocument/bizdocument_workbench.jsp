<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript">
	var worktop = null;
	$(document).ready(
		function() {
			var pageNo = '${param.pageNo}' == '' ? 1 : '${param.pageNo}';
			var limit = '${param.limit}' == '' ? 30 : '${param.limit}';
			worktop = new Worktop([ {
				xtype : 'Xtable',
				xname : 'grid',
				url: 'listBizDocument.do?method=listBizDocument',
				lengthMenu : [ 30, 50, 100 ],
				start : pageNo,
				iPageLength : limit,
				paginationType : 'input',
				el : '#verPersonId1',
				rowNumber : true,
				checkbox : true,
				singleCheck : false,
				columns : [ {
					header : '业务类型',
					field : 'refDesc',
					width : 80
				}, {
					header : '文档名称',
					field : 'fileName',
					width : 80
				}, {
					header : '上传者',
					field : 'createBy',
					width : 100
				}, {
					header : '上传时间',
					field : 'createDate',
					width : 100
				}]
			}, {
				xtype : 'QueryForm',//属性对应的构造函数
				xname : 'form',//属性名
				el : '#fr_wsid'
			}, {
				xtype: 'Toolbar',//属性对应的构造函数
		          xname: 'tbar',
		          tbar: '#fr_tbar',
		          fbar: '#fr_fbar',
		          buttons: {
		        	  'add':function(){
		        		 
		        			var params = {};
		        			params.maxFileSize = params.maxFileSize || 5*1024*1024;		// 字节 5MB
		        			params.noAcceptFileTypes = (params.noAcceptFileTypes == undefined || params.noAcceptFileTypes == null) ? "exe" : params.noAcceptFileTypes; // 默认为exe
		        			params.maxFileCounts = (params.maxFileCounts == undefined || params.maxFileCounts == null) ? 0 :  params.maxFileCounts;	//最多上传文件的个数（默认为0表示不作限制）
		        		  Widget.openContent("goCreateBizDocument.do?method=goCreateBizDocument",params,function(){
								 worktop.form.goQuery();
							 });
		        	  },
		        	  'delete':function(){
		        		    var rows = worktop.grid.selectModel.getSelectRows();
							var fileOids = new Array();

							for (var i = 0; i < rows.length; i++) {
								fileOids[fileOids.length] = rows[i].data.fileOid;
							}
							MessageBox.confirm('提示','是否删除', function (yes) {
								if (yes == "yes") {
									$.ajax({
										url: 'deleteUploadFile.do?method=deleteUploadFile&fileOid='+fileOids,
										dataType: 'json',
										error: function (x, t) {
											alert(t)
										},
										async: false,
										success: function (data) {
											if (data.success) {
												worktop.form.goQuery();
											} else {
												MessageBox.alert('提示', data.message, function () {
													Widget.close();
												});
											}
										}
									});
								}
							})
		        	  },
		        	  'delete-one':function(grid,record,worktop){
		        		    var rows = [record];
							var fileOid = rows[0].data.fileOid;

							MessageBox.confirm('提示','是否删除',function (yes) {
								if (yes == "yes") {
									$.ajax({
										url: 'deleteUploadFile.do?method=deleteUploadFile&fileOid='+fileOid,
										dataType: 'json',
										error: function (x, t) {
											alert(t)
										},
										async: false,
										success: function (data) {
											if (data.success) {
												worktop.form.goQuery();
											} else {
												MessageBox.alert('提示', data.message, function () {
													Widget.close();
												});
											}
										}
									});
								}
							worktop.form.goQuery();
							})
		        	  },
		        	  'view':function(grid,record,worktop){
							var rows = [record];
							var fileOid = rows[0].data.fileOid;
							MessageBox.openWindow("downloadUploadFile.do?method=downloadUploadFile&fileOid="+fileOid);
							worktop.form.goQuery();
						}
		        	  }
		          }
		        	  
       	  ]);
	
			
			$(window).resize(
					
					function() {
						worktop.grid.wrap.find('.ct').height(
								$(window).height()
							    -$(".current-position").outerHeight(true)
								- $(".attendance-org-workbench ").outerHeight(true)
								-$(".excels-tab").outerHeight(true)
								 - 48-25);//48(分页48)
								worktop.grid.wrap.find('.ct tbody').height(worktop.grid.wrap.find('.ct').height()-51);//48(分页48)
					}).resize();

			worktop.form.goQuery();

});
</script>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="search-content personBycp attendance-org-workbench">
	<!-- 查询条件-->
	<div class="search-include clearfix mrb-40 pd10-clear">
		<form id="fr_wsid" action="#" method="post">
		<dl class='search-unit'>
				<dt class="search-unit-dt">业务类型：</dt>
				<dd class="search-unit-dd">
						<select id="itemCode" name="itemCode">
		            	<option value="">--请选择--</option>
						<c:if test="${!empty list}">
							<c:forEach var="dto" items="${list}">
							<option value="${dto.refCode}">${dto.refDesc}</option>
							</c:forEach>
						</c:if>
					</select>
				</dd>
			</dl>
			<dl class='search-unit'>
				<dt class="search-unit-dt">文档名称：</dt>
				<dd class="search-unit-dd">
					<input id="fileName" class="modal_iput" name="fileName"/>
				</dd>
			</dl>
        <div class="search-unit search-btns" >
            <button class="btn-search btn-default">查询</button>
        </div>
        
		</form>
	</div>
	</div>
	<div  class='handle-btn-group'>
	    <!-- 操作按钮 -->
		<div class="handle-btn clearfix" id="fr_tbar">
			<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
			<button class="btn-delete btn-left-icon btn-default" button-click="delete">删除</button>
	    </div>
		<!-- 列表内容展示-->
		<table id="verPersonId1"
			class="x-table sortable ellipsis striped hover personBycp"></table>
	</div>
	<!--浮动操作列表-->
	<div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
    <button class="btn-look btn-left-icon btn-default" button-click="view">查看文档</button>
    <button class="btn-delete btn-left-icon btn-default" button-click="delete-one">删除</button>
	</div>
</body>
</html>
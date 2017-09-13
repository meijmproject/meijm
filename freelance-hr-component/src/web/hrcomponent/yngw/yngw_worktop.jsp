<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	page import="com.yh.hr.component.yngw.util.GwYnInfoConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>医院岗位管理列表</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="hrinfo/ver/unit/comm/pbyngwemployinfo/js/selectWorkTop.js"></script>
<script type="text/javascript">
var worktop = null;
$(document).ready(function(){
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listGwYnInfo.do?method=listGwYnInfo',
			rowNumber: true,
			checkbox: true,
			start : '${param.pageNo}'==''?1:'${param.pageNo}',
			iPageLength: '${param.limit}'==''?30:'${param.limit}',
			singleCheck: false,
			columns: [
				{header:'工作类别', field:'hisWorkType', width:80},
				{header:'岗位类别', field:'hisPositionType', width:80},
				{header:'岗位名称', field:'positionName', width:80},
				{header:'上级岗位名称', field:'parentPositionName', width:80},
				
				{header:'岗位职责', field:'hisPositionObligation', width:80},
				{header:'岗位任职条件', field:'hisPositionQualifications', width:80}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		},
		{
			xtype: 'Toolbar',//属性对应的构造函数
			xname: 'tbar',
			tbar: '#fr_tbar',
			fbar: '#fr_fbar',
			buttons: {
				'add': function(){
					 Widget.openContent("goToCreateGwYnInfoPage.do?method=goToCreateGwYnInfoPage",function(){
						 worktop.form.goQuery();
					 });
				},
				'update': function(grid,record,worktop){
					var positionOid = record.data.positionOid;
					Widget.openContent("goGwYnInfoUpdatePage.do?method=goGwYnInfoUpdatePage&positionOid="+positionOid,function(){
						worktop.form.goQuery();
				    });
					
				},
				'delete': function(grid,record,worktop){
					var rows = worktop.grid.selectModel.getSelectRows();
					var positionOids = new Array();
					if (rows.length == 0) {
						MessageBox.alert('提示', "请至少选择一条记录再操作");
					} else {
						var warning = '删除该岗位的同时会将下级所有岗位一并删除,确认删除吗？';

						for (var i = 0; i < rows.length; i++) {
							positionOids[positionOids.length] = rows[i].data.positionOid;
						}
						
						MessageBox.confirm('提示', warning, function (yes) {
							if (yes == "yes") {
								$.ajax({
									url: 'deleteGwYnInfo.do?method=delete&positionOids='+positionOids,
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
					}
				},
				'show': function(grid,record,worktop){
					var positionOid = record.data.positionOid;
					Widget.openContent("showGwYnInfoPage.do?method=show&positionOid="+positionOid,function(){
						worktop.form.goQuery();
				    });
				}
				
			} 
		}    
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.sitemap').outerHeight(true)
			- worktop.form.el.parent().outerHeight(true) //form 没有高度
			- (worktop.tbar.ct ? worktop.tbar.ct.outerHeight(true) : 0)
			- 48
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
});
</script>
<script type="text/javascript">
$(function(){
	$('#hisPositionName').click(function(){
	        if($('#hisWorkType').val()==null || $('#hisWorkTypeWorktop').val() ==""){
	            MessageBox.alert('提示', '请先选择工作类别!');
	            return;
	        }else if($('#hisPositionTypeWorktop').val()==null || $('#hisPositionTypeWorktop').val() ==""){
	        	MessageBox.alert('提示', '请先选择岗位类别!');
	            return;
	        }else{
	        	var hisWorkType = $('#hisWorkType option:selected').val();
	        	var hisPositionType = $('#hisPositionTypeWorktop option:selected').val();
	        	selectWorkTop.init($('#hisPositionName'),hisWorkType,hisPositionType); 
 	    }
	})
	//根据工作类别的选,加载岗位类别的下拉框
    $('#hisWorkTypeWorktop').change(function(){
//     	alert("1111");
    	//获取工作类别的选择
    	var hisWorkType = $('#hisWorkTypeWorktop option:selected').val();
    	
    	if(hisWorkType==""){
    		//如果为空,则为选择了"--请选择--"
    		$("#hisPositionTypeWorktop").empty();//清空选项
    		$("#hisPositionTypeWorktop").append("<option value=''>--请选择--</option>");
    		$("#positionNameDlWorktop").empty();//清空选项
    		$("#positionNameDlWorktop").append("<option value=''>--请选择--</option>");
    		
    		//加载所有的岗位类别
    		//加载所有的岗位名称(大类)
    		//根据工作类别的选择,获取需要加载到岗位类别的选择
        	$.ajax({
        	    url:'getSelectItems.do?method=resetSelectItems',
        	    type:'POST',
        	    data:{
        	    	hisWorkType:hisWorkType
        	    },
        	    dataType:'json',
        	    success:function(data){
        	    	//生成岗位类别的下拉框
        			$("#hisPositionTypeWorktop").empty();//清空选项
    				$("#hisPositionTypeWorktop").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.hisPositionTypeItems.length; i++){
        				$("#hisPositionTypeWorktop").append("<option value='"+data.hisPositionTypeItems[i].dicItemCode+"'>"+data.hisPositionTypeItems[i].dicItemName+"</option>");
        			}
        			//生成岗位名称(大类)的下拉框
        			$("#positionNameDlWorktop").empty();//清空选项
    				$("#positionNameDlWorktop").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.positionNameDl.length; i++){
        				$("#positionNameDlWorktop").append("<option value='"+data.positionNameDl[i].dicItemCode+"'>"+data.positionNameDl[i].dicItemName+"</option>");
        			}
        	    }
        	});
    	}else{
    		//根据工作类别的选择,获取需要加载到岗位类别的选择
        	$.ajax({
        	    url:'getSelectItems.do?method=getSelectItems',
        	    type:'POST',
        	    data:{
        	    	hisWorkType:hisWorkType
        	    },
        	    dataType:'json',
        	    success:function(data){
        	    	//生成岗位类别的下拉框
        			$("#hisPositionTypeWorktop").empty();//清空选项
    				$("#hisPositionTypeWorktop").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.hisPositionTypeItems.length; i++){
        				$("#hisPositionTypeWorktop").append("<option value='"+data.hisPositionTypeItems[i].dicItemCode+"'>"+data.hisPositionTypeItems[i].dicItemName+"</option>");
        			}
        			//生成岗位名称(大类)的下拉框
        			$("#positionNameDlWorktop").empty();//清空选项
    				$("#positionNameDlWorktop").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.positionNameDl.length; i++){
        				$("#positionNameDlWorktop").append("<option value='"+data.positionNameDl[i].dicItemCode+"'>"+data.positionNameDl[i].dicItemName+"</option>");
        			}
        	    }
        	});
    	}
    	
    });
	
  	//根据岗位类别的选择,加载岗位名称(大类)的下拉框
    $('#hisPositionTypeWorktop').change(function(){
    	//获取岗位类别的选择
    	var hisPositionType = $('#hisPositionTypeWorktop option:selected').val();
    	
    	if(hisPositionType==""){
    		$("#positionNameDlWorktop").empty();//清空选项
    		$("#positionNameDlWorktop").append("<option value=''>--请选择--</option>");
    	}else{
    		//根据岗位类别的选择,获取需要加载到岗位名称的选择
        	$.ajax({
        	    url:'getSelectItems.do?method=getSelectItems',
        	    type:'POST',
        	    data:{
        	    	hisPositionType:hisPositionType
        	    },
        	    dataType:'json',
        	    success:function(data){
        	    	//生成岗位名称的下拉框
        			$("#positionNameDlWorktop").empty();//清空选项
        			$("#positionNameDlWorktop").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.positionNameDl.length; i++){
        				$("#positionNameDlWorktop").append("<option value='"+data.positionNameDl[i].dicItemCode+"'>"+data.positionNameDl[i].dicItemName+"</option>");
        			}
        	    }
        	});
    	}
    });
});
</script>
</head>
<body style="overflow-x: hidden;">
<div class="current-position">
      当前位置：
        <span>基础信息管理 > </span>
        <span>岗位管理 > </span>
        <span>院内岗位管理 </span>
    <div style="clear: both"></div>
</div>
<div>
	<div class="search-content">
			<div class="search-include clearfix mrb-40">
				<form id="fr_wsid" action="#" method="post">
					<dl class='search-unit'>
						<dt class="search-unit-dt">工作类别：</dt>
						<dd class="search-unit-dd">
							<label> <dictionary:dicItemSelect dicTypeCode="<%=GwYnInfoConstants.YHRS0112%>" selected="${param.hisWorkType}" id="hisWorkType" name="hisWorkType"  emptyText="<%=Constant.EMPTY_SELECT %>"  /> </label>
						</dd>
					</dl>
					<dl class='search-unit'>
						<dt class="search-unit-dt">岗位类别：</dt>
						<dd class="search-unit-dd">
							<label> <dictionary:dicItemSelect dicTypeCode="<%=GwYnInfoConstants.YHRS0113%>" selected="${param.hisPositionType}" id="hisPositionTypeWorktop" name="hisPositionType" emptyText="<%=Constant.EMPTY_SELECT %>" /> </label>
						</dd>
					</dl>

					<dl class='search-unit'>
						<dt class="search-unit-dt">上级岗位名称：</dt>
						<dd class="search-unit-dd">
						
							<label> 
								 <input type="text" class="modal_iput" name="hisPositionName" id="hisPositionName" readonly="readonly" value="${param.parentPositionName}" />
                         		<input type="hidden" name="hisPositionOid"  id="hisPositionOid" value="${param.parentPositionOid}" />
                         		<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a> 
<!--                          		<label> <select id="parentPositionName" name="parentPositionOid" class="modal_select">
										<option value="">--请先选择岗位类别--</option>
								</select>  -->
							</label>
						</dd>
					</dl>
					<dl class='search-unit'>
						<dt class="search-unit-dt">岗位名称：</dt>
						<dd class="search-unit-dd">
							<label> <input type="text" name="positionName" value="${param.positionName}" /> </label>
						</dd>
					</dl>
					<dl class="search-unit search-btns">
						<dt>
							<button class="btn_search" button-click="query">查询</button>
						</dt>
					</dl>
				</form>
			</div>
		</div>
	<div class='handle-btn-group'>
		<!-- 操作按钮 -->
		<div class="handle-btn clearfix" id="fr_tbar">
			<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
			<button class="btn-delete btn-left-icon btn-default" button-click="delete">删除</button>
	    </div>
		<!-- 列表内容展示-->
		<table class="x-table sortable ellipsis striped hover"></table>
	</div>
	
	<!--浮动操作列表-->
	<div id="fr_fbar" style="display:none;" class="handel-float" >
	    <em></em>
	    <span></span>
	    <button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
	    <button class="btn-look btn-left-icon btn-default" button-click="show">查看</button>
	</div>
</div>
</body>
</html>
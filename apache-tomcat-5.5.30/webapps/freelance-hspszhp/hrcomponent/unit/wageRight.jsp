<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="dictionary.tld" prefix="dictionary" %>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>绩效工资总量申报设置页面</title>
<style type="text/css">
.gray td{
   background:#c6c6c6!important;
   border:1px;
}
</style>
<script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$(".nav_tab_li").click(function(){
		var pageNo = '${pageNo}';
		var url = $(".nav_tab_checked").attr('url');
		url = url + "&pageNo="+ pageNo; 
		$("#bizViewportFrameId").load(url); 
		$(".nav_tab_li").css("display","none");
	});
	
	
	var selectPersonWorktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listPtAnnualAmountInfo.do?method=listPtAnnualAmountInfo&batchNo=${batchNo}&taskOid=${taskOid}&unitOid=${unitOid}&itemCode=${itemCode}&taskItemCode=${taskItemCode}',
			el:'#selectPersonGrid',
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'年度', field:'year', width:100},
				{header:'单位名称', field:'unitName', width:150},
				{header:'编制数', field:'bianZhiNum', width:150},
				{header:'本年度年初人数', field:'earlyNumber', width:150},
				{header:'上年度平均在职人数', field:'lastYearAvgNumber', width:150},
				{header:'上年度绩效工资总量核定情况', field:'', width:150},
				{header:'基础性绩效工资总量', field:'bmYearAmount', width:150},
				{header:'奖励性绩效工资总量', field:'pmYearAmount', width:150},
				{header:'总量合计', field:'declarePmAmount', width:150},
				{header:'年人均水平', field:'reviewTypeDesc', width:350}
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
    //初核不同意
	$('[button-click=ver-Disagree]').click(function(g){
 		//得到所选列表
		var rows = selectPersonWorktop.grid.selectModel.getSelectRows();
        if(rows.length!=1){
        	MessageBox.alert('提示', '请选择一条记录！');
            return ;
        }else if (rows.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
        //得到选择的taskOid
        var unitOid = rows[0].data.unitOid;
        var unitName = rows[0].data.unitName;
   	    var taskOid = rows[0].data.taskOid;
   	    var batchNo = rows[0].data.batchNo;
   	 	var utUnitOid = rows[0].data.utUnitOid;
		var itemCode = rows[0].data.itemCode;
		var taskItemCode = rows[0].data.taskItemCode;
		//alert("单位="+taskItemCode+"==="+itemCode+"==="+utUnitOid);
		$.ajax({
	        url: "deleteAdBatchInfo.do?method=deleteAdBatchInfo&utUnitOid=" + utUnitOid + "&batchNo=" + batchNo+ "&taskOid=" + taskOid,
	        dataType : 'json',
	        type: 'post',
	        async: false,
	        success: function(data){
				if (data.success) {
					Widget.openContent('goBizDefaultCallBackAgreeUnit.do?method=goBizDefaultCheckAgreeUnit&unitNames='+unitName+'&bizTaskOids='+taskOid+'&itemCodes='+itemCode+'&itemNodeCodes='+taskItemCode,null,{width:null});
					}
	            else
	            {
	            	MessageBox.message('提示', data.message);
	            }
				}

	    });	
/*    	 	
	     Widget.openContent('goToViewWageSyAchievementsTotalTask.do?method=goToViewWageSyAchievementsTotalTask&utUnitOid='+utUnitOid+'&taskOid='+taskOid+'&batchNo='+batchNo,function(){
				selectPersonWorktop.form.goQuery();
		 });  */
	});	
    //查看
	$('[button-click=ver-view]').click(function(g){
 		//得到所选列表
		var rows = selectPersonWorktop.grid.selectModel.getSelectRows();
        if(rows.length!=1){
        	MessageBox.alert('提示', '请选择一条记录！');
            return ;
        }else if (rows.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
        //得到选择的taskOid
        var unitOid = rows[0].data.unitOid;
   	    var taskOid = rows[0].data.taskOid;
   	    var batchNo = rows[0].data.batchNo;
   	 	var utUnitOid = rows[0].data.utUnitOid;
   	 	//alert(unitOid);
	     Widget.openContent('goToViewWageSyAchievementsTotalTask.do?method=goToViewWageSyAchievementsTotalTask&unitOid='+unitOid+'&taskOid='+taskOid+'&batchNo='+batchNo,function(){
				selectPersonWorktop.form.goQuery();
		 }); 
	});
	
	$.copyProperties(selectPersonWorktop.grid.store.baseParams, Widget.getContent().settings.params);

	selectPersonWorktop.form.goQuery();
} );
</script>
</head>
<body>
	    <!-- 查询条件-->
	  <div class="search" id="query-condition">
	    <form id="selectperson_wsid" action="#" method="post">
	        <dl class="search-horizontal">
	            <dt>单位名称：</dt>
	            <dd><label><input type="text" name="earlyNumber" /></label></dd>
	        </dl>
	        <dl class="search-horizontal">
	         <dt>年度：</dt>
	            <dd><label><input type="text" name="year" /></label></dd>
	        </dl>
	        
	         <button style="float: left;" class="btn_search" >查询</button>
	         
	       
        	<div style="clear: both"></div>
        	
       <!--操作按钮-->
       <div class="handel" id="handel-button">
	        <button class="btn_back" button-click="ver-Disagree">初核不同意</button>
	         <button class="btn_check" button-click="ver-view">查看业务信息</button>
	   </div>
        	
	  	<!-- 列表内容展示-->
	    <table class="x-table sortable ellipsis striped hover" id="selectPersonGrid"></table>
	   
	    </form>
	</div>
	<div style="clear: both"></div>
	
</body>
</html>


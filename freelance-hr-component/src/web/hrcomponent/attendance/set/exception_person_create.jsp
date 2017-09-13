<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!--
 * @function    非考勤人员设置页面
 * @page name   hrcomponent/attendance/set/exception_person_create.jsp
 * @author      duxw
 * @date        2017/04/26
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>非考勤人员设置页面</title>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="hrcomponent/attendance/set/js/check_exception_person.js"></script>

<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	$(".btn-return").click(function(){
      HistoryRegister.go("backToExceptionEmployeeConfigPage");
    });
	
	var pageNo = '${param.pageNo}'==''?1:'${param.pageNo}';
	var limit = '${param.limit}'==''?30:'${param.limit}';
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listAttendancePerson.do?method=listAttendancePerson&unitOid=${unitOid}',
			lengthMenu:[30,50,100],
			start : pageNo,
			iPageLength: limit,
			paginationType: 'input',
			el:'#currentPersonList',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'工号', field:'personCode', width:80},
				{header:'姓名', field:'name', width:80},
				{header:'性别', field:'sexCodeStr', width:20},
				{header:'证件类型', field:'idCodeStr', width:80},
				{header:'证件号码', field:'idNo', width:80},
				{header:'科室', field:'hireDeptName', width:80},
				{header:'岗位', field:'hisPositionName', width:80}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		}, {
			xtype: 'Toolbar',//属性对应的构造函数
	          xname: 'tbar',
	          tbar: '#handel-button',
	          buttons: {
					'atn_create_save':function(){
						var selectRow = worktop.grid.selectModel.getSelectRows();
        				var personOids = new  Array();
        				var applyNames = new Array();
        				if (selectRow.length == 0) {
        					MessageBox.alert('提示', '请至少选择一条记录！');
        					return;
        				}
        				for(i=0;i<selectRow.length;i++)
        				{
        					personOids[i] = selectRow[i].data.personOid;
        					applyNames[i] = selectRow[i].data.name;
        				}
       					MessageBox.yes('提示','请确认是否将选中的人员添加到非考勤人员列表中？',function() {
       						$.post("createExceptionEmployee.do?method=createExceptionEmployee&personOids="+personOids,{applyNames:applyNames.join(',')}, 
       						function(data){
       							if (data.message) 
       							{
       								MessageBox.message('提示', data.message,function()
   	    									{
   	    										worktop.form.goQuery();
   	    									}		
   	    								);
       							}
       						},'json');
       					});
					}
       	  	}
         }
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
//			- $('.sitemap').outerHeight(true)
			- $('.current-position').outerHeight(true)
			- $(".search-content").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			-80
			);//48(分页48)
			worktop.grid.wrap.find('.ct tbody').height(
					$(window).height() 
//					- $('.sitemap').outerHeight(true)
					- $('.current-position').outerHeight(true)
					- $(".search-content").outerHeight(true)
					- $("#handel-button").outerHeight(true)
					-80-50
					);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
})
</script>
</head>
<body style="overflow: hidden;">
	<div class="current-position">
		当前位置：
        <span>人事信息管理 > </span>
        <span>考勤管理 > </span>
        <span>考勤参数设置 >  </span>
        <span>非考勤人员设置 >  </span>
        <span>新增</span>
        <button class="btn-return"> &lt; 返回</button>
	    <div style="clear: both"></div>
	</div>
	<!--右边整体-->
	<div id="right" class="personBycp">

	    <!-- 查询条件-->
	    <div class="search-content">
				<div class="search-include clearfix mrb-40">
					<form id="fr_wsid" action="#" method="post">
						<dl class='search-unit'>
							<dt class="search-unit-dt">科室：</dt>
							<dd class="search-unit-dd">
								<label> 
									<input type="text" class="modal_iput" name="hireDeptName" id="hireDeptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,${unitOid},true,null,null,'true')" value="" /> 
									<input type="hidden" id="hiddenHireDeptOid" name="hireDeptOid" value="" /> 
								</label>
							</dd>
						</dl>
						<dl class='search-unit'>
							<dt class="search-unit-dt">姓名：</dt>
							<dd class="search-unit-dd">
								<label> 
									<input type="text" class="modal_iput" name="name" id="name" /> 
								</label>
							</dd>
						</dl>
						<dl class="search-unit search-btns">
							<dt>
								<button class="btn_search">查询</button>
							</dt>
						</dl>
					</form>
					<div style="clear: both"></div>
				</div>
			</div>
		<div class='handle-btn-group'>
			<!-- 操作按钮-->
			<div class="handle-btn clearfix" id="handel-button">
				<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="atn_create_save">确定</button>
			</div>
			<!-- 列表内容展示-->
			<table id="currentPersonList" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
	</div>
</body>

</html>
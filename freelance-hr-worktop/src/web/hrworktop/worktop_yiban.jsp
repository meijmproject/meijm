<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE>已办</TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <%@ include file="/include/jsp_headers.jsp"%>
<script type="text/javascript" src="${dto.workBenchTbar}"></script><!-- 单位通用查询Grid -->
<script type="text/javascript" src="${dto.workBenchForm}"></script><!-- 单位业务岗工具条js -->
<script type="text/javascript" src="${dto.workBenchGrid}"></script><!-- 业务事项工作台js -->
 </HEAD>
<BODY>
</BODY>
<script type="text/javascript">
//所有对象都应放在worktop中，应尽量避免使用全局变量、全局函数
var worktop = null;
var itemNodeCode = "/${itemNodeCode}";
$(document).ready(function() {
	//按初始化级别顺序定义
	worktop = new Worktop([
		{
			wrap: '#bizViewportFrameId',
			xtype: 'WorkBenchForm',//属性对应的构造函数
			xname: 'form',//属性名
			start: '${param.pageNo}'==''?1:'${param.pageNo}',
			dbflag: '${param.dbflag}',
			menuCode : '${param.menuCode}'
		},
		{
			wrap: '#bizViewportFrameId',
			xtype:'WorkBenchGrid',
			xname:'grid',
			url: 'listJhcBtTaskItem.do?method=listJhcBtTaskItem&taskItemCode=${itemNodeCode}&menuCode=${menuCode}&taskItemStatus=2',
			dbflag: '${param.dbflag}'
		},
		{
			wrap: '#bizViewportFrameId',
			xtype: 'WorkBenchTbar',//属性对应的构造函数
			xname: 'tbar',
			dbflag: '${param.dbflag}'
		}
	]);
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
				$('#bizViewportFrameId').height() 
			- worktop.form.ct.outerHeight(true)
			- (worktop.tbar.ct ? worktop.tbar.ct.outerHeight(true) : 0)
			- 48 
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
} );
</script>
</HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位说明书列表页</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="hrcomponent/gb/gbdescription/js/gbdescription_add.js"></script>
<script type="text/javascript">
var worktop = null;
$(document).ready(function() {
	$('#postType').change(function(){
		var postTypeVal = $('#postType option:selected').val();
		if(postTypeVal==1){
			$("#postLevel").empty();//清空选项
    		$("#postLevel").append("<option value=''>--请选择--</option>");
    		$("#postLevel").append('<option value="A1010">管理类</option>');
    		$("#postLevel").append('<option value="A1010010">一级</option>');
    		$("#postLevel").append('<option value="A1010020">二级</option>');
    		$("#postLevel").append('<option value="A1010040">三级</option>');
    		$("#postLevel").append('<option value="A1010050">四级</option>');
    		$("#postLevel").append('<option value="A1010060">五级</option>');
    		$("#postLevel").append('<option value="A1010070">六级</option>');
    		$("#postLevel").append('<option value="A1010080">七级</option>');
    		$("#postLevel").append('<option value="A1010090">八级</option>');
    		$("#postLevel").append('<option value="A1010100">九级</option>');
    		$("#postLevel").append('<option value="A1010110">十级</option>');
    		$("#postLevel").append('<option value="A1010190">未定级</option>');
		}else if(postTypeVal==2){
			$("#postLevel").empty();//清空选项
    		$("#postLevel").append("<option value=''>--请选择--</option>");
    		$("#postLevel").append('<option value="A1020">专业技术类</option>');
    		$("#postLevel").append('<option value="A1020010">高级</option>');
    		$("#postLevel").append('<option value="A1020010010">正高级</option>');
    		$("#postLevel").append('<option value="A1020010010010">一级</option>');
    		$("#postLevel").append('<option value="A1020010010020">二级</option>');
    		$("#postLevel").append('<option value="A1020010010030">三级</option>');
    		$("#postLevel").append('<option value="A1020010010040">四级</option>');
    		$("#postLevel").append('<option value="A1020010020">副高级</option>');
    		$("#postLevel").append('<option value="A1020010020010">五级</option>');
    		$("#postLevel").append('<option value="A1020010020020">六级</option>');
    		$("#postLevel").append('<option value="A1020010020030">七级</option>');
    		$("#postLevel").append('<option value="A1020020">中级</option>');
    		$("#postLevel").append('<option value="A1020020010">八级</option>');
    		$("#postLevel").append('<option value="A1020020020">九级</option>');
    		$("#postLevel").append('<option value="A1020020030">十级</option>');
    		$("#postLevel").append('<option value="A1020030">初级</option>');
    		$("#postLevel").append('<option value="A1020030010">十一级</option>');
    		$("#postLevel").append('<option value="A1020030020">十二级</option>');
    		$("#postLevel").append('<option value="A1020030030">十三级</option>');
    		$("#postLevel").append('<option value="A1020090">未定级</option>');
		}else if(postTypeVal==3){
			$("#postLevel").empty();//清空选项
    		$("#postLevel").append("<option value=''>--请选择--</option>");
    		$("#postLevel").append('<option value="A1030">工勤技能类</option>');
    		$("#postLevel").append('<option value="A1030010">技工</option>');
    		$("#postLevel").append('<option value="A1030010010">一级</option>');
    		$("#postLevel").append('<option value="A1030010020">二级</option>');
    		$("#postLevel").append('<option value="A1030010030">三级</option>');
    		$("#postLevel").append('<option value="A1030010040">四级</option>');
    		$("#postLevel").append('<option value="A1030010050">五级</option>');
    		$("#postLevel").append('<option value="A1030020">普工</option>');
    		$("#postLevel").append('<option value="A1030090">未定级</option>');
		}else{
			$("#postLevel").empty();//清空选项
    		$("#postLevel").append("<option value=''>--请选择--</option>");
    		$("#postLevel").append('<option value="A1010">管理类</option>');
    		$("#postLevel").append('<option value="A1010010">一级</option>');
    		$("#postLevel").append('<option value="A1010020">二级</option>');
    		$("#postLevel").append('<option value="A1010040">三级</option>');
    		$("#postLevel").append('<option value="A1010050">四级</option>');
    		$("#postLevel").append('<option value="A1010060">五级</option>');
    		$("#postLevel").append('<option value="A1010070">六级</option>');
    		$("#postLevel").append('<option value="A1010080">七级</option>');
    		$("#postLevel").append('<option value="A1010090">八级</option>');
    		$("#postLevel").append('<option value="A1010100">九级</option>');
    		$("#postLevel").append('<option value="A1010110">十级</option>');
    		$("#postLevel").append('<option value="A1010190">未定级</option>');
    		$("#postLevel").append('<option value="A1020">专业技术类</option>');
    		$("#postLevel").append('<option value="A1020010">高级</option>');
    		$("#postLevel").append('<option value="A1020010010">正高级</option>');
    		$("#postLevel").append('<option value="A1020010010010">一级</option>');
    		$("#postLevel").append('<option value="A1020010010020">二级</option>');
    		$("#postLevel").append('<option value="A1020010010030">三级</option>');
    		$("#postLevel").append('<option value="A1020010010040">四级</option>');
    		$("#postLevel").append('<option value="A1020010020">副高级</option>');
    		$("#postLevel").append('<option value="A1020010020010">五级</option>');
    		$("#postLevel").append('<option value="A1020010020020">六级</option>');
    		$("#postLevel").append('<option value="A1020010020030">七级</option>');
    		$("#postLevel").append('<option value="A1020020">中级</option>');
    		$("#postLevel").append('<option value="A1020020010">八级</option>');
    		$("#postLevel").append('<option value="A1020020020">九级</option>');
    		$("#postLevel").append('<option value="A1020020030">十级</option>');
    		$("#postLevel").append('<option value="A1020030">初级</option>');
    		$("#postLevel").append('<option value="A1020030010">十一级</option>');
    		$("#postLevel").append('<option value="A1020030020">十二级</option>');
    		$("#postLevel").append('<option value="A1020030030">十三级</option>');
    		$("#postLevel").append('<option value="A1020090">未定级</option>');
    		$("#postLevel").append('<option value="A1030">工勤技能类</option>');
    		$("#postLevel").append('<option value="A1030010">技工</option>');
    		$("#postLevel").append('<option value="A1030010010">一级</option>');
    		$("#postLevel").append('<option value="A1030010020">二级</option>');
    		$("#postLevel").append('<option value="A1030010030">三级</option>');
    		$("#postLevel").append('<option value="A1030010040">四级</option>');
    		$("#postLevel").append('<option value="A1030010050">五级</option>');
    		$("#postLevel").append('<option value="A1030020">普工</option>');
    		$("#postLevel").append('<option value="A1030090">未定级</option>');
		}
	})
	
	
	
	var pageNo = '${param.pageNo}'==''?1:'${param.pageNo}';
	var limit = '${param.limit}'==''?30:'${param.limit}';
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listGbDescription.do?method=listGbDescription',
			lengthMenu:[30,50,100],
			start : pageNo,
			iPageLength: limit,
			paginationType: 'input',
			el:'#verPersonId',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'单位名称', field:'unitName', width:80},
				{header:'岗位类别', field:'postTypeName', width:80},
				{header:'岗位级别', field:'postLevelName', width:100},
				{header:'岗位名称', field:'postName', width:100},
				{header:'岗位聘用条件', field:'postCondition', width:80},
				{header:'岗位职责', field:'postDuty', width:100},
				{header:'岗位目标任务', field:'postAssigment', width:100},
				{header:'岗位绩效考核标准', field:'assessmentCriteria', width:100}
			]
		},
		{
			xtype: 'QueryForm',//属性对应的构造函数
			xname: 'form',//属性名
			el:'#fr_wsid'
		}
	]);
	
	var tabInfoTips = $("#tabInfoTips").remove();
	worktop.grid.on('draw', function(){
		
		tabInfoTips.remove();
		
		tabInfoTips.click(function(){
			tabInfoTips.hide();
		});
		tabInfoTips.find("button[opt=update]").click(function(){
			var rowIndex = tabInfoTips.attr('rowIndex');
			var rows = worktop.grid.getRecordAt(rowIndex);
			var jhgGbDescriptionOid = rows.data.jhgGbDescriptionOid;
			Widget.openContent('goToUpdateGbDescription.do?method=goToUpdateGbDescription&jhgGbDescriptionOid='+jhgGbDescriptionOid);

		});
		tabInfoTips.find("button[opt=view]").click(function(){
			var rowIndex = tabInfoTips.attr('rowIndex');
			var rows = worktop.grid.getRecordAt(rowIndex);
			var jhgGbDescriptionOid = rows.data.jhgGbDescriptionOid;
			Widget.openContent('viewGbDescription.do?method=goToUpdateGbDescription&jhgGbDescriptionOid='+jhgGbDescriptionOid);

	});
		worktop.grid.tbody.find("tr").click(function(event){
				if($(this).next().attr("id")=="trIndex"){
				    if(tabInfoTips.is(":hidden")){
						 tabInfoTips.show();
					  }else{
						  tabInfoTips.hide();
					} 
				}else{
					tabInfoTips.detach();
					$("#trIndex").remove();
					$(this).after('<tr id="trIndex"></tr>');
					$("#trIndex").append(tabInfoTips);
					tabInfoTips.show();
				}
				var rowIndex = $(this).attr('rowIndex');
				var record = worktop.grid.getRecordAt(rowIndex);
				tabInfoTips.attr('rowIndex', rowIndex);
				tabInfoTips.attr('jhgGbDescriptionOid', record.data.jhgGbDescriptionOid);
				event.stopPropagation(); //停止冒泡到rowclick

		});
	}, worktop.grid);
	
	$(document.body).click(function(){
		$("#tabInfoTips").hide();
	});
	
	$(window).resize(function(){
		worktop.grid.wrap.find('.ct').height(
			$(window).height() 
			- $('.current-position').outerHeight(true)
			- $(".c-nav-tab").outerHeight(true)
			- $(".search-content").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			- 60
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
	$('[button-click=add]').click(function(){
		Widget.openContent('goToAddGbDescription.do?method=goToAddGbDescription')
	});
	 $('[button-click=delete]').click(function(){
		 	var selectRow = worktop.grid.selectModel.getSelectRows();
			if (selectRow.length == 0) {
				MessageBox.alert('提示', '请至少选择一条记录！');
				return;
			}
			MessageBox.yes('提示','请确认是否删除记录？',function() {
				var jhgGbDescriptionOids = new  Array();
				for(i=0;i<selectRow.length;i++)
				{
					jhgGbDescriptionOids[i] = selectRow[i].data.jhgGbDescriptionOid;
				}
				
				$.post("deleteGbDescription.do?method=deleteGbDescription",{jhgGbDescriptionOids:jhgGbDescriptionOids.join(',')},		
				function(){
							worktop.form.goQuery();
				},'json');
			});
    }); 


})

</script>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="personBycp">

		    <!-- 查询条件-->
	     <div class="search-content">
		<div class="search-include mrb-40 clearfix">
			<form id="fr_wsid" action="#" method="post">
				<dl class='search-unit' >
					<dt class="search-unit-dt">岗位类别：</dt>
					<dd class="search-unit-dd">
							<label> <dictionary:dicItemSelect id="postType" dicTypeCode="<%=DicConstants.YHRS0022%>" selected="${postType}" name="postType" emptyText="--请选择--" /> </label>
					</dd>
				</dl>
				<dl class='search-unit' >
					<dt class="search-unit-dt">岗位等级：</dt>
					<dd class="search-unit-dd">
						<label> <dictionary:dicItemSelect id="postLevel" dicTypeCode="<%=DicConstants.YHRS0023%>" selected="${postLevel}" name="postLevel" emptyText="--请选择--" /> </label>
					</dd>
				</dl>
				<dl class='search-unit'>
					<dt class="search-unit-dt">岗位名称：</dt>
					<dd class="search-unit-dd">
						<label>
							<input type="text" name="postName" id="postName"/>
						</label>
					</dd>
				</dl>
				
				 <dl class="search-unit search-btns">
				 <dt><button class="btn_search">查询</button></dt>
				  </dl>
			</form>
		</div>
		</div>
		
		<div class='handle-btn-group'>
			<!-- 操作按钮-->
			<div class="handle-btn clearfix" id="handel-button">
				<button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
				<button class="btn-delete btn-left-icon btn-default" button-click="delete" >删除</button>
			</div>
			<!-- 列表内容展示-->
			<table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
	</div>
	<!--浮动操作列表-->
	<div id="tabInfoTips" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
    <button class="btn-modify btn-left-icon btn-default" opt="update">修改</button>
    <button class="btn-look btn-left-icon btn-default" opt="view">查看</button>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>单位人员信息维护列表页</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css"/>
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<style>
.acss{
float: left;
    font-size: 14px;
    color: #fff;
    margin: 15px 10px;
    height: 26px;
    padding-left: 20px;
    padding-right: 10px;
    line-height: 26px;
}
/* .personBycp .page_foot{
	position:fixed;
}
 */
</style>
<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script>
<script type="text/javascript">
var orgOid="${orgOid}";
var organizationOid="${organizationOid}";

var worktop = null;
$(document).ready(function() {
	var pageNo = '${param.pageNo}'==''?1:'${param.pageNo}';
	var limit = '${param.limit}'==''?30:'${param.limit}';
	worktop = new Worktop([
		{
			xtype:'Xtable',
			xname:'grid',
			url: 'listProtectPerson.do?method=listProtectPerson&unitOid='+unitOid+'&orgOid='+orgOid+'&organizationOid='+organizationOid,
			lengthMenu:[30,50,100],
			start : pageNo,
			iPageLength: limit,
			paginationType: 'input',
			el:'#verPersonId',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
                {header:'工号', field:'personCode', width:80},
				{header:'姓名', field:'name', width:80},
				{header:'人员类别', field:'personTypeDesc', width:80},
				{header:'人员状态', field:'personStatusDesc', width:80},
				{header:'所在科室', field:'hireDeptName', width:100},
				{header:'身份证号码', field:'idNo', width:120},
				{header:'性别', field:'sexCodeDesc', width:100},
				{header:'婚姻状况', field:'marriageStatusCodeDesc', width:100},
				{header:'出生日期', field:'birthday', width:100, tip: false},
				{header:'籍贯', field:'ancestorPlaceCodeDesc', width:100},
				{header:'出生地', field:'birthplaceCodeDesc', width:100},
				{header:'户口所在地', field:'hukouPlaceDesc', width:100},
				{header:'是否本地户口', field:'isSzDesc', width:100},
				{header:'政治面貌', field:'politicStatusCodeDesc', width:100},
				{header:'进入本单位时间', field:'entryCurrentUnitDate', width:100,tip: false},
				{header:'全日制最高学校名称', field:'ftSchoolName', width:100},
				{header:'全日制最高专业名称', field:'ftMajorName', width:100},
				{header:'全日制最高学历', field:'ftEducationLevelCodeDesc', width:100},
				{header:'全日制最高学位', field:'ftDegreeCodeDesc', width:100},
				{header:'在职最高学校名称', field:'ojSchoolName', width:100},
				{header:'在职最高专业名称', field:'ojMajorName', width:100},
				{header:'在职最高学历', field:'ojEducationLevelCodeDesc', width:100},
				{header:'在职最高学位', field:'ojDegreeCodeDesc', width:100},
				{header:'最高专业资格名称', field:'profTechNameDesc', width:100},
				{header:'最高专业资格获取时间', field:'profProcureDate', width:100},
				{header:'执业证编号', field:'certificateNo', width:100},
				{header:'执（职）业资格名称', field:'qualificationsNameDesc', width:100,tip: false},
				{header:'所聘院内岗位', field:'hisPositionName', width:100},
				{header:'护士层级', field:'levelCodeDesc', width:100, tip: false}
			]
		},
		{
			 xtype: 'SearcherForm',//属性对应的构造函数
		     xname: 'form',//属性名
		     el:'#verperson_wsid',
		     conditionUrl: 'listInfoCondition.do?method=listInfoCondition&functionCode=personWorkBench',
		     defaultConditionCodeArray: ['personCode','name','personStatus'],
		     selectOrg: {
                unitOid: unitOid
             }
		}
	]);
	
	var tabInfoTips = $("#tabInfoTips").remove();
	worktop.grid.on('draw', function(){
		
		tabInfoTips.remove();
		
		tabInfoTips.click(function(){
			tabInfoTips.hide();
		});
		tabInfoTips.find("button[opt=update]").click(function(){
			var personOid = tabInfoTips.attr('personOid');
			var unitOid = tabInfoTips.attr('unitOid');
			
			var rowIndex = tabInfoTips.attr('rowIndex');
			var record = worktop.grid.getRecordAt(rowIndex);
			if (personOid&&unitOid) {
				var values = worktop.form.getValues();
				var pageNo={};
				var start = {};
				pageNo.name='pageNo';
				pageNo.value=worktop.grid.page.page+1;
				start.name='limit';
				start.value=worktop.grid.page.limit;
				values.push(pageNo);
				values.push(start);
				var params = {};
				$.each(values, function() {
					params[this.name] = this.value;
				});
				HistoryRegister.set("goBackUrl","goProtectPbPersonWorkbench.do?method=goProtectPbPersonWorkbench",values);
				params.index=parseInt($(this).parent().parent().prev().attr("rowIndex"))+1;
				params.pageNo=worktop.grid.page.page+1;
				params.pageSize=worktop.grid.page.limit;
				params.total=worktop.grid.page.total;
				params.functionCode='protectPersonUpdate';
				params.orgOid=orgOid;
				location.href = "goProtectPersonUpdate.do?method=goToViewVerPersonPage&personOid="+personOid+"&orgOid="+orgOid+"&condition="+JSON.stringify(params);
			}
		});
		tabInfoTips.find("button[opt=view]").click(function(){
			var personOid = tabInfoTips.attr('personOid');
			var unitOid = tabInfoTips.attr('unitOid');
			var rowIndex = tabInfoTips.attr('rowIndex');
			var record = worktop.grid.getRecordAt(rowIndex);
			if (personOid&&unitOid) {
				var values = worktop.form.getValues();
				var pageNo={};
				var start = {};
				pageNo.name='pageNo';
				pageNo.value=worktop.grid.page.page+1;
				start.name='limit';
				start.value=worktop.grid.page.limit;
				values.push(pageNo);
				values.push(start);
				var params = {};
				$.each(values, function() {
					params[this.name] = this.value;
				});
				HistoryRegister.set("goBackUrl","goProtectPbPersonWorkbench.do?method=goProtectPbPersonWorkbench",values);
				params.index=parseInt($(this).parent().parent().prev().attr("rowIndex"))+1;
				params.pageNo=worktop.grid.page.page+1;
				params.pageSize=worktop.grid.page.limit;
				params.total=worktop.grid.page.total;
				params.functionCode='protectPersonView';
				params.orgOid=orgOid;
				location.href = "goProtectPersonView.do?method=goToViewVerPersonPage&personOid="+personOid+"&condition="+JSON.stringify(params);
			}
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
				tabInfoTips.attr('personOid', record.data.personOid);
				tabInfoTips.attr('unitOid', record.data.unitOid); 
					
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
			- $("#query-condition").outerHeight(true)
			- $("#nav_tab").outerHeight(true)
			- $("#handel-button").outerHeight(true)
			- 62
			);//48(分页48)
	}).resize();
	
	worktop.form.goQuery();
	//人员新增
	$('[button-click=ver-add]').click(function(){
		/* var unitOid= $('#unitOid').val();
		if(unitOid==null||unitOid==''){
			MessageBox.alert('提示',"请先选择单位再进行操作");
		}
		else{
			Widget.openContent('goToAddVerPersonPage.do?method=goToAddVerPersonPage&unitOid='+unitOid,function(){
				worktop.form.goQuery();
			})
		} */
		Widget.openContent('goToAddVerPersonPage.do?method=goToAddVerPersonPage&unitOid='+unitOid,function(){
			worktop.form.goQuery();
		})
	})
	$('[button-click=infoSetting]').click(function(){
		if($('.mt-info-set').length==0){
			var settingCondition = new SettingCondition(worktop);
			$('.mt-info-set').slideDown(0);
			
		}else{
			if($('.mt-info-set').css('display')=='block'){
				$('.mt-info-set').slideUp(0);
	        }else{
	        	$('.mt-info-set').slideDown(0);
	        }
		  }
	})

	$('[button-click=ver-delete]').click(function(){
		var rows = worktop.grid.selectModel.getSelectRows();
   	 var personOids = new Array();
   	 for (var i=0; i<rows.length; i++) {
			personOids[personOids.length] = rows[i].data.personOid;
   	 }
		if(rows.length==0) {
			MessageBox.alert('提示',"请至少选择一条记录再操作");
		}else {
			MessageBox.confirm('提示', '确认删除？',function (yes){
			    if(yes=="yes"){
           $.ajax({
           		url : 'deleteVerPersonInfo.do?method=deleteVerPersonInfo&personOids='+personOids,
                dataType : 'json',
                error : function(x,t) {
                	alert(t)
                },
                async : false,
                success : function(data) {
                    if (data.success) {
                    	worktop.form.goQuery();
                    }
                    else
                    {
                    	MessageBox.alert('提示',data.message, function() {
                			Widget.close();
        			});
                    }
                }
           	}); 
			    }
			})
		}
	})
	 $('[button-click=ver-printPersonInfo]').click(function(){
		var rows = worktop.grid.selectModel.getSelectRows();
		var personOids = new Array();
	   	 for (var i=0; i<rows.length; i++) {
				personOids[personOids.length] = rows[i].data.personOid;
	   	 }
			if(rows.length==0) {
				MessageBox.alert('提示',"请至少选择一条记录再操作");
			}else {
					/* Widget.openContent('goToExportVerPersonInfo.do?method=goToAddVerPersonPage',function(){
						worktop.form.goQuery(); */
						window.open("printPublicInfo.do?method=printOfficeInfo&personOids="+personOids,"","height=600, width=700, top="+(window.screen.height-500)/2+", left="+(window.screen.width-800)/2+", toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
			}
    }); 
	 $('[button-click=ver-printPersonSkillInfo]').click(function(){
			var rows = worktop.grid.selectModel.getSelectRows();
			var personOids = new Array();
		   	 for (var i=0; i<rows.length; i++) {
					personOids[personOids.length] = rows[i].data.personOid;
		   	 }
				if(rows.length==0) {
					MessageBox.alert('提示',"请至少选择一条记录再操作");
				}else {
						/* Widget.openContent('goToExportVerPersonInfo.do?method=goToAddVerPersonPage',function(){
							worktop.form.goQuery(); */
							window.open("printPublicInfo.do?method=printPublicInfo&personOids="+personOids,"","height=600, width=700, top="+(window.screen.height-500)/2+", left="+(window.screen.width-800)/2+", toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
				}
	});
	 $('[button-click=ver-images]').on('click',function(){
			var rows = worktop.grid.selectModel.getSelectRows();
			if(rows.length==0) {
				MessageBox.alert('提示',"请至少选择一条记录再操作");
			}else if(rows.length>1){
				MessageBox.alert('提示',"只能选择一条记录操作");
			}else{
				var personOid = rows[0].data.personOid;
				$.post("listPersonPhoto.do?method=listPersonPhoto&personOid="+personOid,function(data){
					if(data.list.length==0){
						MessageBox.alert('提示',"该人员暂无影像集");
					}else{
						var list = data.list;
						var photos = []
						for(var i=0;i<list.length;i++){
							var photo = {};
							photo.imgTitle = list[i].photoName;
							photo.imgUrl = "img/sherenfs/common/yh/file/affiche/"+list[i].photoCode;
							photos.push(photo);
						}
						$.albumUi(photos);
					}
				},'json');
			}
	    });
	
	 $('[button-click=ver-export]').click(function(){
		 var rows = worktop.grid.selectModel.getSelectRows();
			var personOids = new Array();
		   	 for (var i=0; i<rows.length; i++) {
					personOids[personOids.length] = rows[i].data.personOid;
		   	 }

		   	 var urlParams = '';
		   	 worktop.form.getValues().forEach(function(v,i) {
			     urlParams += ('&'+v.name+'='+v.value);
			   });
				/* if(rows.length==0) {
					MessageBox.alert('提示',"请至少选择一条记录再操作");
				}else { */
						
				//}
				location.href="printUnitInfoByExcel.do?method=printPersonInfoByExcel"+urlParams+"&orgOid="+orgOid+'&personOids='+personOids;
	});
	 hideExportBtn($('#export'));
});

function goVerUnit(){
	var values = worktop.form.getValues();
	//var unitOid= $('#unitName option:selected').val();
	var unitOid=$('#unitOid').val();
	HistoryRegister.set("goBackUrl","goVerPbPersonWorkbench.do?method=goVerPbPersonWorkbench",values);
	location.href = "goUnitInfoPage.do?method=goUnitInfoPage&unitOid="+unitOid;
}
</script>
</head>
<body style="overflow: hidden;">
	<!--右边整体-->
	<div id="right" class="personBycp">
	    <!-- 已办/待办事项选项卡-->
	    <ul class="c-nav-tab" id="nav_tab">
	        <li><a href="javascript:;">单位人员查询</a></li>
	        
	        <!-- 单位信息   暂时屏蔽 -->
	        <!-- <li class="nav_tab_checked"> -->
	        	<%-- <a href="goUnitInfoPage.do?method=goUnitInfoPage&unitOid=${sessionScope.OPERATOR_UNITID }">单位信息 </a>--%>
	        	<!-- <a onclick="goVerUnit()">单位信息 </a> -->
	       <!--  </li> -->
	    </ul>
	    <!-- 查询条件-->
	    <div class="search search-horizontal-small" id="query-condition" style="margin-bottom: 10px;">
	    <form id="verperson_wsid" action="#" method="post"></form>
      <div style="clear: both"></div>
	    </div>
	    <!-- 操作按钮-->
	    <div class='handle-btn-group'>
	    <div class="handle-btn clearfix" id="handel-button">
	        <button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="ver-add">增员</button>
	       <ul class="btn-list">
	        <li class="btn-delete btn-left-icon btn-default clearfix">减员
                <i class="btn-list-icon"></i>
                <ul class="btn-list-ul">
                    <li><a href="#" button-click="viewWork">离职</a></li>
                    <li><a href="#" button-click="viewUnit">退休</a></li>
                    <li><a href="#" button-click="viewUnit">自然减员</a></li>
                </ul>
            </li>
            </ul>
	        <button class="btn-export btn-left-icon btn-default" button-click="ver-export" id="export">人员导出</button>  
	        <button class="btn-print btn-left-icon btn-default" button-click="ver-printPersonSkillInfo">打印个人专技信息表</button>
	        <button class="btn-print btn-left-icon btn-default" button-click="ver-printPersonInfo">打印工作人员表</button>
	         <button class="btn-look btn-left-icon btn-default" button-click="ver-images">查看影像信息</button>
	        <button class="mt-set btn-set btn-left-icon btn-default" button-click="infoSetting">信息项设置</button>
	    </div>
	    </div>
	    
	    <!-- 列表内容展示-->
	    <table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
	</div>
	<!--浮动操作列表-->
	<div id="tabInfoTips" style="display:none;" class="handel_float" ><!-- class="handel_float" -->
	<em></em>
    <span></span>
    <button class="btn_add" opt="update">修改</button>
    <button class="btn_ok" opt="view">查看</button>
	</div>
</body>
</html>
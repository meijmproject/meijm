<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<title>人员休假信息管理右侧工作台</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet"
	type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<script type="text/javascript" src="js/comm/dictionary.js"></script>
<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script>
<script type="text/javascript">
	var unitOid = "${unitOid}";
	var worktop = null;
	$(document).ready(function() {
		var pageNo = '${param.pageNo}' == '' ? 1 : '${param.pageNo}';
		var limit = '${param.limit}' == '' ? 30 : '${param.limit}';
		worktop = new Worktop([
				{
					xtype : 'Xtable',
					xname : 'grid',
					url : 'listOrgByUnit.do?method=listOrgByUnit&unitOid=' + unitOid,
					lengthMenu : [ 30, 50, 100 ],
					start : pageNo,
					iPageLength : limit,
					paginationType : 'input',
					el : '#verPersonId',
					rowNumber : true,
					checkbox : true,
					singleCheck : false,
					columns : [ {
						header : '科室名称',
						field : 'orgName',
						width : 80
					}, {
						header : '科室类型',
						field : 'orgCategoryDesc',
						width : 80
					} ,{
						header : '上级科室',
						field : 'parentOrgName',
						width : 80
					}, {
						header : '排序号',
						field : 'orderOfView',
						width : 80
					}, {
						header : '状态',
						field : 'orgStatusName',
						width : 80
					} , {
						header : '成立时间',
						field : 'establishedDateStr',
						width : 80
					}, {
						header : '联系人',
						field : 'contacter',
						width : 80
					}, {
						header : '手机',
						field : 'mobilePhone',
						width : 80
					},{
						header : '电子邮箱',
						field : 'email',
						width : 80
					}, {
						header : '联系电话',
						field : 'phone',
						width : 80
					}, {
						header : '传真',
						field : 'fax',
						width : 80
					}, {
						header : '科室地址',
						field : 'address',
						width : 80
					}, {
						header : '备注',
						field : 'remark',
						width : 80
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
            'add': function() {
                Widget.openContent('goCreateOrg.do?method=goCreateOrg&unitOid='+ unitOid,function(){
                	worktop.form.goQuery();
                });
            },
            'revoke': function() {
              var selectRow = worktop.grid.selectModel.getSelectRows();
              if (selectRow.length == 0) {
                MessageBox.alert('提示', '请至少选择一条记录！');
                return;
              }
              var orgOid = new Array();
              var orgName = new Array();
              for (i = 0; i < selectRow.length; i++) {
            	  if(selectRow[i].data.orgStatus=='3'){
            		  MessageBox.alert('提示', '存在已撤销科室，无法撤销！');
                      return;
            	  }
            	orgOid[i] = selectRow[i].data.orgOid;
            	orgName[i] = selectRow[i].data.orgName;
              }
              if (orgOid) {
                MessageBox.yes('提示', '请确认是否撤销记录？', function() {
                  $.post("revokeOrg.do?method=revokeOrg", {
                	orgOids : orgOid.join(','),
                    applyNames : orgName.join(',')
                  }, function(data) {
                    if (data.message) {
                      MessageBox.message('提示', data.message, function() {
                    	  expandNode();  
                          worktop.form.goQuery();
                      });
                    }
                  }, 'json');
                });
              }
            },
            'revoke-one': function(grid,record,worktop) {
                var selectRow = [record];
                var orgOid = new Array();
                var orgName = new Array();
                for (i = 0; i < selectRow.length; i++) {
              	  if(selectRow[i].data.orgStatus=='3'){
              		  MessageBox.alert('提示', '存在已撤销科室，无法撤销！');
                        return;
              	  }
              	orgOid[i] = selectRow[i].data.orgOid;
              	orgName[i] = selectRow[i].data.orgName;
                }
                if (orgOid) {
                  MessageBox.yes('提示', '请确认是否撤销记录？', function() {
                    $.post("revokeOrg.do?method=revokeOrg", {
                  	orgOids : orgOid.join(','),
                      applyNames : orgName.join(',')
                    }, function(data) {
                      if (data.message) {
                        MessageBox.message('提示', data.message, function() {
                      	  expandNode();  
                            worktop.form.goQuery();
                        });
                      }
                    }, 'json');
                  });
                }
              },
            'update': function(grid,record,worktop) {
              var rows = [record];
         	  if(rows[0].data.orgStatus=='3'){
         		  MessageBox.alert('提示', '科室已撤销，不能进行修改操作！');
                   return;
         	  }
         	  var orgOid= rows[0].data.orgOid;
               Widget.openContent('goUpdateOrg.do?method=goUpdateOrg&orgOid=' + orgOid)
            },
            'view': function(grid,record,worktop) {
              var rows = [record];
              if (rows.length == 0) {
                MessageBox.alert('提示', "请至少选择一条记录再操作");
              }else if(rows.length>1){
                MessageBox.alert('提示','您选择了'+rows.length+'条记录,请仅选择一条记录再操作！');
              } else {
                var orgOid = rows[0].data.orgOid;
                Widget.openContent('goViewOrg.do?method=goViewOrg&orgOid=' + orgOid ,function(){
                	worktop.form.goQuery();
                })
              }
            }
            
          }
		}
	]);

		$(window).resize(
				function() {
					worktop.grid.wrap.find('.ct').height(
							$(window).height()
									- $('.current-position').outerHeight(true)
									- $(".search-content").outerHeight(true)
									- $("#nav_tab").outerHeight(true)
									- $("#handel-button").outerHeight(true) - 300);//48(分页48)
				}).resize(); 

		worktop.form.goQuery();
	});
		
</script>
</head>
<body style="overflow: hidden;" >
	<!--右边整体-->
	<div id="right" class="personBycp">
		<div class='infoshow-container  padding-lrb' id="fr_tbar">
		<div class="st-title-box">
		<h3 class="st-title-text">
				科室信息
			</h3>
			<!-- 操作按钮-->
			<div class="st-title-icon">
				<a title="新增" class="st-add-icon  btn" href="javascript:void(0);" button-click="add"></a> 
				<a title="撤销" class="st-delete-icon" href="javascript:void(0);" button-click="revoke"></a>
			</div>
			</div>
			 <table id="verPersonId" class="x-table sortable ellipsis striped hover personBycp"></table>
		</div>
		<!-- 列表内容展示-->
   
		<div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	    <em></em>
	    <span></span>
	    <button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
	    <button class="btn-look btn-left-icon btn-default" button-click="view">查看</button>
        <button class="btn-delete btn-left-icon btn-default" button-click="revoke-one">撤销</button>
	  </div>
	</div>
</body>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>数据字典管理</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var worktop = null;
var dicTypeOid = '${dicTypeOid}';
$(document).ready(function(){
	$(".btn-return").click(function(){
    HistoryRegister.go("goToDictionaryManageWorkbench");
  });
  worktop = new Worktop([
    {
      xtype:'Xtable',
      xname:'grid',
      url: 'listDicItemList.do?method=listDicItemList&dicTypeOid='+dicTypeOid,
      rowNumber: true,
      checkbox: true,
      singleCheck: false,
      start : '${param.pageNo}'==''?1:'${param.pageNo}',
      iPageLength: '${param.limit}'==''?30:'${param.limit}',
      columns: [
		{header:'字典名称', field:'dicItemName', width:80},
        {header:'字典编码', field:'dicItemCode', width:80},
        {header:'排序', field:'displayOrder', width:80},
        {header:'状态', field:'isActiveDesc', width:80},
        {header:'备注', field:'remark', width:200}
        
      ]
    },
    {
      xtype: 'QueryForm',//属性对应的构造函数
      xname: 'form',//属性名
      el:'#dictionary_search_form'
    },
    {
    	xtype: 'Toolbar',//属性对应的构造函数
      xname: 'tbar',
      tbar: '#fr_tbar',
      fbar: '#fr_fbar',
      buttons: {
        'add': function() {
        	Widget.openContent('goCreateDicItemPage.do?method=goCreateDicItem&dicTypeOid='+dicTypeOid,function(){
            worktop.form.goQuery();
          });
        },
        'delete': function() {
	       	var rows = worktop.grid.selectModel.getSelectRows();
	       	var dicItemOids = [];
          for (var i = 0; i < rows.length; i++) {
          	dicItemOids[dicItemOids.length] = rows[i].data.dicItemOid;
          }
          if (rows.length == 0) {
            MessageBox.alert('提示', "请至少选择一条记录再操作");
            return;
          } else if(rows.length>0) {
            var flag = false;
            rows.forEach(function(v,i) {
              if(v.data.isActive!=null&&v.data.isActive!='') {
                flag = true;
              }
            });
            if(flag) {
            	MessageBox.alert('提示', "存在非新建状态下的数据字典，无法删除！");
              return;
            } else {
              MessageBox.confirm('提示', "是否删除", function (yes) {
                if (yes == "yes") {
                  $.ajax({
                    url: 'deleteDicItem.do?method=deleteDicItem&dicItemOids='+dicItemOids,
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
          }
        },
        'update': function(grid,record,worktop) {
			if(record.data.isActive!=null&&record.data.isActive!='') {
				MessageBox.alert('提示', "非新建状态下的数据字典无法进行修改！");
			}else {
				Widget.openContent('goUpdateDicItemPage.do?method=goUpdateDicItem&dicItemOid='+record.data.dicItemOid,function(){
					worktop.form.goQuery();
				});
			}
        },
        'setActiveYes': function() {
        	var rows = worktop.grid.selectModel.getSelectRows();
			if (rows.length <= 0) {
			  MessageBox.alert('提示', "请至少选择一条记录再操作");
			  return;
			} else {
				MessageBox.confirm('提示', "是否将所有选中记录置为生效", function (yes) {
					var dicItemOids = [];
					for (var i = 0; i < rows.length; i++) {
			          	dicItemOids[dicItemOids.length] = rows[i].data.dicItemOid;
					}
                	if (yes == "yes") {
                  		$.ajax({
	                    	url: 'setActiveYesDicItem.do?method=setActiveYesDicItem&dicItemOids='+dicItemOids,
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
        'setActiveNo': function() {
        	var rows = worktop.grid.selectModel.getSelectRows();
			if (rows.length <= 0) {
			  MessageBox.alert('提示', "请至少选择一条记录再操作");
			  return;
			} else {
				MessageBox.confirm('提示', "是否将所有选中记录置为失效", function (yes) {
					var dicItemOids = [];
					for (var i = 0; i < rows.length; i++) {
			          	dicItemOids[dicItemOids.length] = rows[i].data.dicItemOid;
					}
                	if (yes == "yes") {
                  		$.ajax({
	                    	url: 'setActiveNoDicItem.do?method=setActiveNoDicItem&dicItemOids='+dicItemOids,
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
        'view': function(grid,record,worktop) {
			Widget.openContent('goViewDicItemPage.do?method=goViewDicItem&dicItemOid='+record.data.dicItemOid,function(){});
        },
        'delete-one': function(grid,record,worktop) {
        	if(record.data.isActive!=null&&record.data.isActive!='') {
        		MessageBox.alert('提示', "非新建状态下的数据字典无法删除！");
        		return;
			}
        	dicItemOids = [record.data.dicItemOid];
			MessageBox.confirm('提示', "是否删除", function (yes) {
				if (yes == "yes") {
					$.ajax({
						url: 'deleteDicItem.do?method=deleteDicItem&dicItemOids='+dicItemOids,
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
			});
        },
        'setActiveYes-one': function(grid,record,worktop) {
        	/* if(record.data.isActive=='N') {
        		MessageBox.alert('提示', "该数据字典已失效，无法进行生效操作");
        		return;
			} */
        	if(record.data.isActive=='Y') {
        		MessageBox.alert('提示', "该数据字典已生效，无法再次进行生效操作");
        		return;
			}
			MessageBox.confirm('提示', "确认置为生效", function (yes) {
				var dicItemOids = [record.data.dicItemOid];
               	if (yes == "yes") {
                 		$.ajax({
	                    	url: 'setActiveYesDicItem.do?method=setActiveYesDicItem&dicItemOids='+dicItemOids,
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
        'setActiveNo-one': function(grid,record,worktop) {
        	if(record.data.isActive=='N') {
        		MessageBox.alert('提示', "该数据字典已失效，无法再次进行失效操作");
        		return;
			}
        	if(record.data.isActive==null||record.data.isActive=='') {
        		MessageBox.alert('提示', "该数据字典状态为新增，无法进行失效操作");
        		return;
			}
        	MessageBox.confirm('提示', "确认置为失效", function (yes) {
        		var dicItemOids = [record.data.dicItemOid];
            	if (yes == "yes") {
              		$.ajax({
                    	url: 'setActiveNoDicItem.do?method=setActiveNoDicItem&dicItemOids='+dicItemOids,
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
      } 
    }    
  ]);
  
  $(window).resize(function(){
    worktop.grid.wrap.find('.ct').height(
      $(window).height() - $('.current-position').outerHeight(true) - $('.search-content').outerHeight(true) -102
    );
  }).resize();
  
  worktop.form.goQuery();
});
</script>
</head>
<body style="overflow-x: hidden;">
<div>
  <div class="current-position">
    <span>当前位置：</span>
    <span>基础信息管理 ></span>
    <span>信息管理></span>
    <span>数据字典管理</span>
    <button class="btn-return"> &lt; 返回</button>
  </div>
  <div class="search-content">
    <div class="search-include clearfix mrb-40">
      <form id="dictionary_search_form" action="#" method="post">
        <dl class='search-unit'>
          <dt class="search-unit-dt">字典类型：</dt>
          <dd class="search-unit-dd">
            <label><input readonly="readonly" type="text" value="${dicTypeName}"/></label>
          </dd>
        </dl>
        <dl class='search-unit'>
          <dt class="search-unit-dt">字典名称：</dt>
          <dd class="search-unit-dd">
            <label><input type="text" name="dicItemName" id="dicItemName"/></label>
          </dd>
        </dl>
        <dl class="search-unit search-btns">
          <dt>
            <button class="btn_search">查询</button>
          </dt>
        </dl>
      </form>
    </div>
  </div>
  <div class='handle-btn-group'>
	  <div class="handle-btn clearfix" id="fr_tbar">
	    <button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
	    <button class="btn-delete btn-left-icon btn-default" button-click="delete">删除</button>
	  </div>
	  <table class="x-table sortable ellipsis striped hover personBycp"></table>
  </div>
  
  <div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
	<em></em>
	<span></span>
	<button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
	<button class="btn-look btn-left-icon btn-default" button-click="view">查看</button>
	<button class="btn-modify btn-left-icon btn-default" button-click="delete-one">删除</button>
	<button class="btn-declare btn-left-icon btn-default" button-click="setActiveYes-one">置为生效</button>
	<button class="btn-delete btn-left-icon btn-default" button-click="setActiveNo-one">置为失效</button>
  </div>
</div>
</body>

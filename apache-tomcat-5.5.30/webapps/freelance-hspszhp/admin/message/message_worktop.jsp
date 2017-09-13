<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>通知通告管理</title>
<%@ include file="/include/js_css_base_include.jsp"%>
<script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
<link href="hspszhphtml/css/components/handle_btn.css" rel="stylesheet" type="text/css" />
<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var worktop = null;
$(document).ready(function(){
  worktop = new Worktop([
    {
    	xtype:'Xtable',
      xname:'grid',
      url: 'listMessageBoard.do?method=list',
      rowNumber: true,
      checkbox: true,
      singleCheck: false,
      start : '${param.pageNo}'==''?1:'${param.pageNo}',
      iPageLength: '${param.limit}'==''?30:'${param.limit}',
      columns: [
        {header:'标题', field:'title', width:100},
        {header:'发布者', field:'publisher', width:80},
        {header:'接收者', field:'reader', width:80},
        {header:'生效日期', field:'effDate', width:100},
        {header:'失效日期', field:'expDate', width:80},
        {header:'创建日期', field:'createdDate', width:80},
        {header:'内容', field:'content', width:200}
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
      	  Widget.openContent("goCreateMessageBoardPage.do?method=goCreate",function(){
            worktop.form.goQuery();
          });
      	},
      	'delete': function(grid,record,worktop){
          var rows = worktop.grid.selectModel.getSelectRows();
          var messageOids = [];
          for (var i = 0; i < rows.length; i++) {
          	messageOids[messageOids.length] = rows[i].data.messageOid;
          }
          if (rows.length == 0) {
            MessageBox.alert('提示', "请至少选择一条记录再操作");
          } else {
            MessageBox.confirm('提示', "是否删除", function (yes) {
              if (yes == "yes") {
                $.ajax({
                  url: 'deleteMessageBoard.do?method=delete&messageOids='+messageOids,
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
        'update': function(grid,record,worktop){
          var messageOid = record.data.messageOid;
          Widget.openContent("goUpdateMessageBoardPage.do?method=goUpdate&messageOid="+messageOid,function(){
            worktop.form.goQuery();
          });
        },
				'show': function(grid,record,worktop){
					var rows = worktop.grid.selectModel.getSelectRows();
          if (rows.length > 1) {
            MessageBox.alert('提示', "只能选择一条记录查看");
            return;
          } 
					var messageOid = record.data.messageOid;
          Widget.openContent("goViewMessageBoardPage.do?method=goView&messageOid="+messageOid,function(){
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
      - 100
      );//48(分页48)
  }).resize();
  worktop.form.goQuery();
});
</script>
</head>
<body style="overflow-x: hidden;">
<div>
  <div class="current-position">
    当前位置：
        <span>基础信息管理 > </span>
        <span>信息管理 > </span>
        <span>通知通告管理 </span>
    <div style="clear: both"></div>
  </div>
  <div class="search-content">
    <div class="search-include clearfix mrb-40">
      <form id="fr_wsid" action="#" method="post">
        <dl class='search-unit'>
          <dt class="search-unit-dt">标题：</dt>
          <dd class="search-unit-dd">
            <label><input type="text" name="title"/></label>
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
    <!-- 操作按钮-->
    <div class="handle-btn clearfix" id="fr_tbar">
      <button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
      <button class="btn-delete btn-left-icon btn-default" button-click="delete">删除</button>
    </div>
    <!-- 列表内容展示-->
    <table class="x-table sortable ellipsis striped hover"></table>
  </div>
  <div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
    <em></em>
    <span></span>
    <button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
    <button class="btn-look btn-left-icon btn-default" button-click="show">查看</button>
  </div>
</div>
</body>

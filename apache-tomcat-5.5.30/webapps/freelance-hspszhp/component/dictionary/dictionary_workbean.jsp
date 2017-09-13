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
$(document).ready(function(){
  worktop = new Worktop([
    {
      xtype:'Xtable',
      xname:'grid',
      url: 'listDicTypeList.do?method=listDicTypeList',
      rowNumber: true,
      checkbox: true,
      singleCheck: false,
      start : '${param.pageNo}'==''?1:'${param.pageNo}',
      iPageLength: '${param.limit}'==''?30:'${param.limit}',
      columns: [
		{header:'字典类型', field:'dicTypeName', width:80},
        {header:'字典类型编码', field:'dicTypeCode', width:80},
        {header:'排序', field:'displayOrder', width:80, type:'number'},
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
        'update': function(grid,record,worktop) {
        	var dicTypeOid = record.data.dicTypeOid;
        	HistoryRegister.set("goToDictionaryManageWorkbench", "goToDictionaryManageWorkbench.do?method=goToDictionaryManageWorkbench");
          location.href = 'goToDicItemWorkbench.do?method=goToDicItemWorkbench&dicTypeOid='+dicTypeOid;
        },
        'view': function(grid,record,worktop) {
        	var dicTypeOid = record.data.dicTypeOid;
        	Widget.openContent('goViewDicTypePage.do?method=goViewDicType&dicTypeOid='+dicTypeOid,function(){});
        }
      } 
    }    
  ]);
  
  $(window).resize(function(){
    worktop.grid.wrap.find('.ct').height(
    		$(window).height() - $('.current-position').outerHeight(true) - $('.search-content').outerHeight(true) -52
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
  </div>
  <div class="search-content">
    <div class="search-include clearfix mrb-40">
      <form id="dictionary_search_form" action="#" method="post">
        <dl class='search-unit'>
          <dt class="search-unit-dt">字典类型：</dt>
          <dd class="search-unit-dd">
            <label><input type="text" name="queryDicTypeName" id="queryDicTypeName"/></label>
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
  <table class="x-table sortable ellipsis striped hover personBycp"></table>
  </div>
  
  <div id="fr_fbar" style="display:none;" class="handel-float" ><!-- class="handel_float" -->
    <em></em>
    <span></span>
    <button class="btn-modify btn-left-icon btn-default" button-click="update">修改</button>
    <!-- <button class="btn-look btn-left-icon btn-default" button-click="view">查看</button> -->
  </div>
</div>
</body>

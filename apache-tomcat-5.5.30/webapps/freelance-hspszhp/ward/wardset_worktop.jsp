<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>病区配置管理</title>
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
      url: 'listWardSetList.do?method=listWardSetList',
      rowNumber: true,
      checkbox: true,
      singleCheck: false,
      start : '${param.pageNo}'==''?1:'${param.pageNo}',
      iPageLength: '${param.limit}'==''?30:'${param.limit}',
      columns: [
        {header:'病区科室', field:'deptName', width:80},
        {header:'病区类型', field:'waedType', width:80},
        {header:'床位数', field:'bedNum', width:80},
        {header:'备注', field:'remark', width:200}
      ]
    },
    {
      xtype: 'QueryForm',//属性对应的构造函数
      xname: 'form',//属性名
      el:'#ward_search_form'
    },
    {
      xtype: 'Toolbar',//属性对应的构造函数
      xname: 'tbar',
      tbar: '#fr_tbar',
      fbar: '#fr_fbar',
      buttons: {
        'add': function(){
          Widget.openContent("goWardCreatePage.do?method=goCreate",function(){
            worktop.form.goQuery();
            });
        },
        'update': function(grid,record,worktop){
          var waedOid = record.data.waedOid;
          Widget.openContent("goWardUpdatePage.do?method=goUpdate&waedOid="+waedOid,function(){
            worktop.form.goQuery();
            });
        },
        'delete': function(grid,record,worktop){
          var waedOid = record.data.waedOid;
          if(waedOid){
            MessageAction.yeah('请确认是否删除?', function(){
              $.ajax({
                url : 'deleteWard.do?method=delete',
                data : {waedOids:waedOid},
                dataType : 'json',
                type:'POST',
                error : function(){
                  MessageBox.alert("error occured!!!");
                },
                success : function(data) {
                  if(data.success==true){
                      worktop.form.goQuery();
                      loadDeptOptions();
                  }else{
                    alert("刪除失敗！");
                  }
                }
              });
            })
          }
        },
        'show': function(grid,record,worktop){
          var waedOid = record.data.waedOid;
          Widget.openContent("goWardViewPage.do?method=goView&waedOid="+waedOid,function(){
            worktop.form.goQuery();
            });
        },
        'deleteSelecteds': function(grid,record,worktop){
          var rows = worktop.grid.selectModel.getSelectRows();
          var waedOids = [];
          for (var i = 0; i < rows.length; i++) {
            waedOids[waedOids.length] = rows[i].data.waedOid;
          }
          if (rows.length == 0) {
            MessageBox.alert('提示', "请至少选择一条记录再操作");
          } else {
            MessageBox.confirm('提示', "是否删除", function (yes) {
              if (yes == "yes") {
                $.ajax({
                  url: 'deleteWard.do?method=delete&waedOids='+waedOids,
                  dataType: 'json',
                  error: function (x, t) {
                    alert(t)
                  },
                  async: false,
                  success: function (data) {
                    if (data.success) {
                      worktop.form.goQuery();
                      loadDeptOptions();
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
    }    
  ]);
  
  $(window).resize(function(){
    worktop.grid.wrap.find('.ct').height(
      $(window).height() 
      - $('.sitemap').outerHeight(true)
      - worktop.form.el.parent().outerHeight(true) //form 没有高度
      - (worktop.tbar.ct ? worktop.tbar.ct.outerHeight(true) : 0)
      - 60
      );//48(分页48)
  }).resize();
  
  worktop.form.goQuery();
  //loadDeptOptions();
  function loadDeptOptions() {
    $.get('getUnits.do?method=getDeptOptions', function(response) {
      response = eval('('+response+')');
      response.forEach(function(v,i) {
        $('#deptOid').append('<option value="'+v.value+'">'+v.text+'</option>');
      });
    });
  }
});
</script>
</head>
<body style="overflow-x: hidden;">
<div>
  <div class="sitemap" style="padding-bottom: 10px;">
    <ul>
      <li>当前位置：</li>
      <li>基础信息管理<span class="spanColor"> > </span></li>
      <li>机构管理<span class="spanColor"> > </span></li>
      <li>病区配备管理</li>
    </ul>
    <div style="clear: both"></div>
  </div>
  <div class="search-content">
    <div class="search-include clearfix mrb-40">
      <form id="ward_search_form" action="#" method="post">
        <dl class='search-unit'>
          <dt class="search-unit-dt">病区科室：</dt>
          <dd class="search-unit-dd">
            <label><input type="text" name="searchDeptName" id="searchDeptName"/></label>
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
    <!-- 操作按钮-->
    <div class="handle-btn clearfix" id="fr_tbar">
      <button class="btn-add btn-left-icon btn-default check_transaction_popdown" button-click="add">新增</button>
      <button class="btn-delete btn-left-icon btn-default" button-click="deleteSelecteds">删除</button>
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

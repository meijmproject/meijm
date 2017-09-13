<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改字典</title>
<script type="text/javascript" src="component/dictionary/check_dicitem.js"></script>
</head>
<body>
<form id="dicItemForm" class="form-inline" action="" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">数据字典 &gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
        <div class="md-main-content">
          <div class="md-error" style="display: none">
            <span class="back-error"></span>
          </div>
      <input type="hidden" name="dicItemOid" value="${dicItemForm.dicItemOid}">
      
       <div class="infoshow-container multi-info-group md-infoshow-area clearfix">
        <div class="data-units">
          <dl class="data-unit-dl">
            <dt>
              <b class="Required">* </b>字典编码：
            </dt>
            <dd>
              <label>${dicItemForm.dicItemCode}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl">
            <dt>
              <b class="Required">* </b>字典名称：
            </dt>
            <dd>
              <label>${dicItemForm.dicItemName}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl">
            <dt>排序：</dt>
            <dd>
              <label>${dicItemForm.displayOrder}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl remark-unit">
            <dt>备注：</dt>
            <dd>
              <label>${dicItemForm.remark}
              </label>
            </dd>
          </dl>
          <div class="clear"></div>
        </div>
        <div class="md-btn">
          <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">关   闭</button>
        </div>
      </div>
      </div>
    </div>
  </div>
</form>
</body>
</html>
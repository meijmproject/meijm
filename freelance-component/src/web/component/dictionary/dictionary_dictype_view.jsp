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
<form id="dicType" class="form-inline" action="" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">数据字典类型 &gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
      <div class="infoshow-container multi-info-group clearfix">
        <div class="data-units">
          <dl class="data-unit-dl">
            <dt>
              <b class="Required">* </b>字典类型编码：
            </dt>
            <dd>
              <label>${dicType.dicTypeCode}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl">
            <dt>
              <b class="Required">* </b>字典类型：
            </dt>
            <dd>
              <label>${dicType.dicTypeName}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl">
            <dt>排序：</dt>
            <dd>
              <label>${dicType.displayOrder}</label>
            </dd>
          </dl>
          <dl class="data-unit-dl remark-unit">
            <dt>备注：</dt>
            <dd>
              <label>${dicType.remark}
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
</form>
</body>
</html>
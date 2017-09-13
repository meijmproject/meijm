<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加字典</title>
<script type="text/javascript" src="component/dictionary/check_dicitem.js"></script>
</head>
<body>
<form id="dicItemForm" class="form-inline" action="createDicItem.do?method=createDicItem" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
      <div class="md-title">数据字典 &gt;新增<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
      <div class="md-error" style="display: none">
        <p>以下信息有误,请重新输入</p>
      </div>
      <input type="hidden" name="dicTypeOid" value="${dicTypeOid}">
      <div class="md-main-content">
        <div class="md-units-adddata md-units md-three-column clearfix">
          <dl>
            <dt>
              <b class="Required">* </b>字典编码：
            </dt>
            <dd>
              <label><input type="text" name="dicItemCode"
                class="modal_iput" maxlength="20" />
              </label>
            </dd>
          </dl>
          <dl>
            <dt>
              <b class="Required">* </b>字典名称：
            </dt>
            <dd>
              <label><input type="text" name="dicItemName"
                class="modal_iput" maxlength="100" />
              </label>
            </dd>
          </dl>
          <dl>
            <dt>排序：</dt>
            <dd>
              <label> <input type="text" name="displayOrder"
                class="modal_iput" maxlength="8" /> </label>
            </dd>
          </dl>
          <dl class="updown-remark">
            <dt>备注：</dt>
            <dd style="width: 83%;">
              <label><textarea rows="3" name="remark" maxlength="500"></textarea>
              </label>
            </dd>
          </dl>
          <div class="clear"></div>
        </div>
        <div class="md-btn">
          <button id="saveDicItem" type="button" class="md-btn-save">保  存</button>
          <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
        </div>
      </div>
    </div>
  </div>
</form>
</body>
</html>
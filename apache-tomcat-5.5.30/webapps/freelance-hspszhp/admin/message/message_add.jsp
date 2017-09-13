<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/js_css_base_include.jsp"%>  
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息新增页面</title>
<script type="text/javascript" src="admin/message/check_messageboard.js"></script>
</head>

<body>
<form id="messageBoardForm" class="form-inline" action="createMessageBoard.do?method=create" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">通知通告&gt;新增<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
        <div class="md-error" style="display: none"><p>以下信息有误,请重新输入</p></div>
        <div class="md-main-content">
          <div class="md-units-adddata md-units md-three-column clearfix">
            <dl>
              <dt>
                <b class="Required">* </b>标题：
              </dt>
              <dd>
                <label>
                  <input type="text" name="title" class="modal_iput" maxlength="200"/>
                </label>
              </dd>
            </dl>
            <dl>
              <dt>发布者：</dt>
              <dd>
                <label>
                  <input type="text" name="publisher" class="modal_iput" maxlength="100"/>
                </label>
              </dd>
            </dl>
            <dl>
              <dt>接收者：</dt>
              <dd>
                <label>
                  <input type="text" name="reader" class="modal_iput" maxlength="100"/>
                </label>
              </dd>
            </dl>
            <dl>
              <dt><b class="Required">* </b>生效日期：</dt>
              <dd>
                <label>
                  <input type="text" id="effDateStr" name="effDateStr" class="modal_iput"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                </label>
              </dd>
            </dl>
            <dl>
              <dt><b class="Required">* </b>失效日期：</dt>
              <dd>
                <label>
                  <input type="text" id="expDateStr" name="expDateStr" class="modal_iput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                </label>
              </dd>
            </dl>
            <dl class="updown-remark" style=' height: 100px;'>
	            <dt style=' line-height: 100px;'><b class="Required">* </b>内容：</dt>
	            <dd>
	              <label>
	                <textarea name="content" rows="3" class="modal_textarea" maxlength="500"></textarea>
	              </label>
	            </dd>
	          </dl>
            <div class="clear"></div>
          </div>
          <div class="md-btn">
            <button id="saveMessageBoard" type="button" class="md-btn-save">保  存</button>
            <button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
          </div>
        </div>
    </div>
  </div>
</form>
</body>
</html>
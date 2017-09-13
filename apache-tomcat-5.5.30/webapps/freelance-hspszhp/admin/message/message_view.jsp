<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="DicConstants"%>
<%@ include file="/include/js_css_base_include.jsp"%>  
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息修改页面</title>
<script type="text/javascript" src="admin/message/check_messageboard.js"></script>
</head>

<body>
<form id="messageBoardForm" class="form-inline" action="updateMessageBoard.do?method=update" method="post" onsubmit="return false">
  <div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">通知通告&gt;查看<a href="#" class="md-close close-popdown" button-click="widget-close"></a></div>
        <div class="md-main-content">
          <div class="md-error" style="display: none">
            <span class="back-error"></span>
          </div>
          <input type="hidden" id="messageOid" name="messageOid" value="${messageBoardForm.messageOid}" />
          <div class="infoshow-container multi-info-group md-infoshow-area clearfix">
            <div class="data-units">
	            <dl class="data-unit-dl">
	              <dt>
	                <b class="Required">* </b>标题：
	              </dt>
	              <dd>
	                <label>
	                  <bean:write name="messageBoardForm" property="title" />
	                </label>
	              </dd>
	            </dl>
	            <dl class="data-unit-dl">
	              <dt>发布者：</dt>
	              <dd>
	                <label>
	                  <bean:write name="messageBoardForm" property="publisher" />
	                </label>
	              </dd>
	            </dl>
	            <dl class="data-unit-dl">
	              <dt>接收者：</dt>
	              <dd>
	                <label>
	                  <bean:write name="messageBoardForm" property="reader" />
	                </label>
	              </dd>
	            </dl>
	            <dl class="data-unit-dl">
	              <dt><b class="Required">* </b>生效日期：</dt>
	              <dd>
	                <label>
	                  <bean:write name="messageBoardForm" property="effDateStr" />
	                </label>
	              </dd>
	            </dl>
	            <dl class="data-unit-dl">
	              <dt><b class="Required">* </b>失效日期：</dt>
	              <dd>
	                <label>
	                  <bean:write name="messageBoardForm" property="expDateStr" />
	                </label>
	              </dd>
	            </dl>
	            <dl class="data-unit-dl remark-unit">
	              <dt><b class="Required">* </b>内容：</dt>
	              <dd>
	                <label title="<bean:write name="messageBoardForm" property="content" />">
	                  <bean:write name="messageBoardForm" property="content" />
	                </label>
	              </dd>
	            </dl>
	          </div>
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
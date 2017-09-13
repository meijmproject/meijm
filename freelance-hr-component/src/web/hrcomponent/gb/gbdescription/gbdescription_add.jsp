<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!--
 * @function    岗位说明书新增页面
 * @page name   hrbiz\standard\management\goout\gooutperson_add.jsp
 * @author      cheny
 * @created     2017/03/28
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>岗位说明书新增页面</title>
<script type="text/javascript" src="hrcomponent/gb/gbdescription/js/gbdescription_add.js"></script>

</head>
<body>
	<form id="gbDescriptionForm" class="form-inline" action="addGbDescription.do?method=addGbDescription" method="post" onsubmit="return false">
		<div id="transaction_modal_box" class="transaction_modal_box">
			<div class="modal-dialog-container">
				<div class="md-title">
					岗位说明书管理 &gt;新增<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
				</div>
				<div class="md-error" style="display: none">
					<p>以下信息有误,请重新输入</p>
				</div>
				<div class="md-main-content">
					<div class="md-units md-three-column clearfix">
								<dl class="updown-dl">
									<dt><b class="Required">* </b>岗位类别：</dt>
									<dd>
										<label> <dictionary:dicItemSelect id="postType1" dicTypeCode="<%=DicConstants.YHRS0022%>" selected="${postType}" name="postType" emptyText="--请选择--" /> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt><b class="Required">* </b>岗位级别：</dt>
									<dd>
										<label> <dictionary:dicItemSelect id="postLevel1" dicTypeCode="<%=DicConstants.YHRS0023%>" selected="${postLevel}" name="postLevel" emptyText="--请选择--" /> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt><b class="Required">* </b>岗位名称：</dt>
									<dd>
										<label><input type="text"  id="postName" name="postName" maxlength="100"/> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt>岗位聘用条件：</dt>
									<dd>
										<label><input type="text"  id="postCondition"  name="postCondition" maxlength="1000"/> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt>岗位职责：</dt>
									<dd>
										<label><input type="text"  id="postDuty" name="postDuty" maxlength="500"/> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt>岗位目标任务：</dt>
									<dd>
										<label><input type="text"  id="postAssigment" name="postAssigment"  maxlength="500"/> </label>
									</dd>
								</dl>
								<dl class="updown-dl">
									<dt>岗位绩效考核标准：</dt>
									<dd>
										<label><input type="text"  id="assessmentCriteria" name="assessmentCriteria" maxlength="100"/> </label>
									</dd>
								</dl>
					</div>
				</div>
				 <div class="md-btn">
            		<button id="saveGbDescription" type="button" class="md-btn-save">保  存</button>
            		<button type="button" class="md-btn-cancel close-popdown" data-dismiss="modal" button-click="widget-close">取 消</button>
          		</div>
			</div>
		</div>
	</form>
</body>
</html>
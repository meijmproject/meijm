<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<!--
 * @function    内设机构新增页面
 * @page name   /hrinfo/ver/unit/unit/pborginfo/verpb_orginfo_create.jsp
 * @author      xiongyx
 * @created     2016/09/21
 * @version     1.0
-->
<html>
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>弹出窗/弹出表格</title>
    <script src="hrinfo/ver/unit/unit/pborginfo/check_org_info.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
</head>
<body>
<div id="showmodal" class="modal-content">
<html:form styleId="form_orginfo" styleClass="form-inline" action="createVerUbOrgInfo.do?method=createVerUbOrgInfo" method="post" >
    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">内设机构&gt;新增</h4>
    </div>
<div class="modal-body">
<html:hidden name="verUbOrgInfoForm" property="unitOid" />
	       	<div class="modal-wrong" style="display: none">
	                <ol class="titwrong"><embed src="img/index/jg.svg" width="20" height="20" top="0" type="image/svg+xml" class="ico"/> 以下信息有误，请重新输入！</ol>
	                <ol class="wroglist">
	                    <logic:messagesPresent>
	                        <html:messages id="error">
	                            <li><bean:write name="error" /></li>
	                        </html:messages>
	                    </logic:messagesPresent>
	                </ol>
          	  	</div>	
	        <div class="modal-row02">
	        	<dl>
	             	 <dt><b class="Required">* </b>内设机构名称：</dt>
		             <dd><html:text styleId="orgName" styleClass="modal_iput" property="orgName" maxlength="100" /></dd>
		        </dl>
		        <dl >
	             	 <dt>隶属单位：</dt>
		             <dd><input type="text" value="${unitName }" readonly="readonly" class="modal_iput readonly"/> </dd>
		        </dl>
		        </div>
		        <div class="modal-row02">
		        <dl>
	             	 <dt><b class="Required">* </b>内设机构级别：</dt>
		             <dd>
                         <dictionary:dicItemSelect  name="levelCode" id="levelCode" styleClass="modal_select" dicTypeCode="<%=DicConstants.YHRS0093%>" emptyText="<%=Constant.EMPTY_SELECT %>" selected="${verUbOrgInfoForm.levelCode}"  />&nbsp;
		             </dd>
		        </dl>
		        <dl>
	             	 <dt>上级内设机构：</dt>
		             <dd>
						<html:select styleId="parentOrgOid" styleClass="modal_iput" property="parentOrgOid" >
							<option value="">--请选择--</option>
							<c:forEach var="dto" items="${orgLists}">
								<option value="${dto.orgOid }">${dto.orgName }</option>
							</c:forEach>
						</html:select>            
					</dd>
		        </dl>
		        </div>
		        <div class="modal-row02">
	         	<dl>
             	 <dt>成立日期：</dt>
	               <dd><html:text styleId="establishedDate" styleClass="modal_iput" property="establishedDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></dd>
	            </dl>
	            <dl>
	                 <dt><b class="Required">* </b>机构职能：</dt>
		             <dd><html:text styleId="orgFunction" styleClass="modal_iput" property="orgFunction" maxlength="100" /></dd>
		        </dl>
	            </div>
		        <div class="modal-row02">
		        <dl>
	             	 <dt><b class="Required">* </b>排序号：</dt>
		             <dd><html:text styleId="orderOfView" styleClass="modal_iput" property="orderOfView" maxlength="3" /></dd>
		        </dl>

				<dl>
	                 <dt><b class="Required">* </b>内设机构状态：</dt>
		             <dd><html:select styleId="orgStatus" styleClass="modal_iput" property="orgStatus" >
		             		<option value="1">新建</option>
		             	 	<option value="2" selected>正常</option>
		             	 	<option value="3">撤销</option>
		             	 </html:select>
		             </dd>
		        </dl>
		        </div>	        
		        <div class="modal-row02">
			        <dl>
		                 <dt>备注：</dt>
			             <dd><html:text styleId="remark" styleClass="modal_iput" property="remark" maxlength="1000" /></dd>
			        </dl>
		        </div>
    </div>
    <div class="modal-footer">
        <button type="button" id="saveorginfo" class="btn btn-primary" >保　存</button>
        <button type="button" class="btn btn-default close-login close-popdown" data-dismiss="modal" button-click="widget-close">取　消</button>
        </div>
         <div style="clear: both"></div>
    </html:form>
    </div>
</body>
</html>
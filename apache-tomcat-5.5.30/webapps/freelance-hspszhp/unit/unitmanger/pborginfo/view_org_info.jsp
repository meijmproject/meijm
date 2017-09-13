<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
	
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<!--
 * @function    内设机构查看页面
 * @page name   /hrinfo/ver/unit/unit/pborginfo/view_org_info.jsp
 * @author      xiongyx
 * @created     2016/09/23
 * @version     1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>弹出窗/弹出表格</title>

    <script src="hrinfo/ver/unit/unit/pborginfo/check_org_info.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>

</head>

<body>

<!-- 
<div id="w">
    <div id="content">
        <center><a href="#showmodal_resumeinfo" class="flatbtn" id="resumeinfo">Modal Boder</a></center>
    </div>
</div>
 -->
<div id="showmodal" class="modal-content">
<html:form styleId="form_orginfo" styleClass="form-inline" action="updateVerUbOrgInfo.do?method=updateVerUbOrgInfo" method="post" >
    <div class="modal-header">
        <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">内设机构&gt;修改</h4>
    </div>
<div class="modal-body">
<html:hidden name="verUbOrgInfoForm" property="unitOid" />
<html:hidden name="verUbOrgInfoForm" property="orgOid" />
	        <div  id="query-condition">
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
	             	 <dt>内设机构名称：</dt>
		             <dd><label title='<bean:write name="verUbOrgInfoForm" property="orgName" />' >
		             	<bean:write name="verUbOrgInfoForm" property="orgName" /> &nbsp;</label></dd>
		        </dl>
		        <dl >
	             	 <dt>隶属单位：</dt>
		             <dd><label title='<bean:write name="verUbOrgInfoForm" property="unitName" />' >
		             	<bean:write name="verUbOrgInfoForm" property="unitName" /> &nbsp;</label></dd>
		        </dl>
		        </div>
		        <div class="modal-row02">
		        <dl>
	             	 <dt>内设机构级别：</dt>
		             <dd>
                         <dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0093%>" dicItemCode="${verUbOrgInfoForm.levelCode}" /> &nbsp;
		             </dd>
		        </dl>
		        <dl>
	             	 <dt>上级内设机构：</dt>
		             <dd><label title='<bean:write name="verUbOrgInfoForm" property="parentOrgStr" />' >
							<bean:write name="verUbOrgInfoForm" property="parentOrgStr" /> &nbsp;	</label>             
					 </dd>
		        </dl>
		        </div>
		        <div class="modal-row02">
	         	<dl>
             	 <dt>成立日期：</dt>
	               <dd><bean:write name="verUbOrgInfoForm" property="establishedDateStr" /> &nbsp;</dd>
	            </dl>
	            <dl>
	                 <dt>机构职能：</dt>
		             <dd><label title='<bean:write name="verUbOrgInfoForm" property="orgFunction" />' >
		             	<bean:write name="verUbOrgInfoForm" property="orgFunction" /> &nbsp;</label></dd>
		        </dl>
	            </div>
		        <div class="modal-row02">
		        <dl>
	             	 <dt>排序号：</dt>
		             <dd><bean:write name="verUbOrgInfoForm" property="orderOfView" /> &nbsp;</dd>
		        </dl>

				<dl>
	                 <dt>内设机构状态：</dt>
		             <dd><bean:write name="verUbOrgInfoForm" property="orgStatus" /> &nbsp;
		        </dl>
		        </div>
		        
		        <div class="modal-row02">
		        <dl>
	                 <dt>备注：</dt>
		             <dd><label title='<bean:write name="verUbOrgInfoForm" property="remark" />' >
		             	<bean:write name="verUbOrgInfoForm" property="remark" /> &nbsp;</label></dd>
		        </dl>
		        
		        
		        </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" id="save" class="btn btn-primary close-popdown">返回</button>
    </div>
         <div style="clear: both"></div>
    </html:form>
    </div>
</body>
<script type="text/javascript">
  
	
</script>
</html>
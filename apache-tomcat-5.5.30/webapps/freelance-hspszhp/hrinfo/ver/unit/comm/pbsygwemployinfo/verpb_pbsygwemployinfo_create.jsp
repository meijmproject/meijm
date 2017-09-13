<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--                                                 
* @page name  /hrinfo/ver/unit/comm/pbsygwemployinfo/verpb_pbsygwemployinfo_create.jsp
* @function   事业岗位聘任信息新增页面
* @author     liul
* @created    2017-02-15
* @version    1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>事业岗位聘任信息新增页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbsygwemployinfo/js/check_sy_gw_employ_info.js"></script>
    <script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
	<script>
	var preStatus = '${pbSyGwEmployInfoForm.positioningStatus}';
	var prePositionType = '${pbSyGwEmployInfoForm.positionType}';
	var preIsMPosition = '${pbSyGwEmployInfoForm.isMPosition}';
        $(document).ready(function(){
            initPositionName();
            //initDutyLevel();
            //initDutyUnitName();
            /* $.post(url,function(data){
            					alert(data.rs);
        						if(null != data.rs)
        						{
        							document.getElementById("dept").innerHTML = "";
        							document.getElementById("dept").value ='';
        							for(var i=0;i<data.rs.length;i++)
        							{
        								document.getElementById("dept").options[i] = new Option(data.rs[i].orgName,data.rs[i].orgOid);
        							}
        						}
        	},'json'); */
        })
        
    </script>
</head>

<body>

<form id="syGwEmployInfoForm" class="form-inline" action="createPbSyGwEmployInfo.do?method=create" method="post" onsubmit="return false">
<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title">
            事业岗位聘任信息&gt;新增
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
        <div class="md-main-content">
	      	<input type="hidden" id="personOid" name="personOid" value="${personOid}" />
            <input type="hidden" id="url_id" value="${urlId}" />
             <input type="hidden" id="personOid" name="personOid" value="${pbSyGwEmployInfoForm.personOid}" />
             <input type="hidden" id="statusFlag" name="statusFlag"/>
            <input type="hidden" id="isMPositionFlag" name="isMPositionFlag"/>
            <input type="hidden" id="dutyUnitNameActive" value="${pbSyGwEmployInfoForm.dutyUnitName}">
            <input type="hidden" id="dutyUnitOidActive" value="${pbSyGwEmployInfoForm.dutyUnitOid}">

        	<div class="md-units md-three-column clearfix">
                <dl class="updown-dl">
                    <dt><b class="Required">* </b>岗位聘任状态：</dt>
                    <dd>
                        <label>
                            <dictionary:dicItemSelect styleClass="modal_select" id="positioningStatus" onchange="positionStatusOnchange(this.value)" dicTypeCode="<%=DicConstants.YHRS0026%>" selected="001" name="positioningStatus"  emptyText="--请选择--"/>
                        </label>
                    </dd>
                     </dl>
                     <dl class="updown-dl">
                      <dt><b class="Required">* </b>聘任单位名称：</dt>
                    <dd>
                        <label title="<bean:write name="pbSyGwEmployInfoForm" property="dutyUnitName" />">
                            	<input type="text" name="dutyUnitName" readonly="readonly" id="dutyUnitName" value="${pbSyGwEmployInfoForm.dutyUnitName}"/>
                                <!-- <bean:write  name="pbSyGwEmployInfoForm" property="dutyUnitName" />&nbsp; -->
                                <html:hidden name="pbSyGwEmployInfoForm" property="dutyUnitName" />
                                <input type="hidden" name="dutyUnitOid" id="dutyUnitOid" value="${pbSyGwEmployInfoForm.dutyUnitOid}"/>
                        </label>
                    </dd>
                     </dl>
                     <dl class="updown-dl">
                      <div id="deptInput"  style="display:none;height: 100%">
	                    <dt><b class="Required">* </b>内设机构名称：</dt>
	                    <dd>
	                        <label title="<bean:write name="pbSyGwEmployInfoForm" property="deptName" />">
	                            <html:text styleClass="modal_iput" name="pbSyGwEmployInfoForm" property="deptName" maxlength="100"/>
	                            <html:hidden name="pbSyGwEmployInfoForm" property="deptOid" />
	                        </label>
	                    </dd>
	                    </div>
                      <div id="deptSelect"  style="display:block;height: 100%">
	                      <dt> <b class="Required">* </b>内设机构名称：</dt>
	                       
	                        <dd>
	                          <label>
							    <input type="text" class="modal_iput" name="deptName" id="deptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,$('#dutyUnitOid').val(),true,null,['bottom','left'],'true')" value="${pbSyGwEmployInfoForm.deptName}"/>
     			 				<input type="hidden" name="deptOid" value="${pbSyGwEmployInfoForm.deptOid}"/>
     			 				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
	                          </label>
	                          </dd>
                       </div> 
                     </dl>
                     <dl class="updown-dl">
                      <dt><b class="Required">* </b>岗位名称：</dt>
                    <dd>
                    	<label>
                    		<html:text styleClass="modal_iput" styleId="positionName" name="pbSyGwEmployInfoForm" property="positionName" maxlength="100"/> 
                    	</label>
                    </dd>
                     </dl>
                     <dl class="updown-dl">
                     <dt><b class="Required">* </b>岗位类别：</dt>
                    <dd><label><dictionary:dicItemSelect styleClass="modal_select" id="positionType" onchange="changePositionName(this.value);" dicTypeCode="<%=DicConstants.YHRS0022%>" selected="${pbSyGwEmployInfoForm.positionType}" name="positionType"  emptyText="--请选择--"/></label></dd>
                     </dl>
                      <dl class="updown-dl">
                       <dt><b class="Required">* </b>岗位级别：</dt>
                    <dd>
                    <label>
                         <input type="text" class="modal_iput" name="positionLevelName" readonly="readonly"  id="positionLevel" value='<dictionary:viewDicItemName dicTypeCode="<%=DicConstants.YHRS0023%>" dicItemCode="${pbSyGwEmployInfoForm.positionLevel}" />' onclick="min_Dic.min_Dictionary(this,'<%=DicConstants.YHRS0023%>','','',null,null,['bottom','left'],true)" />
                         <html:hidden name="pbSyGwEmployInfoForm" property="positionLevel" />
                         <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
                     </label>
                    </dd>
                      </dl>
                      <dl class="updown-dl">
                       <dt><b class="Required">* </b>职务类型：</dt>
                    <dd>
                        <label>
                            <dictionary:dicItemSelect styleClass="modal_select" id="dutyRecordType" dicTypeCode="<%=DicConstants.YHRS0036%>" selected="${pbSyGwEmployInfoForm.dutyRecordType}" name="dutyRecordType"  emptyText="--请选择--"/>
                        </label>
                    </dd>
                      </dl>
                      <dl class="updown-dl">
                      <dt><b class="Required">* </b>是否主岗位：</dt>
                    <dd><label><dictionary:dicItemSelect styleClass="modal_select" id="isMPosition" dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${pbSyGwEmployInfoForm.isMPosition}" name="isMPosition"  emptyText="--请选择--"/></label></dd>
                      </dl>
                      <dl class="updown-dl">
                       <dt><b class="Required">* </b>岗位聘任开始时间：</dt>
                    <dd><label><html:text styleClass="modal_iput" styleId="beginDateStr" name="pbSyGwEmployInfoForm" property="beginDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                      </dl>
                      <dl class="updown-dl">
                      <dt>岗位聘任拟截止时间：</dt>
                    <dd><label><html:text styleClass="modal_iput" styleId="endDateStr" name="pbSyGwEmployInfoForm" property="endDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                      </dl>
                <dl class="updown-dl">
                	<dt>岗位聘任实际截止时间：</dt>
                    <dd><label><html:text styleClass="modal_iput" styleId="endDateActualStr" name="pbSyGwEmployInfoForm" property="endDateActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                </dl>
                 <dl class="updown-dl">
                	 <dt>职务属性：</dt>
                    <dd><label><dictionary:dicItemSelect styleClass="modal_select" id="dutyAttribute" dicTypeCode="<%=DicConstants.YHRS0028%>" selected="${pbSyGwEmployInfoForm.dutyAttribute}" name="dutyAttribute"  emptyText="--请选择--"/></label></dd>	
                </dl>
                <dl class="updown-remark">
                	<dt>备注：</dt>
							<dd><label title="${pbSyGwEmployInfoForm.remark}">
							<textarea id="remark" name="remark" rows="3" maxlength="1000"></textarea>
							</label></dd>
                </dl>
            </div>
   
    
          </div>
          <div class="md-btn">
		    	<button id="saveSyGwEmployInfo" type="button" class="md-btn-save">保　存</button>
		    	<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
		    </div>
    </div>
	</div>
    
</form>
</body>
</html>
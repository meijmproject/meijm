<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<%@ page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name  /hrinfo/ver/unit/comm/pbyngwemployinfo/verpb_pbyngwemployinfo_update.jsp
* @function   事业岗位聘任信息修改页面
* @author     liul
* @created    2017-02-15
* @version    1.0
-->
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>院内岗位聘任信息修改页面</title>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbyngwemployinfo/js/check_sy_yn_employ_info.js"></script>
    <script type="text/javascript" src="hrinfo/ver/unit/comm/pbyngwemployinfo/js/selectWorkTop.js"></script>
     <script type="text/javascript" src="hrcomponent/orgselectjs/selectOrgWorktop.js"></script>
	<script type="text/javascript" src="js/comm/customJs.js"></script>
	<script>
	var preStatus = '${pbYnGwEmployInfoForm.hisPositionStatus}';
        $(document).ready(function(){
        	positionStatusOnchange(preStatus);
        	 $('#hisPositionName').click(function(){
     	        if($('#hisWorkTypeName').val()==null || $('#hisWorkTypeName').val() ==""){
     	            MessageBox.alert('提示', '请先选择工作类别!');
     	            return;
     	        }else if($('#hisPositionTypeName').val()==null || $('#hisPositionTypeName').val() ==""){
     	        	MessageBox.alert('提示', '请先选择岗位类别!');
     	            return;
     	        }else{
     	        	var hisWorkType = $('#hisWorkTypeName option:selected').val();
     	        	var hisPositionType = $('#hisPositionTypeName option:selected').val();
     	        	selectWorkTop.init($('#hisPositionName'),hisWorkType,hisPositionType); 
         	    }
     	    })
     		//根据工作类别的选,加载岗位类别的下拉框
		    $('#hisWorkTypeName').change(function(){
		    	//获取工作类别的选择
		    	var hisWorkType = $('#hisWorkTypeName option:selected').val();
		    	if(hisWorkType!=""){
		    		//根据工作类别设置岗位级别要显示的内容
			    	if(hisWorkType == '1')
			    	{ 
			    		$("#hisPositionLevel").attr("notInclude","2,3,4");
			    	}else if(hisWorkType == '2')
			    	{
			    		$("#hisPositionLevel").attr("notInclude","1,3,4");
			    	}else if(hisWorkType == '3')
			    	{
			    		$("#hisPositionLevel").attr("notInclude","1,2,3");
			    	}else if(hisWorkType == '4')
			    	{
			    		$("#hisPositionLevel").attr("notInclude","1,2,4");
			    	} 
			    	$("#hisPositionLevel").val("");
		    		$("[name=hisPositionLevel]").val("");
		    		$("#hisPositionName").val("");
		    		$("[name=hisPositionOid]").val("");
		    		
					//判断是否已经弹窗，如果已经弹，则重新加载窗口数据信息，否则不弹窗
			    	if($("#hisPositionLevel").siblings(".prompt_box_info_dictionary") != null 
				    		&& $("#hisPositionLevel").siblings(".prompt_box_info_dictionary").is(":visible"))
		    		{
		    			min_Dic.min_Dictionary($("#hisPositionLevel"),'YHRS0124',true,null,null,null,['bottom','left']);
		    		}
		    		//根据工作类别的选择,获取需要加载到岗位类别的选择
		        	$.ajax({
		        	    url:'getSelectItems.do?method=getSelectItems',
		        	    type:'POST',
		        	    data:{
		        	    	hisWorkType:hisWorkType
		        	    },
		        	    dataType:'json',
		        	    success:function(data){
		        	    	//生成岗位类别的下拉框
		        			$("#hisPositionTypeName").empty();//清空选项
		    				$("#hisPositionTypeName").append("<option value=''>--请选择--</option>");
		        			for(var i=0; i<data.hisPositionTypeItems.length; i++){
		        				$("#hisPositionTypeName").append("<option value='"+data.hisPositionTypeItems[i].dicItemCode+"'>"+data.hisPositionTypeItems[i].dicItemName+"</option>");
		        			}
		        	    }
		        	});
		    	}else
		    	{
		    		$("#hisPositionLevel").attr("notInclude","");
		    	}
		    });
            initWorkType();
        })
    </script>
</head>

<body>

<form id="ynGwEmployInfoForm" class="form-inline" action="updatePbYnGwEmployInfo.do?method=update" method="post" onsubmit="return false">
<div id="transaction_modal_box" class="transaction_modal_box">
    <div class="modal-dialog-container">
        <div class="md-title"> 院内岗位聘任信息&gt;修改
            <a href="#" class="md-close close-popdown"></a>
        </div>
        <div class="md-error" style="display: none">
			<p>以下信息有误,请重新输入</p>
		</div>
        <div class="md-main-content">
	      	<input type="hidden" id="ynGwEmployOid" name="ynGwEmployOid" value="${pbYnGwEmployInfoForm.ynGwEmployOid}" />
			<input type="hidden" id="personOid" name="personOid" value="${pbYnGwEmployInfoForm.personOid}" />
            <input type="hidden" id="url_id" value="${urlId}" />
            <input type="hidden" id="statusFlag" name="statusFlag"/>
            <input type="hidden" id="deptNameActive" value="${pbYnGwEmployInfoForm.deptName}">
            <input type="hidden" id="deptOidActive" value="${pbYnGwEmployInfoForm.deptOid}">
            <input type="hidden" name="unitOid" id="unitOid" value="${pbYnGwEmployInfoForm.unitOid}"/>

        	<div class="md-units md-three-column clearfix">
                <dl class="updown-dl">
                   <dt><b class="Required">* </b>岗位聘任状态：</dt>
                    <dd>
                        <label>
                            <dictionary:dicItemSelect styleClass="modal_select" id="hisPositionStatus"  dicTypeCode="<%=DicConstants.YHRS0026%>" selected="${pbYnGwEmployInfoForm.hisPositionStatus}" onchange="positionStatusOnchange(this.value)" name="hisPositionStatus"  emptyText="<%=Constant.EMPTY_SELECT %>"/>
                        </label>
                    </dd>
                     </dl>
                     <dl class="updown-dl">
                     <dt> <b class="Required">* </b>所在科室：</dt>
	                        <dd>
	                          <label>
							    <input type="text" class="modal_iput" name="deptName" id="deptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,$('#unitOid').val(),true,null,['bottom','left'],'true')" value="${pbYnGwEmployInfoForm.deptName}"/>
     			 				<input type="hidden" name="deptOid" value="${pbYnGwEmployInfoForm.deptOid}"/>
     			 				<a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
	                          </label>
	                          </dd>
                     </dl>
                     
                     <dl class="updown-dl">
                       <dt><b class="Required">* </b>工作类别：</dt>
	                    <dd><label>
	                    <dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS0112%>" selected="${pbYnGwEmployInfoForm.hisWorkType}" id="hisWorkTypeName" name="hisWorkType" emptyText="<%=Constant.EMPTY_SELECT %>" />
	                    <%-- <input type="text" class="modal_iput" name="hisWorkTypeName" id="hisWorkTypeName" readonly="readonly"  value="${pbYnGwEmployInfoForm.hisWorkTypeName}"/>
	        			<input type="hidden" name="hisWorkType"  id="hisWorkType" value="${pbYnGwEmployInfoForm.hisWorkType}" /> --%>
	                    </label></dd>
                     </dl>
                     <dl class="updown-dl">
	                     <dt><b class="Required">* </b>岗位类别：</dt>
	                    <dd><label>
	                     <dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS0113%>" selected="${pbYnGwEmployInfoForm.hisPositionType}" id="hisPositionTypeName" name="hisPositionType" emptyText="<%=Constant.EMPTY_SELECT %>" />
	                   <%--  <input type="text" class="modal_iput" name="hisPositionTypeName" id="hisPositionTypeName" readonly="readonly"  value="${pbYnGwEmployInfoForm.hisPositionTypeName}"/>
	        			<input type="hidden" name="hisPositionType" id="hisPositionType" value="${pbYnGwEmployInfoForm.hisPositionType}"/> --%>
	                    </label></dd>
                     </dl>
                     <dl class="updown-dl">
                      <dt><b class="Required">* </b>岗位名称：</dt>
                    	<dd>
                    	<label><!-- onclick="selectPosition(this)" -->
                          <input type="text" class="modal_iput" name="hisPositionName" id="hisPositionName" readonly="readonly" value="${pbYnGwEmployInfoForm.hisPositionName}"/>
                          <input type="hidden" name="hisPositionOid"  id="hisPositionOid" value="${pbYnGwEmployInfoForm.hisPositionOid}" />
                          <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
                    	</label>
                    	</dd>
                     </dl>
                      <dl class="updown-dl">
	                       <dt><b class="Required">* </b>岗位级别：</dt>
	                    <dd>
	                     <label>
	                        <input type="text" class="modal_iput" name="positionLevelName" readonly="readonly"  id="hisPositionLevel" value='<dictionary:viewDicItemName dicTypeCode="YHRS0124" dicItemCode="${pbYnGwEmployInfoForm.hisPositionLevel}" />' onclick="min_Dic.min_Dictionary(this,'YHRS0124',true,null,null,null,['bottom','left']) " notInclude=""/>
	                         <html:hidden name="pbYnGwEmployInfoForm" property="hisPositionLevel" />
	                         <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
	                     </label>
	                    </dd>
                      </dl>
                      <dl class="updown-dl">
	                       <dt><b class="Required">* </b>岗位聘任开始时间：</dt>
	                    <dd><label><html:text styleClass="modal_iput" styleId="hisBeginDateStr" name="pbYnGwEmployInfoForm" property="hisBeginDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                      </dl>
                      <dl class="updown-dl">
	                      <dt>岗位聘任拟截止时间：</dt>
	                    <dd><label><html:text styleClass="modal_iput" styleId="endDateStr" name="pbYnGwEmployInfoForm" property="endDateStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                      </dl>
                      <dl class="updown-dl">
	                       <dt>岗位聘任实际截止时间：</dt>
	                    <dd><label><html:text styleClass="modal_iput" styleId="endDateActualStr" name="pbYnGwEmployInfoForm" property="endDateActualStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></label></dd>
                      </dl>
                      <dl class="updown-dl">
	                     <dt>职务属性：</dt>
	                    <dd><label>
	                     <dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS0028%>" selected="${pbYnGwEmployInfoForm.hisDutyAttribute}" id="hisDutyAttributeName" name="hisDutyAttribute" emptyText="<%=Constant.EMPTY_SELECT %>" />
	                    </label></dd>
                     </dl>
                     
			   <dl class="updown-dl">
	                     <dt>是否兼职：</dt>
	                    <dd><label>
	                     <dictionary:dicItemSelect dicTypeCode="<%=DicConstants.YHRS0003%>" selected="${pbYnGwEmployInfoForm.isPartTime}" id="isPartTime" name="isPartTime" emptyText="<%=Constant.EMPTY_SELECT %>" />
	                    </label></dd>
              </dl>
              
              <dl class="updown-dl">
	                       <dt><b class="Required"> </b>所聘职称：</dt>
	                    <dd>
	                     <label>
	                        <input type="text" class="modal_iput" name="appointProfTitleName" readonly="readonly"  id="appointProfTitleCode" value='<dictionary:viewDicItemName dicTypeCode="YHRS0117" dicItemCode="${pbYnGwEmployInfoForm.appointProfTitleCode}" />' onclick="min_Dic.min_Dictionary(this,'YHRS0117',true,null,null,null,['bottom','left']) " notInclude=""/>
	                         <html:hidden name="pbYnGwEmployInfoForm" property="appointProfTitleCode"/>
	                         <a class="md-unit-clear" onclick="$(this).siblings('input').val('')"></a>
	                     </label>
	                    </dd>
                </dl>
                     
                <dl class="updown-remark">
                	<dt>备注：</dt>
							<dd><label title="${pbYnGwEmployInfoForm.remark}">
							<textarea name="remark" maxlength="1000">${pbYnGwEmployInfoForm.remark}</textarea>
							</label></dd>
                </dl>
            </div>
   
    
          </div>
          <div class="modal-footer">
	    	<button id="saveYnGwEmployInfo" type="button" class="btn btn-primary">保　存</button>
	    	<button type="button" class="btn btn-default close-popdown" data-dismiss="modal">取　消</button>
	    </div>
    </div>
	</div>
    
</form>
</body>
</html>
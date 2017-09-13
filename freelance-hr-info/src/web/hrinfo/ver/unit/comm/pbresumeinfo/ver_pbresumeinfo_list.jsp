<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    工作经历信息查看列表页面
 * @page name   /hrinfo/ver/unit/comm/pbresumeinfo/verpb_pbresumeinfo_create.jsp
 * @author      huangyj
 * @created     2017/02/13
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>信息校核列表页</title>
	<script type="text/javascript" src="hrinfo/ver/unit/comm/js/personinfocommeffect.js"></script>
	<script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
</head>
<logic:messagesPresent>
		<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
					<li>
						<bean:write name="error" />
					</li>
				</html:messages>
			</ul>
</logic:messagesPresent>
<body>
<div class="infoshow-container padding-lrb">
	<div class="st-title-box">
		<h3 class="st-title-text">工作经历信息</h3>
		<div class="st-title-icon st-title-button">
			<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbResumeInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
			<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPbResumeInfo('${param.personOid}')">上传附件</button>
		</div>
	</div>
	<div class="st-main-table">
		<table class="sr-table">
			<thead class="sr-table-thead">
			  <tr>
			    <th class="md-th"><input type="checkbox"></th>
          <th>开始时间</th>
          <th>截止时间</th>
          <th>工作单位</th>
          <th>职务</th>
          <th>操作</th>
        </tr>
			</thead>
			<tbody class="sr-table-tbody">
			  <c:forEach var ="dto" items="${list}">
			    <tr class="td_content">
            <td class="md-th"><input type="checkbox"/></td>
            <td title="${dto.startDateStr}">${dto.startDateStr}</td>
						<td title="${dto.endDateStr}">${dto.endDateStr}</td>
						<td title="${dto.unit}">${dto.unit}</td>
						<td title="${dto.duty}">${dto.duty}</td>
						<td>
						  <a class="st-handle-delete" href="javascript:void(0);" onclick="deletePbResumeInfo('${dto.resumeOid}')"></a>
						  <a class="st-handle-modify popdown btn" href="goToUpdatePbResumeInfoPage.do?method=goUpdate&urlId=${param.Id}&resumeOid=${dto.resumeOid}"></a>
            </td>
          </tr>
			  </c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>

<script type="text/javascript">

$(document).ready(function(){
    $('.popdown').popdown({width:1200});
})

function deletePbResumeInfo(resumeOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbResumeInfo.do?method=delete',
				data :  {resumeOid:resumeOid},
				dataType : 'json',
				error : function(x,t) {
					alert(t);
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
					}
					else
                    {
                        alert(data.message);
                    }
				}
			});
		}
	});
}
function uploadPbResumeInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '04'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>


<!--
 * @function    工作简历列表页面
 * @page name   /hrinfo/ver/unit/comm/pbresumeinfo/verpb_pbresumeinfo_create.jsp
 * @author      xiongyx
 * @created     2016/08/18
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>信息校核列表页</title>
 <link rel="stylesheet" href="css/messageCheckDetial_resume.css"/>
</head>
<div class="sys_box sys_box_up">
        <div class="sys_base"><span>工作简历</span>
            <!--<a style="float:right;margin-left: 20px;" href="#showmodal_resumeinfo" id="resumeinfo"  ><img src="img/DetailPages/icon_add_green.png"/></a>-->
             <div style="float:right;" >
              <logic:empty name="verPbResumeInfoDTO"> 
            <a href="javasript:void(0)" onclick="goCreatePbResumeInfo()"><img src="img/DetailPages/icon_add_green.png"/></a>
            </logic:empty>
             <logic:notEmpty name="verPbResumeInfoDTO"> 
              <logic:empty name="verPbResumeInfoDTO"  property="remark"> 
                   <a href="javasript:void(0)" onclick="goUpdatePbResumeInfo()"><img src="img/DetailPages/icon_add_green.png"/></a>
              </logic:empty>
              <logic:notEmpty name="verPbResumeInfoDTO" property="remark"> 
             <a style="margin-right: 15px;" href="javasript:void(0)" onclick="goUpdatePbResumeInfo()"><img src="img/DetailPages/icon_revise.png"/></a>
            <a style="margin-right: 20px;" herf="#" onclick="deleteResumeInfo('${verPbResumeInfoDTO.resumeOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
             </logic:notEmpty>
            </logic:notEmpty>
           </div>
             <!-- <a href="goToCreatePbResumeInfo.do?method=goToCreateVerPbResumeInfo&personOid=${param.personOid}&id=${param.Id}"  class="popdown btn" style="float:right;margin-left: 20px;"><img src="img/DetailPages/icon_add_green.png"/></a>-->
        </div>
       
        <logic:notEmpty name="verPbResumeInfoDTO">
          <html:form styleId="form_resume_update" styleClass="form-inline" action="verPbResumeInfoUpdate.do?method=updateVerPbResumeInfo" method="post" onsubmit="return false;">
           <input type="hidden" name="resumeOid" value="${verPbResumeInfoDTO.resumeOid}" />
            <input type="hidden" name="personOid" value="${verPbResumeInfoDTO.personOid}" />
          <div class="sys_list">
                <!-- 文本域-->
            <!-- 简历模板新增加地方-->
            <div class="re_Sample_box">
                <div class="re_Sample_resumes_left" id="update_remark_div" style="width: 99%;">
                    <textarea id="update_remark" name="remark" rows="16" cols="100%" readonly="true" style="padding-left:15px;padding-top:10px;
                    <logic:empty name="verPbResumeInfoDTO"  property="remark">style=display:none</logic:empty>">${verPbResumeInfoDTO.remark}</textarea>
                </div>
                <div class="re_Sample_resumes_right" id="pbresumes_update_template" style="display:none;height: 330px;">
                    <div class="Sample_resumes_box">简历范例</div>
                    <div class="Sample_resumes_dl Resume_dl" style="overflow-y: auto;overflow-x: auto;">
                        <dl class="row_float2 padding_left_0">
                            <dt>1999.07--2009.09</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">某某省某某县某某中学教师</label>
                            </dd>
                        </dl>
                        <dl class="row_float2 padding_left_0">
                            <dt>2009.09--2012.09</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">
                                    某某省某某市委宣传部干事、副科长（期间：2010.09--2010.09在某某大学某某专业学习）
                                </label>
                            </dd>
                        </dl>
                        <dl class="row_float2 padding_left_0">
                            <dt>2012.09--</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">
                                    四位的年+“.”+两位的月+“--”+四位的年+“.”+两位的月+两个空格+正文，如yyyy.mm-yyyy.mm xxxxxxxx</label>
                            </dd>
                        </dl>
                        
                    </div>
               
                <div style="clear: both"></div><!--清除浮动-->
          </div>
            </div>
             <div class="sys_base" id="update_button_div" style="display: none;" >
               <i style="margin-top: -10px;">
                <button class="btn_save" onclick="updatePbResumeInfo()">保存</button>
                <button class="btn_cancel" onclick="cancleUpdatePbResumeInfo()">取消</button>
              </i>
            
            </div>
            <!-- 简历模板新增加地方结束-->
             
        </div>
         </html:form>
        </logic:notEmpty> 
       
    <html:form styleId="form_resume_create" styleClass="form-inline" action="createPbResumeInfo.do?method=createVerPbResumeInfo" method="post" onsubmit="return false;">
        <input type="hidden" name="personOid" value="${personOid}" />
        <div class="sys_list" style="display:none" id="create_pbresumes_div">
             
            <!-- 文本域-->
            <!-- 简历模板新增加地方-->
            <div class="re_Sample_box">
                <div class="re_Sample_resumes_left" style="width:60%;">
                    <textarea name="remark" id="create_remark" rows="16" cols="100%" style="padding-left:15px;padding-top:10px;"></textarea>
                </div>
                <div class="re_Sample_resumes_right" style="height: 330px;">
                    <div class="Sample_resumes_box">简历范例</div>
                    <div class="Sample_resumes_dl Resume_dl" style="overflow-y: auto;overflow-x: auto;">
                        <dl class="row_float2 padding_left_0">
                            <dt>1999.07--2009.09</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">某某省某某县某某中学教师</label>
                            </dd>
                        </dl>
                        <dl class="row_float2 padding_left_0">
                            <dt>2009.09--2012.09</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">
                                    某某省某某市委宣传部干事、副科长（期间：2010.09--2010.09在某某大学某某专业学习）
                                </label>
                            </dd>
                        </dl>
                        <dl class="row_float2 padding_left_0">
                            <dt>2012.09--</dt>
                            <dd  title="1111 ">
                                <label type="text" value="">
                                    四位的年+“.”+两位的月+“--”+四位的年+“.”+两位的月+两个空格+正文，如yyyy.mm-yyyy.mm xxxxxxxx</label>
                            </dd>
                        </dl>
                    
                    </div>
                </div>
                <div style="clear: both"></div><!--清除浮动-->
            </div>
            <!-- 简历模板新增加地方结束-->
           
             <div class="sys_base" >
            <i style="margin-top: 10px;">
                <button class="btn_save" onclick="createPbResumeInfo()">保存</button>
                <button class="btn_cancel" onclick="cancleCreatePbResumeInfo()">取消</button>
            </i>
            </div>
          </div>  
       
       </html:form> 
       
       
        <!--<div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
                    <td>工作起始日期</td>
                	<td>工作结束日期</td>
              	 	<td>所在单位</td>
                	<td>从事工作或担任职务</td>
                    <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list }">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="${dto.startDateStr }">${dto.startDateStr }</td>
               		<td title="${dto.endDateStr }">${dto.endDateStr }</td>
               		<td title="${dto.unit }">${dto.unit }</td>
               		<td title="${dto.duty }">${dto.duty }</td>
                    <td><a id="deleteResumeInfo" href="javascript:void(0);" onclick="deleteResumeInfo('${dto.resumeOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <!-- <a  href="#update_resume" id="modaltriggerUpdate${dto.resumeOid}"  onclick="updateResumeInfo('${dto.resumeOid}')" ><img src="img/DetailPages/icon_revise.png"/></a> -->
                        <a href="goVerPbResumeInfoUpdate.do?method=goToUpdateVerPbResumeInfoPage&personOid=${param.personOid}&id=${param.Id}&resumeOid=${dto.resumeOid}"  class="popdown btn""><img src="img/DetailPages/icon_revise.png"/></a>
                        <script type="text/javascript">$('#modaltriggerUpdate${dto.resumeOid}').leanModal({ top: 150, overlay: 0.45, closeButton: ".hidemodal" });</script>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> -->
<script type="text/javascript">

    $(function() {
        //初始化remark转换
    	var remark = "${verPbResumeInfoDTO.remark}";
     	$('#update_remark').val(toTextarea(remark));

   });
    
    //删除工作简历
    function deleteResumeInfo(resumeOid){
    	MessageBox.confirm('提示', '确认删除？',function (yes){
		    if(yes=="yes"){
    	//alert(resumeOid);
    	//location.href = "deleteVerPbResumeInfo.do?method=deleteVerPbResumeInfo&resumeOid="+resumeOid;
		    	$.ajax({
		    		url : 'deleteVerPbResumeInfo.do?method=deleteVerPbResumeInfo',
		    		data :  {resumeOid:resumeOid},
		    		dataType : 'json',
		    		error : function(x,t) {
		    			MessageBox.alert("提示",t)
		    			MessageBox.alert("提示","error occured!!!");
		    		},
		    		async : false,
		    		success : function(data) {
		    			if (data.success) {
		    				$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
		    			}else{
		    				MessageBox.alert("提示",data.message);
			    	    }
		    		}
		    	});
		    }
		 });
    }
  
    //新增工作简历--显示隐藏模板
    function goCreatePbResumeInfo(){
    	$("#create_pbresumes_div").css("display","block");
    	var pos = $("#${param.Id}").offset();
		$("html,body").animate({ scrollTop: pos.top}, 1000);
    }

     //取消--隐藏模板
    function cancleCreatePbResumeInfo(){
    	$("#create_pbresumes_div").css("display","none");
    }
    
    //保存工作简历信息
    function createPbResumeInfo(){
        //得到输入的remark值
        var createRemark = $("#create_remark").val();
        if(createRemark.length==0){
        	MessageBox.alert("提示","请输入工作简历");
        	return ;
        }
        //保存前转换值
        $("#create_remark").val(textareaTo(createRemark));
        $.ajax({
            url : 'createPbResumeInfo.do?method=createVerPbResumeInfo',
            data :  $("#form_resume_create").serializeArray(),
            dataType : 'json',
            async : false,
            success : function(data) {
                if (data.success) {
                	$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
                }
                else
                {
                	$("#create_remark").val(toTextarea($("#create_remark").val()));
                	MessageBox.alert("提示",data.message);
                }
            }
        });
    }

    //点击修改工作简历
    function goUpdatePbResumeInfo(){
        
        $("#update_remark").css("display","block");
    	$("#update_remark").attr("readonly",false);//文本框变成可写
    	$("#pbresumes_update_template").css("display","block");//显示简历模板
    	$("#update_button_div").css("display","block");//显示保存取消按钮
    	$(".re_Sample_resumes_left").css("width","60%");//显示保存取消按钮
        //滑动效果
    	var pos = $("#${param.Id}").offset();
		$("html,body").animate({ scrollTop: pos.top}, 1000);
    }

    //取消修改工作简历
    function cancleUpdatePbResumeInfo(){
        var remark = "${verPbResumeInfoDTO.remark}";
    	
    	if(remark==null||remark.length==0){
        	
    		$('#update_remark').css("display","none");
        }else{
        	$('#update_remark').val(toTextarea(remark));
        }
    	$("#update_remark").attr("readonly",true);//文本框变成只读
    	$("#pbresumes_update_template").css("display","none");//隐藏简历模板
    	$("#update_button_div").css("display","none");//隐藏保存取消按钮
    	$(".re_Sample_resumes_left").css("width","99%");//显示保存取消按钮
    }

    //修改工作简历
    function updatePbResumeInfo(){
    	//得到输入的remark值
        var updateRemark = $("#update_remark").val();
        if(updateRemark.length==0){
        	MessageBox.alert("提示","请输入工作简历");
        	return ;
        }
        $("#update_remark").val(textareaTo(updateRemark));
    	$.ajax({
            url : 'verPbResumeInfoUpdate.do?method=updateVerPbResumeInfo',
            data :  $("#form_resume_update").serializeArray(),
            dataType : 'json',
            async : false,
            success : function(data) {
                if (data.success) {
                	$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
                }
                else
                {
                	$("#update_remark").val(toTextarea($("#update_remark").val()));
                	MessageBox.alert("提示",data.message);
                }
            }
        });
    }


    /**
    * @funciton 转换textarea存入数据库的回车换行和空格  textarea ---  数据库,用val取数据，置换'\n'
    */
    function textareaTo(str){
        var reg=new RegExp("\n","g");
        var regSpace=new RegExp(" ","g");
        
        str = str.replace(reg,"<br>");
        str = str.replace(regSpace,"&nbsp;");
        
        return str;
    }

    /**
    * @funciton  数据库 ---  编辑页面  .val(str)
    */
    function toTextarea(str){
        var reg=new RegExp("<br>","g");
        var regSpace=new RegExp("&nbsp;","g");
        
        str = str.replace(reg,"\n");
        str = str.replace(regSpace," ");
        
        return str;
    }
        
</script>
</html> --%>
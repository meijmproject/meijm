<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.admin.util.AdminConstants"%>
<%@taglib uri="dictionary.tld" prefix="dictionary" %>
<%@taglib uri="c.tld" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户信息修改页面</title>
	<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
   	<script src="admin/users/js/UpdateUsers.js"></script>
   	<script type="text/javascript" src="js/comm/customJs.js"></script>
   	<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script>
   	<script type="text/javascript">
/* $(function(){
	$.post("findAllSelectUnit.do?method=findAllSelectUnit",function(data){
		var ul = $("<ul class='search_ul_dictionary1  search-horizontal_2_ul_top' id='form_ul' style='display:none;'></ul>").insertAfter($("#unitAllName"));
		for (var i = 0; i < data.length; i++) {
			$("<li id='"+data[i].unitOid+"' title='"+data[i].unitName+"'>"+data[i].unitName+"</li>").appendTo(ul).click(function(){
				$("#unitAllName").val($(this).text());
				$("#unitOid").val($(this).attr('id'));
				$('#form_ul').hide();
				var  unitOid= $(this).attr('id');
				$.ajax({
					url : 'changeOrgByUnitOid.do?method=changeOrgByUnit',
					data :  {unitOid:unitOid},
					dataType : 'json',
					success : function(data) {
						if(null != data.rs)
						{
							document.getElementById("postDept").innerHTML = "";
							document.getElementById("postDept").value ='';
							for(var i=0;i<data.rs.length;i++)
							{
								document.getElementById("postDept").options[i] = new Option(data.rs[i].orgName,data.rs[i].orgOid);
							}
						}
					}
				});
			});
		}	
		
		$(document).unbind('click.form_ul').bind('click.form_ul', function(e){
			ul.hide();
		});
		$("#unitAllName").click(function(e){
			$("#unitOid").val('');
			if($('#form_ul').is(':hidden')){
				$('#form_ul').show();
			}else{
				$('#form_ul').hide();
			}
			e.stopPropagation();
			e.preventDefault();
		}).keyup(function(){
			$("#unitOid").val('');
			$list=$('#form_ul');
			var unitName= $("#unitAllName").val();
			if(unitName){
				var liList = $list.find('li:contains(' + unitName + ')');
				$('li', $list).not($(liList)).slideUp();
				$(liList).slideDown();
			}else{
				$list.find("li").slideDown();
			}
			return false;
			
		})
	},'json');
}); 
function clean()
	{
	if(document.getElementById("postDept").length>1){
		document.getElementById("postDept").length=0;
		document.getElementById("postDept").options[0] = new Option('--请选择--',null);
		}
	} */
	var lastKeyTime, onSearching;
   	function listPersonInfoByName(e) {
   		lastKeyTime = e.timeStamp;
   		setTimeout(function() {
   			if(lastKeyTime==e.timeStamp&&!onSearching) {
   				var name = $('#userName').val();
   				$('#personUl').empty();
   				$('#personOid').val(null);
   				onSearching = true;
   				$.post("listPbPersonInfo.do?method=listPbPersonInfo&name="+name,function(data){
   					if(data.rows.length>0){
   						var list = data.rows;
   						$('#personUl').show();
   						for (var i = 0; i < list.length; i++) {
   							list[i] && 
   							$("<li class='s-pdl-li' id='"+list[i].personOid+"'>"+list[i].name+"</li>").appendTo($('#personUl')).click(function(){
   								$('#refOid').val($(this).data().personOid);
   								$('#userName').val($(this).data().name);
   								$('#userSex').val($(this).data().sexCode);
   								$('#userSexDesc').val($(this).data().sexCodeDesc);
   								//$('#deptName').val($(this).data().hireDeptName);
   								$('#contactPhone').val($(this).data().mobilePhone);
   								$('#address').val($(this).data().address);
   								$.post('getHireDeptName.do?method=getHireDeptName&hireDeptOid='+$(this).data().hireDeptOid, function(resp) {
   									$('#deptName').val(resp);
   								});
   								$('#personUl').hide();
   							}).data(list[i]);
   						}
   					}
   					onSearching = false;
   				},'json');
   			}
   		},1000);
	}
	$(function() {
		$("#userName").click(function(e) {
			$('#personUl').toggle();
		})
	});
</script>
</head>
<body>
<div id="showmodal" class="modal-content">
   <form id="formUsersUpdate" class="form-inline" action="" method="post"  onsubmit="return false">
    	<div class="modal-header">
            <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
            </button>
            <h4 class="modal-title">用户信息&gt;修改</h4>
    	</div>		
      <div class="modal-body">	
      	   <input style="display: none;" name="userOid" value="${usersForm.userOid }"/>
    	   <input style="display: none;" name="password" value="${usersForm.password }"/>
    	   <input style="display: none;" name="defaultLoginSystem" value="${usersForm.defaultLoginSystem }"/>
    	   <input style="display: none;" name="registDateStr" value="${usersForm.registDateStr }"/>   
	        <input style="display: none;" type="text"  id="refOid" name="refOid" value="${usersForm.refOid }"  /> 
	        <input style="display: none;" type="text"  id="refType" name="refType" value="${usersForm.refType }"  />   	
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
            	<div class="modal-row03">
            		<dl>
            			<dt><b class="Required">* </b>用户ID</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" id="userId" name="userId" value="${usersForm.userId }" readonly="readonly"  /> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt><b class="Required">* </b>姓名：</dt>
            			<dd>
            				<label>
                              <input class="modal_iput" type="text" id="userName" placeholder="请输入姓名" name="userName" value="${usersForm.userName }" onkeyup="listPersonInfoByName(event)" autocomplete="off" /> 
                           </label>
				  			<ul class="s-pulldown-list" id="personUl" style="display: none; width: 200px;position:absolute;text-align:left;font-weight:bold;">
							</ul>
            			</dd>
            		</dl>
            		<dl>
            			<dt>隶属单位：</dt>
            			<dd>
            				<label>
	                             <input name='unitName' id='unitAllName' readonly type='text' autocomplete='off' 
            				  value='${usersForm.unitName }' />
            				 <input id='unitOid'  name='unitId' hidden='hidden' value='${usersForm.unitId }'/>
	                      	</label>
            			</dd>
            		</dl>
            	</div>
            	<div class="modal-row03">
            		<dl>
            			<dt><b class="Required">* </b>用户状态：</dt>
            			<dd>
            				<label>
	                             <dictionary:dicItemSelect styleClass="modal_select"  id="userStatus" name="userStatus" dicTypeCode="<%=AdminConstants.YHRS9002%>" selected="0"   emptyText="--请选择--"/> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt>用户类型：</dt>
            			<dd>
            				<label>
	                             <dictionary:dicItemSelect  styleClass="modal_select" id="userType" name="userType" dicTypeCode="<%=AdminConstants.YHRS9001%>"  selected="01" notInclude="01,02,03,04,09"/> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt>所属科室：</dt>
            			<dd>
            				<label>
            					<input  type="text" id="deptName" name="deptName" value="${usersForm.deptName}" class="modal_iput" /> 
	                      	</label>
            			</dd>
            		</dl>
            	</div>
            	<div class="modal-row03">
            		<dl>
            			<dt><b class="Required">* </b>生效日期：</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" id="effectiveDateStr" name="effectiveDateStr" value="${usersForm.effectiveDateStr }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"  /> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>   
                        <dt>性别：</dt>
                         <dd>
                          	<label>
                                  <input class="modal_iput" type="text" readonly="readonly" id="userSexDesc" value="${usersForm.userSexDesc }" />
                                 <input type="hidden"  id="userSex" name="userSex" value="${usersForm.userSex }" />
                          	</label>
                         </dd>
                    </dl>
                    <dl> 
                        <dt>联系电话：</dt>
                         <dd>
                         	<label>
                            	<input class="modal_iput" type="text"  id="contactPhone" name="contactPhone" value="${usersForm.contactPhone }" /> 
                            </label>
                         </dd>
                    </dl>
                </div>
            	<div class="modal-row03">
            		<dl>
            			<dt>失效日期：</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" id="expiredDateStr" name="expiredDateStr"  value="${usersForm.expiredDateStr }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> 
	                      	</label>
            			</dd>
            		</dl>
            		
            		<dl>
            			<dt>地址：</dt>
            			<dd>
            				<label>
	                             <input  type="text" class="modal_iput" name="address" id="address" value="${usersForm.address }" />  
	                      	</label>
            			</dd>
            		</dl>
            	</div>
                <%-- <div class="search search_div"> 
                     <dl class="search-horizontal_2">
                     <dt class="search-horizontal_2_dd_background" ><b class="Required">* </b>用户ID</dt>
                     <dd>
                      	<label>
                             <input type="text" id="userId" name="userId" value="${usersForm.userId }" readonly="readonly"  /> 
                      	</label>
                     </dd>
                    </dl>
                    <dl class="search-horizontal_2">
                        <dt  class="search-horizontal_2_dd_background"><b class="Required">* </b>姓名：</dt>
                         <dd>
                           <label>
                              <input  type="text" id="userName" placeholder="请输入姓名" name="userName" value="${usersForm.userName }" onkeyup="listPersonInfoByName()" autocomplete="off" /> 
                           </label>
				  			<ul class="s-pulldown-list" id="personUl" style="display: none; width: 200px;position:absolute;text-align:left;font-weight:bold;">
							</ul>
                         </dd>
                    </dl> 
                    <dl class='search-horizontal_2'>
    				   <dt class="search-horizontal_2_dd_background"><b class="Required">* </b>隶属单位：</dt>
    				   <dd>
        				<div class='input_wrap_model_min'>
            				<input name='unitName' id='unitAllName'  class='search_input_dictionary' type='text' autocomplete='off' 
            				  value='${usersForm.unitName }' />
            				 <input id='unitOid'  name='unitId' hidden='hidden' value='${usersForm.unitId }'/>
        				</div>
   				 	   </dd>
					</dl>
                    <dl class="search-horizontal_2">
                        <dt class="search-horizontal_2_dd_background" >用户状态：</dt>
                          <dd>
                          	<label>
                                <dictionary:dicItemSelect styleClass="modal_select"  id="userStatus" name="userStatus" dicTypeCode="<%=AdminConstants.YHRS9002%>" selected="${usersForm.userStatus}"   emptyText="--请选择--"/>
                          	</label>
                          </dd>
                     </dl>
                    <dl class="search-horizontal_2">   
                      <dt class="search-horizontal_2_dd_background"><b class="Required">* </b>用户类型：</dt>
                      <dd>
                       	<label>
                      		<dictionary:dicItemSelect  styleClass="modal_select" id="userType" name="userType" dicTypeCode="<%=AdminConstants.YHRS9001%>"  selected="${usersForm.userType}" emptyText="--请选择--" notInclude="02,03,09"/>
                      	</label>
                      </dd>
                    </dl>   
                    <dl class="search-horizontal_2">  
                      <dt class="search-horizontal_2_dd_background">所属科室：</dt>
                        <dd>
                          <label>
                           <input  type="text" id="deptName" name="deptName"  value="${usersForm.deptName }" />
                          </label>
                        </dd>
                     </dl>
                     <dl class="search-horizontal_2">      
                        <dt class="search-horizontal_2_dd_background">生效日期：</dt>
                          <dd>
                            <label>
                               <input  type="text" id="effectiveDateStr" name="effectiveDateStr" value="${usersForm.effectiveDateStr }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"  />
                            </label>
                           </dd>
                     </dl>
                     <dl class="search-horizontal_2">   
                        <dt class="search-horizontal_2_dd_background" >性别：</dt>
                         <dd>
                          	<label>
                                  <input type="text" readonly="readonly" id="userSexDesc" value="${usersForm.userSexDesc }" />
                                 <input type="hidden"  id="userSex" name="userSex" value="${usersForm.userSex }" />
                          	</label>
                         </dd>
                     </dl>
                     
                    
                     <dl class="search-horizontal_2"> 
                        <dt class="search-horizontal_2_dd_background">联系电话：</dt>
                         <dd>
                         	<label>
                            	<input  type="text"  id="contactPhone" name="contactPhone" value="${usersForm.contactPhone }" /> 
                            </label>
                         </dd>
                     </dl>
                      <dl class="search-horizontal_2">          
                        <dt class="search-horizontal_2_dd_background">失效日期：</dt>
                          <dd>
                           <label>
                             <input  type="text" id="expiredDateStr" name="expiredDateStr"  value="${usersForm.expiredDateStr }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                           </label>
                           </dd>
                     </dl>
                     <dl class="search-horizontal_2">       
                        <dt class="search-horizontal_2_dd_background">地址：</dt>
                         <dd>
                           <label>
                            <input  type="text"  name="address" id="address" value="${usersForm.address }" /> 
                           </label>
                         </dd>
                     </dl>
          	</div> --%>
           </div>	
          <div style="clear: both"></div>
      	  <div class="modal-footer">
               <input  type="submit" class="btn btn-primary" value="保　存">
               <button type="button" class="btn btn-default close-login" data-dismiss="modal" button-click="widget-close">
               <a href="#" class="close-popdown">取　消</a></button>
          </div>
    </form>
  </div>  
</body>
</html>
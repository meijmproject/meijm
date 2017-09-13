<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%>
<%@page import="com.yh.admin.util.AdminConstants"%>
<%@taglib uri="dictionary.tld" prefix="dictionary" %>
<%@taglib uri="c.tld" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="l">
    <meta charset="UTF-8">
    <title>增加用户页面</title>
	<link href="hspszhphtml/css/components/search_unit.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/comm/customJs.js"></script>
   	<script src="admin/users/js/CreateUsers.js"></script>
   	<script type="text/javascript" src="hrworktop/flow/selectUnit.js"></script>
   	<script>
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
			if($('#personUl').has('li').length==0)  return;
			$('#personUl').toggle();
		})
	});
   	</script>
</head>
<body>
<div id="showmodal" class="modal-content">
   <form id="formUsersCreate" class="form-inline" action="" method="post" onsubmit="return false">
    	<div class="modal-header">
            <button type="button" class="close close-popdown" data-dismiss="modal" aria-label="Close" button-click="widget-close">
            	<span aria-hidden="true" >&times;</span>
            </button>
            <h4 class="modal-title">用户信息&gt;新增</h4>
    	</div>		
     	<div class="modal-body">	
                <input style="display: none;" type="text"  id="refOid" name="refOid"  /> 
                <input style="display: none;" type="text"  id="refType" name="refType"  /> 	
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
	                             <input type="text" id="userId" name="userId" class="modal_iput" /> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt><b class="Required">* </b>姓名：</dt>
            			<dd>
            				<label>
                              <input type="text" id="userName" class="modal_iput" placeholder="请输入姓名" name="userName" onkeyup="listPersonInfoByName(event)" autocomplete="off" />
                           </label>
				  			<ul class="s-pulldown-list" id="personUl" style="display: none; width: 200px;position:absolute;text-align:left;font-weight:bold;">
							</ul>
            			</dd>
            		</dl>
            		<dl>
            			<dt>隶属单位：</dt>
            			<dd>
            				<label>
	                             <input name='unitName' id='unitAllName' value='${adminUnits.unitName}' readonly class='search_input_dictionary' type='text' autocomplete='off' />
           				 <input id='unitOid'  name='unitId' hidden='hidden' value='${adminUnits.unitOid}' />
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
	                             <dictionary:dicItemSelect  styleClass="modal_select" id="userType" name="userType" dicTypeCode="<%=AdminConstants.YHRS9001%>"  selected="01" notInclude="02,03,09"/> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt>所属科室：</dt>
            			<dd>
            				<label>
	                             <input  type="text" id="deptName" name="deptName" class="modal_iput" /> 
	                      	</label>
            			</dd>
            		</dl>
            	</div>
            	<div class="modal-row03">
            		<dl>
            			<dt><b class="Required">* </b>生效日期：</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" id="effectiveDateStr" name="effectiveDateStr"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" /> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>
            			<dt>失效日期：</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" id="expiredDateStr" name="expiredDateStr"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" /> 
	                      	</label>
            			</dd>
            		</dl>
            		<dl>   
                        <dt>性别：</dt>
                         <dd>
                          	<label>
                                 <input type="text" class="modal_iput" readonly="readonly" id="userSexDesc" name="userSexDesc" />
                                 <input type="hidden"  id="userSex" name="userSex" />
                          	</label>
                         </dd>
                    </dl>
                </div>
            	<div class="modal-row03">
                    <dl> 
                        <dt>联系电话：</dt>
                         <dd>
                         	<label>
                            	<input class="modal_iput" type="text"  id="contactPhone"  name="contactPhone"  /> 
                            </label>
                         </dd>
                    </dl>
            		<dl>
            			<dt>地址：</dt>
            			<dd>
            				<label>
	                             <input class="modal_iput" type="text" name="address" id="address"  />  
	                      	</label>
            			</dd>
            		</dl>
            	</div>
                <%-- <div class="search search_div"> 
                     <dl class="search-horizontal_2">
                     <dt class="search-horizontal_2_dd_background" ><b class="Required">* </b>用户ID</dt>
                     <dd>
                      	<label>
                             <input type="text" id="userId" name="userId"  /> 
                      	</label>
                     </dd>
                    </dl>
                    <dl class="search-horizontal_2">
                        <dt  class="search-horizontal_2_dd_background"><b class="Required">* </b>姓名：</dt>
                         <dd>
                           <label>
                              <input type="text" id="userName" placeholder="请输入姓名" name="userName" onkeyup="listPersonInfoByName()" autocomplete="off" />
                           </label>
				  			<ul class="s-pulldown-list" id="personUl" style="display: none; width: 200px;position:absolute;text-align:left;font-weight:bold;">
							</ul>
                         </dd>
                    </dl> 
                    <dl class='search-horizontal_2'>
    				   <dt class="search-horizontal_2_dd_background"><b class="Required">* </b>隶属单位：</dt>
    				   <dd>
        				<input name='unitName' id='unitAllName' value='${adminUnits.unitName}' readonly class='search_input_dictionary' type='text' autocomplete='off' />
           				 <input id='unitOid'  name='unitId' hidden='hidden' value='${adminUnits.unitOid}' />
   				 	   </dd>
					</dl>
                    <dl class="search-horizontal_2">
                        <dt class="search-horizontal_2_dd_background" >用户状态：</dt>
                          <dd>
                          	<label>
                                <dictionary:dicItemSelect styleClass="modal_select"  id="userStatus" name="userStatus" dicTypeCode="<%=AdminConstants.YHRS9002%>" selected="0"   emptyText="--请选择--"/>
                          	</label>
                          </dd>
                     </dl>
                    <dl class="search-horizontal_2">   
                      <dt class="search-horizontal_2_dd_background"><b class="Required">* </b>用户类型：</dt>
                      <dd>
                       	<label>
                      		<dictionary:dicItemSelect  styleClass="modal_select" id="userType" name="userType" dicTypeCode="<%=AdminConstants.YHRS9001%>"  selected="01" emptyText="--请选择--" notInclude="02,03,09"/>
                      	</label>
                      </dd>
                    </dl>   
                    <dl class="search-horizontal_2">  
                      <dt class="search-horizontal_2_dd_background">所属科室：</dt>
                        <dd>
                          <label>
						    <input  type="text" id="deptName" name="deptName"  />
                          </label>
                        </dd>
                     </dl>
                     <dl class="search-horizontal_2">      
                        <dt class="search-horizontal_2_dd_background">生效日期：</dt>
                          <dd>
                            <label>
                               <input  type="text" id="effectiveDateStr" name="effectiveDateStr"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
                            </label>
                           </dd>
                     </dl>
                     <dl class="search-horizontal_2">   
                        <dt class="search-horizontal_2_dd_background">性别：</dt>
                         <dd>
                          	<label>
                                 <input type="text" readonly="readonly" id="userSexDesc" name="userSexDesc" />
                                 <input type="hidden"  id="userSex" name="userSex" />
                          	</label>
                         </dd>
                     </dl>
                     
                    
                     <dl class="search-horizontal_2"> 
                        <dt class="search-horizontal_2_dd_background">联系电话：</dt>
                         <dd>
                         	<label>
                            	<input  type="text"  id="contactPhone"  name="contactPhone"  /> 
                            </label>
                         </dd>
                     </dl>
                      <dl class="search-horizontal_2">          
                        <dt class="search-horizontal_2_dd_background">失效日期：</dt>
                          <dd>
                           <label>
                             <input  type="text" id="expiredDateStr" name="expiredDateStr"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                           </label>
                           </dd>
                     </dl>
                     <dl class="search-horizontal_2">       
                        <dt class="search-horizontal_2_dd_background">地址：</dt>
                         <dd>
                           <label>
                            <input  type="text" name="address" id="address"  /> 
                           </label>
                         </dd>
                     </dl>
          	</div> --%>
           </div>	
      	  <div class="modal-footer">
               <input  type="submit" class="btn btn-primary" value="保　存">
               <button type="button" class="btn btn-default close-login" data-dismiss="modal" button-click="widget-close">
               <a href="#" class="close-popdown">取　消</a></button>
          </div>
     </form>
    </div>
</body>

</html>
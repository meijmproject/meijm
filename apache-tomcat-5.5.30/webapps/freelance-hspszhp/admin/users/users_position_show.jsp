<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户岗位信息页面</title>
</head>
<body>
<!--中间主体内容-->
  <div class="sys_box" style="height: 710px; padding-bottom: 0px;">
        <div class="sys_base" ><span>岗位信息</span>
        </div>
        <div class="sys_list">
               <div class="sys_box" >
			    <c:forEach items="${userPositionInfo}" var="usInfo" varStatus="status">
			        <div class="sys_base"><span>${usInfo.systemPositionName}</span>
			        	<input id="systemPositionOid" type="text" style="visibility: hidden;" value="${usInfo.systemPositionOid}"/>
			        </div>
			     <div class="sys_list">
			          <div>
			                <dl class="row_float">
			                    <dt>岗位名称</dt>
			                    <dd>${usInfo.systemPositionName}</dd>
			                </dl>
			                <dl class="row_float">
			                    <dt>功能权限</dt>
			                    <dd>${usInfo.functionRoleName}</dd>
			                </dl>
			                 <dl class="row_float">
			                    <dt>岗位生效日期</dt>
			                    <dd>${usInfo.effectiveDt}</dd>
			                </dl>
			                <div style="clear: both"></div>
			          </div>
			          <div>
			            	<dl class="row_float">
			                    <dt>描述</dt>
			                    <dd>${usInfo.systemPositionDesc}</dd>
			                </dl>
			                <dl class="row_float">
			                    <dt>数据权限</dt>
			                    <dd>${usInfo.dataRoleName}</dd>
			                </dl>
			                <dl class="row_float">
			                    <dt>岗位失效日期</dt>
			                    <dd>${usInfo.expiredDt }</dd>
			                </dl>
			                <div style="clear: both"></div>
			          </div>
			     </div>
		       </c:forEach> 
		  </div>
		 <div style="clear: both"></div>
	   </div>
	</div>
	<div style="clear: both"></div>
</body>
</html>
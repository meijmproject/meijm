<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>用户信息</title>
</head>
<body>
<!--左导航-->
    <div class="sys_base">用户信息:<a id="updateUserInfo" style="display: none;" href="javascript:void(0);" onclick="updateUserInfo(${usersForm.userOid})" style="float:right;margin-left: 20px;">
		<img src="img/DetailPages/icon_revise.png"/></a> 
	</div>
    <input hidden="hidden" value="${usersForm.userOid}" id="userOid" />
    <ul class="user_infor">
        <li class="user_front" >姓名：</li>
        <li class="user_latter">
         ${usersForm.userName }
        </li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">用户ID：</li>
        <li class="user_latter" id="userId" >${usersForm.userId }</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">用户类型：</li>
        <li class="user_latter"  >  ${usersForm.userType}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_infor">
        <li class="user_front">用户状态：</li>
         <li class="user_latter"  >  ${usersForm.userStatus}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">联系电话：</li>
         <li class="user_latter"  >  ${usersForm.contactPhone}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">隶属单位：</li>
         <li class="user_latter"  >  ${usersForm.unitName}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">隶属内设机构：</li>
         <li class="user_latter"  >  ${usersForm.deptName}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">注册日期：</li>
         <li class="user_latter"  >  ${usersForm.registDateStr}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">生效日期：</li>
         <li class="user_latter" >${usersForm.effectiveDateStr}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">失效日期：</li>
         <li class="user_latter"  > ${usersForm.expiredDateStr}</li>
    </ul>
    <div style="clear: both"></div>
     <ul class="user_infor">
        <li class="user_front">地址：</li>
         <li class="user_latter"> ${usersForm.address}</li>
    </ul>
    <div style="clear: both"></div>
</body>
</html>
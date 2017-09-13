<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>数据角色信息</title>
</head>
<body>
<!--左导航-->
	<div style="clear: both"></div>
    <div class="sys_base"> 数据角色信息:
	   <a id="updateDatarolesImg" style="float: right; margin-left: 20px;" onclick="updateDataRoles()"><img src="img/DetailPages/icon_revise.png" title="修改"/></a>
    </div>
 	<div style="clear: both"></div>
    <ul class="user_ul">
        <li class="li_front">角色名称：</li>
        <li class="li_latter">${rolesDTo.roleName}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_ul">
        <li class="li_front">描述：</li>
        <li class="li_latter">${rolesDTo.roleDesc}</li>
    </ul>
</body>
</html>
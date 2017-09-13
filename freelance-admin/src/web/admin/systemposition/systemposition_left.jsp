<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
<style type="text/css">
.words{
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    width: 160px;
}
</style>
</head>
<body>
    <div style="clear: both"></div>
    <div class="sys_base">岗位信息:
        <a href="javascript:void(0);" onclick="update()" style="float:right;margin-left: 20px;">
            <img src="img/DetailPages/icon_revise.png"  class="icon_revise" alt=""/>
        </a>
    </div>
    <ul class="user_ul">
        <li class="li_front">系统岗位名称:</li>
        <li class="li_latter">${systemPositionForm.systemPositionName}</li>
    </ul>
    <ul class="user_ul">
        <li class="li_front">系统岗位描述:</li>
        <li class="li_latter">${systemPositionForm.systemPositionDesc}</li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_ul">
        <li class="li_front">数据权限角色:</li>
        <li class="li_latter words" title="${systemPositionForm.dataRoleName}">${systemPositionForm.dataRoleName}</li>
        <li class="li_img">
            <a href="javascript:void(0);" onclick="findRoleList(1)" > <img src="img/DetailPages/icon_revise.png" alt=""/></a>
        </li>
    </ul>
    <div style="clear: both"></div>
    <ul class="user_ul">
        <li class="li_front">功能权限角色:</li>
        <li class="li_latter words" title="${systemPositionForm.functionRoleName}">${systemPositionForm.functionRoleName}</li>
        <li class="li_img">
            <a href="javascript:void(0);" onclick="findRoleList(0)" ><img src="img/DetailPages/icon_revise.png" alt=""/></a>
        </li>
    </ul>
    <div style="clear: both"></div>
</body>
</html>
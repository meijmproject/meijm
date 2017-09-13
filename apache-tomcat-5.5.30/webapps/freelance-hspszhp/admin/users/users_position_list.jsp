<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户岗位信息页面</title>
    <%@ include file="/include/js_css_admin_include.jsp"%>
</head>
<div class="sitemap">
    <ul style="float: left">
    	<li>当前位置：</li>
        <li><a href="#">系统管理<span class="spanColor"> > </span></a></li>
        <li><a href="#">系统安全<span class="spanColor"> > </span></a></li>
        <li><a href="#">用户管理</a></li>
    </ul>
     <div class="go_back"><a href="javascript:void(0);" onclick="goback();"><span style="font-family: sans-serif;"><< </span>返回</a></div>
    <div style="clear: both"></div>
</div>
<script type="text/javascript">
$(function(){
	Widget.load('#left_nav');
	Widget.load('#updateUSP');
})
function updateUserInfo(userOid){
	if (userOid) {
		 Widget.openContent("goUpdateUsers.do?method=goUpdateUsers&userOid="+userOid,
				function(){
			}); 
		}
}
//用户岗位添加
function addUserPosition(){
	var userId =$("#userId").html();
	var userOid=$("#userOid").val();
	var isOPen=true;
	if(userId&&userOid){
		//location.href = "usersPositionListPage.do?method=usersPositionListPage&userId="+userId+"&userOid="+userOid;
		Widget.openContent("usersPositionListPage.do?method=usersPositionListPage&userId="+userId+"&userOid="+userOid,
			function(){
			});
		}
}
//用户岗位移除
function removePosition(systemPositionOid){
	var userId =$("#userId").html();
	var userOid=$("#userOid").val();
	if(userId&&systemPositionOid){
		//Widget.openContent("goUsersPositionUpdate.do?method=goUsersPositionUpdate&userId="+userId+"&systemPositionOid="+systemPositionOid);
		MessageAction.yeah('请确认是否删除?', function(){
			$.ajax({
	       		url : 'removeUsersPosition.do?method=removeUsersPosition',
	           	data :  {systemPositionOid:systemPositionOid,userId:userId,userOid:userOid},
	           	dataType : 'json',
	           	type:'POST',
	           	error : function(x,t) {
	           		alert(t)
	           	},
	           	async : false,
	           	success : function(data) {
	               if (data.success) {
						//window.location.reload();
	            	   Widget.load('#updateUSP');
	               }
	               else
	               {
	               	alert(data.message);
	               }
	           }
	       	});

			});
		}
	}
//用户岗位修改
function updatePosition(systemPositionOid){
	var userId =$("#userId").html();
	if(userId&&systemPositionOid){
		//location.href ="goUsersPositionUpdate.do?method=goUsersPositionUpdate&userId="+userId+"&systemPositionOid="+systemPositionOid;
		 Widget.openContent("goUsersPositionUpdate.do?method=goUsersPositionUpdate&userId="+userId+"&systemPositionOid="+systemPositionOid,
		function(){
			//Widget.load('#updateUSP');
			});
			}
	}
//返回
function goback(){
	HistoryRegister.go('goBackUrl');
}
</script>
<body>
<!--左导航  用户信息-->
<div class="left_nav" id="left_nav" url='updateUserInfo.do?method=updateUserInfo&userOid=${usersForm.userOid }'>
</div>

<!--中间主体内容  用户岗位信息-->
<div class="right_content main_bg" id="updateUSP" url='updateUSP.do?method=updateUSP&userId=${usersForm.userId }' ></div>

</body>
</html>
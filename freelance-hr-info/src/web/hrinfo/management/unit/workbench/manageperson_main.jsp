<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>信息校核列表页</title>
    <%@ include file="/include/js_css_base_include.jsp"%>
</head>
<body>
<div>
<div class="current-position position-fixed">
    	当前位置：
        <span>基础信息管理 > </span>
        <span>信息管理> </span>
        <span>人员信息管理 </span>
        <button class="btn-return"> &lt; 返回</button>
    <div style="clear: both"></div>
</div>
<%-- <div class="left">
    <div class="name_box">
        <ul id="personList">
        </ul>
    </div>
</div>
<div class="right">
			<div class="nav_box" id='informationUrl'>
				<ul id="informationList" class="right_navtext">
				
			</div>
		</div>
<div class="center" id="center" style="margin-top:70px;">
</div>
</div> --%>
<%-- <input type="hidden" id="unitOid" name="unitOid" value="${unitOid}" /> --%>
<div class="main-container">
    <div id="left-column" class="left-column">
       <div class=" infoshow-container">
			<a class="nl-previous"></a>
	        <ul id="personList" class="nl-main">
	        </ul>
	        <a class="nl-next"></a>
		</div>
    </div>
     <div id="top"></div>
    <div id="center" class="main-column">
    </div>
    <div  class="right-column">
    <div class="infoshow-container" id="informationUrl"></div>
    </div>
    <div class="clear"></div>
</div>
</body>
<script>
$(window).resize(function(){$(".scroll_auto").css("height",$(window).height() -48);}).resize();
var personOid=${param.personOid};
var condition=${param.condition}; 
var functionCode=condition.functionCode;
var pageIndex=(parseInt(condition.pageSize)*(parseInt(condition.pageNo)-1)+parseInt(condition.index))%10;
if(pageIndex==0){
	condition.pageNo=Math.floor((parseInt(condition.pageSize)*(parseInt(condition.pageNo)-1)+parseInt(condition.index))/10);
}else{
	condition.pageNo=Math.floor((parseInt(condition.pageSize)*(parseInt(condition.pageNo)-1)+parseInt(condition.index))/10)+1;
}

$(document).ready(function(){
	findInfor(condition,personOid);   
	$(".btn-return").click(function(){
		HistoryRegister.go("goBackUrl");
	})
});
function goback(){
	HistoryRegister.go("goBackUrl");
	//location.href=goBackUrl;
}
$('.nl-previous').click(function(){
	condition.pageNo=parseInt(condition.pageNo)-1;
	if(condition.pageNo<=0){
		MessageBox.alert('提示', '无上一页!');
		return;
	}
	$("#personList li").remove();
	findInfor(condition,personOid);
})
$('.nl-next').click(function(){
	condition.pageNo=parseInt(condition.pageNo)+1;
	 if((parseInt(condition.pageNo)-1)>Math.floor(parseInt(condition.total)/10)){
		 MessageBox.alert('提示', '已是最后一页!');
		return;
	} 
	$("#personList li").remove();
	findInfor(condition,personOid);
})
function findInfor(condition,personOid){
	$.ajax({
		url : 'goFindPersonList.do?method=findAllPerson&functionCode=personManage',
		data : condition,
		dataType : 'json',
		type:'POST',
		error : function() {
			alert("error occured!!!   goFindPersonList.do");
		},
		async : true,
		success : function(data) {
			
			var li="";
			var item=eval(data.rows);
			$.each(item,function(index) {
				var personType=item[index].personType;
				var personId=item[index].personOid;
				var isLock=item[index].isLock;
				var isUnitLock=item[index].isUnitLock;
				if(item[index].personOid==personOid){
					li+="<li title='"+item[index].name+"'><a href='javascript:void(0);' class='nl-name-a nl-name-aselect' onclick=findPersonType(this,'"+personType+"','"+personId+"','"+isLock+"','"+isUnitLock+"')>"+item[index].name+"</a></li>";
					findPersonType(this,personType,personId,isLock,isUnitLock);
					
				  }else{
				    li+="<li title='"+item[index].name+"'><a href='javascript:void(0);' class='nl-name-a' onclick=findPersonType(this,'"+personType+"','"+personId+"','"+isLock+"','"+isUnitLock+"')>"+item[index].name+"</a></li>";
				}
			});
			$("#personList").append(li);
		}
	});
}
function findPersonType(obj,personType,personId,isLock,isUnitLock){
	if(isLock=='1'){
		MessageBox.alert('提示', '人员已被锁定，无法修改!');
		return;
	}
	if (isUnitLock == '1') {
		MessageBox.alert('提示', '单位已被锁定，无法修改!');
		return;
	}
	$('#informationUrl').children().remove();
	var liList=$("#personList a");
	$.each(liList,function(index){
		$(liList[index]).removeClass("nl-name-aselect");
	})
	$(obj).addClass("nl-name-aselect"); 
	$.ajax({
		url : 'goFindInformationList.do?method=findInByfunctionCode',
		data : {functionCode:functionCode},
		dataType : 'json',
		error : function() {
			alert("error occured!!!  goFindInformationList.do");
		},
		async : true,
		success : function(data) {
			if(data.success==false){
				alert(data.message)
			}else{
				$("#center").empty();
				var data=data.list;
				var groups = [];
				$.each(data,function(index) {
					var indexId = data[index].libraryOid;
					var div = "<div id="+indexId+" name="+indexId+"></div>";
					$("#center").append(div);
					var libraryGroupOid=data[index].libraryGroupOid;
					var libraryGroupName=data[index].libraryGroupName
					var group = findGroup(libraryGroupOid,groups);
				    if(group == undefined || group == null){
				    	group = {};
				    	group.libraryGroupOid=libraryGroupOid;
				    	group.libraryGroupName=libraryGroupName;
					    var ary = [];
					    ary.push(data[index]);
					    group.data = ary;
					    groups.push(group);
					} else {
				    	group.data.push(data[index]);
					}
				    if(data[index].isDefaultShow=='1'){
					    findPageUrl(data[index].libraryOid,personId,data[index].infoUrl,false);
					}
				});
				var div = '';
				div+="<ul id='informationList' class='infoshow-nav'>";
				for(var i=0;i<groups.length;i++){
					div+="<li>"+groups[i].libraryGroupName+" ";
					div+="<ul class='sn-nav'>";
					var group=groups[i].data;
					for(var j=0;j<group.length;j++){
						var indexId = group[j].libraryOid;
						var infoUrl= group[j].infoUrl;
						div+="<li id='li"+indexId+"' name='"+infoUrl+"'>"+
								"<a  class='sn-nav-a' href='#"+indexId+"' name='"+indexId+"' onclick=findPageUrl('"+indexId+"','"+personId+"','"+infoUrl+"')>"+group[j].libraryName+"</a>"+
							"</li>";
				    }
					div+="</ul>";
					div+="</li>";
				}
				div+="</ul>";
				div+="<a class='sn-to-top' href='#top' title='回到顶部'></a>";
				$('#informationUrl').append(div);
				/* $("#informationList li").hover(function(){
					$(this).find('a').css('color','#fff');
					$(this).siblings().find('a').css('color','#0195ff');
					$(this).siblings().css('background','none');
					$(this).css('background','#0084E2');
				}) */
				/* var liAyy = $("#informationList li:lt(5)");
				liAyy.each(function(){
					if($(this).attr("id")!=null&&$(this).attr("name")!=null){
					   var liId=$(this).attr("id").substring(2);	
					   var liName=$(this).attr("name");
				  
					   findPageUrl(liId,personId,liName,false);
				   	}
				   
				})  */
			}
		}
	});
}
function findGroup(libraryGroupOid,groups){
	for(var i=0;i<groups.length;i++){
		if(libraryGroupOid==groups[i].libraryGroupOid){
			return groups[i];
		}
	}
}
function findPageUrl(id,personId,infoUrl,flag){
		$("#" + id).attr('url',infoUrl);
		$("#" + id).load(infoUrl,{"personOid":personId,"Id":id},function(){
			if(flag!==false){
				var pos = $("#" + id).offset();
				$("html,body").animate({ scrollTop: pos.top}, 1000);
		    }
	});
}
</script>
</html>
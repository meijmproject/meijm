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
<div class="main-container">
    <div id="center" class="main-column" style="margin-left: 0px">
    </div>
    <div  class="right-column">
    <div class="infoshow-container" id="informationUrl"></div>
    </div>
    <div class="clear"></div>
</div>
</body>
<script>

$(window).resize(function(){$(".scroll_auto").css("height",$(window).height() -48);}).resize();
var personOid=${personOid};
$(document).ready(function(){
	findPersonType(); 
	$(".btn-return").click(function(){
		HistoryRegister.go("goBackUrl");
	})
});
function goback(){
	HistoryRegister.go("goBackUrl");
}
function findPersonType(){
	$('#informationUrl').children().remove();
	var liList=$("#personList a");
	$.each(liList,function(index){
		$(liList[index]).removeClass("nl-name-aselect");
	})
	$.ajax({
		url : 'goFindInformationList.do?method=findInByfunctionCode',
		data : {functionCode:'personInfoUpdate'},
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
						div+="<li id='li"+indexId+"' name='"+infoUrl+"'><a  class='sn-nav-a' href='#"+indexId+"' name='"+indexId+"' onclick=findPageUrl('"+indexId+"','"+personOid+"','"+infoUrl+"')>"+group[j].libraryName+"</a></li>";
				    }
					div+="</ul>";
					div+="</li>";
				}
				div+="</ul>";
				div+="<a class='sn-to-top' href='#top' title='回到顶部'></a>";
				$('#informationUrl').append(div);
				var liAyy = $("#informationList li:lt(5)");
				liAyy.each(function(){
					if($(this).attr("id")!=null&&$(this).attr("name")!=null){
						var liId=$(this).attr("id").substring(2);	
					    var liName=$(this).attr("name");
					    findPageUrl(liId,personOid,liName,false);
					}
				   
				}) 
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
function findPageUrl(id,personOid,infoUrl,flag){
		$("#" + id).attr('url',infoUrl);
		$("#" + id).load(infoUrl,{"personOid":personOid,"Id":id},function(){
			if(flag!==false){
				var pos = $("#" + id).offset();
				$("html,body").animate({ scrollTop: pos.top}, 1000);
		    }
	});
}
</script>
</html>
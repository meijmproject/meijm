<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="poied_html">
<div class="scroll_auto_deail" style="height:90%">
	<div class="right">
        <div class="nav_box nav_podie_bot" id="right_navbox" style="position:absolute;background-color:#deedf6;width: 180px;">
        </div>
    </div>
	<!--中间主体内容-->
	<div class="unit_content marginL50_chm" id="center" style="margin-right: 240px;">
	</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	findPersonType();
});
var unitId='${unitOid}';
function findPersonType(){
	$.ajax({
		url : 'goFindInformationList.do?method=findInByfunctionCode',
		data : {functionCode:'${param.menuCode}'},
		dataType : 'json',
		error : function() {
			alert("error occured!!!");
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
				div+="<ul class='right_navtext rightNav_chm w_poide_bot' id='right_navtext' style='overflow-y: auto'>";
				for(var i=0;i<groups.length;i++){
					div+="<p>"+groups[i].libraryGroupName+"</p>";
					var group=groups[i].data;
					for(var j=0;j<group.length;j++){
						var indexId = group[j].libraryOid;
						var infoUrl= group[j].infoUrl;
						div+="<li id='li"+indexId+"' name='"+infoUrl+"'><a href='#"+indexId+"' name='"+indexId+"' onclick=findPageUrl('"+indexId+"','"+unitId+"','"+infoUrl+"')>"+group[j].libraryName+"</a></li>";
				    }
				}
				div+="</ul>";
				div+="<a class='back_top' style='width:180px;background-color:#deedf6' href='#center' title='回到顶部'></a>";
				$('#right_navbox').append(div);
				$("#right_navtext li").hover(function(){
					$(this).find('a').css('color','#fff');
					$(this).siblings().find('a').css('color','#0195ff');
					$(this).siblings().css('background','none');
					$(this).css('background','#0084E2');
				})
				var liAyy = $("#right_navtext li");
				liAyy.each(function(){
				   var liId=$(this).attr("id").substring(2);	
				   var liName=$(this).attr("name");
				   findPageUrl(liId,unitId,liName,false);
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
function findPageUrl(id,unitId,infoUrl,flag){
		$("#" + id).attr('url',infoUrl);
		$("#" + id).load(infoUrl,{"unitOid":unitId,"Id":id},function(){
			if(flag!==false){
				var pos = $("#" + id).offset();
				$("html,body").animate({ scrollTop: pos.top}, 1000);
		    }
	});
}
</script>
</html>
var selectHisPositionName = "";
var selectHisPositionType = "";
//var selectHisPositionLevel = "";
var selectHisWorkType = "";
var selectHisPositionTypeCode = "";
//var selectHisPositionLevelCode = "";
var selectHisWorkTypeCode = "";
var selectHisPositionOid = "";
var queryHisPositionName = "";
function selectPosition(obj)
{
	/*class='set_next_city_tan' id='dictionaryHtml'*/
	var div=" <div id='dictionaryHtml' class='set_next_city_tan' style='display:none;width: 50%;background: #fff;position: fixed;z-index: 999999;left: 30%;bottom: 20%; '   >";
	div+="<div class='set_next_city_tan_header' style='width: 100%;background: #2D9DF9;height: 50px;'><ul>";
	div+="<li>岗位名称信息</li>"
	div+="<a class='close' href='javascript:void(0);' onclick='closeRankCode1()' style='top:7px;'></a>";
	div+="</ul></div>";
	div+="<div class='z_tan_search'>";
	div+="<button class='btn_add_1' id='back' style='height:27px;margin-top:10px;margin-left:10px;font-size: 14px;color: #00ab3c;'>返回上级</button>";
	div+="<button class='btn_add_1' id='unit_add' style='height:27px;margin-top:10px;margin-left:10px;font-size: 14px;color: #00ab3c;'>确认选择</button>";
	div+="<input type='text' id='queryHisPositionNameId' placeholder='请输入岗位名称' style='width: 33%;border: 1px solid #5a5a5a;height: 25px;border-radius: 15px;margin-top: 1.5%;padding-left: 10px;float: right;margin-right: 10%;margin-bottom: 1.5%;'/>";
	div+="<a class='z_tan_search' href='javascript:void(0);' onclick='query1()' style=' position: absolute;right: 2.8%;top: 63px;cursor: pointer;width: 30px;height: 20px;font-size: 14px;'>搜索</a>";
	div+="<div style='clear: both;'></div>";
	div+="</div>";
	div+="<div class='new_creat_main'>";
	div+="<table style='width: 100%;margin-left: 1.4%;'><tr><th style='width:2%'>&nbsp;</th>";
	div+="<th style='text-align: left;padding-left: 0px;width:10%'>岗位名称</th>";
	div+="<th style='text-align: left;padding-left: 0px;width:10%'>岗位类别</th>";
	//div+="<th style='text-align: left;padding-left: 20px;'>岗位级别</th>";
	div+="<th style='text-align: left;padding-left: 0px;width:10%'>工作类别</th>";
	//div+="<th style='text-align: right;padding-right: 15%;'>单位性质</th>";
	div+="</tr></table></div>";
	div+="<div class='new_table_border' style='height: 280px;width: 100%;overflow: auto;border-top: 1px solid #ccc;'><table id='set_next_city' style='width:100%;'></table></div>";
	//分页
	div+="<div class='theme_popover_content_Left_paging1' style='width: 100%;background: #72C6FF;height: 40px;margin-top:0px;'>";
	div+="<ul class='paging_pagination1' id='ulPage' style='float: left;color: #fff;text-align: center;margin-left: 2%;font-size: 14px;line-height: 40px;'>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px;width:60px;'><a href='javascript:void(0);' onclick='findPrePosition();' >上一页</a></li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px; width:60px;'><a class='active' href='#' style='background-color: #eeeeee;'>1</a></li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px;width:60px;'><a href='javascript:void(0);' onclick='findNextPosition();'>下一页</a></li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px;width:60px;'><input id='pageNumber' type='text' placeholder='页码' class='w_padding_input' style='width:40px;border:1px solid #ccc'/></li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px;width:60px;'><a href='javascript:void(0);' onclick='gotoPagePosition();' >跳转</a></li>";
	div+="</ul><ul class='paging_pagination1' id='ulTotalPage' style='float: right;color: #fff;text-align: center;margin-left: 2%;font-size: 14px;line-height: 40px;' ><li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px; width:60px;'>总页数:</li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px; width:60px;'>0</li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px; width:60px;'>总记录数:</li>";
	div+="<li style='float: left;color: #fff;text-align: center;font-size: 14px;line-height: 40px; width:60px;'>0</li>";
	div+="</ul>";
	div+="</div>";
	div+="</div>";
	if($("#dictionaryHtml").length<=0){
		$(document.getElementsByTagName("body")).append(div);
	}
	$("#dictionaryHtml").show();
	
	var hisPositionName=$(obj).attr("name");
	var hisPositionName=$(obj).attr("id");
	var hisPositionOid=$("#hisPositionOid").attr("name");
	var hisPositionOid=$("#hisPositionOid").attr("id");
	var hisPositionTypeName=$("#hisPositionTypeName").attr("name");
	var hisPositionTypeName=$("#hisPositionTypeName").attr("id");
	//var hisPositionLevelName=$("#hisPositionLevelName").attr("name");
	//var hisPositionLevelName=$("#hisPositionLevelName").attr("id");
	var hisWorkTypeName=$("#hisWorkTypeName").attr("name");
	var hisWorkTypeName=$("#hisWorkTypeName").attr("id");
	var hisPositionType=$("#hisPositionType").attr("name");
	//var hisPositionLevel=$("#hisPositionLevel").attr("name");
	var hisWorkType=$("#hisWorkType").attr("name");
	var hisPositionType=$("#hisPositionType").attr("id");
	//var hisPositionLevel=$("#hisPositionLevel").attr("id");
	var hisWorkType=$("#hisWorkType").attr("id");
	
	
	//查询
	goQuery1(null,"query",queryHisPositionName);
	
	//选择事件
	$("#unit_add").click(function(){
		queryHisPositionName = "";
		$("input[name='"+hisPositionName+"']").val(selectHisPositionName);
		$("input[name='"+hisPositionOid+"']").val(selectHisPositionOid);
		$("input[name='"+hisPositionTypeName+"']").val(selectHisPositionTypeName);
		//$("input[name='"+hisPositionLevelName+"']").val(selectHisPositionLevelName);
		$("input[name='"+hisWorkTypeName+"']").val(selectHisWorkTypeName);
		$("input[name='"+hisPositionType+"']").val(selectHisPositionTypeCode);
		//$("input[name='"+hisPositionLevel+"']").val(selectHisPositionLevelCode);
		$("input[name='"+hisWorkType+"']").val(selectHisWorkTypeCode);
	/*	$("#hisPositonType").val = selectHisPositionType;
		$("#hisPositionLevel").val = selectHisPositionLevel;
		$("#hisWorkType").val = selectHisWorkType;*/
		//$("input[name='"+positionName+"']").val(selectUnitOid);
		$(".set_next_city_tan").remove();
		$("input[name='"+hisPositionName+"']").focus();
		changeWorkType(selectHisWorkTypeCode);
	});
	//返回事件
	$("#back").click(function(){
		queryHisPositionName = "";
		goQuery1(null,"query",queryHisPositionName);
	});
}

//赋值
function findXl(arg0)
{
	queryHisPositionName=arg0;
	goQuery1(null,"click",queryHisPositionName);
}
//赋值
function findPosition(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7)
{
	selectHisPositionName = arg0;
	selectHisPositionTypeName = arg4;
	//selectHisPositionLevelName = arg5;
	selectHisWorkTypeName = arg6;
	selectHisPositionTypeCode = arg1;
	//selectHisPositionLevelCode = arg2;
	selectHisWorkTypeCode = arg3;
	//selectUnitOid = agr1;
	selectHisPositionOid = arg7;
	//goQuery(null,"click",queryHisPositionName);
}
//关闭
function closeRankCode1(){
	$(".set_next_city_tan").remove();
}

//查询
function query1()
{
	queryHisPositionName = $("#queryHisPositionNameId").val();
	goQuery1(null,"query",queryHisPositionName);
}


/**************************页码操作***********************************/

//上一页
function findPrePosition(){
	var pageNo=$("#ulPage li:nth-child(2)").children().text();
	if(pageNo<2){
		MessageBox.alert('提示', '本页为第一页!');
	}else{
		$("#ulPage li:nth-child(2)").children().text(Math.floor(pageNo)-1);
		goQuery1(Math.floor(pageNo)-1,"power",queryHisPositionName);
	}
}

//下一页
function findNextPosition(){
	
	var pageNo=$("#ulPage li:nth-child(2)").children().text();
	var totalPage=$("#ulTotalPage li:nth-child(2)").text();
	if(pageNo>=totalPage){
		MessageBox.alert('提示', '本页为最后一页!');
    }else{
    	$("#ulPage li:nth-child(2)").children().text(Math.floor(pageNo)+1);
    	goQuery1(Math.floor(pageNo)+1,"power",queryHisPositionName);
    }
}

//跳转
function gotoPagePosition(){
	 var pageNo=$("#ulPage li:nth-child(4)").children().val();
	 var totalPage=$("#ulTotalPage li:nth-child(2)").text();
	 
	 var t=/^[0-9]*[1-9][0-9]*$/ ;  
	 if(!t.test(pageNo)){
		 MessageBox.alert('提示', '请输入正整数!');
	 }else if(parseInt(pageNo)>parseInt(totalPage)){
		 MessageBox.alert('提示', '页码不能大于总页数!');
	 }else{
		 $("#ulPage li:nth-child(2)").children().text(pageNo);
		 goQuery1(pageNo,"power",queryHisPositionName);
	 }
}


//查询
function goQuery1(pageNo,flag,queryHisPositionName)
{
	var params = {};
	var initpageNo=$("#ulPage li:nth-child(2)").children().text();
	if(flag=="query"){
		params.pageNo=pageNo==null?1:pageNo;
		$("#ulPage li:nth-child(2)").children().text(1);
		$(".w_padding_input").val("");
		params.flag="";
	}else if(flag=="click"){
		params.pageNo=pageNo==null?1:pageNo;
		$("#ulPage li:nth-child(2)").children().text(1);
		$(".w_padding_input").val("");
		params.flag="true";
	}else if(flag=="power"){
		params.pageNo=pageNo==null?initpageNo:pageNo;
		params.flag="";
	}
	params.pageSize=10;
	params.hisPositionName=queryHisPositionName;
	//params.filterSy = "Y";
	$.ajax({
		url : 'listPositionName.do?method=listPositionName',
		data : params,
		dataType : 'json',
		type:'POST',
		error : function(r,t) {
			alert(t);
		},
		async : true,
		success : function(data) {
			var item=eval(data.rows);
			//var page=Math.floor(data.total/10)+1;
			//$("#ulTotalPage li:nth-child(2)").text(page);
			//$("#ulTotalPage li:nth-child(4)").text(data.total);
			//if(item!=''){
				var page=Math.ceil(data.total/10);
				$("#ulTotalPage li:nth-child(2)").text(page);
				$("#ulTotalPage li:nth-child(4)").text(data.total);
				//清空list内容
				$("#set_next_city").empty("");
				$.each(item,function(index,hisPositionName){
					//var tbTr ="<tr><td style='width:5%' onclick=find('"+PbSyGwEmployInfoDTO.hisPositionName+"')><input type=radio name=infor /></td>"
	   	 			//	+"<td title='"+PbSyGwEmployInfoDTO.hisPositionName+"'>"+PbSyGwEmployInfoDTO.hisPositionName+"</td>"
	   	 			var tbTr ="<tr><th style='width:2%' onclick=findPosition('"+hisPositionName.hisPositionName+"','"+hisPositionName.hisPositionTypeCode+"','"+hisPositionName.hisPositionLevelCode+"','"+hisPositionName.hisWorkTypeCode+"','"+hisPositionName.hisPositionType+"','"+hisPositionName.hisPositionLevel+"','"+hisPositionName.hisWorkType+"','"+hisPositionName.hisPositionOid+"')><input type=radio name=infor /></th>";
		   	 			if(hisPositionName.haveNext =='true'){
		   	 			tbTr+="<th style='text-align: left;padding-left: 0px;width:10%' title='"+hisPositionName.hisPositionName+"' onclick=findXl('"+hisPositionName.hisPositionName+"')><a style='color: #0084E2;'>"+hisPositionName.hisPositionName+"</a></th>";
		   	 			}else{
		   	 			tbTr+="<th style='text-align: left;padding-left: 0px;width:10%' title='"+hisPositionName.hisPositionName+"')>"+hisPositionName.hisPositionName+"</th>";
		   	 			}
		 				
		   	 		tbTr+="<th style='text-align: left;padding-left: 0px;width:10%' title='"+hisPositionName.hisPositionType+"'>"+hisPositionName.hisPositionType+"</th>";
		 				//+"<th style='text-align: left;padding-left: 20px;' title='"+hisPositionName.hisPositionLevel+"'>"+hisPositionName.hisPositionLevel+"</th>"
		   	 		tbTr+="<th style='text-align: left;padding-left: 0px;width:10%' title='"+hisPositionName.hisWorkType+"'>"+hisPositionName.hisWorkType+"</th></tr>";
	   	 				//+"<td>"+utUnitDTO.unitKindName+"</td></tr>";
					$("#set_next_city").append(tbTr);
				});
			//}
			//queryHisPositionName="";
		}
	});
}

//清除选项
function clearPosition(obj)
{
	var hisPositionName=$(obj).attr("name");
	var hisPositionName=$(obj).attr("id");
	var hisPositionOid=$("#hisPositionOid").attr("name");
	var hisPositionOid=$("#hisPositionOid").attr("id");
	var hisPositionTypeName=$("#hisPositionTypeName").attr("name");
	var hisPositionTypeName=$("#hisPositionTypeName").attr("id");
	var hisPositionLevelName=$("#hisPositionLevelName").attr("name");
	var hisPositionLevelName=$("#hisPositionLevelName").attr("id");
	var hisWorkTypeName=$("#hisWorkTypeName").attr("name");
	var hisWorkTypeName=$("#hisWorkTypeName").attr("id");
	var hisPositionType=$("#hisPositionType").attr("name");
	var hisPositionLevel=$("#hisPositionLevel").attr("name");
	var hisWorkType=$("#hisWorkType").attr("name");
	var hisPositionType=$("#hisPositionType").attr("id");
	var hisPositionLevel=$("#hisPositionLevel").attr("id");
	var hisWorkType=$("#hisWorkType").attr("id");
	
	$("input[name='"+hisPositionName+"']").val("");
	$("input[name='"+hisPositionOid+"']").val("");
	$("input[name='"+hisPositionTypeName+"']").val("");
	$("input[name='"+hisPositionLevelName+"']").val("");
	$("input[name='"+hisWorkTypeName+"']").val("");
	$("input[name='"+hisPositionType+"']").val("");
	$("input[name='"+hisPositionLevel+"']").val("");
	$("input[name='"+hisWorkType+"']").val("");
	/*$("#hisPositionType").val = "";
	$("#hisPositionLevel").val = "";
	$("#hisWorkType").val = "";*/
	//$("input[name='"+positionName+"']").val("");
	$(".set_next_city_tan").remove();
	$("input[name='"+hisPositionName+"']").focus();
}

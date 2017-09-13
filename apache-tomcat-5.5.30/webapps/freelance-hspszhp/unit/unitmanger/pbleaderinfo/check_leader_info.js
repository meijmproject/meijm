function addLeaderRow(){
	var isExist = false;
	var chooseDutyAttr = $("#dutyAttribute").val();
	var chooseDutyLevel = $("#chooseDutyLevelVal").val();
	var dutyLevelTxt = $("#chooseDutyLevel").val();
	var dutyAttrTxt = $("#dutyAttribute").find("option:selected").text();
	if(chooseDutyAttr==""||chooseDutyLevel==""){
		MessageBox.alert('提示', '请选择职务属性和职务级别!');
		return;		
	}
	var chooseItems = chooseDutyAttr+chooseDutyLevel;
	if(chooseDutyLevel>299){
		MessageBox.alert('提示', '请选择公务员级别或职员级别!');
		return;
	}
	$("#leaderTable tr").each(function(i){
		var dutyAttrVal = "";
		var existItems = "";
		var dutyLevelVal = "";
		$(this).find("input").each(function(){
				if($(this).attr("name")=="dutyAttribute"){
					dutyAttrVal = $(this).val();
				}
				if($(this).attr("name")=="dutyLevel"){
					dutyLevelVal = $(this).val();
				}
			});
		existItems = dutyAttrVal+dutyLevelVal;

		if(chooseItems==existItems){
			isExist = true;
		}
		});
	if(isExist){
		MessageBox.alert('提示', '该类型领导职数信息已存在记录!');
		return;
	}
	var html = "<tr><td class='md-th'><input type='checkbox' name='leaderCheckBox'/>"+
	"</td><td><input type='text' readonly='readonly' name='dutyAttributeName' value='"+dutyAttrTxt+"'/>"+
	"<input type='hidden' name='dutyAttribute' value='"+chooseDutyAttr+"'/></td>"+
	"<td><input type='text' readonly='readonly' name='dutyLevelName' value='"+dutyLevelTxt+"'/>"+
	"<input type='hidden' name='dutyLevel' value='"+chooseDutyLevel+"'/></td>"+
	"<td><input type='text' value='0' readonly='readonly' name='preCount'/></td>"+
	"<td><input type='text' class='modal_iput' name='chgCount' onchange='checkNum(this)' onblur='checkNum1(this)' /></td>"+
	"<td><input type='text' value='0' readonly='readonly' name='curCount'/></td>"+
	"<td>"+
	
	"<a class='st-handle-delete' href='javascript:void(0);' onclick='deleteRow(this)'></a>"+
	"<a href='javascript:void(0)' title='保存' class='st-handle-save btn' onclick='addLeaderInfo(this)'></a>"+
	
	"</td></tr>";
	$("#leaderTable").append(html);
}
function addLeaderInfo(obj){
	var tds = $(obj).parent().siblings();
	var chgCountVal = tds.find("input[name='chgCount']").val();
	var preCountVal = tds.find("input[name='preCount']").val();
	var curCountVal = Number(preCountVal) + Number(chgCountVal);
	if(chgCountVal == ""){
		MessageBox.alert('提示',"请填写变动数量!");
		return;
	}else if(!isInteger(chgCountVal)){
		MessageBox.alert('提示',"请输入正确格式!");
		return;
	}else if(curCountVal<0){
		MessageBox.alert('提示',"计算变动后数量小于0，请输入正确数字!");
		return;
	}
	tds.find("input[name='curCount']").val(curCountVal);
	var dutyAttributeNameVal = tds.find("input[name='dutyAttributeName']").val();
	var dutyAttributeVal = tds.find("input[name='dutyAttribute']").val();
	var dutyLevelNameVal = tds.find("input[name='dutyLevelName']").val();
	var dutyLevelVal = tds.find("input[name='dutyLevel']").val();
	var args = {dutyAttributeName:dutyAttributeNameVal,dutyAttribute:dutyAttributeVal,dutyLevelName:dutyLevelNameVal,dutyLevel:dutyLevelVal,preCount:"0",chgCount:chgCountVal,curCount:curCountVal};
	var unitOid = $("#unitOid").val();
	$.ajax({
        url : "addVerUbLeaderInfo.do?method=addVerUbLeaderInfo&unitOid="+unitOid,
        data :  {verUbLeaderInfo:JSON.stringify(args)},
        dataType : 'json',
        type:'POST',
        async : false,
        success : function(data) {
        	$('#leaderList').load('listLeaderInfo.do?method=listLeaderInfo',{unitOid:unitOid});
        }
    });
}
function selectAllOnclick(ele){
	$("input[name='leaderCheckBox']").prop("checked",ele.checked);
}
function deleteAll(){
	if($("input[name='leaderCheckBox']:checked").length<1){
		MessageBox.alert('提示', '请至少选择一条记录！');
		return;
	}
	var leaderOids = "";
	$("input[name='leaderCheckBox']:checked").each(function(v,i){
		var leaderOid=$(this).parent().parent().find("input[name='leaderOid']").val();
		if(leaderOid!=undefined){
			leaderOids+=leaderOid+",";
		}else{
			$(this).parent().parent().remove();
		}
	})
	
	if(leaderOids.length>0){
		leaderOids=leaderOids.substr(0,leaderOids.lastIndexOf(","));
		deleteLeaderInfo(leaderOids);
	}
	
}
function deleteLeaderInfo(leaderOids){
	var unitOid = $('#unitOid').val();
	MessageBox.confirm('提示', '确认删除？', function(action) {
		if (action == 'yes') {
			$.ajax({
				url : 'deleteVerUbLeaderInfo.do?method=deleteVerUbLeaderInfo',
				data : {
					leaderOids : leaderOids
				},
				dataType : 'json',
				error : function(x, t) {
					alert(t);
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						$('#leaderList').load('listLeaderInfo.do?method=listLeaderInfo',{unitOid:unitOid});
					} else {
						alert(data.message);
					}
				}
			});
		}
	});
}
function updateLeaderInfo(obj){
	var unitOid = $("#unitOid").val();
	var leaderOidVal = $(obj).parent().find("input").val();
	var tds = $(obj).parent().siblings();
	var chgCountVal = tds.find("input[name='chgCount']").val();
	var preCountVal = tds.find("input[name='preCount']").val();
	var curCountVal = Number(preCountVal) + Number(chgCountVal);
	if(chgCountVal == ""){
		MessageBox.alert('提示',"请填写变动数量!");
		return;
	}else if(!isInteger(chgCountVal)){
		MessageBox.alert('提示',"请输入正确格式!");
		return;
	}else if(curCountVal<0){
		MessageBox.alert('提示',"计算变动后数量小于0，请输入正确数字!");
		return;
	}
	tds.find("input[name='curCount']").val(curCountVal);
	var args = {chgCount:chgCountVal,curCount:curCountVal,leaderOid:leaderOidVal,preCount:preCountVal};
	$.ajax({
        url : "updateVerUbLeaderInfo.do?method=updateVerUbLeaderInfo&unitOid="+unitOid,
        data :  {verUbLeaderInfo:JSON.stringify(args)},
        dataType : 'json',
        type:'POST',
        async : false,
        success : function(data) {
        	var chgCount = $(obj).parent().siblings().eq(4).find("input");
        	chgCount.removeClass("modal_iput").attr("readonly","readonly");
        }
    });
}
function toUpdateLeader(obj){
	var lastLeader = $(obj).parent().siblings().eq(5).find("input").val();
	$(obj).parent().siblings().eq(3).find("input").val(lastLeader);
	var chgCount = $(obj).parent().siblings().eq(4).find("input");
	chgCount.removeAttr("readonly").attr("class","modal_iput");
}
function deleteRow(obj){
	$(obj).parent().parent().remove();
}
function isInteger(num){
	var reg = /^[-]{0,1}[0-9]{1,}$/;
	if(!reg.test(num)){
		return false;
	}else{
		return true;
	}
}
function checkNum(obj){
	var preCount = $(obj).parent().prev().find("input");
	var curCount = $(obj).parent().next().find("input");
	var resulte=Number(preCount.val())+Number($(obj).val());
	curCount.val(resulte);
}
function checkNum1(obj){
	var chgCountVal=obj.value;
	var preCount = $(obj).parent().prev().find("input");
	var curCount = $(obj).parent().next().find("input");
	var curCountVal=Number(preCount.val())+Number($(obj).val());
	if(chgCountVal == ""){
		MessageBox.alert('提示',"请填写变动数量!");
		curCount.val("");
		return;
	}else if(!isInteger(chgCountVal)){
		MessageBox.alert('提示',"请输入正确格式!");
		curCount.val("");
		return;
	}else if(curCountVal<0){
		MessageBox.alert('提示',"计算变动后数量小于0，请输入正确数字!");
		curCount.val("");
		return;
	}
}
function writeLeaderData(data){
	var item=eval(data.rows);
    var rowHeader = "<thead class='sr-table-thead'><tr><th class='md-th'><input type='checkbox'></th><th>职务属性</th>"+
					"<th>职务级别</th><th>变动前数量</th><th>变动数量</th><th>变动后数量</th><th>操作</th></tr></thead>"+
		"<input type='hidden' id='unitOid' value='"+data.unitOid+"'/>";
	$("#leaderTable").empty();
	$("#leaderBtn").hide();	
	$("#leaderTable").append(rowHeader);
	var row = "<tbody class='sr-table-tbody'>";
	$.each(item,function(index) {
		 row += "<tr><td class='md-th'><input type='checkbox'/></td>"+
		"<td><input type='text' readonly='readonly' name='dutyAttributeName' value='"+item[index].dutyAttributeName+"'/>"+
		"<input type='hidden' name='dutyAttribute' value='"+item[index].dutyAttribute+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='dutyLevelName' value='"+item[index].dutyLevelName+"'/>"+
		"<input type='hidden' name='dutyLevel' value='"+item[index].dutyLevel+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='preCount' value='"+item[index].preCount+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='chgCount' onchange='checkNum(this)' onblur='checkNum1(this)' value='"+item[index].chgCount+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='curCount' value='"+item[index].curCount+"'/></td>"+
		"<td><input type='hidden' name='leaderOid' value='"+item[index].leaderOid+"'/>";
		var preCount=item[index].preCount;
		if(preCount=="0"){
			row += "<a class='st-handle-modify  btn' id='"+item[index].leaderOid+"' href='javascript:void(0)' onclick='toUpdateLeader(this);'></a></td></tr>";
		}else{
			row += "<a class='st-handle-modify  btn' id='"+item[index].leaderOid+"' href='javascript:void(0)' onclick='toUpdateLeader(this);'></a></td></tr>";
		}
		
	});
	row +="</tbody>"
	$("#leaderTable").append(row);
}
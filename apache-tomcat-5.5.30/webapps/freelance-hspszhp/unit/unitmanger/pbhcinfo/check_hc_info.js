function addHcRow(){
	var isExist = false;
	var chooseHcCode = $("#hcCode").val();
	var choosebudgetCode = $("#budgetCode").val();
	var hcCodeTxt = $("#hcCode").find("option:selected").text();
	var budgetCodeTxt = $("#budgetCode").find("option:selected").text();
	if(chooseHcCode==""||choosebudgetCode==""){
		MessageBox.alert('提示',"请选择编制类型和经费形式!");
		return;		
	}
	var chooseItems = chooseHcCode+choosebudgetCode;
	$("#hcTable tr").each(function(i){
		var hcCodeVal = "";
		var existItems = "";
		var budgetFromCodeVal = "";
		$(this).find("input").each(function(){
				if($(this).attr("name")=="hcCode"){
					hcCodeVal = $(this).val();
				}
				if($(this).attr("name")=="budgetFromCode"){
					budgetFromCodeVal = $(this).val();
				}
			});
		existItems = hcCodeVal+budgetFromCodeVal;
		if(chooseItems==existItems){
			isExist = true;
		}
		});
	if(isExist){
		MessageBox.alert('提示',"该类型编制信息已存在记录!");
		return;
	}
	var html = "<tr><td class='md-th'><input type='checkbox' name='hcCheckBox'/></td>"+
	
		"<td><input type='text' readonly='readonly' name='hcName' value='"+hcCodeTxt+"'/>"+
		"<input type='hidden' name='hcCode' value='"+chooseHcCode+"'/></td>"+
		
		"<td><input type='text' readonly='readonly' name='budgetFromName' value='"+budgetCodeTxt+"'/>"+
		"<input type='hidden' name='budgetFromCode' value='"+choosebudgetCode+"'/></td>"+
		
		"<td><input type='text' value='0' readonly='readonly' name='preCount'/></td>"+
		"<td><input type='text' class='modal_iput' name='chgCount' onchange='checkNum(this)' onblur='checkNum1(this)'/></td>"+
		"<td><input type='text' value='0' readonly='readonly' name='curCount'/></td>"+
		"<td>"+
		"<a class='st-handle-delete' href='javascript:void(0);' onclick='deleteRow(this)'></a>"+
		"<a href='javascript:void(0)' title='保存' class='st-handle-save btn' onclick='addHcInfo(this)'></a>"+
		"</td></tr>";
	$("#hcTable").append(html);
}


function addHcInfo(obj){
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
	var hcNameVal = tds.find("input[name='hcName']").val();
	var hcCodeVal = tds.find("input[name='hcCode']").val();
	var budgetFromNameVal = tds.find("input[name='budgetFromName']").val();
	var budgetFromCodeVal = tds.find("input[name='budgetFromCode']").val();
	var curCountVal = tds.find("input[name='curCount']").val();
	var args = {hcName:hcNameVal,hcCode:hcCodeVal,budgetFromName:budgetFromNameVal,budgetFromCode:budgetFromCodeVal,preCount:"0",chgCount:chgCountVal,curCount:curCountVal};
	var unitOid = $("#unitOid").val();
	$.ajax({
        url : "addVerUbHcInfo.do?method=addVerUbHcInfo&unitOid="+unitOid,
        data :  {verUbHcInfo:JSON.stringify(args)},
        dataType : 'json',
        type:'POST',
        async : false,
        success : function(data) {
        	$('#hcList').load('listHcInfo.do?method=listHcInfo',{unitOid:unitOid});
        }
    });
}
function deleteRow(obj){
	$(obj).parent().parent().remove();
}
function updateHcInfo(obj){
	var unitOid = $("#unitOid").val();
	var hcOidVal = $(obj).parent().find("input").val();
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
	var args = {chgCount:chgCountVal,curCount:curCountVal,hcOid:hcOidVal,preCount:preCountVal};
	$.ajax({
        url : "updateVerUbHcInfo.do?method=updateVerUbHcInfo&unitOid="+unitOid,
        data :  {verUbHcInfo:JSON.stringify(args)},
        dataType : 'json',
        type:'POST',
        async : false,
        success : function(data) {
        	//$('#hcList').load('listHcInfo.do?method=listHcInfo',{unitOid:unitOid});
        	//var lastHc = $(obj).parent().siblings().eq(5).find("input").val();
        	//$(obj).parent().siblings().eq(3).find("input").val(lastHc);
        	var chgCount = $(obj).parent().siblings().eq(4).find("input");
        	chgCount.removeClass("modal_iput").attr("readonly","readonly");
        }
    });
}
function isInteger(num){
	var reg = /^[-]{0,1}[0-9]{1,}$/;
	if(!reg.test(num)){
		return false;
	}else{
		return true;
	}
}
function toUpdateHc(obj){
	var lastHc = $(obj).parent().siblings().eq(5).find("input").val();
	$(obj).parent().siblings().eq(3).find("input").val(lastHc);
	var chgCount = $(obj).parent().siblings().eq(4).find("input");
	chgCount.removeAttr("readonly").attr("class","modal_iput");
}
function selectAll(ele){
	$("input[name='hcCheckBox']").prop("checked",ele.checked);
}
function deleteAllhc(){
	if($("input[name='hcCheckBox']:checked").length<1){
		MessageBox.alert('提示', '请至少选择一条记录！');
		return;
	}
	var hcOids = "";
	$("input[name='hcCheckBox']:checked").each(function(v,i){
		var hcOid=$(this).parent().parent().find("input[name='hcOid']").val();
		if(hcOid!=undefined){
			hcOids+=hcOid+",";
		}else{
			$(this).parent().parent().remove();
		}
	})
	
	if(hcOids.length>0){
		hcOids=hcOids.substr(0,hcOids.lastIndexOf(","));
		deleteHc(hcOids);
	}
	
}
function deleteHc(hcOids){
	var unitOid = $('#unitOid').val();
	MessageBox.confirm('提示', '确认删除？', function(action) {
		if (action == 'yes') {
			$.ajax({
				url : 'deleteVerUbHcInfo.do?method=deleteVerUbHcInfo',
				data : {
					hcOids : hcOids
				},
				dataType : 'json',
				error : function(x, t) {
					alert(t);
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						$('#hcList').load('listHcInfo.do?method=listHcInfo',{unitOid:unitOid});
					} else {
						alert(data.message);
					}
				}
			});
		}
	});
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
function writeHcData(data){
	var item=eval(data.rows);
    var rowHeader = "<thead class='sr-table-thead'><tr><th class='md-th'><input type='checkbox'/></th><th>编制类型</th>"+
		"<th>经费形式</th><th>变动前数量</th><th>变动数量</th><th>变动后数量</th><th>操作</th></tr></thead>"+
		"<input type='hidden' id='unitOid' value='"+data.unitOid+"'/>";
	$("#hcBtn").hide();	
	$("#hcTable").empty();
	$("#hcTable").append(rowHeader);
	var row = "<tbody class='sr-table-tbody'>";
	$.each(item,function(index) {
		 row += "<tr><td class='md-th'><input type='checkbox'/></td>"+
		"<td><input type='text' readonly='readonly' name='hcName' value='"+item[index].hcName+"'/>"+
		"<input type='hidden' name='hcCode' value='"+item[index].hcCode+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='budgetFromName' value='"+item[index].budgetFromName+"'/>"+
		"<input type='hidden' name='budgetFromCode' value='"+item[index].budgetFromCode+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='preCount' value='"+item[index].preCount+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='chgCount' onchange='checkNum(this)'  onblur='checkNum1(this) value='"+item[index].chgCount+"'/></td>"+
		"<td><input type='text' readonly='readonly' name='curCount' value='"+item[index].curCount+"'/></td>"+
		"<td><input type='hidden' name='hcOid' value='"+item[index].hcOid+"'/>";
		var preCount=item[index].preCount;
		if(preCount=="0"){
			row += "<a class='st-handle-modify  btn' id='"+item[index].hcOid+"' href='javascript:void(0)' onclick='toUpdateHc(this);'></a></td></tr>";
			
		}else{
			row += "<a class='st-handle-modify  btn' id='"+item[index].hcOid+"' href='javascript:void(0)' onclick='toUpdateHc(this);'></a></td></tr>";
		}
	});
	row +="</tbody>"
	$("#hcTable").append(row);
}
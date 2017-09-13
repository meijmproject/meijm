/**
 * 需要进行批量操作的，带三个参数设定为：true
 */
var BizDefaultTaskFlowAction=(function($, window, document){
	
	function processInformation(data,worktop)
	{
		
		$.post("findJhdMtMenu.do?method=findJhdMtMenu&menuCode="+menuCode.substring(0,menuCode.length-3)+"&menuType=1",function(data){
        	var menuList=data.list;
        	var counts=0; var count=0;
        	$.each(menuList,function(i,item){
        	   count=item.count;
        	   $("#"+item.menuCode).html(count);
        	   if(count==0){$("#"+item.menuCode).hide();}else{$("#"+item.menuCode).show();}
        	   counts+=parseInt(count);
        	})
        	$("#"+menuCode.substring(0,4)).html(counts);
        	if(counts==0){$("#"+menuCode.substring(0,4)).hide();}else{$("#"+menuCode.substring(0,4)).show();}
        	if(menuCode.length>3){
        		$.post("findJhdMtMenu.do?method=findJhdMtMenu&menuCode="+menuCode.substring(0,4)+"&menuType=1",function(data){
                	var menuList=data.list;
                	var counts=0; var count=0;
                	$.each(menuList,function(i,item){
                	   count=item.count;
                	   $("#"+item.menuCode).html(count);
                	   if(count==0){$("#"+item.menuCode).hide();}else{$("#"+item.menuCode).show();}
                	   counts+=parseInt(count);
                	})
                	$("#"+menuCode.substring(0,4)).html(counts);
                	if(counts==0){$("#"+menuCode.substring(0,4)).hide();}else{$("#"+menuCode.substring(0,4)).show();}
            	},'json');
        	}
    	},'json');
		worktop.form.goQuery();
	};
	
	function doAction(worktop, url, params) {
		$.post(
			url,
			params,
			function(data) {
				if (data.message) {
					MessageBox.message('提示', data.message, function() {
							processInformation(data, worktop);
						});
				}
			}
		, 'json');
	};
	
	return {
		
	processInformation:processInformation,
	
	// 撤回业务
	revoke:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var itemCode = new  Array();
		var taskItemCode = new Array();
		var taskOid = new Array();
		var applyName = new Array();
		for(i=0;i<selectRow.length;i++)
		{
			taskOid[i] = selectRow[i].data.taskOid;
			applyName[i] = selectRow[i].data.name;
			taskItemCode[i]=selectRow[i].data.taskItemCode;
			itemCode[i]=selectRow[i].data.itemCode;
		}
		$.post("bizDefaultRevoke.do?method=submitRevoke",
		{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')},		
		function(data){
			if (data.message) 
			{
				MessageBox.message('提示', data.message,function()
				{
					processInformation(data,worktop);
				}		
				);
			}
		},'json');
	},
	// 申报业务
	approve:function(worktop,g,single,single)
	{
		
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var itemCode = new  Array();
		var taskItemCode = new Array();
		var taskOid = new Array();
		var applyName = new Array();
		for(i=0;i<selectRow.length;i++)
		{
			taskOid[i] = selectRow[i].data.taskOid;
			applyName[i] = selectRow[i].data.name;
			taskItemCode[i]=selectRow[i].data.taskItemCode;
			itemCode[i]=selectRow[i].data.itemCode;
		}
		
		var params = {bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')};
		
		/*$.post('bizDefaultCheckRemind.do?method=checkRemind',params, function(data){
			if (data.success == false) {
				MessageBox.yes('提示','以下信息有问题，请确认是否上报？<br>'+data.message, function(){
					doAction(worktop, 'bizDefaultReport.do?method=submitReported', params);
				});
			} else {
			}
		}, 'json');*/
		doAction(worktop, 'bizDefaultReport.do?method=submitReported', params);
	},
	// 终止业务
	stop:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		MessageBox.yes('提示','请确认是否终止业务？', function() {
			var itemCode = new  Array();
			var taskItemCode = new Array();
			var taskOid = new Array();
			var applyName = new Array();
			for(i=0;i<selectRow.length;i++)
			{
				taskOid[i] = selectRow[i].data.taskOid;
				applyName[i] = selectRow[i].data.name;
				taskItemCode[i]=selectRow[i].data.taskItemCode;
				itemCode[i]=selectRow[i].data.itemCode;
			}
			
			$.post("bizDefaultStop.do?method=submitStop",
			{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')},		
			function(data){
				if (data.message) 
				{
					MessageBox.message('提示', data.message,function()
							{
								processInformation(data,worktop);
							}		
							);
				}
			},'json');
		});
	},
	// 修改业务
	update:function(worktop,g,single){
		var selectRow = g.selectModel.getSelectRows();
		if (selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var bizPersonOid = selectRow[0].data.bizPersonOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goToUpdateOffInFromOutApprove.do?method=update&menuCode="+menuCode+"&bizPersonOid="+bizPersonOid+"&pageNo="+pageNo,function(){
			$(".nav_tab_li").css("display","block");
		});
	},
	// 删除业务
	del:function(worktop,g,single) 
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		MessageBox.yes('提示','请确认是否删除业务？',function() {
			var itemCode = new  Array();
			var taskItemCode = new Array();
			var taskOid = new Array();
			var applyName = new Array();
			for(i=0;i<selectRow.length;i++)
			{
				taskOid[i] = selectRow[i].data.taskOid;
				applyName[i] = selectRow[i].data.name;
				taskItemCode[i]=selectRow[i].data.taskItemCode;
				itemCode[i]=selectRow[i].data.itemCode;
			}
		
			$.post("bizDefaultDelete.do?method=submitDelete",
			{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')},		
			function(data){
				if (data.message) 
				{
					MessageBox.message('提示', data.message,function()
							{
								processInformation(data,worktop);
							}		
							);
				}
			},'json');
		});
	},
	// 考勤删除业务
	delAttendance:function(worktop,g,single) 
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		MessageBox.yes('提示','请确认是否删除业务？',function() {
			var itemCode = new  Array();
			var taskItemCode = new Array();
			var taskOid = new Array();
			var applyName = new Array();
			for(i=0;i<selectRow.length;i++)
			{
				taskOid[i] = selectRow[i].data.taskOid;
				applyName[i] = selectRow[i].data.orgName;
				taskItemCode[i]=selectRow[i].data.taskItemCode;
				itemCode[i]=selectRow[i].data.itemCode;
			}
		
			$.post("bizDefaultDelete.do?method=submitDelete",
			{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')},		
			function(data){
				if (data.message) 
				{
					MessageBox.message('提示', data.message,function()
							{
								processInformation(data,worktop);
							}		
							);
				}
			},'json');
		});
	},
	// 业务退回
	recheckBack:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var itemCode = new  Array();
		var taskItemCode = new Array();
		var taskOid = new Array();
		var applyName = new Array();
		for(i=0;i<selectRow.length;i++)
		{
			taskOid[i] = selectRow[i].data.taskOid;
			applyName[i] = selectRow[i].data.name;
			taskItemCode[i]=selectRow[i].data.taskItemCode;
			itemCode[i]=selectRow[i].data.itemCode;
		}
		
		$.post("bizDefaultRecheckBack.do?method=submitRecheckBack&bizTaskOids="+taskOid+"&itemCodes="+itemCode+"&itemNodeCodes="+taskItemCode+"&applyNames="+encodeURI(encodeURI(applyName)),
		{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:applyName.join(',')},		
		function(data){
			if (data.message) 
			{
				MessageBox.message('提示', data.message,function()
						{
							processInformation(data,worktop);
						}		
						);
			}
		},'json');
	
	},
	// 业务查看
	view:function(worktop,g,single){
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var bizPersonOid = selectRow[0].data.bizPersonOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("offSyBusView.do?method=update&menuCode=VIEW_"+menuCode+"&bizPersonOid="+bizPersonOid+"&pageNo="+pageNo,function(){
			$(".nav_tab_li").css("display","block");
		});
	},
	// 查看单位信息
	viewUnit:function(worktop,g,single){
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var index = $('.nav_tab_checked').index();
		var unitOid = selectRow[0].data.unitOid;
		if(unitOid==undefined || unitOid==""){
			MessageBox.alert('提示', '本人员暂无单位信息！');
			return;
		}
		var pageNo=worktop.grid.page.page+1;
		$("#rightPage").load("goToViewUnit.do?method=goToViewUnit&unitOid="+unitOid+"&menuCode="+menuCode+"&index="+index+"&pageNo="+pageNo);
	},
	// 查看办理过程
	viewWork:function(worktop,g,single,flag){
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var taskOid = selectRow[0].data.taskOid;
		if(flag=="Biz"){
			Widget.openContent('goToViewWorkBizProcess.do?method=findBizWorkProcess&taskOid='+taskOid,function(){
				worktop.form.goQuery();
			});
		}else if(flag=="Audit"){
			Widget.openContent('goToViewWorkAuditProcess.do?method=findAuditWorkProcess&taskOid='+taskOid,function(){
				worktop.form.goQuery();
			});
		}
		
		
	},
	// 业务上报（需要选择审批部门）
	goBizDefaultReport:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		
		Widget.openContent('goBizDefaultReport.do?method=goBizDefaultReport&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务同意（需要录入领导，日期，意见）
	goRecheckAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultRecheckAgree.do?method=goBizDefaultRecheckAgree&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务同意（需要录入领导，日期，意见）（需选择审批环节）
	goRecheckAgreeForSelect:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultRecheckAgreeForSelect.do?method=goBizDefaultRecheckAgreeForNodeSelect&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务同意（需要录入意见）
	goCheckAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultCheckAgree.do?method=goBizDefaultRecheckAgree&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务同意（需要录入意见）（需选择审批环节）
	goCheckAgreeForSelect:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultCheckAgreeForSelect.do?method=goBizDefaultRecheckAgreeForNodeSelect&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 已接受材料（需要录入意见）
	goReceiveDocument:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultReceiveDocument.do?method=goBizDefaultRecheckAgreeForNodeSelect&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务退回（需要录入意见）
	goCallBack:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultCallBackAgree.do?method=goBizDefaultRecheckAgree&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	// 业务同意
	recheckAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = new  Array();
		var taskItemCode = new Array();
		var taskOid = new Array();
		var name = new Array();
		for(i=0;i<selectRow.length;i++)
		{
			taskOid[i] = selectRow[i].data.taskOid;
			name[i] = selectRow[i].data.name;
			taskItemCode[i]=selectRow[i].data.taskItemCode;
			itemCode[i]=selectRow[i].data.itemCode;
		}

		$.post("bizDefaultRecheckAgree.do?method=submitRecheckAgree",
		{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:name.join(',')},		
		function(data){
			if (data.message) 
			{
				MessageBox.message('提示', data.message,function()
						{
							processInformation(data,worktop);
						}		
						);
			}
		},'json');
	},
	// 业务不同意
	recheckDisAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = new  Array();
		var taskItemCode = new Array();
		var taskOid = new Array();
		var name = new Array();
		for(i=0;i<selectRow.length;i++)
		{
			taskOid[i] = selectRow[i].data.taskOid;
			name[i] = selectRow[i].data.name;
			taskItemCode[i]=selectRow[i].data.taskItemCode;
			itemCode[i]=selectRow[i].data.itemCode;
		}

		$.post("bizDefaultRecheckDisAgree.do?method=submitRecheckDisAgree",
		{bizTaskOids:taskOid.join(','),itemCodes:itemCode.join(','),itemNodeCodes:taskItemCode.join(','),applyNames:name.join(',')},		
		function(data){
			if (data.message) 
			{
				MessageBox.message('提示', data.message,function()
						{
							processInformation(data,worktop);
						}		
						);
			}
		},'json');
	},
	// 办理单位
	findSelectUnit:function(worktop,g,single){
		
	},
	// 业务不同意（需要录入领导，日期，意见）
	goRecheckDisAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultRecheckDisAgree.do?method=goBizDefaultRecheckAgree&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	//业务不同意（需要录入意见）
	goCheckDisAgree:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var applyName = selectRow[0].data.name;
		Widget.openContent('goBizDefaultCheckDisAgree.do?method=goBizDefaultRecheckAgree&applyName='+applyName+'&bizTaskOid='+taskOid+'&itemCode='+itemCode+'&itemNodeCode='+taskItemCode,null,{width:null});
	},
	selectPerson:function(worktop,g,params,fn,scope)
	{
		Widget.openContent('goToSelectPerson.do?method=goSelectPseron',params,function(){
			var that = this;
			if (fn) {
				fn.call(scope||this, that.settings.data);
			}
			that.settings.data = undefined;
		});
	},
	selectGoOutPerson:function(worktop,g,params,fn,scope)
	{
		Widget.openContent('goToSelectGoOutPerson.do?method=goSelectPseron',params,function(){
			var that = this;
			if (fn) {
				fn.call(scope||this, that.settings.data);
			}
			that.settings.data = undefined;
		});
	},
	selectUnit:function(worktop,g,params,fn,scope)
	{
		Widget.openContent('goToSelectUnit.do?method=goSelectUnit',params,function(){
			var that = this;
			if (fn) {
				fn.call(scope||this, that.settings.data);
			}
			that.settings.data = undefined;
		});
	},
	/**
	 * 上传默认实现（参数全部可由调用者指定，方法本身提供默认值，若默认参数不符合要求的，应在调用此方法时指定，严禁在通用方法中加入私有的业务逻辑）
	 * 
	 * params { refRoleCode 操作角色（默认为用户类型+岗位类型） refCode 当前环节的来源代码（默认为当前环节代码）
	 * viewRefCodes 要查看的所有来源（为空时查看所有来源，默认为空） refOid 来源主键，业务主键（默认为taskOid）
	 * 
	 * maxFileSize 文件小大限制（默认5MB） acceptFileTypes 可上传的文件类型（默认仅支持pdf）
	 * 
	 * fileDesc 上传材料说明（外部指定） } 调用方式参考：
	 * 1、freelance-hr-biz/src/web/hrbiz/official/offinfromoutapprove/unitreport/OffInFromOutApproveTbar.js
	 * 2、freelance-hr-biz/src/web/hrbiz/official/offinfromoutapprove/unitadmincheck/OffInFromOutApproveTbar.js
	 */
	fileUpload:function(worktop,g,params)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		}
		
		var itemNodeCode = selectRow[0].data.taskItemCode;
		var userType = itemNodeCode.substr(5,2); // 用户类型
		var posType = itemNodeCode.substr(7,2);  // 岗位类型
		var dbflag = worktop.form.dbflag;// 待办/已办
		
		var viewRefCodes = '';
		var refRoleCode = userType + posType;
		
		refRoleCode = refRoleCode + dbflag;
		// 申报岗
		if(posType == '10') {
			//refRoleCode = refRoleCode + dbflag;
			viewRefCodes = itemNodeCode;// 申报岗只能查询当前环节
		}
		
		params = params || {};
		params.refRoleCode = params.refRoleCode || refRoleCode;		// 操作角色
		params.refCode = params.refCode || itemNodeCode;			// 当前环节的来源代码
		params.viewRefCodes = params.viewRefCodes || viewRefCodes;	// 要查看的所有来源（为空时查看所有来源，默认为空）
		params.refOid = selectRow[0].data.taskOid;					// 来源主键，业务主键（taskOid）
		params.maxFileSize = params.maxFileSize || 10*1024*1024;		// 字节 10MB
//		params.acceptFileTypes = (params.acceptFileTypes == undefined || params.acceptFileTypes == null) ? "doc,docx,xls,xlsx,zip,pdf,jpg,png,tif" : params.acceptFileTypes; // 默认为doc,zip,pdf,jpg,tif
		params.noAcceptFileTypes = (params.noAcceptFileTypes == undefined || params.noAcceptFileTypes == null) ? "exe" : params.noAcceptFileTypes; // 默认为exe
		params.maxFileCounts = (params.maxFileCounts == undefined || params.maxFileCounts == null) ? 0 :  params.maxFileCounts;	//最多上传文件的个数（默认为0表示不作限制）
		
		Widget.openContent('goUploadFile.do?method=goUploadFile',params,function(){
			worktop.form.goQuery();
		},{width:null});
	},
	
	// 查看单位信息--机关
	viewUnitInfo:function(worktop,g,single,flag){
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var index = $('.nav_tab_checked').index();
		var unitOid = selectRow[0].data.unitOid;
		if(unitOid==undefined || unitOid==""){
			MessageBox.alert('提示', '本人员暂无单位信息！');
			return;
		}
		var pageNo=worktop.grid.page.page+1;
		$("#rightPage").load("goToViewUnit.do?method=goToViewUnit&unitOid="+unitOid+"&menuCode="+menuCode+"&index="+index+"&pageNo="+pageNo);
	
	},
	// 单位修改业务
	updateBizUnit:function(worktop,g,single){

		var selectRow = g.selectModel.getSelectRows();
		if (selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var utUnitOid = selectRow[0].data.utUnitOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goToUpdateUnitInfo.do?method=update&menuCode="+menuCode+"&utUnitOid="+utUnitOid+"&pageNo="+pageNo,function(){
			$(".nav_tab_li").css("display","block");
		});
	},
	
	// 单位业务查看
	viewBizUnit:function(worktop,g,single){
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var utUnitOid = selectRow[0].data.utUnitOid;
		var pageNo=worktop.grid.page.page+1;
		
		$("#bizViewportFrameId").load("goToViewUnitInfo.do?method=view&menuCode=VIEW_"+menuCode+"&utUnitOid="+utUnitOid+"&pageNo="+pageNo,function(){
			$(".nav_tab_li").css("display","block");
		});
	},
	// 单位初核同意（需要录入意见）
	goCheckAgreeBizUnit:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var unitName = selectRow[0].data.unitName;
		Widget.openContent('goBizDefaultCheckAgreeUnit.do?method=goBizDefaultCheckAgreeUnit&unitNames='+unitName+'&bizTaskOids='+taskOid+'&itemCodes='+itemCode+'&itemNodeCodes='+taskItemCode,null,{width:null});
	},
	// 单位业务退回（需要录入意见）
	goCallBackBizUnit:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		var itemCode = selectRow[0].data.itemCode;
		var taskItemCode = selectRow[0].data.taskItemCode;
		var taskOid = selectRow[0].data.taskOid;
		var unitName = selectRow[0].data.unitName;
		Widget.openContent('goBizDefaultCallBackAgreeUnit.do?method=goBizDefaultCheckAgreeUnit&unitNames='+unitName+'&bizTaskOids='+taskOid+'&itemCodes='+itemCode+'&itemNodeCodes='+taskItemCode,null,{width:null});
	},
	//设置年度考核人员页面
	goSetPersonYearCheck:function(worktop,g,single)
	{
        var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var reviewBizInfoOid = selectRow[0].data.reviewBizOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goSetPersonYearCheck.do?method=goSetPersonYearCheck&menuCode="+menuCode+"&reviewBizInfoOid="+reviewBizInfoOid+"&pageNo="+pageNo,function(){
			//返回按钮
			$(".nav_tab_li").css("display","block");
		});
		
	},
	//查看年度考核人员页面
	goViewPersonYearCheck:function(worktop,g,single)
	{
        var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var reviewBizInfoOid = selectRow[0].data.reviewBizOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goViewSetPersonYearCheck.do?method=goViewSetPersonYearCheck&menuCode="+menuCode+"&reviewBizInfoOid="+reviewBizInfoOid+"&pageNo="+pageNo,function(){
			//返回按钮
			$(".nav_tab_li").css("display","block");
		});
		
	},
	//申报岗查看年度考核人员页面
	goViewPersonYearCheckReport:function(worktop,g,single)
	{
        var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var reviewBizInfoOid = selectRow[0].data.reviewBizOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goViewSetPersonYearCheckReport.do?method=goViewSetPersonYearCheck&menuCode="+menuCode+"&reviewBizInfoOid="+reviewBizInfoOid+"&pageNo="+pageNo,function(){
			//返回按钮
			$(".nav_tab_li").css("display","block");
		});
		
	},
	//事业设置年度考核人员页面
	goSetSyPersonYearCheck:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var reviewBizInfoOid = selectRow[0].data.reviewBizOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goSetSyPersonYearCheck.do?method=goSetSyPersonYearCheck&menuCode="+menuCode+"&reviewBizInfoOid="+reviewBizInfoOid+"&pageNo="+pageNo,function(){
			//返回按钮
			$(".nav_tab_li").css("display","block");
		});
		
	},
	//事业查看年度考核人员页面
	goViewSyPersonYearCheck:function(worktop,g,single)
	{
		var selectRow = g.selectModel.getSelectRows();
		
		if (single && selectRow.length != 1) {
			MessageBox.alert('提示', '请选择一条记录！');
			return;
		} else if (selectRow.length == 0) {
			MessageBox.alert('提示', '请至少选择一条记录！');
			return;
		}
		
		var reviewBizInfoOid = selectRow[0].data.reviewBizOid;
		var pageNo=worktop.grid.page.page+1;
		$("#bizViewportFrameId").load("goViewSetSyPersonYearCheck.do?method=goViewSetSyPersonYearCheck&menuCode="+menuCode+"&reviewBizInfoOid="+reviewBizInfoOid+"&pageNo="+pageNo,function(){
			//返回按钮
			$(".nav_tab_li").css("display","block");
		});
		
	},
	photoUpload:function(worktop,g,params)
	{
		if(null!=worktop){
			
			var selectRow = g.selectModel.getSelectRows();
			
			/*if (selectRow.length != 1) {*/
			if (selectRow.length < 1) {
				MessageBox.alert('提示', '请选择一条记录！');
				return;
			}
		}
		
		params = params || {};
		params.personOid = params.personOid;		
		params.refType=params.refType;
		params.maxFileSize = params.maxFileSize || 5*1024*1024;		// 字节 5MB
		params.acceptFileTypes = (params.acceptFileTypes == undefined || params.acceptFileTypes == null) ? "doc,docx,xls,xlsx,zip,pdf,jpg,png,tif" : params.acceptFileTypes; // 默认为doc,zip,pdf,jpg,tif
		params.maxFileCounts = (params.maxFileCounts == undefined || params.maxFileCounts == null) ? 0 :  params.maxFileCounts;	//最多上传文件的个数（默认为0表示不作限制）
		
		Widget.openContent('goUploadPhoto.do?method=goUploadPhoto',params,function(){
			/*worktop.form.goQuery();*/
		},{width:null});
	},
	bizPhotoUpload:function(worktop,g,params)
	{
		if(null!=worktop){
			
			var selectRow = g.selectModel.getSelectRows();
			
			/*if (selectRow.length != 1) {*/
			if (selectRow.length < 1) {
				MessageBox.alert('提示', '请选择一条记录！');
				return;
			}
		}
		
		params = params || {};
		params.personOid = params.personOid;		
		params.refType=params.refType;
		params.maxFileSize = params.maxFileSize || 5*1024*1024;		// 字节 5MB
		params.acceptFileTypes = (params.acceptFileTypes == undefined || params.acceptFileTypes == null) ? "doc,docx,xls,xlsx,zip,pdf,jpg,png,tif" : params.acceptFileTypes; // 默认为doc,zip,pdf,jpg,tif
		params.maxFileCounts = (params.maxFileCounts == undefined || params.maxFileCounts == null) ? 0 :  params.maxFileCounts;	//最多上传文件的个数（默认为0表示不作限制）
		
		Widget.openContent('goBizUploadPhoto.do?method=goUploadPhoto',params,function(){
			/*worktop.form.goQuery();*/
		},{width:null});
	},
	viewRule:function(worktop,g,params)
	{
		var refCode='M'+itemNodeCode.substring(0,5);
		var userType = itemNodeCode.substr(5,2); // 用户类型
		var posType = itemNodeCode.substr(7,2);  // 岗位类型
		var dbflag = worktop.form.dbflag;// 待办/已办
		var refRoleCode = userType + posType;
		
		refRoleCode = refRoleCode + dbflag;
		
		params = params || {};
		params.refCode = params.refCode || refCode;			// 当前环节的来源代码
		params.refRoleCode = params.refRoleCode || refRoleCode;		// 操作角色
		
		Widget.openContent('goViewRule.do?method=goViewRule',params,function(){
//			worktop.form.goQuery();
		},{width:null});
	}
  }
})(jQuery, window, document);
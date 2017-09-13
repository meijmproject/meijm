var min_selOrg=(function($, window, document){
	var flag;
	var globalFn;//回调方法
	var column;//决定几列
	var object = new Object();
	var normalClose = 'N';
	var powerControl;//权限控制
	var html="<div class='prompt_box_info_dictionary' style='display: block;'><div class='model-min-head'>";
		html+="<div class='no_search_model_min'><img class='click_search_img_model_min' src='img/business/search_blue_model_min.png' alt='上一级'>";
		html+="<a href='javascript:void(0);' onclick='min_selOrg.backUp()'><span class='up_level_div_model_min'><img class='up_level_img_model_min' src='img/business/up_white_model_min.png' alt='上一级'><span>上级</span></span></a><span class='margin_left_15' id='firstName'></span>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div>";
		html+="<div class='search_and_back_model_min hide'><input class='search_input_model_min' id='dicName' type='text' style='width:65%;padding:0px 10px;height:30px;'>";
		html+="<a href='javascript:void(0);' onclick='min_selOrg.findOrg()'><img class='search_input_img_model_min' src='img/business/search_blue_model_min.png' alt=''></a><div class='back_up_model_min'>";
		html+="<img src='img/business/left_model_min.png' alt='' style='margin: 0px 4px 0px 0px;'><span>返回列表</span></div>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div></div>";
		html+="<div class='model-min-body nano'><div class='nano-content' style='top:0;'>";
		html+="<table><tbody id='dicData'>";
		html+="</tbody></table></div></div>";
		html+="<div class='model-min-footer'></div><div class='prompt_box_nav_dictionary prompt_box_nav_border_dictionary'></div>";
		html+=" <div class='prompt_box_nav_dictionary prompt_box_nav_background_dictionary'></div></div>";
		//isLinkFlag  判断是否从一级节点开始
	function min_selectOrg(obj,unitOid,isLinkFlag,fn,position,power,fix){
		    powerControl = power;
			position = position || ['bottom','right'];
		    if($('.prompt_box_info_dictionary')){$('.prompt_box_info_dictionary').remove()}
			var inst = $(html).insertAfter($(obj));
			$(obj).parent().addClass("label_input-model-min");
			$(obj).attr('readonly','readonly');
			globalFn = fn;//设置回调方法
		    var dictionaryName=$(obj).attr("name");
			var dictionaryCode=$(obj).attr("id");
			var columnClass=$(obj).attr("column");
			if(columnClass==undefined||columnClass==4){column=4;}else if(columnClass==1){column=1;}else if(columnClass==2){column=2;}else if(columnClass==3){column=3;}else{columnClass==4}
			object.dictionaryName=dictionaryName;
			object.unitOid=unitOid;
			object.dictionaryCode=dictionaryCode;
			object.initName = $("input[name='"+object.dictionaryName+"']").val();
		    var orgOid=$(obj).attr("opt")==undefined?"":$(obj).attr("opt");    //自定义从几级字典 
		    initOrg(object.unitOid,orgOid,isLinkFlag);
		    $('.prompt_box_info_dictionary').addClass(position[0]+'-dictionary').addClass(position[1]+'-dictionary');
		    if(fix||position[0]=='bottom'||position[1]=='bottom'){
				
				//确保弹出层的位置在所点击的输入框下面
				var objClientReat = obj.getBoundingClientRect();
				var objTop = objClientReat.top;
				var objLeft = objClientReat.left;
				if(position[0]=='left'||position[1]=='left'){
					var inputWidth = $('.prompt_box_info_dictionary').siblings("input").width();
					objLeft = objLeft+inputWidth-$('.prompt_box_info_dictionary').width();	
				}
				$('.prompt_box_info_dictionary').css({'top':objTop+$(obj).outerHeight()});
				$('.prompt_box_info_dictionary').css({'left':objLeft+$(obj).outerHeight()-40});
				$('.prompt_box_info_dictionary').css({'position':'fixed'});
				//---------------------------------
			}
			inst.find(".close_demol_min").click(function(e){ //红色关闭点击事件
				//$("input[name='"+object.dictionaryName+"']").val(object.initName);
				
				inst.remove();
			 	e.preventDefault();
			 	e.stopPropagation();
		   })
		   //搜索图标点击事件，点击后显示搜索输入框
		    $('.click_search_img_model_min').click(function(e){
		        $(this).parent().hide();
		        $(this).parent().next('.search_and_back_model_min').show();
		        e.preventDefault();
			 	e.stopPropagation();
		    });
			//返回列表图标点击事件，点击后列表
		    $('.back_up_model_min').click(function(){
		        $(this).parent().hide();
		        $(this).parent().prev('.no_search_model_min').show();
		        min_selOrg.backUp()
		    });
		    //利用插件美化滚动条
		    $('.nano').nanoScroller({
		        preventPageScrolling: true
		    });
		    $(".nano").nanoScroller();
	 }
	
	/**
	 * 初始化查询单位名称或者内设机构名称
	 */
	function initOrg(unitOid,orgOid,isLinkFlag){
			if(isLinkFlag=="Y"&&orgOid!=""){
				var orgName=getOrgName(orgOid);
				$("#firstName").text(orgName);
				addTab(unitOid,orgOid,orgName,null,null);
			}else{
				var unitName = getUnitName(unitOid);
				$("#firstName").text(unitName);
				addTab(unitOid,orgOid,unitName,null,null);
			} 
	}
	
	/**
	 * 初始化查询单位名称
	 */
	function getUnitName(unitOid) {
		var unitName = '';
		$.ajax({
			url : 'findUnitNameByUnitOid.do?method=findUnitNameByUnitOid',
			data : {
				unitOid : unitOid
			},
			dataType : 'json',
			error : function(xhr, t, e) {
				alert("error occured!!!");
			},
			async : false,
			success : function(data) {
				unitName = data.unitName;
			}
		});
		return unitName;
	}
	
	function getOrgName(unitOid,orgOid) {
		var orgName = '';
		$.ajax({
			url : 'findOrg.do?method=findOrg',
			data : {
				orgOid : orgOid
			},
			dataType : 'json',
			error : function(xhr, t, e) {
				alert("error occured!!!");
			},
			async : false,
			success : function(data) {
				orgName = data.orgName;
			}
		});
		return orgName;
	}
	
	
	function addTab(unitOid, orgOid,name,orgName,orgType) {
		var data = getchildrenData(unitOid, orgOid);
		if(data.json.length==0){
			$("input[name='"+object.dictionaryName+"']").focus();
			$("input[name='"+object.dictionaryName+"']").val(orgName);
			$("input[name='"+object.dictionaryCode+"']").val(orgOid);
			$("input[name='"+object.dictionaryName+"']").blur();
			$('.prompt_box_info_dictionary').remove();
			$("input[name='"+object.dictionaryName+"']").attr('disabled',false);
			//是否有回调方法联动
			if(globalFn){
				globalFn(orgOid);
			}
			
			return;
		}
//		if(orgName!=null) $("input[name='"+object.dictionaryName+"']").val(orgName);
		$("#firstName").text(name);	
		$("#firstName").attr('value',orgOid);
		if(orgType=='2'){//2为科室
			$("input[name='"+object.dictionaryName+"']").val(orgName);
			$("input[name='"+object.dictionaryCode+"']").val(orgOid);
		}
		var arr = [];
		$("#dicData tr").remove();
		arr = eval(data.json);
		for (var i=0;i<arr.length;) {
			var tr="<tr>"
			for(var j=0;j<column;j++){
				var arrj="arr_"+j;
				    arrj=arr[i+j];
				if(arrj){
					if(!arrj.isleaf){
						  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.orgName+"'><a href='javascript:void(0);'  id='" + arrj.orgOid+ "' onclick=\"min_selOrg.addTab('"+unitOid+"','"+arrj.orgOid+"','"+arrj.orgName+"','"+arrj.orgName+"','"+arrj.orgType+"');\">"+ arrj.orgName + "</a></td>";
					  }else{
						  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.orgName+"'><a href='javascript:void(0);' class='dic_gray' id='" + arrj.orgOid+ "' onclick=\"min_selOrg.addTab('"+unitOid+"','"+arrj.orgOid+"','"+arrj.orgName+"','"+arrj.orgName+"','"+arrj.orgType+"');\">"+ arrj.orgName + "</a></td>";
					  }
				}
			}
			tr+="</tr>";	
			$("#dicData").append(tr);
			i=i+column;
		}
	}
	/**查询子节点数据**/
	function getchildrenData(unitOid, orgOid) {
		var data1 = "";
		$.ajax({
			url : 'getChildOrg.do?method=getChildOrg',
			data : {
				unitOid : unitOid,
				orgOid : orgOid,
				powerControl : powerControl
			},
			dataType : 'json',
			error : function(xhr, t, e) {
				alert(t);
			},
			async : false,
			success : function(data) {
				data1 = data;
			}
		});
				
		return data1;
	}
	/**
	 * 上一级
	 */
	function backUp(){
		var unitOid = object.unitOid;
		var orgOid = $("#firstName").attr('value');
		$.post("findOrgInfoUp.do?method=findOrgInfoUp&unitOid="+unitOid+"&orgOid="+orgOid,function(data){
        	var orgInfo=data.orgDto;
        	if(orgInfo!=null){
        		orgOid=orgInfo.orgOid;
        		var name=orgInfo.orgName;
        		var orgType=orgInfo.orgType;
        		$("#firstName").val(name);
        		$("#firstName").attr('value',orgOid);
        		addTab(unitOid, orgOid,name,orgType);
        	}else{
        		var name = getUnitName(unitOid);
        		addTab(unitOid, null,name,orgType);
        	}
        	
    	},'json');
		
	}
	/**
	 * 查询字典
	 */
	function findOrg(){
		var unitOid = object.unitOid;
		var orgName= $('#dicName').val();
		if(dicName == null || dicName==''){
			MessageBox.alert('提示','请输入要查询的关键字');
			return;
		}
		$.post("listOrg.do?method=listOrg&unitOid="+unitOid+"&orgName="+orgName,function(data){
			$("#dicData tr").remove();
        	var orgInfo=data.orgList;
        	$.each(orgInfo,function(i,item){
//        		alert(item.isleaf+" , "+item.orgName+" , "+item.orgOid);
        		if(item.isleaf){
        			$("<tr><td class='td_list1 td_default' title='"+item.orgName+"'>"+item.orgName+"</td></tr>").appendTo($("#dicData")).click(function(e){
            			$("input[name='"+object.dictionaryName+"']").val(item.orgName);
            			$("input[name='"+object.dictionaryCode+"']").val(item.orgOid);
            			$('.prompt_box_info_dictionary').remove();
            			$("input[name='"+object.dictionaryName+"']").attr('disabled',false);
            			e.preventDefault();
        			 	e.stopPropagation();
            			return;
            		});
        		}else{
        			$("<tr><td class='td_list1 td_default' title='"+item.orgName+"'>" +
        					"<a href='javascript:void(0);' id='" + item.orgOid+ "' onclick=\"min_selOrg.addTab('"+unitOid+"','"+item.orgOid+"','"+item.orgName+"','"+item.orgName+"');\">"+ item.orgName + "</a>" +
        							"</td></tr>").appendTo($("#dicData")).click(function(e){
            			$("input[name='"+object.dictionaryName+"']").val(item.orgName);
            			$("input[name='"+object.dictionaryCode+"']").val(item.orgOid);
            			$('.prompt_box_info_dictionary').remove();
            			$("input[name='"+object.dictionaryName+"']").attr('disabled',false);
            			e.preventDefault();
        			 	e.stopPropagation();
            			return;
            		});
        		}
        		
        	})
    	},'json');
	}
	return {
		min_selectOrg : min_selectOrg,
		addTab: addTab,
		backUp : backUp,
		findOrg : findOrg,
		min_selectOrg : min_selectOrg
	}
})(jQuery, window, document);
var min_Dic=(function($, window, document){
	var flag;
	var globalFn;//回调方法
	var column;//决定几列
	var object = new Object();
	var normalClose = 'N';
	var html="<div class='prompt_box_info_dictionary' style='display: block;'><div class='model-min-head'>";
		html+="<div class='no_search_model_min'><img class='click_search_img_model_min' src='img/business/search_blue_model_min.png' alt='上一级'>";
		html+="<a href='javascript:void(0);' onclick='min_Dic.backUp()'><span class='up_level_div_model_min'><img class='up_level_img_model_min' src='img/business/up_white_model_min.png' alt='上一级'><span>上级</span></span></a><span class='margin_left_15' id='firstName'></span>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div>";
		html+="<div class='search_and_back_model_min hide'><input class='search_input_model_min' id='dicName' type='text' style='width:65%;padding:0px 10px;height:30px;'>";
		html+="<a href='javascript:void(0);' onclick='min_Dic.findDic()'><img class='search_input_img_model_min' src='img/business/search_blue_model_min.png' alt=''></a><div class='back_up_model_min'>";
		html+="<img src='img/business/left_model_min.png' alt='' style='margin: 0px 4px 0px 0px;'><span>返回列表</span></div>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div></div>";
		html+="<div class='model-min-body nano'><div class='nano-content' style='top:0;'>";
		html+="<table><tbody id='dicData'>";
		html+="</tbody></table></div></div>";
		html+="<div class='model-min-footer'></div><div class='prompt_box_nav_dictionary prompt_box_nav_border_dictionary'></div>";
		html+=" <div class='prompt_box_nav_dictionary prompt_box_nav_background_dictionary'></div></div>";
		//isLinkFlag  判断是否从一级节点开始
		//isInDelete  判断是否包含已失效的数据字典项
	function min_Dictionary(obj,dicTypeCode,isLinkFlag,isInDelete,divStyle,fn,position,fix){
			position = position || ['bottom','right'];
		    if($('.prompt_box_info_dictionary')){$('.prompt_box_info_dictionary').remove()}
			//$('.prompt_box_info_dictionary').hide();
			var inst = $(html).insertAfter($(obj));
		//确保弹出层的位置在所点击的输入框下面
			/*var objClientReat = obj.getBoundingClientRect();
			var objTop = objClientReat.top;
			$('.prompt_box_info_dictionary').css({'top':objTop+$(obj).outerHeight()+10});*/
			//---------------------------------
			$(obj).parent().addClass("label_input-model-min");
			$(obj).attr('readonly','readonly');
			globalFn = fn;//设置回调方法
		    var dictionaryName=$(obj).attr("name");
			var dictionaryCode=$(obj).attr("id");
			var columnClass=$(obj).attr("column");
			if(columnClass==undefined||columnClass==4){column=4;}else if(columnClass==1){column=1;}else if(columnClass==2){column=2;}else if(columnClass==3){column=3;}else{columnClass==4}
			object.dictionaryName=dictionaryName;
			object.dicTypeCode=dicTypeCode;
			object.dictionaryCode=dictionaryCode;
			object.initName = $("input[name='"+object.dictionaryName+"']").val();
		    var dicItemCode=$(obj).attr("opt")==undefined?"":$(obj).attr("opt");    //自定义从几级字典            
		    var notInclude=$(obj).attr("notInclude")==undefined?"":$(obj).attr("notInclude");  //不包含的itemcode
		    object.notInclude = notInclude;
		    isInDelete=isInDelete==undefined?"":isInDelete;  //是否包含的失效的
		    object.isInDelete = isInDelete;
			initDictionary(object.dicTypeCode,dicItemCode,isLinkFlag,notInclude,isInDelete);
			//$('.prompt_box_nav_dictionary').css('left','auto');
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
			/*if(divStyle!=undefined&&divStyle!=""){
				if(divStyle == "left"){
					$('.prompt_box_nav_dictionary').css('left','30px');
				}else{
					$('.prompt_box_info_dictionary').css('right','0px');
					$('.prompt_box_nav_dictionary').css('left','346px');
				}
			}*/
			inst.find(".close_demol_min").click(function(e){ //红色关闭点击事件
				//$("input[name='"+object.dictionaryName+"']").val(object.initName);
				
				inst.remove();
			 	//$(obj).attr('disabled',false);
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
		        min_Dic.backUp()
		    });
		    //利用插件美化滚动条
		    $('.nano').nanoScroller({
		        preventPageScrolling: true
		    });
		    $(".nano").nanoScroller();
	 }
	function initDictionary(dicTypeCode,dicItemCode,isLinkFlag,notInclude,isInDelete){
			if(isLinkFlag=="Y"&&dicItemCode!=""){
				var dicItemName=getDicItemName(dicTypeCode,dicItemCode);
				$("#firstName").text(dicItemName);
				addTab(dicTypeCode,dicItemCode,notInclude,dicItemName,null,isInDelete);
			}else{
				var DicTypeName = getDicTypeName(dicTypeCode);
				$("#firstName").text(DicTypeName);
				addTab(dicTypeCode,dicItemCode,notInclude,DicTypeName,null,isInDelete);
			} 
	}
	function getDicTypeName(dicTypeCode) {
		var DicTypeName = '';
		$.ajax({
			url : 'goToSelTree.do?method=goToSelTree',
			data : {
				dicTypeCode : dicTypeCode
			},
			dataType : 'json',
			error : function(xhr, t, e) {
				alert("error occured!!!");
			},
			async : false,
			success : function(data) {
				DicTypeName = data.dicTypeName;
			}
		});
		return DicTypeName;
	}
	function addTab(dicTypeCode, dicItemCode,notInclude,name,dicItemName,isInDelete) {
		var data = getchildrenData(dicTypeCode, dicItemCode,notInclude,isInDelete);
		if(data.json.length==0){
			$("input[name='"+object.dictionaryName+"']").focus();
			$("input[name='"+object.dictionaryName+"']").val(dicItemName);
			$("input[name='"+object.dictionaryCode+"']").val(dicItemCode);
			$("input[name='"+object.dictionaryName+"']").blur();
			$('.prompt_box_info_dictionary').remove();
			$("input[name='"+object.dictionaryName+"']").attr('disabled',false);
			//是否有回调方法联动
			if(globalFn){
				globalFn(dicItemCode);
			}
			
			return;
		}
//		if(dicItemName!=null) $("input[name='"+object.dictionaryName+"']").val(dicItemName);
		$("#firstName").text(name);
		$("#firstName").attr('value',dicItemCode);
		var name = dicItemName?dicItemName:$("input[name='"+object.dictionaryName+"']").val();
		var code = dicItemCode?dicItemName:$("input[name='"+object.dictionaryCode+"']").val();
		$("input[name='"+object.dictionaryName+"']").val(name);
		$("input[name='"+object.dictionaryCode+"']").val(code);
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
						  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.viewName+"'><a href='javascript:void(0);'  id='" + arrj.dicItemCode+ "' onclick=\"min_Dic.addTab('"+dicTypeCode+"','"+arrj.dicItemCode+"','"+notInclude+"','"+arrj.viewName+"','"+arrj.dicItemName+"','"+isInDelete+"');\">"+ arrj.viewName + "</a></td>";
					  }else{
						  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.viewName+"'><a href='javascript:void(0);' class='dic_gray' id='" + arrj.dicItemCode+ "' onclick=\"min_Dic.addTab('"+dicTypeCode+"','"+arrj.dicItemCode+"','"+notInclude+"','"+arrj.viewName+"','"+arrj.dicItemName+"','"+isInDelete+"');\">"+ arrj.viewName + "</a></td>";
					  }
				}
			}
			tr+="</tr>";	
			$("#dicData").append(tr);
			i=i+column;
		}
	}
	function getchildrenData(dicTypeCode, dicItemCode,notInclude,isInDelete) {
		var data1 = "";
		$.ajax({
			url : 'getTreeFilterSecondFloor.do?method=getTreeFilterSecondFloor',
			data : {
				dicTypeCode : dicTypeCode,
				dicItemCode : dicItemCode,
				notInclude  : notInclude,
				isInDelete:isInDelete
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
	function backUp(){
		var dicTypeCode = object.dicTypeCode;
		var notInclude = object.notInclude;
		var isInDelete = object.isInDelete;
		var dicItemCode = $("#firstName").attr('value');
		$.post("findDicItemUp.do?method=findDicItemUp&dicTypeCode="+dicTypeCode+"&dicItemCode="+dicItemCode,function(data){
        	var dicItem=data.dicItem;
        	if(dicItem!=null){
        		dicItemCode=dicItem.dicItemCode;
        		var name=dicItem.viewName;
        		$("#firstName").val(name);
        		$("#firstName").attr('value',dicItemCode);
        		addTab(dicTypeCode, dicItemCode,notInclude,name,null,isInDelete);
        	}else{
        		var name = getDicTypeName(dicTypeCode);
        		addTab(dicTypeCode, null,notInclude,name,null,isInDelete);
        	}
        	
    	},'json');
		
	}
	function findDic(){
		var dicTypeCode = object.dicTypeCode;
		var dicName= $('#dicName').val();
		if(dicName == null || dicName==''){
			MessageBox.alert('提示','请输入要查询的关键字');
			return;
		}
		$.post("findDicItemByName.do?method=findDicItemByName&dicTypeCode="+dicTypeCode+"&dicItemName="+dicName,function(data){
			$("#dicData tr").remove();
        	var dicItem=data.dicItemDto;
        	$.each(dicItem,function(i,item){
        		$("<tr><td class='td_list1 td_default' title='"+item.dicItemName+"'>"+item.dicItemName+"</td></tr>").appendTo($("#dicData")).click(function(e){
        			$("input[name='"+object.dictionaryName+"']").val(item.dicItemName);
        			$("input[name='"+object.dictionaryCode+"']").val(item.dicItemCode);
        			$('.prompt_box_info_dictionary').remove();
        			$("input[name='"+object.dictionaryName+"']").attr('disabled',false);
        			e.preventDefault();
    			 	e.stopPropagation();
        			return;
        		});
        	})
    	},'json');
	}
	return {
		min_Dictionary : min_Dictionary,
		addTab: addTab,
		backUp : backUp,
		findDic : findDic,
		min_Dictionary : min_Dictionary
	}
})(jQuery, window, document);
var selectWorkTop=(function($, window, document){
	var object = new Object();
	var normalClose = 'N';
	var column=3;
	var html="<div class='prompt_box_info_dictionary' style='display: block;'><div class='model-min-head'>";
		html+="<div class='no_search_model_min'><img class='click_search_img_model_min' src='img/business/search_blue_model_min.png' alt='上一级'>";
		html+="<a href='javascript:void(0);' onclick='selectWorkTop.backUps()'><span class='up_level_div_model_min'><img class='up_level_img_model_min' src='img/business/up_white_model_min.png' alt='上一级'><span>上级</span></span></a><span class='margin_left_15' id='firstName'></span>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div>";
		html+="<div class='search_and_back_model_min hide'><input class='search_input_model_min' id='dicName' type='text' style='width:62%;padding:0px 10px;'>";
		html+="<a href='javascript:void(0);' onclick='selectWorkTop.findPosition()'><img class='search_input_img_model_min' src='img/business/search_blue_model_min.png' alt=''></a><div class='back_up_model_min'>";
		html+="<img src='img/business/left_model_min.png' alt='' style='margin: 0px 4px 0px 20px;'><span>返回列表</span></div>";
		html+="<img src='img/business/close_demol_min.png' class='close_demol_min' alt='关闭'></div></div>";
		html+="<div class='model-min-body nano'><div class='nano-content' style='top:0;'>";
		html+="<table><tbody id='dicData'>";
		html+="</tbody></table></div></div>";
		html+="<div class='model-min-footer'></div><div class='prompt_box_nav_dictionary prompt_box_nav_border_dictionary'></div>";
		html+=" <div class='prompt_box_nav_dictionary prompt_box_nav_background_dictionary'></div></div>";
		
	function init(obj,hisWorkType,hisPositionType,position,fix){
			position = position || ['bottom','right'];
		    if($('.prompt_box_info_dictionary')){$('.prompt_box_info_dictionary').remove()}
			var insts = $(html).insertAfter(obj);
			obj.parent().addClass("label_input-model-min");
		
		    var hisPositionName=obj.attr("name");
			object.hisPositionName=hisPositionName;
			object.hisWorkType=hisWorkType;
			object.hisPositionType=hisPositionType;
			object.initName = $("input[name='"+object.hisPositionName+"']").val();
			initDictionarys(hisWorkType,hisPositionType);
			$('.prompt_box_nav_dictionary').css('left','auto');
			insts.find(".close_demol_min").click(function(e){ //红色关闭点击事件
			    //$("input[name='"+object.hisPositionName+"']").val(object.initName);
				insts.remove();
			 	e.preventDefault();
			 	e.stopPropagation();
		   })
		   if(fix||position[0]=='bottom'||position[1]=='bottom'){
				//确保弹出层的位置在所点击的输入框下面
				var objClientReat = obj.offset();
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
		        selectWorkTop.backUp();
		    });
		    //利用插件美化滚动条
		    $('.nano').nanoScroller({
		        preventPageScrolling: true
		    });
		    $(".nano").nanoScroller();
	 }
	function initDictionarys(hisWorkType,hisPositionType){
			$("#firstName").text("院内岗位名称");
			addTabs(hisWorkType,hisPositionType,null);
	}
	function addTabs(hisWorkType,hisPositionType,hisPositionName,flag) {
		var params = {};
		params.hisWorkType= hisWorkType;
		params.hisPositionType= hisPositionType;
		params.positionName = hisPositionName;
		
		$.ajax({
			url : 'listPositionName.do?method=listPositionName',
			data :params,
			dataType : 'json',
			error : function(xhr, t, e) {
				alert(t);
			},
			async : false,
			success : function(data) {
				var data = data.rows;
				if(flag&&flag=="isLeaf"){
					$("input[name='"+object.hisPositionName+"']").val(hisPositionName);
					$('.prompt_box_info_dictionary').remove();
					return;
				}
				if(hisPositionName!=null)
				{
					$("input[name='"+object.hisPositionName+"']").val(hisPositionName);
					$("#firstName").text(hisPositionName);
				}
				var arr = [];
				$("#dicData tr").remove();
				arr = eval(data);
				for (var i=0;i<arr.length;) {
					var tr="<tr>"
					for(var j=0;j<column;j++){
						var arrj="arr_"+j;
						    arrj=arr[i+j];
						if(arrj){
							if(arrj.haveNext=='true'){
								  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.hisPositionName+"'><a href='javascript:void(0);'  onclick=\"selectWorkTop.addTabs('"+hisWorkType+"','"+hisPositionType+"','"+arrj.hisPositionName+"');\">"+ arrj.hisPositionName + "</a></td>";
							  }else{
								  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.hisPositionName+"'><a href='javascript:void(0);' class='dic_gray' onclick=\"selectWorkTop.addTabs('"+hisWorkType+"','"+hisPositionType+"','"+arrj.hisPositionName+"','isLeaf');\">"+ arrj.hisPositionName + "</a></td>";
							  }
						}
					}
					tr+="</tr>";	
					$("#dicData").append(tr);
					i=i+column;
				}
			 }
		});
	}
	function backUps(){
		var hisWorkType = object.hisWorkType;
		var hisPositionType = object.hisPositionType;
		initDictionarys(hisWorkType,hisPositionType)
	}
	
	function findPosition(){
		var hisWorkType = object.hisWorkType;
		var hisPositionType = object.hisPositionType;
		var queryPositionName= $('#dicName').val();
		if(queryPositionName == null || queryPositionName==''){
			MessageBox.alert('提示','请输入要查询的关键字');
			return;
		}
		$.post("listPositionName.do?method=listPositionName&hisWorkType="+hisWorkType+"&queryPositionName="+queryPositionName+"&hisPositionType="+hisPositionType,function(data){
			var data = data.rows;
			var arr = [];
			$("#dicData tr").remove();
			arr = eval(data);
			for (var i=0;i<arr.length;) {
				var tr="<tr>"
				for(var j=0;j<column;j++){
					var arrj="arr_"+j;
					    arrj=arr[i+j];
					if(arrj){
						if(arrj.haveNext=='true'){
							  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.hisPositionName+"'><a href='javascript:void(0);'  onclick=\"selectWorkTop.addTabs('"+hisWorkType+"','"+hisPositionType+"','"+arrj.hisPositionName+"');\">"+ arrj.hisPositionName + "</a></td>";
						  }else{
							  tr+="<td class='td_list"+column+" td_default margin_right_10 ' title='"+arrj.hisPositionName+"'><a href='javascript:void(0);' class='dic_gray' onclick=\"selectWorkTop.addTabs('"+hisWorkType+"','"+hisPositionType+"','"+arrj.hisPositionName+"','isLeaf');\">"+ arrj.hisPositionName + "</a></td>";
						  }
					}
				}
				tr+="</tr>";	
				$("#dicData").append(tr);
				i=i+column;
			}
    	},'json');
	}
	
	return {
		init : init,
		addTabs: addTabs,
		backUps : backUps,
		findPosition : findPosition
	}
})(jQuery, window, document);
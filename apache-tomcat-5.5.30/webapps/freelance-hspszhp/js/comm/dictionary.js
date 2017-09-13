  //  var flag;
	var object = new Object();
	var column;
	function getData(dicTypeCode, dicItemCode,notInclude) {
		var data1 = "";
		$.ajax({
			url : 'getTreeFilterSecondFloor.do?method=getTreeFilterSecondFloor',
			data : {
				dicTypeCode : dicTypeCode,
				dicItemCode : dicItemCode,
				notInclude  : notInclude
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
	var cols = 4;
	function addTab(dicTypeCode, dicItemCode, level,notInclude) {
		var data = getData(dicTypeCode, dicItemCode,notInclude);
		if(data.json.length==0){
			flag="false";
			return;
		 }
		var arr = [];
		++level;
		$("#liListLi li").remove();
		$("#liListLi br").remove();
		var li = '';
		arr = eval(data.json);
		$.each(arr, function(i, item) {
			li += '<li class="conBox_li'+column+'"  title="'+item.viewName+'"><a class="conBox_a'+column+'" href="javascript:void(0);" id="' + item.dicItemCode+ '" onclick=addTabAndContent("'+dicTypeCode+'",this,"'+level+'","'+item.dicItemName+'","'+item.dicItemCode+'","'+notInclude+'");>'+ item.viewName + '</a></li>';
			
		});
		$("#liListLi").append(li);

	}

	function addTabAndContent(dicTypeCode,ele, level,dicItemName,dicItemCode,notInclude) {
		$("#contentDic").val(dicItemName);
		$("input[name='"+object.dictionaryCode+"']").val(dicItemCode);
		//flag="true";
		addTab(dicTypeCode, dicItemCode, level,notInclude);
		//if(flag=="false"){return;}
		var obj = $(ele).html();
		var dicItemCode = $(ele).attr("id");
		var dtList = $("#dtList");
		$("dl.tab dt").each(function() {
			var lev = $(this).attr("level");
			if (lev >= level) {
				$(this).remove();
			}
		});
		$("<dt level="+level+" class='z"+level+"'  onmouseover='changeColor(this)' onmouseout='deleteColor(this)'><a href='#newtab' class='selected' onclick=addTab('"+dicTypeCode+"',"+ dicItemCode+ ",'"+level+"','"+notInclude+"') ><b></b>"+ obj+ "<i></i></a></dt>").appendTo(dtList);
	}
	function dictionaryType(obj,dicTypeCode,isLinkFlag,fn){
		var div=" <div class=iBodyDic style='display:none'  id=dictionaryHtml >";
			div+="<a class=close href=javascript:void(0); onclick=closeRankCodeDic()></a>";
			div+="<div class=seach><input id='contentDic' type=text readonly='readonly' /></div> ";
			div+="<div class=clean></div><dl class=tab id=dtList></dl>";
			div+="<div class=conBox><ul id=liListLi></ul></div>";
			div+="<div class=iBottom><div class=iBottomBox>";
			div+="<a class=bt_1 href=javascript:void(0); onclick=reset()>重置</a> <a class=bt_2 href=javascript:void(0); id=findDicWord>确定</a>";
			div+="</div></div></div>";
		if($("#dictionaryHtml").length<=0){
			$(document.body).append(div);
		}
		$("#dictionaryHtml").show();	
		var dictionaryName=$(obj).attr("name");
		var dictionaryCode=$(obj).attr("id");
		var columnClass=$(obj).attr("column");
		if(columnClass==undefined){column=6;}else if(columnClass==3){column=3;}else if(columnClass==4){column=4;}
		object.dictionaryName=dictionaryName;
		object.dicTypeCode=dicTypeCode;
		object.dictionaryCode=dictionaryCode;
		  $("#contentDic").val($("input[name='"+object.dictionaryName+"']").val());
	      $("#dtList").find("dt").remove();
	      $("#liListLi").find("li").remove();
	      var dicItemCode=$(obj).attr("opt")==undefined?"":$(obj).attr("opt");
	      var notInclude=$(obj).attr("notInclude")==undefined?"":$(obj).attr("notInclude");
		  initDictionary(object.dicTypeCode,dicItemCode,isLinkFlag,notInclude);
	     $("#findDicWord").click(function(){
		    /*	if(flag=="true"){
		    		return;
		 		}*/
		    	var dictionary=$("#contentDic").val();
		    	var dictionaryValue=$("input[name='"+object.dictionaryCode+"']").val();
		 		$("input[name='"+object.dictionaryName+"']").val(dictionary);
		 		
		 		if($("#contentDic").val()==""){
		 			$("input[name='"+object.dictionaryCode+"']").val("");
		 		}
		 		$(".iBodyDic").remove();
				if(fn){
					fn(dictionaryValue);
				}
				$("input[name='"+object.dictionaryName+"']").focus();
		});
	}
     function initDictionary(dicTypeCode,dicItemCode,isLinkFlag,notInclude){
			var li = $(".conBox").find("#liListLi li").children();
			if (li.length < 1) {
				if(isLinkFlag=="Y"&&dicItemCode!=""){
					var dicItemName=getDicItemName(dicTypeCode,dicItemCode);
					$(".tab").append("<dt level='1' class='z1' onmouseover='changeColor(this)' onmouseout='deleteColor(this)'><a class='selected' onclick=addTab('"+dicTypeCode+"','"+dicItemCode+"',1,'"+notInclude+"') >"+ dicItemName+ "<i></i></a></dt>");
					addTab(dicTypeCode, dicItemCode, 1,notInclude);
				}else{
					var DicTypeName = getDicTypeName(dicTypeCode);
					$(".tab").append("<dt level='1' class='z1' onmouseover='changeColor(this)' onmouseout='deleteColor(this)'><a class='selected' onclick=addTab('"+dicTypeCode+"','"+dicItemCode+"',1,'"+notInclude+"') >"+ DicTypeName+ "<i></i></a></dt>");
					addTab(dicTypeCode,dicItemCode, 1,notInclude);
				}
			} else {
				return;
		}
	}
			
			
	function changeColor(obj){
		$(obj).addClass("sed");
	}
	function deleteColor(obj){
		$(obj).removeClass("sed");
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
	
	function getDicItemName(dicTypeCode,dicItemCode) {
		var DicItemName = '';
		$.ajax({
			url : 'goToSelTree.do?method=goToSelTreeChil',
			data : {
				dicTypeCode : dicTypeCode,
				dicItemCode : dicItemCode
			},
			dataType : 'json',
			error : function(xhr, t, e) {
				alert("error occured!!!");
			},
			async : false,
			success : function(data) {
				DicItemName = data.dicItemName;
			}
		});
		return DicItemName;
	}
	function reset(){
	      $("#contentDic").val("");
	      $("input[name='"+object.dictionaryName+"']").val("");
	      $("input[name='"+object.dictionaryCode+"']").val("");
	      $("#dtList").find("dt").remove();
	      $("#liListLi").find("li").remove();
	      initDictionary(object.dicTypeCode);
	}

	function closeRankCodeDic(){
		if($("#contentDic").val()==""){
			$("input[name='"+object.dictionaryCode+"']").val("");
		}
		$(".iBodyDic").remove();
	}
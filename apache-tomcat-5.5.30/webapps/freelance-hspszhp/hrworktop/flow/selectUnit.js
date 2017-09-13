/**
 * 需要进行批量操作的，带三个参数设定为：true
 */
var selectUnit=(function($, window, document){
	function selectUnit_D(unitOid)
	{
		$.post("findSelectUnit.do?method=findSelectUnit&menuCode="+menuCode,function(data){
			$("#"+unitOid).empty();  
			$("<option value=''>--请选择--</option>").appendTo($("#"+unitOid));
			for (var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].unitOid+"'>"+data[i].unitName+"</option>").appendTo($("#"+unitOid));
			}
		},'json');
	}
	function selectUnit_Y(unitOid,unitAllName,fn)
	{
		$.post("findAllSelectUnit.do?method=findAllSelectUnit",function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul' style='display:none;'></ul>").insertAfter($("#"+unitAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].unitOid+"' title='"+data[i].unitName+"'>"+data[i].unitName+"</li>").appendTo(ul).click(function(){
					$("#"+unitAllName).val($(this).text());
					$("#"+unitOid).val($(this).attr('id'));
					$('#form_ul').hide();
				});
			}	
			
			$(document).unbind('click.form_ul').bind('click.form_ul', function(e){
				ul.hide();
			});
			$("#"+unitAllName).click(function(e){
				$("#"+unitOid).val('');
				if($('#form_ul').is(':hidden')){
					$('#form_ul').show();
				}else{
					$('#form_ul').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+unitOid).val('');
				$list=$('#form_ul');
				var unitName= $("#"+unitAllName).val();
				if(unitName){
					var liList = $list.find('li:contains(' + unitName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
			if(fn){
				fn($(this).attr('id'));
			}
		},'json');
	}
	function selectUnit_JgVer(unitOid,unitAllName,fn)
	{
		$.post("findJgVerUnit.do?method=findJgVerUnit",function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul' style='display:none;'></ul>").insertAfter($("#"+unitAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].unitOid+"' title='"+data[i].unitName+"'>"+data[i].unitName+"</li>").appendTo(ul).click(function(){
					$("#"+unitAllName).val($(this).text());
					$("#"+unitOid).val($(this).attr('id'));
					$('#form_ul').hide();
				});
			}	
			
			$(document).unbind('click.form_ul').bind('click.form_ul', function(e){
				ul.hide();
			});
			$("#"+unitAllName).click(function(e){
				$("#"+unitOid).val('');
				if($('#form_ul').is(':hidden')){
					$('#form_ul').show();
				}else{
					$('#form_ul').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+unitOid).val('');
				$list=$('#form_ul');
				var unitName= $("#"+unitAllName).val();
				if(unitName){
					var liList = $list.find('li:contains(' + unitName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
			if(fn){
				fn($(this).attr('id'));
			}
		},'json');
	}
	function selectUnit_SyVer(unitOid,unitAllName,fn)
	{
		$.post("findSyVerUnit.do?method=findSyVerUnit",function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul' style='display:none;'></ul>").insertAfter($("#"+unitAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].unitOid+"' title='"+data[i].unitName+"'>"+data[i].unitName+"</li>").appendTo(ul).click(function(){
					$("#"+unitAllName).val($(this).text());
					$("#"+unitOid).val($(this).attr('id'));
					$('#form_ul').hide();
				});
			}	
			
			$(document).unbind('click.form_ul').bind('click.form_ul', function(e){
				ul.hide();
			});
			$("#"+unitAllName).click(function(e){
				$("#"+unitOid).val('');
				if($('#form_ul').is(':hidden')){
					$('#form_ul').show();
				}else{
					$('#form_ul').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+unitOid).val('');
				$list=$('#form_ul');
				var unitName= $("#"+unitAllName).val();
				if(unitName){
					var liList = $list.find('li:contains(' + unitName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
			if(fn){
				fn($(this).attr('id'));
			}
		},'json');
	}
	return {
		selectUnit_D : selectUnit_D,
		selectUnit_Y : selectUnit_Y,
		selectUnit_JgVer : selectUnit_JgVer,
		selectUnit_SyVer :selectUnit_SyVer
	}
})(jQuery, window, document);
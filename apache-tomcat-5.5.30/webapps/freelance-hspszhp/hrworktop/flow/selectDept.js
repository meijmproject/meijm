/**
 * 需要进行批量操作的，带三个参数设定为：true
 */
var selectDept=(function($, window, document){
	function selectDept_Unit(deptOid,deptAllName,unitOid)
	{
		$.post("findVerDept.do?method=findVerDept&unitOid="+unitOid,function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul' style='display:none;'></ul>").insertAfter($("#"+deptAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].deptOid+"' title='"+data[i].deptName+"'>"+data[i].deptName+"</li>").appendTo(ul).click(function(){
					$("#"+deptAllName).val($(this).text());
					$("#"+deptOid).val($(this).attr('id'));
					$('#form_ul').hide();
				});
			}	
			
			$(document).unbind('click.form_ul').bind('click.form_ul', function(e){
				ul.hide();
			});
			$("#"+deptAllName).click(function(e){
				$("#"+deptOid).val('');
				if($('#form_ul').is(':hidden')){
					$('#form_ul').show();
				}else{
					$('#form_ul').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+deptOid).val('');
				$list=$('#form_ul');
				var deptName= $("#"+deptAllName).val();
				if(deptName){
					var liList = $list.find('li:contains(' + deptName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
		},'json');
	}
	function selectHireDept_Unit(hireDeptOid,hireDeptAllName,unitOid)
	{
		$.post("findVerDept.do?method=findVerDept&unitOid="+unitOid,function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul2' style='display:none;'></ul>").insertAfter($("#"+hireDeptAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].deptOid+"' title='"+data[i].hireDeptName+"'>"+data[i].hireDeptName+"</li>").appendTo(ul).click(function(){
					$("#"+hireDeptAllName).val($(this).text());
					$("#"+hireDeptOid).val($(this).attr('id'));
					$('#form_ul2').hide();
				});
			}	
			
			$(document).unbind('click.form_ul2').bind('click.form_ul2', function(e){
				ul.hide();
			});
			$("#"+hireDeptAllName).click(function(e){
				$("#"+hireDeptOid).val('');
				if($('#form_ul2').is(':hidden')){
					$('#form_ul2').show();
				}else{
					$('#form_ul2').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+hireDeptOid).val('');
				$list=$('#form_ul2');
				var hireDeptName= $("#"+hireDeptAllName).val();
				if(hireDeptName){
					var liList = $list.find('li:contains(' + hireDeptName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
		},'json');
	}
	
	function selectReviewDept_Unit(reviewUnitOid,reviewUnitAllName,unitOid)
	{
		$.post("findVerDept.do?method=findVerDept&unitOid="+unitOid,function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul3' style='display:none;'></ul>").insertAfter($("#"+reviewUnitAllName));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].deptOid+"' title='"+data[i].hireDeptName+"'>"+data[i].hireDeptName+"</li>").appendTo(ul).click(function(){
					$("#"+reviewUnitAllName).val($(this).text());
					$("#"+reviewUnitOid).val($(this).attr('id'));
					$('#form_ul3').hide();
				});
			}	
			
			$(document).unbind('click.form_ul3').bind('click.form_ul3', function(e){
				ul.hide();
			});
			$("#"+reviewUnitAllName).click(function(e){
				$("#"+reviewUnitOid).val('');
				if($('#form_ul3').is(':hidden')){
					$('#form_ul3').show();
				}else{
					$('#form_ul3').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+reviewUnitOid).val('');
				$list=$('#form_ul3');
				var hireDeptName= $("#"+reviewUnitAllName).val();
				if(hireDeptName){
					var liList = $list.find('li:contains(' + hireDeptName + ')');
					$('li', $list).not($(liList)).slideUp();
					$(liList).slideDown();
				}else{
					$list.find("li").slideDown();
				}
				return false;
				
			})
		},'json');
	}
	return {
		selectDept_Unit : selectDept_Unit,
		selectHireDept_Unit : selectHireDept_Unit,
		selectReviewDept_Unit : selectReviewDept_Unit
	}
})(jQuery, window, document);
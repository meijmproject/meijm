/**
 * 需要进行批量操作的，带三个参数设定为：true
 */
var selectMenu=(function($, window, document){
	function selectMenu(menuCode,menuAllTitle,fn)
	{
		$.post("findAllMenuTitle.do?method=findAllMenuTitle",function(data){
			var ul = $("<ul class='search_ul_dictionary1' id='form_ul_menu' style='display:none;'></ul>").insertAfter($("#"+menuAllTitle));
			for (var i = 0; i < data.length; i++) {
				$("<li id='"+data[i].menuCode+"' title='"+data[i].menuTitle+"'>"+data[i].menuTitle+"</li>").appendTo(ul).click(function(){
					$("#"+menuAllTitle).val($(this).text());
					$("#"+menuCode).val($(this).attr('id'));
					$('#form_ul').hide();
				});
			}	
			
			$(document).unbind('click.form_ul_menu').bind('click.form_ul_menu', function(e){
				ul.hide();
			});
			$("#"+menuAllTitle).click(function(e){
				$("#"+menuCode).val('');
				if($('#form_ul_menu').is(':hidden')){
					$('#form_ul_menu').show();
				}else{
					$('#form_ul_menu').hide();
				}
				e.stopPropagation();
				e.preventDefault();
			}).keyup(function(){
				$("#"+menuCode).val('');
				$list=$('#form_ul_menu');
				var menuTitle= $("#"+menuAllTitle).val();
				if(menuTitle){
					var liList = $list.find('li:contains(' + menuTitle + ')');
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
		selectMenu : selectMenu
	}
})(jQuery, window, document);
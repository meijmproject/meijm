
/**
 *	含首页、上一页、下一页、尾页、当前页、当前页两边范围
 *
 *  @name half
 *  @author zhangqp
 *
 *  @example
 *    $(document).ready(function() {
 *        var table = new Xtable({
 *           paginationType: "half"
 *        });
 *    } );
 */
;(function(Plugins,$,window){
	"use strict";
	
	//'<a href="javascript:void(0);" class="{0} {1}{2}" pageNumber="{3}">{4}</a>', classes.sPageButton, classes.sPageFirst, ec, page, lang.sFirst
	function htpl(page, ec, lc, bc, lang) {
		var buffer = [];
		buffer.push('<li>');
		buffer.push('<a href="javascript:;" class="');
		buffer.push(lc);
		if(bc){
			buffer.push(' ');
			buffer.push(bc);
		}
		if(ec){
			buffer.push(' ');
			buffer.push(ec);
		}
		buffer.push('" ');
		buffer.push('pageNumber="');
		buffer.push(page);//页码
		buffer.push('" >');
		buffer.push(lang);//首页
		buffer.push('</a>');
		
		buffer.push('</li>');
		return buffer.join('');
	};
	
	function doUpdate(nPagination, that, self, plugin) {
		var oSettings = that.settings;
		var lang    = oSettings.language.oPaginate;
    	var classes = oSettings.oClasses;
        var oPageInfo = that.page;

		var total = oPageInfo.total;
		
		if (total < 0)  return;
		
        var page = oPageInfo.page + 1;
		//var limit = oPageInfo.length;
		var pages = oPageInfo.pages;
		page=page>pages?pages:page;
		var buffer = [];
		buffer.push('<ul>');
		//首页
		buffer.push(htpl(1, page == 1 ? "disabled" : "active", classes.sPageButton, classes.sPageFirst, lang.sFirst));
		
		//上一页
		buffer.push(htpl(page == 1 ? 1 : page-1, page == 1 ? "disabled" : "active", classes.sPageButton, classes.sPagePrevious, lang.sPrevious));
		
		//页码输入
		buffer.push('<li><input type="text" value="');
		buffer.push(page);
		buffer.push('" class="input_page"/></li>');
		
		//下一页
		buffer.push(htpl(page == pages ? pages : page+1, (page == pages || pages == 0) ? "disabled" : "active", classes.sPageButton, classes.sPageNext, lang.sNext));
		//尾页
		buffer.push(htpl(pages, (page == pages || pages == 0) ? "disabled" : "active", classes.sPageButton, classes.sPageLast, lang.sLast));
		
		
		buffer.push('<li>当前页：'+page+'</li>');
		buffer.push('<li>总页数：'+pages+'</li>');
		buffer.push('<li>总记录数：'+total+'</li>');
		
		buffer.push('</ul>');
		//清除节点
		nPagination.children().remove();
		
		nPagination.append(buffer.join(''));
		
		nPagination.find('.' + classes.sPageButton).click(function(){
			var $this = $(this);
			if($this.hasClass("disabled") || $this.hasClass("current")) return;
			that.fnPageChange(parseInt($this.attr('pageNumber')) - 1);
		});
		nPagination.find('.input_page').keydown(function(e){
			if (e.keyCode == 13) {
				var $this = $(this);
				var pageNumber = parseInt($this.val() || '0');
				if (pageNumber > that.page.pages) {
					pageNumber = that.page.pages;
				}
				if (pageNumber <= 0) {
					pageNumber = 1;
				}
				that.fnPageChange(pageNumber - 1);
				e.stopPropagation();
				e.preventDefault();
			}
		});
	};
	
	Plugins.plugins.oPagination.input = {
		"style":"paginate_input",
	    "fnInit": doUpdate,
	    "fnUpdate": doUpdate
	};
	
})(Xtable, jQuery, window);
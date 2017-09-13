
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
		
		buffer.push('<a href="javascript:void(0);" class="');
		buffer.push(lc);
		if(bc){
			buffer.push(' ');
			buffer.push(bc);
		}
		if(ec){
			buffer.push(' ');
			buffer.push(ec);
		}
		buffer.push('" pageNumber="');
		buffer.push(page);
		buffer.push('">');
		buffer.push(lang);
		buffer.push('</a>');
		
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
		var counta = oSettings.iCounta || 5;//默认5
		var half = parseInt(counta / 2);
		var begin = page - half > 1 ? page - half : 1;
		var end = begin + counta - 1 > pages ? pages : begin + counta - 1;
		if (end == pages) {
			begin = pages - counta >= 1 ? pages - counta + 1 : 1;
		}
		
		var buffer = [];
		//首页
		buffer.push(htpl(1, page == 1 ? "disabled" : "active", classes.sPageButton, classes.sPageFirst, lang.sFirst));
		
		//上一页
		buffer.push(htpl(page == 1 ? 1 : page-1, page == 1 ? "disabled" : "active", classes.sPageButton, classes.sPagePrevious, lang.sPrevious));
		
		buffer.push('<span>');
		for (var pageNumber = begin; pageNumber <= end; pageNumber++) {
			buffer.push(htpl(pageNumber, page == pageNumber ? "current" : "active", classes.sPageButton, null, pageNumber));//active 表示可以点的页码
		}
		buffer.push('</span>');
		//下一页
		buffer.push(htpl(page == pages ? pages : page+1, page == pages ? "disabled" : "active", classes.sPageButton, classes.sPageNext, lang.sNext));
		//尾页
		buffer.push(htpl(pages, page == pages ? "disabled" : "active", classes.sPageButton, classes.sPageLast, lang.sLast));
		
		//清除节点
		nPagination.children().remove();
		
		nPagination.append(buffer.join(''));
		
		nPagination.find('.' + classes.sPageButton).click(function(){
			var $this = $(this);
			if($this.hasClass("disabled") || $this.hasClass("current")) return;
			that.fnPageChange(parseInt($this.attr('pageNumber')) - 1);
		});
	};
	
	Plugins.plugins.oPagination.half = {
	    "fnInit": doUpdate,
	    "fnUpdate": doUpdate
	};
	
})(Xtable, jQuery, window);
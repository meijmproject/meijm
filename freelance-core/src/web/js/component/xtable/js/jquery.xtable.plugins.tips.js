
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
	
	Plugins.setDefaults({
		tips: 'tooltipster'
	});
	
	Plugins.extend(Plugins.additives, {
		"oTips": {
			"_sProp": "tips"/*,
			"fnInit": function(that) {
				
			}*/
		}
	});
	
	//tooltipster 插件实现
	Plugins.additives.oTips.tooltipster = {
		'fnInit': function(that, declare, self) {
			that.on('cellrender', function(value, record, cell, column, rowIndex){
				var content = cell.html();
				if (column.tip !== false && content) {
					cell.tooltipster({content: content});
				}
			}, that);
		}
	}
	
})(Xtable, jQuery, window);
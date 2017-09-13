/**
 *	select length menu
 *
 *  @name half
 *  @author zhangqp
 *
 *  @example
 *    $(document).ready(function() {
 *        var table = new Xtable({
 *            lenthMenuType: "select"
 *        });
 *    } );
 */
;(function(Plugins,$,window) {
	"use strict";
	
	Plugins.plugins.oLengthMenu.select = {
	    // pluginDomNode, pluginInstance, Plugins.plugins.oLengthMenu.select, pluginDetailInfo
	    "fnInit": function (nLengthMenu, that, self, plugin) {
	    	var oSettings = that.settings;
			var buffer = [];

			buffer.push('<select class="length_node length_select">');
			for (var i=0; i<oSettings.lengthMenu.length; i++) {
				buffer.push('<option value="');
				buffer.push(oSettings.lengthMenu[i]);
				buffer.push('">');
				buffer.push(oSettings.lengthMenu[i]);
				buffer.push('</option>');
			}
			buffer.push('</select>');

			nLengthMenu.append(buffer.join(''));
			
			if (oSettings.iPageLength) {
				nLengthMenu.find('select').val(oSettings.iPageLength);
			}

			nLengthMenu.find('select').bind('change',{that:that}, function(event){
				event.data.that.fnLengthChange.call(that, $(this).val(), this);
			});
	    }
	    
	};
	
})(Xtable, jQuery, window);

/**
 *	当前页面操作区域，相当于window，管理当前页面中的所有对象
 * @param {} configs
 * 
 * @author zhangqp, 2016/09/08
 */
var Worktop = function(options) {
	var o = {items:[]};
	
	if ($.type(options) == 'array') {
		 o.items = options;
	} else {
		o = options;
	}
	o.items = o.items || [];
	
	$.copyProperties(this, o);
	
	if (this.items && this.items.length > 0) {
		for (var i=0; i< this.items.length; i++) {
			var item = this.items[i];
			item.xname = item.xname || ('__item_' + i);
			if (item.xtype) {
				item['worktop'] = this; //将worktop也作为参数传进构造函数中去
				this[item.xname] = new (this.getBean(item.xtype))(item);
			}
		}
	}
};
jQuery.extend(Worktop.prototype, {
	
	getBean: function(beanName) {
		if (this.beans) {
			return this.beans[beanName] || window[beanName];
		}
		return window[beanName];
	}
});

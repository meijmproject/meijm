
// 包含组件：TplNamedTbar、Toolbar

/**
 * 按钮模板，封装了tbar通用的初始化方法，可使用 TplNamedTbar.extend 扩展使用。
 * 
 * 封装了固定按钮和浮动按钮的实现
 * 
 * @author zhangqp, 2016/09/07
 */
var TplNamedTbar = (function($, window, document){
	
	var me = function(settings) {
		this.settings = settings || {};
	};
	
	//初始化
	me.extend = function(obj, settings, render) {
		me.call(obj, settings);
		//属性、方法全部拷贝到obj中
		var mixin = me.prototype;
		for (var p in mixin) {
			obj[p] = mixin[p];
		}
		if ($.isFunction(render)) {
			render.call(obj);
			obj.init();
		}
	};
	
	jQuery.extend(me.prototype, {
		init: function() {
			var worktop = this.settings.worktop;
			var that = this;
			
			if (this.fbar) {
				//行内浮动操作按钮
				worktop.grid.on('draw', function(){
					that.fbar.remove();
					
					that.fbar.click(function(){
						that.fbar.hide();
					});
					worktop.grid.tbody.find("tr").click(function(event){
						if ($(this).next().attr("id") == "trIndex") {
							if (that.fbar.is(":hidden")) {
								that.fbar.show();
							} else {
								that.fbar.hide();
								that.fbar.detach();
								$("#trIndex").remove();
							}
						} else {
							that.fbar.detach();
							$("#trIndex").remove();
							if(that.fbar && that.fbar.length>0) {
								$(this).after('<tr id="trIndex" ></tr>');
							}
							$("#trIndex").append(that.fbar);
							that.fbar.show();
						}
						var rowIndex = $(this).attr('rowIndex');
						var record = worktop.grid.getRecordAt(rowIndex);
						that.fbar.attr('rowIndex', rowIndex);
						event.stopPropagation(); //停止冒泡到rowclick
					});
					
					that.fbar.find("button[button-click]").click(function(){
						var action = $(this).attr('button-click');
						var rowIndex = that.fbar.attr('rowIndex');
						var record = worktop.grid.getRecordAt(rowIndex);
						
						var fn = that.settings.buttons[action];
						if (fn) fn.call(worktop.grid, worktop.grid, record, worktop, that, action);
					});
					that.fbar.find("a[button-click]").click(function(){
						var action = $(this).attr('button-click');
						var rowIndex = that.fbar.attr('rowIndex');
						var record = worktop.grid.getRecordAt(rowIndex);

						var fn = that.settings.buttons[action];
						if (fn) fn.call(worktop.grid, worktop.grid, record, worktop, that, action);
					});
				}, worktop.grid);
			}
			
			if (this.tbar) {
				//固定按钮
				this.tbar.find('button[button-click]').click(function(){
					var action = $(this).attr('button-click');
					
					var fn = that.settings.buttons ? that.settings.buttons[action] : null;
					if (fn) fn.call(worktop.grid, worktop.grid, null, worktop, that, action);
				});
				this.tbar.find('a[button-click]').click(function(){
					var action = $(this).attr('button-click');

					var fn = that.settings.buttons ? that.settings.buttons[action] : null;
					if (fn) fn.call(worktop.grid, worktop.grid, null, worktop, that, action);
				});
			}
		}
	});
	
	return me;
})(jQuery, window, document);


/**
 * 对页面按钮添加事件处理
 * 
 * @author zhangqp, 2016/09/08
 */
var Toolbar = (function($, window){
	
	return function(settings){
		//加入基本方法，类似继承
		TplNamedTbar.extend(this, settings, function() {
			//wrap 	包裹本form组件的div节点 	（一般是用来指定生成位置的）
			//ct 	生成的html节点			（html中生成的<div class='search'>）
			//el	操作的主dom节点			（form）
			// 以上节点可按使用情况提供
			
			this.tbar = this.settings.tbar ? $(this.settings.tbar) : null;
			this.fbar = this.settings.fbar ? $(this.settings.fbar) : null;
			this.ct   = this.tbar; //暴露外部使用
		});
	};
})(jQuery, window, document);

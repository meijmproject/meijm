/**
 *	[Plugins].events
 *
 *  @name events
 *  @author zhangqp
 *
 *  @example
 *  //初始化，加入事件支持
 *  [Xtable].events.initialize(this);
 *  
 *  //监听事件
 *  this.on('event',function(arg1,arg2,arg3,event), this,  arg1,arg2,arg3...);
 *  	最后一个参数为事件对象{type:type, fn:fn, data:data, scope:o}, data为参数数组arg1,arg2,arg3...
 *  
 *  //触发事件
 *  this.fire('event',arg1,arg2,arg3...)
 */
;(function(Plugins,$,window) {
	"use strict";
	
	//对象事件监听，简单事件实现
	var Observer = function() {
		this.events = {};
	};

	$.extend(Observer.prototype, {
		//this.fire('sort', name, action, this);
		fire: function(type) {
			var listeners = this.events[type];
			if (listeners && listeners.length > 0) {
				var data = Array.prototype.slice.call(arguments, 1);
				for (var i=0; i<listeners.length; i++) {
					if (listeners[i].fn.apply(listeners[i].scope || this, data.concat([listeners[i]])) === false) {
						return false;
					}
				}
			}
			return true;
		},
		//this.store.on('sort', this.onSort, this);
		//this.store.on('sort', function(p1,p2,p3,event), this, 1,2,3);
		//最后一个参数( arguments[arguments.length-1] ) 获取event对象
		on: function(type, fn, o) {
			var data = Array.prototype.slice.call(arguments, 3);
			this.events[type] = this.events[type] || [];
			this.events[type].push({type:type, fn:fn, data:data, scope:o});
		},
		one: function(type, fn) {
			var origFn = fn;
			var that = this;
			fn = function() {
				that.off(type, fn);
				return origFn.apply(this, arguments);
			}
			arguments[1] = fn;
			this.on.apply(this, arguments);
		},
		off: function(type, fn) {
			var listeners = this.events[type];
			if (listeners && listeners.length > 0) {
				for (var i=0; i<listeners.length; i++) {
					if (listeners[i].fn == fn) {
						listeners.splice(i);
						return i;
					}
				}
			}
			return -1;
		},
		remove: function(type) {
			this.events[type] = [];
		}
	});
	//别名
	Observer.prototype.trigger = Observer.prototype.fire;

	//事件支持初始化
	Observer.initialize = function(obj) {
		Observer.call(obj);
		var mixin = Observer.prototype;
		for (var p in mixin) {
			obj[p] = mixin[p];
		}
	};

	Plugins.events = Observer;
	
})(Xtable, jQuery, window);
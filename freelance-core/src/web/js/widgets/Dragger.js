//拖动效果
// el
// handler

var Dragger = (function($, window, document){
	var defaults = {
		handler : null,// 接收拖动动作的对象，一般是title
		disabled : false
	};
	
	var mX = 0, mY = 0;// 定义鼠标X轴Y轴
	var dX = 0, dY = 0;// 定义div左、上位置
	
	function initEvent(that) {
		// 拖动中
		$(document).bind('mousemove.dragger', function(event) {
			event.preventDefault();
			
			if (that.disabled) return;
	
			if (that.dragging) {
				var x = event.clientX;// 鼠标滑动时的X轴
				var y = event.clientY;// 鼠标滑动时的Y轴
				that.el.css({ "left" : x - mX + dX, "top" : y - mY + dY });// div动态位置赋值
			}
		});
		
		$(document).bind('click.dragger', function(event) {
			that.dragging = false;// 鼠标拖拽启动
			mX = 0; mY = 0;// 定义鼠标X轴Y轴
			dX = 0; dY = 0;// 定义div左、上位置
		});
	}
	
	function destoryEvent(that) {
		$(document).unbind(".dragger");
	}
	
	return function(config) {
		
		$.copyProperties(this, config, defaults);
	
		this.el = $(this.el);
		this.handler = this.handler ? $(this.handler) : this.el;
	
		this.dragging = false;
	
		this.handler.bind('selectstart.dragger', function(e) { e.preventDefault(); return false; });//选择
		this.handler.bind('dragstart.dragger', function(e) { e.preventDefault(); return false; });//拖拽
	
		var that = this;
	
		// 开始拖动
		this.handler.bind('mousedown.dragger', function(event) {
			event.preventDefault();
			
			if (that.disabled) return;
			// 鼠标左键，1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键 
			if (event.which !== 1) return;
	
			that.dragging = true;// 鼠标拖拽启动
			
			initEvent.call(that,that);
	
			var offset = that.el.offset();
			mX = event.clientX;
			mY = event.clientY;
			dX = offset.left;// 获取匹配元素在当前视口的相对偏移。
			dY = offset.top;
		});
		
		// 停止拖动
		this.handler.bind('mouseup.dragger', function(event) {
			event.preventDefault();
			that.dragging = false;// 鼠标拖拽启动
			mX = 0; mY = 0;// 定义鼠标X轴Y轴
			dX = 0; dY = 0;// 定义div左、上位置
			
			destoryEvent.call(that,that);
		});
	
		this.set = function(props) {
			$.copyProperties(that, props);
		}
	};
	
})(jQuery, window, document);

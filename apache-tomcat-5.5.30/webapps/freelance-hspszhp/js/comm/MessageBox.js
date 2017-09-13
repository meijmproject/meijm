/** 弹出框适配器 */
var MessageBox = (function($,window){
	var dialog = null;
	
	function getDialog() {
		return dialog ? dialog : (dialog = new Dialog());
	}
	
	return {
		alert: function(title, content, fn, scope, options) {
			getDialog().show($.copyProperties({
				title: title,
				content: content,
				cancel: false,
				icon: 'tips',
				callback: function(action){
					if(fn) fn.call(scope||this, action);
				}
			}, options));
		},
		message: function(title, content, fn, scope, options) {
			getDialog().show($.copyProperties({
				title: title,
				content: content,
				cancel: false,
				callback: function(action){
					if(fn) fn.call(scope||this, action);
				}
			}, options));
		},
		confirm: function(title, content, fn, scope, options) {
			getDialog().show($.copyProperties({
				title: title,
				content: content,
				icon: 'question',
				callback: function(action){
					if(fn) fn.call(scope||this, action);
				}
			}, options));
		},
		yes: function(title, content, fn, scope, options) {
			MessageBox.confirm(title || '提示', content, function(action) {
				if (action == 'yes') {
					if (fn) fn.call(scope || this, 'yes');
				}
			}, this);
		},
		close: function(title, content, fn, scope, options) {
			getDialog().close();
		},
		/**
		 * 打开一个新窗口，一般用于下载
		 */
		openWindow: function(url, w, h) {
			w = w || 400;
			h = h || 150;
			if (url) {
				url = url + (url.indexOf('?') != -1 ? '&' : '?') + (new Date().getTime()); 
			}
			window.open(url,'',"height="+h+", width="+w+", top="+(window.screen.height-h)/2+", left="+(window.screen.width-w)/2+", toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no")
		}
	}
})(jQuery,window);

var MessageAction = (function(){
	
	function handleCallback(data, callback, options) {
		//json处理
		if (data.message) {
			MessageBox.alert('提示',data.message, function() {
				if (callback) callback.call(this, data, options);
			}, this);
		} else {
			if (callback) callback.call(this, data, options);
		}
	}
	
	function actionCallback() {
		this.form.goQuery();
	}
	
	return {
		/** 弹出确认框，只有点击yes的时候才触发动作 */
		yes: function(worktop, message, url, params, type, callback, options) {
			var title = options &&options.title ? options : '提示';
			var method = type || 'get';
			MessageBox.confirm(title, message, function(action) {
				if (action == 'yes') {
					$[method](url, params, function(data){
						handleCallback.call(worktop, data, callback || actionCallback, options);
					}, 'json');
				}
			}, this);
		},
		yeah: function(message, callback, options) {
			var title = options && options.title ? options : '提示';
			MessageBox.confirm(title, message, function(action) {
				if (action == 'yes') {
					if (callback) callback.call(this);
				}
			}, this);
		},
		confirm: function(worktop, message, url, params, type, callback, options) {
			
		},
		alert: function(worktop, message, url, params, type, callback, options) {
			
		}
	}
})(jQuery, window);

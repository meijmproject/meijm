//平台级操作核心js
var LOGIN_URL = 'login.jsp';
(function($){
	$.ajaxSetup({
		cache: false, //关闭AJAX相应的缓存
		timeout: 300000,
		type: 'POST',
		statusCode: {
			988: function() {
				window.top.location.href=LOGIN_URL;
			},
			400: function() {
				alert('请求被拒绝：含有非法参数或非法字符。');
			}
		}
	});
})(jQuery);

//添加工具函数
(function($, window){
	 function applyIf(o, c){
        if(o && c){
			for(var p in c){
				if(typeof o[p] == "undefined"){ o[p] = c[p]; }
			}
		}
		return o;
    };
    function slice(ary,i,e) {
    	return Array.prototype.slice.call(ary, i, e);
    };
    //格式化string
	function format(fmt, o){
		var combine = $.isPlainObject(o);
		var args = combine ? o : slice(arguments, 1);
		var regex = combine ? /\{([a-zA-Z0-9_$]+)\}/g : /\{(\d+)\}/g ;
		return fmt.replace(regex, function(m,i) {
			return (args[i]||'');
		});
	};
    var extype = /\*|text|json|html|xml|script|jsonp/;
    //给jQuery对象添加静态工具方法
    applyIf($, {
    	//复制参数，或数组
    	slice: slice,
    	//带进度条的post方法
    	//$.request(url,callback,type|message,message)
		request: function() {
			var progress = true;
			var message = '处理中...';
			var args = slice(arguments, 0);
			
			if (typeof args[args.length-1] === 'boolean') {
				progress = args.pop();
			}
			//不是type类型，则视为message
			else if(typeof args[args.length-1] === 'string' && !extype.test($.trim(args[args.length-1]))) {
				message = args.pop();
			}
			
			// 0-url, 1-data, 2-callback, 3-type, true/false/message, message]
			if ( jQuery.isFunction( args[1] ) ) {
				args[3] = args[3] || args[2];
				args[2] = args[1];
				args[1] = undefined;
			}
			var ajaxSetting = {
				url: args[0],//0
				data: args[1],//1
				success: args[2],//2
				type: 'post',
				dataType: args[3]//3
			};
			if (message && (progress !== false)) {
				ajaxSetting.beforeSend = function() {
					$.ligerDialog.waitting(message);
				};
				ajaxSetting.complete =  function(){
					$.ligerDialog.closeWaitting();
				};
			}
			return jQuery.ajax(ajaxSetting);
		},
		//格式化string
		format: format,
	    //复制属性（浅复制），只要是声明的属性都拷贝，而不管是不是undefined
	    copyProperties: function(o, c, defaults) {
		    if(defaults){
		        // no "this" reference for friendly out of scope calls
		        $.copyProperties(o, defaults);
		    }
		    if(o && c && typeof c == 'object'){
		        for(var p in c){
		            o[p] = c[p];
		        }
		    }
		    return o;
		},
		//从数组中移除元素（如果存在）
		remove: function(o, array){
			if (array && array.length > 0) {
				var index = $.inArray(o, array);
				if (index != -1) {
					array.splice(index);
				}
			}
			return array;
		},
		//转成number，失败时，转为0
		parseNumber: function(o){
			switch(typeof o) {
			case 'string':
				o = o.replace(/[A-Za-z\s]+/,'');
				if ($.isNumeric(o)) {
					return new Number(o);
				}
			break;
			case 'number':
				return o;
			break;
			}
			return 0;
		},
		/** 
		 * 这个函数可以添加分隔逗号或者进行四舍五入。
		 *   Usage:  number_format(123456.789, 2, '.', ',');
		 *   result: 123,456.79
		 **/
		formatNumber: function (number, decimals, dec_point, thousands_sep) {
		    number = new String(number).replace(/[^0-9+-Ee.]/g, '');
		    var n = !isFinite(+number) ? 0 : +number,
		        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
		        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
		        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
		        s = '',
		        toFixedFix = function (n, prec) {
		            var k = Math.pow(10, prec);
		            return '' + Math.round(n * k) / k;
		        };
		    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
		    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
		    if (s[0].length > 3) {
		        s[0] = s[0].replace(/B(?=(?:d{3})+(?!d))/g, sep);
		    }
		    if ((s[1] || '').length < prec) {
		        s[1] = s[1] || '';
		        s[1] += new Array(prec - s[1].length + 1).join('0');
		    }
		    return s.join(dec);
		},
		//创建命名空间
		ns: function(o){
			var tail, vname, 
				__ns = '',
				container = window,
				args = Array.prototype.slice.call(arguments, 0)
				;
			if (typeof args[args.length-1] !== 'string') {
				tail = args.pop();
			}
			var namespaces = [];
			for (var i=0; i<args.length; i++) {
				namespaces = namespaces.concat(args[i].split('.'));
			}
			vname = namespaces.pop();
			for (var i=0; i<namespaces.length; i++) {
				__ns = __ns + (__ns?'.':'') + namespaces[i];
				container[namespaces[i]] = container[namespaces[i]] || {__ns: __ns};
				container = container[namespaces[i]];
			}
			__ns = __ns + (__ns?'.':'') + vname;
			return (container[vname] = container[vname] || tail || {__ns: __ns});
		},
		by: function(name,dom){
			var i = name.indexOf(':');
			var type = "*";
			if (i != -1) {
				type = name.substring(0,i);
				name = name.substring(i+1);
			}
			return $(format("{1}[name='{0}']",name,type),dom);
		},
		formBy: function(name,dom) {
			return $.by(name, $(dom).parentsUntil('form').parent());
		}
    });
})(jQuery, window);


//HistoryRegister、CookiesDataRegister、AttrDataRegister、DataRegister

/*
 * 跳转前页面
 * HistoryRegister.register('backVerWorktop', 'goVerJgPbPersonWorkbench.do?method=goVerJgPbPersonWorkbench', {param1:val1...});
 * 
 * 返回到前面注册过的页面，如果未注册，则返回默认url（goXXXUrl）
 * HistoryRegister.go('backVerWorktop', {unitOid: xxx}, 'goXXXUrl.do?method=xxx')
 * 
 */

/**
 * Cookie 数据寄存
 */
var CookiesDataRegister = (function($, window, document) {
	
	function isEnable() {
		
		setCookie('jCookies_isEnable', 'yes', 1000);
		
		if(getCookie('jCookies_isEnable')) {
			setCookie('jCookies_isEnable', 'yes', -1);
			return true
		};
		
		return false;
	}

	function setCookie(key, value, days) {
		var buff = [];
		buff.push(key);
		buff.push('=');
		buff.push(escape(value));
		if (days) {
			var expireDate = new Date();
			expireDate.setTime(expireDate.getTime() + days*24*3600*1000);
			buff.push('; expires=');
			buff.push(expireDate.toGMTString());
		}

		document.cookie = buff.join('');
	}

	function getCookie(key) {
		var cookies = document.cookie.split("; ");
		for (var i = 0; i < cookies.length; i++) {
			var crumb = cookies[i].split("=");
			if (key == crumb[0])
				return unescape(crumb[1]);
		}
		return null;
	}
	
	return {
		isEnable: isEnable,
		set : setCookie,
		get : getCookie,
		remove : function(key) {
			setCookie(key, '1', -1000);
		}
	};
})(jQuery, window, document);


/**
 * 内存（window.top） 操作工具类
 */
var AttrDataRegister = (function($, window, document) {
	
	var store = $(window.top.document.body);
	
	return {
		isEnable: function(){
			return true;
		},
		set : function(key, value) {
			store.attr(key, escape(value));
		},
		get : function(key) {
			return unescape(store.attr(key));
		},
		remove : function(key) {
			store.removeAttr(key);
		}
	};
})(jQuery, window, document);


/**
 * 数据存储寄存器
 * @type 
 */
var DataRegister = (function($, window, document) {
	
//	var dataRegisters = CookiesDataRegisters.isEnable() ? CookiesDataRegisters : MemoryDataRegisters;
	
	var dataRegister = (function(){
		if (!CookiesDataRegister.isEnable()) {
			alert('Cookies被禁用，部分功能将无法使用。');
			
			return AttrDataRegister;
		}
		
		return CookiesDataRegister;
	})();
	
	return {
		set: function(key, value) {
			dataRegister.set(key, value);
		},
		get: function(key) {
			return dataRegister.get(key);
		},
		remove: function(key) {
			dataRegister.remove(key);
		}
	};
})(jQuery, window, document);

/**
 * 保存要返回的url
 */
var HistoryRegister = (function($, window, document){
	
	function bindUrl(url,params) {
		if (url && params) {
			url = url + (url.indexOf('?') != -1 ? '&' : '?') + $.param(params); 
		}
		return url;
	}
	
	return {
		register: function(key, url, params) {
			if (params) {
				url = url + (url.indexOf('?') != -1 ? '&' : '?') + $.param(params); 
			}
			DataRegister.set(key, url);
		},
		
		set : function(key, url, params) {
			if (params) {
				url = url + (url.indexOf('?') != -1 ? '&' : '?') + $.param(params); 
			}
			DataRegister.set(key, url);
		},
		
		get: function(key) {
			return DataRegister.get(key);
		},
		
		go: function(key, params, defaultUrl) {
			var url = HistoryRegister.getUrl(key, params);
			if (url) {
				window.location.href = url;
				return;
			}
			if (defaultUrl) {
				//跳转到默认的地址
				window.location.href = bindUrl(defaultUrl, params);
				return;
			}
			
			return false;
		},
		
		getUrl: function(key, params) {
			return bindUrl(DataRegister.get(key), params);
		}
	};
})(jQuery, window, document);

function clearValue() {
	for (var i=0; i<arguments.length; i++) {
		if (arguments[i].is('input') || arguments[i].is('select')) {
			arguments[i].val('');
		} else {
			arguments[i].html('');
		}
	}
}

/**
 * 回调相关方法模块
 */
var CallbackUtils = (function($) {
	function handler(options, data) {
		//数据处理
		if(typeof data === 'string') {
			try {
				data = $.parseJSON(data);//json
			} catch (e) {
				(options.content||worktop.content).setContent(data);//无法解析则当做 html
				
				return;//返回的是html 结束，返回
			}
		}
		
		var callback = data.success !== false ? options.success : options.fail;
		//json处理
		if (data.message) {
			MessageBox.alert('提示',data.message, function() {
				if (callback) callback.call(options.scope||this, data, options);//返回的json、回调参数
			}, this);
		} else {
			if (callback) callback.call(options.scope||this, data, options);
		}
	};
	
	/**
	 * 通用JSON处理，返回一个处理函数，已设置了成功和失败回调函数
	 * 如果只传一个函数，则作为成功处理函数
	 * 如果只传一个对象，则作为配置对象，必须包含全部参数
	 * 如果想全权处理请求，则传{handler:fn}
	 * 请须按 content,success,fail,scope,dialog 的参数顺序，content没有时，可忽略，自动向前移位
	 */
	function ajaxCallback(content,success,fail,scope,dialog) {
		var config = {};
		if (arguments.length === 1) {
			if ($.isFunction(content)){
				config.handler = content;
			} else {
				config = content;
			}
		} else {
			config = {
				content: content,
				success: success,
				fail: fail === true ? success : $.isFunction(fail) ? fail : undefined,
				scope: scope,
				dialog: dialog,
				handler: handler
			};
		}
		//ajax callback
		return function(data){
			config.dom = this.dom;
			config.handler.apply(this, [config].concat(Array.prototype.slice.call(arguments, 0)));
		}
	};
	
	function create(content) {
		return function(){
			return ajaxCallback.apply(this, [content].concat(Array.prototype.slice.call(arguments, 0)));
		}
	}
	
	//暴露方法
	return {
		handler: handler,//通用处理
		createCallback: ajaxCallback,
		create: create,//
		recall: function(data,options) {
			if (options.dom) {
				Widget.close(options.dom);
				return;
			}
			Widget.close();
		},
		requery: function() {
			worktop.form.goQuery();
		}
	}
}(jQuery));
//如果不适用，ajaxSubmit是可以指定自己的回调函数的
var recall = CallbackUtils.recall;//关闭窗口 worktop.content.close();的简写；		复杂的变量环境可能不适用使用此方法
var requery = CallbackUtils.requery;//刷新数据 worktop.form.goQuery();的简写；	复杂的变量环境可能不适用使用此方法
var createCallback = CallbackUtils.createCallback;//通用创建函数
//通用创建函数 链式写法 var callback = CallbackUtils.create(worktop)(success,fail,scope,dialog);
//主要用于方便缓存worktop对象，创建通用函数，例如下面的实现
var ajaxCallback = CallbackUtils.create();
var formCallback = ajaxCallback(CallbackUtils.recall);//成功情况下才关闭窗口，失败情况下仅弹出提示，一般适用于提交表单数据
var jsonCallback = ajaxCallback(CallbackUtils.recall, true);//成功、失败情况下都弹出提示（如果有提示）并关闭窗口

/** ****** ajax工具模块 ****** */
var AjaxForm = (function($,window){
	//beforeSubmit, form, data, callback, dataType
	function ajaxSubmit() {
		var beforeSubmit, form, data, callback, dataType;//参数
		var args = Array.prototype.slice.call(arguments, 0);//复制参数
		beforeSubmit = $.isFunction(args[0]) ? args.shift() : undefined;//beforeSubmit
		form = args.shift();
		data = $.isPlainObject(args[0]) ? args.shift() : undefined;
		callback = $.isFunction(args[0]) ? args.shift() : undefined;
		dataType = args.shift();
		var $form = $(form);
//		$form.prop('method','post');//强制使用post提交form
		dataType = dataType || $form.data('accept');
		$form.ajaxSubmit({
			data : data,
			dataType : dataType,
			dom: $form[0],
			beforeSend : function() {
				viewer.loadMask();//加载中
			},
			complete : function(){
				viewer.loadMask(false);//关闭加载中
			},
			beforeSubmit: function(formValues, jqForm, o) {
				return beforeSubmit ? beforeSubmit.call(this, jqForm[0], formValues, o) : undefined;
			},
			success: callback || formCallback
		});
		return false;
	}
	
	//暴露方法
	return {
		ajaxSubmit: ajaxSubmit
	}
}(jQuery,window));
var ajaxSubmit = AjaxForm.ajaxSubmit;

/** ****** 验证方法模块 ****** */
var Validator = (function($,window) {
	//文件后缀名验证
	jQuery.validator.addMethod("filetype", function(val, ele, parms) {
		return parms = "string" == typeof parms ? parms.replace(/、|，|,/g, "|") : "png|jpe?g|gif", this.optional(ele) || val.match(new RegExp("\\.(" + parms + ")$", "i"))
	}, jQuery.validator.format("<font color='red'>文件类型必须为“{0}”</font>"));
	
	//正则验证
	jQuery.validator.addMethod("exp", function(val, ele, parms) {
		var parms = "string" == typeof parms ? new RegExp(parms) : parms;
		return parms.test(val);
	}, jQuery.validator.format("<font color='red'>不符合验证规则</font>"));
	
	//更改默认配置
	jQuery.validator.setDefaults({
		errorClass: "qv-error",
		pendingClass: "qv-pending",
		validClass: "qv-valid"
	});
	var defaults = {
//		debug:true,
//        success: function (lable) {
//            lable.remove();
//        },
		errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        wrapper: "li"
	};
	//Validator.init({},"#funcRoleFormId",[rule]{}/null,[message]{}/null, [submitHandler]fn/null);
	function validate() {
		var args = Array.prototype.slice.call(arguments, 0);//复制参数
		var conf = $.isPlainObject(args[0]) ? args.shift() : {};
		var form = args.shift();
		var rules =  $.isPlainObject(args[0]) ? args.shift() : undefined;
		var messages =  $.isPlainObject(args[0]) ? args.shift() : undefined;
		var submitHandler = $.isFunction(args[0]) ? args.shift() : undefined;
		var callback = args.shift();
		
		var $form = $(form);
		
		
		$form.validate($.copyProperties($.copyProperties({rules: rules, messages: messages, errorLabelContainer: $form.find('div.wrong')}, defaults), conf));
	}
	
	return {
		init: validate
	}
}(jQuery,window));
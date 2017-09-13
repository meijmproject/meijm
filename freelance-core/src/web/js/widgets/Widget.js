
// 页面操作组件集合，包含组件：ContentBox、Widget

/**
 * 弹出DIV层
 */
var ContentBox = (function($, window, document){
	
	var defaults = {
		/*width : 1400,//默认弹出宽的最大宽度
*/		height : 'auto',//默认弹出宽的高度
		buttons: {
			'widget-close': function(){
				this.close();
			}
		}
	};

	var me = function(settings) {
		this.settings = $.copyProperties({}, settings, defaults);
		this.initialSettings = $.copyProperties({}, this.settings);
		
		if($('#popdown-opacity-cb').length > 0) {
			$('#popdown-opacity-cb').remove();
		}
		
		this.opacityBg = $('<div />').attr('id', 'popdown-bg').css({
			position: 'absolute',
			top		: 0,
			left	: 0,
			width 	: '100%',
			height 	: '100%',
			zIndex	: 99998,
			background: 'rgba(0, 0, 0, 0.5)',
			'overflow-y': 'auto'
		});
		// Construct the background blend
		this.opacity = $('<div />').attr('id', 'popdown-opacity-cb').css({
			position: 'fixed',
			top		: 0,
			left	: 0,
			width 	: '100%',
			height 	: '100%',
			zIndex	: 99997,
			display : 'none'
		});

		// Construct the content container
		this.container = $('<div class="popdown-loading" />').attr('id', 'popdown-dialog-cb').css({
			/*maxWidth : defaults.width,*/
			height : defaults.height,
			top: 0,
			zIndex	: 99999,
			/*margin	: '0 auto',*/
			position: 'relative',
			display : 'none'
		});

		// Let's add the opacity to the doc body
		$('body').append(this.opacity);

		// Fade in the background blend & add content container
		this.opacity.append(this.container);
		var that = this;
		
		// Re-size the opacity when the window is resized
		$(window)
			.unbind('resize.wcb')
			.bind('resize.wcb', function() {
				if(that.opacity.length > 0) {
					that.opacity.css({
						width : $(document).outerWidth(),
						height: $(document).outerHeight()
					});
				}
			});
	
		// Bind the document ESC key
//		$(document)
//			.unbind('keyup.wcb')
//			.bind('keyup.wcb', function(e){
//				if(e.keyCode === 27) {
//					that.close();
//				}
//			});
	
		// General element to close the popdown
		$(document)
			.unbind('click.wcb')
			.on('click.wcb', '[button-click^=widget-]', function(e){
				var $this = $(this);
				if(!$this.is('[button-click^=widget-]'))  {	 //Only close when someone click on the html element with close-popdown class
					e.preventDefault();
					return;
				}
				that.buttonClick($this.attr('button-click'));
			});
		
		// Fade in the container and load the data
		that.opacity.append(that.opacityBg.append(that.container));
	}

	jQuery.extend(me.prototype, {
		close: function(){
			this.opacity.hide();
			this.container.children().remove();
			this.container.html('');
			this.container.css({
				maxWidth : defaults.width,
				height : defaults.height
			});
			if (this.settings.close) {
				this.settings.close.call(this);
			}
		},
		open: function(settings) {
			
			//是要声明的属性都拷贝，而不管是不是undefined
			this.settings = $.copyProperties({}, settings, this.initialSettings);
			
			var that = this;
			
			this.container.css({
				maxWidth : this.settings.width == null ? '' : this.settings.width == undefined ? defaults.width : this.settings.width,
				height : this.settings.height == null ? '' : this.settings.height == undefined ? defaults.height : this.settings.height
			});
			
			this.settings.type = (this.settings.type || 'post').toLowerCase();
			this.opacity.show();
			
			this.container.fadeIn(50, function(){
				$[that.settings.type](
					that.settings.url, 
					that.settings.params, 
					function(resp) {
						that.container.html(resp)
							.addClass('popdown-done')
							.removeClass('popdown-loading');
					});
			});
			
			return this;
		},
		buttonClick: function(action) {
			var fn = this.settings.buttons ? this.settings.buttons[action] : null;
			if (fn) fn.call(this);
		}
	});

	return me;
})(jQuery, window, document);


var Widget = (function($, window, document){
	var content;
	
	function getContent(){
		return content ? content : (content = new ContentBox());
	}
	
	return {
		
		getContent: getContent,
		
		//参数：url, params, closeCallback, options
		//Widget.openContent(url, params, function(){}, options);//接受全部参数
		//Widget.openContent(url, params, function(){});
		//Widget.openContent(url, params, options);
		//Widget.openContent(url, params);
		//Widget.openContent(url, function(){}, options);
		//Widget.openContent(url, function(){});//接受url、callback参数
		//Widget.openContent(url, null, options);//接受url、options两个参数
		openContent: function(){
			var args = Array.prototype.slice.call(arguments, 0);//复制参数
			var url = args.shift();
			var params = $.isFunction(args[0]) === false ? args.shift() : undefined;
			var closeCallback =  $.isFunction(args[0]) ? args.shift() : undefined;
			var options = args.shift();
			
			var conf = $.copyProperties({url: url, params: params, close: closeCallback}, options);
		
			return getContent().open(conf);
		},
		
		close: function() {
			
			getContent().close();
		},
		//load div，自动从div上查找url属性，方便重新加载
		load: function(divId, url, data, callback){
			var $div = $(divId);
			
			if ($.isPlainObject(url)) {
				callback = data;
				data = url;
				url = undefined;
			}
			
			if ($.isFunction(url)) {
				callback = url;
				data = undefined;
				url = undefined;
			}
			
			url = url || $div.attr('url');
			if (url) {
				$div.load(url, data, callback);
			}
		}
	}
	
})(jQuery, window, document);

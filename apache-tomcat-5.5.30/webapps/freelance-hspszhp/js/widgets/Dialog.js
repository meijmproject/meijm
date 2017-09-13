
// 方法名称、属性是下划线开头的表示私有方法，严禁外部调用

/**
 	弹出式模态对话框插件
 	//用法举例：
 	引入css：modal-dialog.css
	$(function(){
		var dialog = new Dialog().show({
			maxHeight: 200,//大于50
			maxWidth: 600,//大于300
			//minHeight: 300,//大于50
			//minWidth: 800,//大于300
			//height: 200,//大于50
			//width: 350,//大于300
			title:'提示提示提示提示',
			//close: false,
			//cancel: false,
			//lbYes: '好的',
			callback: function(action){
				alert(action);
			},
			icon:'tips',//success error tips question warning
			content:'提交不？'
		});
	});
 */
var Dialog = (function($, window, document){
	var html = [
		'<div class="modal-dialog">',
			'<div class="modal-mask"></div>',
			'<div class="modal-content-min">',
				'<div class="modal-header-min">',
					'<button type="button" class="close" button-click="close"><a href="javascript:;" class="anlu" ><span aria-hidden="true">&times;</span></a></button>',
					'<h4 class="modal-title"></h4>',
				'</div>',
				'<div class="modal-body">',
					'<div class="modal-icon"><i></i></div>',
					'<div class="modal-text"></div>',
				'</div>',
				'<div class="modal-footer">',
					'<button type="button" class="btn btn-primary" button-click="yes"><span class="button-text">确定<span></button>',
					'<button type="button" class="btn btn-default" data-dismiss="modal" button-click="cancel"><a href="javascript:;" class="button-text">取消</a></button>',
				'</div>',
			'</div>',
		'</div>'
	].join('');
	
	var defaults = {
		title: '',
		lbYes: '确定',
		lbCancel: '取消'
	};

	var jDialog = function(settings) {

		this.settings = $.extend(true, {}, defaults, settings);

		this.dialog = $(html).appendTo($(document.body));

		this.content = this.dialog.find('.modal-content-min');//内容
		this.header = this.dialog.find('.modal-header-min');//头
		this.body = this.dialog.find('.modal-body');//内容
		this.footer = this.dialog.find('.modal-footer');//页脚

		this.text = this.dialog.find('.modal-text');//内容
		this.title = this.dialog.find('.modal-title');//标题
		
		this.yes = this.dialog.find('[button-click=yes]');
		this.cancel = this.dialog.find('[button-click=cancel]');
		this.shut = this.dialog.find('[button-click=close]');

		this.iconWrap = this.dialog.find('.modal-icon');//图标
		this.icon = this.iconWrap.find('i');//图标
		
		var that = this;
		this.dialog.find('[button-click]').click(function(){
			that._buttonClick($(this).attr('button-click'));
		});

	}
	
	//保存递归调用的实例数
	var openInstances = 0;

	jQuery.extend(jDialog.prototype, {
		
		_build: function(options) {
			this.title.html(options.title);
			this.icon.removeClass();

			if (!options.icon) {
				this.iconWrap.hide();
				this.text.css('margin-left','auto');
			} else {
				this.iconWrap.show();
				this.text.css('margin-left','');
				this.icon.addClass('md-'+options.icon);
			}

			this.text.html(options.content);

			this.yes.find('.button-text').html(options.lbYes);
			this.cancel.find('.button-text').html(options.lbCancel);

			this.yes[options.yes === false ? 'hide' : 'show']();
			this.cancel[options.cancel === false ? 'hide' : 'show']();
			this.shut[options.close === false ? 'hide' : 'show']();
		},
		
		_close: function(){
			this.dialog.hide();
		},
		
		show: function(options) {
			openInstances++;
			this._close();

			options = $.extend(true, {}, this.settings, options);
			this.settings.callback = options.callback;
			
			this.content.css({'height':'auto', 'width':'auto', 'max-height':'auto', 'min-width':'350px'});
			this.body.css({'height':'auto', 'width':'auto', 'max-height':'auto', 'min-width':'350px'});
			
			this._build(options);

			this.dialog.show();

			this._adjustPosition(options);

			return this;
		},
		_adjustPosition: function(options) {
			
			var maxHeight = $(window).height() - 100;
			maxHeight = options.maxHeight && options.maxHeight < maxHeight && options.maxHeight > 50 ? options.maxHeight : maxHeight;
			var maxWidth  = $(window).width() - 600;
			maxWidth =  options.maxWidth && options.maxWidth < maxWidth && options.maxWidth > 300 ? options.maxWidth : maxWidth;

			var bMaxHeight = maxHeight - this.header.outerHeight(true) - this.footer.outerHeight(true);
			
			this.content.css({'max-height':maxHeight, 'max-width':maxWidth, 'min-height': options.minHeight, 'min-width': options.minWidth});
			this.body.css({'max-height': '210px', 'max-width':maxWidth, 'min-height': options.minHeight, 'min-width': options.minWidth});
			
			var height = options.height && options.height > 50 ? options.height : this.content.height();
			var width = options.width && options.width > 300 ? options.width : this.content.width();
			
			//var bHeigth = height - this.header.outerHeight(true) - this.footer.outerHeight(true);

			//this.content.width(width);
			//this.content.height(height);

			//this.body.outerWidth(width);
			//this.body.outerHeight(bHeigth);
			
			/*this.content.css({'margin-top': -1*height/2, 'margin-left': -1*width/2});*/
		},
		_buttonClick: function(action) {
			if ($.isFunction(this.settings.callback)) {
				this.settings.callback.call(this, action);
			}
			
//			if (action == 'cancel' || action == 'close') {
//				this._close();
//				openInstances = 0;
//				return;
//			}
			
			if (openInstances == 1) {
				this._close();
			}
			if (openInstances > 0) {
				openInstances --;
			}
		}
		,
		close: function(){
			openInstances = 0;
			this._close();
			return this;
		}
	});

	return jDialog;
})(jQuery, window, document);
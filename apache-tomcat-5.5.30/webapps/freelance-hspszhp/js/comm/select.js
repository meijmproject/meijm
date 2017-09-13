/**
 * 下拉框
 * @class Select
 * @constructor
 */
var Select = (function() {
	
	var Select = function(options) {
		this.data = [];
		options || (options={});
		this.options = $.extend({}, {
			$text: null,
			$value: null,
			url: 'listSelectCondition.do?method=listSelectCondition',
			multiple: false,
			textField: 'dicItemName',
			valueField: 'dicItemCode',
			params: null,
		}, options);
		this.init();
	}
	
	$.extend(Select.prototype, {
		init: function() {
			var
				that = this,
				options = this.options,
				url = options.url,
				params = options.params;
			if(url) {
				$.get(url, params, function(response) {
		    		that.data = response.list;
		    		that.renderData();
		    	},'json');
			}
		},
		renderData: function() {
			var
				that = this,
				options = this.options,
				data = this.data,
				defaults = options.$value.val().split(',');
			$options = $('<ul class="s-pulldown-list"></ul>');
			$options.width(options.$text.width());
			options.$text.click(function(e) {
				$options.toggle();
			});
			options.$text.after($options);
			data.forEach(function(v, i) {
				var $option = $('<li class="s-pdl-li"><input type="hidden" value="'+v[options.valueField]+'">'+v[options.textField]+'</li>');
				$options.append($option);
				$option.click(function(e) {
					var text = value = '';
					$(this).toggleClass('s-pdl-selected');
					if(options.multiple) {
						$options.find('li').each(function(i,v) {
				          	if($(v).hasClass('s-pdl-selected')){
				          		text += text?(','+$(v).text()):($(v).text());
				          		value += value?(","+$(v).find('input[type=hidden]').val()):($(v).find('input[type=hidden]').val());
				          	}
				        });
						
					}else {
			          	$(this).siblings().removeClass('s-pdl-selected');
			          	if($(this).hasClass('s-pdl-selected')) {
			          		value = $(this).find('input[type=hidden]').val();
			          		text = $(this).text();
			          	}
					}
					options.$text.val(text);
					options.$value.val(value);
					return false;
				});
				if($.inArray(v[options.valueField], defaults)!=-1) {
					$option.click();
				}
			});
		},
		clear: function() {
			this.options.$text.next('ul').find('li').removeClass('s-pdl-selected');
			this.options.$text.val('');
			this.options.$value.val('');
		}
	});
	
	return Select;
}());
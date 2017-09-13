
// 包含组件：TplQueryForm、QueryForm

/**
 * form模板，封装了form通用的方法，可使用 TplQueryForm.extend 扩展使用。
 * 
 * @author zhangqp, 2016/09/07
 */
var TplQueryForm = (function($, window, document){
	
	var me = function(settings) {
		this.settings = settings || {};
		$.copyProperties(this, settings);
	};
	
	//扩展接口
	me.extend = function(obj, settings, render) {
		me.call(obj, settings);
		//属性、方法全部拷贝到obj中
		var mixin = me.prototype;
		for (var p in mixin) {
			obj[p] = mixin[p];
		}
		if ($.isFunction(render)) {
			render.call(obj);
		}
	};

	jQuery.extend(me.prototype, {
		//[{name:'',value:''}]形式的数组
		getValues: function() {
			return this.el.serializeArray();
		},

		goQuery : function(beforeQuery) {
			var that = this;
			
			var values = this.getValues();
			
			if (beforeQuery 
					&& $.isFunction(beforeQuery) 
					&& beforeQuery.call(this, values) === false) {
				return;
			}
			
			var params = {};
			$.each(values, function() {
				params[this.name] = this.value;
			});
			$.copyProperties(this.worktop.grid.store.baseParams, params);
			var pageNo=(parseInt(this.worktop.items[0].start)-1)*(this.worktop.grid.page.limit);
			if(isNaN(pageNo)){pageNo=0;}
			this.worktop.grid.store.load({
				params: {start:pageNo, limit: this.worktop.grid.page.limit}
			});
		}
	});
	
	return me;
})(jQuery, window, document);

/**
 * 对页面form标签添加查询功能
 * 
 * @author zhangqp, 2016/09/08
 */
var QueryForm = (function($, window){
	
	return function(settings){
		//加入基本方法，类似继承
		TplQueryForm.extend(this, settings, function() {
			//wrap 	包裹本form组件的div节点 	（一般是用来指定生成位置的）
			//ct 	生成的html节点			（html中生成的<div class='search'>）
			//el	操作的主dom节点			（form）
			// 以上节点可按使用情况提供
			
	//		this.ct = $(html).appendTo($(this.wrap || 'body'));//初始化位置由实现者控制
			this.el = $(this.settings.el);//form el
			var that = this;
			
			this.el.submit(function(){ return false; });
			this.el.find('button.btn_search').click(function(){
				that.goQuery();
			});
			this.el.find('button.btn-search').click(function(){
				that.goQuery();
			});
		});
	};
})(jQuery, window, document);

/**
 * 自定义查询表单
 * 
 * @author liaomh 2017/3/3
 */
(function($, window){
	
	/**
	 * @class SearcherForm
	 * @constructor
	 */
	var SearcherForm = window.SearcherForm = function(options) {
		TplQueryForm.extend(this, options, function() {
			this.el = $(options.el);//form el
			var that = this;
			//重写TplQueryForm的获取表单值方法
			this.getValues = function() {
				return this.searchComponent.getConditionForm();
			}
			//重写TplQueryForm的请求方法 -- 由于参数需要动态改变，所以加了this.worktop.grid.store.baseParams = {};
			this.goQuery = function(beforeQuery) {
				var that = this;
				
				var values = this.getValues();
				
				if (beforeQuery 
						&& $.isFunction(beforeQuery) 
						&& beforeQuery.call(this, values) === false) {
					return;
				}
				
				var params = {};
				$.each(values, function() {
					params[this.name] = this.value;
				});
				this.worktop.grid.store.baseParams = {};
				$.copyProperties(this.worktop.grid.store.baseParams, params);
				var pageNo=(parseInt(this.worktop.items[0].start)-1)*(this.worktop.grid.page.limit);
				if(isNaN(pageNo)){pageNo=0;}
				this.worktop.grid.store.load({
					params: {start:pageNo, limit: this.worktop.grid.page.limit}
				});
			}
			this.el.submit(function(){ return false; });
		});
			this.searchComponent = new SearchComponent({
				$el: this.el,
				conditionArray: options.conditionArray,
				conditionUrl: options.conditionUrl,
				defaultConditionCodeArray: options.defaultConditionCodeArray,
				defaultConditionParam: options.defaultConditionParam,
				selectOrg: options.selectOrg,
				searchCallBack: this.goQuery.bind(this)
			});
			
	}
	
  /**
   * 监听器
   */
  var Observer = {
	  //监听
		on: function(name, callback, context) {
	    this._events || (this._events = {});
	    this._events[name] || (this._events[name] = []);
	    this._events[name].push({name: name, callback: callback, context: context});
	  },
	  //他监听
	  listen: function(obj, name, callback) {
	    if(!obj.on || !obj.on===Observer.on) return;
	    obj.on(name, callback, this);
	  },
	  //触发
	  trigger: function(name) {
	    if(!this._events) return;
	    var listeners = this._events[name];
	    if(listeners && listeners.length>0) {
	      var length = Math.max(0, arguments.length-1);
	      var args = Array(length);
	      for(var i=0; i<length; i++) args[i] = arguments[i+1];
	      for(var i=0, len=listeners.length; i<len; i++) {
	        listeners[i].callback.apply(listeners[i].context||this, args);
	      }
	    }
	  }
  };
  var BriefSearchUnit = function(options) {
  	this.$el = $('<li></li>');
  	this.$title = $('<span></span>');
  	this.$sign = $('<span style="padding:0 5px;color:red;"></span>');
  	this.$value = $('<span></span>');
  	this.$close = $('<span class="search-selected-close"></span>');
  	
  	this.title = options.title || '';
  	this.sign = options.sign || '';
  	this.value = options.value || '';
  	this.init();
  }
  $.extend(BriefSearchUnit.prototype, Observer, {
  	init: function() {
  		this.render();
  		this.initEvents();
  	},
  	render: function() {
  		this.$title.html(this.title);
  		this.$sign.html(this.sign);
  		this.$value.html(this.value);
  		this.$el.append(this.$title).append(this.$sign).append(this.$value).append(this.$close);
  		return this;
  	},
  	initEvents: function() {
  		var that = this;
  		this.$close.click(function() {
  			that.remove();
  			that.trigger('remove');
  		});
  	},
  	remove: function() {
  		this.$el.remove();
  	}
  });
  /**
   * 单个查询条件组件
   * @class SearchUnit
   * @constructor
   */
  var SearchUnit = function(options) {
    this.$el = $('<dl class="search-unit"></dl>');
    this.$close = $('<span class="search-unit-close"></span>');
    this.$dt = $('<dt class="search-unit-dt"></dt>');
    this.$dd = $('<dd class="search-unit-dd"></dd>');
    this.$conditionSelector = $('<select></select>');
    this.$signSelector = $('<select style="width:80px;"></select>');
    this.$valueDOM = $('<input type="text"/>');//默认的查询为输入框查询
    
    this.options = options || {};
    this.conditionArray = options.conditionArray || [];//查询条件数组
    this.defaultConditionCode = options.defaultConditionCode;//默认的查询条件代码
    this.defaultConditionSign = options.defaultConditionSign || null;//默认的查询条件符号
    this.defaultConditionValue = options.defaultConditionValue || null;//默认的查询条件值
    this.currentField;//当前输入框/选择框
    this.editable = options.editable;//是否可编辑
    this.selectOrg = options.selectOrg;//单位选择框参数
    this.init();
    this.$el.append(this.$close).append(this.$dt).append(this.$dd);
  }
  $.extend(SearchUnit.prototype, Observer, {
    init: function() {
      this.renderConditionSelector().renderSignSelector();
      this.$dt.append(this.$conditionSelector).append(this.$signSelector);
      this.selectCondition();
      this.initEvents();
    },
    //初始化一些DOM事件
    initEvents: function() {
      var that = this;
      this.$conditionSelector.change(function() {
      	that.currentField.clearValue();
      	that.renderSignSelector();
        that.selectCondition();
      });
      this.$close.click(function() {
        that.remove();
        that.trigger('remove', that);
      });
      if(this.editable) {
      	this.$el.mouseover(function() {
        	$(this).addClass("search-unit-hover");
          that.$close.show();
        });
        this.$el.mouseout(function() {
        	$(this).removeClass("search-unit-hover");
          that.$close.hide();
        });
      }
      this.$signSelector.change(function() {
      	//that.clear();
      	if(that.currentField instanceof SelectField) {
      		if($(this).val()!='in') {
      			that.currentField.multiple = false;
      		}else {
      			that.currentField.multiple = true;
      		}
      	}
      });
    },
    clear: function() {
    	this.currentField.clearValue();
    },
    remove: function() {
    	if(this.brief) {
    		this.brief.$el.remove();
    		this.brief = null;
    	}
    	this.$el.remove();
    },
    //渲染条件选择下拉框
    renderConditionSelector: function() {
      if(!this.editable) {
        this.$conditionSelector.attr('disabled',true);
      }else {
        this.$el.addClass('high-search-unit');
      }
      this.conditionArray.forEach(function(v,i) {
        var selected = this.defaultConditionCode==v.conditionCode ? 'selected' : '';
        var $option = $('<option value="'+v.conditionCode+'" '+selected+'>'+v.conditionName+'</option>').data(v);
        this.$conditionSelector.append($option);
      },this);
      if(!this.editable) {
    	  this.$conditionSelector.addClass('as-label');
      }
      this.renderSignSelector();
      return this;
    },
    //渲染符号选择下拉框
    renderSignSelector: function() {
    	this.$signSelector.empty();
    	if(this.getSelectedCondition().symbolList){
	    	this.getSelectedCondition().symbolList.forEach(function(v,i) {
	    		var selected = this.defaultConditionSign==v.symbolValue?'selected':'';
	    		this.$signSelector.append('<option value="'+v.symbolValue+'" '+selected+'>'+v.symbolName+'</option>');
	    	}, this);
    	}
    	return this;
    },
    //渲染查询条件的样式
    selectCondition: function() {
      var condition = this.getSelectedCondition();
      //if(!this[condition.conditionCode]){
	      this.currentField = FieldFactory.makeField({
	      	multiple: this.getSelectedSignValue()=='='?false:true,
	    	  condition: condition,
	    	  $el: this.$dd,
          selectOrg: this.selectOrg,
          defaultValue: this.defaultConditionValue
	      });
	      //this[condition.conditionCode] = this.currentField;
      //}else {
    	  //this[condition.conditionCode].render();
      //}
    },
    //获取所选择的查询条件
    getSelectedCondition: function() {
      return this.$conditionSelector.find('option:selected').data();
    },
    //获取所选择的查询条件的代码
    getSelectedConditionCode: function() {
      return this.getSelectedCondition().conditionCode;
    },
    //获取所选择的查询条件的数据库字段
    getSelectedConditionDatabaseField: function() {
      return this.getSelectedCondition().databaseField;
    },
    //获取所选择的查询符号值
    getSelectedSignValue: function() {
      return this.$signSelector.find('option:selected').val();
    },
    getSelectedSignText: function() {
      return this.$signSelector.find('option:selected').text();
    },
    //获取所选择的查询条件的数据类型
    getSelectedConditionType: function() {
    	return this.getSelectedCondition().conditionType;
    },
    //获取用户录入的值
    getValue: function() {
      return this.currentField.getValue();
    },
    getText: function() {
      return this.currentField.getText();
    }
  });
  //定义一个工厂，它可以根据选择类型，使用合适的类
	var FieldFactory = {
			makeField : function(options){
				var options = options || {},
						type = options.condition.conditionType,
						field;
				switch(type){
				case "input":
					field = new InputField(options);
					break;
				case "date":
					field = new DateField(options);
					break;
				case "select":
					field = new SelectField({
						$el: options.$el,
				    textField: 'dicItemName',
				    valueField: 'dicItemCode',
				    stripe: false,
				    multiple: options.multiple,
				    readonly: true,
				    url: 'listSelectCondition.do?method=listSelectCondition',
				    params: {dictionaryType: options.condition.dicType},
				    parse: 'list',
				    defaultValue: options.defaultValue
					});
					break;
				case "selectOrg":
					field = new SelectOrgField(options);
					break;
				case "selectModel":
					field = new SelectModalField(options);
					break;
				}
				return field;
			}
	}
	function InputField(options){
		this.options = options || {};
		this.$el = options.$el;
		this.$input = $('<input type="text"/>');
		this.render();
	}
	InputField.prototype = {
			render: function() {
				this.$input.attr('name', this.options.condition.conditionCode);
				this.$el.html(this.$input);
			},
			getValue: function(){
				return this.$input.val();
			},
			getText: function() {
				return this.$input.val();
			},
			clearValue: function() {
				this.$input.val('');
			}
	}
	function DateField(options){
		this.options = options || {};
		this.$el = options.$el;
		this.$date = $('<input type="text" onfocus=WdatePicker({dateFmt:"yyyy-MM-dd"})>');
	  this.render();
	}
	DateField.prototype = {
			render: function() {
				this.$date.attr('name', this.options.condition.conditionCode);
				this.$el.html(this.$date);
			},
			getValue: function(){
				return this.$date.val();
			},
			getText: function() {
				return this.$date.val();
			},
			clearValue: function() {
				this.$date.val('');
			}
	}
	/**
   * 下拉框
   * @Class SelectField
   * @Constructor
   */
  var SelectField = function(options) {
    this.$el= $('<div></div>');
    this.$text = $('<input type="text"/>');
    this.$value = $(' <input type="hidden"/>');
    this.$options = $('<ul class="s-pulldown-list"></ul>');
    this.options = options || {};
    this.$el = this.options.$el || this.$el;
    this.stripe = this.options.stripe==false?false:true;
    this.multiple = this.options.multiple;
    this.readonly = this.options.readonly;
    this.data = this.options.data || [];
    this.textField = this.options.textField || 'text';
    this.valueField = this.options.valueField || 'value';
    this.url = this.options.url;
    this.params = this.options.params;
    this.parse = this.options.parse;
    this.defaultValue = this.options.defaultValue;
    this.on('load', this.render);
    this.init();
  }
  $.extend(SelectField.prototype, Observer, {
    init: function() {
      this.load();
    },
    render: function() {
      this.$el.empty();
      this.$el.css({width: this.width, position: 'relative'});
      if(this.readonly) this.$text.attr('readonly', true);
      this.renderData();
      this.$el.append(this.$text).append(this.$value).append(this.$clear).append(this.$options);
      
      var searchUnitOffset = this.$text.offset();
      var searchUnitWidth = this.$text.outerWidth();
      this.$options.css({ top: searchUnitOffset.top+35, left: searchUnitOffset.left });
      this.$options.width(searchUnitWidth);
    },
    load: function() {
    	var that = this;
    	$.get(this.url, this.params, function(response) {
    		that.data = that.parse?response[that.parse]:response;
    		that.trigger('load');
    	},'json');
    },
    renderData: function() {
    	var that = this;
      this.$options.html('');
      this.data && this.data.forEach(function(v,i) {
      	var $option,$hidden;
        if(this.stripe) {
          $option = i%2 ? $('<li class="s-pdl-li"></li>') : $('<li class="s-pdl-li" style="background-color:#eceff3;"></li>');
        }else {
          $option = $('<li class="s-pdl-li"></li>');
        }
        $hidden = $('<input type="hidden" value="'+v[this.valueField]+'"/>');
        this.$options.append($option.append($hidden).append(v[this.textField]));
        $option.click(function(e) {
        	var text = '',
        			value = '';
          if(that.multiple) {
          	$(this).toggleClass('s-pdl-selected');
	          that.$options.find('li').each(function(i,v) {
	          	if($(v).hasClass('s-pdl-selected')){
	          		text += text?(','+$(v).text()):($(v).text());
	          		value += value?(","+$(v).find('input[type=hidden]').val()):($(v).find('input[type=hidden]').val());
	          	}
	          }, that);
          }else {
          	$(this).toggleClass('s-pdl-selected');
          	$(this).siblings().removeClass('s-pdl-selected');
          	if($(this).hasClass('s-pdl-selected')) {
          		value = $(this).find('input[type=hidden]').val();
          		text = $(this).text();
          	}
          }
          that.$text.val(text);
          that.$value.val(value);
        	e.stopPropagation();
        });
      },this);
      this.initEvents();
      this.defaultValue&&this.defaultValue.forEach(function(v,i) {
    	  this.$options.find('input[value="'+v+'"]').click();
      }, this);
      worktop.form.goQuery();
    },
    initEvents: function() {
      var that = this;
      this.$text.click(function(e) {
        that.$options.toggle();
        e.stopPropagation();
      });
      $('body').click(function(e) {
      	that.$options.hide();
      });
      if(!this.multiple) {
	      this.$text.keyup(function() {
	        that.filte();
	      });
      }
      this.$text.click(function() {
    	  that.rePlaceOptions();
      });
	    $(window).resize(function () {
	    	that.rePlaceOptions();
	    });
    },
    rePlaceOptions: function() {
    	var left = this.$text.offset().left;
        var searchUnitWidth = this.$text.outerWidth();
        this.$options.css({left:left,width:searchUnitWidth});
    },
    getValue: function() {
    	return this.$value.val();
    },
		getText: function() {
			return this.$text.val();
		},
    filte: function() {
      var text = this.$text.val();
      this.$options.find('li').each(function(i, v) {
        var t = $(v).text();
        if(!t.match(text)) {
          $(this).hide();
        }else if(t.match(text)) {
          $(this).show();
        }
      });
      this.$options.show();
    },
		clearValue: function() {
			this.$value.val('');
			this.$text.val('');
			this.$options.find('li').removeClass('s-pdl-selected');
		}
  });
  var SelectOrgField = function(options) {
  	this.options = options || {};
  	this.$text = $('<input type="text" class="modal_iput" name="deptName" id="deptOid" readonly="readonly" onclick="min_selOrg.min_selectOrg(this,\''+this.options.selectOrg.unitOid+'\')"/>');
  	this.$value = $('<input type="hidden" name="deptOid"/>');
  	this.render();
  }
  SelectOrgField.prototype = {
			render: function() {
				this.options.$el.empty();
				this.options.$el.append(this.$text).append(this.$value);
			},
			getValue: function(){
				return this.$value.val();
			},
			getText: function() {
				return this.$text.val();
			},
			clearValue: function() {
				this.$value.val('');
				this.$text.val('');
			}
	}
  var SelectModalField = function(options) {
  	this.options = options || {};
  	this.$text = $('<input type="text" class="modal_iput" name="" id="'+this.options.condition.conditionCode+'" readonly="readonly" onclick="min_Dic.min_Dictionary(this,\''+this.options.condition.dicType+'\',null,\'Y\',null,null,null,\'fixed\')"/>');
    this.$value = $('<input type="hidden" name="'+this.options.condition.conditionCode+'"/>');
    this.render();
  }
  SelectModalField.prototype = {
			render: function() {
				this.options.$el.empty();
				this.options.$el.append(this.$text).append(this.$value);
			},
			getValue: function(){
				return this.$value.val();
			},
			getText: function() {
				return this.$text.val();
			},
			clearValue: function() {
				this.$value.val('');
				this.$text.val('');
			}
	}
  /**
   * 查询表单组件
   * @constructor
   * @class SearchComponent
   */
  var SearchComponent = function(options) {
    this.$searchContent = $('<div class="search-content"></div>');
  	this.$searchSelected = $('<div class="search-selected clearfix"></div>');
  	this.$searchSelectedTitle = $('<div class="search-selected-title">已选条件 &gt; </div>');
  	this.$searchSelectedItemsWrapper = $('<div class="search-selected-cont"></div>');
  	this.$searchSelectedItems = $('<ul class="search-selected-item"></ul>');
  	this.$prevItems = $('<div class="top-group" style="visibility: visible;"></div>');
  	this.$nextItems = $('<div class="bottom-group" style="visibility: visible;"></div>');
  	this.itemsPosition = 0;//标签展示位置
    this.$searchInclude = $('<div class="search-include clearfix"></div>');
    this.$searchBtnGroup = $('<dl class="search-unit search-btns"></dl>');
    this.$searchBtn = $('<button>查询</button>');
    this.$allClearBtn = $('<button>全部清除</button>');
    this.$highSearchBtn = $('<button class="search-advanced-btn"><span>高级查询</span></button>');
    this.$searchAdvanced = $('<div class="search-advanced clearfix"></div>');
    this.$searchAdvancedInclude = $('<div class="search-advanced-include"></div>');
    this.$imgDl = $('<dl class="search-unit search-unit-add"></dl>'); 
    this.$addImg = $('<img src="hspszhphtml/images/components/search_unit/add-condition.png" height="60" width="60"/>');
    
    this.options = options || {};
    this.$el = options.$el;
    this.url = options.url;
    this.queryType = options.queryType || 'sql';//使用sql还是hql查询
    this.conditionUrl = options.conditionUrl;//获取查询条件数组的url
    this.conditionArray = options.conditionArray || [];//查询条件数组
    this.defaultConditionCodeArray = options.defaultConditionCodeArray || [];//默认查询条件代码数组
    this.defaultConditionParam = options.defaultConditionParam;//默认的符号和值的配置（和defaultConditionCodeArray中的元素一一对应）
    this.defaultSearchUnitArray = [];//存储默认显示的查询条件组件数组
    this.moreSearchUnitArray = [];//存储添加的查询条件组件数组
    this.hasConditionCodeArray = [];//存储已经存在的条件代码数组
    this.selectOrg = options.selectOrg;//单位选择框参数
    this.searchCallBack = options.searchCallBack;//查询函数
    this.on('reload', this.reload);
    this.init();
    this.$searchContent.append(this.$searchSelected).append(this.$searchInclude);
    this.$searchSelectedItemsWrapper.append(this.$searchSelectedItems);
    this.$searchSelected.append(this.$searchSelectedTitle).append(this.$searchSelectedItemsWrapper).append(this.$prevItems).append(this.$nextItems);
    this.$el.append(this.$searchContent);
  }
  $.extend(SearchComponent.prototype, Observer, {
    init: function() {
    	this.renderSearchBtnGroup();
    	var that = this;
    	if(this.conditionUrl){//如果查询条件数组是通过后台读取
    		$.get(that.conditionUrl, function(response) {
    			that.conditionArray = response.list;
    			that.trigger('reload');
    		},'json');
    	}else {
    		that.trigger('reload');
    	}
    },
    //重新加载
    reload: function() {
    	this.$imgDl.append(this.$addImg);
      this.renderDefaultSearchInclude().$searchInclude.append(this.$searchBtnGroup).append(this.$highSearchBtn).append(this.$searchAdvanced.append(this.$searchAdvancedInclude.append(this.$imgDl)));
      this.initEvents();
    },
    //渲染默认的查询条件组件
    renderDefaultSearchInclude: function() {
      this.defaultConditionCodeArray.forEach(function(v,i) {
        if(this.indexOfCondition(v)>-1) {
          var defaultSearchUnit = new SearchUnit({
            conditionArray: this.conditionArray,
            defaultConditionCode: v,
            defaultConditionSign: this.defaultConditionParam[i].defaultSign,
            defaultConditionValue: this.defaultConditionParam[i].defaultValue,
            selectOrg: this.selectOrg
          });
          this.listen(defaultSearchUnit, 'clear', this.reSearch);
          this.hasConditionCodeArray.push(v);
          this.defaultSearchUnitArray.push(defaultSearchUnit);
          this.$searchInclude.append(defaultSearchUnit.$el);
        }
      },this);
      //this.$searchInclude.append(this.$addImg);
      return this;
    },
    //查询条件代码在查询数组中的位置
    indexOfCondition: function(value) {
      var index = -1;
      this.conditionArray.forEach(function(v,i) {
        if(v.conditionCode==value) {
          index= i;
          return;
        }
      });
      return index;
    },
    //渲染查询按钮
    renderSearchBtnGroup: function() {
    	var $dt = $('<dt></dt>');
    	var $dd = $('<dd></dd>');
      this.$searchBtnGroup.append($dt.append(this.$searchBtn)).append($dd.append(this.$allClearBtn));
      return this;
    },
    //初始化一些DOM事件
    initEvents: function() {
      var that = this;
      this.$highSearchBtn.click(function() {
        /*that.toggleMoreSearchCondition();*/
      	if ($('.search-advanced').is(':hidden')){
      			that.$searchAdvanced.show();
	          //$(this).addClass('sab-click');
	          //that.$searchAdvancedInclude.append($(this));
	      }else {
	          //$(this).removeClass('sab-click');
	          //that.$searchInclude.append($(this));
	          that.$searchAdvanced.hide();
	      }
      });
      this.$addImg.click(function() {
        that.addSearchUnit();
      });
      this.$searchBtn.click(function() {
      	that.reSearch();
      });
      this.$allClearBtn.click(function() {
      	that.defaultSearchUnitArray.forEach(function(v,i) {
      		v.clear();
      	},that);
      	that.moreSearchUnitArray.forEach(function(v,i) {
      		v.clear();
      		v.remove();
      	},that);
      	that.moreSearchUnitArray = [];
      	that.reSearch();
      });
      this.$prevItems.click(function() {
      	var $li = that.$searchSelectedItems.find('li');
      	if($li.length>0) {
      		var perHeight = $li.outerHeight()+parseInt($li.css('marginTop'))+parseInt($li.css('marginBottom'));
      		that.itemsPosition = that.itemsPosition==0 ? 0 : that.itemsPosition - perHeight;
      		that.$searchSelectedItemsWrapper.animate({scrollTop: that.itemsPosition}, 200);
      	}
      });
      this.$nextItems.click(function() {
      	var $li = that.$searchSelectedItems.find('li');
      	if($li.length>0) {
      		var perHeight = $li.outerHeight()+parseInt($li.css('marginTop'))+parseInt($li.css('marginBottom'));
      		var scrollHeight = that.$searchSelectedItemsWrapper.prop('scrollHeight')-perHeight;
      		that.itemsPosition = that.itemsPosition<scrollHeight ? that.itemsPosition+perHeight : that.itemsPosition;
      		that.$searchSelectedItemsWrapper.animate({scrollTop: that.itemsPosition},200);
      	}
      });
    },
    reSetItemsPosition: function() {
    	this.itemsPosition = 0;
    	this.$searchSelectedItemsWrapper.animate({scrollTop: this.itemsPosition},200);
    },
    reSearch: function() {
    	this.$searchSelectedItems.empty();
    	this.reSetItemsPosition();
    	this.defaultSearchUnitArray.forEach(function(v,i) {
    		if(v.getValue()) {
    			v.brief = new BriefSearchUnit({
    				title: v.getSelectedCondition().conditionName,
    				sign: v.getSelectedSignText(),
    				value: v.getText()
    			});
    			this.$searchSelectedItems.append(v.brief.$el);
    			v.listen(v.brief, 'remove', function(){
    				v.clear();
    				v.trigger('clear');
    			});
    		}
    	},this);
    	this.moreSearchUnitArray.forEach(function(v,i) {
    		if(v.getValue()) {
    			v.brief = new BriefSearchUnit({
    				title: v.getSelectedCondition().conditionName,
    				sign: v.getSelectedSignText(),
    				value: v.getText()
    			});
    			this.$searchSelectedItems.append(v.brief.$el);
    			v.listen(v.brief, 'remove', function(){
    				v.remove();
    				v.trigger('remove', v);
    			});
    		}
    	},this);
    	this.searchCallBack();
    },
    //进入或退出高级查询
    toggleMoreSearchCondition: function() {
    	if (this.$searchAdvanced.is(':hidden')){
    			this.$searchAdvancedInclude.show();
    			this.$highSearchBtn.text('收起');
	    }else {
	    	this.$searchAdvancedInclude.hide();
	      this.$highSearchBtn.text('高级搜索');
	    }
    },
    //添加查询条件组件
    addSearchUnit: function() {
      //if((this.defaultSearchUnitArray.length+this.moreSearchUnitArray.length)<this.conditionArray.length) {
      var su = new SearchUnit({conditionArray:this.conditionArray,editable:true,selectOrg: this.selectOrg});
      this.listen(su,'remove',this.removeSearchUnit.bind(this));
      this.$imgDl.before(su.$el);
      this.moreSearchUnitArray.push(su);
      //}
    },
    //移除添加的查询条件组件
    removeSearchUnit: function(obj) {
    	this.reSetItemsPosition();
      var index = $.inArray(obj, this.moreSearchUnitArray);
      if (index >= 0) {
          this.moreSearchUnitArray.splice(index, 1);
      }
      this.reSearch();
    },
    //获取已经存在的查询条件数组
    getHasConditionArray: function() {
      var hasConditionArray = [];
      this.defaultSearchUnitArray.forEach(function(v,i) {
        hasConditionArray.push(v.getSelectedCondition());
      });
      this.moreSearchUnitArray.forEach(function(v,i) {
        hasConditionArray.push(v.getSelectedCondition());
      });
      return hasConditionArray;
    },
    //获取查询条件表单数组
    getConditionForm: function() {
      var array = [];
      this.defaultSearchUnitArray.forEach(function(v,i) {
        array.push({
	        name: this.queryType=='sql' ? v.getSelectedConditionDatabaseField() : v.getSelectedConditionCode(),
	        sign: v.getSelectedSignValue(),
	        value: v.getValue(),
	        type: v.getSelectedConditionType()
	      });
	    }, this);
	    this.moreSearchUnitArray.forEach(function(v,i) {
	      array.push({
	        name: this.queryType=='sql' ? v.getSelectedConditionDatabaseField() : v.getSelectedConditionCode(),
	        sign: v.getSelectedSignValue(),
	        value: v.getValue(),
	        type: v.getSelectedConditionType()
	      });
	    }, this);
	    return this.formatData1(array);
    },
    //格式化表单数组（方法一）
    formatData1: function(array) {
      var data = [];
      array.forEach(function(v,i) {
        data.push({
	        name: i,
	        value: JSON.stringify(v)
	      });
      });
      return data;
    },
    //格式化表单数组（方法二）
    formatData2: function(array) {
    	var data = [];
    	array.forEach(function(v,i) {
    		data.push({
    			name: v.name+'-'+v.sign,
    			value: v.value
    		});
      });
      return data;
    }
  });
})(jQuery, window, document);

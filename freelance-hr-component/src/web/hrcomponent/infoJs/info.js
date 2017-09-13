(function($, window) {
  window.Searcher = {};
  /**
   * 监听器
   */
  var Observer = Searcher.Observer = {};
  //监听
  Observer.on = function(name, callback, context) {
    this._events || (this._events = {});
    this._events[name] || (this._events[name] = []);
    this._events[name].push({name: name, callback: callback});
  }
  //他监听
  Observer.listen = function(obj, name, callback) {
    if(!obj.on || !obj.on===Observer.on) return;
    obj.on(name, callback, this);
  }
  //触发
  Observer.trigger = function(name) {
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
  /**
   * 查询条件组件（ITEM）
   */
  var SearchUnit = Searcher.SearchUnit = function(options) {
    this.$el = $('<dl class="search-unit"></dl>');
    this.$close = $('<span></span>');
    this.$dt = $('<dt class="search-unit-dt"></dt>');
    this.$dd = $('<dd class="search-unit-dd"></dd>');
    this.$conditionSelector = $('<select></select>');
    this.$signSelector = $('<select></select>');
    this.$valueDOM = $('<input type="text"/>');//默认的查询为输入框查询
    
    this.options = options || {};
    this.conditionArray = options.conditionArray;//查询条件数组
    this.defaultConditionCode = options.defaultConditionCode;//默认的查询条件代码
    this.SIGN_ARRAY = [{value:'',text:''},{value:'greater',text:'大于'},{value:'less',text:'小于'},{value:'equal',text:'等于'}];//符号数组
    this.editable = options.editable;//是否可编辑
    this.init();
    this.$el.append(this.$dt).append(this.$dd).append(this.$close);;
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
        that.selectCondition();
      });
      this.$close.click(function() {
        that.$el.remove();
        that.trigger('remove',that);
      });
    },
    //渲染条件选择下拉框
    renderConditionSelector: function() {
      if(!this.editable) {
        this.$conditionSelector.attr('disabled',true)
      }else {
        this.$el.addClass('high-search-unit');
      }
      this.conditionArray.forEach(function(v,i) {
        var selected = this.defaultConditionCode==v.conditionCode ? 'selected' : '';
        var $option = $('<option value="'+v.conditionCode+'" '+selected+'>'+v.conditionName+'</option>').data(v);
        this.$conditionSelector.append($option);
      },this);
      return this;
    },
    //渲染符号选择下拉框
    renderSignSelector: function() {
      this.SIGN_ARRAY.forEach(function(v,i) {
        this.$signSelector.append('<option value="'+v.value+'">'+v.text+'</option>');
      },this);
      return this;
    },
    //渲染查询条件的样式
    selectCondition: function() {
      var condition = this.getSelectedCondition();
      if(condition.conditionType=='1') {//输入框
        this.$valueDOM = $('<input type="text" name="'+condition.conditionCode+'">');
      }else if(condition.conditionType=='2') {//字典下拉框
        this.$valueDOM = $('<select name="'+condition.conditionCode+'"><option value=""></option><option value="1">1</option></select>');
      }else if(condition.conditionType=='3') {//时间下拉框
        
      }
      this.$dd.html(this.$valueDOM);
    },
    //获取所选择的查询条件
    getSelectedCondition: function() {
      return this.$conditionSelector.find('option:selected').data();
    },
    //获取所选择的查询条件的代码
    getSelectedConditionCode: function() {
      return this.getSelectedCondition().conditionCode;
    },
    //获取所选择的查询符号值
    getSelectedSignValue: function() {
      return this.$signSelector.find('option:selected').val();
    },
    //获取用户录入的值
    getValue: function() {
      return this.$valueDOM.val();
    }
  });
  /**
   * 查询组件
   */
  var SearchComponent = Searcher.SearchComponent = function(options) {
    this.$el = $('<form></form>');
    this.$searchInclude = $('<div class="search-include"></div>');
    this.$searchBtnGroup = $('<div class="search-btn-group"></div>');
    this.$searchBtn = $('<button class="search-btn" onclick="return false;">查询</button>');
    this.$highSearchBtn = $('<a class="search-advanced-btn">高级查询</a>');
    this.$addImg = $('<img class="search-condition-add" src="images/common/add-condition.png"/>');
    
    this.options = options || {};
    this.$el = options.$el || this.$el;
    this.url = options.url;
    this.conditionArray = options.conditionArray || [];//查询条件数组
    this.defaultConditionCodeArray = options.defaultConditionCodeArray || [];//默认查询条件代码数组
    this.defaultSearchUnitArray = [];//存储默认显示的查询条件组件数组
    this.moreSearchUnitArray = [];//存储添加的查询条件组件数组
    this.hasConditionCodeArray = [];//存储已经存在的条件代码数组
    this.init();
    this.$el.append(this.$searchInclude).append(this.$searchBtnGroup);
  }
  $.extend(SearchComponent.prototype, Observer, {
    init: function() {
      this.renderDefaultSearchInclude().renderSearchBtnGroup();
      this.initEvents();
    },
    //渲染默认的查询条件组件
    renderDefaultSearchInclude: function() {
      this.defaultConditionCodeArray.forEach(function(v,i) {
        if(this.indexOfCondition(v)>-1) {
          var defaultSearchUnit = new SearchUnit({
            conditionArray: this.conditionArray,
            defaultConditionCode: v
          });
          this.hasConditionCodeArray.push(v);
          this.defaultSearchUnitArray.push(defaultSearchUnit);
          this.$searchInclude.append(defaultSearchUnit.$el);
        }
      },this);
      this.$searchInclude.append(this.$addImg);
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
      this.$searchBtnGroup.append(this.$searchBtn).append(this.$highSearchBtn);
      return this;
    },
    //初始化一些DOM事件
    initEvents: function() {
      var that = this;
      this.$highSearchBtn.click(function() {
        that.toggleMoreSearchCondition();
      });
      this.$addImg.click(function() {
        that.addSearchUnit();
      });
      this.$searchBtn.click(function() {
        var data = that.getConditionForm();
        $.get(that.url,{data:data}, function(r) {
          
        });
      });
    },
    //进入或退出高级查询
    toggleMoreSearchCondition: function() {
      this.$addImg.toggle();
      this.moreSearchUnitArray.forEach(function(v,i) {
        v.$el.toggle();
      });
    },
    //添加查询条件组件
    addSearchUnit: function() {
      //if((this.defaultSearchUnitArray.length+this.moreSearchUnitArray.length)<this.conditionArray.length) {
      var su = new SearchUnit({conditionArray:this.conditionArray,editable:true});
      this.listen(su,'remove',this.removeSearchUnit.bind(this));
      this.$addImg.before(su.$el);
      this.moreSearchUnitArray.push(su);
      //}
    },
    //移除添加的查询条件组件
    removeSearchUnit: function(obj) {
      var index = $.inArray(obj, this.moreSearchUnitArray);
      if (index >= 0) {
          this.moreSearchUnitArray.splice(index, 1);
      }
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
        name: v.getSelectedConditionCode(),
        sign: v.getSelectedSignValue(),
        value: v.getValue()
      });
    });
    this.moreSearchUnitArray.forEach(function(v,i) {
      array.push({
        name: v.getSelectedConditionCode(),
        sign: v.getSelectedSignValue(),
        value: v.getValue()
      });
    });
    return this.formatData(array);
    },
    //格式化表单数组
    formatData: function(array) {
      var data = [];
      array.forEach(function(v,i) {
        data.push({
        name: i,
        value: v
        });
      });
      return data;
    }
  });
})(jQuery, window);


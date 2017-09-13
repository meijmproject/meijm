/**
 *	[Plugins].ux.Store
 *
 *  @name Store
 *  @author zhangqp
 *
 *	@desc 
 *	数据管理对象

	settings.remoteSort //默认不启用

	url:''
	fields:[{name:'field1',type:'string',sort:'field_1'}]
	total: response.total
	totalRecords: rows.length
	rows: [record]
	record : {index:i, data:{field1:v1,field2:v2}}

	store.rows[i].data['field1'];

	事件：
	load 
		beforeload
		loading
		beforeajax

	handleResponse 
		ajax ajax完成事件
	sort
		sort 排序完成事件
	_loadData
		load [this, this.totalRecords, this.rows, data] 数据加载完成事件
 *
 *  @example
 *  new [Xtable].ux.Store({
 *  	url:'',
 *  	fields:[{}]
 *  })
 */
;(function(Plugins,$,window){
	"use strict";
	
	var Store = function(settings){

		Plugins.events.initialize(this);

		this.settings = settings;
		
		this.url = this.settings.url;
		this.fields = this.settings.fields;

		this.params = {};//当前参数
		this.baseParams = {};//不可覆盖的参数
		this.page = {};//分页信息

		this.reader = Plugins.utils.apply( {}, 
			this.reader, 
			{
				//"start": "start",//开始行	FirstResult limit start, limit
				//"limit": "limit", //获取行数 MaxResults
				"total": "total",
				"rows":  "rows"
			}
		);
	};

	$.extend(Store.prototype, {
		// this.store.load({data:data,{start:0,limit:30},fnCallback:fnCallback});
		load: function(options) {
			if (this.fire('beforeload', options, this) === false) return;

			this.params = {}//清除原参数
			Plugins.utils.apply(this.params, this.baseParams, options.params);
			
			this.fire('loading', options, this.params, this);

			if (this.url && this.settings.remoteMode !== false) {
				
				if (this.fire('beforeajax', this.params, this) === false) return;

				this.loadAjax(
					this.url, 
					this.params, 
					function(data, e) {
						this.handleResponse(data, e, options);
					}
				);

				return;
			}

			// 不是ajax请求，直接加载
			this._loadData(options.data, options.params, options);
		},
		//protected
		loadAjax: function(sSource, oParams, fnCallback) {
			var that = this;
			$.ajax(Plugins.utils.apply( {}, 
				this.settings.ajaxOptions,
				{  
					type : this.settings.method || 'post',
//					contentType : this.settings.contentType || 'application/json', 
					url : sSource,
					dataType : this.settings.dataType || 'json',  
					data : oParams,
					error: function(x,m,e) {fnCallback.call(that, null, m ? m : e.message);},
					success : function(data) {fnCallback.call(that, data);}
				}
			));
		},
		//protected 数据处理
		handleResponse: function(data, e, options) {
//			console.log(data)
			this.fire('ajax', data, e); //ajax 完成事件
			this._loadData(data, null, options);
		},

		each: function(fn,scope){
			for (var i=0; i<this.rows.length; i++) {
				if (fn.call(scope||this.rows[i]||this,  this.rows[i], i, this.rows) === false) return;
			}
		},
		
		at: function(i) {
			return this.rows[i];
		},

		fieldIndexOf: function(name) {
			for (var i=0; i<this.fields.length; i++) {
				if (this.fields[i].name === name) return i;
			}
			return -1;
		},

		sort: function(name, action) {
			var index = this.fieldIndexOf(name);
			var field = this.fields[index];

			var asc = action === 'asc' ? 1 : -1;

			if (field) {

				if (this.settings.remoteSort !== true) {
					this.rows.sort(function(r1, r2){
						return Plugins.sorters[field.type](r1.data[field.name], r2.data[field.name]) * asc;
					});

					this.fire('sort', name, action, this);

					return;
				}

				//服务器排序，重新加载数据
				if (this.url) {
					this.load({
						params:{"order": field.type, "asc": action}, 
						fnCallback: function(){
							this.fire('sort', field.sort || name, action, this);
						}
					});
				}
			}
		},
		
		//private
		_loadData: function(data, params, options) {
			
			switch($.type(data)) {
				case 'string': 
					var oData =  $.parseJSON(data);
					if (!oData) return;

					this._loadData(oData, options);
					return;
				break;
				case 'function':
					var oData =  data.call(this, data)
					if (!oData) return;

					this._loadData(oData, options);
					return;
				break;
				case 'array':
					this.oRows = data; //保存原始数据
					this.total = this.oRows.length;
				break;
				case 'object':
					this.oRows = data[this.reader.rows] || [];
					this.total = data[this.reader.total] || this.oRows.length;
					//this.start = data[this.reader.start] || this.oRows.length;
				break;
				default : //undefined
					this.oRows = this.oRows || [];
					this.total = this.oRows.length;
				break;
			}

			this.rows = [];
			var start = params ? params.start || 0 : 0;
			var limit = params ? params.limit || this.oRows.length : this.oRows.length;
			var index = 0;
			for (var end = limit+start > this.oRows.length ? this.oRows.length : limit+start; start<end; start++) {
				this.rows.push({index: index++, data: this.oRows[start]});
			}
			this.totalRecords = this.rows.length;

			this.fire('load', this, this.totalRecords, this.rows, this.oData, options);

			if ($.isFunction(options.fnCallback)) options.fnCallback.call(this, this, this.totalRecords, this.rows, this.oData, options);
		}
	});
	
	Plugins.ux.Store = Store;
	
})(Xtable, jQuery, window);

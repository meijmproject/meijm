/**
 *	Xtable plugins
 *
 *  @name Xtable
 *  @author zhangqp
 *
 *  @example
 *    $(document).ready(function() {
 *        var table = new Xtable({
 *            url: 'customers/list',
 *            el: 'table.order-table'
 *        });
 *    } );
 */
var Xtable = 
(function($, window) {
	"use strict";

	function rt_false(e){e.preventDefault();event.stopPropagation(); return false;};
	
	var defaults = {
		el: 'table.x-table',
		classes: {
			sortable: 'sortable'
		},
		sort: {
			toggle: {"sorting":"sorting_asc","sorting_asc":"sorting_desc","sorting_desc":"sorting_asc"}
		},
		loading : '加载中',
		paginationType:'input',
		lengthMenuType: "select",
		lengthMenu: [30,50,100],
		checkbox: false,
		singleCheck: true,
		selectable: true,
		rowNumber: true,
//		clickCheck: false, //点击行不选中
		oClasses: {
			"sPageButton": "paginate_button",
			"sPageFirst": "first",
			"sPagePrevious": "previous",
			"sPageNext": "next",
			"sPageLast":"last"
		},
		language: {
			oPaginate: {
				"sFirst": "首页",
				"sPrevious": "上一页",
				"sNext": "下一页",
				"sLast": "尾页"
			}
		},
		dom:'<".ct"t><".page_foot"lip>'//目前只识别双引号，不建议修改
	};
	
	var Plugins = function(settings) {

		this.settings = $.extend(true, {}, defaults, settings);
		
		this.dom = this.settings.dom;
		
		//没有配置节点，玩不了
		if (!this.settings.el)  throw 'no el parameter in settings.';

		this.table = $(this.settings.el);
		if (!this.table.is('table')) {
			this.wrap = $(this.settings.el);
			
			//找不到节点，玩不了
			if (this.wrap.length == 0) throw 'cannot find el node.';

			this.table = this.wrap.find('table:first');
			if (this.table.length == 0) {
				this.table = $('<table><thead></thead><tbody></tbody></table>');
			}
		} else {
			this.wrap = $('<div class="xtable-wrap"></div>').insertAfter(this.table);
		}
		
		this.loading = $('<div class="loading-mask" style="display: none;"><span class="loading"></span></div>').appendTo(this.wrap);
		
		new Plugins.ux.DomExprParser().parse(this.dom, this.wrap, {'t':this.table});

		this.thead = this.table.find('thead');
		if (this.thead.length == 0) {
			this.thead = $('<thead></thead>').appendTo(this.table);
		}
		this.tbody = this.table.find('tbody');
		if (this.tbody.length == 0) {
			this.tbody = $('<tbody></tbody>').appendTo(this.table);
		}
		//this.tfoot = this.table.find('tfoot');

		var that = this;

		if (this.table.data('sortable')) {
			this.table.addClass(this.settings.classes.sortable);
		}

		this.sortable = this.table.hasClass(this.settings.classes.sortable);
		
		Plugins.events.initialize(this);

		this.initThead();//创建thead fields columns
		this.url = this.settings.url;
		this.store = new Plugins.ux.Store({
			url: this.url,
			fields: this.fields
		});
		
		var data = [];
		this.tbody.find('tr').each(function() {
			var rowData = {};
			$(this).children().each(function(i) {
				rowData[that.columns[i] ? that.columns[i].field : i] = $(this).html();
			});
			data.push(rowData);
		});

		this.settings.aData = data;//原始数据

		//erase
		this.tbody.children().remove();

		//暂不支持文字描述
		if (this.settings.loading !== false) {
			this.store.on('beforeload', function(){this.loading.show();}, this);
			this.on('draw', function(){this.loading.hide();}, this)
		}
		
		this.store.on('sort', this.onSort, this);
		this.store.on('load', this.draw,   this);
		
		//插件初始化
		this.plugins = {};//插件管理对象
		for (var sPluginName in Plugins.plugins) {
			initPlugin(this, Plugins.plugins[sPluginName], sPluginName, 'fnInit');
		}
		
		//全局onRender，创建附属功能
		this.additives = {};
		for (var sAdditionName in Plugins.additives) {
			initAddition(this, Plugins.additives[sAdditionName], sAdditionName);
		}
		
		if ($.isFunction(this.settings.onRender)) {
			this.settings.onRender.call(this);
		}
		
		//添加扩展
		this.fire('render', this);
		
		// after render
		if (this.settings.defer === false) {
			this.store.load({data: this.settings.aData, params:{start:this.page.start, limit: this.page.limit}});
		}
	};
	
	function initAddition(that, oAdditionDeclare, sAdditionName) {
		var sInstanceType = that.settings[oAdditionDeclare["_sProp"]];//'tip'
		
		if (!sInstanceType) return;//不启用
		
		var oAdditionInstance = oAdditionDeclare[sInstanceType];//插件实现，'half' => Plugins.plugins.oPagination.half
		
		var additive = 
		that.additives[sAdditionName] = {
			declare: oAdditionDeclare, 
			instance: oAdditionInstance
		};
		
		var adFnInit = oAdditionInstance['fnInit'];
		if (adFnInit) adFnInit.call(that, that, additive, oAdditionInstance);
	}
	
	function initPlugin(that, oPluginDeclare, sPluginName, action){
		var sInstanceType = that.settings[oPluginDeclare["_sProp"]];//'half'
		
		if (!sInstanceType) return;//不启用
		
		var node = that.wrap.find(oPluginDeclare["_sSelector"]||('.'+oPluginDeclare["_sCss"]));
		
		if (node.length == 0) throw 'not found ' + oPluginDeclare["_sSelector"] + ' element in wrap.';//没有在dom中声明

		Plugins.utils.apply(that, oPluginDeclare["_oOverrides"]);
		
		var oPluginInstance = oPluginDeclare[sInstanceType];//插件实现，'half' => Plugins.plugins.oPagination.half
		
		node.addClass(oPluginInstance.style || ((oPluginDeclare["_sCss"]||'')+'_'+sInstanceType));
		
		var plugin = 
		that.plugins[sPluginName] = {
			node: node,
			declare: oPluginDeclare, 
			instance: oPluginInstance
		};

		var fnPluginInit = oPluginDeclare["_fnInit"];
		if (fnPluginInit) fnPluginInit.call(that, node, that);
		
		callAction(that, plugin, 'fnInit');
	}

	function callAction(that, plugin, action) {
		var fnBefore = plugin.declare["_"+action+'Before'];
		//参数依次为： 插件定义的节点、table实例、插件实现、插件信息
		if (fnBefore) fnBefore.call(that, plugin.node, that, plugin.instance, plugin);
		
		var fnAction = plugin.instance[action];
		if (fnAction) fnAction.call(that, plugin.node, that, plugin.instance, plugin);
	}
	
	$.extend(Plugins.prototype, {

		initThead: function() {
			var fields = [], column, field, header;

			var headers = this.thead.find('tr:first').children();
			var hLength = headers.length;
			var columns = [];
			
			if (this.settings.columns && this.settings.columns.length > 0) {
				var col ;
				for (var i=0; i<this.settings.columns.length; i++) {
					col = this.settings.columns[i];
					col.index = col.index != undefined ? col.index : i;
					col.index = col.index < 0 ? col.index + hLength : col.index;
					columns[col.index] = col;
				}
			}
			
			//table中包含了thead>tr>th信息 
//			if (hLength > 0) {
//				headers.each(function(i){
//					header = $(this);
//					column = Plugins.utils.apply({ header: header.html(), index: i }, columns[i], header.data());
//					columns[i] = column;
//				});
//			}
			
			this.thead.children().remove();
			
			//找不到列信息，玩不了
			if (!columns || columns.length == 0) throw 'not set columns.';
			
			this.columns = [];
			
			if (this.settings.rowNumber) {
				this.columns.push(new Plugins.ux.RowNumber(this));
			}
			if (this.settings.checkbox) {
				this.columns.push(new Plugins.ux.SelectModel(this, this.settings.singleCheck));
			} else {
				if (this.settings.selectable) {
					new Plugins.ux.SelectModel(this, this.settings.singleCheck);
				}
			}
			
			this.columns = this.columns.concat(columns);
			
			/****lmh设置表格默认列宽***/
			this.defaultColumnWidth = 80;
			/**********/

			headers = $('<tr></tr>').appendTo(this.thead);
			
			for (var i=0; i<this.columns.length; i++) {
				column = this.columns[i];
				field = column.field || i;
				column.field = field;

				this._renderHeader(headers, column);

				fields.push({ name: field, type: column['type'] || 'string', sort: column['sort'] });
			}
			this.fire('allheadersrender', this);
			this.fields = fields;
			this.headers = this.thead.find('tr:first').children();
		},
		_renderHeader: function(headers, column) {
			var header = $('<th>'+(column.header||'')+'</th>');
				
			if (column.style) {
				header.css(column.style);
			}
			
			/***lmh设置表头单元格宽***/
			/*if (column.width) {
				header.css({width:column.width});
			}*/
			var realWidth = column.width ? column.width : this.defaultColumnWidth;
			header.css({width: realWidth});
			/**********/
			if (column.minWidth) {
				header.css({minWidth:column.minWidth});
			}
			if (column.maxWidth) {
				header.css({maxWidth:column.maxWidth});
			}

			if (column.type == 'html') {
				column.sortable = false;
			}

			header.attr('data-field', column.field);

//				header.bind('selectstart.xt', rt_false);//选择
			header.bind('dragstart.xt', rt_false);//拖拽

			if (this.sortable && column.sortable !== false) {
				header.addClass('sorting');

				header.bind('click.xt', {name: column.field, that: this}, this.onHeaderClick);
			}
			headers.append(header);
			this.fire('headerrender', headers, column);
		},
		onHeaderClick: function(event){
			var that = event.data.that;
			var header = $(this);
			var cur = header.hasClass('sorting') ? 'sorting' : 
								header.hasClass('sorting_asc') ? 'sorting_asc' : 
									header.hasClass('sorting_desc') ? 'sorting_desc' : null;
			if (cur) {
				var action = that.settings.sort.toggle[cur];
				that.store.sort(event.data.name, action === "sorting_asc" ? "asc" : "desc");
			}
		},
		_formatCellValue: function(value, column) {
			value = $.trim(value);
			if (value && column.format != undefined) {
				if ($.isFunction(column.format)) {
					return column.format.call(this, value, record, column, this);
				}
				//当作date
				if ($.type(column.format) === 'string') {
					if ($.type(value) === 'string') {
						//' 0' ' 1'
						var format = column.format.replace(/\d*/, '') || ' ';
						var index = column.format.replace(format, '') || 0;
						value = value.split(format)[parseInt(index)];//2006-07-02 08:09:04.423
					} else {
						value = value.format(column.format);//flat.js/Date.prototype.format
					}
				}
			}
			return value;
		},
		_renderCell: function(row, value, column, record, rowIndex) {
			var cell = $('<td></td>');
			
			value = this._formatCellValue(value, column);
			
			cell.html(column.render ? column.render.call(this, value, record, rowIndex, cell, column, this) : value);

			if (column.style) {
				cell.css(column.style);
			}
			if (column.css) {
				cell.addClass($.isFunction(column.css) ? column.css.call(this, value, record, rowIndex, cell, column, this) : column.css);
			}
			/***lmh设置表格单元格宽***/
			var realWidth = column.width ? column.width : this.defaultColumnWidth;
			cell.css({width: realWidth});
			/**********/
			
			this.fire('cellrender', value, record, cell, column, rowIndex);
			
			return cell;
		},
		draw: function(){
			var that = this;
			//erase
			this.tbody.children().remove();
			
			this.store.each(function(record, rowIndex){
				var column, value, row;

				row = $('<tr></tr>').appendTo(this.tbody);
				
				row.attr('rowIndex', rowIndex);

				for (var i=0; i<this.columns.length; i++) {
					column = this.columns[i];
					value = record.data[column.field];
					if (column.rowCss) {
						row.addClass($.isFunction(column.rowCss) ? column.rowCss.call(this, value, record, rowIndex, column, this) : column.rowCss);
					}
					row.append(this._renderCell(row, value, column, record, rowIndex));
				}
				that.fire('rowrender', record, rowIndex, row);
			}, this);
			
			//插件更新通知
			for (var plugin in this.plugins) {
				callAction(this, this.plugins[plugin], 'fnUpdate');
			}
			
			this.fire('draw',this);
			
			/**lmh 表格高度和拖动x轴效果**/
			var that = this;
			this.tbody.css({'height':this.table.height()-this.thead.height()});
			$(window).resize(function() {
				that.tbody.css({'height':that.table.height()-that.thead.height()});
			});
			this.tbody.scroll(function(e) {
				that.thead.find('tr').css({left: -this.scrollLeft});
			});
			/*****************/
		},
		
		getRecordAt: function(rowIndex) {
			return this.store.at(rowIndex);
		},

		onSort: function(field, action) {
			this.draw();
			var header = this.headers.filter('[data-field='+field+']');
			this.headers.filter('.sorting, .sorting_asc, .sorting_desc')
					.removeClass('sorting sorting_asc sorting_desc')
						.not(header).addClass('sorting');
			header.addClass('sorting_'+action);
		}
	});
	
	Plugins.setDefaults = function(settings) {
		$.extend(true, defaults, settings);
	};
	
	Plugins.utils = {
		parseNumber: function (o){
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
		apply: function (o, c, defaults) {
			if(defaults){
				Plugins.utils.apply(o, defaults);
			}
			if(o && c && typeof c == 'object'){
				for(var p in c){
					o[p] = c[p];
				}
			}
			return o;
		}
	};
	
	//Plugins.utils.apply简写
	Plugins.extend = Plugins.utils.apply;
	
	//增强功能
	Plugins.additives = {}//向当局全局afterRender事件
	
	//plugins 增强功能插件注册声明 declare
	Plugins.plugins =  {
		"oPagination": {
			// 插件查找标识
			//"_sSelector": '.paginate',//对应到domexpr中的class
			"_sCss": 'paginate',//对应到domexpr中的class
			//插件在settings中指定的实例类型，暂只支持在setting中添加
			"_sProp": "paginationType",
			// 添加或重写table 方法
			"_oOverrides": {
				"fnPageChange": function(page) {
					this.page.page = Plugins.utils.parseNumber(page);
					this.page.start =  this.page.page * this.page.limit; // page base 0
					this.store.load({params: {start: this.page.start, limit: this.page.limit}});
				}
			},
			//"_fnInit": function() {
			//	//初始分页信息
			//	this.page = { start: 0, end:0, limit: this.settings.iPageLength || this.settings.lengthMenu[0] || 30, total: 0, display: 0, page: 0, pages: 0};
			//},
			"_fnInitBefore": function() {
				this.page = { start: 0, end:0, limit: this.settings.iPageLength || this.settings.lengthMenu[0] || 30, total: 0, display: 0, page: 0, pages: 0};
				//获取最终生效的分页信息
				this.store.on('beforeload',function(options){
					this.page.start = options && options.params && options.params.start != undefined && options.params.start != null ? options.params.start : this.page.start;
					//同时监听limit变化？
				}, this);
			},
			"_fnUpdateBefore": function() {
				//all base 0
				this.page.start   = this.page.start || 0;
				this.page.end     = this.page.start + this.store.totalRecords;
				//this.page.limit   = this.page.limit;
				this.page.total   = this.store.total || 0;
				this.page.display = this.store.totalRecords;
				this.page.page    = Math.ceil(this.page.start / this.page.limit);
				this.page.pages   = Math.ceil(this.page.total / this.page.limit);
			}
		},
		"oLengthMenu": {
			"_sCss": 'length_menu',
			"_sProp": "lengthMenuType",
			"_oOverrides": {
				"fnLengthChange": function(length,cp) {
					this.page.limit = Plugins.utils.parseNumber(length);
					if(cp !== false) this.fnPageChange(0);
				}
			}
		}
	};

	//排序函数
	Plugins.sorters = {
		'string': function(v1, v2) {
			v1 = new String(v1);
			v2 = new String(v2);
			if (v1 && v2) {
				return v1.localeCompare(v2);
			} else if (!v1 && !v2) {
				return 0;
			} else if (v2) {
				return -1;
			}
			return 1;
		},
		'number': function(v1, v2) {
			
			v1 = new Number(v1 || 0);
			v2 = new Number(v2 || 0);

			return v1 - v2 == 0 ? 0 : v1 - v2 > 0 ? 1 : -1;
		}
	};

	Plugins.ux = {};//ux 额外引用对象 store domexpr
	
	Plugins.ux.RowNumber = function(that,config) {
		Plugins.utils.apply(this, config, {
			width: that.settings.rnWidth || 30,
			sortable: false,
			tip:false,
			style: {'text-align': 'center',  'padding': '0 5px'},
			render: function(value, record, rowIndex) {
				return rowIndex+1;
			}
		});
	};
	
	Plugins.ux.SelectModel = function(that, singleCheck, settings) {
		Plugins.utils.apply(this, {
			width: that.settings.cbWidth || 30,
			sortable: false,
			tip:false,
			style: {'text-align': 'center',  'padding': '0 5px'},
			header: '<input type="checkbox" class="hcbm"/>',
			render: function(value, record, rowIndex) {
				return $.format('<input type="checkbox" class="cbm" rowIndex="{0}"/>', rowIndex);
			}
		});
		that.selectModel = this;
		
		that.on('allheadersrender', function() {
			that.thead.find('input.hcbm').click(function() {
				var checked = $(this).prop('checked');
				that.tbody.find('tr').each(function(){
					that.selectModel.selectRow($(this).attr('rowIndex'), checked);
				})
			});
		}, this);
		
		this.clearRow = function(rowIndex) {
			if (rowIndex !== undefined && rowIndex != null) {
				that.tbody.find('tr[rowIndex='+rowIndex+']').removeClass('selected').find('input.cbm').prop('checked',false);
			} else {
				that.tbody.find('tr.selected').removeClass('selected').find('input.cbm').prop('checked',false);
			}
		}
		
		//选中/不选中行
		this.selectRow = function(rowIndex, selected) {
			var row = that.tbody.find('tr[rowIndex='+rowIndex+']');
			var record = that.store.at(rowIndex);
			
			//选中？
			var isSelected = row.hasClass('selected');
			var fireSelect = selected === false ? false : true;
			
			if (that.fire('beforeselect', record, fireSelect, row) === false) return;
			
			if (row.hasClass('disabled')) {
				row.removeClass('selected').find('input.cbm').prop('checked',false);
				return;
			}
			
			if (fireSelect !== isSelected) {
				if (fireSelect) {
					if (singleCheck) {
						that.tbody.find('tr.selected').removeClass('selected').find('input.cbm').prop('checked',false);
					}
					row.addClass('selected').find('input.cbm').prop('checked',true);
				}
				else { //取消
					row.removeClass('selected').find('input.cbm').prop('checked',false);
				}
			}
			
			that.fire('rowselect', record, selected, rowIndex);
		};
		
		this.getSelectRows = function() {
			var selected = [];
			that.tbody.find('tr.selected').each(function(){
				selected.push(that.store.at($(this).attr('rowIndex')));
			});
			return selected;
		};
		
		this.getSelectCount = function() {
			return that.tbody.find('tr.selected').length;
		};
		
		that.on('rowrender', function(record, rowIndex, row) {
			row.click(function(event) {
				if (that.settings.clickCheck !== false && that.fire('rowclick', record, rowIndex, row, event) !== false) {
					that.selectModel.selectRow(rowIndex);
				}
				
			});
			
			row.find('input.cbm').click(function(event) {
				that.selectModel.selectRow(rowIndex, $(this).prop('checked'));
				that.fire('rowcheck', record, rowIndex, row, event);
				event.stopPropagation();//停止冒泡到rowclick
			});
			
		}, that);
		
	};

	return Plugins;

})(jQuery, window);

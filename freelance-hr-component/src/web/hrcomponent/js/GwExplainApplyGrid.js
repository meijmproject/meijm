/**
 * 工作台表格实现方式示例
 * 
 * @author zhangqp, 2016/09/07
 */ 
var GwExplainApplyGrid = (function($, window, document) {
	var html = [
		"<table class='x-table sortable ellipsis striped hover' ></table>"
	].join('');
	
	var me = function(settings) {
		$.copyProperties(this, settings);
	
		//wrap ct el
		this.ct = $(html).appendTo($(this.wrap || 'body'));//初始化位置由实现者控制
		this.el = this.ct;
		
		return new Xtable( $.copyProperties({
//			widget: this,	//外部引用，视情况决定是否要暴露此属性
			el: this.el,	//指定table节点
			lengthMenu:[30,50,100],
			paginationType: 'input',
			rowNumber: true,
			checkbox: true,
			singleCheck: false,
			columns: [
				{header:'单位', field:'unitName', width:180},
				{header:'人员姓名', field:'name', width:80},
				{header:'证件号码', field:'idNo', width:120},
				{header:'人员状态', field:'personStatusDesc', width:80, tip: false},
				{header:'职务', field:'administrativeDuty', width:100},
				{header:'职务层次', field:'administrativeDutyLevelDesc', width:100},
				{header:'校核完成标识', field:'isVerifiedDesc', width:100, tip: false},
				{header:'人员校核结果', field:'verifiedInfo', width:100},
				{header:'人员锁定标识', field:'isLockDesc', width:100, tip: false},
				{header:'单位锁定标识', field:'isUnitLockDesc', width:100, tip: false}
			]
		}, settings) );
	}
	return me;
})(jQuery, window, document);

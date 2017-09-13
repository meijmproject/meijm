/**
 * 工作台form查询条件实现方式示例
 * 
 * @author zhangqp, 2016/09/07
 */
var GwExplainApplyForm = (function($, window, document){
	var html = [
		"<div class='search'>",
		'<form>',
	 	"<dl class='search-horizontal'><dt>人员姓名：</dt> <dd><label><input type='text'/></label></dd></dl>",
		"<dl class='search-horizontal'><dt>证件号码：</dt><dd><label><input type='text'/></label></dd></dl>",
		"<dl class='search-horizontal'><dt>人员状态：</dt> <dd><label><input type='text'/></label></dd> </dl>",
		"<dl style='float: left;line-height: 28px;'><dt style='float: left;clear: left'><input type='checkbox' checked/></dt>",
		" <dd style='color: #333;float: left;padding-left: 6px;'>一般在职</dd></dl>",
		"<dl class='search-horizontal'><dt>人员类别：</dt> <dd><label><select><option>身份证</option><option>驾驶证</option></select></label></dd></dl>",
		"<dl class='search-horizontal'><dt>岗位级别：</dt><dd><label><input type='text'/></label></dd></dl>",
		"<td><dl class='search-horizontal'><dt>是否包含下设机构：</dt><dd><label><input type='text'/></label></dd></dl>",
		"<button style='float: left' class='btn_search'>查询</button><button style='float: left' class='btn_cancel'>取消</button>",
		"<div style='clear: both'></div></div>",
		'</form>'
	].join('');
	
	return function(settings){
		//加入基本方法，类似继承
		TplQueryForm.extend(this, settings);
		
		//wrap 	包裹本form组件的div节点 	（一般是用来指定生成位置的）
		//ct 	生成的html节点			（html中生成的<div class='search'>）
		//el	操作的主dom节点			（form）
		// 以上节点可按使用情况提供
		
		this.ct = $(html).appendTo($(this.wrap || 'body'));//初始化位置由实现者控制
		this.el = this.ct.find('form');
		var that = this;
		
		this.el.submit(function(){ return false; });
		this.el.find('button.btn_search').click(function(){
			that.goQuery();
		});
	};
 })(jQuery, window, document);
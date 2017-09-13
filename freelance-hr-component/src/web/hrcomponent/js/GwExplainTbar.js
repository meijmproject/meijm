/**
 * 工作台按钮实现方式示例
 * 
 * @author zhangqp, 2016/09/07
 */
var GwExplainTbar = (function($, window, document){
	// 行内浮动操作按钮
	var fbar = [
		'<div style="display:none;" class="handel_float" >',
			'<em></em>',
		    '<span></span>',
		    '<button class="btn_add" button-click="update">修改</button>',
		    '<button class="btn_ok" button-click="view">查看</button>',
		    '<button class="btn_ok" button-click="check">校核完成</button>',
		'</div>'
	].join('');
	// 固定按钮、批量操作按钮
	var tbar_D = [
		'<div class="handel">',
	        '<button class="btn_add" button-click="export">导出人员名册</button>',
	    '</div>'
	].join('');
	
	var tbar_Y = [
	    		'<div class="handel">',
	    	        '<button class="btn_add" button-click="view">查看</button>',
	    	    '</div>'
	].join('');
	
	return function(settings) {
		TplNamedTbar.extend(this, settings);
		
		var worktop = settings.worktop;
		if(settings.dbflag=="1"){
			this.tbar = $(tbar_D).insertAfter(worktop.form.ct); //固定按钮要放到form下面，不需要此按钮可以不初始化
		}else if(settings.dbflag=="2"){
			this.tbar = $(tbar_Y).insertAfter(worktop.form.ct); //固定按钮要放到form下面，不需要此按钮可以不初始化
		}
		this.ct = this.tbar;//别名
		this.fbar = $(fbar).appendTo($(settings.wrap || 'body'));//浮动按钮，不需要此按钮可以不初始化
		
		
		//按钮设置
		this.settings.buttons = {
			//参数说明：
			//第一个参数为grid对象；第二个参数更具情况而定，如果是tbar，则为null，如果是浮动按钮，则为当前行数据
			'export':function(g){alert(g)},
			'update':function(g,r){alert(r.data.personOid)}
		}
		
		this.init();//初始化
	};
})(jQuery, window, document);
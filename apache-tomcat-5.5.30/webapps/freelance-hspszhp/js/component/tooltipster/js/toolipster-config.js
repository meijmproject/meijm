//useage
//1、引入基础css：tooltipster/css/tooltipster.css
//2、引入基础要使用的主题css：tooltipster/css/themes/tooltipster-shadow.css
//3、$('#tooltipId').tooltipster({content:'提示'});
//4、详细配置选项请参考官方文档：http://iamceege.github.io/tooltipster/

//修改提示组件的默认设置
(function($) {
	var tipConfig = {
		debug: false,
		maxWidth: 1200,
		contentAsHTML: true,
		theme:'tooltipster-shadow'//换主题时，对应的主题css也需要更换
	};
	$.fn.tooltipster('setDefaults', tipConfig);
}(jQuery))
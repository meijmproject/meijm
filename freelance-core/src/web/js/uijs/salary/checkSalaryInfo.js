$(document).ready(function() {
    var dlInfo=$(".dl-horizontal");
    // 获取当前屏幕宽度
    var screenWidth = $(document.body).width();
    for(var i=0;i<dlInfo.length;i++){
    	 var ddText = $(dlInfo[i]).find("dd");
    	 // dd内容超过10个字符并且屏幕宽度在1281-1920之间时字体用12px
    	 if ($(ddText).text().length>10 && screenWidth >=1281 && screenWidth <1920) {
    	 	$(ddText).addClass("font-size-12");
    	 };
    }
})
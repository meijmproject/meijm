/*
*
* 该js用于模态框的拖拽，拖拽范围为top>0px;left>0px;
* obj1为拖放点击的div块
* obj2为拖放移动的div块
*
* */
var drag=(function($, window, document){
	function drag_and_drop(obj1,obj2){
	    //x方向的偏移值
	    var disX = 0;
	    //y方向的偏移值
	    var disY = 0;
        /*$(document).on('mousedown',obj1,function(ev){*/
	    obj1.mousedown(function(ev){
	        //判断鼠标抬起时间
	        var drag_is = true;
	        //计算body的宽度
	        var body_width = parseInt($('body').css('width'));
	        //计算模态框的宽度
	        var modal_width = parseInt(obj2.css('width'));
	        //计算body的高度
	        var body_height = parseInt($(window).height());
	        //计算模态框的高度
	        var modal_height = parseInt(obj2.css('height'));
	        //计算body与模态框的宽度差
	        var differ_width = body_width - modal_width;
	        //计算body与模态框的高度度差
	        var differ_height =  body_height - modal_height;

	        disX = ev.pageX - obj2.offset().left;
	        disY = ev.pageY - obj2.offset().top;
	        $(document).mousemove(function(ev){
	            if(drag_is){
	                obj2.css('left',ev.pageX - disX);
	                obj2.css('top',ev.pageY - disY);
	                if((ev.pageX - disX) < 0){
	                    obj2.css('left','0px');
	                }
	                if((ev.pageX - disX) > differ_width){
	                    obj2.css('left',differ_width+'px');
	                }
	                if((ev.pageY - disY) < 0){
	                    obj2.css('top','0px');
	                }else if((ev.pageY - disY) >differ_height){
	                    obj2.css('top',differ_height+'px');
	                }
	            }else{
	                return;
	            }
	        });
	        $(document).mouseup(function(){
	            drag_is = false;
	            return;
	        });
	        return false;
	    });
	}
	return {
		drag_and_drop : drag_and_drop
	}
})(jQuery, window, document);

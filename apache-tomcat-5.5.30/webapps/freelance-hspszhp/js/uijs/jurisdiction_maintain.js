
//点击切换颜色
$(document).ready(function () {
// 地区点击显示更多
    var $w_area_full=$(".w_Hidden_content");
    toggleColor($('.w_select_color span:eq(0)'),$('.w_select_color span:eq(1)'),$('.w_select_color span:eq(2)'));
    var $toggleBen=$(".w_area_more>a"); //找到显示 按钮
    $toggleBen.click(function(){  //当点击更多的函数
        if($w_area_full.is(":visible")){ //如果隐藏内容是显示
            $w_area_full.hide();    //那么隐藏内容隐藏
        }else{
            $w_area_full.show(); //负责隐藏内容显示
        }
        return false;
    });
  //系统类别点击显示更多
    var $w_System_full=$(".w_System_Hidden_content");
    var $toggleBenSystem=$(".w_System_more>a"); //找到显示 按钮
    $toggleBenSystem.click(function(){  //当点击更多的函数
        if($w_System_full.is(":visible")){ //如果隐藏内容是显示
            $w_System_full.hide();    //那么隐藏内容隐藏
        }else{
            $w_System_full.show(); //负责隐藏内容显示
        }
        return false;
    });
    //单位性质点击时显示更多
    var $w_nature_unit_full=$(".w_nature_unit_Hidden_content");
    var $toggleBenunit=$(".w_nature_unit_more>a"); //找到显示 按钮
    $toggleBenunit.click(function(){  //当点击更多的函数
        if($w_nature_unit_full.is(":visible")){ //如果隐藏内容是显示
            $w_nature_unit_full.hide();    //那么隐藏内容隐藏
        }else{
            $w_nature_unit_full.show(); //负责隐藏内容显示
        }
        return false;
    });
 // 左边的高度等于右边的高度
      var rheight=$('.right_content').height();
      $('.left_nav').css('height',rheight+'px');
});

//点击显示更多
$.switchover={
    //定义一个点击显示更多的方法
    toggle:function(){
        //找到隐藏的内容
        var $w_area_full=$(".w_Hidden_content");
        var $toggleBen=$(".w_area_more>a"); //找到显示 按钮
        $toggleBen.click(function(){  //当点击更多的函数
            if($w_area_full.is(":visible")){ //如果隐藏内容是显示
                $w_area_full.hide();    //那么隐藏内容隐藏
            }else{
                $w_area_full.show(); //负责隐藏内容显示
            }
            return false;
        });
    }
}
$.switchover.toggle();

//封装切换功能
function toggleColor(obj1,obj2,obj3){
    obj1.click(function(){
        $(this).css('color','#fff');
        $(this).css('background','#0084e2');
        obj2.css('color','#ff0000');
        obj2.css('background-color','#ddd');
        obj3.css('color','#5e5e5e');
        obj3.css('background-color','#e4e4e4');
        // 全选
        $(".w_area_full_ul .w_area_full_ulone").css("background-color","#0084e2");
        $(".w_area_full_ul .w_area_full_ulone a").css("color","#fff");
    });
    obj2.click(function(){
        obj1.css('color','#5e5e5e');
        obj1.css('background','#e4e4e4');
        obj2.css('color','#fff');
        obj2.css('background-color','#ff3333');
        obj3.css('color','#5e5e5e');
        obj3.css('background-color','#e4e4e4');
        //取消
        $(".w_area_full_ul .w_area_full_ulone").css("background-color","#fff");
        $(".w_area_full_ul .w_area_full_ulone a").css("color","#333");
    });
    obj3.click(function(){
        obj1.css('color','#5e5e5e');
        obj1.css('background','#e4e4e4');
        obj2.css('color','#ff0000');
        obj2.css('background','#e4e4e4');
        obj3.css('color','#fff');
        obj3.css('background-color','#339de8');
    });
}


//颜色切换
//$(function(){
//    $('.w_select_color span:eq(0)').click(function(){
//        $('.w_select_color span:eq(0)').css('color','#fff');
//        $('.w_select_color span:eq(0)').css('background','#339de8');
//        $('.w_select_color span:eq(1)').css('color','#ff0000');
//        $('.w_select_color span:eq(1)').css('background-color','#ddd');
//        $('.w_select_color span:eq(2)').css('color','#5e5e5e');
//        $('.w_select_color span:eq(2)').css('background-color','#e4e4e4');
//     // 全选
//        $(".w_area_full_ul .w_area_full_ulone").css("background-color","#0084e2");
//        $(".w_area_full_ul .w_area_full_ulone a").css("color","#fff");
//    });
//    $('.w_select_color span:eq(1)').click(function(){
//        $('.w_select_color span:eq(0)').css('color','#000');
//        $('.w_select_color span:eq(0)').css('background','#ddd');
//        $('.w_select_color span:eq(1)').css('color','#fff');
//        $('.w_select_color span:eq(1)').css('background-color','#ff3333');
//        $('.w_select_color span:eq(2)').css('color','#5e5e5e');
//        $('.w_select_color span:eq(2)').css('background-color','#e4e4e4');
//    //取消
//        $(".w_area_full_ul .w_area_full_ulone").css("background-color","#fff");
//        $(".w_area_full_ul .w_area_full_ulone a").css("color","#333");
//    });
//    $('.w_select_color span:eq(2)').click(function(){
//        $('.w_select_color span:eq(0)').css('color','#5e5e5e');
//        $('.w_select_color span:eq(0)').css('background','#e4e4e4');
//        $('.w_select_color span:eq(1)').css('color','#ff0000');
//        $('.w_select_color span:eq(1)').css('background','#e4e4e4');
//        $('.w_select_color span:eq(2)').css('color','#fff');
//        $('.w_select_color span:eq(2)').css('background-color','#339de8');
//    });
//    //单击选中
//    $('.w_area_full_ul li:eq(0)').click(function(){
//        $('.w_area_full_ul li:eq(0)').css("background-color","#0084e2");
//        $('.w_area_full_ul li a:eq(0)').css("color","#fff");
//    })
//});
////仅包含主管单位
//$(function(){
//    $('.w_select_YES span:eq(0)').click(function(){
//        $('.w_select_YES span:eq(0)').css('background','#0084e2');
//        $('.w_select_YES span:eq(0)').css('color','#fff');
//        $('.w_select_YES span:eq(1)').css('background','#dddddd');
//        $('.w_select_YES span:eq(1)').css('color','#5e5e5e');
//    })
//    $('.w_select_YES span:eq(1)').click(function(){
//        $('.w_select_YES span:eq(0)').css('background','#dddddd');
//        $('.w_select_YES span:eq(0)').css('color','#5e5e5e');
//        $('.w_select_YES span:eq(1)').css('background','#0084e2');
//        $('.w_select_YES span:eq(1)').css('color','#fff');
//    })
//});











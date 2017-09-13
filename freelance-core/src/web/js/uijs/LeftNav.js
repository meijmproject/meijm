
//事项树环节树切换
$(function(){
    $('.tabPanel li').click(function(){
        $(this).addClass('hit').siblings().removeClass('hit');
//        eq(index)匹配一个给定索引值的元素$("tr:eq(1)")
        $('.panes>div:eq('+$(this).index()+')').show().siblings().hide();
    })
})
// 点击进行样式操作
$(function change(){
    $(".dianji").click(function(){
        var lanseS=$(this).css('background-color','#0095ff');
        $(this).siblings('li').css("background-color", '#63ade3');
    })
})
//超过屏幕高度出现滚动条
/*function layout(){
    $(".panes").css("height",$(window).height() - 152);
}
layout();
$(window).resize(function(){layout()});
//滚动条优化
$(window).resize(function(){$(".panes_scroll").css("height",$(window).height() -100);}).resize();*/


//点击时变色
$(document).ready(function(){
    $(".nav-THREE .THREE-LI .THREE-A").click(function(){
        var colorchange=$(this).toggleClass("THREE-A_click");
        // $(this).siblings('.THREE-LI .THREE-A').css("background-color", '#63ade3');
        var div=$(this).parent().siblings('li').children("div");
        $(div).each(function(){
            if($(this).attr("class")=="THREE-A THREE-A_click"){
                $(this).removeClass("THREE-A_click");
            }
        })

    });
});

//左导航切换
$(function(){
    $('.left_nav_hide').css('left','-352px');
    var left_nav_hide_expanded = true;
    $('.left_nav_bar').click(function(){
        if(left_nav_hide_expanded){
            $('.left_nav_hide').animate({left:'0'},1000);
            $('.left_nav_bar').css('background-position','-25px 0px');
        }else{
            $('.left_nav_hide').animate({left:'-352px'},1000);
            $('.left_nav_bar').css('background-position','-0px 0px');
        }
        left_nav_hide_expanded = !left_nav_hide_expanded;
    });

});
//屏幕高度自适应方法
jQuery.extend({
    aoutHeight:function(){
        var windowHeight=$(window).height();
        $('.left_nav').height(windowHeight-102)
        $('.right_content').height(windowHeight-102)
    }
});
$.aoutHeight();


//点击显示隐藏
$(function(){
    $(".leftN_hidden").click(function(){
        $("#left_nav").toggle();
    });
})


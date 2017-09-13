//左侧高度比右侧高度高,如果想让左高度自适应，取消Left-container固定高度，让下面这段注释掉的代码生效
$(function () {
    $(".Left-container").height($(".Righttop-Content").height()-220);
});

//通知公告和预警高度相等
$(document).ready(function() {
    var lheight=$(".Left-items3").height();
    $(".Right-items4").css("height",lheight+"px");
})

//头部导航栏
$(document).ready(function(){
    var timer_dd1 = null;
    // 点击淡入淡出
    $('.navigation-down-inner dl dd').click(function(){
        if(!timer_dd1){
            clearTimeout(timer_dd1);
            setTimeout(function(){
                $('.navigation-down').slideUp(500);//淡出
            },200);
        }else{
            timer_dd1 = setTimeout(function(){
                $('.navigation-down').slideUp(500);//淡出
            },200);
        }
    })
    $('.navigation-down-inner dl dd').click(function(){
        if(!timer_dd1){
            clearTimeout(timer_dd1);
            setTimeout(function(){
                $('.navigation-down').slideUp(500);//淡出
            },200);
        }else{
            timer_dd1 = setTimeout(function(){
                $('.navigation-down').slideUp(500);//淡出
            },200);
        }
    })
    $('.navigation-v3 ul:eq(1) li').mousemove(function(){
        $('.navigation-down').slideDown();//淡入
    })
    var qcloud={};
    $('[_t_nav]').hover(function(){
        var _nav = $(this).attr('_t_nav');
        clearTimeout( qcloud[ _nav + '_timer' ] );
        qcloud[ _nav + '_timer' ] = setTimeout(function(){
            $('[_t_nav]').each(function(){
                $(this)[ _nav == $(this).attr('_t_nav') ? 'addClass':'removeClass' ]('nav-up-selected');
            });
            $('#'+_nav).stop(true,true).slideDown(500);//滑入
        }, 150);
    },function(){
        var _nav = $(this).attr('_t_nav');
        clearTimeout( qcloud[ _nav + '_timer' ] );
        qcloud[ _nav + '_timer' ] = setTimeout(function(){
            $('[_t_nav]').removeClass('nav-up-selected');
            $('#'+_nav).stop(true,true).slideUp(500);//滑出
        }, 150);
    });
});


//代办事项下拉，当待办内容较多时，点击可以展开
$(document).ready(function(){
//$("tr:gt(5)") 选择前6个之后的所有 <tr> 元素：选取除了tr 元素以外的最后一个元素：
    var hdw = $('.index_backlog_content .table_customers tr:gt(5):not(:last)');
//   隐藏
    hdw.hide();
//  找到代办事项下拉内容给点击事件函数
    $('.index_backlog_boxmore a').click(function(){
        if (hdw.is(':visible')){
            hdw.hide();
            $('.index_backlog_boxmore a span').css('background','url(../img/index/I-47down.png) no-repeat right 0').text('');
            $('.table_customers tr').removeClass('onebox');
            $(".Left-container").height($(".Righttop-Content").height()-280);
        }else{
            hdw.show();
            $('.index_backlog_boxmore a span').css('background','url(../img/index/I-47up.png) no-repeat right 0').text('');
            $('.table_customers tr').filter(":contains(''),:contains(''),:contains(''),:contains('')").addClass('onebox');
            $(".Left-container").height($(".Righttop-Content").height()+20);
        };
        return false;
    });
});

//代办事项下拉，当待办内容较多时，点击可以展开
$(document).ready(function(){
//$("tr:gt(5)") 选择前6个之后的所有 <li> 元素：选取除了li 元素以外的最后一个元素：
    var hdw = $('.index_backlog_content .panelRightmain li:gt(5):not(:last)');
//   隐藏
    hdw.hide();
//  找到代办事项下拉内容给点击事件函数
    $('.index_backlog_boxmore a').click(function(){
        if (hdw.is(':visible')){
            hdw.hide();
            $('.index_backlog_boxmore a span').css('background','url(../img/index/I-47down.png) no-repeat right 0').text('');
            $('ul li').removeClass('onebox');
        }else{
            hdw.show();
            $('.index_backlog_boxmore a span').css('background','url(../img/index/I-47up.png) no-repeat right 0').text('');
            $('ul li').filter(":contains(''),:contains(''),:contains(''),:contains('')").addClass('');
        };
        return false;
    });
});

//导航下拉菜单显示隐藏
$(document).ready(function(){
    $('.arrow_down').click(function(){
        if($('.hide_content ul').css('display')=='none'){
            $('.hide_content ul').show();
        }else{
            $('.hide_content ul').hide();
        }
    })
})
//$(document).ready(function(){
//    $('.arrow_down_all').click(function(){
//        if($('.hide_content ul').css('display')=='none'){
//            $('.hide_content ul').show();
//        }else{
//            $('.hide_content ul').hide();
//        }
//    })
//})
//$(window).scroll(function() {
//    // 当滚动到140像素时， 重新定位
//    if ($(this).scrollTop() >= 115) {
//        $(".left").css({"position":"fixed","top":"20px"});
//        $(".right").css({"position":"fixed","top":"20px"});
//        $(".go_back").css({"position":"fixed","top":"5px","right":"30px"});
//        $(".sitemap").css({"position":"fixed","top":"0px","left":"0px",width:"100%"});
//    }else{
//        $(".left").css({"position":"fixed","top":"103px"});
//        $(".right").css({"position":"fixed","top":"103px"});
//        $(".go_back").css({"position":"fixed","top":"62px","right":"30px"});
//        $(".sitemap").css({"position":"fixed","top":"62px","left":"0px",width:"100%"});
//    }
//});

var  is_scale = true;
$(function(){
    /*$('.testcj ').show();
    div_width_scale($('.testcj '),1000,1);*/
    //判断菜单是否自动缩放，默认可以自动缩放
    /*var  is_scale = true;*/
    //定时器，判断窗口是否改变
    var timer_resize=null;
    isScale(false);
    //根据屏幕宽度，自由缩放，初始化
    
    left_nav_hide_or_show();
    //自执行函数，解决一级菜单初始化li背景部分透明问题
    /*(function(){
        $(".left_nav_whole").find('a>div:eq(0)').css('background','#5dc1ff');
        $(".left_nav_whole").find('a>div:eq(1)').css('background','#fff');
        $(".left_nav_whole").css('background','#fff');
    })()*/

   /* timer_resize=setInterval(function(){
        get_window_width();
        clearTimeout(timer_resize);
        $(window).resize(function(){
            left_nav_screen_init();
            left_nav_hide_or_show();
        })
    },200);*/
    //点击一级菜单
    $('.left_nav_whole').click(function(){
        left_nav_big_screen();
        //变量i记录一级目录上是否有ul_show的class
        var i = $(this).hasClass('ul_show_1');
        $('.left_nav_whole').find('ul:first').hide();
        $('.left_nav_whole').removeClass('ul_show_1');
        $(".left_nav_whole").find('a>div:eq(0)').css('background','#5dc1ff');
        $(".left_nav_whole").find('a>div:eq(1)').css('background','#fff');
        $(".left_nav_whole").css('background','#fff');
        $(".left_nav_whole").find('.left_li_div_responsive_id').css('color','#333');
        $(".left_nav_whole").find('a>img').attr('src','../img/messageCheck/arrow_right.png');
        if(!i){
            $(this).find('ul:first').show();
            $(this).addClass('ul_show_1');
            $(this).find('a>div').css('background','#0777bf');
            $(this).css('background','#0777bf');
            $(this).find('.left_li_div_responsive_id').css('color','#fff');
            $(this).find('a>img').attr('src','../img/messageCheck/arrow_down.png');
        }else{
            $('.ul_show_1').find('ul:first').hide();
            $(this).removeClass('ul_show_1');
            $(this).find('a>div:eq(0)').css('background','#5dc1ff');
            $(this).find('a>div:eq(1)').css('background','#fff');
            $(this).css('background','#fff');
        }
        return false;
    });
    //点击二级菜单
    $(".nav-TWO").click(function(){
        var i = $(this).hasClass('ul_show_2');
        $('.nav-TWO').find('ul:first').hide();
        $('.nav-TWO').removeClass('ul_show_2');
        if(!i){
            $(this).find('ul:first').show();
            $(this).addClass('ul_show_2');
        }else{
            $('.ul_show_2').find('ul:first').hide();
            $(this).removeClass('ul_show_2');
        }
        return false;
    });
    //点击三级菜单
    $(".nav-THREE").click(function(event){
        //改变三级菜单背景颜色
        //$(this)\
        $('.nav-THREE').removeClass('ul_show_3');
        $(this).addClass('ul_show_3');
        return false;
    });
    //一级菜单移入移出效果
    $('.left_nav_whole').each(function(){
        $(this).find('a>div:first').mouseover(function(){
            /*console.log($(this).parent().parent().hasClass('ul_show_1'));*/
            if(!$(this).parent().parent().hasClass('ul_show_1')){
                $(this).css('background','#0777bf');
            }else{
                return;
            }
        })
    });
    $('.left_nav_whole').each(function(){
        $(this).find('a>div:first').mouseout(function(){
            /*console.log($(this).parent().parent().hasClass('ul_show_1'));*/
            if(!$(this).parent().parent().hasClass('ul_show_1')){
                $(this).css('background','#5dc1ff');
            }else{
                return;
            }
        })
    });

    //强制展开菜单
    $(".extended_menus").bind('click',function(e){
        /*is_scale = false;*/
        left_nav_big_screen();
        //滚动条展开后滚动条出现的位置
       // $('.panes_scroll').css('width',350);
        e.stopPropagation();
    });
    //强制缩放菜单
    $(".pack_up_menus").bind('click',function(e){
        /*is_scale = false;*/
        left_nav_small_screen();
       //滚动条缩放后滚动条出现的位置
      //  $('.panes_scroll').css('width',40);
        e.stopPropagation();
    });
    $(".tabPanel li").each(function(){
        $(this).mouseover(function(){
            $(this).find('div:first').show();
        });
        $(this).mouseout(function(){
            $(this).find('div:first').hide();
        });
    });
    
    //根据屏幕大小，对鼠标一如移除事件进行切换
    function left_nav_hide_or_show(){
        if(get_window_width()<=1440){
            $('.panes').mouseover(function(){
                if(is_scale){
                    left_nav_big_screen();
                }else{
                    return;
                }
            });
            $('.panes').mouseout(function(){
                if(is_scale){
                    left_nav_small_screen();
                }else{
                    return;
                }
            });
        }else{
            $('.panes').mouseenter(function() {
                if (is_scale) {
                    left_nav_big_screen();
                } else {
                    return;
                }
            });
            $('.panes').mouseleave(function () {
                if (is_scale) {
                    left_nav_big_screen();
                } else {
                    return;
                }
            });
        }
    }
    //小屏幕的操作
    function left_nav_small_screen(){
        ul_hide();
        $('.left_nav_MIV2').hide();
        /*$('.left_nav_responsive_id').css('width',40);*/
        $('.panes_scroll').css('width',40);
        $(".left_nav_responsive_id").removeClass("left_nav_responsive_big").addClass("left_nav_responsive_small");
        $(".LeftNav_top_responsive_id").removeClass("LeftNav_top_responsive_big").addClass("LeftNav_top_responsive_small");
        $(".left_li_div_responsive_id").removeClass("left_li_div_responsive_big").addClass("left_li_div_responsive_small");
        $(".LeftNav_top_responsive_div1_id").removeClass("LeftNav_top_responsive_div1_big").addClass("LeftNav_top_responsive_div1_small");
        $(".LeftNav_top_responsive_div2_id").removeClass("LeftNav_top_responsive_div2_big").addClass("LeftNav_top_responsive_div2_small");
        $(".left_nav_whole>a>img").hide();
    }
    //大屏幕操作
    function left_nav_big_screen(){
        ul_show();
        $('.left_nav_MIV2').show();
        /*$('.left_nav_responsive_id').css('width',350);*/
        $('.panes_scroll').css('width',350);
        $(".left_nav_responsive_id").removeClass("left_nav_responsive_small").addClass("left_nav_responsive_big");
        $(".LeftNav_top_responsive_id").removeClass("LeftNav_top_responsive_small").addClass("LeftNav_top_responsive_big");
        $(".left_li_div_responsive_id").removeClass("left_li_div_responsive_small").addClass("left_li_div_responsive_big");
        $(".LeftNav_top_responsive_div1_id").removeClass("LeftNav_top_responsive_div1_small").addClass("LeftNav_top_responsive_div1_big");
        $(".LeftNav_top_responsive_div2_id").removeClass("LeftNav_top_responsive_div2_small").addClass("LeftNav_top_responsive_div2_big");
        $(".left_nav_whole>a>img").show();
}
    //获取窗口大小
    function get_window_width(){
        return window.innerWidth ? window.innerWidth : document.body.clientWidth;
    }
    //二级菜单的显示
    function ul_show(){
        $('.ul_show_1').find('ul:first').show();
    }
    //二级菜单的影藏
    function ul_hide(){
        $('.ul_show_1').find('ul:first').hide();
    }
});
function isScale(value){
    is_scale = value;
}

//切换标签页
$(function(){
    $('.nav_tab li').click(function(){
        $(this).addClass('pitch').siblings().removeClass('pitch');
        $('.nav_tab li a').css('color','#0195ff');
        $('.query_criteria >div:eq('+ $(this).index()+')').show().siblings().hide();

    });
    $(".nav_tab li a").click(function(){
          $('.nav_tab li a').css('color','#0195ff');
          $(this).css('color','#fff');
    });
})




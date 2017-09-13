
//事项树环节树切换
$(function(){
    $('.tabPanel li').click(function(){
        $(this).addClass('hit').siblings().removeClass('hit');
//        eq(index)匹配一个给定索引值的元素$("tr:eq(1)")
        $('.panes>div:eq('+$(this).index()+')').show().siblings().hide();
    })
})
$(function(){
    $('.LeftNav_top_responsive_div2_id div').click(function(){
        $('.panes>div:eq('+$(this).index()+')').show().siblings().hide();
        $(this).addClass('MattersToDeal_color').siblings().removeClass('MattersToDeal_color');
    })
})
// 点击进行样式操作
$(function change(){
    $(".dianji").click(function(){
        var lanseS=$(this).css('background-color','#0095ff');
        $(this).siblings('li').css("background-color", '#63ade3');
    })
})

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
//点击的时候更换图标
$(function(){
    $('.ionic_background_fujian').click(function(){
        $(this).addClass('ionic_fujian');
    })
});



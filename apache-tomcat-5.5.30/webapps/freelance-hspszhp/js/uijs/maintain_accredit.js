//ajax请求页面数据
//右侧的高度等于左侧的高度
$(document).ready(function(){
    var rheight=$('.theme_popover_content_Left').height();
    $('.popover_right_onscroll').css('height',rheight+"px")
});
//    点击弹出
$(document).ready(function($){
    $('.theme_login').click(function(){
        $('.theme_popover_mask').show();
        $('.theme_popover_mask').height($(document).height());
        $('.theme_popover').fadeIn(500);
    })
//    点击关闭
    $('.theme_poptit .theme_poptit_close').click(function(){
        $('.theme_popover_mask').hide();
        $('.theme_popover').fadeOut(500);
    });
//    点击取消
//    $('.popover_footer_cancel').click(function(){
//        $('.theme_popover_mask').hide();
//        $('.theme_popover').fadeOut(500);
//    });
//    点击保存
    $('.popover_footer_save_bt').click(function(){
        $('.theme_popover_mask').hide();
        $('.theme_popover').fadeOut(500);
    });
    $('.theme_poptit_close').hover(
        function(){$(this).addClass("close");},
        function(){$(this).removeClass("close");}
    );
//    弹窗口右侧表格隔行变色
//    $('tbody tr:nth-child(2n)').css('background','#f5f9fc');
//    $('tr').hover(
//        function(){$(this).addClass("tbodyhover");},
//        function(){$(this).removeClass("tbodyhover");}
//    );

});

//    当点击系统安全的时候变成浅蓝色
$(function changecolor(){
    $(".system_safety").click(function(){
        var systemSafetycolor=$(this).css('background-color','#408ace');
    })
})


//树
$(document).ready(function() {
//    点击系统安全方法
    $('.popover_inactive').click(function(){
//        如果二级下拉的css不显示
        if($(this).siblings('ul').css('display')=='none'){
//          移除一级下拉的图片
            $(this).parent('li').siblings('li').removeClass('popover_inactives');
//            添加系统安全a的图片
            $(this).addClass('popover_inactives');
//           显示三级菜单的li
            $(this).siblings('ul').slideDown(100).children('li');
//            如果所有二级ul的css显示为块级
            if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
//               所有二级ul的父li的a移除图片
                $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('popover_inactives');
//                所有一级菜单的ul隐藏
//                $(this).parents('li').siblings('li').children('ul').slideUp(100);
            }
        }else{
            //a移除图片
            $(this).removeClass('popover_inactives');
            //移除所有二级ul
            $(this).siblings('ul').slideUp(100);
            //添加二级修改菜单a的图片
            $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('popover_inactives');
            //隐藏ul修改下的li
            $(this).siblings('ul').children('li').children('ul').slideUp(100);
            //控制同级菜单只保持一个是展开的）
            $(this).siblings('ul').children('li').children('a').append();
//            $(this).siblings('ul').children('li').children('a').removeClass('popover_inactives');
        }
    })
});
$(document).ready(function(){
    $(".popover_inactive_systemSafety li").click(function(){
        $(this).toggleClass("toggleClass_systemSafety");
    });
});
//切换颜色
$(document).ready(function(){
    $("#tbody tr").click(function(){
        $(this).toggleClass("toggleClass_systemSafety");
    });
});
$(document).ready(function(){
    $(".removeElement").click(function(){
        $("#tbody tr").eq(1).remove();
    });
});
/*角色授权切换代码*/
$(document).ready(function(){
    $(".authorization_management span").click(function(){
        $(".authorization_management span").removeClass("authorization_management_span_active").addClass("authorization_management_span_default");
        $(this).removeClass("authorization_management_span_default").addClass("authorization_management_span_active");
    });
});

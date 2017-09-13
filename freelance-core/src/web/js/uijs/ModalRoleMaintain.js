//ajax请求页面数据
function reque() {
    $.ajax( {
        url:'ModalRoleMaintain_a.html', //请求片段的地址
        type: "GET", //静态页用get方法，否则服务器会抛出405错误
        dataType:"html",
        success: function(data){
//            alert(data);
            $(".ajax").html(data);

        }
    })
};
//模态框功能
$(function(){
    //右侧的高度等于左侧的高度
    var rheight=$('.theme_popover_content_Left').height();
    $('.theme_popover_content_Right').css('height',rheight+"px")

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
    $('.popover_footer_cancel').click(function(){
        $('.theme_popover_mask').hide();
        $('.theme_popover').fadeOut(500);
    });
//    点击保存
    $('.popover_footer_save_bt').click(function(){
        $('.theme_popover_mask').hide();
        $('.theme_popover').fadeOut(500);
    });
    $('.theme_poptit_close').hover(
        function(){$(this).addClass("close");},
        function(){$(this).removeClass("close");}
    );
    //    当点击系统安全的时候变成浅蓝色
    $(".system_safety").click(function(){
        var systemSafetycolor=$(this).css('background-color','#408ace');
    })
    //选项卡切换
    $('.role_tabPanel .role_tabPanelUl li').click(function(){
        $(this).addClass('role_hit').siblings().removeClass('role_hit');
        $('.Role_panes>div:eq('+$(this).index()+')').show().siblings().hide();
    })
   //当点击选择卡li的时候字体没有加粗
//    $('.role_tabPanelUl li').css('font-weight','normal')
    // 点击结构树切换颜色
    $(".popover_inactive_systemSafety li").click(function(){
        $(this).addClass("toggleClass_systemSafety").siblings().removeClass('toggleClass_systemSafety');
    });
//    添加节点
    $(".addElement").click(function(){
        $("#tbody").append(' <tr class="customers_tr_height">' +
                '<td class="customers_contet_input">' +
                '<input type="checkbox"/>' +
                '</td>' +
                '<td class="customers_contet_td">' +
                '人事信息管理子系统' +
                '</td>' +
                '<td class="customers_contet_td">' +
                '资源维护' +
                '</td>' +
                '</tr>'
        );
    });
//   删除节点
    $(".removeElement").click(function(){
        $("#tbody tr").eq(1).remove();
    });

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


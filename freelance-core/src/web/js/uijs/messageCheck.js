
//切换主管单位名称颜色
function switchClass(my_this, item1, item2){
    $(my_this).removeClass(item1);
    $(my_this).addClass(item2);
}

//增加节点
function  add_item(my_this,arrowFlag){
    if ($(my_this).parent('li').parent('ul').attr('id') == "firstUl")
    {
        $(my_this).addClass('left_backgroud_blue');
        switchClass($(my_this), 'unit_font_black', 'unit_font_white');
        // 箭头移除新增
        if(arrowFlag=="Y"){//一级菜单无子节点 箭头无需变化
        	/*$(my_this).children('div:first-child').removeClass('left_arrows_r');
            $(my_this).children('div:first-child').addClass('left_arrows_d');*/
        	$(my_this).children('img').attr('src','img/messageCheck/arrow_down.png');
        }
        $(my_this).children('div:eq(0)').removeClass('left_icon_ji');
        $(my_this).children('div:eq(0)').addClass('left_icon_ji_deepblue');
        $(my_this).parent().addClass('ul_show_1');
        $(my_this).parent().siblings().removeClass('ul_show_1');
        
    }
    // 显示
    $(my_this).siblings('ul').slideDown(100);
}

//删除节点
function  delete_item(my_this)
{
	 
    if($(my_this).parent('li').parent('ul').attr('id') == "firstUl")
    {
        $(my_this).removeClass('left_backgroud_blue');
        switchClass($(my_this), 'unit_font_white', 'unit_font_black');

        /*$(my_this).children('div').removeClass('left_arrows_d');
        $(my_this).children('div').addClass('left_arrows_r');*/
        $(my_this).children('img').attr('src','img/messageCheck/arrow_right.png');
        $(my_this).each(function(){
        	$(this).children('div:eq(0)').removeClass('left_icon_ji_deepblue');
        })
       /* $(my_this).children('div:eq(0)').removeClass('left_icon_ji_deepblue');
        $(my_this).children('div:eq(0)').addClass('left_icon_ji');*/
        $(my_this).parent().removeClass('ul_show_1');
    }
    // 隐藏
    $(my_this).siblings('ul').slideUp(100);
    $(my_this).siblings('ul').children('li').children('ul').slideUp(100);
}

//主管单位点击事件
$(document).ready(function() {
    $('.inactive').click(function(){
        if($(this).siblings('ul').css('display')=='none'){
            add_item($(this));
            delete_item($(this).parent('li').siblings('li').children('a'));
        }else{
            delete_item($(this));
        }
    })
});
//点击图片切换
$(".inactive").click(function(){
    $('.inactive .arrow_img2').show();
});




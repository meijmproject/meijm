/**
 * Created by Administrator on 2016/11/21.
 */
$(document).ready(function(){

    //屏幕高度自适应方法
    function aoutSalaryHeight() {
        var windowHeight = get_window_height();
     $('.c-main-content').height(windowHeight-$(".head-v3").height()-$(".sitemap").height()-$(".salarymanage-search").height()-38);

        console.log($('.c-main-content').height());
        $('.ct').height($(".c-main-content").height()-$(".page_foot").height()-20);
        console.log($('.ct').height());
        $("div.length_menu.length_menu_select").css("margin-top","14px");
    }
    aoutSalaryHeight();
//    
    $(window).resize(function(){
        aoutSalaryHeight();
        search_condition_hide_or_show();
    });


    search_condition_hide_or_show();
    //根据屏幕<1440px时左边查询条件区隐藏
    function search_condition_hide_or_show() {
        if (get_window_width() <= 1440) {
            search_condition_hide();
        }else {
            search_condition_show();
        }
    }
    
  //获取窗口高度
    function get_window_height(){
        return window.innerHeight ? window.innerHeight : document.body.clientHeight;
    }
    //获取窗口宽度
    function get_window_width(){
        return window.innerWidth ? window.innerWidth : document.body.clientWidth;
    }
//    搜索框隐藏和显示
    $(".btn-salary-search").click(function(){
        if($(".search-condition-detail").is(':hidden')){
            search_condition_show();
        }else {
            search_condition_hide();
        }
    });
    //查询详情区隐藏
    function search_condition_hide() {
        $(".search-condition-detail").hide();
        $(".salarymanage-right-content").addClass("salarymanage-right-content-extend");
        $(".btn-salary-search").find("span:first-child").text(">>");
        $(".btn-salary-search").find("span:last-child").text("展开搜索");
        return false;
    }
    function search_condition_show() {
        $(".search-condition-detail").show();
        $(".salarymanage-right-content").removeClass("salarymanage-right-content-extend");
        $(".btn-salary-search").find("span:first-child").text("<<");
        $(".btn-salary-search").find("span:last-child").text("隐藏搜索");
    }

//    已选条件的删除
//    $(".selected-condition").click(function(){
//        $(this).remove();
//    })
//    $(".selected-clear").click(function(){
//       $(this).siblings('.selected-condition').remove();
//    });

//    $(".search-condition-unit").blur(function(){
//    	$(this).removeClass("search-condition-unit-focus");
//    });
//   三项列表选择
//    $(".search-condition-ul li").click(function(){
//        $(this).addClass("search-condition-active")
//        $(this).siblings('li').removeClass("search-condition-active");
//    });
//    单位选项输入下拉
//    $(".search-condition-unit").focus(function(){
//         $(this).siblings('.search-condition-unit-ul').show();
//         $(this).addClass("search-condition-unit-focus");
//    });
//    $(".search-condition-unit-ul li").hover(function(){
//        $(this).toggleClass("search-condition-active");
//    });
//    $(".search-condition-unit-ul li").click(function(){
//       $(this).parent().hide();
//        $(".search-condition-unit").removeClass("search-condition-unit-focus");
//        $(".search-condition-unit").val($(this).text());
//    });
});
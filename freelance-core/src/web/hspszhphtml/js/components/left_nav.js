
$(function () {
    $(window).load();
    //左导航全部展开
    function leftNavShow() {
        $('.left-nav').find('ul').show();
        $('.left-nav').find('.ln-plus-icon').attr('class','ln-minus-icon');
    }
    leftNavShow();

    //点击左导航收缩展开按钮
    $('.leftnav-flod-icon').click(function () {
        // 展开
        if ($(this).hasClass('leftnav-unflod-icon')){
            $(this).removeClass('leftnav-unflod-icon');
            $(this).parent().attr('class','leftnav-fold');
            $(this).parent().siblings('div').show();
            $('.left-content').css('width','257px');
        }else {
            /*收缩*/
            $(this).addClass('leftnav-unflod-icon');
            $(this).parent().siblings('div').hide();
            $(this).parent().attr('class','leftnav-unflod');
            $('.left-content').css('width','45px');
        }
    });

    // 点击左导航"+"号展开子菜单
    $(document).on('click','.ln-plus-icon',function (e) {
        e.stopPropagation();//防止冒泡
        $(this).attr('class','ln-minus-icon');  //"+"号变"-"
        $(this).siblings('ul').show();
        leftNavScrollHide();
    });

    // 点击左导航"-"号收缩子菜单
    $(document).on('click','.ln-minus-icon',function (e) {
        e.stopPropagation();//防止冒泡
        $(this).attr('class','ln-plus-icon');  //"-"号变"+"
        $(this).siblings('ul').hide();

        leftNavScrollHide();
    });
    //点击左导航中li选中状态显示
    $('.left-nav li a').click(function (e) {
        e.stopPropagation();//防止冒泡
        // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
        $('.left-nav').find('a').removeClass('left-nav-active');
        $(this).addClass('left-nav-active');
    });


    // 滚动条优化
    $(".nano.left-nav-scroll").nanoScroller();
    //重新计算滚条的高度
    $(".nano.left-nav-scroll").nanoScroller({ sliderMaxHeight: 300 });
    $(".nano.left-nav-scroll").nanoScroller({ sliderMinHeight: 80 });
    $(".nano.left-nav-scroll").nanoScroller({ flashDelay: 1000 });

    //左部导航隐藏
    function leftNavScrollHide() {
        //获取页面可视区域高度
        var windowHeight = $(window).height();
        var leftNavMaxHeight = windowHeight - $('.head-nav').outerHeight(true)-$('.leftnav-control').outerHeight(true);
        var leftNavRealHeight = $('.left-nav').outerHeight(true);

        // 左导航高度不需要滚动时滚动条隐藏
        if (leftNavRealHeight < leftNavMaxHeight){
            $(".nano.left-nav-scroll").nanoScroller({ stop: true });
        }else {
            $(".nano.left-nav-scroll").nanoScroller({ stop: false });
        }
    }


    //点击事项环节树tab切换
    $(document).on('click','.tab-left-nav .leftnav-fold li',function () {
        var tabLeftName = $(this).attr("name");
       $(this).siblings('li').removeClass("leftnav-tab-selected");
       $(this).addClass("leftnav-tab-selected");

        $(".nano[name = '"+tabLeftName+"']").show();
        $(".nano[name = '"+tabLeftName+"']").siblings(".nano").hide();
        $(".nano.left-nav-scroll").nanoScroller();
    });
    
    // 左导航查询input事件
    $(document).on('click','.leftnav-search-area>input',function () {
        var objWidth = $(this).width();
        var pdList = $(this).siblings(".s-pulldown-list");
        if (pdList.is(':visible')){
            pdList.hide();
        }else {
            pdList.show();
            pdList.width(objWidth + 30);
        }
    });
});

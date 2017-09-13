/**
 * Created by Administrator on 2016/12/26.
 */

var is_collapse = false;
$(function () {
    var is_collapse = false;
    //滚动条美化
    $(".left-nano").nanoScroller();
    //重新计算滚条的高度
    $(".left-nano").nanoScroller({ sliderMaxHeight: 300 });
    $(".left-nano").nanoScroller({ sliderMinHeight: 80 });
    $(".left-nano").nanoScroller({ flashDelay: 1000 });
    $(".left-nano-pane").css('display','block');
    //leftNavBar.nanoscroller.js文件
    
    $('.pack_up_menus').click(function(){
        pack_up_left_nav_bar();
    });
    //左右横滚动条置底
    var documentheight=$(document).height();
    var bodyheight=$(document.body).height();//浏览器当前窗口文档body的高度
    var allHeight=$(document.body).outerHeight(true);//浏览器当前窗口文档body的总高度 包括border padding margin
    var windowheight=$(window).height();//获取window高度.
    var sitemapheight=$('.sitemap').height()+21;
    $(".left_NavBar").css("height",windowheight-sitemapheight);
   // console.log(sitemapheight)
  //  var headerheight=$('.head-v3').height();//获取head-v3高度
 //   var sitemapheight=$('.sitemap').height()+21;//获取sitemap高度
  //  var  leftNavmoreheight=windowheight-(sitemapheight+headerheight);//获取除去head-v3高度和sitemap高度以外的高度
 //   $(".left_NavBar").css("height",leftNavmoreheight);//左边的高度等于剩下window的高度
   /* $(".left_NavBar").css("max-height",845);
    $(".right_NavBar").css("height",leftNavmoreheight);
    $(".right_NavBar").css("max-height",845);*/
    
    
    


    $('.pack_up_menus').click(function(){
        pack_up_left_nav_bar();
    });
    //头部导航栏展开收缩功能
    $('.SelectIocniBg').click(function(){
        pack_up_left_nav_bar();
    });
    $('.extended_menus').click(function(){
        spread_left_nav_bar();
    });
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
    //左侧导航栏收缩展开
  $('.NavBar_FirstLiImg').click(function () {
      // console.log($('.NavBar_FirstLiImgJia').parent().parent().siblings('.PansSecondUl'));
      //如果二级UL元素为隐藏,则将它显现
    if($(this).parent().parent().siblings('.PansSecondUl').is(":hidden")){
        $('.PansSecondUl').hide();
        $('.NavBar_FirstLiImg').addClass('NavBar_FirstLiImgJia');
        $('.NavBar_FirstLiImg').removeClass('NavBar_FirstLiImgJian');
        $('.NavBar_FirstLiImg').parents('p').parents('div').siblings('ul').hide();
        $('.NavBar_FirstLiImg').siblings('b').css('background','#2fabff');
        $('.NavBar_FirstLiImg').parents('p').css('background','#fff');
        $('.NavBar_FirstLiImg').parents('p').css('width','100%');
        $('.NavBar_FirstLiImg').children('i').removeClass('PansFirstLi_active');

        $(this).removeClass('NavBar_FirstLiImgJia');
        $(this).addClass('NavBar_FirstLiImgJian');
        $(this).parents('p').parents('div').siblings('ul').show(1).children('li');
        $(this).siblings('b').css('background','#1388d5');
        $(this).parents('p').css('background','#eef1f4');
        $(this).parents('p').css('width','100%');
        $(this).children('i').addClass('PansFirstLi_active');
        /*//如果打开li，其他兄弟li关闭
        if($(this).parents('li').siblings('li').children('ul').is(':hidden')){
            $(this).parents('li').siblings('li').children('ul').slideUp(100);
            $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('NavBar_FirstLiImgJia');
        }else{

        }*/
    }else{
        //如果二级ul元素为显现,则将其隐藏
        $(this).addClass('NavBar_FirstLiImgJia');
        $(this).removeClass('NavBar_FirstLiImgJian');
        $(this).parents('p').parents('div').siblings('ul').hide();
        $(this).siblings('b').css('background','#2fabff');
        $(this).parents('p').css('background','#fff');
        $(this).parents('p').css('width','100%');
        $(this).children('i').removeClass('PansFirstLi_active');

    }
  });
  //三级ul
  $('.NavBar_SecondLiImg').click(function () {
      //如果三级ul不显示
      if($(this).parent().parent().siblings('.PansThirdUl').is(":hidden")){
          $('.NavBar_SecondLiImg').parents('p').parents('div').siblings('ul').hide();
          $('.NavBar_SecondLiImg').children('b').removeClass('NavBar_SecondLiImgJiaB');
          $('.NavBar_SecondLiImg').addClass('NavBar_SecondLiImgJia');
          $('.NavBar_SecondLiImg').removeClass('NavBar_SecondLiImgJian');

          $(this).parents('p').parents('div').siblings('ul').show().children('li');
          $(this).children('b').addClass('NavBar_SecondLiImgJiaB');

          $(this).removeClass('NavBar_SecondLiImgJia');
          $(this).addClass('NavBar_SecondLiImgJian');

      }else{
          $(this).parents('p').parents('div').siblings('ul').hide();
          $(this).children('b').removeClass('NavBar_SecondLiImgJiaB');

          $(this).addClass('NavBar_SecondLiImgJia');
          $(this).removeClass('NavBar_SecondLiImgJian');
      }
  });
  //四级
    $('.NavBar_ThirdLiImg').click(function () {
        //四级展开和隐藏
        if($(this).parent().parent().siblings('.PansFourthUl').is(":hidden")){
            $('.NavBar_ThirdLiImg').parents('p').parents('div').siblings('ul').hide();
            $('.NavBar_ThirdLiImg').children('b').removeClass('NavBar_ThirdLiImgJiaB');
            $('.NavBar_ThirdLiImg').addClass('NavBar_ThirdLiImgJia');
            $('.NavBar_ThirdLiImg').removeClass('NavBar_ThirdLiImgJian');

            $(this).parents('p').parents('div').siblings('ul').show().children('li');
            $(this).children('b').addClass('NavBar_ThirdLiImgJiaB');

            $(this).removeClass('NavBar_ThirdLiImgJia');
            $(this).addClass('NavBar_ThirdLiImgJian');
        }else{
            $(this).parents('p').parents('div').siblings('ul').hide();
            $(this).children('b').removeClass('NavBar_ThirdLiImgJiaB');

            $(this).addClass('NavBar_ThirdLiImgJia');
            $(this).removeClass('NavBar_ThirdLiImgJian');
        }
    });
    //五级
    $('.NavBar_FourthLiImg').click(function () {
        //五级展开和隐藏
        if($(this).parent().parent().siblings('.PansFifthUl').is(":hidden")){
            $('.NavBar_FourthLiImg').parents('p').parents('div').siblings('ul').hide();
            $('.NavBar_FourthLiImg').children('b').removeClass('NavBar_FourthLiImgJiaB');
            $('.NavBar_FourthLiImg').addClass('NavBar_FourthLiImgJia');
            $('.NavBar_FourthLiImg').removeClass('NavBar_FourthLiImgJian');

            $(this).parents('p').parents('div').siblings('ul').show().children('li');
            $(this).children('b').addClass('NavBar_FourthLiImgJiaB');

            $(this).removeClass('NavBar_FourthLiImgJia');
            $(this).addClass('NavBar_FourthLiImgJian');
        }else{
            $(this).parents('p').parents('div').siblings('ul').hide();
            $(this).children('b').removeClass('NavBar_FourthLiImgJiaB');

            $(this).addClass('NavBar_FourthLiImgJia');
            $(this).removeClass('NavBar_FourthLiImgJian');
        }
    });
});
//左导航收起方法
function pack_up_left_nav_bar(){
    $('.pack_up_img_left_nav_bar').show();
    $('.LeftNav_top_responsive_div1_id').show();
    $('.LeftNav_top_responsive_div2_id').hide();
    $('.left_nav_MIV2').hide();
    $('.left-nano').css('width','55px');
    $('#line').css('left',$('.left-nano').css('width'));
    is_collapse = true;

    $('.leftNavSelect').hide();
    /*$('.leftNavBarPackUp').show();*/
    //一级菜单影藏
    $('.PansFirstLi_p1 span').hide();
    $('.PansFirstLi_p1 a').hide();
    $('.PansFirstA_title').hide();
    //二级菜单影藏
    $('.PansSecondline1').hide();
    $('.NavBar_SecondLiImg').hide();
    $('.PansSecondA_title').hide();
    $('.PansSecondLi_greenOffice').hide();
    $('.PansSecondline2_LastOne').hide();
    //三级菜单影藏
    $('.PansThirdline1').hide();
    $('.PansThirdline2').hide();
    $('.NavBar_ThirdLiImg').hide();
    $('.PansThirdline2_LastOne').hide();
    $('.PansThirdLi_greenOffice').hide();
    $('.PansThirdA_title').hide()
    //四级菜单影藏
    $('.PansFourthline1').hide();
    $('.PansFourthline2').hide();
    $('.PansFourthline3').hide();
    $('.PansFourthline3_LastOne').hide();
    $('.NavBar_FourthLiImg').hide();
    $('.PansFourthLi_greenOffice').hide();
    $('.PansFourthA_title').hide();
    //五级菜单影藏
    $('.PansFifthline1').hide();
    $('.PansFifthline2').hide();
    $('.PansFifthline3').hide();
    $('.PansFifthline4').hide();
    $('.PansFifthline5').hide();
    $('.PansFifthLi_greenOffice').hide();
    $('.PansFifthLi_greenOffice02').hide();
    $('.PansFifthA_title').hide();
}
//左导航展开方法
function spread_left_nav_bar(){
    $('.pack_up_img_left_nav_bar').hide();
    $('.left-nano').css('width','350px');
    $('.LeftNav_top_responsive_div1_id').hide();
    $('.LeftNav_top_responsive_div2_id').show();
    $('.left_nav_MIV2').show();
    $('#line').css('left',$('.left-nano').css('width'));
    is_collapse = false;

    $('.leftNavSelect').show();
    $('.leftNavBarPackUp').hide();
    //一级菜单影藏
    $('.PansFirstLi_p1 span').show();
    $('.PansFirstLi_p1 a').show();
    $('.PansFirstA_title').show();
    //二级菜单影藏
    $('.PansSecondline1').show();
    $('.NavBar_SecondLiImg').show();
    $('.PansSecondA_title').show();
    $('.PansSecondLi_greenOffice').show();
    $('.PansSecondline2_LastOne').show();
    //三级菜单影藏
    $('.PansThirdline1').show();
    $('.PansThirdline2').show();
    $('.NavBar_ThirdLiImg').show();
    $('.PansThirdline2_LastOne').show();
    $('.PansThirdLi_greenOffice').show();
    $('.PansThirdA_title').show()
    //四级菜单影藏
    $('.PansFourthline1').show();
    $('.PansFourthline2').show();
    $('.PansFourthline3').show();
    $('.PansFourthline3_LastOne').show();
    $('.NavBar_FourthLiImg').show();
    $('.PansFourthLi_greenOffice').show();
    $('.PansFourthA_title').show();
    //五级菜单影藏
    $('.PansFifthline1').show();
    $('.PansFifthline2').show();
    $('.PansFifthline3').show();
    $('.PansFifthline4').show();
    $('.PansFifthline5').show();
    $('.PansFifthLi_greenOffice').show();
    $('.PansFifthLi_greenOffice02').show();
    $('.PansFifthA_title').show();
}
//获取窗口大小
function get_window_width(){
    return window.innerWidth ? window.innerWidth : document.body.clientWidth;
}
//拖动
//获取class
function getElementsClass(classnames){
    var classobj= new Array();//定义数组
    var classint=0;//定义数组的下标
    var tags=document.getElementsByTagName("*");//获取HTML的所有标签
    for(var i in tags){//对标签进行遍历
        if(tags[i].nodeType==1){//判断节点类型
            if(tags[i].getAttribute("class") == classnames)//判断和需要CLASS名字相同的，并组成一个数组
            {
                classobj[classint]=tags[i];
                classint++;
            }
        }
    }
    return classobj;//返回组成的数组
}
setTimeout(function (O) {
    var Opane=getElementsClass("left-nano-pane");
    // console.log(Opane)
    return Opane
},100)
window.onload = function(oLine) {
    var window_width= get_window_width();
    var oBox=document.getElementById("box");
    var oTop=document.getElementById("top");
    var oBottom=document.getElementById("right_attachment");
    var oLine=document.getElementById("line");
    //onmousedown 属性在鼠标按钮在元素上按下时触发
    oLine.onmousedown = function(e) {
        //获取当前x坐标,clientX 事件属性返回当事件被触发时鼠标指针向对于浏览器页面（或客户区）的水平坐标
        var disX = (e || event).clientX;
        //获取oLine左偏移
        oLine.left = oLine.offsetLeft;
        //鼠标指针移动到元素上时触发
        document.onmousemove = function(e) {
            //it=oLine横坐标位置
            var iT = oLine.left + ((e || event).clientX - disX);
            //event.srcElement 可以捕获当前事件作用的对象
            var e=e||window.event,tarnameb=e.target||e.srcElement;
            var maxT = oBox.clientWight - oLine.offsetWidth;
            oLine.style.margin = 0;
            iT < 0 && (iT = 0);
            iT > maxT && (iT = maxT);
            if(iT<55){
                pack_up_left_nav_bar();//收起方法
                oLine.style.left = $('.left-nano').css('width');
            }
            else if(iT>55&&iT<120){
                spread_left_nav_bar();//展开方法
                $('.left-nano').css('width',iT+'px');
                oLine.style.left =  $('.left-nano').css('width');
            }else if(iT<250 && iT>120){
                pack_up_left_nav_bar();//收起方法
                oLine.style.left = $('.left-nano').css('width');
            }else if(iT>250 && (window_width-iT)>400){ //window_width整个屏幕的宽度-横坐标位置
                spread_left_nav_bar();//展开方法
                $('.left-nano').css('width',iT+'px');
                oLine.style.left =  $('.left-nano').css('width');
            }else if((window_width-iT)<400){
                oLine.style.left = $('.left-nano').css('width');
                oTop.style.width = window_width-400 + "px";
                oLine.style.left = $('.left-nano').css('width');
            }else{
                oLine.style.left = $('.left-nano').css('width');
                oTop.style.width = iT + "px";
                oLine.style.left = $('.left-nano').css('width');
            }
            // else{
            //     oLine.style.left = $('.left-nano').css('width');
            //     oTop.style.width = iT + "px";
            // }
            oBottom.style.marginRight=0;
            $("msg").innerText='top.width:'+oLine.style.width+'---bottom.width:'+oBottom.style.width+'---oLine.offsetLeft:'+oLine.offsetLeft+'---disX:'+disX+'---tarnameb:'+tarnameb.tagName;
            return false
        };
        //onmouseup 属性在松开鼠标按钮时触发
        document.onmouseup = function() {
            //onmousemove 属性在鼠标指针移动到元素上时触发
            document.onmousemove = null;
            //onmouseup 属性在松开鼠标按钮时触发
            document.onmouseup = null;
            oLine.releaseCapture && oLine.releaseCapture()
        };
        oLine.setCapture && oLine.setCapture();
        return false
    };
};

// 滚动条功能
var leftNavscroll=function (a,b,c,d) {
    var $scroll=$(a);
    var $content=$(b);
    var $scrBot=$(c);
    var $scrTop=$(d);
    //滚动条盒子的高
    var $scrollH=$scroll.parent().height()-$scroll.height();
    //滚动条内容的高
    var $contentHA=$content.height()-$content.parent().height();
    var $per=$contentHA/$scrollH;//内容的高/滚动盒子的高
    var timeDo=null;
    var timeDo1=null;
    var timeTo=null;
    var timeTo1=null;
    if($contentHA<=0) return;
    //滚动条,mousedown当按下鼠标按钮时
    $scroll.mousedown(function (e) {
        //pageY鼠标指针的位置
        var mouseY=e.pageY; //当前Y坐标位置
        var $scrollP=$scroll.position().top;//滚动条顶部的高
        //bind将事件和函数绑定到元素,bind() 方法为被选元素添加一个或多个事件处理程序，并规定事件发生时运行的函数
        $(document).bind('mousemove',
            function (e) {
                var mouseYnow=e.pageY;
                var mValue=mouseYnow-mouseY+$scrollP;//移动值
                var $contentH= -mValue * $per;//移动值 *滚动区的高
                if(mValue>= 0 && mValue <= $scrollH){
                    $scroll.css("top",mValue+'px');
                    $content.css('top',$contentH+'px');
                }
                if(mValue<=0){ //如果坐标Y值在顶部
                    $scroll.css("top",'0');
                    $content.css('top','0');
                }
                if(mValue>=$scrollH){
                    $scroll.css('top',$scrollH+'px');
                    $content.css('top',-$contentHA+'px')
                }
            })
        e.preventDefault();//取消事件的默认动作
        document.onselectstart=function () {  //禁止鼠标选中文字
            return false;
        }
    })
    //滚轮
    $content.hover(function () {
        if(window.addEventListener){this.addEventListener('DOMMouseScroll',wheel,false)};//DOMMouseScroll鼠标事件
        //鼠标滚轮事件onmousewheel处理
        this.onmousewheel=wheel;//鼠标滚轮滚动控制图片或文字的大小,转动鼠标滚轮实现缩放等等交互效果中，会用到 Mousewheel事件
        window.onmousewheel=document.onmousewheel=function (){return false;}
    },function () {
        if(window.addEventListener)this.removeEventListener('DOMMouseScroll',wheel,false);
        this.onmousewheel=this.onmousewheel=null;
        window.onmousewheel=document.onmousewheel=function () {return true;}
    })
    function  handle(delta) {
        var i=$content.position().top;//内容顶部
        if(delta<0){
            i=i-10;
            i2=i/$per;  //顶部/内容的高/滚动盒子的高
            if(i<= -$contentHA){
                $content.css('top',-$contentHA+'px');
                $scroll.css('top',$scrollH+'px')
                i=-$contentHA; //滚动条内容的高
            }else{
                $content.css('top',i+'px');
                $scroll.css('top',-i2+'px')
            }
        }else{
            i=i+10;
            i2=i/$per;
            if(i>0){
                $content.css('top',0+'px');
                $scroll.css('top',0+'px');
                i=0;
            }
            else{
                $content.css('top',i+'px');
                $scroll.css('top',-i2+'px');
            }
        }
    }
    var ss = 0;
    var delta=0;
    //监听事件函数
    function  wheel(event) {
        if(!event)event=window.event;//如果window不是当前事件
        if(event.wheelDelta){
            delta=event.wheelDelta/120;
            if(window.opera)delta=-delta;
        }else if(event.detail)delta=-event.detail/3;
        ss = ss+ delta;
        if(ss)handle(ss);
        if(delta)handle(delta);
        if(!window.event){
            event.preventDefault();
        }
    }
}
/*leftNavscroll('#leftNavScroll','#scrollcontent','#scroll_bot','#scroll_top')*/
//detail与wheelDelta,判断滚轮向上或向下
//detail只取±3，wheelDelta只取±120，其中正数表示为向上，负数表示向下







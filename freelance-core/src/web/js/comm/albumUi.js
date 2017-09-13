/**
 * Created by chenjie on 2017/3/6.
 */

$.extend({
    albumUi:function(data){
        var arr = data;
        var html = '<div class="black-bg">'
                 + '</div>'
                 + '<div class="photo-album">'
                 +     '<div class="img-title">'
                 +         '<span style="margin-right: 30px;" class="caption-album">暂无图片显示</span>'
                 +         '<span>( <span class="current-number-album">1</span> / <span class="sum-album">1</span>)</span>'
                 +     '</div>'
                 +     '<div class="div-close-album">'
                 +          '<i class="close-album"></i>'
                 +     '</div>'
                 +     '<div class="div-arrow-left-album">'
                 +         '<i class="img-arrow-left-album"></i>'
                 +     '</div>'
                 +     '<div class="div-img">'
                 +         '<img draggable="true" class="img-album" src="" alt="">'
                 +     '</div>'
                 +     '<div class="div-arrow-right-album">'
                 +         '<i class="img-arrow-right-album"></i>'
                 +     '</div>'
                 +     '<div class="img-operate">'
                 +         '<i class="clockwise-rotation-album"></i>'
                 +         '<i class="shrink-album"></i>'
                 +         '<i class="magnify-album"></i>'
                 +         '<i class="contra-rotate-album" ></i>'
                 +     '</div>'
                 + '</div>';
        $('body').append(html);
        //控制操作区图标在底部居中显示问题
        initDivCenter();
        //加载图片数据
        initAlbum(arr);
        /*相册图片拖动*/
        drag_and_drop($('.img-album'));

        //imgWidth获取加载图片的宽度
        var imgWidth = parseInt($('.img-album').css('width'));
        //scaling放缩比例
        var scaling = 1;
        //下面是浏览器类型和版本的判断以便调用不同的旋转方法
        var browser=navigator.appName
        var b_version=navigator.appVersion
        var version=b_version.split(";");
        var trim_Version=version[1].replace(/[ ]/g,"");
        if(browser=="Microsoft Internet Explorer" && (trim_Version=="MSIE6.0" || trim_Version=="MSIE7.0" ||trim_Version=="MSIE8.0" || trim_Version=="MSIE9.0")){
            picture_rotate_ie($('.contra-rotate-album'),$('.clockwise-rotation-album'),$('.img-album'));
        }else{
            picture_rotate($('.contra-rotate-album'),$('.clockwise-rotation-album'),$('.img-album'));
        };
        //控制缩放代码
        //scaling,图片硕放比例，1为不缩放,最小为0.25，最大为2
        $(document).on('click','.shrink-album',function(){
            if(scaling<=0.25){
                return;
            }else{
                scaling=scaling-0.25;
                imgScaling(scaling)
            }
        });
        //图片放大
        $(document).on('click','.magnify-album',function(){
            if(scaling>=4){
                return;
            }else{
                scaling=scaling+0.25;
                imgScaling(scaling);
            }
        });
        //上一张图片
        $('.img-arrow-left-album').unbind('click').click(function(){
            var i = parseInt($('.current-number-album').text())-1;
            if(i<=0){
                i = arr.length-1;
                imageSwitching(i);
                $('.current-number-album').text(i+1);
            }else{
                i--;
                imageSwitching(i);
                $('.current-number-album').text(i+1);
            }
        });
        //下一张图片
        $('.img-arrow-right-album').unbind('click').click(function(){
            var i = parseInt($('.current-number-album').text())-1;
            if(i>=arr.length-1){
                i = 0;
                imageSwitching(i);
                $('.current-number-album').text(i+1);
            }else{
                i++;
                imageSwitching(i);
                $('.current-number-album').text(i+1);
            }
        });
        //关闭相册操作
        $('.div-close-album').click(function(){
            $('.black-bg').remove();
            $('.photo-album').remove();
        })
        //控制操作区图标在底部居中显示问题
        function initDivCenter(){
            $('.photo-album').css('height',$(window).height()+'px');
            $('.black-bg').css('height',$(window).height()+'px');
            $('.div-arrow-right-album').css('height',$(window).height()-200+'px');
            $('.div-arrow-left-album').css('height',$(window).height()-200+'px');
            $('.img-operate').css('left',getOperateDivLeft()+'px');
            $(window).resize();
            $(window).resize(function () {
                $('.black-bg').css('height',$(window).height()+'px');
                $('.photo-album').css('height',$(window).height()+'px');
                $('.div-arrow-right-album').css('height',$(window).height()-200+'px');
                $('.div-arrow-left-album').css('height',$(window).height()-200+'px');
                $('.img-operate').css('left',getOperateDivLeft()+'px');
            });
        }
        //图片缩放方法
        function imgScaling(scaling){
            $('.img-album').css('width',scaling*imgWidth+'px');
            $('.img-album').css('height','auto');
        };
        //初始化相册内容
        function initAlbum(arr){
            if(arr.length == 0 || undefined || false){
                return;
            }else{
                imageSwitching(0);
            }

        }
        //左右切换图片的渲染方法
        function imageSwitching(i){
            imgScaling(1);
            cj_rotate($('.img-album'),0);
            rotate($('.img-album'),0);
            $('.img-album').css({'top':'10%','left':'20%'})
            $('.caption-album').text(arr[i].imgTitle);
            $('.current-number-album').text(i+1);
            $('.sum-album').text(arr.length);
            if(arr[i].imgUrl != ''){
                $('.img-album').attr('src',arr[i].imgUrl);
            }else{
                $('.img-album').attr('src','');
            }
        }
        //计算操作按钮区的left(为使他居中)
        function getOperateDivLeft(){
            var width1 = parseInt($('body').css('width'));
            var width2 = parseInt($('.img-operate').css('width'));
            return (width1-width2)/2;
        };
        //图片拖拽函数
        function drag_and_drop(obj){
            //x方向的偏移值
            var disX = 0;
            //y方向的偏移值
            var disY = 0;
            obj.mousedown(function(ev){
                //判断鼠标抬起时间
                var drag_is = true;
                disX = ev.pageX - obj.offset().left+70;
                disY = ev.pageY - obj.offset().top+60;
                $(document).mousemove(function(ev){
                    if(drag_is){
                        obj.css('left',ev.pageX - disX);
                        obj.css('top',ev.pageY - disY);
                    }else{
                        return;
                    }
                });
                $(document).mouseup(function(){
                    drag_is = false;
                    return;
                });
                return false;
            });
        };
        //ie下的旋转方法
        function picture_rotate_ie(obj1,obj2,imgClass){
            var cj=0;
            obj1.unbind('click').click(function(){
                cj++;
                cj_rotate(imgClass,cj);
                return false;
            });
            obj2.unbind('click').click(function(){
                cj--;
                cj_rotate(imgClass,cj);
                return false;
            });
        };
        //ie下的旋转具体操作
        function cj_rotate(imgClass,number){
            number = number%4;
            if(number<0)number+=4;
            imgClass.css('filter','progid:DXImageTransform.Microsoft.BasicImage(rotation='+number+')');
            return false;
        };
        //Chrome、Firefox等浏览器下的旋转方法
        function picture_rotate(obj1,obj2,imgClass) {
            var picture_angle = 0;
            obj1.unbind('click').click(function(){
                picture_angle += 90;
                rotate(imgClass, picture_angle);
            });
            obj2.unbind('click').click(function(){
                picture_angle -= 90;
                rotate(imgClass, picture_angle);
                return false;
            });
        };
        //Chrome、Firefox等浏览器下的旋转具体操作
        function rotate(imgClass, picture_angle) {
            imgClass.css('transform', 'rotate(' + picture_angle + 'deg)');
            imgClass.css('-ms-transform', 'rotate(' + picture_angle + 'deg)');
            imgClass.css('-webkit-transform', 'rotate(' + picture_angle + 'deg)');
            imgClass.css('-o-transform', 'rotate(' + picture_angle + 'deg)');
            imgClass.css('-moz-transform', 'rotate(' + picture_angle + 'deg)');
            return false;
        };
    }
})
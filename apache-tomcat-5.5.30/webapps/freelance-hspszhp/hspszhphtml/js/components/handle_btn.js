/**
 * Created by Administrator on 2017/1/12.
 */
$(function () {
    $(window).load();

    //信息项设置按钮点击显示具体设置内容区
    // 给信息设置ul中内容添加序号<i>
    function addUlSerialNumber(o) {
        // var oUl = $(o).children('li').length;
        var num = 1;
        $(o).children('li').each(function () {
            $(this).children('i').text(num++);
        });
    }
    // 点击信息设置按钮展开设置项
    $(document).on('click','.btn-set',function (e) {
        e.stopPropagation();                                //阻止事件冒泡
        if ($(this).hasClass("btn-set-selected")){
            $(this).removeClass("btn-set-selected");
        }else{
            $(this).addClass("btn-set-selected");
            addUlSerialNumber($(".mt-info-show"));
            addUlSerialNumber($(".mt-info-sort"));
            $('.message-alert').show().delay(2000).hide(0);
        }

    });

    // 点击保存、关闭按钮都隐藏信息设置div
    $(document).on('click','.mt-infoset-sure',function (e) {
        e.stopPropagation();
        $('.btn-set').removeClass("btn-set-selected");
    });
    $(document).on('click','.mt-infoset-cancel',function (e) {
        e.stopPropagation();
        $('.btn-set').removeClass("btn-set-selected");
    });

    //信息项设置中信息显示部分选中部分的样式改变
    $(document).on('click','.mt-infoset-lia',function (e) {
        e.stopPropagation();
        $(".mt-info-set").show();
        if ($(this).hasClass("mt-infoset-liaselect")){
            $(this).removeClass("mt-infoset-liaselect");
            $(this).siblings('span').remove();
        }else{
            $(this).addClass("mt-infoset-liaselect");
            //添加置顶，置底按钮
            $(this).after(" <span class='mt-set-bottom'></span> " + "<span class='mt-set-top'></span>");

            // 信息项设置显示部分点击“置顶”图标
            $('.mt-set-top').unbind('click').click(function (e) {
                e.stopPropagation();
                var objUl = $(this).parent('li').parent('ul');
                var onthis = $(this).parent();
                var getUp=onthis.prev();

                if ($(getUp).length == 0)
                {
                    alert("已经是顶级元素，不能上移！");
                    return;
                }
                $(objUl).prepend(onthis);
                addUlSerialNumber($(".mt-info-show"));
            });
            //信息项设置显示部分点击“置底”图标
            $('.mt-set-bottom').unbind('click').click(function (e) {
                e.stopPropagation();
                var objUl = $(this).parent('li').parent('ul');
                var onthis = $(this).parent();
                var getDown=onthis.next();

                if ($(getDown).length == 0)
                {
                    alert("已经是最后一个元素，不能下移");
                    return;
                }
                $(objUl).append(onthis);
                addUlSerialNumber($(".mt-info-show"));
            });
        }
    });

    //信息项设置信息显示部分点击“全选”时的事件
    $(document).on('click','.mt-info-checkall',function (e) {
        e.stopPropagation();
        $(".mt-info-show li").each(function (index) {
            if ($(this).find("span").length == 0){
                $(this).children('a').addClass("mt-infoset-liaselect");
                $(this).append(" <span class='mt-set-bottom'></span> " + "<span class='mt-set-top'></span>");
            }
        });
    });
    //信息项设置信息显示部分点击“清空条件”时的事件
    $(document).on('click','.info-show-empty',function (e) {
        e.stopPropagation();
        $(".mt-info-show li").each(function (index) {
            $(this).children('a').removeClass("mt-infoset-liaselect");
            $(this).find('span').remove();
        });
    });
    //信息项设置信息排序部分点击“清空”时的事件
    $(document).on('click','.info-sort-empty',function (e) {
        e.stopPropagation();
        $(".mt-info-sort li").each(function (index) {
            $(this).children('span').removeClass("mt-radioselect");
        });
    });


    // 信息项设置中的拖拽实现
    $( ".mt-info-show" ).sortable({ update: function() {    addUlSerialNumber($(".mt-info-show")); } });
    $( ".mt-info-show" ).disableSelection();
    $( ".mt-info-sort" ).sortable({ update: function() {    addUlSerialNumber($(".mt-info-sort")); } });
    $( ".mt-info-sort" ).disableSelection();


    $(document).on('mousedown','.mt-info-show .mt-infoset-li',function(){
        // 给将拖拽的元素添加虚线边框
        var that = this;
        $(that).css('border','1px dashed #d9dfe9');
        var arrText = [];
        // 获取原始排序ul中所有的li值 去掉后面的"升降"字眼
        $('.mt-info-sort .mt-infoset-li').each(function() {
            var sortText = $(this).children('a').text();
            // sortText = sortText.substring(0,sortText.length-4);
            arrText.push(sortText.trim());
        });
        var text = $(this).children('a').text().trim();
        var drag_is = true;
        $(this).mousemove(function(){
            if($(this).position().left>parseInt($('.mt-info-show').css('width'))
                &&$(this).position().top>0
                &&$(this).position().top<parseInt($('.mt-info-sort').css('height'))
                &&drag_is && text != ''
                && !contains(arrText,text)
            ){
                var num = arrText.length+1;
                $('.mt-info-sort').append('<li class=\'mt-infoset-li\'><i>'+ num + '</i><a>'+text+'</a>'+
                    '<span class=\'mt-close\'></span> ' +
                    '<span class=\'mt-radio\'>降</span> ' +
                    '<span class=\'mt-radio\'>升</span>' +
                    '</li>');
                drag_is = false;
                text = '';

                //信息设置项排序部分选中升降序的样式改变，只能单选
                $('.mt-radio').unbind('click').click(function (e) {
                    e.stopPropagation();
                    if ($(this).hasClass("mt-radioselect")){
                        $(this).removeClass("mt-radioselect");
                    }else{
                        if ($(this).siblings('span').hasClass("mt-radioselect")){
                            $(this).addClass("mt-radioselect");
                            $(this).siblings('span').removeClass("mt-radioselect");
                        }else {
                            $(this).addClass("mt-radioselect");
                        }
                    }
                });
                //信息设置项排序部分点击删除事件
                $('.mt-close').on('click',function (e) {
                    e.stopPropagation();
                    $(this).parent().remove();
                });
            }
        })
        $(document).on('mouseup',function(){
            $(that).css('border','1px dashed #fff');
            text = '';
        })
    });
    //判断数组arr中是否包含对象obj
    function contains(arr, obj) {
        var i = arr.length;
        while (i--) {
            if (arr[i] === obj) {
                return true;
            }
        }
        return false;
    }

});


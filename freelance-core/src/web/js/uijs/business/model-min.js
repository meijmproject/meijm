/**
 * Created by 6386 on 2016/11/3.
 */
$(function(){
    //利用插件美化滚动条
    $('.nano').nanoScroller({
        preventPageScrolling: true
    });
    $(".nano").nanoScroller();
    //红色关闭点击事件
    $('.close_demol_min').click(function(){
        $('.prompt_box_info_dictionary').hide();
        return false;
    });
    //输入框点击事件，点击后弹出字典模态框
    $('.input-model-min').focus(function(){
        $('.prompt_box_info_dictionary').hide();
        $(this).next('.prompt_box_info_dictionary').show();
        $(window).resize();
    });
    //列表内各个字典td的点击事件,单层模态框点击后关闭，多层模态框点击后未做处理
    $('.prompt_box_info_dictionary table td').click(function(){
        if($(this).html() != '' && $(this).html() != null){
            $('.prompt_box_info_dictionary table td').removeClass('td_selected').addClass('td_default');
            $(this).removeClass('td_default').addClass('td_selected');
            console.log($(this).parents('.prompt_box_info_dictionary').prev('.input-model-min'));
            $(this).parents('.prompt_box_info_dictionary').prev('.input-model-min').val($(this).html());
            if($(this).hasClass('single-deck')){
                setTimeout(function(){
                    $('.prompt_box_info_dictionary').hide();
                },200);
            }
        }else{
            return;
        }
    });
    //搜索图标点击事件，点击后显示搜索输入框
    $('.click_search_img_model_min').click(function(){
        $(this).parent().hide();
        $(this).parent().next('.search_and_back_model_min').show();
    });
    //返回列表图标点击事件，点击后列表
    $('.back_up_model_min').click(function(){
        $(this).parent().hide();
        $(this).parent().prev('.no_search_model_min').show();
    });
    //下面是搜索框和下拉框的切换
    $('.search_input_dictionary').focus(function(){
        $(this).next('.search_ul_dictionary1').show();
        /*$('.search_ul_dictionary2').hide();*/
    });
    $('.search_input_dictionary').blur(function(){
        $('.search_ul_dictionary1').hide();
    });
    $('.search_more_img_dictionary').click(function(){
        $('.search_ul_dictionary1').hide();
        $('.search_ul_dictionary2').show();
    });
    $('.search_ul_dictionary1 li').click(function(){
        $('.search_ul_dictionary1').hide();
        /*$('.search_input_dictionary').val($(this).html);*/
    });
    $('.search_ul_dictionary2 li').click(function(){
        $('.search_ul_dictionary2').hide();
        /*$('.search_input_dictionary').val($(this).html);*/
    });
    //下面是搜索框和下拉框的切换结束
});
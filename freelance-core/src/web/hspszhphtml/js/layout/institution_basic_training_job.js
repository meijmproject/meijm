/**
 * Created by asus on 2016/11/30.
 */
$(function(){
    $('.double_click_modify').dblclick(function(){
       $(this).find('span').hide();
       $(this).find('input').show().val($(this).find('span').html()).focus();
    });
    $('.double_click_modify input').blur(function(){
        $(this).hide();
        $(this).parent().find('span').show().html($(this).val());
    });
    $('.training_job_main_add').click(function(){
        var str  ='<tr class="institution_basic_td_color">';
            str +='<td colspan="4" class="td_has_input"><input type="text"></td>'
            str +='<td colspan="4" class="td_has_input"><input type="text"></td>'
            str +='<td colspan="3">12</td>'
            str +='<td colspan="1"><i class="delete_info training_job_main_delete"></i></td>'
            str +='</tr>'
        $(this).parent().parent().before(str);
        $('.training_job_main').attr('rowspan',Number($('.training_job_main').attr('rowspan'))+1);
        $('.training_job').attr('rowspan',Number($('.training_job').attr('rowspan'))+1);
    });
    $('.training_job_other_add').click(function(){
        var str  ='<tr class="institution_basic_td_color">';
        str +='<td colspan="4" class="td_has_input"><input type="text"></td>'
        str +='<td colspan="4" class="td_has_input"><input type="text"></td>'
        str +='<td colspan="3">12</td>'
        str +='<td colspan="1"><i class="delete_info training_job_other_delete"></i></td>'
        str +='</tr>'
        $(this).parent().parent().before(str);
        $('.training_job_other').attr('rowspan',Number($('.training_job_other').attr('rowspan'))+1);
        $('.training_job').attr('rowspan',Number($('.training_job').attr('rowspan'))+1);
    });
    $(document).on('click','.training_job_main_delete',function(){
        $(this).parent().parent().remove();
        $('.training_job_main').attr('rowspan',$('.training_job_main_delete').length+2);
        $('.training_job').attr('rowspan',$('.training_job_main_delete').length+$('.training_job_other_delete').length+5);
    });
    $(document).on('click','.training_job_other_delete',function(){
        $(this).parent().parent().remove();
        $('.training_job_other').attr('rowspan',$('.training_job_other_delete').length+2);
        $('.training_job').attr('rowspan',$('.training_job_main_delete').length+$('.training_job_other_delete').length+5);

    });
    $(document).on('click','.training_job_reset',function(){
        $(this).parent('td').siblings('td').find('input').val('');
        $(this).parent('td').siblings('.scale').html('0%');
    });
    /*$('.training_job_main_delete').click(function(){
    });
    $('.training_job_other_delete').click(function(){
        $(this).parent().parent().remove();
        $('.training_job_other').attr('rowspan',Number($('.training_job_other').attr('rowspan'))-1);
        $('.training_job').attr('rowspan',Number($('.training_job').attr('rowspan'))-1);
    });*/
});
/*function training_job_main_delete_fn(){

        $(this).parent().parent().remove();
        $('.training_job_main').attr('rowspan',Number($('.training_job_main').attr('rowspan'))-1);
        $('.training_job').attr('rowspan',Number($('.training_job').attr('rowspan'))-1);
};*/
/*
function training_job_other_delete_fn(){
        $(this).parent().parent().remove();
        $('.training_job_other').attr('rowspan',Number($('.training_job_other').attr('rowspan'))-1);
        $('.training_job').attr('rowspan',Number($('.training_job').attr('rowspan'))-1);
}*/

/**
 * Created by asus on 2016/10/17.
 */
$(function(){

//    人员业务信息区操作
    //呈批件工作台表格中行鼠标滑过及点击事件
    $(".people_info tr").mouseover(function(){
        //呈批件待办工作台人员信息区操作按钮为移出
        $(this).find("i.handle_moveout").addClass("handle_moveout_checked");
        //呈批件已办工作台人员信息区操作按钮为打印
        $(this).find("i.handle_print").addClass("handle_print_checked");
        $(this).addClass("hover_checked");
    });
    $(".people_info tr").mouseout(function(){
        var item_checkbox = $(this).find("input[type='checkbox']");
        if(!item_checkbox.is(':checked')){
            $(this).removeClass("hover_checked");
            $(this).find("i").removeClass("handle_moveout_checked");
            $(this).find("i").removeClass("handle_print_checked");
        }
    });
//    unbind防止多次调用click
    $(".people_info tr").unbind("click").click(function(){
        var item_icon =  $(this).find("i");
        var item_checkbox = $(this).find("input[type='checkbox']");
        if(item_checkbox.is(':checked'))
        {
            item_checkbox.prop("checked",false);
            $(this).removeClass("hover_checked");
            item_icon.removeClass("handle_moveout_checked");
            item_icon.removeClass("handle_print_checked");
        }else{
            item_checkbox.prop("checked",true);
            //呈批件待办工作台人员信息区操作按钮为移出
            $(this).find("i.handle_moveout").addClass("handle_moveout_checked");
            //呈批件已办工作台人员信息区操作按钮为打印
            $(this).find("i.handle_print").addClass("handle_print_checked");
            $(this).addClass("hover_checked");
        }
    });
    // 人员全选功能
    $(".all_people_choice").unbind("change").change(function(){
        var item_icon =  $(".people_info").find("i");
        if($(this).prop("checked")){
            $(".people_info").find("input[type='checkbox']").prop("checked",true);
            $(".people_info tr").addClass("hover_checked");
            //呈批件待办工作台人员信息区操作按钮为移出
            $(".people_info").find("i.handle_moveout").addClass("handle_moveout_checked");
            //呈批件已办工作台人员信息区操作按钮为打印
            $(".people_info").find("i.handle_print").addClass("handle_print_checked");
        }else{
            $(".people_info").find("input[type='checkbox']").prop("checked",false);
            $(".people_info tr").removeClass("hover_checked");
            $(".people_info").find("i.handle_moveout").removeClass("handle_moveout_checked");
            $(".people_info").find("i.handle_print").removeClass("handle_print_checked");
        }
    });

    //批件信息区只提供鼠标滑过功能
    $(".instructions_info tr").mouseover(function(){
        $(this).find("i.handle_submit").addClass("handle_submit_checked");
        $(this).find("i.handle_print").addClass("handle_print_checked");
        $(this).find("i.handle_delete").addClass("handle_delete_checked");
        $(this).find("i.handle_recall").addClass("handle_recall_checked");
    });
    $(".instructions_info tr").mouseout(function(){
        $(this).find("i.handle_submit").removeClass("handle_submit_checked");
        $(this).find("i.handle_print").removeClass("handle_print_checked");
        $(this).find("i.handle_delete").removeClass("handle_delete_checked");
        $(this).find("i.handle_recall").removeClass("handle_recall_checked");
    });
    
    //加入新批件模态框
    $(".newjion_instructions tr").mouseover(function(){
        $(this).find("i").addClass("handle_moveout_checked");
    });
    $(".newjion_instructions tr").mouseout(function(){
        $(this).find("i").removeClass("handle_moveout_checked");
    });

    //新建批件模态框，全选功能
    $(".newBuilt_instructions").unbind("change").change(function(){
        if($(this).prop("checked")){
            $(".newHandle_instructions").find("input[type='checkbox']").prop("checked",true);
            $(".newHandle_instructions .people_info tr").addClass("hover_checked");
            $(".newHandle_instructions .people_info tr").find("i").addClass("handle_moveout_checked");
        }else{
            $(".newHandle_instructions .people_info").find("input[type='checkbox']").prop("checked",false);
            $(".newHandle_instructions .people_info tr").removeClass("hover_checked");
            $(".newHandle_instructions .people_info tr").find("i").removeClass("handle_moveout_checked");
        }
    });

    //加入已有批件模态框点击事件
    $(".joinHave_instructions tr").unbind("click").click(function(){
        var item_icon =  $(this).find("i");
        item_icon.removeClass("handle_moveout_checked");
        if(item_icon.attr("class")=="instructions_checked")
        {
            item_icon.attr("class","instructions_check");
            $(this).removeClass("hover_checked");
        }else{
            item_icon.attr("class","instructions_checked");
            $(this).addClass("hover_checked");
        }
        // 只供单选
        var sibling_tr = $(this).siblings('tr');
        $(sibling_tr).each(function(){
           $(this).find("i").attr("class","instructions_check");
           $(this).removeClass("hover_checked");
        })
    });

})
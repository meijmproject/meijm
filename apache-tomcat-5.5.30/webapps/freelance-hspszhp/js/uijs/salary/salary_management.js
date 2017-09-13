/**
 * Created by asus on 2016/10/13.
 */
$(function(){
    //用于交互效果
    (function(){
        var is_section1 = false;
        salary_tbody_bg();
        salary_management_tr_look()
        $(".search_button_id").click(function(){
            console.log("搜索！");
        });
        $(".search_more_span_img").each(function(){
            $(this).click(function(){
                $(this).parent().remove();
            })
        });
        $(".search_more_id").click(function(){
            if(!is_section1)
            {
                $(".section1").show();
                $(".search_more_img_id").addClass("transform_180deg");
                is_section1 = true;
            }
            else
            {
                $(".section1").hide();
                $(".search_more_img_id").removeClass("transform_180deg");
                is_section1 = false;
            }
        });
        function salary_tbody_bg(){
            $(".salary_tbody_id tr:odd").css("background","#f0f5fb");
            $(".salary_tbody_id tr:even").css("background","#fff");
        }
        function salary_management_tr_look(){
            $(".salary_tbody_id tr").click(function(){
                $(".salary_management_add_tr").remove();
                salary_tbody_bg();
                $(this).parent().find("tr td").css("color","#7c7c7c");
                var html = "";
                $(this).find("td").css("color","#004470");
                $(this).css("background","#e7f8ff");
                html +='<tr class="salary_management_add_tr"><td colspan="17">'
                html += '<div class="salary_info_container">';
                html +='<div class="salary_info_content">';
                html +='<button class="salary_info_btn1" id="btn_update" onclick="updateWageInfo()"><img src="img/salary/btn_revise.png">修改</button><button class="salary_info_btn2" id="btn_view" onclick="viewWageInfo()"><img src="img/salary/btn_check.png">查看</button>';
                html +='</div><s><i></i></s></div></td></tr>';
                $(this).after(html);
            });
        }
    })();
    //自执行函数结束
})
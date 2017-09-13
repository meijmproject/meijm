/*
 * 自然减员校验
 */
$(function(){
    $('#saveDicItem').click(function(){
        $('#dicItemForm').submit();
    });

    $("#dicItemForm").validate({
        rules:{
        	dicItemCode: {
            required: true
          },
          dicItemName: {
            required: true
          },
          displayOrder: {
          	isNum: true
          }
        },
        messages: {
        	dicItemCode: {
        		required: "请输入字典编码！"
        	},
        	dicItemCode: {
            required: "请输入字典名称！"
          },
          displayOrder: {
          	isNum: "请确认排序为数字！"
          }
        },
        errorLabelContainer: $("#dicItemForm div.md-error"),
        wrapper: 'span',
        submitHandler: function (form)
        {
        	var data = $("#dicItemForm").serializeArray();
        	data.push({
        		name: 'viewName',
        		value: data[2].value
        	});
        	$.ajax({
                url : $('#dicItemForm').attr("action"),
                data :  data,
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                    if (data.success) {
                    	Widget.close();
                      worktop.form.goQuery();
                    }
                    else
                    {
                    	$("#dicItemForm div.md-error").css('display','block');
                      if($("#dicItemForm div.md-error .back-error").length>0) {
                      	$("#dicItemForm div.md-error .back-error").html(data.message);
                      }else {
                      	$("#dicItemForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                      }
                    }
                }
            });
        }
    });
});
//验证输入值为数字
jQuery.validator.addMethod("isNum", function(value, element) {
	var num = /^[0-9\.]*$/;
    return this.optional(element) || (num.test(value));
}, "请输入正确的数字");
//验证输入值为10位整数+2位小数的浮点数
jQuery.validator.addMethod("isFloat", function(value, element) {
	var num = /^[0-9]{0,10}(\.[0-9]{0,2})?$/;
    return this.optional(element) || (num.test(value));
}, "请输入整数部分最长10位，小数部分最长2位的数字");

jQuery.validator.addMethod("compareDate", function(value, element){
	var startDate = $("#effDateStr").val().replace(/-/g,'/'),
    	endDate = $("#expDateStr").val().replace(/-/g,'/');
		startDate = new Date(parseInt(Date.parse(startDate),10));
		endDate = new Date(parseInt(Date.parse(endDate),10));
    return startDate<=endDate;
}, "生效日期不能早于生效日期!");
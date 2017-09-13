/*
 * 自然减员校验
 */
$(function(){
    $('#saveMessageBoard').click(function(){
        $('#messageBoardForm').submit();
    });

    $("#messageBoardForm").validate({
        rules:{
      		title: {
            required: true
          },
          effDateStr: {
            required: true
          },
          expDateStr: {
            required: true,
            compareDate: true
          },
          content: {
          	required: true
          }
        },
        messages: {
        	title: {
        		required: "请输入标题！"
        	},
          effDateStr: {
            required: "请输入生效日期！"
          },
          expDateStr: {
            required: "请输入失效日期！"
          },
          content: {
          	required: "请输入内容！"
          }
        },
        errorLabelContainer: $("#messageBoardForm div.md-error"),
        wrapper: 'span',
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#messageBoardForm').attr("action"),
                data :  $("#messageBoardForm").serializeArray(),
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
                    	$("#messageBoardForm div.md-error").css('display','block');
                      if($("#messageBoardForm div.md-error .back-error").length>0) {
                      	$("#messageBoardForm div.md-error .back-error").html(data.message);
                      }else {
                      	$("#messageBoardForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
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
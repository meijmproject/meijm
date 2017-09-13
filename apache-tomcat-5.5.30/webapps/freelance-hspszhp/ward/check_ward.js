$(function(){
	$("#saveWard").click(function() {
		$('#cfWardForm').submit();
	});
    $("#cfWardForm").validate({
        rules:{
        	deptOid:{
                required: true
            },
    		waedType:{
    			required: true
    		},
    		bedNum: {
            	isNum: 1
            }
        },
        messages: {
        	deptOid: {
                required: "请选择病区科室"
            },
            waedType: {
                required: "请选择病区类型"
            },
            bedNum: {
                required: "请确认床位数为正整数"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#cfWardForm div.md-error"),
        wrapper: "span",
        submitHandler: function( form) {
        	$.ajax({
                url : $('#cfWardForm').attr("action"),
                data :  $("#cfWardForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                    if (data.success) {
                    	Widget.close();
                    }
                    else {
                    	$("#cfWardForm div.md-error").css('display','block');
                      if($("#cfWardForm div.md-error .back-error").length>0) {
                      	$("#cfWardForm div.md-error .back-error").html(data.message);
                      }else {
                      	$("#cfWardForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                      }
                    }
                    return;
                }
            });
        }
    });
});
//验证输入值为数字
jQuery.validator.addMethod("isNum", function(value, element) {
	var num = /^[0-9]*$/;
    return this.optional(element) || (num.test(value));
}, "请输入正整数");
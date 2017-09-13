/*
 * 自然减员校验
 */
$(function(){
    $('#saveDeathInfo').click(function(){
        $('#deathInfoForm').submit();
    });

    $("#deathInfoForm").validate({
        rules:{
        	deathDateStr: {
                required: true
            },
            deathType: {
                required: true
            },
            deathReason: {
                required: true
            },
            smartMoney: {
            	isNum: 1,
            	isFloat: true,
            	maxlength: 13
            },
            funeralMoney: {
            	isNum: 1,
            	isFloat: true,
            	maxlength: 13
            }
        },
        messages: {
        	deathDateStr: {
                required: "请选择死亡日期，"
            },
            deathType: {
                required: "请选择死亡原因类别，"
            },
            deathReason: {
                required: "请输入死亡原因，"
            },
            smartMoney: {
            	isNum: '请确认抚恤金为数字',
            	isFloat: '请确认抚恤金的整数部分不超过10位且小数部分不超过2位',
            	maxlength: '请确认抚恤金的整数部分不超过10位且小数部分不超过2位'
            },
            funeralMoney: {
            	isNum: '请确认丧葬费为数字',
            	isFloat: '请确认丧葬费的整数部分不超过10位且小数部分不超过2位',
            	maxlength: '请确认抚恤金的整数部分不超过10位且小数部分不超过2位'
            }
        },
        errorLabelContainer: $("#deathInfoForm div.md-error"),
        wrapper: 'span',
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#deathInfoForm').attr("action"),
                data :  $("#deathInfoForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                    if (data.success) {
                        $.fn.close_popdown();
                        var url_id=$('#url_id').val();
                        var url_personoid=$('#personOid').val();
                        //findPageUrl(url_id,url_personoid);
                        $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                    }
                    else
                    {
	                    	$("#deathInfoForm div.md-error").css('display','block');
		                    if($("#deathInfoForm div.md-error .back-error").length>0) {
		                    	$("#deathInfoForm div.md-error .back-error").html(data.message);
		                    }else {
		                    	$("#deathInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
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

//初始化
$(document).ready(function(){
    //点击保存提交
    $('#saveRetrieInfo').click(function(){
        $('#retrieInfoForm').submit();
    });
    //校验
    $("#retrieInfoForm").validate({
        rules:{
        	retrieTypeCode:{
                required: true   //离退类别
            },
            retireAllowanceRatio:{
                isNum: 1,
                isFloat: true
            },
            retireFeeRatio:{
                isNum: 1,
                isFloat: true
            },
            specialContributionRatio:{
                isNum: 1,
                isFloat: true
            },
            returnedOverseasChiScale:{
            	isNum: 1,
                isFloat: true
            }
        },
        messages: {
        	retrieTypeCode: {
                required: "请选择离退类别，"
            },
            retireAllowanceRatio: {
            	isNum: "请确认离退休补助比例为数字",
            	isFloat: '请确认离退休补助比例的整数部分不超过10位且小数部分不超过2位，'
            },
            retireFeeRatio: {
            	isNum: "请确认离退休费比例为数字",
            	isFloat: '请确认离退休费比例的整数部分不超过10位且小数部分不超过2位，'
            },
            specialContributionRatio: {
            	isNum: "请确认特殊贡献比例为数字",
            	isFloat: '请确认特殊贡献比例的整数部分不超过10位且小数部分不超过2位，'
            },
            returnedOverseasChiScale: {
            	isNum: "请确认归侨加发比例为数字",
            	isFloat: '请确认归侨加发比例的整数部分不超过10位且小数部分不超过2位，'
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#retrieInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
         {
        	$.ajax({
                url : $('#retrieInfoForm').attr("action"),
                data :  $("#retrieInfoForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                    if (data.success) {
                        $.fn.close_popdown();
                        var url_id=$('#url_id').val();
                        var url_personoid=$('#personOid').val();
                        if(url_id==''||url_personoid==''){
                        	Widget.close();
                        }else{
                        	$('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                        }
                    }
                    else
                    {
                        $("#retrieInfoForm div.md-error").css('display','block');
                        if($("#retrieInfoForm div.md-error .back-error").length>0) {
                        	$("#retrieInfoForm div.md-error .back-error").html(data.message);
                        }else {
                        	$("#retrieInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
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
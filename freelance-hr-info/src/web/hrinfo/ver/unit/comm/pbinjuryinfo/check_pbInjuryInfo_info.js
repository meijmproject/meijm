/*
 * 工伤信息校验
 */
$(function(){
    $('#saveInjuryInfo').click(function(){
        $('#injuryInfoForm').submit();
    });

    $("#injuryInfoForm").validate({
        rules:{
        	hurtDateStr: {
                required: true
            },
            hurtLevel: {
                required: true
            },
            judgeOrgan: {
                required: true
            }
        },
        messages: {
        	hurtDateStr: {
                required: "请选择受伤日期"
            },
            hurtLevel: {
                required: "请选择伤残等级"
            },
            judgeOrgan: {
                required: "请输入认定单位"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#injuryInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#injuryInfoForm').attr("action"),
                data :  $("#injuryInfoForm").serializeArray(),
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
                    	$("#injuryInfoForm div.md-error").css('display','block');
	                    if($("#injuryInfoForm div.md-error .back-error").length>0) {
	                    	$("#injuryInfoForm div.md-error .back-error").html(data.message);
	                    }else {
	                    	$("#injuryInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                    }
                    }
                }
            });
        }
    });
});

/*
 * 执业注册信息校验
 */
$(function(){
    $('#saveCertificateInfo').click(function(){
        $('#CertificateInfoForm').submit();
    });

    $("#CertificateInfoForm").validate({
        rules:{
        	certificateNo: {
                required: true
            },
            cancelFlag: {
            	required: true
            }
        },
        messages: {
        	certificateNo: {
                required: "请填写执业证书编号"
            },
            cancelFlag: {
                required: "请选择是否注销"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#CertificateInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#CertificateInfoForm').attr("action"),
                data :  $("#CertificateInfoForm").serializeArray(),
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
                    	$("#CertificateInfoForm div.md-error").css('display','block');
	                    if($("#CertificateInfoForm div.md-error .back-error").length>0) {
	                    	$("#CertificateInfoForm div.md-error .back-error").html(data.message);
	                    }else {
	                    	$("#CertificateInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                    }
                    }
                }
            });
        }
    });
});

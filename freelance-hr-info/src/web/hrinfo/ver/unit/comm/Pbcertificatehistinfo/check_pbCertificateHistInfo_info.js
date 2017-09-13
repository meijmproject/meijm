/*
 * 执业注册历史信息校验
 */
$(function(){
    $('#saveCertificateHistInfo').click(function(){
        $('#CertificateHistInfoForm').submit();
    });

    $("#CertificateHistInfoForm").validate({
        rules:{
        	
        },
        messages: {
        	
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#CertificateHistInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#CertificateHistInfoForm').attr("action"),
                data :  $("#CertificateHistInfoForm").serializeArray(),
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
		                  	$("#CertificateHistInfoForm div.md-error").css('display','block');
		                    if($("#CertificateHistInfoForm div.md-error .back-error").length>0) {
		                    	$("#CertificateHistInfoForm div.md-error .back-error").html(data.message);
		                    }else {
		                    	$("#CertificateHistInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
		                    }
                    }
                }
            });
        }
    });
});

/**
 * 工作经历校核
 */
$(function(){    
    $('#saveResumeInfo').click(function(){
    	$('#resumeInfoForm').submit();
    });
    
    $("#resumeInfoForm").validate({
    	rules:{
    		startDateStr:{
                 required: true
             },
             endDateStr: {
            	 required: true
             },
             unit: {
                 required: true
             }
    	},
    	messages: {
    		startDateStr: {
                required: "请选择开始日期!"
            },
            endDateStr: {
                required: "请选择截止日期!"
            },
            unit: {
                required: "请填写工作单位!"
            }
    	},
    	errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#resumeInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#resumeInfoForm').attr("action"),
                data :  $("#resumeInfoForm").serializeArray(),
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
                        $("#resumeInfoForm div.md-error").css('display','block');
                        if($("#resumeInfoForm div.md-error .back-error").length>0) {
                        	$("#resumeInfoForm div.md-error .back-error").html(data.message);
                        }else {
                        	$("#resumeInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                        }
                    }
                }
            });
        }    
    });
});

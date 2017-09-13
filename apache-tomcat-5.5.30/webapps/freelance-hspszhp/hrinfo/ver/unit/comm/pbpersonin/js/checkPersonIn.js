/*
 * 家庭成员与社会关系校验
 */
$(function(){
    //校验
    $('#savePersonIn').click(function(){
        $('#pbPersonInForm').submit();
    });

    $("#pbPersonInForm").validate({
        rules:{
            entryCurrentUnitType: {
                required: true
            },
            entryCurrentUnitDateStr: {
                required: true
            }
        },
        messages: {
            entryCurrentUnitType: {
                required: "请选择进入单位类型"
            },
            entryCurrentUnitDateStr: {
                required: "请选择进入单位时间"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#pbPersonInForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#pbPersonInForm').attr("action"),
                data :  $("#pbPersonInForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                	try {
                		if (data.success) {
                            $.fn.close_popdown();
                            var url_id=$('#url_id').val();
                            var url_personoid=$('#personOid').val();
                            $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                        }
                        else
                        {
                        	$("#pbPersonInForm div.md-error").css('display','block');
  		                    if($("#pbPersonInForm div.md-error .back-error").length>0) {
  		                    	$("#pbPersonInForm div.md-error .back-error").html(data.message);
  		                    }else {
  		                    	$("#pbPersonInForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
  		                    }
                        }
                		return;
                	} catch(e) {
                	}
                	$("#popdown-dialog").html(data);
                    $("#formSyPost div.md-error").css('display','block');
                }
            });
        }
    });
});

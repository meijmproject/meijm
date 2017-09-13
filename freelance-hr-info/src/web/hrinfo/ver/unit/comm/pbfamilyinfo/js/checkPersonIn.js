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
        errorLabelContainer: $("#pbPersonInForm div.modal-wrong"),
        wrapper: "li",
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
                            $("#pbPersonInForm div.modal-wrong").css('display','block');
                            $(".wroglist").html(data.message);
                        }
                		return;
                	} catch(e) {
                	}
                	$("#popdown-dialog").html(data);
                    $("#formSyPost div.modal-wrong").css('display','block');
                }
            });
        }
    });
});

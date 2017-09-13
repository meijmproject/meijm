/*
 * 人员信息管理 - 减员 - 离职
 */
$(function(){
    //校验
    $('#savePersonOut').click(function(){
        $('#pbPersonOutForm').submit();
    });

    $("#pbPersonOutForm").validate({
        rules:{
            personOutType: {
                required: true
            }
        },
        messages: {
            personOutType: {
                required: "请选择减员类型"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#pbPersonOutForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#pbPersonOutForm').attr("action"),
                data :  $("#pbPersonOutForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                	try {
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
                            $("#pbPersonOutForm div.md-error").css('display','block');
                            if($("#pbPersonOutForm div.md-error .back-error").length>0) {
                            	$("#pbPersonOutForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#pbPersonOutForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
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

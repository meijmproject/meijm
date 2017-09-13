/*
 * 家庭成员与社会关系校验
 */
$(function(){
    //校验
    $('#saveFamily').click(function(){
        $('#familyInfoForm').submit();
    });

    $("#familyInfoForm").validate({
        rules:{
            name: {
                required: true
            },
            relationshipName: {
                required: true
            },
            birthdayStr: {
                required: true
            },
            politicsVisage: {
                required: true
            }
        },
        messages: {
            name: {
                required: "请输入姓名,"
            },
            relationshipName: {
                required: "请选择与本人关系,"
            },
            birthdayStr: {
                required: "请选择出生日期,"
            },
            politicsVisage: {
                required: "请选择政治面貌"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#familyInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#familyInfoForm').attr("action"),
                data :  $("#familyInfoForm").serializeArray(),
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
                            $("#familyInfoForm div.md-error").css('display','block');
                            if($("#familyInfoForm div.md-error .back-error").length>0) {
                            	$("#familyInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#familyInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
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
/**
 * 身份证号码验证及出生日期截取
 */
function validateIdNo(obj)
{
	var idType = document.getElementsByName('idType')[0].value;
	if(idType=="1" && obj)
	{
		//1.身份证号码验证
		if(!validIdCodeNoNullTishi(obj.value)) 
			{
			$('#idNo').val("");
			return;
			}
			
		//2.根据身份证号码截取出生日期
		document.getElementsByName('birthdayStr')[0].value = getBirthdatByIdNo(obj.value);
	}
}



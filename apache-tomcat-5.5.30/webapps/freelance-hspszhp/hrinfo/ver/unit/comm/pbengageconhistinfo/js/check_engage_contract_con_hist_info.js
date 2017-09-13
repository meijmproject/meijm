/*
 * 合同历史信息校验
 */
$(function(){
    //校验
    $('#saveEngageConHistInfo').click(function(){
    	contractNoFlag();
        $('#engageConHistInfoForm').submit();
    });
    $.validator.addMethod("checkContractDate", function(value, element){
        var contractBegin = $('#contractBeginStr').val();
    	var contractEnd = $('#contractEndStr').val();
        var reg = new RegExp('-','g');
        contractBegin = contractBegin.replace(reg,'/');//正则替换
        contractEnd = contractEnd.replace(reg,'/');
        contractBegin = new Date(parseInt(Date.parse(contractBegin),10));
        contractEnd = new Date(parseInt(Date.parse(contractEnd),10));
        if(contractBegin>contractEnd){
            return false;
        }else{
            return true;
        }

    }, "合同截止时间不能早于合同开始时间");
    $("#engageConHistInfoForm").validate({
    	/*onfocusin: function(element)
    	{
    		$(element).valid();
    	},*/
    	
        rules:{
            contractType: {
                required: true
            },
            changeType: {
                required: true
            },
            contractBeginStr: {
                required: true
            },
            contractEndStr:{
            	checkContractDate:function(){
            		contractEndStr = $("#contractEndStr").val();
            		if(contractEndStr!=''){
            			return true;
            		}
            		return false;
            	}
            }
        },
        messages: {
            contractType: {
                required: "请选择合同签订方式,"
            },
            changeType: {
                required: "请选择合同类型,"
            },
            contractBeginStr: {
                required: "请选择合同开始时间,"
            },
            contractEndStr:{
            	checkContractDate:"合同截止时间不能早于合同开始时间"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#engageConHistInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#engageConHistInfoForm').attr("action"),
                data :  $("#engageConHistInfoForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                	try {
                		if (data.success) {
                            $.fn.close_popdown();
                            var url_id=$('#url_id').val();
                            var url_personoid=$('#personOid').val();
                            //findPageUrl(url_id,url_personoid);
                            $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                        }
                        else
                        {
                            $("#engageConHistInfoForm div.md-error").css('display','block');
                            if($("#engageConHistInfoForm div.md-error .back-error").length>0) {
                            	$("#engageConHistInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#engageConHistInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                		return;
                	} catch(e) {
                	}
                	$("#popdown-dialog").html(data);
                    $("#engageConHistInfoForm div.md-error").css('display','block');
                }
            });
        }
    });
});

/*一期暂时不要这些规则
 * function contractTypeChange()
{
    var contractType = $("#contractType").val();//合同签订方式
    //=固定期限时，合同结束日期必填，否则不填
    if(contractType=='1')
    {
        $("#contractEndDiv").css('display','block');
        $("#contractEndNone").css('display','none');
    }
    else
    {
        $("#contractEndStr").val('');
        $("#contractEndDiv").css('display','none');
        $("#contractEndNone").css('display','block');
    }
}*/
function contractNoFlag(){
	var contractNo = $("#contractNo").val();
	if(contractNo == preContractNo)
		{
		$("#contractNoFlag").val(false);
		}else{$("#contractNoFlag").val(true);}
}
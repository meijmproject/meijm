 /*
   合同信息校验
  */ 
$(function(){
    //校验
    $('#saveEngageContractInfo').click(function(){
    	/*checkContractDate1();
    	checkContractDate2();*/
    	//checkProbationDate();
    	contractNoFlag();
    	statusFlag();
        $('#engageContractInfoForm').submit();
    });
    $.validator.addMethod("checkProbationDate", function(value, element){
        var probationBegin = $('#probationBeginStr').val();
    	var probationEnd = $('#probationEndStr').val();
        var reg = new RegExp('-','g');
        probationBegin = probationBegin.replace(reg,'/');//正则替换
        probationEnd = probationEnd.replace(reg,'/');
        probationBegin = new Date(parseInt(Date.parse(probationBegin),10));
        probationEnd = new Date(parseInt(Date.parse(probationEnd),10));
        if(probationBegin>probationEnd){
            return false;
        }else{
            return true;
        }

    }, "试用期结束时间不能早于试用期开始时间");
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
    $("#engageContractInfoForm").validate({
    	/*onfocusout: function(element)
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
            	required: true,
            	checkContractDate:function(){
            		contractEndStr = $("#contractEndStr").val();
            		if(contractEndStr!=''){
            			return true;
            		}
            		return false;
            	}
            },
            probationBeginStr: {
                required: function(){
                	var probationFlag = $("#probationFlag").val();//是否存在试用期
                	if(probationFlag=='1'){
                		
                		return true;
                	}
                	return false;
                }
            },
            probationEndStr: {
            	checkProbationDate: true,
                required: function(){
                	var probationFlag = $("#probationFlag").val();//是否存在试用期
                	if(probationFlag=='1'){
                		return true;
                	}
                	return false;
                }
                
            },
            status: {
                required: true
            }
        },
        messages: {
            contractType: {
                required: "请选择合同签订方式"
            },
            changeType: {
                required: "请选择合同类型"
            },
            contractBeginStr: {
                required: "请选择合同开始时间"
            },
            contractEndStr:{
            	required: "请选择合同截止时间",
            	checkContractDate:"合同截止时间不能早于合同开始时间"
            },
            probationBeginStr: {
                required: "是否存在试用期为是时，试用期开始时间必填"
            },
            probationEndStr: {
                required: "是否存在试用期为是时，试用期结束时间必填",
                checkProbationDate: "试用期结束时间不能早于试用期开始时间"
            },
            status: {
                required: "请选择合同状态"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#engageContractInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#engageContractInfoForm').attr("action"),
                data :  $("#engageContractInfoForm").serializeArray(),
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
                            $("#engageContractInfoForm div.md-error").css('display','block');
                            if($("#engageContractInfoForm div.md-error .back-error").length>0) {
                            	$("#engageContractInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#engageContractInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                		return;
                	} catch(e) {
                	}
                	$("#popdown-dialog").html(data);
                    $("#engageContractInfoForm div.md-error").css('display','block');
                }
            });
        }
    });
});

function probationChange()
{
    var probationFlag = $("#probationFlag").val();//是否存在试用期
    //为否时：
    if(probationFlag=='0')
    {
    	$("#probationBeginStr").val('');
        $("#probationEndStr").val('');
        $("#probationDiv").css('display','none');
         $("#probationDivE").css('display','none');
        $("#probationBeginB").css('display','none');
        $("#probationBegin").css('display','none');
        $("#probationEndB").css('display','none');
        $("#probationEnd").css('display','none');
        /*$("#probationBeginB").css('display','none');
        $("#probationEndDiv").css('display','none');
        $("#probationEndDiv1").css('display','none');*/
    }else if(probationFlag=='1')
    {
        $("#probationDiv").css('display','block');
         $("#probationDivE").css('display','block');
        $("#probationBeginB").css('display','block');
        $("#probationEndB").css('display','block');
        $("#probationBegin").css('display','none');
        $("#probationEnd").css('display','none');
    }
    else
    {
    	 $("#probationDiv").css('display','block');
    	 $("#probationDivE").css('display','block');
         $("#probationBeginB").css('display','none');
         $("#probationEndB").css('display','none');
         $("#probationBegin").css('display','block');
         $("#probationEnd").css('display','block');
    }
};
/*一期暂时不要这些规则
function statusChange()
{
    var status = $("#status").val();//合同状态
    //=解除时，实际结束日期必填，否则不bi填
    if(status=='2')
    {
        $("#contractEndActualDiv").css('display','block');
        $("#contractEndActualNone").css('display','none');
    }
    else
    {
        $("#contractEndActualStr").val('');
        $("#contractEndActualDiv").css('display','none');
        $("#contractEndActualNone").css('display','block');
    }
};
function contractTypeChange()
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
};*/
function contractNoFlag(){
	var contractNo = $("#contractNo").val();
	if(contractNo == preContractNo)
		{
		$("#contractNoFlag").val(false);
		}else{$("#contractNoFlag").val(true);}
}
function statusFlag(){
	var status = $("#status").val();
	if(status == preStatus)
		{
		$("#statusFlag").val(false);
		}else{$("#statusFlag").val(true);}
}
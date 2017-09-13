
//初始化
$(document).ready(function(){
    //点击保存提交
    $('#savePolitic').click(function(){
        $('#politicInfoForm').submit();
    });
 
    $.validator.addMethod("compareDate1", function (value, element){return compareDate("joinPoliticDateStr", "probationDateStr");}, "转正时间不能早于参加党派时间!");
//    $.validator.addMethod("compareDate2", function (value, element){return isBeforeNow("probationDateStr");}, "转正时间不能晚于今天");
//    $.validator.addMethod("compareDate3", function (value, element){return isBeforeNow("joinPoliticDateStr");}, "参加党派时间不能晚于今天");
    $.validator.addMethod("compareDate4", function (value, element){return isBeforeNow("outPartyDateStr");}, "离党日期不能晚于今天");
   
    $.validator.addMethod("compareDate5", function (value, element){return compareDate("joinPoliticDateStr", "outPartyDateStr");}, "离党日期不能早于参加党派时间!");
    $.validator.addMethod("compareDate6", function (value, element){return compareDate("probationDateStr", "outPartyDateStr");}, "离党日期不能早于转正时间!");
    //校验
    $("#politicInfoForm").validate({
        rules:{
        	politicStatusCode:{
                required: true   //离退类别
            },
            joinPoliticDateStr:{
            	required:function(){
            		var politicStatusCode = $("#politicStatusCode").val();  //政治面貌
            		if(politicStatusCode=='01'||politicStatusCode=='02'||politicStatusCode=='08'||politicStatusCode=='09')
                    {
                        return true;
                    }
            		return false;
            	},
//            	compareDate3:true
            },
    		unitOfJoinParty:{
    			maxlength:100
    		},
    		introducer:{
    			maxlength:100
    		},
    		probationDateStr:{
//    			compareDate2:true,
    			compareDate1:true
    		},
    		outPartyDateStr:{
     			compareDate4:true,
    			compareDate5:true,
    			compareDate6:true
    		},
    		abnormityReason:{
    			maxlength:1000
    		},
    		remark:{
    			maxlength:1000
    		}
        },
        messages: {
        	politicStatusCode: {
                required: "请选择政治面貌!"
            },
            joinPoliticDateStr:{
            	required: "请选择入党日期!",
//            	compareDate3:"参加党派时间不能晚于今天"
            },
			unitOfJoinParty:{
				maxlength:"参加党派时所在单位不能超过100字符!"
			},
    		introducer:{
    			maxlength:"介绍人不能超过100字符!"
    		},
    		probationDateStr:{
//    			compareDate2:"转正时间不能晚于今天",
    			compareDate1:"转正时间不能早于参加党派时间!"
    		},
    		abnormityReason:{
    			maxlength:"异常事项原因不能超过1000字符!"
    		},
    		outPartyDateStr:{
    			compareDate4:"离党日期不能晚于今天",
    			
    			compareDate5:"离党日期不能早于参加党派时间!",
    			compareDate6:"离党日期不能早于转正时间!",
    		},
			remark:{
				maxlength:"备注不能超过1000字符"
			}
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#politicInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
         {
        	$.ajax({
                url : $('#politicInfoForm').attr("action"),
                data :  $("#politicInfoForm").serializeArray(),
                dataType : 'json',
                type : 'post',
                async : false,
                success : function(data) {
                    if (data.success) {
                        $.fn.close_popdown();
                        var url_id=$('#url_id').val();
                        var url_personoid=$('#personOid').val();
//                        var url_politicOid=$('#politicOid').val();
                        //findPageUrl(url_id,url_personoid);
                        var basePerosnDivId = $('#center').find('div:first').attr('id');
                        $('#'+basePerosnDivId).load($('#'+basePerosnDivId).attr('url'),{personOid:url_personoid,Id:basePerosnDivId});
                        $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                    }
                    else
                    {
                    	$("#politicInfoForm div.md-error").css('display','block');
	                    if($("#politicInfoForm div.md-error .back-error").length>0) {
	                    	$("#politicInfoForm div.md-error .back-error").html(data.message);
	                    }else {
	                    	$("#politicInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                    }
                    }
                }
            });
        }
    });
});
function changePolitic(politicStatusCode)
{
	if(politicStatusCode=='01'||politicStatusCode=='02'||politicStatusCode=='08'||politicStatusCode=='09'){
        $("#Required_flag").html("* ");
    }else{
    	$("#Required_flag").html("");
	}
}

//比较时间、
function compareDate(start,end){
	var startDate = $("#"+start).val();  //
	var grantDate = $("#"+end).val();  //
	var reg = new RegExp('-','g');
    startDate = startDate.replace(reg,'/');//正则替换
    grantDate = grantDate.replace(reg,'/');
    startDate = new Date(parseInt(Date.parse(startDate),10));
    grantDate = new Date(parseInt(Date.parse(grantDate),10));
    
    if(startDate>grantDate){
        return false;
    }else{
        return true;
    }
}

////是否早于今天
function isBeforeNow(date){
	var date = $("#"+date).val();
	if(date.length>0){
		var reg = new RegExp('-','g');
	    date = date.replace(reg,'/');//正则替换
	    now = new Date();
	    date = new Date(parseInt(Date.parse(date),10));
	    
	    if(date<now){
	        return true;
	    }else{
	        return false;
	    }
	}else{
		return true;
	}
}

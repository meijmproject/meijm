$(document).ready(function(){
		    //校验
//	alert(111);
		    $('#punishmentSave').click(function(){
		        $('#punishmentInfoForm').submit();
		    });

//		    $.validator.addMethod("compareDate1", function (value, element){return isBeforeNow("punishmentDateStr");}, "处分批准日期不能晚于今天");
		    $.validator.addMethod("compareDate2", function (value, element){return compareDate("punishmentDateStr", "punishmentEndDateStr");}, "处分截止日期不能早于处分批准日期");
		    
		    $("#punishmentInfoForm").validate({
		        rules:{
		        	punishmentName: {
		                required: true,
		                maxlength:100
		            },
		            punishmentReason: {
		                required: true,
		                maxlength:500
		            },
		            punishmentApprovalUnit: {
		                required: true,
		                maxlength:100
		            },
		            punishmentDateStr: {
		                required: true,
//		                compareDate1:true
		            },
		            punishmentEndDateStr:{
		            	compareDate2:true
		            },
		            isCancalPunishment: {
		                required: true
		            },
		            punishmentMonth:{
		            	min:0
		            },
		            remark:{
		            	maxlength:1000
		            }
		        },
		        messages: {
		        	punishmentName: {
		                required: "请填写处分名称!",
		                maxlength:"处分名称不能超过100字符!"
		            },
		            punishmentReason: {
		                required: "请选择处分原因!",
		                maxlength:"处分原因不能超过500字符!"
		            },
		            punishmentApprovalUnit: {
		                required: "请填写处分批准机关名称!",
		                maxlength:"处分批准机关名称不能超过100字符!"
		            },
		            punishmentDateStr: {
		                required: "请选择处分批准日期!",
//		                compareDate1:"处分批准日期不能晚于今天"
		            },
		            punishmentEndDateStr:{
		            	compareDate2:"处分截止日期不能早于处分批准日期!"
		            },
		            isCancalPunishment: {
		                required: "请选择处分是否撤销标识!"
		            },
		            punishmentMonth:{
		            	min:"请输入正确的数字!"
		            },
		            remark:{
		            	maxlength:"备注信息不能超过1000字符!"
		            }
		        },
		        errorPlacement: function (error, element)
		        {
		        	 error.appendTo(element.parents("div:first"));
		        },
		        errorLabelContainer: $("#punishmentInfoForm div.md-error"),
		        wrapper: "span",
		        submitHandler: function (form)
		        {
		            $.ajax({
		                url : $('#punishmentInfoForm').attr("action"),
		                data :  $("#punishmentInfoForm").serializeArray(),
		                dataType : 'json',
		                type:'POST',
		                async : false,
		                success : function(data) {
		                //	try{
		                	//	data = $.parseJSON(data);
		                    if (data.success) {
		                        $.fn.close_popdown();
		                        var url_id=$('#url_id').val();
		                        var url_personoid=$('#personOid').val();
		                        $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
		                   // }
		                	}
		                	//catch(e){}
		                    else
		                    {
		                    	$("#punishmentInfoForm div.md-error").css('display','block');
			                    if($("#punishmentInfoForm div.md-error .back-error").length>0) {
			                    	$("#punishmentInfoForm div.md-error .back-error").html(data.message);
			                    }else {
			                    	$("#punishmentInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
			                    }
		                    }
		                }
		            });
		        }
		    });
		});
 
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
//function isBeforeNow(date){
//	var date = $("#"+date).val();
//	if(date.length>0){
//		var reg = new RegExp('-','g');
//	    date = date.replace(reg,'/');//正则替换
//	    now = new Date();
//	    date = new Date(parseInt(Date.parse(date),10));
//	    
//	    if(date<now){
//	        return true;
//	    }else{
//	        return false;
//	    }
//	}else{
//		return true;
//	}
//}
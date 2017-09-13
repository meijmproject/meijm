


//初始化
$(document).ready(function(){
    // 点击保存提交
		    $('#saveReward').click(function(){
		        $('#rewardInfoForm').submit();
		    });
//		    $.validator.addMethod("compareDate1", function (value, element){return isBeforeNow("rewardDateStr");}, "奖励批准日期不能晚于于今天");
		    
		    $("#rewardInfoForm").validate({
		        rules:{
		        	rewardName: {
		                required: true,
		                maxlength:50
		            },
		            rewardTypeCode: {
		                required: true
		            },
		            honourLevel: {
		                required: true
		            },
		            encouragementReason:{
		            	maxlength:500
		            },
		            rewardUnit: {
		                required: true,
		                maxlength:100
		            },
		            rewardDateStr: {
		                required: true,
//		                compareDate1:true
		            },
		            remark:{
		            	maxlength:1000
		            }
		        },
		        messages: {
		        	rewardName: {
		                required: "请填写奖励名称!",
		                maxlength:"奖励名称不能超过100字符!"
		            },
		            rewardTypeCode: {
		            	required: "请选择奖励类别!"
		            },
		            honourLevel:{
		            	required:"请选择授予荣誉称号级别!"
		            },
		            encouragementReason:{
		            	maxlength:"奖励原因不能超过500字符!"
		            },
		            rewardUnit: {
		                required: "请填写奖励批准单位!",
		                maxlength:"奖励批准单位不能超过100字符!"
		            },
		            rewardDateStr: {
		                required: "请填写奖励批准日期!",
//		                compareDate1:"奖励批准日期不能晚于于今天"
		            },
		            remark:{
		            	maxlength:"备注信息不能超过1000字符!"
		            }
		           
		        },
		        errorPlacement: function (error, element)
		        {
		            error.appendTo(element.parents("div:first"));
		        },
		        errorLabelContainer: $("#rewardInfoForm div.md-error"),
		        wrapper: "span",
		        submitHandler: function (form)
		        {
		            $.ajax({
		                url : $('#rewardInfoForm').attr("action"),
		                data :  $("#rewardInfoForm").serializeArray(),
		                dataType : 'json',
		                type:'POST',
		                async : false,
		                success : function(data) {
		                    if (data.success) {
		                        $.fn.close_popdown();
		                        var url_id=$('#url_id').val();
		                        var url_personoid=$('#personOid').val();
		                        $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
		                    }
		                    else
		                    {
		                    	$("#rewardInfoForm div.md-error").css('display','block');
			                    if($("#rewardInfoForm div.md-error .back-error").length>0) {
			                    	$("#rewardInfoForm div.md-error .back-error").html(data.message);
			                    }else {
			                    	$("#rewardInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
			                    }
		                    }
		                }
		            });
		        }
		    });	   
		});

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
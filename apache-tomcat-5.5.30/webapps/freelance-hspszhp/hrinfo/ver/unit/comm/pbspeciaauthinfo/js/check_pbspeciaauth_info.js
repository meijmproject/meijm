
//初始化
$(document).ready(function(){
    //点击保存提交
    $('#saveSpeciaAuth').click(function(){
        $('#speciaAuthForm').submit();
    });
//    $.validator.addMethod("compareDate1", function (value, element){return isBeforeNow("authDateStr");}, "授权时间不能晚于今天");
//    $.validator.addMethod("compareDate2", function (value, element){return isBeforeNow("cancelAuthDateStr");}, "取消授权时间不能晚于今天");
    $.validator.addMethod("compareDate3", function (value, element){return compareDate("authDateStr", "cancelAuthDateStr");}, "取消授权时间不能早于授权时间");
    
    //校验
    $("#speciaAuthForm").validate({
        rules:{
        	authType:{
        		required:true
        	},
        	authLevelName:{
				required:true
			},
			authStatus:{
				required:true
			},
			authDateStr:{
				required:true,
				date:true,
//				compareDate1:true
				
			},
			cancelAuthDateStr:{
				compareDate3:true
			},
			remark:{
				maxlength:1000
			}
        },
        messages: {
        	authType:{
        		required:"权限类别不能为空  "
        	},
        	authLevelName:{
				required:"授权级别不能为空  "
			},
			authStatus:{
				required:"授权状态不能为空  "
			},
			authDateStr:{
				required:"授权时间不能为空",
				date:"请输入正确的日期",
//				compareDate1:"授权时间不能晚于今天"
			},
			cancelAuthDateStr:{
				compareDate3:"取消授权时间不能早于授权时间  "
			},
			remark:{
				maxlength:"备注不能超过1000字符"
			}
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#speciaAuthForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
         {
        	$.ajax({
                url : $('#speciaAuthForm').attr("action"),
                data :  $("#speciaAuthForm").serializeArray(),
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
                    	$("#speciaAuthForm div.md-error").css('display','block');
	                    if($("#speciaAuthForm div.md-error .back-error").length>0) {
	                    	$("#speciaAuthForm div.md-error .back-error").html(data.message);
	                    }else {
	                    	$("#speciaAuthForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                    }
                    }
                }
            });
        }
    });
});
function changeAuthType(authType)
{   
    
	if(authType=='1')
    {
    	$("#authLevel").attr("opt","1");
//    	$("#techName").css('display','none');
    }
    else if(authType=='2')
    {
    	$("#authLevel").attr("opt","2");
//    	$("#techName").css('display','block');
//    	$("#profTechCodeName1").css('display','block');
//    	$("#profTechCodeName2").css('display','block');
//    	$("#qualificationsCodeName1").css('display','none');
//    	$("#qualificationsCodeName2").css('display','none');
    }
    else if(authType=='3')
    {
    	$("#authLevel").attr("opt","3");
//    	$("#techName").css('display','block');
//    	$("#qualificationsCodeName1").css('display','block');
//    	$("#qualificationsCodeName2").css('display','block');
//    	$("#profTechCodeName1").css('display','none');
//    	$("#profTechCodeName2").css('display','none');
    }   
    else if(authType=='4')
    {
    	$("#authLevel").attr("opt","4");
//    	$("#techName").css('display','block');
//    	$("#qualificationsCodeName1").css('display','block');
//    	$("#qualificationsCodeName2").css('display','block');
//    	$("#profTechCodeName1").css('display','none');
//    	$("#profTechCodeName2").css('display','none');
    }
    $("#authLevel").val('');
    $("input[name='authLevelName']").val('');
}

function initAuthType()
{
	
    var authType = $("#authType").val();
    if(authType=='1')
    {
        $("#authLevel").attr("opt","1");
//        $("#techName").css("display","none");
    }
    else if(authType=='2')
    {
        $("#authLevel").attr("opt","2");
//        $("#profTechCodeName1").css('display','block');
//    	$("#profTechCodeName2").css('display','block');
    }
    else if(authType=='3')
    {
        $("#authLevelName").attr("opt","3");
//        $("#qualificationsCodeName1").css('display','block');
//    	$("#qualificationsCodeName2").css('display','block');
    }
    else if(authType=='4')
    {
        $("#authLevel").attr("opt","4");
//        $("#qualificationsCodeName1").css('display','block');
//    	$("#qualificationsCodeName2").css('display','block');
    }
    else
	{
        $("#authLevel").attr("opt","");
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
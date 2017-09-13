 $(function(){

    $('#saveReview').click(function(){
        $('#reviewInfoForm').submit();
    });
//    $.validator.addMethod("compareDate1", function (value, element){return isBeforeNow("reviewDateStr");}, "考核日期不能晚于于今天");
    
    $("#reviewInfoForm").validate({
    
        rules:{
        	reviewTypeCode: {
                required: true
            },
            reviewResultCode: {
                required: true
            },
            reviewYearStr: {
            	required: true
            },
//            reviewDateStr:{
//            	compareDate1:true
//            },
            reviewUnitName:{
            	required: true
            },
            remark:{
         	   maxlength:1000
            }
        },
        messages: {
        	reviewTypeCode: {
                required: "请选择考核类别  "
            },
            reviewResultCode: {
                required: "请选择考核结论类别  "
            },
            reviewYearStr: {
            	required: "请选择考核年度  "
//            	compareDate1:"考核年度不能晚于今年"
           },
//           reviewDateStr:{
//           		compareDate1:"考核时间不能晚于今天"
//           },
           reviewUnitName:{
        	   required: "请选择考核时所在科室  "
           },
           remark:{
        	   maxlength:"备注信息不能超过1000字符"
           }
            
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#reviewInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#reviewInfoForm').attr("action"),
                data :  $("#reviewInfoForm").serializeArray(),
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
                        $("#reviewInfoForm div.md-error").css('display','block');
//                        $("#reviewInfoForm div.modal-wrong").append(data.message);
                        $(".md-error").html("<li>"+data.message+"</li>");
                    }
                }
            });
        }
    });
});

 //初始化属性
 function reviewTypeCodeOnchange()
 {
	 var reviewTypeCode = $('#reviewTypeCode').val();
	 var reviewResultType = $('#reviewResultType').val();
	 if(reviewTypeCode=='4'){
//	        $("#YearDivId").css('display','block');
	        $("#reviewYearId").css('display','none');
	        $("#reasonTypeId").css('display','none');
	        $("#NoreasonTypeId").css('display','none');
	        $("#reviewResultTypeId").css('display','none');
	        $("#TypeId").css('display','none');
	        $("#YearTypeId").css('display','none');
	        $("#reviewResultTypeId").css('display','none');
	        $("#changereviewResultTypeId").css('display','block');
	        if (!(reviewResultType=='优秀' || reviewResultType=='合格' || reviewResultType=='不合格')) {
	        	$('#second_reviewResultType').val("");
			}
	 }
	 if(reviewTypeCode=='2'){
	        $("#reviewYearId").css('display','block');
//	        $("#YearDivId").css('display','none');
	        $("#reviewDateId").css('display','block');
	        $("#reasonTypeId").css('display','block');
	        $("#reviewResultTypeId").css('display','block');
	        $("#NoreasonTypeId").css('display','block');
	        $("#TypeId").css('display','block');
	        $("#YearTypeId").css('display','block');
	        $("#changereviewResultTypeId").css('display','none');
	 }
 }
 
 function OnreviewTypeCodeOnchange(reviewTypeCode)
 {
	 var reviewTypeCode = $('#reviewTypeCode').val();
	 if(reviewTypeCode=='4'){
//	        $("#YearDivId").css('display','block');
	        $("#reviewYearId").css('display','none');
	        $("#reasonTypeId").css('display','none');
	        $("#NoreasonTypeId").css('display','none');
	        $("#reviewResultTypeId").css('display','none');
	        $("#TypeId").css('display','none');
	        $("#YearTypeId").css('display','none');
	        $("#reviewResultTypeId").css('display','none');
	        $("#changereviewResultTypeId").css('display','block');
	        
	 }
	 if(reviewTypeCode=='2'){
	        $("#reviewYearId").css('display','block');
//	        $("#YearDivId").css('display','none');
	        $("#reviewDateId").css('display','block');
	        $("#reasonTypeId").css('display','block');
	        $("#reviewResultTypeId").css('display','block');
	        $("#NoreasonTypeId").css('display','block');
	        $("#TypeId").css('display','block');
	        $("#YearTypeId").css('display','block');
	        $("#changereviewResultTypeId").css('display','none');
	 }
	 
	 
 }
 
 
 /*
 function reviewTypeCodeOnchanges(reviewTypeCode)
 {
	 var reviewTypeCode = $('#reviewTypeCode').val();
	 if(reviewTypeCode=='4'){
	        $("#YearDivId").css('display','block');
	        $("#reviewYearId").css('display','none');
	        $("#reasonTypeId").css('display','none');
	        $("#NoreasonTypeId").css('display','none');
	        $("#TypeId").css('display','none');
	        $("#YearTypeId").css('display','none');
	 }
	 if(reviewTypeCode=='2'){
	        $("#reviewYearId").css('display','block');
	        $("#YearDivId").css('display','none');
	        $("#reviewDateId").css('display','block');
	        $("#reasonTypeId").css('display','block');
	        $("#NoreasonTypeId").css('display','block');
	        $("#TypeId").css('display','block');
	        $("#YearTypeId").css('display','block');
	 }
 }*/
 
////是否早于今天
// function isBeforeNow(date){
// 	var date = $("#"+date).val();
// 	if(date.length>0){
// 		var reg = new RegExp('-','g');
// 	    date = date.replace(reg,'/');//正则替换
// 	    now = new Date();
// 	    date = new Date(parseInt(Date.parse(date),10));
// 	    
// 	    if(date<now){
// 	        return true;
// 	    }else{
// 	        return false;
// 	    }
// 	}else{
// 		return true;
// 	}
// }
 jQuery.validator.addMethod("oidCheck", function(value, element){
		var reviewUnitOid = $("#reviewUnitOid").val();//名称
	    var hiddenReviewUnitOid = $("#hiddenReviewUnitOid").val();//代码
	    
	    if(reviewUnitOid!=null&&reviewUnitOid!=""){
	    	if(hiddenReviewUnitOid!=null&&hiddenReviewUnitOid!=""){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }else{
	    	return true;
	    }

	}, "请选择考核时所在科室");
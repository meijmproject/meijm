 $(function(){
    //校验
    $('#saveEducationTrainingInfo').click(function(){
        $('#educationTrainingInfoForm').submit();
    });
    
    $.validator.addMethod("compareDate", function(value, element){
		var startDate = $("#trainingBeginDateStr").val();
	    var endDate = $("#trainingEndDateStr").val();
	    
	    var reg = new RegExp('-','g');
        startDate = startDate.replace(reg,'/');//正则替换
        endDate = endDate.replace(reg,'/');
        startDate = new Date(parseInt(Date.parse(startDate),10));
        endDate = new Date(parseInt(Date.parse(endDate),10));
        
        if(startDate>endDate){
            return false;
        }else{
            return true;
        }
	
	}, "培训终止日期不能早于培训起始日期");

    $("#educationTrainingInfoForm").validate({
        rules:{
        	educationTrainingKinkCode: {
                required: true
            },
            trainingType: {
                required: true
            },
            trainingBeginDateStr: {
                required: true
            },
            trainingEndDateStr: {
                required: true,
                compareDate: true
            }
        },
        messages: {
        	educationTrainingKinkCode: {
                required: "请选择教育培训性质"
            },
            trainingType: {
                required: "请选择培训类别"
            },
            trainingBeginDateStr: {
                required: "请选择培训起始日期"
            },
            trainingEndDateStr: {
                required: "请选择培训终止日期",
                compareDate: "培训终止日期不能早于培训起始日期"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#educationTrainingInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#educationTrainingInfoForm').attr("action"),
                data :  $("#educationTrainingInfoForm").serializeArray(),
                dataType : 'json',
                async : false,
                type : 'post',
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
                        $("#educationTrainingInfoForm div.md-error").css('display','block');
                        if($("#educationTrainingInfoForm div.md-error .back-error").length>0) {
                        	$("#educationTrainingInfoForm div.md-error .back-error").html(data.message);
                        }else {
                        	$("#educationTrainingInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                        }
                    }
                }
            });
        }
    });
});	



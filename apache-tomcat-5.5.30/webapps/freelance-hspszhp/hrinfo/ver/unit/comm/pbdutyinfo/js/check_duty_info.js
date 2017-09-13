/*
 * 院内中层职务任职信息校验
 */
$(function(){
    //校验
    $('#saveDutyInfo').click(function(){
        $('#dutyInfoForm').submit();
    });
    
    $.validator.addMethod("compareDate", function(value, element){
		var startDate = $("#startDateStr").val();
	    var endDate = $("#endDateStr").val();
	    
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
	
	}, "离职时间不能早于任职时间");
    
    
    $("#dutyInfoForm").validate({
        rules:{
        	dutyStatus: {
                required: true
        	},
        	dutyName: {
                required: true
            },
            deptName: {
            	required: true
            },
            endDateStr: {
            	compareDate: true
            },
            isMainDutyInfo:{
            	remote: {
        		    url: "uniqueCheckDutyInfo.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	isMainDutyInfo: function() {
        		            return $("#isMainDutyInfo").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        dutyOid: function() {
        		            return $("#dutyOid").val();
        		        }
        		    }
        		}
            }
        },
        messages: {
        	dutyStatus: {
                required: "请选择任职状态，"
        	},
        	dutyName: {
                required: "请选择任职职务，"
            },
            deptName: {
            	required: "请选择任职科室，"
            },
            endDateStr: {
            	compareDate: "离职时间不能早于任职时间，"
            },
            isMainDutyInfo:{
            	remote: "主要职务只能有一笔，"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#dutyInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#dutyInfoForm').attr("action"),
                data :  $("#dutyInfoForm").serializeArray(),
                dataType : 'json',
                type: 'post',
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
                            $("#dutyInfoForm div.md-error").css('display','block');
                            if($("#dutyInfoForm div.md-error .back-error").length>0) {
                            	$("#dutyInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#dutyInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                		return;
                	}
                    catch(e) {
                    }
                    $("#popdown-dialog").html(data);
                    $("#formSyPost div.md-error").css('display','block');
                }
            });
        }
    });
});

 $(function(){
    //校验
    $('#saveProfTechQualifInfo').click(function(){
        $('#profTechQualifInfoForm').submit();
    });
    
    $.validator.addMethod("compareDate", function(value, element){
		var startDate = $("#procureDateStr").val();
	    var endDate = $("#validityDateStr").val();
	    
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
	
	}, "有效期不能早于取得资格日期");

    $("#profTechQualifInfoForm").validate({
        rules:{
        	certificateNo: {
               
                remote: {
        		    url: "uniqueCheckProfTechQualifInfo.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	certificateNo: function() {
        		            return $("#certificateNo").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        profTechQualifOid: function() {
        		            return $("#profTechQualifOid").val();
        		        }
        		    }
        		}
            },
            profTechName: {
                required: true
            },
            profTechLevelName: {
                required: true
            },
            isHighestLevel: {
                required: true,
                remote: {
        		    url: "uniqueCheckProfTechQualifInfo.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	isHighestLevel: function() {
        		            return $("#isHighestLevel").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        profTechQualifOid: function() {
        		            return $("#profTechQualifOid").val();
        		        }
        		    }
        		}
            },
            validityDateStr: {
                compareDate: true
            }
        },
        messages: {
        	certificateNo: {
                
                remote: "资格证书编号必须唯一,请重新输入"
            },
            profTechName: {
                required: "请选择专业技术资格名称"
            },
            profTechLevelName: {
                required: "请选择专业技术资格等级"
            },
            isHighestLevel: {
                required: "请选择是否主要专业技术资格",
                remote: "主要专业技术资格只能有一笔"
            },
            validityDateStr: {
                compareDate: "有效期不能早于取得资格日期"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#profTechQualifInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#profTechQualifInfoForm').attr("action"),
                data :  $("#profTechQualifInfoForm").serializeArray(),
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
                        $("#profTechQualifInfoForm div.md-error").css('display','block');
                        if($("#profTechQualifInfoForm div.md-error .back-error").length>0) {
                        	$("#profTechQualifInfoForm div.md-error .back-error").html(data.message);
                        }else {
                        	$("#profTechQualifInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                        }
                    }
                }
            });
        }
    });
});	



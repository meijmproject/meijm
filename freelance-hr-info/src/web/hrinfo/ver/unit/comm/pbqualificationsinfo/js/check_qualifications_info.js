 $(function(){
    //校验
    $('#saveQualificationsInfo').click(function(){
        $('#qualificationsInfoForm').submit();
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

    $("#qualificationsInfoForm").validate({
        rules:{
        	certificateNo: {
                remote: {
        		    url: "uniqueCheckQualificationsInfo.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	certificateNo: function() {
        		            return $("#certificateNo").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        qualificationsOid: function() {
        		            return $("#qualificationsOid").val();
        		        }
        		    }
        		}
            },
            qualificationsName: {
                required: true
            },
            isHighestLevel: {
                required: true,
                remote: {
        		    url: "uniqueCheckQualificationsInfo.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	isHighestLevel: function() {
        		            return $("#isHighestLevel").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        qualificationsOid: function() {
        		            return $("#qualificationsOid").val();
        		        }
        		    }
        		}
            },
            validityDateStr: {
                compareDate: true
            },
            qualificationsType: {
                required: true
            }
        },
        messages: {
        	certificateNo: {
                remote: "执业（职业）资格证书编号必须唯一,请重新输入  "
            },
            qualificationsName: {
                required: "请选择执业（职业）资格名称   "
            },
            isHighestLevel: {
                required: "请选择是否本人当前最高技术工人等级  ",
                remote: "最高执业（职业）资格只能有一笔  "
            },
            validityDateStr: {
                compareDate: "有效期不能早于取得资格日期  "
            },
            qualificationsType: {
                required: "请选择职业资格类型  "
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#qualificationsInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#qualificationsInfoForm').attr("action"),
                data :  $("#qualificationsInfoForm").serializeArray(),
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
                    	$("#qualificationsInfoForm div.md-error").css('display','block');
	                    if($("#qualificationsInfoForm div.md-error .back-error").length>0) {
	                    	$("#qualificationsInfoForm div.md-error .back-error").html(data.message);
	                    }else {
	                    	$("#qualificationsInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                    }
                    }
                }
            });
        }
    });
});	



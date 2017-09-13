/*
 * 学历学位信息校验
 */
$(function(){
    //校验
    $('#saveEducationLevelDegree').click(function(){
        $('#educationLevelDegreeForm').submit();
    });
    
    $.validator.addMethod("compareDate", function(value, element){
		var startDate = $("#schoolEnrollDateStr").val();
	    var endDate = $("#graduateDateStr").val();
	    
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
	
	}, "毕（肄）业日期不能早于入学日期");
    
    $.validator.addMethod("compareDate2", function(value, element){
		var startDate = $("#graduateDateStr").val();
	    var grantDate = $("#degreeGrantDateStr").val();
	    
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
	
	}, "学位授予日期不能早于毕（肄）业日期");
    
    $.validator.addMethod("majorCodeValidate", function(value, element){
    	var educationCodeDic = $('#educationCodeId').val();
		//如果是高中7,初中8,小学9,其他90,跳过必填校验
    	if(educationCodeDic==7||educationCodeDic==8||educationCodeDic==9||educationCodeDic==90){
    		return true;//不验证
    	}else{
    		//如果填了就不验证
    		if($.trim($('#majorCode').val()).length>0){
    			return true;//不验证
    		}
    		return false;//验证
    	}
	
	}, "请输入所学专业");
    
    $("#educationLevelDegreeForm").validate({
    	groups: {  
            username: "educationCode degreeCode"
        },
        rules:{
        	schoolName: {
                required: true
        	},
            studyTypeCode: {
                required: true
            },
            schoolEnrollDateStr: {
            	required: true
            },
            eduType: {
                required: true
            },
            majorCode: {
            	majorCodeValidate: true
            },
            educationCodeName: {
            	required: {
            		depends:function(){ //二选一  
                	    return ($('select[name=degreeCode]').val().length <= 0);  
                    }
            	}
            },
            degreeCode: {
            	required: {
            		depends:function(){ //二选一  
                	    return ($('input[name=educationCode]').val().length <= 0);  
                    }
            	}
            },
            eductionalSystem:{
            	range:[0,99]
            },
        	graduateDateStr:{
        		 required: true,
        		compareDate: true
        	},
        	degreeGrantDateStr:{
        		compareDate2: true
        		
        	},
        	educationCertificate:{
        		remote: {
        		    url: "uniqueCheckPbEducationLevelDegree.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		    	educationCertificate: function() {
        		            return $("#educationCertificate").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        educationLevelOid: function() {
        		            return $("#educationLevelOid").val();
        		        }
        		    }
        		}
        	},
        	degreeCertificateCode:{
        		remote: {
        		    url: "uniqueCheckPbEducationLevelDegree.do?method=uniqueCheck",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		        degreeCertificateCode: function() {
        		            return $("#degreeCertificateCode").val();
        		        },
        		        personOid: function() {
        		            return $("#personOid").val();
        		        },
        		        educationLevelOid: function() {
        		            return $("#educationLevelOid").val();
        		        }
        		    }
        		}
        	}
        },
        messages: {
        	schoolName: {
                required: "请输入学校名称,"
            },
            studyTypeCode: {
                required: "请选择学习形式,"
            },
            schoolEnrollDateStr: {
                required: "请选择入学日期,"
            },
          
            eduType: {
                required: "请选择教育类别,"
            },
            majorCode:{
            	majorCodeValidate: "请输入所学专业,"
            },
            educationCodeName:{
            	required: "请选择学历或者学位,"
            },
            degreeCode:{
            	required: "请选择学历或者学位,"
            },
            eductionalSystem:{
            	range: "请输入正确的数字,例如4,"
            },
            graduateDateStr:{
            	required: "请选择毕(肄)业日期,",
            	compareDate: "毕（肄）业日期不能早于入学日期,"
	    	},
	    	degreeGrantDateStr:{
	    		compareDate2: "学位授予日期不能早于毕（肄）业日期,"
        	},
        	educationCertificate:{
        		remote: "学历证书号码必须唯一,请重新输入,"
        	},
        	degreeCertificateCode:{
        		remote: "学位证书号码必须唯一,请重新输入"
        	}
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("div:first"));
        },
        errorLabelContainer: $("#educationLevelDegreeForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#educationLevelDegreeForm').attr("action"),
                data :  $("#educationLevelDegreeForm").serializeArray(),
                dataType : 'json',
                type: 'post',
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
                            $("#educationLevelDegreeForm div.md-error").css('display','block');
                            if($("#educationLevelDegreeForm div.md-error .back-error").length>0) {
                            	$("#educationLevelDegreeForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#educationLevelDegreeForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                		return;
                	}
                    catch(e) {
                    }
                    $("#popdown-dialog").html(data);
                    $("#educationLevelDegreeForm div.md-error").css('display','block');
                }
            });
        }
    });
});

//切换教育类别
function changeEduType(eduTypeCode)
{
	if(eduTypeCode == "1")
	{
		$("#flag_schoolEnrollDateStr").html("* ");
		$("#flag_eductionalSystem").html("* ");
	}
	else
	{
		$("#flag_schoolEnrollDateStr").html("");
		$("#flag_eductionalSystem").html("");
	}
}

//切换学历等级
function changeEducationLevel(){
	//获取学历,学位的值
	var educationCode = $("#educationCodeId").val();
	var degreeCode = $("#degreeCode").val();
	
	if(educationCode==7||educationCode==8||educationCode==9||educationCode==90){
		//取消所学专业的必填效果
		$('.educationcodevalidate').text("");
	}else{
		//添加所学专业的必填效果
		$('.educationcodevalidate').text("* ");
	}

	//获取学历等级代码
	$.ajax({
	    url:'getEducationLevel.do?method=getEducationLevel',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	educationCode:educationCode,
	    	degreeCode:degreeCode
	    },
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	        //显示学历等级
	        $("#educationLevelName").val(data.educationLevelName);
	        $("#educationLevel").val(data.educationLevel);
	    }
	});
	
}
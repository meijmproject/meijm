 $(function(){
    //校验
    $('#saveYnGwInfo').click(function(){
        $('#gwYnInfoForm').submit();
    });
    
    //初始化加载（修改用）
    getHisPositionType();
    
    //根据工作类别的选,加载岗位类别的下拉框
    $('#hisWorkTypeId').change(function(){
    	//获取工作类别的选择
    	var hisWorkType = $('#hisWorkTypeId option:selected').val();
    	
    	if(hisWorkType==""){
    		$("#hisPositionType").empty();//清空选项
    		$("#hisPositionType").append("<option value=''>--请先选择工作类别--</option>");
    		$("#parentPositionName").empty();//清空选项
    		$("#parentPositionName").append("<option value=''>--请先选择岗位类别--</option>");
    	}else{
    		//根据工作类别的选择,获取需要加载到岗位类别的选择
        	$.ajax({
        	    url:'getSelectItems.do?method=getSelectItems',
        	    type:'POST',
        	    data:{
        	    	hisWorkType:hisWorkType
        	    },
        	    dataType:'json',
        	    success:function(data){
        			//生成岗位类别的下拉框
        			$("#hisPositionType").empty();//清空选项
    				$("#hisPositionType").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.hisPositionTypeItems.length; i++){
        				$("#hisPositionType").append("<option value='"+data.hisPositionTypeItems[i].dicItemCode+"'>"+data.hisPositionTypeItems[i].dicItemName+"</option>");
        			}
        			//生成上级岗位名称的下拉框
        			$("#parentPositionName").empty();//清空选项
    				$("#parentPositionName").append("<option value=''>--请选择--</option>");
        			for(var i=0; i<data.parentPositionName.length; i++){
        				$("#parentPositionName").append("<option value='"+data.parentPositionName[i].positionOid+"'>"+data.parentPositionName[i].positionName+"</option>");
        			}
        	    }
        	});
    	}
    });
    
    
    //根据岗位类别的选择,加载岗位名称的下拉框
    $('#hisPositionType').change(function(){
    	getHisPositionType();
    });
    
    
    
    $("#gwYnInfoForm").validate({
        rules:{
        	positionName: {
                required: true,
                
            },
            hisWorkType: {
                required: true
            },
            hisPositionType: {
                required: true
            }
        },
        messages: {
        	positionName: {
                required: "请输入岗位名称"
            },
            hisWorkType: {
                required: "请选择工作类别"
            },
            hisPositionType: {
                required: "请选择岗位类别"
            }
        },
        errorLabelContainer: $("#gwYnInfoForm div.md-error"),
        wrapper: 'span',
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#gwYnInfoForm').attr("action"),
                data :  $("#gwYnInfoForm").serializeArray(),
                dataType : 'json',
                async : false,
                type : 'post',
                success : function(data) {
                	try {
	                    if (data.success) {
	                        Widget.close();//关闭弹出框
	                        worktop.form.goQuery();
	                    }
	                    else
	                    {
	                        $("#gwYnInfoForm div.md-error").css('display','block');
	                        if($("#gwYnInfoForm div.md-error .back-error").length>0) {
	                        	$("#gwYnInfoForm div.md-error .back-error").html(data.message);
	                        }else {
	                        	$("#gwYnInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                        }
	                    }
                	}
                    catch(e) {
                    }
                    $("#popdown-dialog").html(data);
                    $("#gwYnInfoForm div.md-error").css('display','block');
                }
            });
        }
    });
});	


function getHisPositionType(){
	//获取工作类别的选择
	var hisPositionType = $('#hisPositionType option:selected').val();
	
	if(hisPositionType==""){
		$("#parentPositionName").empty();//清空选项
		$("#parentPositionName").append("<option value=''>--请先选择岗位类别--</option>");
	}else{
		//根据岗位类别的选择,获取需要加载到岗位名称的选择
    	$.ajax({
    	    url:'getSelectItems.do?method=getSelectItems',
    	    type:'POST',
    	    data:{
    	    	hisPositionType:hisPositionType
    	    },
    	    dataType:'json',
    	    success:function(data){
    			
    			//生成岗位名称的下拉框
    			$("#parentPositionName").empty();//清空选项
    			var parentPositionOid = $('#parentPositionOid').val();
    			$("#parentPositionName").append("<option value=''>--请选择--</option>");
    			for(var i=0; i<data.parentPositionName.length; i++){
    				//设置默认选中
    				if(null != parentPositionOid && parentPositionOid == data.parentPositionName[i].positionOid)
					{
    					$("#parentPositionName").append("<option value='"+data.parentPositionName[i].positionOid+"' selected>"+data.parentPositionName[i].positionName+"</option>");
					}else
					{
						$("#parentPositionName").append("<option value='"+data.parentPositionName[i].positionOid+"'>"+data.parentPositionName[i].positionName+"</option>");
					}
    			}
    	    }
    	});
	}
}
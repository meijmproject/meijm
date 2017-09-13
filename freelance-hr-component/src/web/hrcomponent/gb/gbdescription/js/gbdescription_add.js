$(function(){
	$('#saveGbDescription').click(function(){
        $('#gbDescriptionForm').submit();
    });

		$("#gbDescriptionForm").validate({
		    rules:{
		    	postType:{
		            required: true
		        },
		        postLevel:{
		            required: true
		        },
		        postName:{
		            required: true
		        }
		    },
		    messages: {
		    	postType:{
		            required: "请选择岗位类别"
		        },
		        postLevel: {
		            required: "请选择岗位级别"
		        },
		        postName: {
		            required: "请填写岗位名称"
		        }
		    },
	        errorPlacement: function (error, element)
	        {
	            error.appendTo(element.parents("dl:first"));
	        },
	        errorLabelContainer: $("#gbDescriptionForm div.md-error"),
	        wrapper: "span",
	        submitHandler: function (form)
	        {
	                var options = {
	                    url:$('#gbDescriptionForm').attr("action"),
	                    dataType:  'json',
	                    success : function(data) {
	                    	try {
	                            if (data.success) {
	                                Widget.close();
	                                worktop.form.goQuery();
	                            }
	                            else
	                            {
	                                $("#gbDescriptionForm div.md-error").css('display','block');
	                                if($("#gbDescriptionForm div.md-error .back-error").length>0) {
	                                	$("#gbDescriptionForm div.md-error .back-error").html(data.message);
	                                }else {
	                                	$("#gbDescriptionForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
	                                }
	                            }
	                            return;
	                        } catch (e) {
	                        }
	                        $("#popdown-dialog").html(data);
	                        $("#gbDescriptionForm div.md-error").css('display','block');
	                    }
	                };
	                $('#gbDescriptionForm').ajaxSubmit(options);
	        }
	    });
		
		
		$('#postType1').change(function(){
			var postTypeVal = $('#postType1 option:selected').val();
			if(postTypeVal==1){
				$("#postLevel1").empty();//清空选项
	    		$("#postLevel1").append("<option value=''>--请选择--</option>");
	    		$("#postLevel1").append('<option value="A1010">管理类</option>');
	    		$("#postLevel1").append('<option value="A1010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1010040">三级</option>');
	    		$("#postLevel1").append('<option value="A1010050">四级</option>');
	    		$("#postLevel1").append('<option value="A1010060">五级</option>');
	    		$("#postLevel1").append('<option value="A1010070">六级</option>');
	    		$("#postLevel1").append('<option value="A1010080">七级</option>');
	    		$("#postLevel1").append('<option value="A1010090">八级</option>');
	    		$("#postLevel1").append('<option value="A1010100">九级</option>');
	    		$("#postLevel1").append('<option value="A1010110">十级</option>');
	    		$("#postLevel1").append('<option value="A1010190">未定级</option>');
			}else if(postTypeVal==2){
				$("#postLevel1").empty();//清空选项
	    		$("#postLevel1").append("<option value=''>--请选择--</option>");
	    		$("#postLevel1").append('<option value="A1020">专业技术类</option>');
	    		$("#postLevel1").append('<option value="A1020010">高级</option>');
	    		$("#postLevel1").append('<option value="A1020010010">正高级</option>');
	    		$("#postLevel1").append('<option value="A1020010010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1020010010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1020010010030">三级</option>');
	    		$("#postLevel1").append('<option value="A1020010010040">四级</option>');
	    		$("#postLevel1").append('<option value="A1020010020">副高级</option>');
	    		$("#postLevel1").append('<option value="A1020010020010">五级</option>');
	    		$("#postLevel1").append('<option value="A1020010020020">六级</option>');
	    		$("#postLevel1").append('<option value="A1020010020030">七级</option>');
	    		$("#postLevel1").append('<option value="A1020020">中级</option>');
	    		$("#postLevel1").append('<option value="A1020020010">八级</option>');
	    		$("#postLevel1").append('<option value="A1020020020">九级</option>');
	    		$("#postLevel1").append('<option value="A1020020030">十级</option>');
	    		$("#postLevel1").append('<option value="A1020030">初级</option>');
	    		$("#postLevel1").append('<option value="A1020030010">十一级</option>');
	    		$("#postLevel1").append('<option value="A1020030020">十二级</option>');
	    		$("#postLevel1").append('<option value="A1020030030">十三级</option>');
	    		$("#postLevel1").append('<option value="A1020090">未定级</option>');
			}else if(postTypeVal==3){
				$("#postLevel1").empty();//清空选项
	    		$("#postLevel1").append("<option value=''>--请选择--</option>");
	    		$("#postLevel1").append('<option value="A1030">工勤技能类</option>');
	    		$("#postLevel1").append('<option value="A1030010">技工</option>');
	    		$("#postLevel1").append('<option value="A1030010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1030010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1030010030">三级</option>');
	    		$("#postLevel1").append('<option value="A1030010040">四级</option>');
	    		$("#postLevel1").append('<option value="A1030010050">五级</option>');
	    		$("#postLevel1").append('<option value="A1030020">普工</option>');
	    		$("#postLevel1").append('<option value="A1030090">未定级</option>');
			}else{
				$("#postLevel1").empty();//清空选项
	    		$("#postLevel1").append("<option value=''>--请选择--</option>");
	    		$("#postLevel1").append('<option value="A1010">管理类</option>');
	    		$("#postLevel1").append('<option value="A1010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1010040">三级</option>');
	    		$("#postLevel1").append('<option value="A1010050">四级</option>');
	    		$("#postLevel1").append('<option value="A1010060">五级</option>');
	    		$("#postLevel1").append('<option value="A1010070">六级</option>');
	    		$("#postLevel1").append('<option value="A1010080">七级</option>');
	    		$("#postLevel1").append('<option value="A1010090">八级</option>');
	    		$("#postLevel1").append('<option value="A1010100">九级</option>');
	    		$("#postLevel1").append('<option value="A1010110">十级</option>');
	    		$("#postLevel1").append('<option value="A1010190">未定级</option>');
	    		$("#postLevel1").append('<option value="A1020">专业技术类</option>');
	    		$("#postLevel1").append('<option value="A1020010">高级</option>');
	    		$("#postLevel1").append('<option value="A1020010010">正高级</option>');
	    		$("#postLevel1").append('<option value="A1020010010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1020010010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1020010010030">三级</option>');
	    		$("#postLevel1").append('<option value="A1020010010040">四级</option>');
	    		$("#postLevel1").append('<option value="A1020010020">副高级</option>');
	    		$("#postLevel1").append('<option value="A1020010020010">五级</option>');
	    		$("#postLevel1").append('<option value="A1020010020020">六级</option>');
	    		$("#postLevel1").append('<option value="A1020010020030">七级</option>');
	    		$("#postLevel1").append('<option value="A1020020">中级</option>');
	    		$("#postLevel1").append('<option value="A1020020010">八级</option>');
	    		$("#postLevel1").append('<option value="A1020020020">九级</option>');
	    		$("#postLevel1").append('<option value="A1020020030">十级</option>');
	    		$("#postLevel1").append('<option value="A1020030">初级</option>');
	    		$("#postLevel1").append('<option value="A1020030010">十一级</option>');
	    		$("#postLevel1").append('<option value="A1020030020">十二级</option>');
	    		$("#postLevel1").append('<option value="A1020030030">十三级</option>');
	    		$("#postLevel1").append('<option value="A1020090">未定级</option>');
	    		$("#postLevel1").append('<option value="A1030">工勤技能类</option>');
	    		$("#postLevel1").append('<option value="A1030010">技工</option>');
	    		$("#postLevel1").append('<option value="A1030010010">一级</option>');
	    		$("#postLevel1").append('<option value="A1030010020">二级</option>');
	    		$("#postLevel1").append('<option value="A1030010030">三级</option>');
	    		$("#postLevel1").append('<option value="A1030010040">四级</option>');
	    		$("#postLevel1").append('<option value="A1030010050">五级</option>');
	    		$("#postLevel1").append('<option value="A1030020">普工</option>');
	    		$("#postLevel1").append('<option value="A1030090">未定级</option>');
			}
		})
});


/**
 * 
 */
//提交前数据校验
$(function(){
    $("#updateFuncRoleForm").validate({
        rules:{
        	roleName:{
                required: true,
                maxlength: 50
            },
            roleDesc: {
    			maxlength: 100
    		}
        },   
        messages: {
        	roleName: {
                required: "请输入功能角色名称",
                maxlength: '角色名称不能超过50个字'
            },
            roleDesc: {
            	maxlength: '角色描述不能超过100个字'
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#updateFuncRoleForm div.modal-wrong"),
        wrapper: "li",
        submitHandler: function( form) {
                  var options = {
                      type : "POST" ,  
                      url:'updateFuncRole.do?method=updateFuncRole',
                      success : function(data) {
	            	  	  data = $.parseJSON(data);
	            	  	  if(data.success==false){
	            	  		  $("#updateFuncRoleForm div.modal-wrong").css('display','block');
		                      $(".wroglist li").html(data.message).css('color','#000000');
	            	  	  }else if(data.success==true){
	            	  		  $("#Role_Bigbog_2").hide();
	            	  		  $("#Role_Bigbog_3").show();
	            	  		  $("#Role_Bigbog_3 input[name='roleName']").val($("#Role_Bigbog_2 input[name='roleName']").val());
	            	  		  $("#Role_Bigbog_3 textarea[name='roleDesc']").val($("#Role_Bigbog_2 textarea[name='roleDesc']").val());
	            	  	  }
                      }
                  };
            $('#updateFuncRoleForm').ajaxSubmit(options);
        }
    });
});

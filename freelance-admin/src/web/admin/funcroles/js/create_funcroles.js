/**
 * 
 */
//提交前数据校验
$(function(){
    $("#funcRoleFormId").validate({
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
                required: "请输入数据角色名称",
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
        errorLabelContainer: $("#funcRoleFormId div.modal-wrong"),
        wrapper: "li",
        submitHandler: function( form) {
        	//alert("数据提交！");
                  var options = {
                      type : "POST" ,  
                      url:'addFuncRole.do?method=addFuncRole',
                      success : function(data) {
                    	  data = $.parseJSON(data);
                          if (data.success) {
                        	  Widget.close();
//                        	var goBackUrl='goFuncRoleList.do?method=goFuncRoleList';
//          					location.href='goUpdateFuncRole.do?method=goUpdateFuncRole&roleId='+data.message+"&goBackUrl="+goBackUrl;
          					Widget.openContent('goUpdateFuncRole.do?method=goUpdateFuncRole&roleId='+data.message,null,{width:null});
                          }
                          else
                          {
                              $("#funcRoleFormId div.modal-wrong").css('display','block');
	                          $(".wroglist li").html(data.message).css('color','#000000');
                          }
                          return;
                      }
                  };
            $('#funcRoleFormId').ajaxSubmit(options);
        }
    });
});

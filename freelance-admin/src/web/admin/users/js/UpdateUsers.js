/**
 * 提交前数据校验
 */
$(function(){
    $("#formUsersUpdate").validate({
    	rules:{
        	userName:{
                required: true,
                maxlength: 50
            },
        	personOid:{
                required: true
            },
            unitName: {
                required: true,
                maxlength: 100
            },
            userId: {
                required: true,
                maxlength: 20,
                isWord: 1
            },
            userType:{
                required: true
            },
            address: {
            	maxlength: 100
            },
            contactPhone :{
            	maxlength: 20,
            	isNum : 1
            }
        },   
        messages: {
        	userName: {
                required: "请输入用户姓名",
                maxlength:"用户名不能超过{0}"
            },
        	personOid: {
                required: "请选择人员"
            },
            unitName: {
                required: "请选择单位单位信息",
            },
            userId: {
                required: "请输入用户ID",
                maxlength:"用户ID不能超过{0}"
            },
            userType: {
                required: "请选择用户类型",
            },
            address: {
            	maxlength: "地址不能超过{0}"
            },
            contactPhone :{
            	maxlength: "电话的长度不能超过{0}"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#formUsersUpdate div.modal-wrong"),
        wrapper: "li",
        submitHandler: function( form) {
                  var options = {
                      type : "POST" ,  
                      url:'usersUpdate.do?method=updateUsers',
                      success : function(data) {
                    	  data = $.parseJSON(data);
                          if (data.success) {
                        	  //location.href ="goUsersView.do?method=ViewUsersPage&userOid="+data.message.userOid+"&userId="+data.message.userId;
                        	  Widget.close();
                        	  Widget.load('#left_nav');
                          }
                          else
                          {
                        	  $("#formUsersUpdate div.modal-wrong").css('display','block');
	                          $(".wroglist li").html(data.message).css('color','#000000');
                          }
                          return;
                      }
                  };
            $('#formUsersUpdate').ajaxSubmit(options);
        }
    });
});
//验证输入值为字母：[a-zA-Z] 
jQuery.validator.addMethod("isWord", function(value, element) {   
    var tel = /^[0-9a-zA-Z]*$/;
    return this.optional(element) || (tel.test(value));
}, "用户ID不能包含汉字！");
//验证输入值为数字
jQuery.validator.addMethod("isNum", function(value, element) {   
    //var tel = /^[0-9]*$/;
    var tel = /^1[34578]\d{9}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的联系电话");

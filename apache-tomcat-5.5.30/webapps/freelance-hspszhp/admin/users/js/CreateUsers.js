/**
 * 提交前数据校验
 */
$(function(){
    $("#formUsersCreate").validate({
        rules:{
        	userName:{
                required: true,
                maxlength: 50
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
            refOid:{
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
            unitName: {
                required: "请选择单位信息",
            },
            userId: {
                required: "请输入用户ID",
                maxlength:"用户ID不能超过{0}"
            },
            userType: {
                required: "请选择用户类型",
            },
            refOid: {
                required: "请选择用户",
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
        errorLabelContainer: $("#formUsersCreate div.modal-wrong"),
        wrapper: "li",
        submitHandler: function( form) {
        	//alert("数据提交！");
                  var options = {
                      type : "POST" ,  
                      url:'createUsers.do?method=createUsers',
                      success : function(data) {
                    	  data = $.parseJSON(data);
                          if (data.success) {
                        	  //location.href = "goUsersList.do?method=goUsersList"
                        	  MessageBox.alert("提示","用户创建成功，默认密码为“666666”！",function(){
                        		  HistoryRegister.set("goUsersList",'goUsersList.do?method=goUsersList');
                            	  var goBackUrl='goUsersList.do?method=goUsersList';
                            	  location.href ="goUsersPositionPage.do?method=goUsersPositionPage&userOid="+data.message.userOid+"&userId="+data.message.userId+"&goBackUrl="+goBackUrl;
	                    	  });
                          }
                          else 
                          {
	                          $("#formUsersCreate div.modal-wrong").css('display','block');
	                          $(".wroglist li").html(data.message).css('color','#000000');
                          }
                          return;
                      }
                  };
            $('#formUsersCreate').ajaxSubmit(options);
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
   // var tel = /^[0-9]*$/;
    var tel = /^1[34578]\d{9}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的联系电话");

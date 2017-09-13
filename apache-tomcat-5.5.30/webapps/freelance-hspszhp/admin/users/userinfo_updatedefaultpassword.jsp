<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户密码修改页面</title>
</head>
<script src="admin/users/js/CreateUsers.js"></script>
 <script type="text/javascript" src="js/comm/customJs.js"></script>
<script type="text/javascript">
	$('#showmodal').css('margin-top',150);
</script>
<script type="text/javascript">
$(function(){
	// 在键盘按下并释放及提交后验证提交表单
		  $("#updateUserPassword").validate({
		    rules: {
		    	userPassword:{
		    	required: true
			  },
		    	newPassword: {
		        required: true,
		        rangelength:[8,12]
		        
		      },
		      confirm_password: {
		        required: true,
		        equalTo: "#newPassword",
		        rangelength:[8,12]
		      }
		    },
		    messages: {
		    	userPassword:{
			    	required: "请输入密码"	
			  },
		    	newPassword: {
		        required: "请输入新密码",
		        rangelength :"密码不符合规范！（要求长度为8~12位）"
		      },
		      confirm_password: {
		        required: "请再次输入新密码",
		        equalTo: "两次密码输入不一致",
		        rangelength :"密码不符合规范！（要求长度为{0}~{1}位）"
		      }
		    },
		    errorPlacement: function (error, element)
	        {
	            error.appendTo(element.parents("dl:first"));
	        },
	        errorLabelContainer: $("#updateUserPassword div.modal-wrong"),
	        wrapper: "li",
			submitHandler: function(form) {
		    		//alert("数据提交！");
		    		  var userpassword=$('userPassword').val(); 
		    		  var confirm_password=$('confirm_password').val();
		              var options = {
		                  type : "POST" ,  
		                  url:'updateUserPassword.do?method=updateUserPassword',
		                  data:{userpassword:userpassword,confirm_password:confirm_password},
		                  success : function(data) {
		                	  data = $.parseJSON(data);
		                      if (data.success) {
		                    	  MessageBox.alert("提示","密码修改成功，请重新登录！",function(){
		                    	  	location.href="logout.do?method=logout";
			                    	});
		                      }
		                      else
		                      {
		                          $("#updateUserPassword div.md-error").css('display','block');
		                          $(".back-error").html(data.message).css('color','#000000');
		                      }
		                      return;
		                  }
		              };
		        $('#updateUserPassword').ajaxSubmit(options);
		    }
		});
	});
</script>
<body>
 <form id="updateUserPassword" class="form-inline" action="updateUserPassword.do?method=updateUserPassword"  method="post" onsubmit="return false">
    <div id="transaction_modal_box" class="transaction_modal_box top200">
			<div class="modal-dialog-container">
				<div class="md-title">
					用户密码 >修改<a href="#" class="md-close close-popdown"
						button-click="widget-close"></a>
				</div>
				<div class="md-error" style="display: none">
					<span class="back-error"></span>
				</div>
				<div class="md-main-content">
					<div class="md-units md-one-column clearfix">
						<ol class="tit">温馨提示：密码长度为8-12位，须同时包含数字和大小写英文字母，例如：Aa000001。
						</ol>
						<dl class="leftright-dl">
							<dt>当前密码：</dt>
							<dd>
								<input class="modal_iput" type="password" name="userPassword"
									id="userPassword" autocomplete="off" />
							</dd>
						</dl>
						<dl class="leftright-dl">
							<dt>新密码：</dt>
							<dd>
								<input class="modal_iput" type="password" name="newPassword"
									id="newPassword" autocomplete="off" />
							</dd>
						</dl>
						<dl class="leftright-dl">
							<dt>再次输入新密码：</dt>
							<dd>
								<input class="modal_iput" type="password"
									name="confirm_password" id="confirm_password"
									autocomplete="off" />
							</dd>
						</dl>
					</div>
				</div>

			<!-- </div> -->
			<div class="md-btn">
				<button type="submit" class="md-btn-save close-popdown">保存</button>
			</div>
		</div>
		</div>
    </form>
</body>
</body>
</html>
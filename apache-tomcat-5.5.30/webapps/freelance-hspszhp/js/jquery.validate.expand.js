$.validator.addMethod("isIdCardNo",function(value,element,flag){
	return !flag || this.optional(element) || idCardNoUtil.checkIdCardNo(value);
},"请输入正确身份证号码");

$.validator.addMethod("isMobilePhone", function(value, element){
	if(value&&!value.match(/^1[3|4|5|7|8][0-9]{9}$/)){
		return false;
	}
	return true;
}, "移动号码格式错误");

$.validator.addMethod("isPhone", function(value, element){
	if(value&&!value.match(/^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$/)){
		return false;
	}
	return true;
}, "电话格式错误");

$.validator.addMethod("isEmail", function(value, element){
	if(value&&!value.match(/^[a-zA-Z0-9_-]+(\\.([a-zA-Z0-9_-])+)*@[a-zA-Z0-9_-]+[.][a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*$/)){
		return false;
	}
	return true;
}, "电子邮箱格式错误");

$.validator.addMethod("isThreeNum", function(value, element){
	if(value&&!value.match(/^[0-9a-zA-Z]{3}$/)){
		return false;
	}
	return true;
}, "数字格式错误");
jQuery.validator.addMethod("isDate", function(value, element) {
	var num = /\d{4}-\d{2}-\d{2}/;
    return this.optional(element) || (num.test(value));
}, "日期格式不正确!");


/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "<font color='black'>该字段不能为空</font>",
		remote: "<font color='red'>请修正该字段</font>",
		email: "<font color='red'>请输入正确格式的电子邮件</font>",
		url: "<font color='red'>请输入合法的网址</font>",
		date: "<font color='red'>请输入合法的日期</font>",
		dateISO: "<font color='red'>请输入合法的日期 (ISO)</font>",
		number: "<font color='red'>请输入合法的数字</font>",
		digits: "<font color='red'>只能输入整数</font>",
		creditcard: "<font color='red'>请输入合法的信用卡号</font>",
		equalTo: "<font color='red'>请再次输入相同的值</font>",
		accept: "<font color='red'>请输入拥有合法后缀名的字符串</font>",
		maxlength: jQuery.validator.format("<font color='red'>该字段最多输入{0}字</font>"),
		minlength: jQuery.validator.format("<font color='red'>该字段最少输入{0}字</font>"),
		rangelength: jQuery.validator.format("<font color='red'>该字段长度介于 {0} 字和 {1}字 之间</font>"),
		range: jQuery.validator.format("<font color='red'>请输入一个介于 {0} 和 {1} 之间的值</font>"),
		max: jQuery.validator.format("<font color='red'>请输入一个最大为 {0} 的值</font>"),
		min: jQuery.validator.format("<font color='red'>请输入一个最小为 {0} 的值</font>"),

        notnull:"<font color='red'>不能为空</font>"
});
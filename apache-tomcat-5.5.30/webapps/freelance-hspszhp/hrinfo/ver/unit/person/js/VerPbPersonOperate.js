$(function(){
	var birthday =  $('#birthdayStrUpdate').val();
	if(birthday!=""&&birthday!=undefined){
		var birthdayYear = birthday.replace(/-/g,"");
		countAge(birthdayYear);
//		var birthdayYear = birthday.replace(/-/g,"").substring(0,4);
//		var currentYear = new Date().getFullYear();
//		alert(1);
//		$('#ageUpdate').val(parseInt(currentYear)-parseInt(birthdayYear)+1);
	}
	
    //校验
    $("#formPersonUpdate").validate({
	    rules:{
	        name:{
	            required: true
	        },
	        sexCode:{
	            required: true
	        },
	        hireDeptName:{
	            required: true
	        },
	        personStatus: {
	            required: true
	        },
	        personTypeName: {
	            required: true
	        },
	        unitName: {
	            required: true
	        },
	        personOrderView: {
	            isNum: true
	        },
	        image: {
	        	isExistImg : false
	        }
	    },
	    messages: {
	        name: {
	            required: "请输入姓名"
	        },
	        sexCode:{
	            required: "请选择性别"
	        },
	        hireDeptName:{
	            required: "请选择所在科室"
	        },
	        personStatus: {
	            required: "请选择人员状态"
	        },
	        personTypeName: {
	            required: "请选择人员类别"
	        },
	        unitName: {
	            required: "请选择工作单位"
	        },
	        personOrderView: {
	            isNum: "人员排序号必须为数字格式"
	        }
	    },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#formPersonUpdate div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	var imageNewSrc = $('#file_upload').val();
            if(imageNewSrc!='')
            {
                var options = {
                    url:$('#formPersonUpdate').attr("action"),
                    dataType:  'json',
                    success : function(data) {
                    	
                    	try {
                            if (data.success) {
                                $.fn.close_popdown();
                                var url_id=$('#url_id').val();
                                var url_personoid=$('#url_personoid').val();
                                if(url_id==''||url_personoid==''){
                                	Widget.close();
                                }else{
                                $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                            }
                            }
                            else
                            {
                                $("#formPersonUpdate div.md-error").css('display','block');
                                if($("#formPersonUpdate div.md-error .back-error").length>0) {
                                	$("#formPersonUpdate div.md-error .back-error").html(data.message);
                                }else {
                                	$("#formPersonUpdate div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                                }
                            }
                            return;
                        } catch (e) {
                        }
                        $("#popdown-dialog").html(data);
                        $("#formPersonUpdate div.md-error").css('display','block');
                    }
                };
                $('#formPersonUpdate').ajaxSubmit(options);
            }
            else
            {
                $.ajax({
                    url : $('#formPersonUpdate').attr("action"),
                    data :  $("#formPersonUpdate").serializeArray(),
                    dataType : 'json',
                    async : false,
                    success : function(data) {                   
                        try {
                            //data = $.parseJSON(data);
                            //alert(data);
                            if (data.success) {
                                $.fn.close_popdown();
                                var url_id=$('#url_id').val();
                                var url_personoid=$('#url_personoid').val();
                                if(url_id==''||url_personoid==''){
                                	Widget.close();
                                }else{
                                $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                            }
                            }
                            else
                            {
                            	$("#formPersonUpdate div.md-error").css('display','block');
                                if($("#formPersonUpdate div.md-error .back-error").length>0) {
                                	$("#formPersonUpdate div.md-error .back-error").html(data.message);
                                }else {
                                	$("#formPersonUpdate div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                                }
                            }
                            return;
                        } catch (e) {
                        }
                        $("#popdown-dialog").html(data);
                        $("#formPersonUpdate div.md-error").css('display','block');
                        
                    }
                });
            }
        }
    });

});
//验证输入值为数字
jQuery.validator.addMethod("isNum", function(value, element) {
	var num = /^[0-9\.]*$/;
    return this.optional(element) || (num.test(value));
}, "请输入正确的数字!");
jQuery.validator.addMethod("isExistImg", function(value, element) {
	var imageflag = $('#imageflag').val();
	if(imageflag=='1'){
		return true;
	}else{
		if(value==null || value==''){
			return false;
		}
		return true;
	}
}, "请上传一张照片!");

//验证输入值为数字或字母或两者组合
jQuery.validator.addMethod("isNumChar", function(value, element) {
	var num = /^[0-9a-zA-Z]*$/;
    return this.optional(element) || (num.test(value));
}, "请输入数字或字母!");
//验证输入值为数字
jQuery.validator.addMethod("isLength", function(value, element) {
    return this.optional(element) || value.length == 11 ;
}, "手机号码的长度必须为11位!");
//身份证
jQuery.validator.addMethod("idNo", function(value, element) {
	if($('[name=idName]').val()=='居民身份证') {
		var num = /^\d{17}[0-9xX]{1}$/;
	    return this.optional(element) || (num.test(value));
	}else {
		return true;
	}
}, "身份证格式不正确!");

/************************************************** 相片操作 ****************************************************/
Photo = function(){
    var previewTimeoutId;
    var previewTimeoutId = null;

    return {
        getPosXY: function(a,offset){
            var p=offset?offset.slice(0):[0,0],tn;
            while(a){
                tn=a.tagName.toUpperCase();
                if(tn=='IMG'){
                    a=a.offsetParent;continue;
                }
                p[0]+=a.offsetLeft-(tn=="DIV"&&a.scrollLeft?a.scrollLeft:0);
                p[1]+=a.offsetTop-(tn=="DIV"&&a.scrollTop?a.scrollTop:0);
                if(tn=="BODY"){
                    break;
                }
                a=a.offsetParent;
            }
            return p;
        },

        checkComplete: function(){
            if(this.__img&&this.__img.complete){
                this.__onload();
            }
        },

        __onload : function(){
            clearInterval(this.__timeId);
            var w=this.__img.width;
            var h=this.__img.height;
        },

        showPreview: function(e){
            this.hidePreview(e);
            previewFrom=e.target||e.srcElement;
            previewTimeoutId=setTimeout('Photo._showPreview()',500);
            this.__img=null;
        },

        hidePreview: function(e){
            if(e){
                var toElement=e.relatedTarget||e.toElement;
                while(toElement){
                    if(toElement.id=='PreviewBox')
                        return;
                    toElement=toElement.parentNode;
                }
            }
            try{
                clearInterval(this.__timeId);
                this.__img=null;
            }
            catch(e){}
            clearTimeout(previewTimeoutId);
        },

        _showPreview: function(){
            this.__img=new Image();
            if(previewFrom.tagName.toUpperCase()=='A')
                previewFrom=previewFrom.getElementsByTagName('img')[0];
            var largeSrc=previewFrom.getAttribute("large-src");
            var picLink=previewFrom.getAttribute("pic-link");
            if(!largeSrc)
                return;
            else{
                this.__img.src=largeSrc;
                this.href=picLink;
                this.__timeId=setInterval("Photo.checkComplete()",20);
                var pos=this.getPosXY(previewFrom,[75,-2]);
            }
        }
    };
}()
/********************************************* 相片操作 *******************************************************/

$(function() {
    $("#file_upload").change(function() {
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#previewImg");
        if(fileObj && fileObj.files && fileObj.files[0]){
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            $img.attr('src',dataURL);
        }else{
            dataURL = $file.val();

            // $img.css("filter",'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = scale,src="' + dataURL + '")');

            // var imgObj = document.getElementById("previewImg");
            // imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\"" + dataURL + "\")";
            // imgObj.style.width = "48px";
            // imgObj.style.height = "48px";

            var imgObj = document.getElementById("previewImg");
            // 两个坑:
            // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
            // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
            imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

        }
    });
});

/**
 * 身份证号码验证及出生日期截取
 */
function validateIdNo(obj)
{
	var idType = document.getElementsByName('idCode')[0].value;
	if(idType=="1" && obj.value)
	{
		//1.身份证号码验证
		if(!validIdCode(obj.value)) 
			{
			$('#idNo').val("");
			return;
			}
			
		//2.根据身份证号码截取出生日期
		document.getElementsByName('birthdayStr')[0].value = getBirthdatByIdNo(obj.value);
		
		//3.计算年龄
		var iIdNo = trim(obj.value);
		if (iIdNo.length == 15) {
			tmpStr = iIdNo.substring(6, 12);
			tmpStr = "19" + tmpStr;
			tmpStr = tmpStr.substring(0, 4) +  tmpStr.substring(4, 6) 
					+ tmpStr.substring(6)
			countAge(tmpStr);
		} else {
			tmpStr = iIdNo.substring(6, 14);
			tmpStr = tmpStr.substring(0, 4)  + tmpStr.substring(4, 6) 
					+ tmpStr.substring(6)
			countAge(tmpStr);
		}
	}
}
function numAge(obj){
	var birthday =  $(obj).val().replace(/-/g,"");
	countAge(birthday);
	
}

function countAge(birthday){
	
	var birthdayYear = birthday.substring(0,4);
	var currentYear = new Date().getFullYear();
	
	var age=parseInt(currentYear)-parseInt(birthdayYear);
	
	var nowtime = new Date();  
	var month = padleft0(nowtime.getMonth() + 1);  
	var day = padleft0(nowtime.getDate());  
	if(age>0){
	  var birthdayMonth = birthday.substring(4,6);
	  if((month-birthdayMonth)>0){
		
	  }else if((month-birthdayMonth)==0){
		  var birthdayDate=birthday.substring(6,8);
		  if(day-birthdayDate<0){
			  age=age-1;
		  }
	  }else{
		  age=age-1;
	  }
   }	
	$('#ageUpdate').val(age);
}
//补齐两位数  
function padleft0(obj) {  
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
} 

function getnowtime() {  
    var nowtime = new Date();  
    var year = nowtime.getFullYear();  
    var month = padleft0(nowtime.getMonth() + 1);  
    var day = padleft0(nowtime.getDate());  
   /* var hour = padleft0(nowtime.getHours());  
    var minute = padleft0(nowtime.getMinutes());  
    var second = padleft0(nowtime.getSeconds()); */ 
   // var millisecond = nowtime.getMilliseconds(); millisecond = millisecond.toString().length == 1 ? "00" + millisecond : millisecond.toString().length == 2 ? "0" + millisecond : millisecond;  
    return year + "-" + month + "-" + day ;  
}  
 

/**
 * 参加工作日期大于出生日期
 *//*
function checkDay(obj){
	var birthday =  new Date($('#birthdayStr').val().replace(/-/g,"/"));
	var start = new Date($('#entryCurrentUnitDateStr').val().replace(/-/g,"/"));
	if(start < birthday){
		MessageBox.alert('提示',"进入本单位时间应该大于出生日期");
		$('#entryCurrentUnitDateStr').val(null);
	}
}*/
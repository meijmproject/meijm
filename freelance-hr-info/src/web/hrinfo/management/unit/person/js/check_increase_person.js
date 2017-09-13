$(function(){
//	$('#submitIncreasePerson').click(function(){
//        $('#increasePersonForm').submit();
//    });
	
	//自定义规则
	$.validator.addMethod("checkContractDate", function(value, element){
        var contractBegin = $('#contractBeginStr').val();
    	var contractEnd = $('#contractEndStr').val();
        var reg = new RegExp('-','g');
        contractBegin = contractBegin.replace(reg,'/');//正则替换
        contractEnd = contractEnd.replace(reg,'/');
        contractBegin = new Date(parseInt(Date.parse(contractBegin),10));
        contractEnd = new Date(parseInt(Date.parse(contractEnd),10));
        if(contractBegin>contractEnd){
            return false;
        }else{
            return true;
        }

    }, "合同截止时间不能早于合同开始时间");
	$.validator.addMethod("checkProbationDate", function(value, element){
        var probationBegin = $('#probationBeginStr').val();
    	var probationEnd = $('#probationEndStr').val();
        var reg = new RegExp('-','g');
        probationBegin = probationBegin.replace(reg,'/');//正则替换
        probationEnd = probationEnd.replace(reg,'/');
        probationBegin = new Date(parseInt(Date.parse(probationBegin),10));
        probationEnd = new Date(parseInt(Date.parse(probationEnd),10));
        if(probationBegin>probationEnd){
            return false;
        }else{
            return true;
        }

    }, "试用期结束时间不能早于试用期开始时间");
    
    
    //校验
    $("#increasePersonForm").validate({
	    rules:{
	        name:{
	            required: true
	        },
	        sexCode:{
	            required: true
	        },
	        personCode:{
	            required: true
	        },
	        personStatus: {
	            required: true
	        },
	        personTypeName: {
	            required: true
	        },
	        startWorkDateStr: {
	            required: true
	        },
	        hireDeptName: {
	            required: true
	        },
	        idName: {
	            required: true
	        },
	        idNo: {
	            required: true,
	        },
	        birthdayStr: {
	            required: true
	        },
	        isSz: {
	            required: true
	        },
	        mobilePhone: {
	            required: true,
	            isNum: true
	        },
	        unitName: {
	            required: true
	        },
	        entryCurrentUnitType: {
                required: true
            },
            contractType: {
                required: true
            },
            contractBeginStr: {
                required: true
            },
            contractEndStr:{
            	required: true,
            	checkContractDate:function(){
            		contractEndStr = $("#contractEndStr").val();
            		if(contractEndStr!=''){
            			return true;
            		}
            		return false;
            	}
            },
            contractEndActualStr: {
            	compareDate:function(){
            		var contractEndActualStr = $("#contractEndActualStr").val();
            		if(contractEndActualStr!=''){
            			return true;
            		}
            		return false;
            	}
            },
            probationBeginStr: {
                required: function(){
                	var probationFlag = $("#probationFlag").val();//是否存在试用期
                	if(probationFlag=='1'){
//                		$("#probationBeginFlag").show();
                		return true;
                	}
//                	$("#probationBeginFlag").hide();
                	return false;
                }
            },
            probationEndStr: {
            	checkProbationDate: true,
                required: function(){
                	var probationFlag = $("#probationFlag").val();//是否存在试用期
                	if(probationFlag=='1'){
//                		$("#probationEndFlag").show();
                		return true;
                	}
//                	$("#probationEndFlag").hide();
                	return false;
                }
                
            },
            personOrderView: {
            	isNumber: true
            },
            entryCurrentUnitDateStr: {
            	required: true
            }
	    },
	    messages: {
	        name: {
	            required: "请输入姓名"
	        },
	        sexCode:{
	            required: "请选择性别"
	        },
	        personCode:{
	            required: "请输入工号"
	        },
	        personStatus: {
	            required: "请选择人员状态"
	        },
	        personTypeName: {
	            required: "请选择人员类别"
	        },
	        startWorkDateStr: {
	            required: "请选择参加工作时间"
	        },
	        hireDeptName: {
	            required: "请选择所在科室"
	        },
	        idName: {
	            required: "请选择证件类别"
	        },
	        idNo: {
	            required: "请输入证件号码"
	        },
	        birthdayStr: {
	            required: "请选择出生日期"
	        },
	        isSz: {
	            required: "请选择是否本地户口"
	        },
	        mobilePhone: {
	            required: "请输入手机号码"
	        },
	        unitName: {
	            required: "请选择工作单位"
	        },
	        entryCurrentUnitType: {
                required: "请选择进入单位方式"
            },
            contractType: {
                required: "请选择合同签订方式"
            },
            contractBeginStr: {
                required: "请选择合同开始时间"
            },
            contractEndStr:{
            	required: "请输入合同截止时间",
            	checkContractDate:"合同截止时间不能早于合同开始时间"
            },
            contractEndActualStr: {
            	compareDate: "合同实际结束日期不能早于合同开始时间"
            },
            probationBeginStr: {
                required: "是否存在试用期为是时，试用期开始时间必填"
            },
            probationEndStr: {
                required: "是否存在试用期为是时，试用期结束时间必填",
                checkProbationDate: "试用期结束时间不能早于试用期开始时间"
            },
            personOrderView: {
            	isNumber: "人员排序号必须为数字格式"
            },
            entryCurrentUnitDateStr: {
            	required: "进入单位时间为必填项"
            }
	    },
        errorLabelContainer: $("#increasePersonForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
        	var imageNewSrc = $('#file_upload').val();
            if(imageNewSrc!='')
            {
                var options = {
                    url:$('#increasePersonForm').attr("action"),
                    dataType:  'json',
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
                                $("#increasePersonForm div.md-error").css('display','block');
    	                        if($("#increasePersonForm div.md-error .back-error").length>0) {
    	                        	$("#increasePersonForm div.md-error .back-error").html(data.message);
    	                        }else {
    	                        	$("#increasePersonForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
    	                        }
                            }
                            return;
                        } catch (e) {
                        }
                        $("#popdown-dialog").html(data);
                        $("#increasePersonForm div.md-error").css('display','block');
                        $("#increasePersonForm div.md-error .back-error").html(data.message);
                    }
                };
                $('#increasePersonForm').ajaxSubmit(options);
            }
            else
            {
                $.ajax({
                    url : $('#increasePersonForm').attr("action"),
                    data :  $("#increasePersonForm").serializeArray(),
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
                                	worktop.form.goQuery();	
                                }else{
                                $('#'+url_id).load($('#'+url_id).attr('url'),{personOid:url_personoid,Id:url_id});
                            }
                            }
                            else
                            {
                                $("#increasePersonForm div.md-error").css('display','block');
    	                        if($("#increasePersonForm div.md-error .back-error").length>0) {
    	                        	$("#increasePersonForm div.md-error .back-error").html(data.message);
    	                        }else {
    	                        	$("#increasePersonForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
    	                        }
                            }
                            return;
                        } catch (e) {
                        }
                        $("#popdown-dialog").html(data);
                        $("#increasePersonForm div.md-error").css('display','block');
                        $("#increasePersonForm div.md-error .back-error").html(data.message);
                    }
                });
            }
        }
    });

});


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
	if(idType=="1" && obj)
	{
		//1.身份证号码验证
		if(!validIdCode(obj.value)) 
			{
			$('#idNo').val("");
			return;
			}
			
		//2.根据身份证号码截取出生日期
		document.getElementsByName('birthdayStr')[0].value = getBirthdatByIdNo(obj.value);
	}
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


$(function() {
	$('#probationFlag').change(function() {
		var probationFlagCurrent = $('#probationFlag').children('option:selected').val();//是否存在试用期
		if(probationFlagCurrent=='1') {
			$("#probationBeginFlag").show();
			$("#probationEndFlag").show();
		}else {
			$("#probationBeginFlag").hide();
			$("#probationEndFlag").hide();
		}
	});
});

jQuery.validator.addMethod("compareDate", function(value, element){
	var startDate = $("#contractBeginStr").val().replace(/-/g,'/'),
    	endDate = $("#contractEndActualStr").val().replace(/-/g,'/');
		startDate = new Date(parseInt(Date.parse(startDate),10));
		endDate = new Date(parseInt(Date.parse(endDate),10));
    return startDate<=endDate;
}, "截止时间不能早于开始时间!");
//验证输入值为数字
jQuery.validator.addMethod("isNum", function(value, element) {   
   // var tel = /^[0-9]*$/;
    var tel = /^1[34578]\d{9}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的*国内*手机号码");

jQuery.validator.addMethod("oidCheck", function(value, element){
	var hireDeptOid = $("#hireDeptOid").val();//名称
    var hiddenHireDeptOid = $("#hiddenHireDeptOid").val();//代码
    
    if(reviewUnitOid!=null&&reviewUnitOid!=""){
    	if(hiddenReviewUnitOid!=null&&hiddenReviewUnitOid!=""){
    		return true;
    	}else{
    		return false;
    	}
    }else{
    	return true;
    }

}, "请选择所在科室");

//验证输入值为数字
jQuery.validator.addMethod("isNumber", function(value, element) {
	var num = /^[0-9\.]*$/;
    return this.optional(element) || (num.test(value));
}, "请输入正确的数字!");
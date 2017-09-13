$(function(){
	$('#saveSyGwEmployInfo').click(function(){
		statusFlag();
		isMPositionFlag();
        $('#syGwEmployInfoForm').submit();
    });
	$.validator.addMethod("checkDate", function(value, element){
        var beginDate = $('#beginDateStr').val();
    	var endDate = $('#endDateStr').val();
        var reg = new RegExp('-','g');
        beginDate = beginDate.replace(reg,'/');//正则替换
        endDate = endDate.replace(reg,'/');
        beginDate = new Date(parseInt(Date.parse(beginDate),10));
        endDate = new Date(parseInt(Date.parse(endDate),10));
        if(beginDate>endDate){
            return false;
        }else{
            return true;
        }

    }, "岗位聘任拟截止时间不能早于岗位聘任开始时间");
    $("#syGwEmployInfoForm").validate({
        rules:{
        	positioningStatus:{
                required: true
            },
            dutyUnitName:{
                required: true
            },
            deptName:{
                required: true
            },
            positionName: {
                required: function(){
                    var positioningStatus = $("#positioningStatus").val();//任职状态
                    var positionType = $("#positionType").val();//岗位类别
                    var positionLevel = $("#positionLevel").val();//岗位级别
                    if(positioningStatus=='001')
                    {
                        return true;
                    }
                    else if(positionType!=''||positionLevel!='')
                    {
                        return true;
                    }
                    return false;
                }
            },
            positionType: {
                required: function(){
                    var positioningStatus = $("#positioningStatus").val();//任职状态
                    var positionName = $("#positionName").val();//岗位名称
                    var positionLevel = $("input[name='positionLevel']").val();//岗位级别
                    if(positioningStatus=='001')
                    {
                        return true;
                    }
                    else if(positionName!=''||positionLevel!='')
                    {
                        return true;
                    }
                    return false;
                }
            },
            positionLevelName: {
                required: function(){
                    var positioningStatus = $("#positioningStatus").val();//任职状态
                    var positionName = $("#positionName").val();//岗位名称
                    var positionType = $("#positionType").val();//岗位类别
                    if(positioningStatus=='001')
                    {
                        return true;
                    }
                    else if(positionName!=''||positionType!='')
                    {
                        return true;
                    }
                    return false;
                }
            },
            dutyRecordType: {
                required: true
            },
            isMPosition:{
            	required: true
            },
            beginDateStr: {
                required: true
            },
            endDateStr:{
            	checkDate:function(){
            		endDateStr = $("#endDateStr").val();
            		if(endDateStr!=''){
            			return true;
            		}
            		return false;
            	}
            }/*,
            endDateStr: {
                required: function(){
                    var positioningStatus = $("#positioningStatus").val();//任职状态
                    if(positioningStatus=='002')
                    {
                        return true;
                    }
                    return false;
                }
            }*/
        },
        messages: {
        	positioningStatus: {
                required: "请选择岗位聘任状态,"
            },
            dutyUnitName: {
                required: "请输入聘任单位名称,"
            },
            deptName: {
                required: "请输入内设机构名称,"
            },
            positionName: {
                required: "岗位聘任状态为在任、或者岗位类别、岗位级别不为空、岗位名称必填,"
            },
            positionType: {
                required: "岗位聘任状态为在任、或者岗位名称、岗位级别不为空、岗位类别必须选择,"
            },
            positionLevelName: {
                required: "岗位聘任状态为在任、或者岗位名称、岗位类别不为空、岗位级别必须选择,"
            }/*,
            endDateStr: {
                required: "岗位聘任状态为不在任，岗位聘任拟截止时间必填"
            }*/,
            dutyRecordType: {
                required: "请选择职务类型,"
            },
            beginDateStr: {
                required: "请选择岗位聘任开始时间,"
            },
            endDateStr:{
            	checkDate:"岗位聘任拟截止时间不能早于岗位聘任开始时间,"
            },
            isMPosition:{
            	required:"请选择是否主岗位,"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#syGwEmployInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#syGwEmployInfoForm').attr("action"),
                data :  $("#syGwEmployInfoForm").serializeArray(),
                async : false,
                success : function(data) {
                    try {
                        data = $.parseJSON(data);

                        if (data.success) {
                            $.fn.close_popdown();
                            var url_id=$('#url_id').val();
                            var url_personoid=$('#personOid').val();
                            var url = $('#'+url_id).attr('url');
                            findPageUrl(url_id,url_personoid,url);
                        }
                        else
                        {
                            $("#syGwEmployInfoForm div.md-error").css('display','block');
                            if($("#syGwEmployInfoForm div.md-error .back-error").length>0) {
                            	$("#syGwEmployInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#syGwEmployInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                        return;
                    } catch (e) {
                    }
                    $("#popdown-dialog").html(data);
                    $("#syGwEmployInfoForm div.md-error").css('display','block');
                }
            });
        }
    });
});
function changePositionName(positionType)
{   
    if(positionType=='1')
    {
    	$("#positionLevel").attr("opt","A1010");
    }
    else if(positionType=='2')
    {
    	$("#positionLevel").attr("opt","A1020");
    }
    else if(positionType=='3')
    {
    	$("#positionLevel").attr("opt","A1030");
    }
    $("#positionLevel").val('');
    $("input[name='positionLevel']").val('');
}

function initPositionName()
{
    var positionType = $("#positionType").val();
    if(positionType=='1')
    {
        $("#positionLevel").attr("opt","A1010");
    }
    else if(positionType=='2')
    {
        $("#positionLevel").attr("opt","A1020");
    }
    else if(positionType=='3')
    {
        $("#positionLevel").attr("opt","A1030");
    }
    else
	{
        $("#positionLevel").attr("opt","");
	}
}
function positionStatusOnchange(positioningStatus)
{
    var dutyUnitName = $('#dutyUnitNameActive').val();
    var dutyUnitOid = $('#dutyUnitOidActive').val();
    
    var currentUnitName = $('#currentUnitName').val();
    var currentUnitOid = $('#currentUnitOid').val();
    if(positioningStatus=='002')
    {
        //添加不在职元素
        document.getElementById("dutyUnitNameId").innerHTML='<input type="text" class="modal_iput" id="dutyUnitName" name="dutyUnitName" value="'+dutyUnitName+'">';
        $("#deptInput").css('display','block');
        $("#deptSelect").css('display','none');
    }
    else if(positioningStatus=='001')
    {
        //添加在职元素
        document.getElementById("dutyUnitNameId").innerHTML='<input type="text" id="dutyUnitName" readonly class="readonly" name="dutyUnitName" value="'+dutyUnitName+'">' +
            '<input type="hidden" id="dutyUnitOid" name="dutyUnitOid" value="'+currentUnitOid+'">';
        $("#deptInput").css('display','none');
        $("#deptSelect").css('display','block');
        
        if(currentUnitName!=""&&currentUnitOid!=""){
        	//给单位名称和单位oid赋值
            /*$("#dutyUnitNameId").text(currentUnitName);*/
            $("#dutyUnitName").val(currentUnitName);
            $("#dutyUnitOid").val(currentUnitOid);
        }
        
    }
}
function removeElement(_element){
    if(_element){
        _element.removeChildNodes();
    }
}
function statusFlag(){
	var status = $("#positioningStatus").val();
	var positionType = $("#positionType").val();
	if(status == preStatus&&positionType==prePositionType)
		{
		$("#statusFlag").val(false);
		}else{$("#statusFlag").val(true);}
}
function isMPositionFlag(){
	var isMPosition = $("#isMPosition").val();
	if(isMPosition == preIsMPosition)
		{             
		$("#isMPositionFlag").val(false);
		}else{$("#isMPositionFlag").val(true);}
}
function changeDept(){
	$("#deptName").val(document.getElementById("dept").innerHTML);
	$("#deptOid").val(document.getElementById("dept").value);
    }

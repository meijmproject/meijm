$(function(){
	$('#saveYnGwEmployInfo').click(function(){
		statusFlag();
        $('#ynGwEmployInfoForm').submit();
    });
	$.validator.addMethod("checkDate", function(value, element){
        var beginDate = $('#hisBeginDateStr').val();
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
    $("#ynGwEmployInfoForm").validate({
        rules:{
            hisPositionStatus:{
                required: true
            },
            deptName:{
                required: true
            },
            hisPositionName: {
            	required:true
            }/*,
            hisPositionName: {
                required: function(){
                    var hisPositionStatus = $("#hisPositionStatus").val();//任职状态
                    var hisPositionType = $("#hisPositionType").val();//岗位类别
                    var hisPositionLevel = $("#hisPositionLevel").val();//岗位级别
                    if(hisPositionStatus=='001')
                    {
                        return true;
                    }
                    else if(hisPositionType!=''||hisPositionLevel!='')
                    {
                        return true;
                    }
                    return false;
                }
            }*//*,
            hisPositionType: {
                required: function(){
                    var hisPositionStatus = $("#hisPositionStatus").val();//任职状态
                    var hisPositionName = $("#hisPositionName").val();//岗位名称
                    var hisPositionLevel = $("input[name='hisPositionLevel']").val();//岗位级别
                    if(hisPositionStatus=='001')
                    {
                        return true;
                    }
                    else if(hisPositionName!=''||hisPositionLevel!='')
                    {
                        return true;
                    }
                    return false;
                }
            }*/,
            positionLevelName: {
                required: function(){
                    var hisPositionStatus = $("#hisPositionStatus").val();//任职状态
                    var hisPositionName = $("#hisPositionName").val();//岗位名称
                    //var hisPositionType = $("#hisPositionType").val();//岗位类别
                    if(hisPositionStatus=='001')
                    {
                        return true;
                    }
                    else if(hisPositionName!='')
                    {
                        return true;
                    }
                    return false;
                }
            },
            hisBeginDateStr: {
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
                    var hisPositionStatus = $("#hisPositionStatus").val();//任职状态
                    if(hisPositionStatus=='002')
                    {
                        return true;
                    }
                    return false;
                }
            }*/
        },
        messages: {
            hisPositionStatus: {
                required: "请选择岗位聘任状态,"
            },
            deptName: {
                required: "请输入内设机构名称,"
            },
            hisPositionName: {
                required: "请选择岗位名称,"
            }/*,
            hisPositionName: {
                required: "岗位聘任状态为在任，或者岗位类别、岗位级别不为空，岗位名称必须选择"
            },
            hisPositionType: {
                required: "岗位聘任状态为在任，或者岗位名称、岗位级别不为空，岗位类别必须选择"
            }*/,
            positionLevelName: {
                required: "岗位聘任状态为在任，或者岗位名称不为空，岗位级别必须选择,"
            }/*,
            endDateStr: {
                required: "岗位聘任状态为不在任，岗位聘任拟截止时间必填"
            }*/,
            hisBeginDateStr: {
                required: "请选择岗位聘任开始时间,"
            },
            endDateStr:{
            	checkDate:"岗位聘任拟截止时间不能早于岗位聘任开始时间"
            }
        },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#ynGwEmployInfoForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            $.ajax({
                url : $('#ynGwEmployInfoForm').attr("action"),
                data :  $("#ynGwEmployInfoForm").serializeArray(),
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
                            $("#ynGwEmployInfoForm div.md-error").css('display','block');
                            if($("#ynGwEmployInfoForm div.md-error .back-error").length>0) {
                            	$("#ynGwEmployInfoForm div.md-error .back-error").html(data.message);
                            }else {
                            	$("#ynGwEmployInfoForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                            }
                        }
                        return;
                    } catch (e) {
                    }
                    $("#popdown-dialog").html(data);
                    $("#ynGwEmployInfoForm div.md-error").css('display','block');
                }
            });
        }
    });
});
function changeWorkType(hisPositionType)
{   
    if(hisPositionType=='1')
    {
    	//临床
    	$("#hisPositionLevel").attr("opt","1");
    }
    else if(hisPositionType=='2')
    {
    	//行政与后勤
    	$("#hisPositionLevel").attr("opt","2");
    }
    else if(hisPositionType=='3')
    {
    	//研究人员
    	$("#hisPositionLevel").attr("opt","4");
    }
    $("#hisPositionLevel").val('');
    $("input[name='hisPositionLevel']").val('');
}

function initWorkType()
{
    var hisPositionType = $("#hisWorkType").val();
    if(hisPositionType=='1')
    {
        $("#hisPositionLevel").attr("opt","1");
    }
    else if(hisPositionType=='2')
    {
        $("#hisPositionLevel").attr("opt","2");
    }
    else if(hisPositionType=='3')
    {
        $("#hisPositionLevel").attr("opt","4");
    }
    else
	{
        $("#hisPositionLevel").attr("opt","");
	}
}
function positionStatusOnchange(positioningStatus)
{
    if(positioningStatus=='002')
    {
        //添加不在职元素
        $("#deptInput").css('display','block');
        $("#deptSelect").css('display','none');
    }
    else if(positioningStatus=='001')
    {
        //添加在职元素
        $("#deptInput").css('display','none');
        $("#deptSelect").css('display','block');
    }
}
function removeElement(_element){
    if(_element){
        _element.removeChildNodes();
    }
}
function statusFlag(){
	var status = $("#hisPositionStatus").val();
	if(status == preStatus)
		{
		$("#statusFlag").val(false);
		}else{$("#statusFlag").val(true);}
}
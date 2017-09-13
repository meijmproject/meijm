$(function(){
    $("#formAttendanceUpdate").validate({
	    rules:{
	    	attendanceYear:{
		            required: true
		        },
		    attendancePeriod:{
		            required: true
		    },
	        noAttendanceDays: {
	            required: true
	        }
	    },
	    messages: {
	    	attendanceYear:{
	            required: "请选择年度"
	        },
	        attendancePeriod:{
	            required: "请选择周期"
	        },
	        noAttendanceDays: {
	            required: "请输入非出勤天数"
	        }
	    },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#formAttendanceUpdate div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
            var options = {
            url:$('#formAttendanceUpdate').attr("action"),
            dataType:  'json',
            type: 'post',
            success : function(data) {
            	try {
                    if (data.success) {
                        $.fn.close_popdown();
                        Widget.close();
                        $.get('getYearList.do?method=getYearList',function(data) {
                        	$('#startYear').empty();
                        	$('#endYear').empty();
                        	$('#startYear').append('<option value="">--请选择--</option>');
                        	$('#endYear').append('<option value="">--请选择--</option>');
                        	$('#startMonth').val('');
                        	$('#endMonth').val('');
                        	data.forEach(function(v,i) {
                        		$('#startYear').append('<option value="'+v+'">'+v+'</option>');
                        		$('#endYear').append('<option value="'+v+'">'+v+'</option>');
                        	});
                        	 worktop.form.goQuery();
                        },'json');
                    }
                    else
                    {
                    	$("#formAttendanceUpdate div.md-error").css('display','block');
                      if($("#formAttendanceUpdate div.md-error .back-error").length>0) {
                      	$("#formAttendanceUpdate div.md-error .back-error").html(data.message);
                      }else {
                      	$("#formAttendanceUpdate div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                      }
                    }
                    return;
                } catch (e) {
                }
            }
        };
        $('#formAttendanceUpdate').ajaxSubmit(options);
    }
});
});

function init(valuYear,valuePerioid){
	$("#attendanceYear").val(valuYear);
	$("#attendancePeriod").val(valuePerioid);
}
function changeNoAttendanceDays(){
	var noAttendanceDays=$("#noAttendanceDays").val();
	var calendarDays=$("#calendarDays").val();
	if(calendarDays-noAttendanceDays<0){
		MessageBox.alert('提示',"非出勤天数不能大于日历天数");
		$("#noAttendanceDays").val('');
	}else{
		$("#attendanceDays").val(calendarDays-noAttendanceDays);
	}
}
function DayNumOfMonth(attendanceYear,attendancePeriod)
{
	if(''!=attendanceYear&&''!=attendancePeriod&&null!=attendanceYear&&null!=attendancePeriod){
		var d = new Date(attendanceYear,attendancePeriod,0);
		return d.getDate();
	}
	return '';
}
function changeDays() {
	var attendanceYear=$("#attendanceYear").val();
	var attendancePeriod=$("#attendancePeriod").val();
	var days = DayNumOfMonth(attendanceYear,attendancePeriod);
	$('#calendarDays').val(days);
	changeNoAttendanceDays()
}
function changeMonth() {
	changeDays();
	changeDate();
}
function changeDate(){
	var attendanceYear=$("#attendanceYear").val();
	var attendancePeriod=$("#attendancePeriod").val();
	var calendarDays=$("#calendarDays").val();
	if(''!=attendanceYear&&''!=attendancePeriod&&null!=attendanceYear&&null!=attendancePeriod){
		if(attendancePeriod<10){
			$("#startDateStr").val(attendanceYear+"-0"+attendancePeriod+"-01");
			$("#endDateStr").val(attendanceYear+"-0"+attendancePeriod+"-"+calendarDays);
		}else{
			$("#startDateStr").val(attendanceYear+"-"+attendancePeriod+"-01");
			$("#endDateStr").val(attendanceYear+"-"+attendancePeriod+"-"+calendarDays);
		}
	}else{
		$("#startDateStr").val('');
		$("#endDateStr").val('');
	}
}
function changeYear(value){
	$.ajax({
		url : 'getAttendancePeriodList.do?method=getAttendancePeriodList',
		data :  {changeYear:value},
		dataType : 'json',
		error : function(x,t) {
			alert(data.massage);
		},
		async : false,
		success : function(data) { 
				if(null != data.rs ){
					$("#attendancePeriod").empty();
					for(var i=1; i<13; i++) {
						$("#attendancePeriod").append("<option value="+i+">"+i+"</option>");
					}
					data.rs&&data.rs.forEach(function(v,i) {
						if($("#attendanceYear").val()!=$('#initYear').val()) {
							$("#attendancePeriod").find('option[value="'+v.attendancePeriod+'"]').remove();
						}else if(v.attendancePeriod==$('#initMonth').val()){
							
						}else {
							$("#attendancePeriod").find('option[value="'+v.attendancePeriod+'"]').remove();
						}
					});
					$("#attendancePeriod option").eq(0).selected();
					changeDays();
					changeDate();
				}
		}
	});
}
function changeStartYear(value){
	if(''==value||null==value){
		$("#startMonth").val('');
		$("#startMonth").empty();
	}else{
		$.ajax({
			url : 'getAttendancePeriodList.do?method=getAttendancePeriodList',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#startMonth").empty();
					if(null != data.rs ){
						data.rs&&data.rs.forEach(function(v,i) {
							$("#startMonth").append("<option value="+v.attendancePeriod+">"+v.attendancePeriod+"</option>");
						});
					}
			}
		});
	}
}
function changeEndYear(value){
	if(''==value||null==value){
		$("#endMonth").val('');
		$("#endMonth").empty();
	}else{
		$.ajax({
			url : 'getAttendancePeriodList.do?method=getAttendancePeriodList',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#endMonth").empty();
					if(null != data.rs ){
						data.rs&&data.rs.forEach(function(v,i) {
							$("#endMonth").append("<option value="+v.attendancePeriod+">"+v.attendancePeriod+"</option>");
						});
					}
			}
		});
	}
}
function clearNoNum(obj)
{
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    //只能输入两个小数
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); 
}

function changeInitYear(){
	var startYear=$("#startYear").val();
	var endYear=$("#endYear").val();
	var startMonth=$("#startMonth").val();
	var endMonth=$("#endMonth").val();
	if(''!=startYear&&''!=endYear&&null!=startYear&&null!=endYear){
		if(endYear-startYear<0){
			MessageBox.alert('提示', '截止年度不能小于开始年度');
			return;
		}
		else if(endYear-startYear==0){
			if(''!=startMonth&&''!=endMonth&&null!=startMonth&&null!=endMonth){
				if(endMonth-startMonth<0){
					MessageBox.alert('提示', '年度相同时，截止周期不能小于开始周期');
					return;
				}
			}
		}
	}
	worktop.form.goQuery();
}

/**
 * 根据设置的日期计算非出勤天数和应出勤天数
 */
function sumNoAttendanceDays()
{
	var noAttendanceDateStr = $("#noAttendanceDateStr").val();
	if(noAttendanceDateStr && noAttendanceDateStr!=null)
	{
		//设置title属性
		$("#noAttendanceDateStr").attr("title",noAttendanceDateStr);
		//切割非出勤日期，得到具体有多少天；
		var noAttendanceDateArr = noAttendanceDateStr.split(",");
		//设置非出勤天数的值
		$("#noAttendanceDays").val(noAttendanceDateArr.length);
		//计算应出勤天数
		changeNoAttendanceDays();
	}
}

//清空非出勤日期内容
function clearNoAttendanceDateStr()
{
	$("#noAttendanceDateStr").val('');
	//设置非出勤天数的值
	$("#noAttendanceDays").val(0);
	//计算应出勤天数
	changeNoAttendanceDays();
}
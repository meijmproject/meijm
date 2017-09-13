function changeOrgStartYear(value){
	if(''==value||null==value){
		$("#startMonth").val('');
		$("#startMonth").empty();
	}else{
		$.ajax({
			url : 'listOrgAttendancePeriod.do?method=listOrgAttendancePeriod',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#startMonth").empty();
					if(null != data.rs ){
						data.rs[0].newList&&data.rs[0].newList.forEach(function(v,i) {
							$("#startMonth").append("<option value="+v+">"+v+"</option>");
						});
					}
			}
		});
	}
}
function changeOrgEndYear(value){
	if(''==value||null==value){
		$("#endMonth").val('');
		$("#endMonth").empty();
	}else{
		$.ajax({
			url : 'listOrgAttendancePeriod.do?method=listOrgAttendancePeriod',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#endMonth").empty();
					if(null != data.rs ){
						data.rs[0].newList&&data.rs[0].newList.forEach(function(v,i) {
							$("#endMonth").append("<option value="+v+">"+v+"</option>");
						});
					}
			}
		});
	}
}
function changePersonStartYear(value){
	if(''==value||null==value){
		$("#startMonth1").val('');
		$("#startMonth1").empty();
	}else{
		$.ajax({
			url : 'listPersonAttendancePeriod.do?method=listPersonAttendancePeriod',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#startMonth1").empty();
					if(null != data.rs ){
						data.rs[0].newList&&data.rs[0].newList.forEach(function(v,i) {
							$("#startMonth1").append("<option value="+v+">"+v+"</option>");
						});
					}
			}
		});
	}
}
function changePersonEndYear(value){
	if(''==value||null==value){
		$("#endMonth1").val('');
		$("#endMonth1").empty();
	}else{
		$.ajax({
			url : 'listPersonAttendancePeriod.do?method=listPersonAttendancePeriod',
			data :  {changeYear:value},
			dataType : 'json',
			error : function(x,t) {
				alert(data.massage);
			},
			async : false,
			success : function(data) {
				    $("#endMonth1").empty();
					if(null != data.rs ){
						data.rs[0].newList&&data.rs[0].newList.forEach(function(v,i) {
							$("#endMonth1").append("<option value="+v+">"+v+"</option>");
						});
					}
			}
		});
	}
}
function changeInitYear(){
	 var startYear=$("#startYear").val();
	 var startMonth=$("#startMonth").val();
	 var endYear=$("#endYear").val();
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
	 attendanceOrgWorktop.form.goQuery();
}
function changePersonYear(){
	 var startYear=$("#startYear1").val();
	 var startMonth=$("#startMonth1").val();
	 var endYear=$("#endYear1").val();
	 var endMonth=$("#endMonth1").val();
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
	 attendancePersonWorktop.form.goQuery();
}
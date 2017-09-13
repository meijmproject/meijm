
$(function(){  
    $('#saveorginfo').click(function(){
    	$('#form_orginfo').submit();
    });
    
    $("#form_orginfo").validate({
    	rules:{
    		orgName: {
                required: true
            },
            levelCode: {
                required: true
            },
            orgFunction: {
                required: true
            },
            orderOfView: {
                 required: true
            }
    	},
    	messages: {
    		orgName: {
                required: "请输入内设机构名称！"
            },
            levelCode: {
                required: "请选择内设机构级别！"
            },
            orgFunction: {
                required: "请输入机构职能！"
            },
            orderOfView: {
                 required: "请输入排序号！"
            }
    	},
    	errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#form_orginfo div.modal-wrong"),
        wrapper: "li",
        submitHandler: function (form)
        {
        	$.ajax({
                url : $('#form_orginfo').attr("action"),
                data :  $("#form_orginfo").serializeArray(),
                dataType : 'json',
                type:'POST',
                async : false,
                success : function(data) {
                    if (data.success) {
                        $.fn.close_popdown();
                        //var org = 'orgTable';
                        //alert(data.message);
                        //window.location.href="goUnitInfoPage.do?method=goUnitInfoPage&unitOid="+data.message+"&pos="+org;
                        $('#test').load("deleteVerUbOrgInfo.do?method=goListOrgInfo&unitOid="+data.message);
                    }
                    else
                    {
                        $("#form_orginfo div.modal-wrong").css('display','block');
                        $(".wroglist").append(data.message);
                    }
                }
            });
        }    
    });
});
function deleteOrgInfo(orgOid,unitOid){
	MessageBox.confirm('提示', '确认删除？',function (yes){
	    if(yes=="yes"){
	//alert(resumeOid);
	//location.href = "deleteVerPbResumeInfo.do?method=deleteVerPbResumeInfo&resumeOid="+resumeOid;
	    	$.ajax({
	    		url : 'deleteVerUbOrgInfo.do?method=deleteVerUbOrgInfo&unitOid='+unitOid,
	    		data :  {orgOid:orgOid},
	    		dataType : 'json',
	    		type:'POST',
	    		error : function(x,t) {
	    			alert(t)
	    			alert("error occured!!!");
	    		},
	    		async : false,
	    		success : function(data) {
	    			if (data.success) {
	    				//window.location.reload();
	    				//var org = 'orgTable';
                        //window.location.href="goUnitInfoPage.do?method=goUnitInfoPage&unitOid="+data.message+"&pos="+org;
	    				$('#test').load("deleteVerUbOrgInfo.do?method=goListOrgInfo&unitOid="+data.message);
	    			}
	    		}
	    	});
	    }
	 });
}
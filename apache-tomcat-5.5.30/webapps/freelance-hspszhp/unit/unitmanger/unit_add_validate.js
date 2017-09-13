$(function(){
	 $('#saveUnit').click(function(){
	        $('#ubUnitForm').submit();
	    });
    $("#ubUnitForm").validate({
	    rules:{
	    	unitName:{
	            required: true
	        },
        	orderOfView:{
        		isThreeNum:true
	        },
	        mobilePhone:{
	        	isMobilePhone:true
	        },
	       /* phone:{
	        	isPhone:true
	        },*/
	        email:{
	        	isEmail:true
	        }/*,
	        fax:{
	        	isPhone:true
	        }*/
	    },
	    messages: {
	    	unitName: {
	            required: "请输入单位名称"
	        },
        	orderOfView:{
        		isThreeNum:'请输入三位数字'
	        },
	        mobilePhone:{
        		isMobilePhone:'手机格式不正确'
	        },
	        /*phone:{
        		isMobilePhone:'联系电话格式不正确'
	        },*/
	        email:{
	        	isEmail:'电子邮箱格式不正确'
	        }/*,
	        fax:{
	        	isPhone:'传真格式不正确'
	        }*/
	    },
        errorPlacement: function (error, element)
        {
            error.appendTo(element.parents("dl:first"));
        },
        errorLabelContainer: $("#ubUnitForm div.md-error"),
        wrapper: "span",
        submitHandler: function (form)
        {
                $.ajax({
                    url : $('#ubUnitForm').attr("action"),
                    data :  $("#ubUnitForm").serializeArray(),
                    dataType : 'json',
                    async : false,
                    success : function(data) {                   
                        try {
                            if (data.success) {
                            	expandNode();
                            	$('.main-container').load('goToUnitWorkTop.do?method=goToAddUbUnit');
                            }
                            else
                            {
                            	$("#ubUnitForm div.md-error").css('display','block');
                                if($("#ubUnitForm div.md-error .back-error").length>0) {
                                	$("#ubUnitForm div.md-error .back-error").html(data.message);
                                }else {
                                	$("#ubUnitForm div.md-error").append("<span class='back-error'>"+data.message+"</span>");
                                }
                            }
                            return;
                        } catch (e) {
                        }
                        $("#popdown-dialog").html(data);
                        $("#ubUnitForm div.md-error").css('display','block');
                    }
                });
        }
    });

});
function expandNode(){
	$.ajax({
		url : 'findOrg.do?method=findOrgList',
		data :　{controlDataAuthority : 'N'},
		dataType : 'json',
		type:'POST',
		/* beforeSend:function(XMLHttpRequest){ 
	    	Mask.showMask();
        },
		complete:function(XMLHttpRequest,textStatus){ 
	    	Mask.hideMask();
         }, */
		async : true,
		success : function(data) {
				$("#firstUl li").remove();
				$.each(data,function(index,item){
					var firstLi='';
					firstLi+="<li class='ln-firstli'>";
					firstLi+="<span class='ln-plus-icon'></span><a class='ln-firstli-a' href='javascript:void(0);' title='"+item.orgName+"' onclick=workUnitLocation('"+item.unitOid+"')>"+item.orgName+"</a>";
					firstLi+="<ul class='ln-second-nav' id='SecondUl"+item.organizationOid+"'></ul></li>";
					firstLi = $(firstLi);
				    firstLi.appendTo($("#firstUl"));
				    if(item.children){
				    	$.each(item.children,function(index1,item1){
							var SecondLi="";
							if(!item1.leaf)
							{
								 SecondLi+="<li class='ln-secondli'><span class='ln-line'></span>";
								 if(index1==item.children.length-1){
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a>";
								 }
							}else{
								 SecondLi+="<li class='ln-secondli'>";
								 if(index1==item.children.length-1){
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a> ";
								 }else{
									 SecondLi+=" <span class='ln-plus-icon'></span><a class='ln-secondli-a' href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') >"+item1.orgName+"</a>";
								 }
							}
							SecondLi+="<ul class='ln-third-nav' id='TrirdUl"+item1.organizationOid+"'></ul></li>";   
							SecondLi=$(SecondLi);  
							SecondLi.appendTo($("#SecondUl"+item.organizationOid));
							if(item1.children){
								$.each(item1.children,function(index2,item2){
									var TrirdLi="";
									if(!item2.leaf)
									{
										 TrirdLi+="<li class='ln-thirdli'><span class='ln-line'></span>";
										 if(index2==item1.children.length-1){
											 TrirdLi+=" <span class='ln-plus-icon'></span><a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }else{
											 TrirdLi+=" <span class='ln-plus-icon'></span><a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }
									}else{
										TrirdLi+="<li class='ln-thirdli'><span class='ln-line'></span>";
										 if(index2==item1.children.length-1){
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }else{
											 TrirdLi+=" <a class='ln-thirdli-a' href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')>"+item2.orgName+"</a>";
										 }
									}
									TrirdLi+="<ul class='ln-fourth-nav' id='ForthUl"+item2.organizationOid+"'></ul></li>";   
									TrirdLi=$(TrirdLi);  
									TrirdLi.appendTo($("#TrirdUl"+item1.organizationOid));
									if(!item2.leaf){
										$.each(item2.children,function(index3,item3){
											var ForthLi="";
											if(!item3.leaf)
											{
												 ForthLi+="<li class='ln-fourthli'><span class='ln-line'></span>";
												 if(index3==item2.children.length-1){
													 ForthLi+=" <span class='ln-plus-icon'><a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }else{
													 ForthLi+=" <span class='ln-plus-icon'><a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }
											}else{
												ForthLi+="<li class='ln-fourthli'><span class='ln-line'></span>";
												 if(index3==item2.children.length-1){
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }else{
													 ForthLi+=" <a class='ln-fourthli-a' href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') >"+item3.orgName+"</a>";
												 }
											}
											ForthLi+="</li>";   
											ForthLi=$(ForthLi);  
											ForthLi.appendTo($("#ForthUl"+item2.organizationOid));
										})
									}
					
								})
							}
				
						})
					}
				})
				$('.left-nav').find('ul').show();
		        $('.left-nav').find('.ln-plus-icon').attr('class','ln-minus-icon');
		        leftNavScrollHide();
//		     // 点击左导航"+"号展开子菜单
//		        $(document).on('click','.ln-plus-icon',function (e) {
//		            e.stopPropagation();//防止冒泡
//		            $(this).attr('class','ln-minus-icon');  //"+"号变"-"
//		            $(this).siblings('ul').show();
//
//		            leftNavScrollHide();
//		        });
//		        // 点击左导航"-"号收缩子菜单
//		        $(document).on('click','.ln-minus-icon',function (e) {
//		            e.stopPropagation();//防止冒泡
//		            $(this).attr('class','ln-plus-icon');  //"-"号变"+"
//		            $(this).siblings('ul').hide();
//
//		            leftNavScrollHide();
//		        });
//		        //点击左导航中li选中状态显示
//		        $('.left-nav li a').click(function (e) {
//		            e.stopPropagation();//防止冒泡
//		            // 给点击的li添加选中的样式，先将之前点击的li的样式去掉，保证只有一个li显示选中状态
//		            $('.left-nav').find('a').removeClass('left-nav-active');
//		            $(this).addClass('left-nav-active');
//		        });
//		    //默认显示右侧工作台  
//		    $('.ln-firstli-a').click();
			//$(".right-content").load("getUnitInformation.do?method=getUnitInformation");
		}
	});
}
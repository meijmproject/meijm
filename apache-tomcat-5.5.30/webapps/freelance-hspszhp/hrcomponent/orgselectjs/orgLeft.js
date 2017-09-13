function expandNode(){
	$.ajax({
		url : 'findOrg.do?method=findOrgList',
		dataType : 'json',
		type:'POST',
		 beforeSend:function(XMLHttpRequest){ 
	    	Mask.showMask();
        },
		complete:function(XMLHttpRequest,textStatus){ 
	    	Mask.hideMask();
         }, 
		async : true,
		success : function(data) {
				$("#firstUl li").remove();
				$.each(data,function(index,item){
					var firstLi='';
					firstLi+="<li class='PansFirstLi'><div class='PansFirstLi_div1'><p class='PansFirstLi_p1'> <b class='PansFirstLi_span1_shi'> <i></i> </b> <span class='NavBar_FirstLiImg NavBar_FirstLiImgJia'><i></i></span>";
					firstLi+="<a href='javascript:void(0);' title='"+item.orgName+"' onclick=workUnitLocation('"+item.organizationOid+"') class='PansFirstA_title'>"+item.orgName+"</a>";
					firstLi+="<div class='clear'></div></p></div>";
					firstLi+="<ul style='display:none' class='PansSecondUl' id='SecondUl"+item.organizationOid+"'></ul></li>";
					firstLi = $(firstLi);
					$(firstLi).find('.NavBar_FirstLiImg').click(function(){
						if($(this).parent().parent().siblings('.PansSecondUl').is(":hidden")){
							expandFirstUi(this);
					    }else{
					    	hideFirstUi(this);
					    }
					})
				    firstLi.appendTo($("#firstUl"));
				    if(item.children){
				    	$.each(item.children,function(index1,item1){
							var SecondLi="";
							if(!item1.leaf)
							{
								SecondLi+="<li><div class='PansSecondLi_div1'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
								 if(index1==item.children.length-1){
									 SecondLi+=" </i> </b><b class='PansSecondline2_LastOne'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
									 SecondLi+=" <a href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') class='PansSecondA_title'>"+item1.orgName+"</a> <div class='clear'></div></p></div>";
								 }else{
									 SecondLi+=" </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_SecondLiImg NavBar_SecondLiImgJia'><b></b></span>"
									 SecondLi+=" <a href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') class='PansSecondA_title'>"+item1.orgName+"</a> <div class='clear'></div></p></div>";
								 }
										 
							}else{
								 SecondLi+="<li><div class='PansSecondLi_div1' style='background-color:#eef1f4'><p class='PansSecondLi_p1'><b class='PansSecondLi_span1'><i class='pack_up_img_left_nav_bar'>";
								 if(index1==item.children.length-1){
									 SecondLi+=" </i> </b><b class='PansSecondline2_LastOne'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
									 SecondLi+=" <a href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') class='PansSecondA_title'>"+item1.orgName+"</a> <div class='clear'></div></p></div>";
								 }else{
									 SecondLi+=" </i> </b><b class='PansSecondline1'> <i></i></b><span class='NavBar_ThirdLiImg'><b></b></span>"
									 SecondLi+=" <a href='javascript:void(0);' title='"+item1.orgName+"' onclick=workLocation('"+item1.orgOid+"') class='PansSecondA_title'>"+item1.orgName+"</a> <div class='clear'></div></p></div>";
								 }
							}
							SecondLi+="<ul style='display: none' class='PansThirdUl' id='TrirdUl"+item1.organizationOid+"'></ul></li>";   
							SecondLi=$(SecondLi);  
							$(SecondLi).find('.NavBar_SecondLiImg').click(function(){
								if($(this).parent().parent().siblings('.PansThirdUl').is(":hidden")){
									expandSecondUi(this);
							      }else{
							    	  hideSecondUi(this);
							      }
							}) 
							SecondLi.appendTo($("#SecondUl"+item.organizationOid));
							if(item1.children){
								$.each(item1.children,function(index2,item2){
									var TrirdLi="";
									if(!item2.leaf)
									{
										 TrirdLi+="<li><div class='PansThirdLi_div1'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
										 if(index2==item1.children.length-1){
											 TrirdLi+="</i></b><b class='PansThirdline1'></b><b class='PansThirdline2_LastOne'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
											 TrirdLi+=" <a href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"') class='PansThirdA_title'>"+item2.orgName+"</a> <div class='clear'></div></p></div>";
										 }else{
											 TrirdLi+="</i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia'><b></b></span>"
											 TrirdLi+=" <a href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"') class='PansThirdA_title'>"+item2.orgName+"</a> <div class='clear'></div></p></div>";
										 }
									}else{
										TrirdLi+="<li><div class='PansThirdLi_div1'><p class='PansThirdLi_p1'><b class='PansThirdLi_span1'><i class='pack_up_img_left_nav_bar'>";
										 if(index2==item1.children.length-1){
											 TrirdLi+="</i></b><b class='PansThirdline1'></b><b class='PansThirdline2_LastOne'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia Nav_clearplus'><b></b></span>"
											 TrirdLi+=" <a href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"') class='PansThirdA_title'>"+item2.orgName+"</a> <div class='clear'></div></p></div>";
										 }else{
											 TrirdLi+="</i></b><b class='PansThirdline1'></b><b class='PansThirdline2'><i></i></b><span class='NavBar_ThirdLiImg  NavBar_ThirdLiImgJia Nav_clearplus'><b></b></span>"
											 TrirdLi+=" <a href='javascript:void(0);' title='"+item2.orgName+"' onclick=workLocation('"+item2.orgOid+"')  class='PansThirdA_title'>"+item2.orgName+"</a> <div class='clear'></div></p></div>";
										 }
									}
									TrirdLi+="<ul style='display: none' class='PansFourthUl' id='ForthUl"+item2.organizationOid+"'></ul></li>";   
									TrirdLi=$(TrirdLi);  
									$(TrirdLi).find('.NavBar_ThirdLiImg').click(function(){
										if($(this).parent().parent().siblings('.PansFourthUl').is(":hidden")){
											expandThirdUi(this);
								        }else{
								        	hideThirdUi(this);
								        }
									}) 
									TrirdLi.appendTo($("#TrirdUl"+item1.organizationOid));
									if(!item2.leaf){
										$.each(item2.children,function(index3,item3){
											var ForthLi="";
											if(!item3.leaf)
											{
												 ForthLi+="<li><div class='PansFourthLi_div1'><p class='PansFourthLi_p1'><b class='PansFourthLi_span1'><i class='pack_up_img_left_nav_bar'>";
												 if(index3==item2.children.length-1){
													 ForthLi+="</i></b><b class='PansFourthline1'></b><b class='PansFourthline2'></b><b class='PansFourthline3_LastOne'><i></i></b><span class='NavBar_FourthLiImg  NavBar_FourthLiImgJia'><b></b></span>"
													 ForthLi+=" <a href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') class='PansFourthA_title'>"+item3.orgName+"</a> <div class='clear'></div></p></div>";
												 }else{
													 ForthLi+="</i></b><b class='PansFourthline1'></b><b class='PansFourthline2'></b><b class='PansFourthline3'><i></i></b><span class='NavBar_FourthLiImg  NavBar_FourthLiImgJia'><b></b></span>"
													 ForthLi+=" <a href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') class='PansFourthA_title'>"+item3.orgName+"</a> <div class='clear'></div></p></div>";
												 }
											}else{
												ForthLi+="<li><div class='PansFourthLi_div1'><p class='PansFourthLi_p1'><b class='PansFourthLi_span1'><i class='pack_up_img_left_nav_bar'>";
												if(index3==item2.children.length-1){
													 ForthLi+="</i></b><b class='PansFourthline1'></b><b class='PansFourthline2'></b><b class='PansFourthline3_LastOne'><i></i></b><span class='NavBar_FourthLiImg Nav_clearplus NavBar_FourthLiImgJia'><b></b></span>"
													 ForthLi+=" <a href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') class='PansFourthA_title'>"+item3.orgName+"</a> <div class='clear'></div></p></div>";
												 }else{
													 ForthLi+="</i></b><b class='PansFourthline1'></b><b class='PansFourthline2'></b><b class='PansFourthline3'><i></i></b><span class='NavBar_FourthLiImg Nav_clearplus  NavBar_FourthLiImgJia'><b></b></span>"
													 ForthLi+=" <a href='javascript:void(0);' title='"+item3.orgName+"' onclick=workLocation('"+item3.orgOid+"') class='PansFourthA_title'>"+item3.orgName+"</a> <div class='clear'></div></p></div>";
												 }
											}
											ForthLi+="</li>";   
											ForthLi=$(ForthLi);  
											$(ForthLi).find('.NavBar_ThirdLiImg').click(function(){
												if($(this).parent().parent().siblings('.PansFourthUl').is(":hidden")){
													expandForthUi(this);
										        }else{
										        	hideForthUi(this);
										        }
											}) 
											ForthLi.appendTo($("#ForthUl"+item2.organizationOid));
										})
									}
					
								})
							}
				
						})
					}
				})
				/* expandFirstUi($('.NavBar_FirstLiImg'));
				expandSecondUi($('.NavBar_SecondLiImg'));
				expandThirdUi($('.NavBar_ThirdLiImg'));
				expandForthUi($('.NavBar_ThirdLiImg')); */
				$('.PansSecondUl').show();
				$('.PansThirdUl').show();
				$('.PansFourthUl').show();
				$('.NavBar_FirstLiImg').removeClass('NavBar_FirstLiImgJia');
				$('.NavBar_FirstLiImg').addClass('NavBar_FirstLiImgJian');
				
				$('.NavBar_SecondLiImg').removeClass('NavBar_SecondLiImgJia');
			     $('.NavBar_SecondLiImg').addClass('NavBar_SecondLiImgJian');
			      $('.NavBar_ThirdLiImg').each(function(){
				     if($(this).hasClass('NavBar_ThirdLiImgJia')&&!$(this).hasClass('Nav_clearplus')){
				    	 $(this).removeClass('NavBar_ThirdLiImgJia').addClass('NavBar_ThirdLiImgJian');
					     }
				  }) 
			    /*  $('.NavBar_ThirdLiImg').removeClass('NavBar_ThirdLiImgJia');
			     $('.NavBar_ThirdLiImg').addClass('NavBar_ThirdLiImgJian'); */
		    //默认显示右侧工作台  
			$("#rightpage").load("goTopersonWorkBench.do?method=goTopersonWorkBench");
		}
	});
}
function expandFirstUi(that){
	$('.PansSecondUl').hide();
    $('.NavBar_FirstLiImg').addClass('NavBar_FirstLiImgJia');
    $('.NavBar_FirstLiImg').removeClass('NavBar_FirstLiImgJian');
    $('.NavBar_FirstLiImg').parents('p').parents('div').siblings('ul').hide();
    $('.NavBar_FirstLiImg').siblings('b').css('background','#2fabff');
    $('.NavBar_FirstLiImg').parents('p').css('background','#fff');
    $('.NavBar_FirstLiImg').parents('p').css('width','100%');
    $('.NavBar_FirstLiImg').children('i').removeClass('PansFirstLi_active');
    $(that).removeClass('NavBar_FirstLiImgJia');
    $(that).addClass('NavBar_FirstLiImgJian');
    $(that).parents('p').parents('div').siblings('ul').show(1).children('li');
    $(that).siblings('b').css('background','#1388d5');
    $(that).parents('p').css('width','100%');
    $(that).children('i').addClass('PansFirstLi_active');
}
function expandSecondUi(that){
	 $('.NavBar_SecondLiImg').parents('p').parents('div').siblings('ul').hide();
     $('.NavBar_SecondLiImg').children('b').removeClass('NavBar_SecondLiImgJiaB');
     $('.NavBar_SecondLiImg').addClass('NavBar_SecondLiImgJia');
     $('.NavBar_SecondLiImg').removeClass('NavBar_SecondLiImgJian');
     $(that).parents('p').parents('div').siblings('ul').show().children('li');
     $(that).children('b').addClass('NavBar_SecondLiImgJiaB');
     $(that).removeClass('NavBar_SecondLiImgJia');
     $(that).addClass('NavBar_SecondLiImgJian');
}
function expandThirdUi(that){
	$('.NavBar_ThirdLiImg').parents('p').parents('div').siblings('ul').hide();
    $('.NavBar_ThirdLiImg').children('b').removeClass('NavBar_ThirdLiImgJiaB');
    $('.NavBar_ThirdLiImg').addClass('NavBar_ThirdLiImgJia');
    $('.NavBar_ThirdLiImg').removeClass('NavBar_ThirdLiImgJian');
    $(that).parents('p').parents('div').siblings('ul').show().children('li');
    $(that).children('b').addClass('NavBar_ThirdLiImgJiaB');
    $(that).removeClass('NavBar_ThirdLiImgJia');
    $(that).addClass('NavBar_ThirdLiImgJian');
}
function expandForthUi(that){
	$('.NavBar_ThirdLiImg').parents('p').parents('div').siblings('ul').hide();
    $('.NavBar_ThirdLiImg').children('b').removeClass('NavBar_ThirdLiImgJiaB');
    $('.NavBar_ThirdLiImg').addClass('NavBar_ThirdLiImgJia');
    $('.NavBar_ThirdLiImg').removeClass('NavBar_ThirdLiImgJian');
    $(that).parents('p').parents('div').siblings('ul').show().children('li');
    $(that).children('b').addClass('NavBar_ThirdLiImgJiaB');
    $(that).removeClass('NavBar_ThirdLiImgJia');
    $(that).addClass('NavBar_ThirdLiImgJian');
}

function hideFirstUi(that){
	$(that).addClass('NavBar_FirstLiImgJia');
	$(that).removeClass('NavBar_FirstLiImgJian');
	$(that).parents('p').parents('div').siblings('ul').hide();
	$(that).siblings('b').css('background','#2fabff');
	$(that).parents('p').css('background','#fff');
	$(that).parents('p').css('width','100%');
	$(that).children('i').removeClass('PansFirstLi_active');
}
function hideSecondUi(that){
	 $(that).parents('p').parents('div').siblings('ul').hide();
     $(that).children('b').removeClass('NavBar_SecondLiImgJiaB');
     $(that).addClass('NavBar_SecondLiImgJia');
     $(that).removeClass('NavBar_SecondLiImgJian');
}
function hideThirdUi(that){
	 $(that).parents('p').parents('div').siblings('ul').hide();
     $(that).children('b').removeClass('NavBar_ThirdLiImgJiaB');
     $(that).addClass('NavBar_ThirdLiImgJia');
     $(that).removeClass('NavBar_ThirdLiImgJian');
}
function hideForthUi(that){
	 $(that).parents('p').parents('div').siblings('ul').hide();
     $(that).children('b').removeClass('NavBar_ThirdLiImgJiaB');
     $(that).addClass('NavBar_ThirdLiImgJia');
     $(that).removeClass('NavBar_ThirdLiImgJian');
}
function workLocation(orgOid)
{
	$("#rightpage").load("goTopersonWorkBench.do?method=goTopersonWorkBench&orgOid="+orgOid);
}
function workUnitLocation(organizationOid)
{
	$("#rightpage").load("goTopersonWorkBench.do?method=goTopersonWorkBench&organizationOid="+organizationOid);
}

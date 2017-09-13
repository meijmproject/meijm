$(function() {
		//校验

		$("#formVerProfTech")
				.validate(
						{
							rules : {
								profTechNameId : {
									required : true
								},
								profTechLevel : {
									required : true
								}
							},
							messages : {
								profTechNameId : {
									required : "请选择专业技术资格名称"
								},
								profTechLevel : {
									required : "请选择专业技术资格等级"
								}
							},
							errorPlacement : function(error, element) {
								error.appendTo(element.parents("ol:first"));
							},
							errorLabelContainer : $("#formVerProfTech div.modal-wrong"),
							wrapper : "li",
							submitHandler : function(form) {
								$
										.ajax({
											url : $('#formVerProfTech').attr("action"),
											data : $("#formVerProfTech")
													.serializeArray(),
											dataType : 'json',
											async : false,
											success : function(data) {
												try{
												if (data.success) {
													$.fn.close_popdown();
													var url_id = $(
															'#profTechId')
															.val();
													var url_personoid = $(
															'#profTechPersonOid')
															.val();
													$('#' + url_id)
															.load(
																	$('#'+ url_id).attr('url'),
																	{
																		personOid : url_personoid,
																		Id : url_id
																	});
												} else {
													$(
															"#formVerProfTech div.modal-wrong")
															.css('display',
																	'block');
													$(
															".wroglist")
															.html(
																	data.message);
												}
												return;
												}catch(e){
													
												}
												 $("#popdown-dialog").html(data);
							                        $("#formVerProfTech div.modal-wrong").css('display','block');
											}
										});

								$('#formVerProfTech').submit(function() {
									$(this).ajaxSubmit(options);
									return false;//防止dialog 自动关闭
								});
							}
						});

	});

function deleteVerProfTechInfo(profTechOid) {
	MessageBox.confirm('提示', '确认删除？',function (yes){
	    if(yes=="yes"){
			$.ajax({
				url : 'deleteVerProfTechInfo.do?method=delete',
				data : {
					profTechOid : profTechOid
				},
				dataType : 'json',
				error : function(x, t) {
					alert(t)
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						var url_id = $(
						'#verVerProfTechId')
						.val();
				var url_personoid = $(
						'#verVerProfTechPersonOid')
						.val();
				$('#' + url_id)
						.load(
								$('#'+ url_id).attr('url'),
								{
									personOid : url_personoid,
									Id : url_id
								});
					}
				}
			});
	    }
	})
		}
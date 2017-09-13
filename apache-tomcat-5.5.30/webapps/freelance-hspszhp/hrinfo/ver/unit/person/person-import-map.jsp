<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<%@ page import="com.yh.platform.core.constant.Constant"%>
<!DOCTYPE html>
<html>
<head >
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>人员导入页面</title>

<script type="text/javascript">
</script>
</head>
<body>
<div id="head-nav"></div>
<div class="current-position">
    当前位置：
    <span>基础信息管理 &gt; </span>
    <span>机构管理 &gt; </span>
    <span>人员信息导入 </span>
</div>
		<div class="main-container file-import-wrap">
			   <div class="person-info-import">
		        <div class="file-import-process">
		            <ol class="progress progress-6 clearfix">
		                <li class="active">
		                    <div class="process-line"></div>
		                    <div class="process-cont">
		                        <div class="process-cont-number">1</div>
		                        <div class="process-cont-text">导入</div>
		                    </div>
		                </li>
		                <li>
		                    <div class="process-line"></div>
		                    <div class="process-cont">
		                        <div class="process-cont-number">2</div>
		                        <div class="process-cont-text">采集项映射</div>
		                    </div>
		                </li>
		                <li>
		                    <div class="process-line"></div>
		                    <div class="process-cont">
		                        <div class="process-cont-number">3</div>
		                        <div class="process-cont-text">数据检查</div>
		                    </div>
		                </li>
		                <li>
		                    <div class="process-line"></div>
		                    <div class="process-cont">
		                        <div class="process-cont-number">4</div>
		                        <div class="process-cont-text">数据校核</div>
		                    </div>
		                </li>
		                <li>
		                    <div class="process-line"></div>
		                    <div class="process-cont">
		                        <div class="process-cont-number">5</div>
		                        <div class="process-cont-text">人员入库</div>
		                    </div>
		                </li>
		                <li>
		                    <div class="process-cont">
		                        <div class="process-cont-number">6</div>
		                        <div class="process-cont-text">完成</div>
		                    </div>
		                </li>
		            </ol>
		        </div>
<%-- 		        <div class="file-import-cont">
		            <a class="btn-search btn-default import-download-btn" href="<%=request.getContextPath()%>/hrinfo/ver/unit/person/import_person.xls">下载模版</a>
			        <span class="fi-title">选择文件：</span>
			        <div class="show-fileName" type="text"></div>
			        <input type="hidden" name="unitOid" value="${unitOid}"/>
			        <input type="hidden" name="orgOid" value="${orgOid}"/>
			        <a class="btn-browse-icon btn-search btn-default">浏览<input type="file" class="file-choice" name="uploadFile" id="uploadFile"></a>
			        <button id="submitForm" class="btn-upload-icon btn-search btn-default">导入</button>
		        </div> --%>
			    <input type="hidden" name="formFile"  id="formFile" value="${formFile}"/>
			    <input type="hidden" name="importDtoList"  id="importDtoList" value="${importDtoList}"/>
		    </div>  
		    <div class="person-info-loading">
		        <div class="person-map-cont">
		            <div class="person-map-title">
		                <h2>使用说明：</h2>
		                <p>左侧为系统当前已支持人员数据采集项；</p>
		                <p>右侧为即将导入的EXCEL数据表的表头，即第一行内容。</p>
		            </div>
		            <div class="person-map-table">
		             <div class="person-map-table-title">
			             <table class="sr-table">
			                <thead class="sr-table-thead">
			                <tr>
			                    <th>系统名称</th>
			                    <th>采集项名称</th>
			                </tr>
			                </thead>
			             </table>
		             </div>
		             <div class="person-map-table-tbody">
		                <table class="sr-table">		         
		                <tbody class="sr-table-tbody">
		                <c:forEach var ="dto" items="${list}"  varStatus="status">
			                 <tr>
			                    <td>${dto.templateCollName}
			                    	<input type="hidden" id ="isRequired"name="isRequired" value="${dto.isRequired}"/>
			                    	<input type="hidden" id ="dataColumnCode" value="${dto.databaseColumnCode}"/>
			                    	<input type="hidden" id ="importCollName"  name="importCollName" value="${dto.importCollName}" />
			                    	<input type="hidden" id ="templateOid" name="templateOid" value="${dto.templateOid}"/>
			                    </td>
			                    <td>
			                    	<select id ="nowselect_${status.index}"><option value="${dto.importCollName}">${dto.importCollName}</option>
			                    		<c:forEach items="${importNameList}" var="importName" ><option value="${importName.importCollName}"<c:if test="${imCollectTemplateDTO.importCollName==importName.importCollName}"><c:out value="selected"/></c:if> > ${importName.importCollName}</option></c:forEach>
			                   		 </select>
			                    </td>
			                </tr>
		                </c:forEach>
		                </tbody>
		                </table>
		               </div>
						<div class="person-map-table-btn person-map-table-btn-center clearfix">
							<button id="save" class="btn-search btn-default">保存</button>
							<button id="exit" class="btn-search btn-default">退出</button>
						</div>
		            </div>
		        </div>
		    </div>
		</div>
</body>
<script>
    $(function() {
        personMapTableHeight();

        $(window).resize(
            function(){
                personMapTableHeight();
            }
        );
        checkMustShow();
        //根据必填项自动加载*号
        function checkMustShow() {
        	$('.sr-table-tbody tr').each(function(){
        		var firstName = $(this).find('#importCollName').val(); //获取原先的值
        		var isRequiredFlag = $(this).find('#isRequired').val(); //获取到是否必填的项
            	if(isRequiredFlag == '<%=Constant.ONE%>'){
            		$(this).find("td:nth-child(1)").append('<b class="Required">*</b>'); 
            	}
            	//var importDtoList = ${importDtoList};
            	//console.log(importDtoList);
        		//若以前默认值为空
<%--         		if(firstName != '<%=Constant.EMPTY_SELECT%>'){
        			
        		} --%>
        	})
        }
        // 页面加载后主内容区域的高度设置
        function eachAreaHeight() {
            var windowHeight = $(window).height();
            //主内容区高度
            $('.main-container').height(windowHeight - $('.current-position').outerHeight(true));
        }

        function personMapTableHeight() {
            eachAreaHeight();
            var personMapTableMaxHeight = $('.main-container').height()-$('.person-info-import').outerHeight(true)-$('.person-map-title').outerHeight(true)-20;
            var personMapTableRealHeight = $(".person-map-table").height();
            if (personMapTableRealHeight>personMapTableMaxHeight){
                $(".person-map-table .person-map-table-tbody").height(personMapTableMaxHeight-$('.person-map-table-btn').outerHeight(true)-$('.person-map-table .sr-table thead').outerHeight(true));
            }
        }
        
	 	$('#save').click(function() {
	 		var mapDatas = [];
	 		var map=new Array();  //已经匹配上的
	 		var noMap = '';//未匹配上的
	 		var checkMust = '';//校验必填的
	 		var i = 0;
	 		$('.sr-table-tbody tr').each(function(){
	 			var mapdata = {};
		 			var templateCollName = $(this).find('td:nth-child(1)').text(); 
		 			var importCollName = $(this).find('td:nth-child(2)').find('select').val();
		 			var nowImportCollName = $(this).find('td:nth-child(2)').find('select option:selected').text();
		 			var isRequiredFlag = $(this).find('#isRequired').val(); //获取到是否必填的项
		 			var dataColumnCode = $(this).find('#dataColumnCode').val(); //数据库字段代码
		 			var firstName = $(this).find('#importCollName').val(); //获取原先的值
		 			var templateOid = $(this).find('#templateOid').val(); //模板OID
		 			//console.log(firstName + ',' + importCollName + ',' + nowImportCollName);
		 			if(isRequiredFlag == '<%=Constant.ONE%>'){
		 				//若以前默认值为空
 		 				if(firstName == '<%=Constant.EMPTY_SELECT%>'){
 		 					//并且没有选择
		 					if(importCollName ==  '<%=Constant.EMPTY_SELECT%>'){
		 						//必填项
			 					if(checkMust.length > 0){
			 						checkMust = checkMust +  ',' + templateCollName;
			 					}else{
			 						checkMust = checkMust + templateCollName;
			 					}
		 						//未匹配上的
		 						if(noMap.length > 0){
		 							noMap = noMap +',' + templateCollName;
		 						}else{
		 							noMap = noMap  + templateCollName;
		 						}
		 					}
		 					//已经选择值了
		 					else{
		 						if(map.length > 0){
		 							map[i] = nowImportCollName.replace(/\s/ig,'');
		 							i++;
		 						}else{
		 							map[i] = nowImportCollName.replace(/\s/ig,'');
		 							i++;
		 						}
		 					}
		 				}//选择了值
 		 				else{
 		 					//现在又手动选择了空(即为请选择)
 		 					if(importCollName == '<%=Constant.EMPTY_SELECT%>'){
 		 					//必填项
			 					if(checkMust.length > 0){
			 						checkMust = checkMust +  ',' + templateCollName;
			 					}else{
			 						checkMust = checkMust + templateCollName;
			 					}
 		 					}else{
 		 						if(map.length > 0){
 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
 		 							i++;
 		 						}else{
 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
 		 							i++;
 		 						}
 		 					}
		 				}
		 			}
		 			//不为必填项的
		 			else{
 		 				if(firstName ==  '<%=Constant.EMPTY_SELECT%>' ){
		 					if(importCollName ==  '<%=Constant.EMPTY_SELECT%>'){
		 						//未匹配上的
		 						if(noMap.length > 0){
		 							noMap = noMap +',' + templateCollName;
		 						}else{
		 							noMap = noMap  + templateCollName;
		 						}
		 					}
		 					//已经选择值了
		 					else{
		 						if(map.length > 0){
		 							map[i] = nowImportCollName.replace(/\s/ig,'');
		 							i++;
		 						}else{
		 							map[i] = nowImportCollName.replace(/\s/ig,'');
		 							i++;
		 						}
		 					}
		 				}//选择了值
 		 				else{
 		 					//console.log(nowImportCollName);
 		 					//但选择的值为空(即为请选择的)
 		 					if(importCollName == '<%=Constant.EMPTY_SELECT%>'){
 		 					//未匹配上的
		 						if(noMap.length > 0){
		 							noMap = noMap +',' + templateCollName;
		 						}else{
		 							noMap = noMap  + templateCollName;
		 						}
 		 					}
 		 					else{
 		 						if(map.length > 0){
 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
 		 							i++;
 		 						}else{
 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
 		 							i++;
 		 						}
 		 					}
		 				}
		 			}
		 			//mapdata.templateCollName = templateCollName;
		 			mapdata.templateOid = templateOid;
		 			mapdata.dataColumnCode = dataColumnCode;
		 			mapdata.nowImportCollName = nowImportCollName;
		 			mapDatas.push(mapdata);
	 		})
	 		if(checkMust.length > 0){
	 			MessageBox.alert('提示',checkMust + '为必填项，请确认！');
	 		}else{
  	 			if(map.length > 0){
		  	 		//判断数组重复值
		  	 		for(var i=0;i<map.length;i++){
		  	 			for(var j = i+1;j<map.length;j++){
		  	 				if (map[i]==map[j]){
				  	 			MessageBox.alert('提示',"采集项" + map[i] + '重复，请确认！');
				  	 			i--;
				  	 			return;
		  	 				}
		  	 			}
		  	 			}
		  	 	}
	 	 		if(noMap.length > 0){
	 	 			MessageBox.confirm('提示', '当前还存在未映射的采集项，继续保存还是修改采集项映射?', function(action) {
	  	 				if (action == 'yes') {
	  	 					$.post("updateImCollectTemplate.do?method=updateImCollectTemplate&mapDatas="+encodeURI(JSON.stringify(mapDatas)),function(data){
	  	 						if(data.success){
	  	 							var formFile = $("#formFile").val();
	  	 							location.href = "goTocheckImportFile.do?method=goTocheckImportFile&formFile="+formFile;
	  	 							//MessageBox.alert('提示','映射成功');
	  	 						}else{
	  	 							MessageBox.alert('提示',data.message);
	  	 						}
	  	 					},'json');
	  	 				}
	  	 			});
		 		}else{
						$.post("updateImCollectTemplate.do?method=updateImCollectTemplate&mapDatas="+encodeURI(JSON.stringify(mapDatas)),function(data){
		 						if(data.success){
		 			            	var formFile = $("#formFile").val();
		 							location.href = "goTocheckImportFile.do?method=goTocheckImportFile&formFile="+formFile;
		 							//MessageBox.alert('提示','映射成功');
		 						}else{
		 							MessageBox.alert('提示',data.message);
		 						}
		 					},'json');
		 		} 
	 		}
		}); 
	 	
	 	$('#exit').click(function() {
	 		MessageBox.confirm('提示', '是否保存当前映射规则?', function(action) {
	 			if(action == 'yes'){
	 				var mapDatas = [];
	 		 		var map=new Array();  //已经匹配上的
	 		 		var noMap = '';//未匹配上的
	 		 		var checkMust = '';//校验必填的
	 		 		var i = 0;
	 		 		$('.sr-table-tbody tr').each(function(){
	 		 			var mapdata = {};
	 			 			var templateCollName = $(this).find('td:nth-child(1)').text(); 
	 			 			var importCollName = $(this).find('td:nth-child(2)').find('select').val();
	 			 			var nowImportCollName = $(this).find('td:nth-child(2)').find('select option:selected').text();
	 			 			var isRequiredFlag = $(this).find('#isRequired').val(); //获取到是否必填的项
	 			 			var dataColumnCode = $(this).find('#dataColumnCode').val(); //数据库字段代码
	 			 			var firstName = $(this).find('#importCollName').val(); //获取原先的值
	 			 			var templateOid = $(this).find('#templateOid').val(); //模板OID
	 			 			//console.log(firstName + ',' + importCollName + ',' + nowImportCollName);
	 			 			if(isRequiredFlag == '<%=Constant.ONE%>'){
	 			 				//若以前默认值为空
	 	 		 				if(firstName == '<%=Constant.EMPTY_SELECT%>'){
	 	 		 					//并且没有选择
	 			 					if(importCollName ==  '<%=Constant.EMPTY_SELECT%>'){
	 			 						//必填项
	 				 					if(checkMust.length > 0){
	 				 						checkMust = checkMust +  ',' + templateCollName;
	 				 					}else{
	 				 						checkMust = checkMust + templateCollName;
	 				 					}
	 			 						//未匹配上的
	 			 						if(noMap.length > 0){
	 			 							noMap = noMap +',' + templateCollName;
	 			 						}else{
	 			 							noMap = noMap  + templateCollName;
	 			 						}
	 			 					}
	 			 					//已经选择值了
	 			 					else{
	 			 						if(map.length > 0){
	 			 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 			 							i++;
	 			 						}else{
	 			 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 			 							i++;
	 			 						}
	 			 					}
	 			 				}//选择了值
	 	 		 				else{
	 	 		 					//现在又手动选择了空(即为请选择)
	 	 		 					if(importCollName == '<%=Constant.EMPTY_SELECT%>'){
	 	 		 					//必填项
	 				 					if(checkMust.length > 0){
	 				 						checkMust = checkMust +  ',' + templateCollName;
	 				 					}else{
	 				 						checkMust = checkMust + templateCollName;
	 				 					}
	 	 		 					}else{
	 	 		 						if(map.length > 0){
	 	 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 	 		 							i++;
	 	 		 						}else{
	 	 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 	 		 							i++;
	 	 		 						}
	 	 		 					}
	 			 				}
	 			 			}
	 			 			//不为必填项的
	 			 			else{
	 	 		 				if(firstName ==  '<%=Constant.EMPTY_SELECT%>' ){
	 			 					if(importCollName ==  '<%=Constant.EMPTY_SELECT%>'){
	 			 						//未匹配上的
	 			 						if(noMap.length > 0){
	 			 							noMap = noMap +',' + templateCollName;
	 			 						}else{
	 			 							noMap = noMap  + templateCollName;
	 			 						}
	 			 					}
	 			 					//已经选择值了
	 			 					else{
	 			 						if(map.length > 0){
	 			 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 			 							i++;
	 			 						}else{
	 			 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 			 							i++;
	 			 						}
	 			 					}
	 			 				}//选择了值
	 	 		 				else{
	 	 		 					//console.log(nowImportCollName);
	 	 		 					//但选择的值为空(即为请选择的)
	 	 		 					if(importCollName == '<%=Constant.EMPTY_SELECT%>'){
	 	 		 					//未匹配上的
	 			 						if(noMap.length > 0){
	 			 							noMap = noMap +',' + templateCollName;
	 			 						}else{
	 			 							noMap = noMap  + templateCollName;
	 			 						}
	 	 		 					}
	 	 		 					else{
	 	 		 						if(map.length > 0){
	 	 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 	 		 							i++;
	 	 		 						}else{
	 	 		 							map[i] = nowImportCollName.replace(/\s/ig,'');
	 	 		 							i++;
	 	 		 						}
	 	 		 					}
	 			 				}
	 			 			}
	 			 			//mapdata.templateCollName = templateCollName;
	 			 			mapdata.templateOid = templateOid;
	 			 			mapdata.dataColumnCode = dataColumnCode;
	 			 			mapdata.nowImportCollName = nowImportCollName;
	 			 			mapDatas.push(mapdata);
	 		 		})
	 		 		if(checkMust.length > 0){
	 		 			MessageBox.alert('提示',checkMust + '为必填项，请确认！');
	 		 		}else{
	 	  	 			if(map.length > 0){
	 			  	 		//判断数组重复值
	 			  	 		for(var i=0;i<map.length;i++){
	 			  	 			for(var j = i+1;j<map.length;j++){
	 			  	 				if (map[i]==map[j]){
	 					  	 			MessageBox.alert('提示',"采集项" + map[i] + '重复，请确认！');
	 					  	 			i--;
	 					  	 			return;
	 			  	 				}
	 			  	 			}
	 			  	 			}
	 			  	 	}
	 		 	 		if(noMap.length > 0){
	 		 	 			MessageBox.confirm('提示', '当前还存在'+ noMap + '未映射的采集项，继续保存还是修改采集项映射?', function(action) {
	 		  	 				if (action == 'yes') {
	 		  	 					$.post("updateImCollectTemplate.do?method=updateImCollectTemplate&mapDatas="+encodeURI(JSON.stringify(mapDatas)),function(data){
	 		  	 						if(data.success){
	 		  	 			            	location.href = "goToUploadPersonListPage.do?method=goToUploadPersonListPage&isNewImport=1";
	 		  	 						}else{
	 		  	 							MessageBox.alert('提示',data.message);
	 		  	 						}
	 		  	 					},'json');
	 		  	 				}
	 		  	 			});
	 			 		}else{
	 							$.post("updateImCollectTemplate.do?method=updateImCollectTemplate&mapDatas="+encodeURI(JSON.stringify(mapDatas)),function(data){
	 			 						if(data.success){
	 			 			            	location.href = "goToUploadPersonListPage.do?method=goToUploadPersonListPage&isNewImport=1";
	 			 						}else{
	 			 							MessageBox.alert('提示',data.message);
	 			 						}
	 			 					},'json');
	 			 		} 
	 		 		}
	 			}else{
		            location.href = "goToUploadPersonListPage.do?method=goToUploadPersonListPage&isNewImport=1";
	 			}
	 		});	
	 	})
    });
</script>
</html>
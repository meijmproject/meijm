<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE>已办</TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <%@ include file="/include/jsp_headers.jsp"%>
<BODY>
<div class="person-map-table-title">
 <table class="sr-table">
   <thead class="sr-table-thead">
       <tr>
           <th>导入值</th>
           <th>标准值</th>
       </tr>
       </thead>
       </table>
</div>
<div class="person-map-table-tbody">
 <table class="sr-table">
      <tbody class="sr-table-tbody">
       <c:forEach var ="dto" items="${list}" varStatus="status">
        <tr>
            <td>${dto.importItemName} <input type='text' hidden value='${dto.dicItemMappingOid}'/></td>
            <td><dictionary:dicItemSelect id="${databaseColumnCode}" dicTypeCode="${dicTypeCode}" selected="${dto.dicItemCode}" name="${databaseColumnCode}" /></td>
        </tr>
       </c:forEach>
      </tbody>
  </table>
  </div>
   <c:if test='${not empty  list}'>
   <div class="person-map-table-btn clearfix"><button class="btn-search btn-default" id='save'>更新</button></div>
    </c:if>
</BODY>
<script type="text/javascript">
$('#save').click(function(){
    var dicMappedDatas = [];
    $('.sr-table-tbody tr').each(function(){
        var dicMappedData = {} ;
        var importItemName = $(this).find('td:nth-child(1)').text();
        var dicItemCode = $(this).find('td:nth-child(2)').find('select').val();
        var dicItemName = $(this).find('td:nth-child(2)').find('select option:selected').text();
        var dicItemMappingOid = $(this).find('input').val();
        dicMappedData.importItemName = importItemName;
        dicMappedData.dicItemCode=dicItemCode;
        dicMappedData.dicItemName=dicItemName;
        dicMappedData.dicItemMappingOid=dicItemMappingOid;
        dicMappedDatas.push(dicMappedData);
    })
    $.post('saveDicMapped.do?method=saveDicMapped&dicMappedDatas='+JSON.stringify(dicMappedDatas)+"&dicTypeCode=${dicTypeCode}",function(data){
        if(data.success){
       	 MessageBox.alert('提示',data.message);
       	 $('.c-nav-tab a').each(function(){
       	   	   if($(this).hasClass('c-navtab-selected')){
       	   	   	   $(this).click();
       	   	   	 }
      	     })
      	     $.post('listNoDicMapped.do?method=listNoDicMapped',function(data){
				$('#dicUl li').remove();
				var li='';
				$.each(data,function(index,item){
					li+='<li class="ln-firstli"><a class="ln-firstli-a" href="javascript:void(0);" onclick=workLocation("'+item.dicTypeCode+'","'+item.dicTypeMappingOid+'")>'+item.name+'</a>';
					if(item.count!=0){
						li+='<span class="tips-num">'+item.count+'</span>';
					  }
					li+='</li>';
				})
				$('#dicUl').append($(li));
			},'json')
        }else{
        	MessageBox.alert('提示',data.message,function(){
	       		$.fn.close_popdown();
		       	location.href ='goTocheckPersonDicRepeat.do?method=goTocheckPersonDicRepeat';
	       	 });
       }
     },'json');
})
</script>
</HTML>
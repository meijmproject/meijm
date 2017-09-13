<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
 * @function    人员离开查看页面
 * @page name   pbPersonOutList.jsp
 * @author      liupt
 * @created     2017/04/7
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>减员信息列表页</title>
</head>
<logic:messagesPresent>
		<bean:message key="errors.header" />
			<ul>
				<html:messages id="error">
					<li>
						<bean:write name="error" />
					</li>
				</html:messages>
			</ul>
</logic:messagesPresent>
<body>
<div class="infoshow-container padding-lrb">
  <div class="st-title-box">
    <h3 class="st-title-text">人员离开信息</h3>
    <div class="st-title-icon st-title-button">
      <button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToInsertPbPersonOut.do?method=goInsert&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
      <button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadPersonIn('${param.personOid}')">上传附件</button>
    </div>
  </div>
  <div class="st-main-table">
    <table class="sr-table">
      <thead class="sr-table-thead">
        <tr>
          <th class="md-th"><input type="checkbox"></th>
          <th>减员类型</th>
          <th>离开日期</th>
          <th>离开后去向</th>
          <th>备注</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody class="sr-table-tbody">
        <c:forEach var ="dto" items="${list}">
          <tr class="td_content">
            <td class="md-th"><input type="checkbox"/></td>
            <td><dictionary:viewDicItemName dicItemCode="${dto.personOutType}" dicTypeCode="<%=DicConstants.YHRS0128%>"/>&nbsp;</td>
            <td>${dto.outDateStr}</td>
            <td>${dto.personOutTo }</td>
            <td>${dto.remark }</td>
            <td>
              <a class="st-handle-delete" href="javascript:void(0);" onclick="deletePersonOut('${dto.personOutOid}')"></a>
              <a class="st-handle-modify popdown btn" href="goToUpdatePbPersonOut.do?method=goUpdate&urlId=${param.Id}&personOutOid=${dto.personOutOid}&personOid=${dto.personOid}"></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
    $('.popdown').popdown({width:1200});
})

function deletePersonOut(personOutOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbPersonOut.do?method=delete',
				data :  {personOutOid:personOutOid},
				dataType : 'json',
				error : function(x,t) {
					alert(t);
					alert("error occured!!!");
				},
				async : false,
				success : function(data) {
					if (data.success) {
						$('#${param.Id}').load($('#${param.Id}').attr('url'),{personOid:'${param.personOid}',Id:'${param.Id}'});
					}
					else
                    {
                        alert(data.message);
                    }
				}
			});
		}
	});
}
</script>
</html>
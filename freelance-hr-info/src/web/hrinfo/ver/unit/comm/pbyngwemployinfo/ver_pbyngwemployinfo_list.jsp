<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>

<!--
* @page name  ver_pbyngwemployinfo_list.jsp
* @function   院内岗位聘任信息列表
* @author     liul
* @created    2017-02-15
* @version    1.0
-->
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="hrworktop/flow/BizDefaultTaskFlowAction.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
	<title>院内岗位聘任信息列表页</title>
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
<%-- <div class="sys_box sys_box_up">
        <div class="sys_base"><span>院内岗位聘任信息</span>
        <c:if test="${'1' ne flag}">
            <i>
            	<a style="float:right;margin-left: 20px;" href="goToCreatePbYnGwEmployInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}" class="popdown btn"><img src="img/DetailPages/icon_add_green.png"/></a>
            </i>
        </c:if>
        </div>
        <div class="sys_list">
            <table class="table_edit_bg" width="95%">
                <tr class="td_title">
                    <td width="50"><input type="checkbox"/></td>
	                <td>岗位聘任状态</td>
	                <td>岗位名称</td>
	                <td>岗位类别</td>
	                <td>岗位级别</td>
	                <td>工作类别</td>
	                <td>岗位聘任开始时间</td>
	                <td width="100">操作</td>
                </tr>
                <c:forEach var ="dto" items="${list}">
                <tr class="td_content">
                    <td width="50"><input type="checkbox"/></td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.hisPositionStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>&nbsp;
                    </td>
                    <td title="${dto.hisPositionName}">${dto.hisPositionName}</td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionType}" dicTypeCode="YHRS0113"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionLevel}" dicTypeCode="YHRS0124"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.hisWorkType}" dicTypeCode="<%=DicConstants.YHRS0112%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.hisWorkType}" dicTypeCode="<%=DicConstants.YHRS0112%>"/>&nbsp;
                    </td>
                    <td title="${dto.hisBeginDateStr}">
                            ${dto.hisBeginDateStr}
                    </td>
                	<td>
                    	<a href="javascript:void(0);" onclick="deleteYnGwEmployInfo('${dto.ynGwEmployOid}')"><img src="img/DetailPages/icon_delete.png"/></a>
                        <a href="goToUpdatePbYnGwEmployInfoPage.do?method=goUpdate&urlId=${param.Id}&ynGwEmployOid=${dto.ynGwEmployOid}" class="popdown btn"><img src="img/DetailPages/icon_revise.png"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
 </div> --%>
 <div class="infoshow-container padding-lrb">
        <div class="st-title-box"><h3 class="st-title-text">院内岗位聘任信息</h3>
	       <div class="st-title-icon st-title-button">
		        <c:if test="${'1' ne flag}">
		            <i>
		            	<button class="popdown btn-add btn-left-icon btn-default btn-right" href="goToCreatePbYnGwEmployInfoPage.do?method=goCreate&personOid=${param.personOid}&urlId=${param.Id}">新增</button>
	           			<button title="上传附件" class="btn-upload btn-left-icon btn-default btn-right" href="javascript:void(0);" onclick="uploadYnGwEmployInfo('${param.personOid}')">上传附件</button>
		            </i>
	        	</c:if>
	       </div>
        </div>
        <div class="st-main-table">
            <table class="sr-table">
             <thead class="sr-table-thead">
                <tr>
                	<th class="md-th"></th>
                	<th class="md-th"><input type="checkbox"></th>
                	<th>岗位聘任状态</th>
	                <th>岗位名称</th>
	                <th>岗位类别</th>
	                <th>岗位级别</th>
	                <th>工作类别</th>
	                <th title="岗位聘任开始时间">岗位聘任开始时间</th>
	                <th>操作</th>
                </tr>
             </thead>
             <tbody class="sr-table-tbody">
              <c:forEach var ="dto" items="${list}" varStatus="status">
                <tr>
                 <td class="md-th">${status.count}</td>
                    <td class="md-th"><input type="checkbox"/></td>
                  <td title="<dictionary:viewDicItemName dicItemCode="${dto.hisPositionStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionStatus}" dicTypeCode="<%=DicConstants.YHRS0026%>"/>&nbsp;
                    </td>
                    <td title="${dto.hisPositionName}">${dto.hisPositionName}</td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionType}" dicTypeCode="YHRS0113"/>&nbsp;
                    </td>
                    <td>
                        <dictionary:viewDicItemName dicItemCode="${dto.hisPositionLevel}" dicTypeCode="YHRS0124"/>&nbsp;
                    </td>
                    <td title="<dictionary:viewDicItemName dicItemCode="${dto.hisWorkType}" dicTypeCode="<%=DicConstants.YHRS0112%>"/>">
                        <dictionary:viewDicItemName dicItemCode="${dto.hisWorkType}" dicTypeCode="<%=DicConstants.YHRS0112%>"/>&nbsp;
                    </td>
                    <td title="${dto.hisBeginDateStr}">
                            ${dto.hisBeginDateStr}
                    </td>
                	 <td>
                    	<a class="st-handle-delete" href="javascript:void(0);" onclick="deleteYnGwEmployInfo('${dto.ynGwEmployOid}')"></a>
                        <a class="st-handle-modify popdown btn" href="goToUpdatePbYnGwEmployInfoPage.do?method=goUpdate&urlId=${param.Id}&ynGwEmployOid=${dto.ynGwEmployOid}"></a>
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
    
  //滚动效果
    $(window).load();
    tableScorll = function () {
        //表头和tbody内容相等
        var ths=$(".sr-table-thead tr th");
        var td=$(".sr-table-tbody tr td");
        for(var i=0;i<td.length;i++){
            $(ths[i]).width($(td[i]).width());
        }
        //表头滚动
        $(".sr-table-tbody").scroll(function (e) {
            $(this).siblings(".sr-table-thead").css({left:-this.scrollLeft});
        });
    }
    tableScorll();
})

function deleteYnGwEmployInfo(ynGwEmployOid){
	MessageBox.confirm('提示', '确认删除？',function(action)
	{
		if (action == 'yes') {
			$.ajax({
				url : 'deletePbYnGwEmployInfo.do?method=delete',
				data :  {ynGwEmployOid:ynGwEmployOid},
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
function uploadYnGwEmployInfo(personOid){
	
	var params = {
		personOid : personOid//字节 5MB
		,refType : '03'
	}
	BizDefaultTaskFlowAction.photoUpload.call(null,null,null,params);
}
</script>
</html>
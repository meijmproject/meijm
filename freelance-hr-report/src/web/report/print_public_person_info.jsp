<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/html_headers.jsp"%>
<%@page import="gov.szghrs.base.utils.DicParameter"%>
<%@page import="gov.szghrs.base.utils.Constants"%>
<html:html>
<head>
<title>print_public_person_info.jsp</title>
<style media="print">
<!--
.Noprint{display:none;}
.PageNext{page-break-after:always;}
-->
</style>
<script type="text/javascript">
function printpreview(){ 
wb.execwb(7,1);// 打印页面预览
} 
function printit() { 
wb.execwb(6,1);//打印页面
} 
</script>
</head>

<body>
<OBJECT id=wb height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT>	

<logic:present name="person">
<table width="100%" border="0" cellpadding="0" >
 <tr>
  	<td>
    <table width="650" border="0" cellpadding="0" cellspacing="0">
        <tr bordercolor="0">
          <td height="40" colspan="6" align="center"><font size="5"><b>人员信息采集卡</b></font></td>
        </tr>
        <tr>
          <td width="102" height="33" bordercolor="0" align="center">单位（盖章）：</td>
          <td width="105" bordercolor="0">&nbsp;</td>
          <td width="100" bordercolor="0" align="center">填表人：</td>
          <td width="190" bordercolor="0">&nbsp;</td>
          <td width="145" bordercolor="0" align="center">填表日期：</td>
          <td bordercolor="0">&nbsp;</td>
        </tr>
     </table>
	
  <tr>
    <td width="650" height="303"><table width="650" border="1" cellspacing="0">
      <tr>
        <td width="55" height="35" align="center">姓&nbsp;&nbsp;名</td>
        <td colspan="2" align="center"><bean:write name="person" property="name"/>&nbsp;</td>
        <td width="75" colspan="2"><div align="center">性&nbsp;&nbsp;别</div></td>
        <td width="85" align="center"><szghrs:viewDicItemName dicTypeCode="<%= DicParameter.SZRS0001 %>" dicItemCode="${person.sexCode}"/>&nbsp;</td>
        <td width="85" colspan="2" align="center">出生年月</td>
        <td colspan="2" align="center"><bean:write name="person" property="birthday" format="yyyy.MM.dd"/>&nbsp;</td>
        <td colspan="2" rowspan="5" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td height="35" align="center">籍贯描述</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.birthplaceCode}" dicTypeCode="<%=DicParameter.SZRS0003%>"/>&nbsp;</td>
        <td colspan="2" align="center">籍贯代码</td>
        <td align="center"><szghrs:viewDicItemName dicItemCode="${person.birthplaceCode}" dicTypeCode="<%=DicParameter.SZRS0003%>"/>&nbsp;</td>
        <td colspan="2" align="center">民族</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.peopleCode}" dicTypeCode="<%=DicParameter.SZRS0004%>"/>&nbsp;</td>
        </tr>
      <tr>
        <td height="35" align="center"><div>健康状况</div></td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicTypeCode="<%= DicParameter.SZRS0005 %>" dicItemCode="${person.healthStatusCode}"/>&nbsp;</td>
        <td colspan="2" align="center">参见工作时间</td>
        <td align="center"><bean:write name="person" property="startWorkDate" format="yyyy.MM"/>&nbsp;</td>
        <td colspan="2" align="center">离岗退养标识</td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isRetrieFlag">是</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isRetrieFlag">否</logic:notEqual>&nbsp;</td>
        </tr>
      <tr>
        <td height="30" align="center">证件类别</td>
        <td align="center"><szghrs:viewDicItemName dicItemCode="${person.idCode}" dicTypeCode="<%=DicParameter.SZRS0002%>"/>&nbsp;</td>
        <td align="center">证件号码</td>
        <td colspan="3" align="center"><bean:write name="person" property="idNo"/>&nbsp;</td>
        <td colspan="2" align="center">参照公务员法<br>管理标识 </td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isCadre">是</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isCadre">否</logic:notEqual>&nbsp;</td>
        </tr>
      <tr>
        <td height="35" colspan="3" align="center">退出现役军人（武警）标识</td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isVeteran">是</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isVeteran">否</logic:notEqual>&nbsp;</td>
        <td colspan="3" align="center">现职务层次</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.administrativeDutyLevel}" dicTypeCode="<%=DicParameter.SZRS_S0021%>"/>&nbsp;</td>
        </tr> 
      <c:set var="sequ" value="0"/>      
      <logic:present name="educationLevelDegree">
      	<logic:iterate id="edu" name="educationLevelDegree">
       <c:if test="${sequ < '1'}">  
      <tr>
        <td rowspan="<c:out value="${fn:length(educationLevelDegree)+1}"/>"  align="center">学<br />
          历<br />
          学<br />
          位</td>
        <td height="45" align="center">教育 <br />
          类别</td>
        <td height="45" align="center">学历<br />
          名称</td>
        <td width="50" align="center">学历<br />
          代码</td>
        <td width="50" align="center">所学<br />
          专业</td>
        <td align="center">入学<br />
          时间</td>
        <td width="50" align="center">毕业<br />
          时间</td>
        <td width="50" align="center">学位<br />
          名称</td>
        <td width="49" align="center">学位<br />
          代码</td>
        <td width="64" align="center">学位授<br />
          予日期</td>
        <td width="62" align="center">学校（单<br />
          位）名称</td>
        <td width="46" align="center">学位授<br />
          予单位</td>
      </tr>  
      </c:if>
	      <tr>	           
	        <td height="42" align="center" ><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0027%>" dicItemCode="${edu.eduType}"/>&nbsp;</td>	       
	        <td height="42" align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0028%>" dicItemCode="${edu.educationCode}"/>&nbsp;</td>
	        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0028%>" dicItemCode="${edu.educationCode}"/>&nbsp;</td>
	        <td align="center"> <szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0026%>" dicItemCode="${edu.majorCode}"/>&nbsp;</td>
	        <td align="center"><bean:write name="edu" property="schoolEnrollDate" format="yyyy.MM"/>&nbsp;</td>
	        <td align="center"><bean:write name="edu" property="graduateDate" format="yyyy.MM"/>&nbsp;</td>
	        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0028%>" dicItemCode="${edu.educationCode}"/>&nbsp;</td>
	        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0028%>" dicItemCode="${edu.educationCode}"/>&nbsp;</td>
	        <td align="center"><bean:write name="edu" property="degreeGrantDate" format="yyyy.MM"/>&nbsp;</td>
	        <td align="center"><c:out value="${edu.degreeGrantUnit}" />&nbsp;</td>
	        <td align="center"><c:out value="${edu.degreeGrantUnit}" />&nbsp;</td>
	      </tr>
	      <c:set var="sequ" value="1"/>
	    </logic:iterate>
</logic:present>


    </table></td>
  </tr>
  <tr class="PageNext"></tr>
  <tr>
    <td height="208"><table width="650" border="1" cellspacing="0">
    <c:set var="pdSequ" value="0"/>   
     <logic:present name="positioningDisposal">
      	<logic:iterate id="pd" name="positioningDisposal">
       <c:if test="${pdSequ < '1'}">  
      <tr>
        <td width="54" rowspan="<c:out value="${fn:length(positioningDisposal)+1}"/>"><div align="center">任<br />
          职<br />
          信<br />
          息</div></td>
        <td width="90" height="35" align="center">职务属性</td>
        <td width="100" height="35" align="center">任职机构名称</td>
        <td width="70" height="35" align="center">任职时间</td>
        <td width="80" height="35" height="35" align="center">职务描述</td>
        <td width="70" align="center">职务代码</td>
        <td width="80" height="35" align="center">任职方式</td>
        <td width="60" height="35" align="center">职务层次</td>
        <td width="50" height="35" align="center">任职<br>状态</td>
      </tr>
      </c:if>
      <tr>
        <td align="center" height="35"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0016 %>" dicItemCode="${pd.dutyAttribute}"/>&nbsp;</td>
        <td align="center"><bean:write name="pd" property="dutyUnitName"/>&nbsp;</td>
        <td align="center"><bean:write name="pd" property="dutyDate" format="yyyy.MM"/>&nbsp;</td>
        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0017%>" dicItemCode="${pd.dutyCode}"/>&nbsp;</td>
        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0017%>" dicItemCode="${pd.dutyCode}"/>&nbsp;</td>
        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0018%>" dicItemCode="${pd.positioningTypeCode}"/>&nbsp;</td>
        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS_S0021%>" dicItemCode="${pd.dutyLevelCode}"/>&nbsp;</td>
        <td align="center"><szghrs:viewDicItemName dicTypeCode="<%=gov.szghrs.base.utils.DicParameter.SZRS0126%>" dicItemCode="${pd.positioningStatus}"/>&nbsp;</td>
      </tr>
       <c:set var="pdSequ" value="1"/>
      </logic:iterate>
      </logic:present>
    </table></td>    
  </tr>    		
</table>	
</logic:present>
<INPUT onclick=javascript:printit() type=button value="打印" name="button_print" class="Noprint"/> 
<INPUT onclick=javascript:printpreview(); type=button value=打印页面设置 name=button_setup class="Noprint" />	
 <logic:notPresent name="person">
 无此人信息
 </logic:notPresent>
</body>
</html:html>
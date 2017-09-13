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
wb.execwb(7,1);// ��ӡҳ��Ԥ��
} 
function printit() { 
wb.execwb(6,1);//��ӡҳ��
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
          <td height="40" colspan="6" align="center"><font size="5"><b>��Ա��Ϣ�ɼ���</b></font></td>
        </tr>
        <tr>
          <td width="102" height="33" bordercolor="0" align="center">��λ�����£���</td>
          <td width="105" bordercolor="0">&nbsp;</td>
          <td width="100" bordercolor="0" align="center">����ˣ�</td>
          <td width="190" bordercolor="0">&nbsp;</td>
          <td width="145" bordercolor="0" align="center">������ڣ�</td>
          <td bordercolor="0">&nbsp;</td>
        </tr>
     </table>
	
  <tr>
    <td width="650" height="303"><table width="650" border="1" cellspacing="0">
      <tr>
        <td width="55" height="35" align="center">��&nbsp;&nbsp;��</td>
        <td colspan="2" align="center"><bean:write name="person" property="name"/>&nbsp;</td>
        <td width="75" colspan="2"><div align="center">��&nbsp;&nbsp;��</div></td>
        <td width="85" align="center"><szghrs:viewDicItemName dicTypeCode="<%= DicParameter.SZRS0001 %>" dicItemCode="${person.sexCode}"/>&nbsp;</td>
        <td width="85" colspan="2" align="center">��������</td>
        <td colspan="2" align="center"><bean:write name="person" property="birthday" format="yyyy.MM.dd"/>&nbsp;</td>
        <td colspan="2" rowspan="5" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td height="35" align="center">��������</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.birthplaceCode}" dicTypeCode="<%=DicParameter.SZRS0003%>"/>&nbsp;</td>
        <td colspan="2" align="center">�������</td>
        <td align="center"><szghrs:viewDicItemName dicItemCode="${person.birthplaceCode}" dicTypeCode="<%=DicParameter.SZRS0003%>"/>&nbsp;</td>
        <td colspan="2" align="center">����</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.peopleCode}" dicTypeCode="<%=DicParameter.SZRS0004%>"/>&nbsp;</td>
        </tr>
      <tr>
        <td height="35" align="center"><div>����״��</div></td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicTypeCode="<%= DicParameter.SZRS0005 %>" dicItemCode="${person.healthStatusCode}"/>&nbsp;</td>
        <td colspan="2" align="center">�μ�����ʱ��</td>
        <td align="center"><bean:write name="person" property="startWorkDate" format="yyyy.MM"/>&nbsp;</td>
        <td colspan="2" align="center">���������ʶ</td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isRetrieFlag">��</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isRetrieFlag">��</logic:notEqual>&nbsp;</td>
        </tr>
      <tr>
        <td height="30" align="center">֤�����</td>
        <td align="center"><szghrs:viewDicItemName dicItemCode="${person.idCode}" dicTypeCode="<%=DicParameter.SZRS0002%>"/>&nbsp;</td>
        <td align="center">֤������</td>
        <td colspan="3" align="center"><bean:write name="person" property="idNo"/>&nbsp;</td>
        <td colspan="2" align="center">���չ���Ա��<br>�����ʶ </td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isCadre">��</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isCadre">��</logic:notEqual>&nbsp;</td>
        </tr>
      <tr>
        <td height="35" colspan="3" align="center">�˳����۾��ˣ��侯����ʶ</td>
        <td colspan="2" align="center"><logic:equal value="<%=Constants.IS_TRUE%>" name="person" property="isVeteran">��</logic:equal><logic:notEqual value="<%=Constants.IS_TRUE%>" name="person" property="isVeteran">��</logic:notEqual>&nbsp;</td>
        <td colspan="3" align="center">��ְ����</td>
        <td colspan="2" align="center"><szghrs:viewDicItemName dicItemCode="${person.administrativeDutyLevel}" dicTypeCode="<%=DicParameter.SZRS_S0021%>"/>&nbsp;</td>
        </tr> 
      <c:set var="sequ" value="0"/>      
      <logic:present name="educationLevelDegree">
      	<logic:iterate id="edu" name="educationLevelDegree">
       <c:if test="${sequ < '1'}">  
      <tr>
        <td rowspan="<c:out value="${fn:length(educationLevelDegree)+1}"/>"  align="center">ѧ<br />
          ��<br />
          ѧ<br />
          λ</td>
        <td height="45" align="center">���� <br />
          ���</td>
        <td height="45" align="center">ѧ��<br />
          ����</td>
        <td width="50" align="center">ѧ��<br />
          ����</td>
        <td width="50" align="center">��ѧ<br />
          רҵ</td>
        <td align="center">��ѧ<br />
          ʱ��</td>
        <td width="50" align="center">��ҵ<br />
          ʱ��</td>
        <td width="50" align="center">ѧλ<br />
          ����</td>
        <td width="49" align="center">ѧλ<br />
          ����</td>
        <td width="64" align="center">ѧλ��<br />
          ������</td>
        <td width="62" align="center">ѧУ����<br />
          λ������</td>
        <td width="46" align="center">ѧλ��<br />
          �赥λ</td>
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
        <td width="54" rowspan="<c:out value="${fn:length(positioningDisposal)+1}"/>"><div align="center">��<br />
          ְ<br />
          ��<br />
          Ϣ</div></td>
        <td width="90" height="35" align="center">ְ������</td>
        <td width="100" height="35" align="center">��ְ��������</td>
        <td width="70" height="35" align="center">��ְʱ��</td>
        <td width="80" height="35" height="35" align="center">ְ������</td>
        <td width="70" align="center">ְ�����</td>
        <td width="80" height="35" align="center">��ְ��ʽ</td>
        <td width="60" height="35" align="center">ְ����</td>
        <td width="50" height="35" align="center">��ְ<br>״̬</td>
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
<INPUT onclick=javascript:printit() type=button value="��ӡ" name="button_print" class="Noprint"/> 
<INPUT onclick=javascript:printpreview(); type=button value=��ӡҳ������ name=button_setup class="Noprint" />	
 <logic:notPresent name="person">
 �޴�����Ϣ
 </logic:notPresent>
</body>
</html:html>
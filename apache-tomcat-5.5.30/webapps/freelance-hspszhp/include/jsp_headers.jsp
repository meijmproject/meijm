<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="struts-logic.tld"  prefix="logic" %>
<%@ taglib uri="struts-logic-el.tld"  prefix="logic-el" %>
<%@ taglib uri="struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="struts-nested.tld" prefix="nested"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@taglib uri="fn.tld" prefix="fn"%>

<%@taglib uri="dictionary.tld" prefix="dictionary" %>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","Public");
response.setHeader("Expires","0");
%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<%@page import="com.yh.platform.core.constant.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.info.ver.unit.comm.dto.VerPbPhotoDTO"%>
<%@page import="com.yh.hr.info.ver.unit.comm.facade.VerPbPhotoFacade"%>
<%@page import="java.util.List"%>
<%@page import="com.yh.platform.core.util.SpringBeanUtil"%>
<%@page import="com.yh.platform.core.util.ConfigUtil"%>
<%@page import="java.io.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.yh.platform.core.util.NumberUtils" %>
<%@ page import="org.apache.commons.collections.CollectionUtils" %>
<%
response.reset();
try
{
	 String photoPath=request.getParameter("photoPath");
	 
		byte[] bs= null;
		//File file = new File("D:/sharenfs/common/yh/file/affiche/76156/761561491809870934.jpg"); 
		File file = new File(photoPath); 
		InputStream ip = new FileInputStream(file);
		bs = new byte[(int)file.length()];
		ip.read(bs,0,bs.length);
		
		ServletOutputStream outs = response.getOutputStream();
		outs.write(bs);
		outs.flush();
		outs.close();
		
		out.clear();   
		out = pageContext.pushBody(); 
	}
catch(RuntimeException  e)
{
	Logger log = Logger.getLogger("web");
	log.error("Image show error!",e);
}
%>

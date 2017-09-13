<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.info.ver.unit.comm.dto.VerPbImageDTO"%>
<%@page import="com.yh.hr.info.ver.unit.comm.facade.VerPbImageFacade"%>
<%@page import="com.yh.platform.core.util.SpringBeanUtil"%>
<%@page import="com.yh.platform.core.util.ConfigUtil"%>
<%@page import="java.io.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.yh.platform.core.util.NumberUtils" %>
<%
	response.reset();
	try
	{
		VerPbImageFacade verPbImageFacade = (VerPbImageFacade) SpringBeanUtil.getBean("verPbImageFacade");
		String personOid=request.getParameter("personOid");

		byte[] bs= null;
		VerPbImageDTO verPbImageDTO = null;
		if(personOid!=null&&personOid.length()>0)
		{
			verPbImageDTO = verPbImageFacade.getPbImage(NumberUtils.longValue(personOid));
			if(verPbImageDTO!=null)
			{
				bs=verPbImageDTO.getPhotoPath();
			}
		}
		
		if(verPbImageDTO==null || verPbImageDTO.getPhotoPath().length < 100)
		{			
			File file = new File(ConfigUtil.getProperty("file.path.affiche")+"photo.jpg");
			//File file = new File(getServletContext().getRealPath("/")+"photo.jpg");
			InputStream ip = new FileInputStream(file);
			
			bs = new byte[(int)file.length()];
			ip.read(bs,0,bs.length);					
			
		}
		
		if(bs!=null && bs.length>0)
		{
			if(verPbImageDTO == null)
				response.setContentType("image/jpg"); 
			else
				if(verPbImageDTO.getPhotoPath().length < 100) {
					response.setContentType("image/jpg"); 
				} 
				else {	
					response.setContentType("image/"+verPbImageDTO.getPhotoType());
				}
				
			ServletOutputStream outs = response.getOutputStream();
			outs.write(bs);
			outs.flush();
			outs.close();
			
			out.clear();   
			out = pageContext.pushBody(); 
		
		}
	}
	catch(RuntimeException  e)
	{
		//e.printStackTrace();
		Logger log = Logger.getLogger("web");
		log.error("Image show error!",e);
	}%>

package com.yh.platform.core.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SafeFilter implements Filter {
	public final static Logger log = Logger.getLogger(SafeFilter.class);
	//private static final int MAXLENOFREQUEST = 1024000;
	private String use_safe;
	private String[] s_sign ;
	private String[] s_or;
	private String s_or_view;//s_or 的显示
	private String[] s_dos;
	private String s_rejectPage;
	private String[] s_and;
	private String[] s_referer;
	private String[] s_sign_replace;
	private String s_to_sdc;

	public void init(FilterConfig config) throws ServletException 
	{
		if(config.getInitParameter("use_safe") != null)
		{
			use_safe = config.getInitParameter("use_safe");
		}
		if (config.getInitParameter("s_sign") != null)
		{
			String sign = config.getInitParameter("s_sign");
			if (sign != null){
				if (sign.indexOf(",") != -1)
				{
					s_sign = sign.split(",");
				}
				//只能用","分隔，因为配置的字符中含有分号，例如&lt;是完整含义
				else
				{
					s_sign = sign.concat(",").split(",");
				}
			}
		}
        if (config.getInitParameter("s_or") != null)
        {
        	String or = config.getInitParameter("s_or");
			if (or != null){
				if (or.indexOf(",") != -1)
				{
					s_or = or.split(",");
					s_or_view = " " + or.replaceAll(",", " , ");
				}
				else if (or.indexOf(";") != -1)
				{
					s_or = or.split(";");
					s_or_view = " " + or.replaceAll(";", " ; ");
				}
			}
        }
        if (config.getInitParameter("s_dos") != null)
        {
        	String sdo = config.getInitParameter("s_dos");
        	if (sdo != null){
        		if (sdo.indexOf(",") != -1)
        			s_dos = sdo.split(",");
        		else if (sdo.indexOf(";") != -1)
        			s_dos = sdo.split(";");        	
        	}
        }
        if (config.getInitParameter("s_rejectPage") != null)
        {
        	s_rejectPage = config.getInitParameter("s_rejectPage");
        }
        if (config.getInitParameter("s_and") != null)
		{
			String sand = config.getInitParameter("s_and");
			if (sand != null)
			{
				if (sand.indexOf(",") != -1)
				{
					s_and = sand.split(",");
				}
				else if (sand.indexOf(";") != -1)
				{
					s_and = sand.split(";");        	
				}else
				{
					s_and = sand.concat(",").split(",");
				}
			}
		}
        if (config.getInitParameter("s_referer") != null)
		{
			String sreferer = config.getInitParameter("s_referer");
			if (sreferer != null)
			{
				if (sreferer.indexOf(",") != -1)
				{
					s_referer = sreferer.split(",");
				}else
				{
					s_referer = sreferer.concat(",").split(",");
				}
			}
		}
        if (config.getInitParameter("s_sign_replace") != null)
		{
			String srefererReplace = config.getInitParameter("s_sign_replace");
			if (srefererReplace != null)
			{
				if (srefererReplace.indexOf(",") != -1)
				{
					s_sign_replace = srefererReplace.split(",");
				}else
				{
					s_sign_replace = srefererReplace.concat(",").split(",");
				}
			}
		}
        if (config.getInitParameter("s_to_sdc") != null)
		{
        	s_to_sdc = config.getInitParameter("s_to_sdc");
		}
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	throws IOException, ServletException 
	{
		//读取开关，为Y才使用
		if(!"Y".equalsIgnoreCase(use_safe))
		{
			log.info("safeFilter: the use_safe flag is not Y,don't use SafeFilter!");
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//httponly是微软对cookie做的扩展,该值指定 Cookie 是否可通过客户端脚本访问, 
		//解决用户的cookie可能被盗用的问题,减少跨站脚本攻击  
//		response.setHeader( "Set-Cookie", "name=value; HttpOnly");
		
		//过滤未知来源
		if(checkReferer(request) == 0)
		{
			log.info("safeFilter: 未知来源请求:"+request.getHeader("Referer"));
			rejectRequest(request,response,"未知来源请求请确认！");
			return;
		}
		
		String queryString = request.getQueryString();

		String parameterString = getParameterString(servletRequest);

		String checkString = queryString + "&" + parameterString;
		
		log.info("checkString = "+ checkString);
		
		int sdo = requireFilter(request);
		
		if (sdo == 0) 
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}

		int ret = checkQueryString(checkString);

		if (ret == 0) 
		{
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request,s_to_sdc);
			filterChain.doFilter(xssRequest, response);
		}
		else if(ret == 1)
		{
			rejectRequest(request,response,"请不要输入中括号中的 " + Arrays.deepToString(s_sign) + " 任意字符！");
		}
		else if(ret == 2) 
		{
			rejectRequest(request,response,"请不要输入以下字符串:" + s_or_view);
		}
		else if(ret == 3) 
		{
			rejectRequest(request,response,"请不要输入以下字符串:" + Arrays.deepToString(s_and));
		}
	}

	@SuppressWarnings("unchecked")
	public String getParameterString(ServletRequest servletRequest)
	{
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		StringBuffer sb = new StringBuffer();
		boolean addDelimeter = false;

		Enumeration<String> parameters = req.getParameterNames();
		log.info("-------------now print parameters-----------------------");
		while(parameters.hasMoreElements())
		{
			String parameter = (String)parameters.nextElement();
			log.info(parameter + " = ");
			if(addDelimeter)
			{
				sb.append("&");
			}
			sb.append(parameter).append("=");
			String[] a = req.getParameterValues(parameter);
			for(int i=0;i<a.length;i++){
				log.info(" = "+a[i] );
				sb.append(a[i]).append(" ");
			}
		}
		return sb.toString();
	}
	
	private int requireFilter(HttpServletRequest req) {
		String uri = req.getRequestURI();
        log.info("Now request uri is :"+uri);
        
        int t = uri.indexOf(".do");
		if(t>0)
			uri = uri.substring(0,t);
		
		int begin = uri.lastIndexOf("/");		
		
		if (begin >=0 )
			uri = uri.substring(begin+1);
		
        if (s_dos!=null)
        {
			for (int i = 0; i < s_dos.length; i++)
			{
				if (s_dos[i].equals(uri))
				{
					log.info("Skipped filter");
					return 0;
				}
			}
		}
        return 1;
    }

	public int checkQueryString(String queryString)
	{
		if(null == queryString)
		{
			return 0;
		}
		int passed = 0;
		String query = queryString.toLowerCase();
		
		//解决明码攻击，例如：<script 
		String query_sign = query.replaceAll(" ", "");
		String urldecode;
		try {
			//替换特殊编码字符  如%00
			if(null != s_sign_replace)
			{
				for(String rp : s_sign_replace)
				{
					query_sign = query_sign.replaceAll(rp, "");
				}
			}			
			//解决编码后的攻击,例如：%3Cscript
			urldecode = URLDecoder.decode(query_sign, "gbk").replaceAll(" ", "");
		} catch (Exception e) {
			//出现异常均不处理
			urldecode = query_sign;
		}		
		if (s_sign != null) 
		{
			for (int i = 0; i < s_sign.length; i++) {
				
				if(query_sign.contains(s_sign[i]))
				{
					log.info("with or character");
					passed = 1;
					return passed;
				}else if(urldecode.contains(s_sign[i]))
				{
					log.info("with or character encode");
					passed = 1;
					return passed;
				}
					
			}
		}
		
		if(s_or != null && s_or.length != 0)
		{
			for (int i = 0; i < s_or.length; i++) {
				
				if(query.contains(" " + s_or[i] + " "))
				{
					log.info("with or character");
					passed = 2;
					return passed;
				}
			}
		}
		//处理一些不能单独过滤字符，需组合过滤的漏洞，如window.location，单独拆分过滤可能影响正常功能
		if(s_and != null && s_and.length != 0)
		{
			for (int i = 0; i < s_and.length; i++) 
			{
				//example:window.location
				for(String a_nd : s_and[i].split("\\."))
				{
					if(query.contains(a_nd) || urldecode.contains(a_nd))
					{
						passed = 3;
					}else
					{
						passed = 0;
						break;
					}
				}
				if(passed == 3)
				{
					log.info("with and character");
					return passed;
				}
			}
		}
		return passed;
	}

	public void destroy() {
	}
	/**
	 * 校验请求来源
	 * 解决跨站点请求伪造漏洞
	 * @param request
	 * @return
	 */
	public int checkReferer(HttpServletRequest request)
	{
		String referer = request.getHeader("Referer");
		//如果已配置，则执行过滤，否则未配置过滤项默认为合法
		//注意，通过window.location连接过来的，无referer值，默认合法
		if(null != referer && s_referer != null && s_referer.length != 0)
		{
			for(String s_ref : s_referer)
			{
				if(referer.startsWith(s_ref))
				{
					return 1;
				}
			}
			return 0;
		}else
		{
			return 1;
		}
	}
	/**
	 * 因请求内容不合法，拒绝请求
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void rejectRequest(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException
	{
		//自定义较友好的提示界面。
		request.setAttribute("cause",message);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400   请求语法错误
		request.getRequestDispatcher(s_rejectPage).forward(request, response);
		//通用标准的错误提示
//		response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
	}
}

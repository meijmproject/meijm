package com.yh.platform.core.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.platform.core.util.CommonFunctions;
import com.yh.platform.core.web.UserContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yh.platform.core.exception.ServiceException;

/**
 * default filter:
 * 1. set request encoding
 * 2. check user login
 * 3. call RequestListener if exists
 * 
 * NOTE: login pages should be placed in /login, which will be skiped at step 2
 * 
 * @author chenkb
 */
public class SecurityFilter implements Filter
{
	private String m_loginUrl ;
	private String sso_check_enabled;
	private String sso_login_url;
    private String[] skipCheckURI;
    private String[] skipFilterStarter;
    @SuppressWarnings("unused")
	private String m_authorityUrl ;
    private FilterConfig	filterConfig	= null;
    
   
    private Logger log=Logger.getLogger(SecurityFilter.class);
    
	public void init(FilterConfig config) throws ServletException 
	{		
		this.filterConfig = config;		
		getSkipCheckURL(this.filterConfig);	        
	    getSkipCheckDir(this.filterConfig);
	        
        if (config.getInitParameter("login_url") != null) {
            m_loginUrl = config.getInitParameter("login_url");
        }
        if (config.getInitParameter("security_check_fail_uri") != null)
        {
        	m_authorityUrl = config.getInitParameter("security_check_fail_uri");
        }
        
        if (config.getInitParameter("sso_check_enabled") != null)
        {
        	sso_check_enabled = config.getInitParameter("sso_check_enabled");
        }
        
        if (config.getInitParameter("sso_login_url") != null)
        {
        	sso_login_url = config.getInitParameter("sso_login_url");
        }
        //working for sso
        if("Y".equalsIgnoreCase(sso_check_enabled))
        {
        	if(sso_login_url!=null)
        	{
        		m_loginUrl = sso_login_url;
        	}
        }
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException	
	{			
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        
        HttpSession sess=req.getSession();        
        if (sess == null )
        {        	
        	String redir=req.getContextPath()+m_loginUrl;
        	if("Y".equalsIgnoreCase(sso_check_enabled))
    	 	{
    		 	redir = m_loginUrl;
    	 	}
	      	resp.sendRedirect(redir);
	      	return;
        }
        
        log.info("session id is :"+sess.getId());
        
        //通过会话更新当前线程全局变量
        UserContext uc=(UserContext)sess.getAttribute(UserContext.SESSION_CONTEXT);
        if(uc != null)
        {
        	UserContext.setInstance(uc);
            UserContext.setSession(sess);
        }
        
            //we don't filter login page and related resources
        if(!requireFilter(req)) {
            chain.doFilter(request, response);
            return;
        }
        
        if(uc==null) 
        {
        	log.info("UserContext not found in curent session");
        	sess.invalidate();
      		//not login, redirect to login page
      		String redir=req.getContextPath()+m_loginUrl;            
      		if("Y".equalsIgnoreCase(sso_check_enabled))
      		{
          		redir = m_loginUrl;
      		}
      		log.info("Redirect to "+redir);
      		if (StringUtils.isNotEmpty(req.getHeader("X-Requested-With"))) {
      			log.info("*************登录过期，ajax 请求，写入错误码988 ************* ");
      			resp.setStatus(988);//自定义代码，表示跳转到登录页面
      			return;
      		}
      		resp.sendRedirect(redir);
       		return;
        }
                
        // whether security check required
        if (CommonFunctions.isSecurityCheckRequired())
        {
        	try
			{
				if (checkAuthentication((HttpServletRequest) request) == false)
				{
					log.info("request uri is: "+req.getRequestURI());
					String reqMethod = request.getParameter("_req");
					if (StringUtils.isNotEmpty(reqMethod) && reqMethod.equalsIgnoreCase("ext"))
					{
						log.info("check Authentication failed in ext request.");
						response.setContentType("text/html;charset=GBK");
						request.setCharacterEncoding("GBK");
						response.getWriter().print("'warning':'<font color=red>对不起，您没有该功能的操作权限!</font>'");
						return;
					}
					else
					{
						log.info("check Authentication failed in normal request.");
						response.setContentType("text/html;charset=GBK");
						request.setCharacterEncoding("GBK");
						response.getWriter().print("<font size=6 color=red>对不起，您没有该功能的操作权限!</font>");
						return;
					}
				}
			}
			catch (Exception se)
			{
				se.printStackTrace();
				throw new ServletException(se);
			}
		}
        
        try {
            chain.doFilter(request, response);
        }catch(Exception e) {
        	e.printStackTrace();
        } finally {
            UserContext.setInstance(null);
            UserContext.setSession(null);
        }

	}

    /**
     * we skip /login
     *  
     * @param uri
     * @return
     */
	private boolean requireFilter(HttpServletRequest req) {
        String uri=req.getRequestURI().substring(req.getContextPath().length());
        log.info("Now request uri is :"+uri);
        if(log.isInfoEnabled())
        {
        	if(StringUtils.isNotEmpty(req.getQueryString()) && req.getQueryString().indexOf("&") > 0)
            {
            	log.info("Now request uri is :"+req.getRequestURI()+"?"+StringUtils.substring(req.getQueryString(), 0,req.getQueryString().indexOf("&")));
            }else if(StringUtils.isNotEmpty(req.getQueryString()))
            {
            	log.info("Now request uri is :"+req.getRequestURI()+"?"+StringUtils.substring(req.getQueryString(), 0));
            }else
            {
            	log.info("Now request uri is :"+req.getRequestURI());
            }
        }
        if (skipFilterStarter!=null)
        {
			for (int i = 0; i < skipFilterStarter.length; i++)
			{
				if (uri.startsWith(skipFilterStarter[i]))
				{
					log.info("Skipped filter");
					return false;
				}
			}
		}
        return true;
    }
	
	
	/**
	 * get skip check url
	 * 
	 * */
	private void getSkipCheckURL(FilterConfig filterConfig)
	{
		if (filterConfig!=null)
		{
			String skipCheckUriStr = filterConfig.getInitParameter("security_check_skip_uri");
			if (skipCheckUriStr != null){
				if (skipCheckUriStr.indexOf(",") != -1)
					skipCheckURI = skipCheckUriStr.split(",");
				else if (skipCheckUriStr.indexOf(";") != -1)
					skipCheckURI = skipCheckUriStr.split(";");        	
			}
		}
		
		if (log.isDebugEnabled())
		{
			if (skipCheckURI != null )
			{
				for(int i=0 ;i < skipCheckURI.length;i++)
				log.debug(" skip check url "+i+":"+skipCheckURI[i]);
			}
		}
	}
	
	/**
	 * get skip check dir
	 * 
	 * */
	private void getSkipCheckDir(FilterConfig filterConfig)
	{
		
		String skipFilterDirs = filterConfig.getInitParameter("filter_skip_dir");
        if (skipFilterDirs != null){
        	if (skipFilterDirs.indexOf(",") != -1)
        		skipFilterStarter = skipFilterDirs.split(",");
        	else if (skipFilterDirs.indexOf(";") != -1)
        		skipFilterStarter = skipFilterDirs.split(";");        	
        }
        if (log.isDebugEnabled())
        {
        	if(skipFilterStarter!=null)
        	{
        		for(int i=0 ; i<skipFilterStarter.length;i++)
        		{
        			log.debug(" skip check dir "+i+":"+skipFilterStarter[i]);
        			
        		}
        	}
        }
	}
	
	/**
	 * Check whether user is already authenticated.
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkAuthentication(HttpServletRequest request)throws ServiceException
	{
		String uri = request.getRequestURI();
		log.info("check authertication:: uri="+uri+"?method="+request.getParameter("method"));
		int t = uri.indexOf(".do");
		if(t>0)
			uri = uri.substring(0,t);
		
		int begin = uri.lastIndexOf("/");		
		
		if (begin >=0 )
			uri = uri.substring(begin+1);
		
		// whether skip
		if (skipCheckURI != null)
		{
			for (int i = 0; i < skipCheckURI.length; i++)
			{
				if (skipCheckURI[i].equals(uri))
				{
					log.info("uri is skipped by checking");
					return true;
				}
			}
		}
		// whether been autherticated
		UserContext uc = (UserContext) request.getSession().getAttribute(UserContext.SESSION_CONTEXT);
		if(uc != null)
		{
			@SuppressWarnings("rawtypes")
			List resources = uc.getResources();
			if( resources.contains(uri))
			{
				log.info("uri is autherticated successfully");
				return true;
			}
		}
		
		return false;
	}

    public void destroy() {
        
    }
}

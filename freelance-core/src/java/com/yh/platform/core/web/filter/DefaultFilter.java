package com.yh.platform.core.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * default filter:
 * 1. set request encoding
 * 2. check user login
 * 3. call RequestListener if exists
 * <p/>
 * NOTE: login pages should be placed in /login, which will be skiped at step 2
 *
 * @author eric
 */
public class DefaultFilter implements Filter {
    private Log log = LogFactory.getLog(this.getClass());

    private String m_encoding = "UTF-8";
    private String resp_encoding = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
        if (config.getInitParameter("encoding") != null) {
            m_encoding = config.getInitParameter("encoding");
        }
        if (config.getInitParameter("out_encoding") != null) {
            resp_encoding = config.getInitParameter("out_encoding");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(m_encoding);
        response.setContentType("text/html;charset="+resp_encoding);
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("system error!", e);
        }
    }

    public void destroy() {

    }
}

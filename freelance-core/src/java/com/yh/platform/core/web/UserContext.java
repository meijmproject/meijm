package com.yh.platform.core.web;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.StringUtil;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

/**
 * user context which is stored both in session and thread local,
 * you can simply get UserContext by UserContext.getInstance().
 *
 * @author eric
 */
public final class UserContext implements Serializable {
	private static final long serialVersionUID = -2183856788064560222L;
	
	public static final String SESSION_CONTEXT = "JADE_USER_CONTEXT";
    public static final String WEB_KEY_SYSTEMID = "WEB_KEY_SYSTEMID";

    private static final ThreadLocal<UserContext> s_threadContext = new ThreadLocal<UserContext>();
    private static final ThreadLocal<HttpSession> s_sessionContext = new ThreadLocal<HttpSession>();

	private String			m_uid;
	private String			m_agentUid;
	private String			m_displayName;
	private String			m_systemPositionOid;
	private List<String>	m_resources;

    private X509Certificate	x509Certificate;
    
    private HashMap<String, Object> m_attributes = new HashMap<String, Object>();

    /**
     * @return the x509Certificate
     */
    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    /**
     * @param x509Certificate the x509Certificate to set
     */
    public void setX509Certificate(X509Certificate x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public String getDisplayName() {
        return m_displayName;
    }

    public void setDisplayName(String displayName) {
        m_displayName = displayName;
    }

    public String getUid() {
        return m_uid;
    }

    public void setUid(String uid) {
        m_uid = uid;
    }

    public Object getAttribute(String name) {
        return m_attributes.get(name);
    }

    public void setAttribute(String name, Object obj) {
        m_attributes.put(name, obj);
    }

    public String getAttributeAsString(String name, String def) {
        if (m_attributes.containsKey(name)) {
            return (String) m_attributes.get(name);
        }

        return def;
    }

    public int getAttributeAsInt(String name, int def) {
        if (m_attributes.containsKey(name)) {
            return (Integer) m_attributes.get(name);
        }

        return def;
    }

    public static void setInstance(UserContext uc) {
        s_threadContext.set(uc);
    }

    public static UserContext getInstance() {
        return s_threadContext.get();
    }

    public static HttpSession getSession() {
        return s_sessionContext.get();
    }

    public static void setSession(HttpSession session) {
        s_sessionContext.set(session);
    }

    public List<String> getResources() {
        return m_resources;
    }

    public void setResources(List<String> resources) {
        m_resources = resources;
    }

    /**
     * @return the m_agentUid
     */
    public String getAgentUid() {
        return m_agentUid;
    }

    /**
     * @param uid the m_agentUid to set
     */
    public void setAgentUid(String uid) {
        m_agentUid = uid;
    }

    public Long getSystemPositionOid() {
        if (m_systemPositionOid == null || m_systemPositionOid.equals("")) {
            return null;
        } else {
            return new Long(m_systemPositionOid);
        }
    }

    public void setSystemPositionOid(String systemPositionOid) {
        this.m_systemPositionOid = systemPositionOid;
    }


    /**
     * Get current request user context
     *
     * @param request
     * @return
     *//*
    public static UserContext getLogInUser(javax.servlet.http.HttpServletRequest request) {
        return (UserContext) request.getSession().getAttribute(UserContext.SESSION_CONTEXT);
    }

    *//**
     * Get current request user id
     *
     * @param request
     * @return
     *//*
    public static String getLoginUserID(javax.servlet.http.HttpServletRequest request) {
        UserContext userCtx = getLogInUser(request);

        if (userCtx != null)
            return userCtx.getUid();

        return "unknow";
    }
    
    *//**
     * Get current request user id
     *
     * @param request
     * @return
     *//*
    public static String getLoginUserName(javax.servlet.http.HttpServletRequest request) {
        UserContext userCtx = getLogInUser(request);

        if (userCtx != null)
            return userCtx.getDisplayName();

        return "unknow";
    }*/

    /**
     * 当前线程变量
     * @return
     */
    public static UserContext getUserContext() {
        UserContext uc = UserContext.getInstance();
        if (uc == null) {
            WebContext wc = WebContextFactory.get();
            if (wc != null) {
                uc = (UserContext) wc.getSession().getAttribute(UserContext.SESSION_CONTEXT);
            }
        }
        return uc;
    }

    /**
     * 当前线程变量 - systemId
     * @return
     */
    public static String getSystemId() {
        UserContext uc = getUserContext();
        if (null != uc) return uc.getAttributeAsString(UserContext.WEB_KEY_SYSTEMID, null);

        return StringUtil.BLANK;
    }

	/**
	 * 当前线程变量 - 登录人ID
	 * @return
	 */
	public static String getLoginUserID() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return userCtx.getUid();

        return "unknow";
	}
	
	/**
	 * 当前线程变量 - 登录人name
	 * @return
	 */
	public static String getLoginUserName() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return userCtx.getDisplayName();

        return "unknow";
	}
	
	/**
	 * 当前线程变量 - 当前登录人代理的用户ID
	 * @return
	 */
	public static String getLoginAgentUserID() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return userCtx.getAgentUid();

        return "unknow";
	}

	/**
	 *  当前线程变量 - 当前登录人代理的用户ID
	 * @return
	 */
	public static Long getLoginUserUnitOid() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return NumberUtils.createLong(userCtx.getAttribute("unit_oid"));

        return null;
	}
	
	/**
	 * 当前线程变量 - 当前登录人的用户信息OID
	 * @return
	 */
	public static Long getLoginPersonOid() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return NumberUtils.createLong(userCtx.getAttribute("PERSON_OID"));

        return null;
	}
	
	/**
	 * 当前线程变量 - 当前登录人的用户信息来源类型
	 * @return
	 */
	public static String getLoginRefType() {
		UserContext userCtx = getUserContext();

        if (userCtx != null)
            return userCtx.getAttributeAsString("REF_TYPE",null);

        return null;
	}
}

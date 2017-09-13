package com.yh.component.taglib;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.util.CryptoUtil;
import com.yh.platform.core.util.StringMap;

/**
 * bean for TableTag
 * 
 * @author eric
 */
public class TableTagBean
{
    public static final int DEFAULT_PAGE_SIZE = 20;

    private static final String DELIMITER = "\n";
    private static final String CHARSET = "UTF-8";

    // 当前页码
    private int m_page;

    // 每页记录条数
    private int m_pageSize = DEFAULT_PAGE_SIZE;

    // 记录总数
    private int m_total;

    // 排序字段
    private String m_orderBy;

    // 是否是升序
    private boolean m_asc;

    // 查询条件map
    private StringMap m_condition = new StringMap();

    // 本页数据列表
    @SuppressWarnings("rawtypes")
	private List m_list;

    // 是否已经build
    private boolean m_builded = false;

    private String m_conditionParam = null;

    private int m_pageCount;

    private boolean m_canPrev;

    private boolean m_canNext;

    public TableTagBean() {}

    public boolean getAsc() {
        return m_asc;
    }

    public void setAsc(boolean asc) {
        ensureNotBuilded();
        m_asc = asc;
    }

    public StringMap getCondition() {
        return m_condition;
    }

    public void setCondition(StringMap condition) {
        ensureNotBuilded();
        m_condition = condition;
    }

    public String getOrderBy() {
        return m_orderBy;
    }

    public void setOrderBy(String orderBy) {
        ensureNotBuilded();
        m_orderBy = orderBy;
    }

    public int getPage() {
        return m_page;
    }

    public void setPage(int page) {
        ensureNotBuilded();
        m_page = page;
    }

    public int getPageSize() {
        return m_pageSize;
    }

    public void setPageSize(int pageSize) {
        ensureNotBuilded();
        m_pageSize = pageSize;
    }

    public int getTotal() {
        return m_total;
    }

    public void setTotal(int total) {
        ensureNotBuilded();
        m_total = total;
        while(this.getPage() > total)
        {
        	this.setPage((m_page - this.m_pageSize) < 0 ? 0 : (m_page - this.m_pageSize));
        	
        }
    }

    @SuppressWarnings("rawtypes")
	public List getList() {
        return m_list;
    }

    @SuppressWarnings("rawtypes")
	public void setList(List list) {
        ensureNotBuilded();
        m_list = list;
    }

    public void ensureNotBuilded() {
        if (m_builded) {
            throw new IllegalStateException(
                    "Can't set attribute to TableTagBean after build() is called.");
        }
    }

    public boolean getCanNext() {
        return m_canNext;
    }

    public boolean getCanPrev() {
        return m_canPrev;
    }

    public String getConditionParam() {
        return m_conditionParam;
    }

    public int getPageCount() {
        return m_pageCount;
    }

    /**
     * 计算总页数，是否可以前一页，后一页，首页，末页，生成查询条件编码后的字符串。
     * 
     * NOTE:调用build以后将不能再设置该对象的属性
     * 
     * @throws UnsupportedEncodingException
     */
    public void build() throws UnsupportedEncodingException {

        if (m_condition.size() > 0) {
            m_conditionParam = CryptoUtil.base64url_encode(m_condition
                    .toString(DELIMITER).getBytes(CHARSET));
        }

        if (m_total % m_pageSize != 0) {
            m_pageCount = m_total / m_pageSize + 1;
        } else {
            m_pageCount = m_total / m_pageSize;
        }

        m_canPrev = m_page > 0;
        m_canNext = m_page < m_pageCount - 1;

        m_builded = true;
    }

    public static TableTagBean getFromRequest(HttpServletRequest req)
            throws UnsupportedEncodingException {
        TableTagBean r = new TableTagBean();

        String p = req.getParameter("p");
        if (StringUtils.isNotEmpty(p) ) {
            r.setPage(Integer.parseInt(p));
        }
        String o = req.getParameter("o");
        if (StringUtils.isNotEmpty(o)) {
            r.setOrderBy(o);
        }
        String a = req.getParameter("a");
        if (StringUtils.isNotEmpty(a)) {
            r.setAsc(a.equals("1"));
        }
        String c = req.getParameter("c");
        if (StringUtils.isNotEmpty(c)) {
            r.setCondition(decodeConditionParam(c));
        }

        return r;
    }

    private static StringMap decodeConditionParam(String c)
            throws UnsupportedEncodingException {
        String s = new String(CryptoUtil.base64url_decode(c), CHARSET);
        return new StringMap(s, DELIMITER);
    }

    public String getQueryParameter() {
        StringBuffer sb=new StringBuffer();
        
        sb.append("p="+m_page);
        if(m_orderBy!=null) {
            sb.append("&o="+m_orderBy+"&a="+(m_asc?"1":"0"));
        }
        
        if(m_conditionParam!=null) {
            sb.append("&c="+m_conditionParam);
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("[TableTagBean]\r\n");
        sb.append("m_page: " + m_page + "\r\n");
        sb.append("m_pageSize :" + m_pageSize + "\r\n");
        sb.append("m_total :" + m_total + "\r\n");
        sb.append("m_orderBy :" + m_orderBy + "\r\n");
        sb.append("m_asc :" + m_asc + "\r\n");
        sb.append("m_condition :" + m_condition + "\r\n");
        sb.append("m_list(size) :" + m_list.size() + "\r\n");
        sb.append("m_builded :" + m_builded + "\r\n");
        sb.append("m_conditionParam :" + m_conditionParam + "\r\n");
        sb.append("m_pageCount :" + m_pageCount + "\r\n");
        sb.append("m_canPrev :" + m_canPrev + "\r\n");
        sb.append("m_canNext :" + m_canNext + "\r\n");

        return sb.toString();
    }
	/**
	 * 升序值
	 */
    public static final String PAGE_ASC = "ASC";
	/**
	 * 当前页数
	 */
    public static final int PAGE = 0;
    /**
     * 构造函数
     * @param request
     */
    @SuppressWarnings("unchecked")
	public TableTagBean(HttpServletRequest request){
    	
    	String start   = request.getParameter("start");		// 指定页码数
		String limit   = request.getParameter("limit");		// 指定记录数
		String asc     = request.getParameter("dir");		// 是否升序
		String orderBy = request.getParameter("sort"); 		// 按什么字段排序
		m_page 	   = start!=null? Integer.parseInt(start) : PAGE;		// 起始页数
		m_pageSize   = limit!=null? Integer.parseInt(limit) : DEFAULT_PAGE_SIZE;  // 每页记录数
		m_asc  = PAGE_ASC.equals(asc)? true : false;				// 是否升序（true：升序；false：降序）
		m_orderBy = orderBy;
		//构造parmarterMap
		Map<String, String[]> m = request.getParameterMap();
		if(null!=m){
			for(Entry<String, String[]> entry : m.entrySet()){
				String key = entry.getKey();
				String[] value = (String[]) entry.getValue();
				m_condition.put(key, value[0]);
			}
		}
    	
    }
}
	
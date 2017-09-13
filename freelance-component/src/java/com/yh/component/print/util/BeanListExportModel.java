
package com.yh.component.print.util;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * ExportModel bean list implementation
 * 
 * @author eric
 */
@SuppressWarnings("rawtypes")
public class BeanListExportModel implements ExportModel
{
    private static final Logger log=Logger.getLogger(BeanListExportModel.class);
    
    private String[][] m_columns;
    
    private Iterator<? extends Object> m_iterator;
    private Object m_bean;
    
	private Map m_map ;
    
    public BeanListExportModel(String[][] columns,List<? extends Object> list,Map map) {
        m_columns = columns;
        if(CollectionUtils.isNotEmpty(list))
        {
        	m_iterator = list.iterator();
        }
        m_map = map;
    }
    
    public String[][] getColumns() {
        return m_columns;
    }

    public boolean next() {
        ensureNotClose();
        
        m_bean=null;
        if(m_iterator != null)
        {
	        if(m_iterator.hasNext()) {
	            m_bean=m_iterator.next();
	        }
        }
        return m_bean!=null;
    }

    public String getString(String column) {
        ensureNotClose();
        
        if(m_bean==null) {
            return null;
        }
        
        try {
            return BeanUtils.getProperty(m_bean,column);
        } catch (Exception e) {
            log.error("",e);
            return null;
        }
    }

    public void close() {
        if(m_iterator!=null) {
            m_iterator=null;
            m_bean=null;
        }
    }
    
    private void ensureNotClose() {
        if(m_iterator==null) {
        	//去掉抛异常，改为打日志
        	log.info("m_iterator is null!");
        	// throw new IllegalStateException("BeanListExportModel is already closed.");
        }
    }

	public Map getParameters() {
		return m_map;
	}
}

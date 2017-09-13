package com.yh.component.print.util;

import java.util.Map;


/**
 * export data model
 * 
 * @author eric
 */
public interface ExportModel
{
    /**
     * String[0]: column id
     * String[1]: column display name
     * 
     * @return
     */
    public String[][] getColumns();
    
    public boolean next();
    
    public String getString(String column);
    
    @SuppressWarnings("rawtypes")
	public Map getParameters();
    
    public void close();
}

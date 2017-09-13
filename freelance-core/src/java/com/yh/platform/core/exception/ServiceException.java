package com.yh.platform.core.exception;

/**
 * product exception class
 * 
 * @author chenkb
 *
 */
public class ServiceException extends Exception
{
	/**
	 * uid 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String errorKey;
	protected String errorCode;
	protected Object[] values;
    protected Object value1;
    
    /**
     * construct an exception with null as the detailed message
     *
     */
    public ServiceException()
    {
        super();
    }
    
    /**
     * construct an exception with the specified error message
     * 
     * @param message
     */
    public ServiceException(String errorKey,String message)
    {
        super(message);
        this.errorKey = errorKey;
    }
    
    /**
     * construct an exception with error message and root cause
     * 
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause)
    {
        super(message, cause); 
    }
    
    /**
     * construct an exception with errorkey
     * 
     * @param errorKey
     */
    public ServiceException(String errorKey)
    {        
    	super(errorKey);
        this.errorKey = errorKey;
    }
    
    /**
     * construct an exception with the specified error message
     * 
     * @param message
     */
    public ServiceException(String errorKey,String errorCode, String message)
    {
        super(message);
        this.errorKey = errorKey;
    }

    public ServiceException(String errorKey,String errorCode, String message,Object value1)
    {
        super(message);
        this.errorKey = errorKey;
        this.value1 = value1;
        this.errorCode = errorCode;
    }
    
    public ServiceException(String errorKey,String errorCode, String message,Object[] values)
    {
        super(message);
        this.errorKey = errorKey;
        this.values = values;
      
    }
    
    public ServiceException(String errorKey,String errorCode, String message,Object value1, Throwable e)
    {
        super(message,e);
        this.errorKey = errorKey;
        this.value1 = value1;
        this.errorCode = errorCode;
    }
    
    public ServiceException(String errorKey,String errorCode, String message,Object[] values, Throwable e)
    {
        super(message,e);
        this.errorKey = errorKey;
        this.values = values;
      
    }
  
    /**
     * the getter method for error key
     * 
     * @return
     */
    public String getErrorKey()
    {
    	return this.errorKey;
    }  

	/**
	 * @return Returns the value.
	 */
	public Object getValue1()
	{
		return value1;
	}


	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param values the values to set
	 */
	public Object[] getValues() {
		return values;
	}

	
}

package com.yh.platform.core.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <code>{@link XssHttpServletRequestWrapper}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper 
{
	HttpServletRequest orgRequest = null;
	private String to_sdc_char = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request,String toSdcChars) {
		super(request);
		orgRequest = request;
		to_sdc_char = toSdcChars;
	}

	/**
	* 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	* 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	* getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	*/
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	* 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	* 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	* getHeaderNames 也可能需要覆盖
	*/
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	* 将容易引起xss漏洞的半角字符直接替换成全角字符
	*
	* @param s
	* @return
	*/
	private String xssEncode(String s) {
		if (to_sdc_char == null || "".equals(to_sdc_char) || s == null || "".equals(s)) 
		{
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(to_sdc_char.indexOf(c) >= 0)
			{
				sb.append(toSbc(String.valueOf(c)));
			}else
			{
				sb.append(c);
			}		
		}
		return sb.toString();
	}

	/**
	* 获取最原始的request
	*
	* @return
	*/
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	* 获取最原始的request的静态方法
	*
	* @return
	*/
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}
	/**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
    private static String toSbc(String input) {
             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == ' ') {
                 c[i] = '\u3000';
               } else if (c[i] < '\177') {
                 c[i] = (char) (c[i] + 65248);

               }
             }
             return new String(c);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     
    private static String toDbc(String input) {
        

             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == '\u3000') {
                 c[i] = ' ';
               } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                 c[i] = (char) (c[i] - 65248);

               }
             }
        String returnString = new String(c);
        
             return returnString;
    }
    */
}

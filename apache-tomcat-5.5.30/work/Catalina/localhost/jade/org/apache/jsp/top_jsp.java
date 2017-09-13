package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(11);
    _jspx_dependants.add("/include/jsp_headers.jsp");
    _jspx_dependants.add("/WEB-INF/tlds/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-html-el.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-bean-el.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-logic-el.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-tiles.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-nested.tld");
    _jspx_dependants.add("/WEB-INF/tlds/c.tld");
    _jspx_dependants.add("/WEB-INF/tlds/jade.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale.release();
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_html_005fhtml_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_html_005fhtml_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:html
    org.apache.struts.taglib.html.HtmlTag _jspx_th_html_005fhtml_005f0 = (org.apache.struts.taglib.html.HtmlTag) _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale.get(org.apache.struts.taglib.html.HtmlTag.class);
    _jspx_th_html_005fhtml_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhtml_005f0.setParent(null);
    _jspx_th_html_005fhtml_005f0.setLocale(true);
    int _jspx_eval_html_005fhtml_005f0 = _jspx_th_html_005fhtml_005f0.doStartTag();
    if (_jspx_eval_html_005fhtml_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("<head>  \r\n");
        out.write("<title>JadeStarter</title>\r\n");
        out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
        out.write("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"style/common.css\" />\r\n");
        out.write("<style type=\"text/css\">\r\n");
        out.write("<!--\r\n");
        out.write("body {\r\n");
        out.write("\tmargin-left: 0px;\r\n");
        out.write("\tmargin-top: 0px;\r\n");
        out.write("\tborder-bottom: 1px solid #AAA;\r\n");
        out.write(" \tborder-left: 0px solid #999;\r\n");
        out.write("\tborder-right: 0px solid #999;\r\n");
        out.write("\twidth:100%;\r\n");
        out.write("}\r\n");
        out.write("-->\r\n");
        out.write("</style>\r\n");
        out.write("</head>\r\n");
        out.write("<body>\r\n");
        out.write("<table border=\"0\" width=\"100%\" cellpadding=\"2\" cellspacing=\"0\">\r\n");
        out.write("    <tr> \r\n");
        out.write("       <td valign=\"top\"> \r\n");
        out.write("        <table border=\"0\" cellpadding=\"0\" cellspacing=\"2\">\r\n");
        out.write("          <tr> \r\n");
        out.write("            <td valign=\"top\"> <img src=\"images/logo2.gif\" align=\"absMiddle\" height=\"64\" width=\"65\" border=\"0\" />\r\n");
        out.write("            <img src=\"images/logo.gif\" align=\"absMiddle\" height=\"45\" width=\"132\" border=\"0\" /> \r\n");
        out.write("            </td>\r\n");
        out.write("          </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("      </td>\r\n");
        out.write("      <th width=\"388\" align=\"right\" valign=\"top\" nowrap  background=\"images/bg.jpg\"> \r\n");
        out.write("      \t<img src=\"images/help.gif\" border=\"0\" align=\"absmiddle\" height=\"16\" width=\"16\" /> \r\n");
        out.write("        <a href='#' >");
        if (_jspx_meth_bean_005fmessage_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
          return true;
        out.write("</a> \r\n");
        out.write("      \t| <img src=\"images/home.gif\" border=\"0\" align=\"absmiddle\" height=\"16\" width=\"16\" /> \r\n");
        out.write("        <a href=\"#\">");
        if (_jspx_meth_bean_005fmessage_005f1(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
          return true;
        out.write("</a> \r\n");
        out.write("        | <img src=\"images/exit.gif\" border=\"0\" align=\"absmiddle\" height=\"16\" width=\"16\"/> \r\n");
        out.write("        <a href=\"logout.do?method=logout\" target=\"_top\">");
        if (_jspx_meth_bean_005fmessage_005f2(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
          return true;
        out.write("</a> <br/>\r\n");
        out.write("        ");
        if (_jspx_meth_bean_005fmessage_005f3(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
          return true;
        out.write(": <span >Test</span><br/> \r\n");
        out.write("        </br>\r\n");
        out.write("        <div align=\"right\">\r\n");
        out.write("         <script>\r\n");
        out.write("\t\t\ttodayDate = new Date();\r\n");
        out.write("\t\t\tdate = todayDate.getDate();\r\n");
        out.write("\t\t\tmonth= todayDate.getMonth() +1;\r\n");
        out.write("\t\t\tyear= todayDate.getYear();\r\n");
        out.write("\t\t\tdocument.write(\"Today is &nbsp;\")\r\n");
        out.write("\t\t\tif(navigator.appName == \"Netscape\")\r\n");
        out.write("\t\t\t{\r\n");
        out.write("\t\t\t\tdocument.write(1900+year);\r\n");
        out.write("\t\t\t\tdocument.write(\"/\");\r\n");
        out.write("\t\t\t\tdocument.write(month);\r\n");
        out.write("\t\t\t\tdocument.write(\"/\");\r\n");
        out.write("\t\t\t\tdocument.write(date);\r\n");
        out.write("\t\t\t\tdocument.write(\"/\");\r\n");
        out.write("\t\t\t\t}\r\n");
        out.write("\t\t\tif(navigator.appVersion.indexOf(\"MSIE\") != -1)\r\n");
        out.write("\t\t\t{\r\n");
        out.write("\t\t\t\tdocument.write(year);\r\n");
        out.write("\t\t\t\tdocument.write(\"/\");\r\n");
        out.write("\t\t\t\tdocument.write(month);\r\n");
        out.write("\t\t\t\tdocument.write(\"/\");\r\n");
        out.write("\t\t\t\tdocument.write(date);\r\n");
        out.write("\t\t\t\tdocument.write(\"\");\r\n");
        out.write("\t\t\t}\r\n");
        out.write("\t\t\tdocument.write(\"&nbsp;\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 5) document.write(\"Fridy\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 6) document.write(\"Saturday\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 0) document.write(\"Sunday\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 1) document.write(\"Monday\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 2) document.write(\"Tuesday\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 3) document.write(\"Wednesday\")\r\n");
        out.write("\t\t\tif (todayDate.getDay() == 4) document.write(\"Thursday\")\r\n");
        out.write("\t\t\tdocument.write(\"<br>\")\r\n");
        out.write("  \t\t</script>\r\n");
        out.write("\t</div>\r\n");
        out.write("      </th>\r\n");
        out.write("    </tr>\r\n");
        out.write("  </table>   \r\n");
        out.write("</body>\r\n");
        int evalDoAfterBody = _jspx_th_html_005fhtml_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_005fhtml_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale.reuse(_jspx_th_html_005fhtml_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhtml_0026_005flocale.reuse(_jspx_th_html_005fhtml_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f0 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f0.setKey("label.help");
    int _jspx_eval_bean_005fmessage_005f0 = _jspx_th_bean_005fmessage_005f0.doStartTag();
    if (_jspx_th_bean_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f1 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f1.setKey("button.gohome");
    int _jspx_eval_bean_005fmessage_005f1 = _jspx_th_bean_005fmessage_005f1.doStartTag();
    if (_jspx_th_bean_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f2 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f2.setKey("button.logout");
    int _jspx_eval_bean_005fmessage_005f2 = _jspx_th_bean_005fmessage_005f2.doStartTag();
    if (_jspx_th_bean_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f3 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f3.setKey("label.user.code");
    int _jspx_eval_bean_005fmessage_005f3 = _jspx_th_bean_005fmessage_005f3.doStartTag();
    if (_jspx_th_bean_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
    return false;
  }
}

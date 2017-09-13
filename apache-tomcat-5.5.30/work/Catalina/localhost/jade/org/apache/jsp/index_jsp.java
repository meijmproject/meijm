package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  public Object getDependants() {
    return _jspx_dependants;
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
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>JadeStarter</title>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<frameset rows=\"75,*\" cols=\"*\" frameborder=\"0\" border=\"0\" framespacing=\"0\" height=\"100%\">\r\n");
      out.write("\t\t<frame name=\"topFrame\" scrolling=\"no\" noresize src=\"top.jsp\" rows=\"10,*\">\r\n");
      out.write("\t\t<frameset cols=\"190,*\" frameborder=\"0\" framespacing=\"0\" border=\"0\">\r\n");
      out.write("\t\t\t<frame name=\"leftFrame\" src=\"treemenu.jsp\" border=\"1\" height=\"100%\">\r\n");
      out.write("\t\t\t<frame name=\"mainFrame\" src=\"main.jsp\" scrolling=\"YES\" border=\"6\">\r\n");
      out.write("\t\t</frameset>\r\n");
      out.write("\t</frameset>\r\n");
      out.write("\t<noframes>\r\n");
      out.write("\t\t<body bgcolor=\"#FFFFFF\" text=\"#000000\">\r\n");
      out.write("\r\n");
      out.write("\t\t</body>\r\n");
      out.write("\t</noframes>\r\n");
      out.write("</html>\r\n");
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
}

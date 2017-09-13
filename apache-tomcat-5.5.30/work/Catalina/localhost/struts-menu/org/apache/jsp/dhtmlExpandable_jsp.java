package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dhtmlExpandable_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\r\n");
      out.write("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Expandable List Menu Example, using DHTML</title>\r\n");
      out.write("    \r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\"\r\n");
      out.write("        href=\"styles/global.css\" />\r\n");
      out.write("        \r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" \r\n");
      out.write("        href=\"styles/menuExpandable.css\" />    \r\n");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\" src=\"scripts/menuExpandable.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "listMenu.jsp", out, true);
      out.write("\r\n");
      out.write(" \r\n");
      out.write("<div id=\"content\">\r\n");
      out.write("This menu is based on <a href=\"http://www.gazingus.org/dhtml/?id=109\">this example</a>\r\n");
      out.write("from Dave Lindquist.\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"pageSource\">\r\n");
      out.write("<strong>View source of files used in this page:</strong><br />\r\n");
      out.write("- <a href=\"styles/menuExpandable.css.src\">styles/menuExpandable.css</a>\r\n");
      out.write("    <a href=\"styles/menuExpandable.css\" \r\n");
      out.write("        title=\"Right click and 'Save Target As...' to download\">\r\n");
      out.write("        <img src=\"images/download.gif\" \r\n");
      out.write("        class=\"download\" alt=\"download\"/></a><br />\r\n");
      out.write("- <a href=\"scripts/menuExpandable.js.src\">scripts/menuExpandable.js</a>\r\n");
      out.write("    <a href=\"scripts/menuExpandable.js\" \r\n");
      out.write("        title=\"Right click and 'Save Target As...' to download\">\r\n");
      out.write("        <img src=\"images/download.gif\" \r\n");
      out.write("        class=\"download\" alt=\"download\"/></a><br />\r\n");
      out.write("- <a href=\"images/minus.gif\">images/minus.gif</a><br />\r\n");
      out.write("- <a href=\"images/plus.gif\">images/plus.gif</a><br />\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"source\">\r\n");
      out.write("\t<a href=\"");
      out.print(request.getRequestURI());
      out.write(".src\">View JSP Source</a>\r\n");
      out.write("  <br />\r\n");
      out.write("  <a href=\"");
      out.print(request.getContextPath());
      out.write("/index.jsp\">Back to Index</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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

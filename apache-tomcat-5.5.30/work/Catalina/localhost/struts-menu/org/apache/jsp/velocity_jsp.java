package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class velocity_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/taglibs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.release();
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\r\n");
      out.write("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Velocity Menu Example</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\"\r\n");
      out.write("            href=\"styles/global.css\" />\r\n");
      out.write("    </head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id=\"content\">\r\n");
      out.write("Table Menu with Velocity:<br />\r\n");
      if (_jspx_meth_menu_005fuseMenuDisplayer_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<hr />\r\n");
      out.write("\r\n");
      out.write("Simple menu with Velocity:<br />\r\n");
      if (_jspx_meth_menu_005fuseMenuDisplayer_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"pageSource\">\r\n");
      out.write("<strong>Files used in this page:</strong><br />\r\n");
      out.write("- <a href=\"templates/table.html.src\">templates/table.html</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"source\">\r\n");
      out.write("    <a href=\"");
      out.print(request.getRequestURI());
      out.write(".src\">View JSP Source</a><br />\r\n");
      out.write("    <a href=\"");
      out.print(request.getContextPath());
      out.write("/index.jsp\">Back to Index</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_menu_005fuseMenuDisplayer_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:useMenuDisplayer
    net.sf.navigator.taglib.UseMenuDisplayerTag _jspx_th_menu_005fuseMenuDisplayer_005f0 = (net.sf.navigator.taglib.UseMenuDisplayerTag) _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.get(net.sf.navigator.taglib.UseMenuDisplayerTag.class);
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setParent(null);
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setName("Velocity");
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setConfig("/templates/table.html");
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setBundle("org.apache.struts.action.MESSAGE");
    int _jspx_eval_menu_005fuseMenuDisplayer_005f0 = _jspx_th_menu_005fuseMenuDisplayer_005f0.doStartTag();
    if (_jspx_eval_menu_005fuseMenuDisplayer_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  ");
        if (_jspx_meth_menu_005fdisplayMenu_005f0(_jspx_th_menu_005fuseMenuDisplayer_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_menu_005fuseMenuDisplayer_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_menu_005fuseMenuDisplayer_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.reuse(_jspx_th_menu_005fuseMenuDisplayer_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.reuse(_jspx_th_menu_005fuseMenuDisplayer_005f0);
    return false;
  }

  private boolean _jspx_meth_menu_005fdisplayMenu_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_menu_005fuseMenuDisplayer_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:displayMenu
    net.sf.navigator.taglib.DisplayMenuTag _jspx_th_menu_005fdisplayMenu_005f0 = (net.sf.navigator.taglib.DisplayMenuTag) _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.get(net.sf.navigator.taglib.DisplayMenuTag.class);
    _jspx_th_menu_005fdisplayMenu_005f0.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fdisplayMenu_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_menu_005fuseMenuDisplayer_005f0);
    _jspx_th_menu_005fdisplayMenu_005f0.setName("indexMenu");
    int _jspx_eval_menu_005fdisplayMenu_005f0 = _jspx_th_menu_005fdisplayMenu_005f0.doStartTag();
    if (_jspx_th_menu_005fdisplayMenu_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f0);
    return false;
  }

  private boolean _jspx_meth_menu_005fuseMenuDisplayer_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:useMenuDisplayer
    net.sf.navigator.taglib.UseMenuDisplayerTag _jspx_th_menu_005fuseMenuDisplayer_005f1 = (net.sf.navigator.taglib.UseMenuDisplayerTag) _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.get(net.sf.navigator.taglib.UseMenuDisplayerTag.class);
    _jspx_th_menu_005fuseMenuDisplayer_005f1.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fuseMenuDisplayer_005f1.setParent(null);
    _jspx_th_menu_005fuseMenuDisplayer_005f1.setName("Velocity");
    _jspx_th_menu_005fuseMenuDisplayer_005f1.setConfig("simple.html");
    _jspx_th_menu_005fuseMenuDisplayer_005f1.setBundle("org.apache.struts.action.MESSAGE");
    int _jspx_eval_menu_005fuseMenuDisplayer_005f1 = _jspx_th_menu_005fuseMenuDisplayer_005f1.doStartTag();
    if (_jspx_eval_menu_005fuseMenuDisplayer_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  ");
        if (_jspx_meth_menu_005fdisplayMenu_005f1(_jspx_th_menu_005fuseMenuDisplayer_005f1, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_menu_005fuseMenuDisplayer_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_menu_005fuseMenuDisplayer_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.reuse(_jspx_th_menu_005fuseMenuDisplayer_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fuseMenuDisplayer_0026_005fname_005fconfig_005fbundle.reuse(_jspx_th_menu_005fuseMenuDisplayer_005f1);
    return false;
  }

  private boolean _jspx_meth_menu_005fdisplayMenu_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_menu_005fuseMenuDisplayer_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:displayMenu
    net.sf.navigator.taglib.DisplayMenuTag _jspx_th_menu_005fdisplayMenu_005f1 = (net.sf.navigator.taglib.DisplayMenuTag) _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.get(net.sf.navigator.taglib.DisplayMenuTag.class);
    _jspx_th_menu_005fdisplayMenu_005f1.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fdisplayMenu_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_menu_005fuseMenuDisplayer_005f1);
    _jspx_th_menu_005fdisplayMenu_005f1.setName("ToDoListMenuFile");
    int _jspx_eval_menu_005fdisplayMenu_005f1 = _jspx_th_menu_005fdisplayMenu_005f1.doStartTag();
    if (_jspx_th_menu_005fdisplayMenu_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f1);
    return false;
  }
}

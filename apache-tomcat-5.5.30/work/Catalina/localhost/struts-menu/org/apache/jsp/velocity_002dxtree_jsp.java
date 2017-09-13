package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class velocity_002dxtree_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head>\r\n");
      out.write("    <title>XTree (with Velocity) Example</title>\r\n");
      out.write("\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\"\r\n");
      out.write("        href=\"styles/global.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\"\r\n");
      out.write("        href=\"styles/xtree.css\" />\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("    div.container {\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        width: 200px;\r\n");
      out.write("        top: 0px;\r\n");
      out.write("        left: 0px;\r\n");
      out.write("        height: 100%;\r\n");
      out.write("        padding: 5px;\r\n");
      out.write("        overflow: auto;\r\n");
      out.write("    }\r\n");
      out.write("    </style>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"scripts/xtree.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      if (_jspx_meth_menu_005fuseMenuDisplayer_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"content\" style=\"margin-left: 150px\">\r\n");
      out.write("The homepage for this menu is <a href=\"http://webfx.eae.net/dhtml/xtree/index.html\">http://webfx.eae.net/dhtml/xtree/index.html</a>.\r\n");
      out.write("Please refer to this site and it's support system for any menu-specific questions. \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"pageSource\">\r\n");
      out.write("<strong>Files used in this page:</strong><br />\r\n");
      out.write("- <a href=\"templates/xtree.html.src\">templates/xtree.html</a>\r\n");
      out.write("    <a href=\"templates/xtree.html\" \r\n");
      out.write("        title=\"Right click and 'Save Target As...' to download\">\r\n");
      out.write("        <img src=\"images/download.gif\" \r\n");
      out.write("        class=\"download\" alt=\"download\"/></a><br />\r\n");
      out.write("- <a href=\"styles/xtree.css.src\">styles/xtree.css</a>\r\n");
      out.write("    <a href=\"styles/xtree.css\" \r\n");
      out.write("        title=\"Right click and 'Save Target As...' to download\">\r\n");
      out.write("        <img src=\"images/download.gif\" \r\n");
      out.write("        class=\"download\" alt=\"download\"/></a><br />\r\n");
      out.write("- <a href=\"scripts/xtree.js.src\">scripts/xtree.js</a>\r\n");
      out.write("    <a href=\"scripts/xtree.js\" \r\n");
      out.write("        title=\"Right click and 'Save Target As...' to download\">\r\n");
      out.write("        <img src=\"images/download.gif\" \r\n");
      out.write("        class=\"download\" alt=\"download\"/></a><br />\r\n");
      out.write("- <a href=\"images/foldericon.png\">images/foldericon.png</a><br />\r\n");
      out.write("- <a href=\"images/openfoldericon.png\">images/openfoldericon.png</a><br />\r\n");
      out.write("- <a href=\"images/foldericon.png\">images/foldericon.png</a><br />\r\n");
      out.write("- <a href=\"images/file.png\">images/file.png</a><br />\r\n");
      out.write("- <a href=\"images/I.png\">images/I.png</a><br />\r\n");
      out.write("- <a href=\"images/L.png\">images/L.png</a><br />\r\n");
      out.write("- <a href=\"images/Lminus.png\">images/Lminus.png</a><br />\r\n");
      out.write("- <a href=\"images/Lplus.png\">images/Lplus.png</a><br />\r\n");
      out.write("- <a href=\"images/T.png\">images/T.png</a><br />\r\n");
      out.write("- <a href=\"images/Tminus.png\">images/Tminus.png</a><br />\r\n");
      out.write("- <a href=\"images/Tplus.png\">images/Tplus.png</a><br />\r\n");
      out.write("- <a href=\"images/blank.png\">images/blank.png</a><br />\r\n");
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
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setConfig("/templates/xtree.html");
    _jspx_th_menu_005fuseMenuDisplayer_005f0.setBundle("org.apache.struts.action.MESSAGE");
    int _jspx_eval_menu_005fuseMenuDisplayer_005f0 = _jspx_th_menu_005fuseMenuDisplayer_005f0.doStartTag();
    if (_jspx_eval_menu_005fuseMenuDisplayer_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("if (document.getElementById) {\r\n");
        out.write("    ");
        if (_jspx_meth_menu_005fdisplayMenu_005f0(_jspx_th_menu_005fuseMenuDisplayer_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_menu_005fdisplayMenu_005f1(_jspx_th_menu_005fuseMenuDisplayer_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_menu_005fdisplayMenu_005f2(_jspx_th_menu_005fuseMenuDisplayer_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("} else {\r\n");
        out.write("  var msg = \"Your browser does not support document.getElementById().\\n\";\r\n");
        out.write("    msg += \"You must use a modern browser for this menu.\";\r\n");
        out.write("  alert(msg);\r\n");
        out.write("}\r\n");
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
    _jspx_th_menu_005fdisplayMenu_005f0.setName("ToDoListMenuFile");
    int _jspx_eval_menu_005fdisplayMenu_005f0 = _jspx_th_menu_005fdisplayMenu_005f0.doStartTag();
    if (_jspx_th_menu_005fdisplayMenu_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f0);
    return false;
  }

  private boolean _jspx_meth_menu_005fdisplayMenu_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_menu_005fuseMenuDisplayer_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:displayMenu
    net.sf.navigator.taglib.DisplayMenuTag _jspx_th_menu_005fdisplayMenu_005f1 = (net.sf.navigator.taglib.DisplayMenuTag) _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.get(net.sf.navigator.taglib.DisplayMenuTag.class);
    _jspx_th_menu_005fdisplayMenu_005f1.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fdisplayMenu_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_menu_005fuseMenuDisplayer_005f0);
    _jspx_th_menu_005fdisplayMenu_005f1.setName("ToDoListMenuEdit");
    int _jspx_eval_menu_005fdisplayMenu_005f1 = _jspx_th_menu_005fdisplayMenu_005f1.doStartTag();
    if (_jspx_th_menu_005fdisplayMenu_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f1);
    return false;
  }

  private boolean _jspx_meth_menu_005fdisplayMenu_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_menu_005fuseMenuDisplayer_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  menu:displayMenu
    net.sf.navigator.taglib.DisplayMenuTag _jspx_th_menu_005fdisplayMenu_005f2 = (net.sf.navigator.taglib.DisplayMenuTag) _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.get(net.sf.navigator.taglib.DisplayMenuTag.class);
    _jspx_th_menu_005fdisplayMenu_005f2.setPageContext(_jspx_page_context);
    _jspx_th_menu_005fdisplayMenu_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_menu_005fuseMenuDisplayer_005f0);
    _jspx_th_menu_005fdisplayMenu_005f2.setName("CaseDetailMenuCase");
    int _jspx_eval_menu_005fdisplayMenu_005f2 = _jspx_th_menu_005fdisplayMenu_005f2.doStartTag();
    if (_jspx_th_menu_005fdisplayMenu_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fmenu_005fdisplayMenu_0026_005fname_005fnobody.reuse(_jspx_th_menu_005fdisplayMenu_005f2);
    return false;
  }
}

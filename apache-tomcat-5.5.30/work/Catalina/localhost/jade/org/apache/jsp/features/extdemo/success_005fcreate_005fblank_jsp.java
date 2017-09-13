package org.apache.jsp.features.extdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class success_005fcreate_005fblank_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(13);
    _jspx_dependants.add("/include/html_headers.jsp");
    _jspx_dependants.add("/include/jsp_headers.jsp");
    _jspx_dependants.add("/include/Ext_Js_Css.jsp");
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
      response.setContentType("text/html; charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("<link rel=\"stylesheet\" href=\"style/common.css\" type=\"text/css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"style/input_date.css\" type=\"text/css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"style/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"style/tag.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"style/input_date.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"style/meizz_date.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"  href=\"style/tabs.css\" /> ");
      out.write('\r');
      out.write('\n');
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
      out.write('\r');
      out.write('\n');
      out.write("<script type=\"text/javascript\" src=\"ext/adapter/ext/ext-base.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/ext-all.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/source/locale/ext-lang-zh_CN-GBK-min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/source/util/Format.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/source/util/Date.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/RowExpander.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/colorableTabPanel.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/pageSizePlugin.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/ResetPageNumToolbar.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/ModalDialogWindow.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/ext-base.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/GridPanelExtend.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/UnitGridPanelExtend.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/DataGridPanel.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/BaseInfoPanel.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/TabPanelExtend.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/BusinessCommon.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"style/cookieOperation.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/SearchSelectField.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/examples/examples.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"ext/examples/examples.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"ext/customs/global.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"ext/customs/colorableTabPanel.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"ext/resources/css/ext-all.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"ext/customs/MonthField.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var MaxPageSize = 30;\t//for pagination global variable\r\n");
      out.write("var TimeOut = 300000;  //for overtime global variable\r\n");
      out.write("(!Ext)?'':Ext.BLANK_IMAGE_URL='./ext/resources/images/default/s.gif';\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write(" * @page name  project/success_blank.jsp\r\n");
      out.write(" * @function   中转页面\r\n");
      out.write(" * @author     Feng.Yuan\r\n");
      out.write(" * @created    2013-07-17\r\n");
      out.write(" * @version    1.0\r\n");
      out.write("-->\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function init(){\t\r\n");
      out.write("\tExt.MessageBox.alert('提示信息', '<table><caption>保存成功!&nbsp;&nbsp;&nbsp;&nbsp;</caption></table>', doSuccess);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function doSuccess(){\r\n");
      out.write("\tparent.win.win.close();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"init()\">\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

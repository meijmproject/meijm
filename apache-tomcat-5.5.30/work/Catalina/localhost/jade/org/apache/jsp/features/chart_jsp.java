package org.apache.jsp.features;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jade.util.*;

public final class chart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tlds/jade.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>chart</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#FFFFFF\" text=\"#000000\">\r\n");
      out.write("<p>统计图表功能演示</p>\r\n");
      out.write("<h4>Single series chart</h4>\r\n");
      if (_jspx_meth_jade_005fchart_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_jade_005fchart_005f1(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_jade_005fchart_005f3(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f4(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_jade_005fchart_005f5(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f6(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_jade_005fchart_005f7(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      out.write("<h4>Multi series chart</h4>\r\n");
      if (_jspx_meth_jade_005fchart_005f8(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f9(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f10(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f11(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      if (_jspx_meth_jade_005fchart_005f12(_jspx_page_context))
        return;
      out.write("<br>\r\n");
      out.write("<hr>\r\n");
      out.write("源代码：<br>\r\n");
      out.write("web/features/chart.jsp <br>\r\n");
      out.write("web/features/single_series_xml.jsp <br>\r\n");
      out.write("web/features/multi_series_xml.jsp \r\n");
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

  private boolean _jspx_meth_jade_005fchart_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f0 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f0.setParent(null);
    _jspx_th_jade_005fchart_005f0.setSwf("Column2D.swf");
    _jspx_th_jade_005fchart_005f0.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f0.setWidth("400");
    _jspx_th_jade_005fchart_005f0.setHeight("300");
    int _jspx_eval_jade_005fchart_005f0 = _jspx_th_jade_005fchart_005f0.doStartTag();
    if (_jspx_th_jade_005fchart_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f0);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f1 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f1.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f1.setParent(null);
    _jspx_th_jade_005fchart_005f1.setSwf("Column3D.swf");
    _jspx_th_jade_005fchart_005f1.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f1.setWidth("400");
    _jspx_th_jade_005fchart_005f1.setHeight("300");
    int _jspx_eval_jade_005fchart_005f1 = _jspx_th_jade_005fchart_005f1.doStartTag();
    if (_jspx_th_jade_005fchart_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f1);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f2 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f2.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f2.setParent(null);
    _jspx_th_jade_005fchart_005f2.setSwf("Pie3D.swf");
    _jspx_th_jade_005fchart_005f2.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f2.setWidth("400");
    _jspx_th_jade_005fchart_005f2.setHeight("300");
    int _jspx_eval_jade_005fchart_005f2 = _jspx_th_jade_005fchart_005f2.doStartTag();
    if (_jspx_th_jade_005fchart_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f2);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f3 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f3.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f3.setParent(null);
    _jspx_th_jade_005fchart_005f3.setSwf("Pie2D.swf");
    _jspx_th_jade_005fchart_005f3.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f3.setWidth("400");
    _jspx_th_jade_005fchart_005f3.setHeight("300");
    int _jspx_eval_jade_005fchart_005f3 = _jspx_th_jade_005fchart_005f3.doStartTag();
    if (_jspx_th_jade_005fchart_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f3);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f4 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f4.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f4.setParent(null);
    _jspx_th_jade_005fchart_005f4.setSwf("Line.swf");
    _jspx_th_jade_005fchart_005f4.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f4.setWidth("400");
    _jspx_th_jade_005fchart_005f4.setHeight("300");
    int _jspx_eval_jade_005fchart_005f4 = _jspx_th_jade_005fchart_005f4.doStartTag();
    if (_jspx_th_jade_005fchart_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f4);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f5 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f5.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f5.setParent(null);
    _jspx_th_jade_005fchart_005f5.setSwf("Bar2D.swf");
    _jspx_th_jade_005fchart_005f5.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f5.setWidth("400");
    _jspx_th_jade_005fchart_005f5.setHeight("300");
    int _jspx_eval_jade_005fchart_005f5 = _jspx_th_jade_005fchart_005f5.doStartTag();
    if (_jspx_th_jade_005fchart_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f5);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f6 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f6.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f6.setParent(null);
    _jspx_th_jade_005fchart_005f6.setSwf("Doughnut2D.swf");
    _jspx_th_jade_005fchart_005f6.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f6.setWidth("400");
    _jspx_th_jade_005fchart_005f6.setHeight("300");
    int _jspx_eval_jade_005fchart_005f6 = _jspx_th_jade_005fchart_005f6.doStartTag();
    if (_jspx_th_jade_005fchart_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f6);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f7 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f7.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f7.setParent(null);
    _jspx_th_jade_005fchart_005f7.setSwf("Doughnut3D.swf");
    _jspx_th_jade_005fchart_005f7.setXml("single_series_xml.jsp");
    _jspx_th_jade_005fchart_005f7.setWidth("400");
    _jspx_th_jade_005fchart_005f7.setHeight("300");
    int _jspx_eval_jade_005fchart_005f7 = _jspx_th_jade_005fchart_005f7.doStartTag();
    if (_jspx_th_jade_005fchart_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f7);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f8 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f8.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f8.setParent(null);
    _jspx_th_jade_005fchart_005f8.setSwf("MSColumn2D.swf");
    _jspx_th_jade_005fchart_005f8.setXml("multi_series_xml.jsp");
    _jspx_th_jade_005fchart_005f8.setWidth("640");
    _jspx_th_jade_005fchart_005f8.setHeight("480");
    int _jspx_eval_jade_005fchart_005f8 = _jspx_th_jade_005fchart_005f8.doStartTag();
    if (_jspx_th_jade_005fchart_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f8);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f9 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f9.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f9.setParent(null);
    _jspx_th_jade_005fchart_005f9.setSwf("MSColumn3D.swf");
    _jspx_th_jade_005fchart_005f9.setXml("multi_series_xml.jsp");
    _jspx_th_jade_005fchart_005f9.setWidth("640");
    _jspx_th_jade_005fchart_005f9.setHeight("480");
    int _jspx_eval_jade_005fchart_005f9 = _jspx_th_jade_005fchart_005f9.doStartTag();
    if (_jspx_th_jade_005fchart_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f9);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f10 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f10.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f10.setParent(null);
    _jspx_th_jade_005fchart_005f10.setSwf("MSLine.swf");
    _jspx_th_jade_005fchart_005f10.setXml("multi_series_xml.jsp");
    _jspx_th_jade_005fchart_005f10.setWidth("640");
    _jspx_th_jade_005fchart_005f10.setHeight("480");
    int _jspx_eval_jade_005fchart_005f10 = _jspx_th_jade_005fchart_005f10.doStartTag();
    if (_jspx_th_jade_005fchart_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f10);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f11 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f11.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f11.setParent(null);
    _jspx_th_jade_005fchart_005f11.setSwf("MSArea.swf");
    _jspx_th_jade_005fchart_005f11.setXml("multi_series_xml.jsp");
    _jspx_th_jade_005fchart_005f11.setWidth("640");
    _jspx_th_jade_005fchart_005f11.setHeight("480");
    int _jspx_eval_jade_005fchart_005f11 = _jspx_th_jade_005fchart_005f11.doStartTag();
    if (_jspx_th_jade_005fchart_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f11);
    return false;
  }

  private boolean _jspx_meth_jade_005fchart_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:chart
    jade.core.taglib.ChartTag _jspx_th_jade_005fchart_005f12 = (jade.core.taglib.ChartTag) _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.get(jade.core.taglib.ChartTag.class);
    _jspx_th_jade_005fchart_005f12.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fchart_005f12.setParent(null);
    _jspx_th_jade_005fchart_005f12.setSwf("MSBar2D.swf");
    _jspx_th_jade_005fchart_005f12.setXml("multi_series_xml.jsp");
    _jspx_th_jade_005fchart_005f12.setWidth("640");
    _jspx_th_jade_005fchart_005f12.setHeight("480");
    int _jspx_eval_jade_005fchart_005f12 = _jspx_th_jade_005fchart_005f12.doStartTag();
    if (_jspx_th_jade_005fchart_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fchart_0026_005fxml_005fwidth_005fswf_005fheight_005fnobody.reuse(_jspx_th_jade_005fchart_005f12);
    return false;
  }
}

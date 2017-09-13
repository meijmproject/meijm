package org.apache.jsp.features;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import jade.util.fusionchart.*;

public final class single_005fseries_005fxml_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/xml; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


LinkedHashMap<String,String> data=new LinkedHashMap<String,String>();
data.put("IBM","12.3");
data.put("Oracle","17.8");
data.put("Sybase","9");
data.put("Jadeforturn","68.32");
data.put("Microsoft","54.98");

Chart chart=ChartFactory.createSingleSeriesChart(data);
chart.setCaption("Software companies revenue chart");
chart.setXAxisName("Company");
chart.setYAxisName("Revenue($)");

TrendLine tl=chart.createTrendLine();
tl.setStartValue("10");
tl.setEndValue("40");
tl.setColor("009933");
tl.setDisplayValue("Avg");

chart.outputXml(response.getOutputStream());

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

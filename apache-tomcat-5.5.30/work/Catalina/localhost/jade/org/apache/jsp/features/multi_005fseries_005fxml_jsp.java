package org.apache.jsp.features;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import jade.util.fusionchart.*;

public final class multi_005fseries_005fxml_jsp extends org.apache.jasper.runtime.HttpJspBase
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


LinkedHashMap<String,String[]> data=new LinkedHashMap<String,String[]>();
String[] seriesNames=new String[]{"2005","2006","2007"};

data.put("IBM",new String[]{"12.3","28.58","29.23"});
data.put("Oracle",new String[]{"57.23","39.69","59.39"});
data.put("Sybase",new String[]{"48.29","45.23","20.4"});
data.put("Jadeforturn",new String[]{"80.2","67.23","402.13"});
data.put("Microsoft",new String[]{"38.46","47.89","28.98"});

Chart chart=ChartFactory.createMultiSeriesChart(seriesNames,data);
chart.setCaption("Software companies year revenue chart");
chart.setXAxisName("Company");
chart.setYAxisName("Revenue($)");

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

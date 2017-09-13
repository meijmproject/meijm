package org.apache.jsp.features;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import jade.util.*;

public final class crypto_jsp extends org.apache.jasper.runtime.HttpJspBase
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

String str="1234567890";
byte[] key="encrypt_key".getBytes();

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>CryptoUtil</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#FFFFFF\" text=\"#000000\">\r\n");
      out.write("<p></p>\r\n");
      out.write("<p>平台提供了标准的数据压缩方法，封装在CryptoUtil中，可以通过静态方法直接调用</p>\r\n");
      out.write("<p>比如：原始数据是：1234567890</p>\r\n");
      out.write("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"5\">\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td width=\"20%\">方法</td>\r\n");
      out.write("    <td width=\"80%\">密文hex_encode后的字符串</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>md5</td>\r\n");
      out.write("    <td>");
      out.print(CryptoUtil.md5(str));
      out.write("</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>rc6</td>\r\n");
      out.write("    <td>");
      out.print(CryptoUtil.hex_encode(CryptoUtil.rc6(true,str.getBytes(),key)));
      out.write("</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>blowfish</td>\r\n");
      out.write("    <td>");
      out.print(CryptoUtil.hex_encode(CryptoUtil.blowfish(true,str.getBytes(),key)));
      out.write("</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<hr>\r\n");
      out.write("<p>源代码：web/features/crypto.jsp </p>\r\n");
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

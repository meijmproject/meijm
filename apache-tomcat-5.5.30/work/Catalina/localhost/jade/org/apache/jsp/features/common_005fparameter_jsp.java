package org.apache.jsp.features;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jade.starter.util.*;
import java.util.*;
import org.apache.struts.util.LabelValueBean;

public final class common_005fparameter_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/tlds/jade.tld");
    _jspx_dependants.add("/WEB-INF/tlds/struts-html.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Common Parameters</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<p>平台提供了统一的基础参数维护功能(菜单位置：系统管理\\参数维护)，有效避免了代码中的硬编码</p>\r\n");
      out.write("<p><b>参数表释义如下：</b></p>\r\n");
      out.write("<table width=\"500\" border=\"1\" cellspacing=\"0\" cellpadding=\"5\">\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td colspan=\"2\">参数表说明：</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td width=\"30%\">name</td>\r\n");
      out.write("    <td width=\"70%\">description</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>参数类别</td>\r\n");
      out.write("    <td>指参数的属性，如用户类型、用户状态等</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>参数值</td>\r\n");
      out.write("    <td>对于某一类参数，可能存在多个属性值，如 1、2、3；或a、b、c、d等</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>参数中文名称</td>\r\n");
      out.write("    <td>指参数对应值的中文释义，用于国际化显示</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>参数英文名称</td>\r\n");
      out.write("    <td>指参数对应值的英文释义，用于国际化显示</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td>序号</td>\r\n");
      out.write("    <td>用于指定某一类参数各值在下拉选项中的位置，用于排序</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<p><b>构造下拉选项：</b></p>\r\n");
      out.write("平台提供了统一的静态方法用于读取指定参数并返回下拉标准对象列表，即 LabelValueBean List，并使用了缓存和有效期管理机制；<br>\r\n");
      out.write("<b>参数有效期设置：</b> conf\\common-config.properties&nbsp;&nbsp;[parameter.active.period=60000](ms)<br>\r\n");
      out.write("<b>类名：</b>jade.starter.util.ParameterHelper.java<br>\r\n");
      out.write("<b>方法名：</b>getParameterOptions(Locale locale,String type,boolean isEmptyOptionRequired)  其中<br>\r\n");
      out.write("locale - 当前会话指定的 Locale类；<br>\r\n");
      out.write("type - 参数类别，一般定义在 jade.starter.util.Constant.java 类中；<br>\r\n");
      out.write("isEmptyOptionRequired - 是否需要增加空选项；<br>\r\n");

	List<LabelValueBean> vb = (List<LabelValueBean>)ParameterHelper.getParameterOptions(request.getLocale(),"UserType",true);
	request.setAttribute("UserTypes",vb);
	
	request.setAttribute("one","1");	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<p><b>显示页面中参数值到参数名称的转换：</b></p>\r\n");
      out.write("1、Action 中准备数据并放到request 中;<br>\r\n");
      out.write("2、通过标签（ParameterTag）显示参数名称。<br><br>\r\n");
      out.write("<i>详细实现请参考系统用户维护</i><br><br>\r\n");
      out.write("\r\n");
      out.write("以用户类型为例，代码如下：&lt;jade:paramWrite paramType=\"UserType\" paramValue=\"1\" collection=\"UserTypes\"/&gt;<br>\r\n");
      out.write("运行效果如下：");
      if (_jspx_meth_jade_005fparamWrite_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>");
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

  private boolean _jspx_meth_jade_005fparamWrite_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jade:paramWrite
    jade.core.taglib.ParameterTag _jspx_th_jade_005fparamWrite_005f0 = (jade.core.taglib.ParameterTag) _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody.get(jade.core.taglib.ParameterTag.class);
    _jspx_th_jade_005fparamWrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jade_005fparamWrite_005f0.setParent(null);
    _jspx_th_jade_005fparamWrite_005f0.setParamType("UserType");
    _jspx_th_jade_005fparamWrite_005f0.setParamValue("1");
    _jspx_th_jade_005fparamWrite_005f0.setCollection("UserTypes");
    int _jspx_eval_jade_005fparamWrite_005f0 = _jspx_th_jade_005fparamWrite_005f0.doStartTag();
    if (_jspx_th_jade_005fparamWrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody.reuse(_jspx_th_jade_005fparamWrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjade_005fparamWrite_0026_005fparamValue_005fparamType_005fcollection_005fnobody.reuse(_jspx_th_jade_005fparamWrite_005f0);
    return false;
  }
}

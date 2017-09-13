package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.constant.Constant;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(13);
    _jspx_dependants.add("/include/jsp_headers.jsp");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-html-el.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-bean-el.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-logic-el.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-tiles.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/struts-nested.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/c.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/fmt.tld");
    _jspx_dependants.add("/WEB-INF/core/tlds/fn.tld");
    _jspx_dependants.add("/WEB-INF/component/dictionary/tlds/dictionary.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fmessagesPresent;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005flogic_005fmessagesPresent = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005flogic_005fmessagesPresent.release();
    _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.release();
    _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody.release();
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

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","Public");
response.setHeader("Expires","0");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"cn\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>医院人事管理系统</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"hspszhphtml/css/login.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"hspszhphtml/css/common/reset.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"hspszhphtml/css/common/base.css\">\r\n");
      out.write("    <script type=\"application/x-javascript\"> \r\n");
      out.write("    \taddEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }\r\n");
      out.write("    \tif (parent.opener != null)  {\r\n");
      out.write("            parent.opener.location =\"logout.do?method=logout\";\r\n");
      out.write("            parent.close();\r\n");
      out.write("        }\r\n");
      out.write("    \tif(parent != self) {\r\n");
      out.write("    \t\ttop.location.href=self.location.href;\r\n");
      out.write("    \t}\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"main-body\">\r\n");
      out.write("    <div class=\"lg-title\">\r\n");
      out.write("        <img src=\"hspszhphtml/images/copyright/logo_login.png\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"lg-banner\">\r\n");
      out.write("        <div class=\"lg-container\">\r\n");
      out.write("            <div class=\"lg-ad-img\"></div>\r\n");
      out.write("            <div class=\"lg-form-cont\">\r\n");
      out.write("              <h2 class=\"lg-form-title\">中国领先的医疗行业人事管理软件服务供应商</h2>\r\n");
      out.write("\t            <form class=\"lg-form\" action=\"login.do?method=login\" method=\"post\">\r\n");
      out.write("\t                <h3 class=\"lg-form-title\">用户登录</h3>\r\n");
      out.write("\t                <div class=\"lg-form-item\">\r\n");
      out.write("\t                    <input class=\"lg-account lg-error-border\" type=\"text\" name=\"userCode\" placeholder=\"请输入用户名\" value=\"\" onfocus=\"this.value = '';\" onblur=\"if (this.value == '') {this.value = '';}\">\r\n");
      out.write("\t                    <span class=\"lg-ac-icon lg-icon\"></span>\r\n");
      out.write("\t                    <span class=\"lg-error-icon\"></span>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t                <div class=\"lg-error\">\r\n");
      out.write("\t\t                    ");
      //  logic:messagesPresent
      org.apache.struts.taglib.logic.MessagesPresentTag _jspx_th_logic_005fmessagesPresent_005f0 = (org.apache.struts.taglib.logic.MessagesPresentTag) _005fjspx_005ftagPool_005flogic_005fmessagesPresent.get(org.apache.struts.taglib.logic.MessagesPresentTag.class);
      _jspx_th_logic_005fmessagesPresent_005f0.setPageContext(_jspx_page_context);
      _jspx_th_logic_005fmessagesPresent_005f0.setParent(null);
      int _jspx_eval_logic_005fmessagesPresent_005f0 = _jspx_th_logic_005fmessagesPresent_005f0.doStartTag();
      if (_jspx_eval_logic_005fmessagesPresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<ul>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t");
          //  html:messages
          org.apache.struts.taglib.html.MessagesTag _jspx_th_html_005fmessages_005f0 = (org.apache.struts.taglib.html.MessagesTag) _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.get(org.apache.struts.taglib.html.MessagesTag.class);
          _jspx_th_html_005fmessages_005f0.setPageContext(_jspx_page_context);
          _jspx_th_html_005fmessages_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fmessagesPresent_005f0);
          _jspx_th_html_005fmessages_005f0.setId("error");
          int _jspx_eval_html_005fmessages_005f0 = _jspx_th_html_005fmessages_005f0.doStartTag();
          if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.String error = null;
            if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_html_005fmessages_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_html_005fmessages_005f0.doInitBody();
            }
            error = (java.lang.String) _jspx_page_context.findAttribute("error");
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t<li>");
              if (_jspx_meth_bean_005fwrite_005f0(_jspx_th_html_005fmessages_005f0, _jspx_page_context))
                return;
              out.write("</li>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t");
              int evalDoAfterBody = _jspx_th_html_005fmessages_005f0.doAfterBody();
              error = (java.lang.String) _jspx_page_context.findAttribute("error");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_html_005fmessages_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.reuse(_jspx_th_html_005fmessages_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.reuse(_jspx_th_html_005fmessages_005f0);
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t</ul>\r\n");
          out.write("\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_logic_005fmessagesPresent_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_logic_005fmessagesPresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005flogic_005fmessagesPresent.reuse(_jspx_th_logic_005fmessagesPresent_005f0);
        return;
      }
      _005fjspx_005ftagPool_005flogic_005fmessagesPresent.reuse(_jspx_th_logic_005fmessagesPresent_005f0);
      out.write("\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t                <div class=\"lg-form-item\">\r\n");
      out.write("\t                    <input class=\"lg-account\" type=\"password\" name=\"password\" placeholder=\"请输入密码\">\r\n");
      out.write("\t                    <span class=\"lg-pw-icon lg-icon\"></span>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t                <a class=\"lg-forget-pw\"></a><!-- 忘记密码 -->\r\n");
      out.write("\t                <button class=\"lg-submit\" type=\"submit\">登 录</button>\r\n");
      out.write("\t            </form>\r\n");
      out.write("\t          </div>\r\n");
      out.write("            <!-- <div class=\"cl\"></div> -->\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"lg-footer\">深圳市嘉德永丰开发科技股份有限公司 版权所有</div>\r\n");
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

  private boolean _jspx_meth_bean_005fwrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fmessages_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f0 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fmessages_005f0);
    _jspx_th_bean_005fwrite_005f0.setName("error");
    int _jspx_eval_bean_005fwrite_005f0 = _jspx_th_bean_005fwrite_005f0.doStartTag();
    if (_jspx_th_bean_005fwrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_0026_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
    return false;
  }
}

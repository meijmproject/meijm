package com.yh.platform.core.web.yhstrutsservlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.digester.Digester;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BaseServlet extends ActionServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5712220202683131174L;
/**
 * 初始化
 */
	protected ModuleConfig initModuleConfig(String prefix, String paths)
			throws ServletException {
		
		// Parse the configuration for this module
		ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
		ModuleConfig config = factoryObject.createModuleConfig(prefix);

		// Configure the Digester instance we will use
		Digester digester = initConfigDigester();
		//解析路径
		String[] source = StringUtils.tokenizeToStringArray(paths, ",; \t\n");
		
		ResourcePatternResolver resourcePatternResolver = new ServletContextResourcePatternResolver(super.getServletContext());
		for(int i= 0;i<source.length;i++){
			try {
				//分析文件
				Resource[] rl = resourcePatternResolver.getResources(source[i]);
				for(Resource r : rl){
					digester.push(config);
					parseModuleConfigFile(digester, r.getInputStream(),r.getFilename());
					log.info(r.getDescription());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getServletContext().setAttribute(
				Globals.MODULE_KEY + config.getPrefix(), config);
		
	   FormBeanConfig[] fbs = config.findFormBeanConfigs();
	    for (int i = 0; i < fbs.length; ++i) {
	      if (fbs[i].getDynamic()) {
	        fbs[i].getDynaActionFormClass();
	      }
	    }
		return config;
	}
	/**
	 * 注入
	 * @param digester
	 * @param is
	 * @param fileName
	 * @throws UnavailableException
	 */
	  protected void parseModuleConfigFile(Digester digester, InputStream is,String fileName)
	    throws UnavailableException
	  {
	    try {
			 digester.parse(new InputSource(is));
			} catch (IOException e) {
				  handleConfigException(fileName, e);
			} catch (SAXException e) {
				 handleConfigException(fileName, e);
		    } finally {
		      if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
	  }
	  /**
	   * 异常处理
	   * @param fileName
	   * @param e
	   * @throws UnavailableException
	   */
	  private void handleConfigException(String fileName, Exception e)
	    throws UnavailableException
	  {
	    String msg = this.internal.getMessage("configParse",fileName);
	    log.error(msg, e);
	    throw new UnavailableException(msg);
	  }
}

package com.yh.component.i18n;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 国际化转换工具 v2.0
 * @author	zhangqp
 * @version	1.0,	16/08/17
 */
@SuppressWarnings(value = { "unchecked" })
public class I18NGen2  extends Task {
	private String				xml;
	private Collection<File>	files;
	private String				dest;
	private String				suffix = "i18n.xml";
	private String				lang;
	private List<String>		langs;
	
	public void execute() throws BuildException {
		try {
			version();

			checkParameter();
			extractFiles();

			if(files != null && !files.isEmpty()) {
				
				Map<String, LinkedHashMap<String, String>> resources = new HashMap<String, LinkedHashMap<String, String>>();
				
				for (File path : files) {
					log("parsing path: " + path);
					parse(path, resources);
				}
				
				outputFile(resources);
			}
		} catch (Exception e) {
			 throw new BuildException(e);
		}
	}
	
	private void parse(File file,Map<String, LinkedHashMap<String, String>> resources) throws Exception {
		SAXReader reader = new SAXReader();

		Document doc = reader.read(file);

		Element root = doc.getRootElement();

		List<Element> entries = root.elements("entry");
		
		if (!entries.isEmpty()) {
			//local - key - mesasge
			
			for (Element e : entries) {
				String key = e.attributeValue("key");	//key
				
				List<Element> localeElements = e.elements();
				for (Element le : localeElements) {
					
					String local = le.getName();	// local
					
					if (isAllowed(langs, local)) {
						String text = le.getText();		// message
						if (text != null && text.trim().length() > 0) {
							LinkedHashMap<String, String> message = resources.get(local);
							if(message == null) {
								message = new LinkedHashMap<String, String>();
								resources.put(local, message);
							}
							message.put(key, text);
						}
					}
				}
			}
		}
	}
	
	public boolean isAllowed(List<String> langs, String local) {
		return langs == null || langs .isEmpty() || langs.indexOf(local) != -1;
	}

	private void extractFiles() {

		files = FileUtils.listFiles(new File(xml), new String[] { suffix }, true);
	}

	private void outputFile(Map<String, LinkedHashMap<String, String>> resources) throws Exception {
		for (Map.Entry<String,LinkedHashMap<String,String>> entry : resources.entrySet()) {
			writeLocal(entry.getKey(), entry.getValue());
		}
	}
	
	private void writeLocal(String local, LinkedHashMap<String, String> messages) throws Exception {
		File file = new File(dest, "resources"+"_"+local+".properties");
		
		FileWriter out = new FileWriter(file);
		
		for (Map.Entry<String, String> entry : messages.entrySet()) {
			out.write(entry.getKey() + "=");
			char[] values = entry.getValue().toCharArray();
			for (char c : values) {
				outputChar(c, out);
			}
			out.write("\r\n");
		}
		out.close();
		
		log("generate file: " + file.getAbsolutePath());
	}

	private void outputChar(char c, Writer out) throws Exception {
		if (c > '\177') {
			out.write('\\');
			out.write('u');
			String s1 = Integer.toHexString(c);
			StringBuffer stringbuffer = new StringBuffer(s1);
			stringbuffer.reverse();
			int l = 4 - stringbuffer.length();
			for (int i1 = 0; i1 < l; i1++)
				stringbuffer.append('0');

			for (int j1 = 0; j1 < 4; j1++)
				out.write(stringbuffer.charAt(3 - j1));

		} else {
			out.write(c);
		}
	}

	private void version() {
		log("*********** welcome to use the i18n generation tool version 2.0 ***********");
	}

	private void checkParameter() throws BuildException {
		if (xml == null) {
			throw new BuildException("Attribute 'xml' is missing.");
		}

		if (dest == null) {
			throw new BuildException("Attribute 'dest' is missing.");
		}

	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
		this.langs = (lang == null || lang.length() == 0) ?  null : Arrays.asList(lang.replaceAll(" ", "").replaceAll("\\t", "").replaceAll("\\n", "").split(","));
	}
}

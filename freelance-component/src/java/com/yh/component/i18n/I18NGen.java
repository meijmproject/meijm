package com.yh.component.i18n;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * convert i18n xml files to i18n properties file
 * 
 * @author eric
 */
@SuppressWarnings(value = { "unchecked" })
public class I18NGen extends Task
{
    private String xml;
    private String dest;

    public void execute() throws BuildException {
        version();

        checkParameter();
        ArrayList<String> locales = new ArrayList<String>();
        LinkedHashMap<String, HashMap<String, String>> map = new LinkedHashMap<String, HashMap<String, String>>();

        log("Parsing '" + xml + "'...");
        try {
            SAXBuilder builder = new SAXBuilder();
            Document xmldoc = builder.build(new File(xml));

            Element resources_e = xmldoc.getRootElement();
            String locales_str = resources_e.getAttributeValue("locales");
            StringTokenizer stk = new StringTokenizer(locales_str, ",");
            while (stk.hasMoreTokens()) {
                locales.add(stk.nextToken());
            }

            log("Defined locales: " + locales);
            Iterator<Element> iter = resources_e.getChildren("entry")
                    .iterator();
            while (iter.hasNext()) {
                Element entry_e = iter.next();

                String key = entry_e.getAttributeValue("key");
                HashMap<String, String> localeStrs = new HashMap<String, String>();

                // get string for all locales
                for (String locale : locales) {
                    String str = entry_e.getChildTextTrim(locale);
                    if (str == null) {
                        log("WARNING: '" + locale + "' string for key '" + key
                                + "' not exists");
                        localeStrs.put(locale, key);
                    } else {
                        localeStrs.put(locale, str);
                    }
                }

                map.put(key, localeStrs);
            }

            //write properties files
            String base_fn=removeFileExtension(new File(xml).getName());
            for(String locale:locales) {
                String fn;
//                if(locale.equals(defaultLocale)) {
//                    fn=base_fn+".properties";
//                } else {
                    fn=base_fn+"_"+locale+".properties";
//                }
                
                log("Output properties file '"+new File(dest,fn)+"'");
                outputFile(new File(dest,fn),map,locale);
            }
            
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

    private void outputFile(File file,
            LinkedHashMap<String, HashMap<String, String>> map, String locale)
            throws Exception {
        FileWriter out = new FileWriter(file);

        for(String key:map.keySet()) {
            out.write(key+"=");
            
            char[] values=map.get(key).get(locale).toCharArray();
            for(char c:values) {
                outputChar(c,out);
            }
            
            out.write("\r\n");
        }
        
        out.close();
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

    private static String removeFileExtension(String fn) {
       int i = fn.lastIndexOf('.');
       if (i >= 0 && i < fn.length() - 1) {
           return fn.substring(0,i);
       }
       return fn;
    }

    private void version() {
        log(" i18n generation tool version 0.1 :)");
    }

    private void checkParameter() throws BuildException {
        if (xml == null) {
            throw new BuildException("Attribute 'xml' is missing.");
        }

        if (dest == null) {
            throw new BuildException("Attribute 'dest' is missing.");
        }

        if (!new File(xml).exists()) {
            throw new BuildException("File '" + xml
                    + "' specified by 'xml' is not exist.");
        }

        if (!new File(dest).exists()) {
            throw new BuildException("Folder '" + dest
                    + "' specified by 'dest' is not exist.");
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

}

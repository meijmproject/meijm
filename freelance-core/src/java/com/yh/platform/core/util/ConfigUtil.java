/*
 * @(#) PropertyResourceUtil.java        1.00         2006-7-26
 * 
 * Copyright (c) 2006 JADE EXPRESS Corporation. All Rights Reserved.
 *
 *
 */
package com.yh.platform.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author chenkebing
 * @version 1.0
 * @description
 * @created 2006-7-26
 */

public class ConfigUtil {
    @SuppressWarnings("rawtypes")
	private static HashMap messages = new HashMap();
    private static Properties props = new Properties();

    /**
     * @param args
     */
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream is = null;
        is = classLoader.getResourceAsStream("common-config.properties");
        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static String getProperty(String key) {
        String msg = (String) messages.get(key);
        if (msg == null) {
            String pros = props.getProperty(key);
            if (pros != null) {
                messages.put(key, pros);
            }

            msg = pros;
        }

        return msg;
    }


    /**
     * is security check required
     *
     * @return
     */
    public static boolean isSecurityCheckRequired() {
        return "Y".equals(ConfigUtil.getProperty("security.check.enabled"));
    }

//    public static boolean isPswdCheckRequired() {
//        return "Y".equals(ConfigUtil.getProperty("password.check.enabled"));
//    }

    public static String getHomePath() {
        return ConfigUtil.getProperty("home.path");
    }
}

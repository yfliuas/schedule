package com.common.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by TR on 2015/11/18.
 */
public class PropertiesReaderUtils {

    public static String getPropertiesAttr(String fileName, String attr) {
        try {
            Configuration config = new PropertiesConfiguration(fileName);
            return config.getString(attr);
        } catch (ConfigurationException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getPropertiesAttr("test.properties","port"));
    }

}

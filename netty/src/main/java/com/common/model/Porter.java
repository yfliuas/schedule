package com.common.model;

import com.common.utils.PropertiesReaderUtils;
import com.common.utils.ScanPortUtils;

/**
 * 用于服务器拿到端口号的对象
 * Created by TR on 2015/11/18.
 */
public class Porter {

    private static String DEFAULTATTR = "port";

    private int port;

    /**
     * 会自动扫描
     */
    public Porter() {
        port = ScanPortUtils.getAvailablePort();
    }

    /**
     * 自行配置
     */
    public Porter(int port) {
        this.port = port;
    }

    /**
     * 从配置文件中读取，默认配置时port
     */
    public Porter(String propertiesFileName) {
        String portStr = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, DEFAULTATTR);
        if(portStr != null) {
            port = Integer.parseInt(portStr);
        }
    }

    /**
     * 从配置文件中读取
     */
    public Porter(String propertiesFileName, String attr) {
        String portStr = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, attr);
        if(portStr != null) {
            port = Integer.parseInt(portStr);
        }
    }

    public int getPort() throws Exception {
        if(port == 0) {
            throw new Exception("port error");
        }
        return port;
    }

    // test
    public static void main(String[] args) {
        try {
            System.out.println(new Porter("test.properties","PORT").getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

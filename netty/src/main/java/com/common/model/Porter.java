package com.common.model;

import com.common.utils.PropertiesReaderUtils;
import com.common.utils.ScanPortUtils;

/**
 * ���ڷ������õ��˿ںŵĶ���
 * Created by TR on 2015/11/18.
 */
public class Porter {

    private static String DEFAULTATTR = "port";

    private int port;

    /**
     * ���Զ�ɨ��
     */
    public Porter() {
        port = ScanPortUtils.getAvailablePort();
    }

    /**
     * ��������
     */
    public Porter(int port) {
        this.port = port;
    }

    /**
     * �������ļ��ж�ȡ��Ĭ������ʱport
     */
    public Porter(String propertiesFileName) {
        String portStr = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, DEFAULTATTR);
        if(portStr != null) {
            port = Integer.parseInt(portStr);
        }
    }

    /**
     * �������ļ��ж�ȡ
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

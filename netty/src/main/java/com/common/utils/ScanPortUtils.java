package com.common.utils;

import java.io.IOException;
import java.net.Socket;

/**
 * 扫描端口，找到一个可用的端口
 * 端口范围在1024以上
 * Created by TR on 2015/11/18.
 */
public class ScanPortUtils {

    public static String HOST = "127.0.0.1";
    public static int MINPORT = 1025;
    public static int MAXPORT = 65535;

    public static int getAvailablePort() {
        for(int i = MINPORT;i < MAXPORT;i ++) {
            if(isFree(i)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isFree(int port) {
        Socket socket = null;
        try {
            socket = new Socket(HOST, port);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ScanPortUtils.getAvailablePort());
    }
}

package com.common.utils;

import io.netty.buffer.ByteBuf;

/**
 * Created by TR on 2015/11/19.
 */
public class ByteBuff2JSON {

    public static String transform(ByteBuf byteBuf) {
        int len = byteBuf.readableBytes();
        byte[] data = new byte[len];
        for(int i=0;i<len;i++) {
            data[i] = byteBuf.getByte(i);
        }
        return new String(data);
    }

}

package com.common.utils;

import io.netty.buffer.ByteBuf;

/**
 * Created by TR on 2015/11/18.
 */
public class ByteBuff2ByteUtils {

    public static byte[] transform(ByteBuf byteBuf) {
        return byteBuf.array();
    }


    public static void main(String[] args) {

    }
}

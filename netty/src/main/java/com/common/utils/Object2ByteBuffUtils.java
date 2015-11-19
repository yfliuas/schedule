package com.common.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 把一个对象转成一个ByteBuff
 * Created by TR on 2015/11/18.
 */
public class Object2ByteBuffUtils {

    public static ByteBuf transform(Object object) {
        byte[] bytes = Object2ByteUtils.transform(object);
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = transform("sds");
        byte[] bytes = ByteBuff2ByteUtils.transform(byteBuf);
        Object object = Byte2ObjectUtils.transform(bytes);
        System.out.println(object);
    }

}

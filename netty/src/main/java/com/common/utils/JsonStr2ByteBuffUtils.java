package com.common.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 把一个json转成一个ByteBuff
 * Created by TR on 2015/11/18.
 */
public class JsonStr2ByteBuffUtils {

    public static ByteBuf transform(String json) {
        byte[] bytes = json.getBytes();
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


    public static void main(String[] args) {
        ByteBuf byteBuf = JsonStr2ByteBuffUtils.transform("jhsjdbsjhsbj");
        System.out.println(byteBuf.readableBytes());
        System.out.println("jhsjdbsjhsbj".getBytes().length);
        String json = ByteBuff2JSON.transform(byteBuf);
        System.out.println(json);
    }

}

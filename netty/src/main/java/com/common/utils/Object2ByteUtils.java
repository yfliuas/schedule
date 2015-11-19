package com.common.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TR on 2015/11/18.
 */
public class Object2ByteUtils {

    /**
     * 把对象转成字节数组
     * @param object
     * @return
     */
    public static byte[] transform(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        byte[] bytes = Object2ByteUtils.transform(list);
        System.out.println(Byte2ObjectUtils.transform(bytes));
    }

}

package com.common.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by TR on 2015/11/18.
 */
public class Byte2ObjectUtils {

    public static Object transform(byte[] bytes) {
        Object object = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}

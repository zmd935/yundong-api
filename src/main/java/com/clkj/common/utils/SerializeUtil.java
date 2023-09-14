package com.clkj.common.utils;

import java.io.*;

/**
 * 序列化工具
 *
 * @author Created by jojo on 2019/11/26
 */
public class SerializeUtil {

    /**
     * 序列化
     *
     * @param object 对象
     * @return
     */
    public static byte[] serialize(Object object) {
        try {
            // 序列化
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes 字节
     * @return
     */
    public static <T> T unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            // 反序列化
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

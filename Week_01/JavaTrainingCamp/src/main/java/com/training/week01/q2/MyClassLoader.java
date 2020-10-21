package com.training.week01.q2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class<?> clazz = new MyClassLoader().findClass("Hello", "src/main/java/com/training/week01/q2/Hello.xlass"); // 加载并初始化Hello类
            Object hello = clazz.newInstance();

            // 调用hello方法
            Method helloMethod = clazz.getMethod("hello");
            helloMethod.invoke(hello);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载class
     * 
     * @param name 类名
     * @param path class文件地址
     * @return
     * @throws ClassNotFoundException
     */
    private Class<?> findClass(String name, String path) throws ClassNotFoundException {
        byte[] bytes = readClass(path);
        classDecrypt(bytes); // 解密，可能存在多种解密算法时改为调接口
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 读取class
     * 
     * @param path
     * @return
     */
    private byte[] readClass(String path) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path))) {
            int len = bis.available();
            byte[] bytes = new byte[len];
            bis.read(bytes, 0, len);
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[] {};
    }

    /**
     * 解密class文件，255-x
     * 
     * @param bytes
     */
    private void classDecrypt(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }
}

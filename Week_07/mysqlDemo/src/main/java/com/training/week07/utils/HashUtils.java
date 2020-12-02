package com.training.week07.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希散列
 * 
 *
 */
public class HashUtils {
    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算数据的md5
     * 
     * @param mess
     * @return 参数无效时为null
     */
    public static String getMd5(String data) {
        if (null == data) {
            return null;
        }
        if (null == md) {
            return "";
        }

        byte[] bs;
        try {
            bs = data.getBytes("utf-8");
            byte[] array = md.digest(bs);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取加盐密码
     * 
     * @param password
     * @param sale
     * @return
     */
    public static String getPasswordBySale(String password, String sale) {
        return getMd5(password + "{" + sale + "}");
    }
}

package com.example.typehandler;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptUtil {

    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}

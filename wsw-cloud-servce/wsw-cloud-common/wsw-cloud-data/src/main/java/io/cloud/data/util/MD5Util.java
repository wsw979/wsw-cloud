package io.cloud.data.util;

import io.cloud.data.constant.SecurityConstant;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: wsw-cloud-servce
 * @description: MD5加密
 * @author: wsw
 * @create: 2020-06-28 15:34
 **/
public class MD5Util {


    /**
     * 需要盐值
     *
     * @param password
     * @return
     */
    public static String encodeSalt(String password) {
        String encodePassword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(SecurityConstant.MD5);
            BASE64Encoder base64en = new BASE64Encoder();
            encodePassword = base64en.encode(md5.digest(password.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e2) {

        }
        return encodePassword;
    }

    /**
     * 不需要盐值
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(SecurityConstant.MD5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static void main(String[] args) {

        String salt = "HyopmV";
        String username = "1271278964698173442";
        String password = "wusiwei..";
        String passwordSalt = salt + MD5Util.encode(StringUtil.buffer(username, password));
        String md5PasswordSalt = MD5Util.encodeSalt(passwordSalt);
        System.out.println(md5PasswordSalt);

    }
}

package org.zhump.familybill.util;

import java.security.MessageDigest;

/**
 *@Title Md5Util
 *@Description: 加密工具类
 *
 *@author zhump
 *@date 2021/4/10 20:57
 */
public class Md5Util {
    /**
     * 返回 hexstr_lowercase(md5(明文))
     *
     * @param str 明文
     * @return
     */
    public static String getMd5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * TODO 描述一下这个方法是干嘛用的
     *
     * @param str
     * @author zhump
     * @return java.lang.String
     * @date 2021/4/10 20:57
     * @throws
     */
    public static String getMd5Str(byte[] str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    
    public static void main(String[] args) {
    	 String pwdStr = "13530103112" + "00000000" + "123456" + "0330100300";
    	 System.out.println(pwdStr);
         pwdStr = Md5Util.getMd5Str(pwdStr);
         System.out.println(pwdStr);
	}
}

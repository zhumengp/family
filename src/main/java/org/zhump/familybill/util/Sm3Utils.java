package org.zhump.familybill.util;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * SM3对称加密
 */
public class Sm3Utils {

    /**
     * 默认字符串编码
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;


    /**
     * 加密
     * @param password 明文密码
     * @return
     */
    public static String encode(String password) {
        return encode(password,DEFAULT_CHARSET);
    }

    /**
     * 加密
     * @param password 明文密码
     * @param charset 字符串编码
     * @return
     */
    public static String encode(String password, Charset charset) {
        byte[] digestSize = digestSize(password, charset);
        return ByteUtils.toHexString(digestSize);
    }

    /**
     *
     * @param password 明文密码
     * @param charset 字符串编码
     * @return
     */
    public static byte[]  digestSize(String password, Charset charset){
        byte[] bytes = password.getBytes();
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(bytes,0,bytes.length);
        byte[] hash = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(hash,0);
        return hash;

    }

    public static void main(String[] args) {
        String str = "e9539bf4-dfc9-4a48-8812-2007fcd4f629" + new BigDecimal("9783") + new BigDecimal("0") + "12b38493-2bb1-466f-9638-699185b7628f";
        String encode = Sm3Utils.encode(str);
        System.out.println(encode);
    }

}

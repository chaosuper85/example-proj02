package com.example.common.utils;


import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuchao
 * @date 2022/2/5 11:53 下午
 */
@Slf4j
public class AesUtil {
    public static String encrypt(String sSrc, String sKey) throws Exception {
        // 判断Key是否正确
        if (sKey == null || sKey.length() != 16) {
            return "";
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return Base64.getEncoder().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    public static String decrypt(String sSrc, String sKey){
        try {
            // 判断Key是否正确
            if (sKey == null || sKey.length() != 16) {
                return "";
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception ex) {
            log.error("decrypt error", ex);
            return "";
        }
    }

    public static void main(String[] args) throws Exception {
        String cKey = "@2#456789035!@#2";
        // 加密
        String cSrc = "orderNo=NSTEST12458&paidTp=35&payBank=103&bankOrderNo=201708041025147895&bankTansNo=7201708041025dd5322ds&payAmount=70&outOrderNo=T201708041025147895";
        String enString = AesUtil.encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = AesUtil.decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
    }

    private AesUtil(){}
}

package com.cloud.way.demotest.sm2;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

import java.security.KeyPair;

public class Main {

    static String txt = "Hello World";

    /***
     * 随机生成的密钥对加密或解密
     */
    public static void test1(){

        SM2 sm2 = SmUtil.sm2();

        System.out.println("私钥:" + sm2.getPrivateKey());
        System.out.println("公钥:" + sm2.getPublicKey());

        String encryptStr = sm2.encryptBcd(txt, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("密文:" + encryptStr);
        System.out.println("明文:" + decryptStr);
    }

    /***
     * 自定义密钥对加密或解密
     */
    public static void test2(){

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        String key = Base64.encode(privateKey);
        String value = Base64.encode(publicKey);
        String str = "1234";
        SM2 sm2 = SmUtil.sm2(key, value);
        System.out.println("第1个密钥对：\n"+"key:"+key+"\n"+"value:"+value+"\n==============================");
        String encryptBcd = sm2.encryptBcd(str, KeyType.PublicKey);
        String decryptBcd = StrUtil.utf8Str(sm2.decryptFromBcd(encryptBcd, KeyType.PrivateKey));
        System.out.println("密文："+encryptBcd);
        System.out.println("明文："+decryptBcd);


        KeyPair pair2 = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey2 = pair2.getPrivate().getEncoded();
        byte[] publicKey2 = pair2.getPublic().getEncoded();
        String key2 = Base64.encode(privateKey2);
        String value2 = Base64.encode(publicKey2);
        System.out.println("第2个密钥对：\n"+"key:"+key2+"\n"+"value:"+value2+"\n==============================");
        SM2 sm22 = SmUtil.sm2(key2, value2);
        String encryptBcd2 = sm22.encryptBcd(str, KeyType.PublicKey);
        String decryptBcd2 = StrUtil.utf8Str(sm22.decryptFromBcd(encryptBcd2, KeyType.PrivateKey));
        System.out.println("密文："+encryptBcd2);
        System.out.println("明文："+decryptBcd2);


    }

    /***
     * 使用OpenSSL生成的SM2公钥和私钥加密或解密
     */
    public static void test3(){

//        String privateKey = "MHcCAQEEIE8DQeWXexdeDsDh/e/SeZsT3SXFFxYTPvQrp2wO3Zc9oAoGCCqBHM9VAYItoUQDQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";
//        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";

        String privateKey = "MIGIAgEAMBQGCCqBHM9VAYItBggqgRzPVQGCLQRtMGsCAQEEIIjqPkShc43zuL/68DMWYC1c1XePqKqeaSHwEpRAL8Y+oUQDQgAE7UEXWQuCscVXMhiHy/DTpWTpcna3K5+VbzN3MHGk5BgS1yOEKqi8xXj1sOiLnvjBW64ENAkog7x3bJkeXAMfaA==";
        String publicKey = "MFowFAYIKoEcz1UBgi0GCCqBHM9VAYItA0IABO1BF1kLgrHFVzIYh8vw06Vk6XJ2tyuflW8zdzBxpOQYEtcjhCqovMV49bDoi574wVuuBDQJKIO8d2yZHlwDH2g=";

                SM2 sm2 = SmUtil.sm2(Base64.decode(privateKey), Base64.decode(publicKey));
        String encryptStr = sm2.encryptBcd(txt, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("密文:" + encryptStr);
        System.out.println("明文:" + decryptStr);
    }

    /***
     * 随机密钥 签名和验签
     */
    public static void test4(){

        SM2 sm2 = SmUtil.sm2();
        String sign = sm2.signHex(HexUtil.encodeHexStr(txt));
        System.out.println("sign:" + sign);
        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(txt), sign);
        System.out.println("verify:" + verify);
    }

    /***
     * 自定义密钥对 签名和验签
     */
    public static void test5(){

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        final SM2 sm2 = new SM2(pair.getPrivate(), pair.getPublic());

        byte[] sign = sm2.sign(txt.getBytes());
        System.out.println("sign");
        for(Integer i = 0; i < sign.length; i++){

            System.out.print(sign[i] + " ");
        }
        System.out.println();

        boolean verify = sm2.verify(txt.getBytes(), sign);
        System.out.print("verify:" + verify);
    }

    /**
     * OpenSSL密钥对 签名和验签
     */
    public static void test6(){

        String privateKey = "MHcCAQEEIE8DQeWXexdeDsDh/e/SeZsT3SXFFxYTPvQrp2wO3Zc9oAoGCCqBHM9VAYItoUQDQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";
        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";

        SM2 sm2 = SmUtil.sm2(Base64.decode(privateKey), Base64.decode(publicKey));
        byte[] sign = sm2.sign(txt.getBytes());

        System.out.println("sign");
        for(Integer i = 0; i < sign.length; i++){

            System.out.print(sign[i] + " ");
        }
        System.out.println();

        boolean verify = sm2.verify(txt.getBytes(), sign);
        System.out.print("verify:" + verify);
    }

    /***
     * 分开的：私钥签名，公钥验签
     * 密钥使用OpenSSL生成
     */
    public static void test7(){

        String privateKey = "MHcCAQEEIE8DQeWXexdeDsDh/e/SeZsT3SXFFxYTPvQrp2wO3Zc9oAoGCCqBHM9VAYItoUQDQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";
        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE5SMhCzQHRkg5cjVY4NgnZbniyslJG9hsmcibn8Q/vpqUOV7jE428SuQ9qo/gH9US3oVoEC40xmmJ6yswZhG1GA==";

        SM2 sm2Sign = SmUtil.sm2(Base64.decode(privateKey), null);
        sm2Sign.usePlainEncoding();
        byte[] sign = sm2Sign.sign(txt.getBytes(), null);

        System.out.println("sign");
        for(Integer i = 0; i < sign.length; i++){

            System.out.print(sign[i] + " ");
        }
        System.out.println();

        SM2 sm2 = SmUtil.sm2(null, Base64.decode(publicKey));
        sm2.usePlainEncoding();
        boolean verify = sm2.verify(txt.getBytes(), sign);
        System.out.print("verify:" + verify);
    }

    public static void test8(){

        String privateKeyHex = "FAB8BBE670FAE338C9E9382B9FB6485225C11A3ECB84C938F10F20A93B6215F0";
        String x = "9EF573019D9A03B16B0BE44FC8A5B4E8E098F56034C97B312282DD0B4810AFC3";
        String y = "CC759673ED0FC9B9DC7E6FA38F0E2B121E02654BF37EA6B63FAF2A0D6013EADF";

// 数据和ID此处使用16进制表示
        String data = "434477813974bf58f94bcf760833c2b40f77a5fc360485b0b9ed1bd9682edb45";
        String id = "31323334353637383132333435363738";

        final SM2 sm2 = new SM2(privateKeyHex, x, y);
// 生成的签名是64位
        sm2.usePlainEncoding();

        final String sign = sm2.signHex(data, id);
// true
        boolean verify = sm2.verifyHex(data, sign);
        System.out.print("verify888:" + verify);
    }

    public static void main(String[] args) {

        /*test1();*/
        System.out.println("--------------------华丽的分割线------------------------");
        test2();
        /*System.out.println("--------------------华丽的分割线------------------------");
        test3();*/
        /*System.out.println("--------------------华丽的分割线------------------------");
        test4();
        System.out.println("--------------------华丽的分割线------------------------");
        test5();
        System.out.println("--------------------华丽的分割线------------------------");
        test6();
        System.out.println("--------------------华丽的分割线------------------------");
        test7();
        System.out.println("--------------------华丽的分割线------------------------");
        test8();*/
    }
}
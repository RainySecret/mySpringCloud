package com.cloud.way.demotest.sm2;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description 国密SM2加解密工具类
 * @author chongwei
 * @date 2022-6-28
 */
public class SM2UtilsNew {

    /*
     * 前端私钥->后端进行解密使用
     */
    private static final String PRIVATE_KEY = "62197f3979bc3864cb02e65ef8ab08cdbb3d5688944463b70837b7eab561d0f6";

    /*
     * 前端公钥->给前端进行加密使用
     */
    private static final String PUBLIC_KEY = "04774f8b4b041236a92ac9d0b5b9dacd63197df5e864294c393c9282bc8e59ed90788643780482f82b478c87b5c951fcb931c4aa486bfa8c79066b7c8da46b2fe7";

    /**
     * 前端SM2加密发
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        SM2 sm2 = getSM2(null, PUBLIC_KEY);
        String dataHex = sm2.encryptBcd(data, KeyType.PublicKey);
        return dataHex;
    }

    /**
     * SM2解密
     * @param encryptedData
     * @return
     */
    public static String web_decrypt(String encryptedData) {
        if (!StringUtils.hasText(encryptedData)) {
            return null;
        }
        try {
            //创建sm2 对象
            SM2 sm2 = getSM2(PRIVATE_KEY, null);
            String data = StrUtil.utf8Str(sm2.decryptFromBcd(encryptedData, KeyType.PrivateKey));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static SM2 getSM2(String privateKey, String publicKey) {
        ECPrivateKeyParameters ecPrivateKeyParameters = null;
        ECPublicKeyParameters ecPublicKeyParameters = null;
        if (StringUtils.hasText(privateKey)) {
            ecPrivateKeyParameters = BCUtil.toSm2Params(privateKey);
        }
        if (StringUtils.hasText(publicKey)) {
            if (publicKey.length() == 130) {
                //这里需要去掉开始第一个字节 第一个字节表示标记
                publicKey = publicKey.substring(2);
            }
            String xhex = publicKey.substring(0, 64);
            String yhex = publicKey.substring(64, 128);
            ecPublicKeyParameters = BCUtil.toSm2Params(xhex, yhex);
        }
        //创建sm2 对象
        SM2 sm2 = new SM2(ecPrivateKeyParameters, ecPublicKeyParameters);
        sm2.usePlainEncoding();
        return sm2;
    }

    public static void main(String[] args) {
//        LocalDateTime startDate = LocalDateTime.parse("2022-08-02 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        String param = "{\"eventCode\":\"123123123\",\"deviceCode\":\"haikang1211\",\"identityCode\":\"41132319850324001X\",\"eventType\":\"2\",\"receiveTime\":\"2022-7-8 15:53:02\",\"eventInOut\":\"1\",\"longitude\":\"12.3\",\"latitude\":\"86.4\",\"qrId\":\"1222\"}";
        String param = "{\"personName\":\"测试名称07222033\",\"idCard\":\"219837198231231\",\"personPhone\":\"113213121323\",\"taxiNum\":\"陕AE86\",\"startTime\":\"2022-7-22 20:33:25\",\"driverCompany\":\"007公司\",\"startAddress\":\"地点111\",\"endAddress\":\"地点222\",\"startLatitude\":\"21.8767\",\"startLongitude\":\"98.7567\"}";
        String param2 = "{\"queryType\":1,\"queryCode\":\"232103198707051350\"}";
        System.out.println(SM2UtilsNew.encrypt(param));
        System.out.println(SM2UtilsNew.encrypt(param2));

//        PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
//
//        System.out.println(passwordEncoder.encode("123456"));
        String aa = null;
        System.out.println(JSON.toJSONString(aa).equals("null"));
        System.out.println(SM2UtilsNew.web_decrypt("04111773672270A7BCE5140392A2FBC4EE20861CC184CDE92B40F5EF11D09262BE6C86D88B59AE990CDE2AF3E323B39FBC25CB645174C67534E2DAF046416CFD0D9EF682211C2928EEC2016C9009180E4A5068174C11311AE4C99F2930C8463A803C0732A3205EEFC9B7BAE15D04CD6164427F427AC14F38DD20CA980DD7B1E9E5E68F54AF5A1BD46A70EDA4162CAFC42EDE582014C95C65DB11A153A4C9B87DCC88319458E0BC7F77319E9DBFBEDFA23AC4"));

//        LocalDateTime endDate = LocalDateTime.now();
//        Duration between = Duration.between(startDate, endDate);
//        System.out.println(between.toMillis());
//        System.out.println(between.getSeconds());
    }

}

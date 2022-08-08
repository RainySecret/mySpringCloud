package com.cloud.way.demotest.sm2;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.springframework.util.StringUtils;

/**
 * @description 国密SM2加解密工具类
 * @author chongwei
 * @date 2022-6-28
 */
public class SM2Utils {

    /**
     * 私钥
     */
    private static final String PRIVATEKEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgxL/XrC3w3B4YMD7TJYoC273Ic5+oTwsysbmyiAT4epSgCgYIKoEcz1UBgi2hRANCAASMNKhkA2gKB05I//8jLW+4yVHlWKSeL+/cigCBSICD8shxGEHd/1Mx23AYI2idhI3rrnc0gTTGlUrL8vbSXY+3";
    /**
     * 公钥
     */
    private static final String PUBLICKEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEjDSoZANoCgdOSP//Iy1vuMlR5Vikni/v3IoAgUiAg/LIcRhB3f9TMdtwGCNonYSN6653NIE0xpVKy/L20l2Ptw==";

    private static final SM2 sm2 = SmUtil.sm2(PRIVATEKEY, PUBLICKEY);

    /**
     * SM2加密
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        if (!StringUtils.hasLength(data)) {
            return null;
        }
        return sm2.encryptBcd(data, KeyType.PublicKey);
    }

    /**
     * SM2解密
     * @param encryptedData
     * @return
     */
    public static String decrypt(String encryptedData) {
        if (!StringUtils.hasLength(encryptedData)) {
            return null;
        }
        return StrUtil.utf8Str(sm2.decryptFromBcd(encryptedData, KeyType.PrivateKey));
    }

    /**
     * main方法测试
     * @param args
     */
    public static void main(String[] args) {
//        String param = "{\"queryType\":2,\"idCard\":\"610124199608057226\",\"qrCode\":\"562680709039259648\",\"personPhone\":\"18629386585\"}";
        String param = "{\"deviceCode\":\"haikang111\",\"identityCode\":\"41132319850324001X\",\"eventType\":\"2\",\"receiveTime\":\"2022-7-8 15:53:02\",\"eventInOut\":\"1\",\"longitude\":\"12.3\",\"latitude\":\"86.4\",\"qrId\":\"1222\"}";
        String encryptStr = SM2Utils.encrypt(param);
        System.out.println(encryptStr);
        String decryptStr = SM2Utils.decrypt("047316F8FC43E45240CC947111334C3F6081A7C40AAE7E9A427F8929D60E40A8D9C1982B36CD8F75649A0BA50AC9E47C415CCC76E15D1766508F596EE465926762D54DFA9F93451651ED6010333446C81C9AB3EA279202BA10C07525AD1CA5501B1A05C3FE22EBC2733BE70C36D1D2BE46ABA12EC2009CBE1FA4C2026ED2506C50DEC08C8C5EEDAA3CE5E7637451CF0EA8F4C8C4384265DDF2BF10412025131F88BA45E807A439713961BC41404C81C1D4F838FCB9AE86AC55CB260CAF7FF37BDA3B6DEAC92F853AE27E9437D45B6045D47157ADF207DEE9711BA750103E17BFFFAB736E82110941A7F96DA7FD82CC93C34C0D6686421786CCBADFEF1A36F20E3A88E2ABF3CDC7D122FF71797348E00032CF5A6E38BD878618FD6FCBAA5EBE6C8BA992D1D3B8E25A22286377970615912C76A37A86D78AB2606DAF3B797EDA0B2A68DE13AC8C5B298CABC830383BB5CB3EBA430F780E7A52CB48745B7045EA5E89E91CF7F86757C1EF8F82E93AB858675365687F3882E2EB936E1EF6900A36A687A44F7B3EC98A46ACB2AF64D51A813487178A336D919613F8D7487E5A936F01DE8D1737635EDAE11AA3D739DB6CD587049D06514128EF77204B55D544124DFCCBE7");
        System.out.println(decryptStr);
    }

}
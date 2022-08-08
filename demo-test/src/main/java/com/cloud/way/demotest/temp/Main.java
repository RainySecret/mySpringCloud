package com.cloud.way.demotest.temp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author chongwei
 * @Description
 * @date 2022/7/7
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(DateUtil.date(1660302319044L));
        System.out.println(System.currentTimeMillis());
//        getTXT();

//        aes("dZG8G57SuifM7Qn9+pk4m0qhyeKqmAx9QhahyjkuT76+G/AP7Lf73Fjy3bg446rW7Tc7ltgktT7O5yrb+7LjSWdUOd2ZXbgE4oqcfdJjVBYa71Oh3d82c0UfSE1l2kw0ov7+wMrNye81/yv7gEqwe1sfRpdKXMiTynOSHJJasYGy258xca7RxlwP3Ihp7NpQkplhk1oa+LI2VuW51XkTxUkBpYU+y+WoChRIleXa5rHxXY6ACOc5ohbbo4Rqpr/sHaMlZQXa03GKCuL6G8k5RfbBCYcVJLKkUhaBpQ/CXx4xP6WN79cXslvTKPlPVUxjSVGHDBssfZADSMl3PICuFvfdfMuXEWcWw7JRV+7S8+E6e0AmfvEyYPm78CGZyUHR");

//        des();

    }

    private static void getTXT() {
        StringBuilder result = new StringBuilder();

        File file = new File("D:\\myprojects\\ideaprojects\\test\\demo\\src\\main\\resources\\temp");

        File[] f = file.listFiles();

        try {
            BufferedReader br;
            for (File file1 : f) {
                // 构造一个BufferedReader类来读取文件
                br = new BufferedReader(new FileReader(file1));

                String s;
                // 使用readLine方法，一次读一行
                while ((s = br.readLine()) != null) {

                    result.append(System.lineSeparator() + s);

                }

                br.close();
            }


        } catch (Exception e) {

            e.printStackTrace();

        }

        StringBuilder sb = new StringBuilder();

//        System.out.println(result.toString());
        String[] elements = result.toString().split(",,,");

        for (String element : elements) {
            String aa = aes(element);
            JSONObject jsonObject = (JSONObject) JSON.parse(aa);
            String identityCode = jsonObject.getString("identityCode");
            if (ObjectUtils.isEmpty(identityCode) || identityCode.length() < 20 || identityCode.contains("http")) {
                System.out.println(identityCode);
            } else {
                System.out.println(des(identityCode));
            }
        }
    }

    private static String aes(String param) {
        AES aes = SecureUtil.aes("zm9dlrycptdns0b0pcav99ryd50lo6n4".getBytes());

//        System.out.println(aes.decryptStr(param));

        return aes.decryptStr(param);
    }

    private static String des(String param) {
        String secretKey = "1Q23LyC45Jtt67Z8";
        DES des = SecureUtil.des(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), secretKey.getBytes()).getEncoded());

//        System.out.println(des.decryptStr("param"));
        String res = null;
        try {
            res = des.decryptStr(param);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(param);
            throw new RuntimeException("错了");
        }
        return res;
    }
}

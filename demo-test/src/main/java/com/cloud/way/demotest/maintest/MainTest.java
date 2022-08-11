package com.cloud.way.demotest.maintest;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 * @date 2022/6/1
 */
public class MainTest {

    public static void main(String[] args) {
        Long a = System.currentTimeMillis();
        System.out.println(a);

        Person person = new Person();
        person.setName("John");
        person.setSex("M");
        System.out.println(JSON.toJSON(person));

        System.out.println(DateUtil.date(1658449114000L));
    }

    @Setter
    @Getter
    public static class Person{
        private String name;
        private String sex;
        private Integer age;
        /*工龄*/
        private Integer seniority;
    }
}

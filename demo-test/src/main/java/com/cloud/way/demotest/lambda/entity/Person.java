package com.cloud.way.demotest.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2022/6/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String sex;
    private Integer age;
    /*工龄*/
    private Integer seniority;
}

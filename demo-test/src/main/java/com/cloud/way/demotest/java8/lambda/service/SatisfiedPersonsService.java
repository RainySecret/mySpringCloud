package com.cloud.way.demotest.java8.lambda.service;

/**
 * @author Administrator
 * @date 2022/6/7
 */
public interface SatisfiedPersonsService<T> {

    /**
     * 对传递过来的T类型的数据进行过滤
     * 符合规则返回true，不符合规则返回false
     */
    boolean filter(T t);
}

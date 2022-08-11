package com.cloud.way.demotest.java8.lambda.functionInterface;

/**
 * @author Administrator
 * @date 2022/6/7
 */
@FunctionalInterface
public interface MyFunctionInterface<T> {

    int compare(T o1, T o2);

}

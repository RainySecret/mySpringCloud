package com.cloud.way.demotest.java8.lambda.service;

import com.cloud.way.demotest.java8.lambda.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2022/6/7
 */
@Service
public class SatisfiedPersonsServiceImpl implements SatisfiedPersonsService {


    /**
     * 对传递过来的T类型的数据进行过滤
     * 符合规则返回true，不符合规则返回false
     *
     * @param obj
     */
    @Override
    public boolean filter(Object obj) {
        return false;
    }

    /**
     * 根据年龄过滤
     * @param list
     * @return
     */
    public List<Person> filterByAge(List<Person> list){
        List<Person> persons = new ArrayList<>();
        for(Person e : list){
            if(e.getAge() >= 30){
                persons.add(e);
            }
        }
        return persons;
    }

    /**
     * 根据工龄过滤
     * @param list
     * @return
     */
    public List<Person> filterBySeniority(List<Person> list){
        List<Person> persons = new ArrayList<>();
        for(Person e : list){
            if(e.getSeniority() > 5){
                persons.add(e);
            }
        }
        return persons;
    }
}

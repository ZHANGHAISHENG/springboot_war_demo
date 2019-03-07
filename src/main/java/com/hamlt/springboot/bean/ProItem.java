package com.hamlt.springboot.bean;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件映射对象
 */
@Component
@PropertySource("classpath:object.properties")
@ConfigurationProperties(prefix="obj")
public class ProItem {

    private String name;
    private String age;
    //集合必须初始化，如果找不到就是空集合，会报错
    private List<String> className = new ArrayList<>();

    public List<String> getClassName() {
        return className;
    }
    public void setClassName(List<String> className) {
        this.className = className;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}

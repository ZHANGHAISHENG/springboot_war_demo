package com.hamlt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//默认组件扫描的是@SpringBootApplication所在目录以及子目录下的所有目录
//相当于： @ComponentScan("com.hamlt.springboot")
//@EnableScheduling //注解开启对定时任务的支持(发现注解@Scheduled的任务并后台执行,也可以只放到定时器类上)
//@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />,默认已存在，不用手动添加
//@ImportResource("classpath:transaction.xml") 也可以直接使用xml声明式事务（或定义java类（麻烦））
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {


	public DemoApplication(){
		super();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

}


package com.resume;

// 导入SpringBoot相关的注解和类
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类 - 整个后端程序的入口
 *
 * @SpringBootApplication 是一个复合注解，相当于同时加了三个注解：
 *   1. @Configuration     - 表示这是一个配置类
 *   2. @EnableAutoConfiguration - 开启SpringBoot自动配置（自动帮我们配置很多东西）
 *   3. @ComponentScan    - 自动扫描当前包下所有的组件（Controller、Service等）
 */
@SpringBootApplication
public class ResumeApplication {

    /**
     * main方法 - Java程序永远从这里开始执行
     * SpringApplication.run() 会启动整个Spring容器和内置的Tomcat服务器
     */
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class, args);
        System.out.println("=================================");
        System.out.println("  简历后端服务启动成功！");
        System.out.println("  访问地址: http://localhost:8080");
        System.out.println("=================================");
    }
}
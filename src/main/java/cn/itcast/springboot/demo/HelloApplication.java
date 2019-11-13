package cn.itcast.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;

@Controller //标明这是一个springMVC的controller控制器
//spring boot的核心注解，主要目的是开启自动配置，组合注解，里面有很多注解
@SpringBootApplication //(exclude = {...class})排除某个自动配置
@Configuration	//表明这是一个配置spring的配置类，相当于xml文件
public class HelloApplication {	//类名规则为 XXXApplication ，为整个应用的入口类
    
	//@ComponentScan：默认扫描@SpringBootApplication所在类的同级目录以及它的子目录。
	//spring mvc的control写法
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world！你好啊！";
    }
    
    /*
     * 自定义一个消息转化器：可以指定消息的编码格式，ISO-8859-1中文出现乱码
     * 如果自己没有配置，springboot会默认帮我们配置，编码格式为utf-8
     */
    //消息转化器的第一种方法
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
    	StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("ISO-8859-1"));
    	return converter;
    }
    
    //main中启动一个应用，即应用的入口，入口类一般有一个main方法
    public static void main(String[] args) {
    	/*
    	 * 在Spring Boot项目中，启动的方式有两种，
    	 * 一种是直接run Java Application
    	 * 另外一种是通过Spring Boot的Maven插件运行。输入命令：spring-boot:run
    	 */
        SpringApplication.run(HelloApplication.class, args);
    }

}

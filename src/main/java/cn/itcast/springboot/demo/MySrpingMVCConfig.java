package cn.itcast.springboot.demo;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration //申明这是一个配置，自定义一个springmvc的配置类
public class MySrpingMVCConfig extends WebMvcConfigurerAdapter{

    /*
     *	自定义拦截器 生效的原理：
     *		因为自定义的spring MVC配置类跟xxApplication类在同一个包下，而xxApplication中的@SpringBootApplication
     *		注解内部有@ComponentScan注解，会自动扫描跟xxApplication类同包以及子包下的所有类，使其生效。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//定义拦截器对象，使用匿名内部类实现
        HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
                System.out.println("自定义拦截器............");
                return true;
            }
            
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    ModelAndView modelAndView) throws Exception {
                
            }
            
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                    Exception ex) throws Exception {
            }
        };
        //注册到拦截器表中，并规定拦截规则，/** 全部拦截
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }

    // 自定义消息转化器的第二种方法，现在自定义了两个，不算内部自定义的
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(converter);
    }

}

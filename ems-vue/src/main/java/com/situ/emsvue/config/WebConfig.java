package com.situ.emsvue.config;


import com.situ.emsvue.converter.String2DateConverter;
import com.situ.emsvue.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把登录的拦截器配置上才能起作用
        // addPathPatterns("/**") 拦截器拦截所有的请求，静态资源也拦截了，需要放行
        // excludePathPatterns 代表哪些请求不需要拦截
        System.out.println("WebConfig.addInterceptors");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/page/login", "/user/login", "/user/logout" , "/captcha");
    }
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:/D:/Demo/ProgrameDemo/blogimage/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }*/
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter() );
    }
}

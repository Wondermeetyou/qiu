package com.qiu.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TokenInterceptor 自定义拦截器后，需要配置进Spring
 * 也可以mapping，跨域设置
 */
@Slf4j
@Configuration
public class TokenConfig implements WebMvcConfigurer {

    @Autowired
    TokenInterceptor tokenInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("拦截器添加成功！");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")//指定该类拦截的url
                .excludePathPatterns("/static/**");//过滤静态资源
    }

    /**
     * 如果实现了Filter跨域拦截，这个跨域无效
     * 拦截器实现 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("跨域拦截器启动！");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}

package com.fastcampus.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMcvConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/**") //인터셉터 적용 대상
                .excludePathPatterns("/css/**","/js/**"); //인터셉터 적용 제외 대상
    }
}

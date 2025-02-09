package com.pieces.todoapp.config;

import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.interceptor.SecurityKeyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final SecurityKeyInterceptor securityKeyInterceptor;

    public WebConfiguration(SecurityKeyInterceptor securityKeyInterceptor) {
        this.securityKeyInterceptor = securityKeyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityKeyInterceptor)
                .addPathPatterns(RequestMappingField.CREATE_USER_MAP, RequestMappingField.USER_LOGIN_MAP);
    }

}

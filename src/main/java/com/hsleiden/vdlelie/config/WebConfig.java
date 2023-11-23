package com.hsleiden.vdlelie.config;

import com.hsleiden.vdlelie.filters.HttpMethodOverrideFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<HttpMethodOverrideFilter> httpMethodOverrideFilter() {
        FilterRegistrationBean<HttpMethodOverrideFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpMethodOverrideFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

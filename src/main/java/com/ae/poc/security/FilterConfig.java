package com.ae.poc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private SessionAuthFilter sessionAuthFilter;

    @Bean
    public FilterRegistrationBean<SessionAuthFilter> sessionAuthFilterRegistration() {
        FilterRegistrationBean<SessionAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(sessionAuthFilter);
        registration.addUrlPatterns("/poc/*"); // Apply to all /poc/ endpoints
        registration.setOrder(1);
        return registration;
    }
}

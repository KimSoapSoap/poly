package org.mtcoding.poly.core.config;
import org.mtcoding.poly.core.filter.UrlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<?> urlValidationFilter() {
        FilterRegistrationBean<UrlFilter> urlValidationFilter = new FilterRegistrationBean(new UrlFilter());
        urlValidationFilter.addUrlPatterns("/*");
        return urlValidationFilter;
    }

}

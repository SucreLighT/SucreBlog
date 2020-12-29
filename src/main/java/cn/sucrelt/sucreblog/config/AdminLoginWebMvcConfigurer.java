package cn.sucrelt.sucreblog.config;

import cn.sucrelt.sucreblog.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 拦截器配置类
 * @author: sucre
 * @date: 2020/12/29
 * @time: 09:19
 */
@Configuration
public class AdminLoginWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin/login").
                excludePathPatterns("/admin/dist/**").
                excludePathPatterns("/admin/plugins/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").
                addResourceLocations("file: " + ConstantsConfig.FILE_UPLOAD_DIC);
    }
}

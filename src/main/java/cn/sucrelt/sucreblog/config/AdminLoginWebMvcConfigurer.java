package cn.sucrelt.sucreblog.config;

import cn.sucrelt.sucreblog.interceptor.AdminLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @description: 拦截器配置类
 * @author: sucre
 * @date: 2020/12/29
 * @time: 09:19
 */
@Configuration
public class AdminLoginWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private AdminLoginInterceptor adminLoginInterceptor;


    /**
     * 设置前端拦截路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin/login").
                excludePathPatterns("/admin/dist/**").
                excludePathPatterns("/admin/plugins/**");
    }

    /**
     * 设置文件上传和拦截的路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").
                addResourceLocations("file: " + ConstantsConfig.FILE_UPLOAD_DIC);
    }
}

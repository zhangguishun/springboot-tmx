package com.example.uniappspringboot.Config;



import com.example.uniappspringboot.Util.TokenInterceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 开启跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许携带cookie参数
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    private TokenInterceptor tokenInterceptor;

    //构造方法
    public WebMvcConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/user/PostLogin");  //登录
        excludePath.add("/alipay/**");//支付
        excludePath.add("/user/PostReg"); //注册
        excludePath.add("/**/*.html");//放开所有.html的资源
        excludePath.add("/swagger-resources/**");//放开所有.html的资源
        excludePath.add("/webjars/**");//放开所有.html的资源
        excludePath.add("/v2/**");//放开所有.html的资源
        excludePath.add("/swagger-ui.html/**");//放开所有.html的资源
        excludePath.add("/swagger-ui.html");//放开所有.html的资源
        excludePath.add("/user/purview"); //综合网权限（用户信息）
        excludePath.add("/code/GetCode"); //获取验证码
        excludePath.add("/code/YzCode"); //校验
        excludePath.add("/upload/avatar");//头像上传
        excludePath.add("/code/DeletCode"); //清除验证码
        excludePath.add("/email/send"); //发送邮箱
        excludePath.add("/software/selSoftGet");//查询全部
        excludePath.add("/email/login"); //邮箱登录
        excludePath.add("/list/selItem");//获取综合网数据列表
        excludePath.add("/img/**");  //静态资源
        excludePath.add("/song/**");  //静态资源
        excludePath.add("/api/getPublicKey");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

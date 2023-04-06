package com.example.uniappspringboot.Config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpConfig {//设置分页查询拦截器作为Spring管理的Bean
    @Bean
    public MybatisPlusInterceptor mpInterceptor(){
        //定义mp拦截器
        MybatisPlusInterceptor mpInterceptor =new MybatisPlusInterceptor();
        //添加具体拦截器
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());//添加分页拦截器
        return mpInterceptor;
    }

}
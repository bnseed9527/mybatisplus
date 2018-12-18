package com.mp.generator;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author YsCY丶
 * @Date 2018/10/17 17:33
 * @Version 1.0
 **/
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.wise.mapper*","com.wise.service*"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}

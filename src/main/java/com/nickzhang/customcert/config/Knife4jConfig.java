package com.nickzhang.customcert.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 张骏山
 * @Date: 2026/1/15 1:07
 * @PackageName: com.nickzhang.customcert.config
 * @ClassName: Knife4jConfig
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("海关正式xml兼容生成")
                .version("1.0")
                .description("自定义证书管理系统接口文档")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("张骏山")
                        .email("1656384784@qq.com")
                )
        );
    }
}

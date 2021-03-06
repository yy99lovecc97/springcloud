package com.lovecc.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private List<Parameter> parameter(){
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("Authorization")
        .description("Authorization Bearer token")
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(false).build());
        return parameters;
    }
    @Bean
    public Docket sysApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lovecc.userservice.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameter());
        //在所有rest统一添加一个参数
    }

    /**
     * API文档的描述信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("user-service api")
                .description("user-service 微服务")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}

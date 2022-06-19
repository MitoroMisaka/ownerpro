package com.ownerpro.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnExpression("${swagger.enable:true}")//当enable为true时才选择加载该配置类
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket createAccountApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("account")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.account"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    //scan admin
    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("article")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.article"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    //scan search
    @Bean
    public Docket createSearchApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("search")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.search"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();

    }

    //scan file
    @Bean
    public Docket createFileApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("file")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.file"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    //add area docker
    @Bean
    public Docket createAreaApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("area")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.area"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    //add statistics docker
    @Bean
    public Docket createStatisticsApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("statistics")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.statistics"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    //add comment docker
    @Bean
    public Docket createCommentApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("comment")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ownerpro.web.controller.comment"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PaperManager API Documentation")
                .description("PaperManager接口文档")
//                .termsOfServiceUrl("http://localhost:8081/swagger-ui.html")//数据源
                .version("1.0")
                .build();
    }
}

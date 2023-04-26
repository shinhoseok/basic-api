package com.basic.api.config;

import com.basic.api.component.interceptor.AuthenticInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

//@EnableWebMvc //MessageConverter 커스터마이징시 구성
@Configuration
@ComponentScan(basePackages = "com.basic.api", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
public class BasicConfigWebDispatcherServlet implements WebMvcConfigurer {
    final static String RESOLVER_DEFAULT_ERROR_VIEW = "cmm/error/egovError";

    final static int URL_BASED_VIEW_RESOLVER_ORDER = 1;
    final static String URL_BASED_VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    final static String URL_BASED_VIEW_RESOLVER_SUFFIX = ".jsp";

    //private final String[] CORS_ORIGIN_SERVER_URLS = {"http://127.0.0.1:3000", "http://localhost:3000"};

    // =====================================================================
    // RequestMappingHandlerMapping 설정
    // =====================================================================
    // -------------------------------------------------------------
    // RequestMappingHandlerMapping 설정 - Interceptor 추가
    // -------------------------------------------------------------
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticInterceptor())
                .addPathPatterns(
                        "/uat/uia/*.do")
                .excludePathPatterns(
                        "/uat/uia/actionLogin.do",
                        "/uat/uia/actionLoginAPI.do",
                        "/uat/uia/egovLoginUsr.do",
                        "/uat/uia/egovLoginUsrAPI.do",
                        "/uat/uia/actionLogoutAPI.do"
                );
//        registry.addInterceptor(new CustomAuthenticInterceptor())
//                .addPathPatterns(
//                        "/**/*.do")
//                .excludePathPatterns(
//                        "/uat/uia/**");
    }

    // -------------------------------------------------------------
    // RequestMappingHandlerMapping 설정 View Controller 추가
    // -------------------------------------------------------------
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/cmmn/validator.do")
//                .setViewName("cmmn/validator");
//        registry.addViewController("/").setViewName("forward:/index.jsp");
//    }

    // -------------------------------------------------------------
    // HandlerExceptionResolver 설정
    // -------------------------------------------------------------
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
//
//        simpleMappingExceptionResolver.setDefaultErrorView(RESOLVER_DEFAULT_ERROR_VIEW);
//
//        Properties mappings = new Properties();
//        mappings.setProperty("org.springframework.dao.DataAccessException", "cmm/error/dataAccessFailure");
//        mappings.setProperty("org.springframework.transaction.TransactionException", "cmm/error/transactionFailure");
//        mappings.setProperty("org.egovframe.rte.fdl.cmmn.exception.EgovBizException", "cmm/error/egovError");
//        mappings.setProperty("org.springframework.security.AccessDeniedException", "cmm/error/accessDenied");
//
//        simpleMappingExceptionResolver.setExceptionMappings(mappings);
//
//        exceptionResolvers.add(simpleMappingExceptionResolver);
//    }

    // -------------------------------------------------------------
    // View Resolver 설정
    // -------------------------------------------------------------
//    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setOrder(URL_BASED_VIEW_RESOLVER_ORDER);
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix(URL_BASED_VIEW_RESOLVER_PREFIX);
        urlBasedViewResolver.setSuffix(URL_BASED_VIEW_RESOLVER_SUFFIX);
        return urlBasedViewResolver;
    }

    //json or xml 메시지 컨버터 아래와 같이 설정함
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter());
//    }
//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }
}

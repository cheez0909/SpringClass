package configs;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc // 핸들러매핑, 어댑터등 직접 설정하지 않아도 활성화해준다.
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        // 요청 자원이 컨트롤러에 없는 경우 -> 정적자원(css, js, 이미지)
    }

    /**
     * 컨트롤러와 상관없음
     * 정적 자원에 직접 접근이 가능하도록 설정
     * 모든 경로
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//
//        registry.jsp("/WEB-INF/templates/", ".jsp");
//    }

    /**
     * 개발시에는 소스를 추가하는 일이 많으므로
     * 캐시를 false로 해두는 것이 좋다.
     * 배포시에는 true로 변경
     * true -> 최초 로딩시 번역, 다음 요청시 기존 파일을 그대로 사용(실 사용중 서버)
     * false -> 매번 요청시마다 다시 번역(개발중)
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true); // EL식 허용
        templateEngine.addDialect(new Java8TimeDialect());
        // Date time API(java 타임 패키지)
        templateEngine.addDialect(new LayoutDialect());
        //
        return templateEngine;
    }

    /**
     * 타입 설정
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }
    /**
     * 최종 설정
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }
}
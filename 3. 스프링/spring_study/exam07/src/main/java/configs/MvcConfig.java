package configs;

import common.Utils;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc // 핸들러매핑, 어댑터등 직접 설정하지 않아도 활성화해준다.
@Import(DbConfig1.class) // db설정파일 import
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

        /**
         * 파일 업로드 시 이미지 파일등 해당 파일을 웹에서도 확인할 수 있도록
         * 경로를 지정함. 이미지 파일=정적파일
         * 이미지 파일이 아닐 경우 다운로드됨....
         */
        /**
         * 프로퍼티 파일을 불러와서
         * 유동적으로 바뀔 수 있도록....?
         *
         */
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///c:/uploads/");
        // 이 경로에 접근하도록
        // "/"를 이스케이프 문자로 인식하기 때문에 3개를 입력해야 2개로 인식함
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
        // 레이아웃 기능 추가
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

    /**
     * 메세지 설정 : 브라우저에서 언어 설정 시
     * html에서 메세지 표현식으로 표현되어 있는 부분이
     * 해당 언어로 변경됨
     */
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        // resource 파일 내에
        // properties 파일 확장자 제거 후 입력
        ms.setBasenames("message.commons", "message.validations");
        // 공통성, 유효성, 에러 메세지를 주로 넣음

        return ms;
    }

    @Bean
    public Utils utils(){
        return new Utils();
    }

    /**
     * 컨트롤러를 만들지 않고
     * 페이지 연동 (ex. 이벤트 페이지나 회사소개등등... 굳이 컨트롤러가 필요 없을 경우)
     * 직접 템플릿을 연동했을 경우
     * 모델 추가하기 불가능함 / 값을 못 넘김
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main/index");
        // *이 한개면 포함, *이 두개면 모든 하위 경로
        // 마이페이지에 인가가 없으니, url로 접속이 가능함
        // 인터셉터가 필요하다.
        registry.addViewController("/mypage/**").setViewName("mypage/index");
    }

    /**
     * 인터셉터 빈으로 등록
     */
    @Bean
    public MemberOnlyInterceptor memberOnlyInterceptor(){
        return new MemberOnlyInterceptor();
    }

    /**
     * 어떤 범위에서 사용할 건지 url 등록
     * 인가에 대한 구현은 다른 것으로 하기 때문에 인터셉터를 이용하지 않음
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberOnlyInterceptor())
                .addPathPatterns("/mypage/**");

        /**
         * 사이트 전역에 정보 추가
         */
        registry.addInterceptor(commonInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public CommonInterceptor commonInterceptor(){
        return new CommonInterceptor();
    }

    /**
     * 프로퍼티 파일을 빈으로 등록
     * PropertySources -> 프로퍼티에서 가져오는 설정이다
     * Placeholder -> 교체 방식, 설정 방식 : 위치를 치환하는 방식
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer(){
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocation(new ClassPathResource("application.properties"));
        return conf;
    }
}
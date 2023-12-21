package org.choongang.configs;

import lombok.extern.slf4j.Slf4j;
import org.choongang.scheduling.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 파일을 프로퍼티스 형태의 파일로 설정해놓으면 편하다
 */

@Configuration
@EnableConfigurationProperties(FileProperties.class)
@Slf4j
@EnableScheduling
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(registry);
        registry.addResourceHandler(fileProperties.getUrl()+"**")
                .addResourceLocations("file:///"+fileProperties.getPath());
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:///c:/uploads/");

    }

//    @Scheduled
//    public Stat stat(){
//        return new Stat();
//    }
}

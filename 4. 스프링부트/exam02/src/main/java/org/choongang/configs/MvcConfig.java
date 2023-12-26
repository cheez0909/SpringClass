package org.choongang.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
// @EntityListeners(AuditingEntityListener.class)가 활성화된다.
// 추가한 것을 활성화하기 위해서 추가 설정이 필요하다.
public class MvcConfig implements WebMvcConfigurer {
}

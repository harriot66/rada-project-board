package com.rada.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/*jpa에서 자동으로 값을 넣어주는 기능*/
@EnableJpaAuditing
/*스프링 설정 클래스를 선언하는 어노테이션*/
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("harriot"); //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }

}

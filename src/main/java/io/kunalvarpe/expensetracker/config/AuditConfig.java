package io.kunalvarpe.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Optional;

@Configuration
@EnableJdbcAuditing
public class AuditConfig {

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("d3dfaa27-d47e-4e5f-808f-25afdbf7016b");
    }
}

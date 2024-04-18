package com.vincent.playground.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class FlyWayConfig {
    private final Environment environment;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return new Flyway(Flyway.configure()
                .baselineOnMigrate(true)
                .locations(environment.getRequiredProperty("spring.flyway.locations"))
                .table(environment.getRequiredProperty("spring.flyway.table"))
                .schemas(environment.getRequiredProperty("spring.flyway.schemas"))
                .dataSource(environment.getRequiredProperty("spring.datasource.url"),
                        environment.getRequiredProperty("spring.datasource.username"),
                        environment.getRequiredProperty("spring.datasource.password"))
        );
    }
}
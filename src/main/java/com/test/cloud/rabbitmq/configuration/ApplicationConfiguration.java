package com.test.cloud.rabbitmq.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableR2dbcRepositories
@EnableWebFlux
public class ApplicationConfiguration {

}

package com.example.demo.config;

import com.example.demo.tcp.TcpVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    public static final String CR = "\r\n";

    @Bean
    Vertx vertx() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(
                TcpVerticle.class.getName(),
                new DeploymentOptions().setInstances(VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE)
        );

        return vertx;
    }
}

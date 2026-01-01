package com.app.protoSmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ProtoSmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtoSmartApplication.class, args);
    }

}

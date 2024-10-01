package ru.webapp.everylab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class EverylabApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverylabApplication.class, args);
    }

}

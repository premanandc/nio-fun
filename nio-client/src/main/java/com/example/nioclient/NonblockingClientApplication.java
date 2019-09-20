package com.example.nioclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

@SpringBootApplication
public class NonblockingClientApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(NonblockingClientApplication.class);
        CommandLineRunner runner = context.getBean(CommandLineRunner.class);
        runner.run(args);
    }

    @Bean
    public WebClient client() {
        return WebClient.create("http://localhost:8080/");
    }

    @Bean
    CommandLineRunner cli(WebClient client) {
        return args -> client.get()
                .uri("/stream")
                .accept(APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(MyNumber.class)
                .log()
                .subscribe();
    }
}

package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main App
 *
 * @author long
 */

@SpringBootApplication
@RestController
public class App {

    @RequestMapping("/")
    public Object home() {
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

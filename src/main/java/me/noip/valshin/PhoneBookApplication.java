package me.noip.valshin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcConfig.class, args);
    }
}

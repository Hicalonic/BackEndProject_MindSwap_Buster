package org.mindswap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovieApplication {
    public static void main(String[] args) {

        SpringApplication.run(MovieApplication.class, args);
    }
}

//todo @data nas anotacoes lombok
//todo controllers
//todo meter email e role no token
//todo swagger

//todo junçao com a outra API

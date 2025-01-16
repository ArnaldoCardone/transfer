package com.paysimples.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
    //Classe para configuração do RestTemplate
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductApplication
{
    public static void main( String[] args ) {
        SpringApplication.run(ProductApplication.class, args);
    }

    /*@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    @Bean
    @LoadBalanced
    public RestClient.Builder restClient() {
        return RestClient.builder();
    }
}

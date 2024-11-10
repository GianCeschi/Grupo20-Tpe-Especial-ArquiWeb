package org.example.microviaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MicroViajeApplication {

    public static void main(String[] args) {
        SpringApplication.run(org.example.microviaje.MicroViajeApplication.class, args);
    }

}

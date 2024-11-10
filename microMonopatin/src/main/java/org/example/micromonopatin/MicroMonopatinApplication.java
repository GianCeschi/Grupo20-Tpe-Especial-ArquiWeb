package org.example.micromonopatin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroMonopatinApplication {

    public static void main(String[] args) {
        SpringApplication.run(org.example.micromonopatin.MicroMonopatinApplication.class, args);
    }

}

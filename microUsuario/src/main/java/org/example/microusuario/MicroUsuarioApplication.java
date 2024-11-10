package org.example.microusuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroUsuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(org.example.microusuario.MicroUsuarioApplication.class, args);
    }

}

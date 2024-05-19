package com.gl.ceir;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan({"com.gl.ceir.config"})

@ComponentScan({"com.gl.ceir"})

@EnableEncryptableProperties

public class PlatformComponents {

    public static void main(String[] args) {
        SpringApplication.run(PlatformComponents.class, args);
    }
}


package com.huang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.huang.dao")
@SpringBootApplication
public class PetcenterBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetcenterBackApplication.class, args);
        System.out.println("git");
    }

}

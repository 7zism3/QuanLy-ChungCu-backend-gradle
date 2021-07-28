package com.nhom43.quanlychungcubackendgradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class QuanLyChungCuBackendGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyChungCuBackendGradleApplication.class, args);
    }

}

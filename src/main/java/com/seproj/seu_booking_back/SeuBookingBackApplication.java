package com.seproj.seu_booking_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seproj.seu_booking_back.mapper")
public class SeuBookingBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeuBookingBackApplication.class, args);
    }

}

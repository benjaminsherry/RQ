package com.example.rqchallenge;

import com.example.rqchallenge.employees.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RqChallengeApplication {


    public static void main(String[] args) {
        SpringApplication.run(RqChallengeApplication.class, args);
    }


}

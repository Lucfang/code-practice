package com.lcf.codepractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync      // 开启异步
@EnableRetry      // 开启重试
public class CodePracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodePracticeApplication.class, args);
    }
}
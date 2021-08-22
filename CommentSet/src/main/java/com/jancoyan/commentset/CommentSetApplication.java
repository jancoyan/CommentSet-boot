package com.jancoyan.commentset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jancoyan.commentset.mapper")
public class CommentSetApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentSetApplication.class, args);
    }
}

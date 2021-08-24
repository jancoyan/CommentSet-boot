package com.jancoyan.commentset;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentSetApplicationTests {

    @Test
    void contextLoads() {

        String target = "file.html";
        target = target.substring(0, target.indexOf("."));
        System.out.println(target);

    }

}

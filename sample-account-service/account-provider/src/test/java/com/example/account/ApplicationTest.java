package com.example.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Test
    public void testChar() {
        char name = '张';
        System.out.println(name);
    }

}

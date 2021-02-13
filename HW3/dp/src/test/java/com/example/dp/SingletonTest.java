package com.example.dp;

import com.example.dp.singleton.Singleton;
import com.example.dp.singleton.SpringSingleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SingletonTest {

    @Autowired
    SpringSingleton springSing1;
    
    @Autowired
    SpringSingleton springSing2;

    @Test
    void contextLoads() {
        Singleton sing1 = Singleton.getInstance();
        Singleton sing2 = Singleton.getInstance();

        //testing the traditional way
        Assertions.assertSame(sing1, sing2);

        // Assertions.assertEquals("expected", "actual");

        //testing the spring way
        Assertions.assertSame(springSing1, springSing2);
    }
}

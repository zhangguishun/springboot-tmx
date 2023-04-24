package com.example.uniappspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.uniappspringboot.Util.secretUtil.desEncrypt;
import static com.example.uniappspringboot.Util.secretUtil.encrypt;

@SpringBootTest
class UniAppSpringbootApplicationTests {

    @Test
    void contextLoads() {
        String resiltP="admin,user"+","+"SVIPusers";
        System.out.println(resiltP);

        System.out.println( encrypt(resiltP));
    }

}

package com.hsleiden.vdlelie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations= "classpath:applicationTest.properties")
class BackendSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }

}

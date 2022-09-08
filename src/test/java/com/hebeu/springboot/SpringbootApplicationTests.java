package com.hebeu.springboot;

import com.hebeu.springboot.Mapper.UserMapper;
import com.hebeu.springboot.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    UserMapper userService;
    @Test
    void contextLoads() {
    }

    @Test
    void hello(){
        System.out.println(userService.findAll());

    }
}

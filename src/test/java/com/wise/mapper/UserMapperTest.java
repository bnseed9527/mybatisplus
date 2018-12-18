package com.wise.mapper;

import com.wise.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void list(){

        List<User>list = userMapper.selectList(null);
        for (User u :
                list) {
            System.out.println(u);
        }
    }
}
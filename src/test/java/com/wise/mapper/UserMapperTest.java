package com.wise.mapper;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wise.entity.User;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void list(){

        List<User>list = userMapper.selectList(new QueryWrapper<User>().eq(true,"name",null));
        list.forEach(System.out::println);
    }
    @Test
    public void insert(){
        User user = new User();
        user.setName("小王");
        user.setAge(23).setEmail("123456789@qq.com");
        userMapper.insert(user);
    }
    @Test
    public void delete(){
        //删除条件
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","马冬梅");//删除全部name为马冬梅的数据
        userMapper.deleteByMap(columnMap);
    }
    @Test
    public void deleteWapper(){
        /**删除条件
         个别参数说明:
         params : key为数据库字段名,value为字段值
         null2IsNull : 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
         */
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id",5);
        userMapper.delete(new QueryWrapper<User>().allEq(columnMap,true));
    }
    @Test
    public void deleteBatchIds(){
        List<Integer>lists = new ArrayList<>();
        lists.add(7);lists.add(8);lists.add(9);
        userMapper.deleteBatchIds(lists);
    }
    @Test
    public void select(){
        System.out.println(userMapper.selectOne(new QueryWrapper<User>().eq(false,"name","Jack")));
    }
}
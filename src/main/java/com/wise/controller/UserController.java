package com.wise.controller;


import com.wise.entity.User;
import com.wise.mapper.UserMapper;
import com.wise.validation.Code;
import com.wise.validation.MyException;
import com.wise.validation.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YsCy丶
 * @since 2018-11-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
        @PostMapping(value = "/update")
        Response<Boolean> update(User user){

        //todo 此处为模拟异常抛出
        if(true){
            throw new MyException("更新失败");
        }
        //todo 此处为模拟返回
        Response<Boolean> response = new Response<>();
        response.setCode(Code.SUCCESSED);
        response.setResult(true);
        return  response;
    }
    @PostMapping(value = "/add")
    Response<User> add(@Validated User user){

        //todo 此处为模拟返回
        Response<User> response = new Response<>();
        response.setCode(Code.SUCCESSED);
        response.setResult(new User());
        return  response;
    }

    @PostMapping("/findAll")
    public Response<List<User>>findAll(){
        Response<List<User>> response = new Response<>();
        List<User>list = userMapper.selectList(null);
        response.setCode(200);
        response.setMsg("查询全部成功");
        response.setResult(list);
        return response;
    }

}

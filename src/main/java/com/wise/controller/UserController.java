package com.wise.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wise.entity.User;
import com.wise.mapper.UserMapper;
import com.wise.service.IUserService;
import com.wise.validation.Code;
import com.wise.validation.MyException;
import com.wise.validation.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private IUserService userService;
        @PostMapping(value = "/update")
        Response<User> update(@RequestParam String name){
            User user = userService.getOne(new QueryWrapper<User>().eq("name",name));
            user.setName("小萝莉");
         boolean b = userService.update(user,new UpdateWrapper<User>().eq("id",user.getId()));
        Response<User> response = new Response<>();
            if (b){
                response.setCode(Code.SUCCESSED);
                User user1 = userService.getById(user.getId());
                response.setResult(user1);
            }
        return response;
    }
    @PostMapping(value = "/add")
    Response<User> add(@RequestBody User user){
        userService.save(user);
        //todo 此处为模拟返回
        Response<User> response = new Response<>();
        response.setCode(Code.SUCCESSED);
        response.setResult(new User());
        return  response;
    }

    @PostMapping("/findAll")
    public Response<List<User>>findAll(){
        Response<List<User>> response = new Response<>();
        List<User>list = userService.list(new QueryWrapper<User>().select("name"));
        response.setCode(200);
        response.setMsg("查询全部成功");
        response.setResult(list);
        return response;
    }

}

package com.wise.controller;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * student 前端控制器
 * </p>
 *
 * @author YsCy丶
 * @since 2018-11-12
 */
@RestController
//@RequestMapping("/equip")
public class StudentController {

    /*
     * http://192.168.3.200:8080/equip/add
     */
    @PostMapping(value = "/add")
    public String savebyjson(@RequestBody Map<String, String> map, HttpServletResponse resp) throws ParseException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String EQUIPMENT_ID;
        String sysEdition;
        String FACTORY_TIME;//ddddd
        if (map.containsKey("CPU_ID")) {
            EQUIPMENT_ID = map.get("CPU_ID");
            System.out.println("CPU_ID>>>" + EQUIPMENT_ID);
        }
        if (map.containsKey("Vender_Date")) {
            FACTORY_TIME = map.get("Vender_Date");
            System.out.println("工厂注册时间>>>" + FACTORY_TIME);
        }
        return "{\"status\":\"200\"}";
    }

    /*
     * json
     * http://192.168.3.200:8080/equip/update
     */
    @PostMapping(value = "/update")
    public String getjson(@RequestBody Map<String, String> map, HttpServletResponse resp) throws ParseException, IOException {
        String USER_TIME;
        String EQUIPMENT_ID;
        resp.setContentType("application/json;charset=utf-8");
        if (map.containsKey("CPU_ID")) {
            EQUIPMENT_ID = map.get("CPU_ID");
            System.out.println("CPU_ID>>>" + EQUIPMENT_ID);
        }
        if (map.containsKey("User_Date")) {
            USER_TIME = map.get("User_Date");
            System.out.println("用户注册时间>>>" + USER_TIME);
        }
        return "{\"status\":\"200\"}";
    }

    /*
     * json
     * http://192.168.3.200:8080/equip/update
     */
    @GetMapping(value = "/wangyilei")
    public String jsonTest(HttpServletResponse resp) throws ParseException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        return "{\"status\":\"小王你好\"}";
    }
}

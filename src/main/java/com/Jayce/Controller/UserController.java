package com.Jayce.Controller;

import com.Jayce.Model.User;
import com.Jayce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/2/24.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "insert.do")
    @ResponseBody
    public String insert(){
        User user = new User();
        user.setId("1");
        user.setName("黄伟杰");
        user.setPassword("jaycekon");
        userService.setUserToRedis(user);
        return "success";
    }


    @RequestMapping(value = "get.do")
    @ResponseBody
    public String get(){
        User user = userService.getUserFromRedis(1);
        return "success";
    }

}

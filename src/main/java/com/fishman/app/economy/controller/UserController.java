package com.fishman.app.economy.controller;

import com.fishman.app.economy.model.User;
import com.fishman.app.economy.service.UserService;
import com.fishman.app.economy.util.RespCodeUtil;
import com.fishman.app.economy.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hema on 16/9/21.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam(value="username",required=true) String username,
                           @RequestParam(value="password",required=true) String password,
                           @RequestParam(value="role",required=true) String role){
        User u = new User();
        u.setUsername(username);
        u = userService.getUser(u);
        if(u!=null){
            return RespCodeUtil.error(RespCodeUtil.e20002);
        }
        u = new User();
        u.setUsername(username);
        try {
            u.setPassword(PasswordUtil.encrypt(password,PasswordUtil.password,PasswordUtil.salt.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        u.setRole(Integer.parseInt(role));
        u.setCreTime(new Date());
        u.setUpdateTime(new Date());
        userService.save(u);
        return RespCodeUtil.success();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value="username",required=true) String username,
                        @RequestParam(value="password",required=true) String password){
        User u = new User();
        u.setUsername(username);
        u = userService.getUser(u);
        if(u!=null){
            try {
                String cipherPwd = PasswordUtil.encrypt(password,PasswordUtil.password,PasswordUtil.salt.getBytes());
                if(!cipherPwd.equals(u.getPassword())){
                    return RespCodeUtil.error(RespCodeUtil.e20001);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return RespCodeUtil.error(RespCodeUtil.e20000);
        }
        Map<String,User> data = new HashMap<String, User>();
        data.put("user",u);
        return RespCodeUtil.success(data);
    }
}

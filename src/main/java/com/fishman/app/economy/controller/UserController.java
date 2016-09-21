package com.fishman.app.economy.controller;

import com.fishman.app.economy.common.redis.RedisClientTemplate;
import com.fishman.app.economy.model.User;
import com.fishman.app.economy.service.UserService;
import com.fishman.app.economy.util.MD5Util;
import com.fishman.app.economy.util.RespCodeUtil;
import com.fishman.app.economy.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hema on 16/9/21.
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisClientTemplate redis;
    @ApiOperation(value = "/register", notes = "注册", response = Void.class)
    @RequestMapping(value = "/register",method = RequestMethod.POST)
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
        String accessToken = MD5Util.MD5(username+ System.currentTimeMillis());
        redis.set("accessToken-"+accessToken,u.getId()+"");
        redis.expire("accessToken-"+accessToken,1*24*60*60);//有效期一天
        Map<String,User> data = new HashMap<String, User>();
        u.setAccessToken(accessToken);
        data.put("user",u);
        return RespCodeUtil.success(data);
    }
}

package com.fishman.app.economy.controller;

import com.fishman.app.economy.model.User;
import com.fishman.app.economy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hema on 16/9/21.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        User u = userService.getUserById((long)1);
        System.out.print(u.getUsername());
    }
}

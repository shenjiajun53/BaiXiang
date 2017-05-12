package com.baixiang.controller;

import com.baixiang.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shenjj on 2017/5/4.
 */

@RestController
public class RouterController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/service/index");
        modelAndView.addObject("user", new User("shenjiajun", "123"));
        return modelAndView;
    }

    @RequestMapping(value = "/SignIn")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/service/signin");

        return modelAndView;
    }

    @RequestMapping(value = "/SignUp")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/index");

        return modelAndView;
    }
}

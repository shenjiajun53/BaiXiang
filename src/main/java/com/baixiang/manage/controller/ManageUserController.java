package com.baixiang.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shenjj on 2017/5/16.
 */

@RestController
public class ManageUserController {

    @RequestMapping(value = "/manage/sign_up")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_up");
        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_in")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_in");
        return modelAndView;
    }
}

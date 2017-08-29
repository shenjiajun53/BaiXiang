package com.baixiang.manageController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RouterController {
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_in")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_up")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/spider")
    public ModelAndView spider() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

}

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

    @RequestMapping(value = "/SignIn")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

    @RequestMapping(value = "/SignUp")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/index2");

        return modelAndView;
    }

    @RequestMapping(value = "/MyFollow")
    public ModelAndView myFollow() {
        ModelAndView modelAndView = new ModelAndView("/test");

        return modelAndView;
    }

    @RequestMapping(value = "/WriteBlog")
    public ModelAndView writeBlog() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");

        return modelAndView;
    }

    @RequestMapping(value = "/BlogDetail/*")
    public ModelAndView blogDetail() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");

        return modelAndView;
    }
}

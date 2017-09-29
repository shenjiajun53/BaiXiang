package com.baixiang.controller;

import com.baixiang.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ManageController {
    @RequestMapping(value = Urls.MANAGE, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_index");

        return modelAndView;
    }

    @RequestMapping(value = Urls.SIGN_IN)
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/user_index");

        return modelAndView;
    }

    @RequestMapping(value = Urls.SIGN_UP)
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/user_index");

        return modelAndView;
    }

    @RequestMapping(value = Urls.MANAGE_EDIT_MOVIE)
    public ModelAndView addMovie() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_index");

        return modelAndView;
    }

    @RequestMapping(value = Urls.MANAGE_EDIT_MOVIE_WITH_ID)
    public ModelAndView editMovie() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_index");

        return modelAndView;
    }

    @RequestMapping(value = Urls.MANAGE_SPIDER)
    public ModelAndView spider() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_index");

        return modelAndView;
    }

}

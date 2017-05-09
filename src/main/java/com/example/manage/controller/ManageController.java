package com.example.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shenjj on 2017/5/9.
 */

@RestController(value = "")
public class ManageController {

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_film", method = RequestMethod.GET)
    public ModelAndView editFile() {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_film");

        return modelAndView;
    }
}

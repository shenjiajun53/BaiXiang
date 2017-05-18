package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.User;
import com.baixiang.repository.MovieRepository;
import com.baixiang.repository.UserRepository;
import com.baixiang.service.UserService;
import com.baixiang.utils.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shenjj on 2017/5/9.
 */

@RestController(value = "")
public class ManageController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getAll();
        modelAndView.addObject("movieList", movieArrayList);
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_movie", method = RequestMethod.GET)
    public ModelAndView editFilm() {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_movie");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @RequestMapping(value = "/manage/sign_up")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_up");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_in")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_in");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

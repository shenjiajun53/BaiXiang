package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.User;
import com.baixiang.repository.MovieRepository;
import com.baixiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by shenjj on 2017/5/4.
 */

@RestController
public class RouterController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/service/index");
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getAll();
        modelAndView.addObject("movieList", movieArrayList);
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.User;
import com.baixiang.repository.MovieHibernateRepository;
import com.baixiang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by shenjj on 2017/5/9.
 */

@RestController(value = "")
public class ManageController {
    private static final Logger logger = LoggerFactory.getLogger(ManageController.class);
    @Autowired
    MovieHibernateRepository movieRepository;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getAll();
        modelAndView.addObject("movieList", movieArrayList);
        ArrayList<Movie> hotList = (ArrayList<Movie>) movieRepository.getHostest();
        modelAndView.addObject("hotList", hotList);
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @RequestMapping(value = "/manage/edit_movie", method = RequestMethod.GET)
    public ModelAndView editFilm(@RequestParam(value = "movieId", required = false) Integer movieId) {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_movie");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        if (null != movieId) {
            Movie movie = movieRepository.getById(movieId);
//            System.out.printf("movie=" + movie.toString());
            modelAndView.addObject("movie", movie);
        }
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

    @RequestMapping(value = "/manage/spider")
    public ModelAndView spider() {
        ModelAndView modelAndView = new ModelAndView("/manage/spider");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

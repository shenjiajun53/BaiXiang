package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.User;
import com.baixiang.repository.MovieRepository;
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
 * Created by shenjj on 2017/5/4.
 */

@RestController
public class RouterController {
    private static final Logger logger = LoggerFactory.getLogger(RouterController.class);
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserService userService;

    private static final int PAGE_SIZE = 3;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/service/index");
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getAll();
        logger.info(movieArrayList.toString());
        modelAndView.addObject("movieList", movieArrayList);
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/movie_list", method = RequestMethod.GET)
    public ModelAndView movieList(@RequestParam(value = "tag", required = true) String tag,
                                  @RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("/service/movie_list");
        if (null == page) {
            page = 1;
        }
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getByTag(tag, page - 1, PAGE_SIZE);
        logger.info(movieArrayList.toString());
        modelAndView.addObject("movieList", movieArrayList);
        int maxPage = (int) Math.ceil(((double) movieRepository.getSizeByTag(tag)) / PAGE_SIZE);  //进一
        logger.info("tag=" + tag + " maxPage=" + maxPage);
        modelAndView.addObject("maxPage", maxPage);
        ArrayList<Movie> hotList= (ArrayList<Movie>) movieRepository.getHostest();
        modelAndView.addObject("hotList", hotList);
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/movie_detail", method = RequestMethod.GET)
    public ModelAndView movieDetail(@RequestParam(value = "movieId", required = false) Integer movieId) {
        ModelAndView modelAndView = new ModelAndView("/service/movie_detail");
        User user = userService.getUserBySession();
        modelAndView.addObject("user", user);
        if (null != movieId) {
            Movie movie = movieRepository.getById(movieId);
            System.out.printf("movie=" + movie.toString());
            Long viewTimes = movie.getViewTimes();
            if (null == viewTimes) {
                viewTimes = 0L;
            }
            movie.setViewTimes(++viewTimes);
            movieRepository.update(movie);
            modelAndView.addObject("movie", movie);
        } else {
            modelAndView.setViewName("/default/error_page");
        }
        return modelAndView;
    }
}

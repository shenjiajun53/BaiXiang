package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.User;
import com.baixiang.service.MovieService;
import com.baixiang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/service/index");
        Pageable pageable=new PageRequest(0,20);
        Page<Movie> movieArrayList = movieService.getByTag("推荐", pageable);
        logger.info(movieArrayList.toString());
        modelAndView.addObject("movieList", movieArrayList);
        ArrayList<Movie> hotList = (ArrayList<Movie>) movieService.getHostest();
        modelAndView.addObject("hotList", hotList);
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
        Pageable pageable=new PageRequest(page-1,PAGE_SIZE);

        Page<Movie> movieArrayList = movieService.getByTag(tag, pageable);
        logger.info(movieArrayList.toString());
        modelAndView.addObject("movieList", movieArrayList);
        int maxPage = (int) Math.ceil(((double) movieService.getSizeByTag(tag)) / PAGE_SIZE);  //进一
        logger.info("tag=" + tag + " maxPage=" + maxPage);
        modelAndView.addObject("maxPage", maxPage);
        ArrayList<Movie> hotList = (ArrayList<Movie>) movieService.getHostest();
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
            Movie movie = movieService.getById(movieId);
//            System.out.printf("movie=" + movie.toString());
            Long viewTimes = movie.getViewTimes();
            if (null == viewTimes) {
                viewTimes = 0L;
            }
            movie.setViewTimes(++viewTimes);
            movieService.save(movie);
            modelAndView.addObject("movie", movie);
        } else {
            modelAndView.setViewName("/default/error_page");
        }
        return modelAndView;
    }
}

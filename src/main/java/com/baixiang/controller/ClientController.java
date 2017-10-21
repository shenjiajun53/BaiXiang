package com.baixiang.controller;

import com.baixiang.model.jpa.Movie;
import com.baixiang.model.jpa.User;
import com.baixiang.model.response.MovieWrapBean;
import com.baixiang.model.response.UserBean;
import com.baixiang.service.DoubanMovieService;
import com.baixiang.service.MovieService;
import com.baixiang.service.UserService;
import com.baixiang.utils.Urls;
import org.apache.http.util.TextUtils;
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

//@Controller
@RestController
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoubanMovieService doubanMovieService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping(value = Urls.BASE_URL, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/service/index");
        Pageable pageable = new PageRequest(0, 20);
        Page<Movie> movieArrayList = movieService.getByTag("推荐", pageable);
//        logger.info(movieArrayList.toString());
        modelAndView.addObject("movieList", movieArrayList);
        ArrayList<Movie> hotList = (ArrayList<Movie>) movieService.getHostest();
        modelAndView.addObject("hotList", hotList);
        modelAndView.addObject("user", userService.getUserBeanBySession());
        return modelAndView;
    }

    @RequestMapping(value = Urls.MOVIE_LIST, method = RequestMethod.GET)
    public ModelAndView movieList(@RequestParam(value = "tag", required = false) String tag,
                                  @RequestParam(value = "actor", required = false) String actor,
                                  @RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("/service/movie_list");
        if (null == page) {
            page = 1;
        }
        Pageable pageable = new PageRequest(page - 1, PAGE_SIZE);
        Page<Movie> movieArrayList = null;
        int maxPage = 0;
        if (!TextUtils.isEmpty(tag)) {
            movieArrayList = movieService.getByTag(tag, pageable);
            if (null != movieArrayList) {
                logger.info(movieArrayList.toString());
            }
            maxPage = (int) Math.ceil(((double) movieService.getSizeByTag(tag)) / PAGE_SIZE);  //进一
        } else if (!TextUtils.isEmpty(actor)) {
            movieArrayList = movieService.getByActor(actor, pageable);
            if (null != movieArrayList) {
                logger.info(movieArrayList.toString());
            }
            maxPage = (int) Math.ceil(((double) movieService.getSizeByActor(actor)) / PAGE_SIZE);  //进一
        }
        modelAndView.addObject("movieList", movieArrayList);
        modelAndView.addObject("maxPage", maxPage);
        ArrayList<Movie> hotList = (ArrayList<Movie>) movieService.getHostest();
        modelAndView.addObject("hotList", hotList);
        modelAndView.addObject("user", userService.getUserBeanBySession());
        return modelAndView;
    }

    @RequestMapping(value = Urls.MOVIE_DETAIL, method = RequestMethod.GET)
    public ModelAndView movieDetail(@RequestParam(value = "movieId", required = false) Integer movieId) {
        ModelAndView modelAndView = new ModelAndView("/service/movie_detail");
        modelAndView.addObject("user", userService.getUserBeanBySession());
        if (null != movieId) {
            Movie movie = movieService.getById(movieId);
//            System.out.printf("movie=" + movie.toString());
            Long viewTimes = movie.getViewTimes();
            if (null == viewTimes) {
                viewTimes = 0L;
            }
            movie.setViewTimes(++viewTimes);
            movieService.save(movie);
            modelAndView.addObject("movie", new MovieWrapBean(movie, doubanMovieService.getById(movie.getDoubanId())));
//            modelAndView.addObject("movie", movie);
//            modelAndView.addObject("doubanInfo", doubanMovieService.getById(movie.getDoubanId()));
        } else {
            modelAndView.setViewName("/default/error_page");
        }
        return modelAndView;
    }


//    @RequestMapping(value = "/movie_detail", method = RequestMethod.GET)
//    public String movieDetail(Model model,@RequestParam(value = "movieId", required = false) Integer movieId) {
//        model.addAttribute("user", userService.getUserBeanBySession());
//        if (null != movieId) {
//            Movie movie = movieService.getById(movieId);
////            System.out.printf("movie=" + movie.toString());
//            Long viewTimes = movie.getViewTimes();
//            if (null == viewTimes) {
//                viewTimes = 0L;
//            }
//            movie.setViewTimes(++viewTimes);
//            movieService.save(movie);
//            model.addAttribute("movie", movie);
//        } else {
//            return "/default/error_page";
//        }
//        return "/service/movie_detal_f";
//    }
}

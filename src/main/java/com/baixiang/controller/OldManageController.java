package com.baixiang.controller;

import com.baixiang.service.MovieService;
import com.baixiang.service.UserService;
import com.baixiang.spider.BtTianTang.BtTianTangProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shenjj on 2017/5/9.
 */

@RestController(value = "")
public class OldManageController {
    private static final Logger logger = LoggerFactory.getLogger(OldManageController.class);
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    BtTianTangProcessor btTianTangProcessor;


//    @RequestMapping(value = "/manage", method = RequestMethod.GET)
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView("/manage/index");
//        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieService.getNewest();
//        modelAndView.addObject("movieList", movieArrayList);
//        ArrayList<Movie> hotList = (ArrayList<Movie>) movieService.getHostest();
//        modelAndView.addObject("hotList", hotList);
//        User user = userService.getUserBySession();
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }


//    @RequestMapping(value = "/manage/edit_movie", method = RequestMethod.GET)
//    public ModelAndView editFilm(@RequestParam(value = "movieId", required = false) Integer movieId) {
//        ModelAndView modelAndView = new ModelAndView("/manage/edit_movie");
//        User user = userService.getUserBySession();
//        modelAndView.addObject("user", user);
//        if (null != movieId) {
//            Movie movie = movieService.getById(movieId);
////            System.out.printf("movie=" + movie.toString());
//            modelAndView.addObject("movie", movie);
//        }
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/manage/sign_up")
//    public ModelAndView signUp() {
//        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_up");
//        User user = userService.getUserBySession();
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/manage/sign_in")
//    public ModelAndView signIn() {
//        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_in");
//        User user = userService.getUserBySession();
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

//    @RequestMapping(value = "/manage/spider")
//    public ModelAndView spider() {
//        ModelAndView modelAndView = new ModelAndView("/manage/spider");
//        User user = userService.getUserBySession();
//        modelAndView.addObject("user", user);
//        modelAndView.addObject("btSpiderRunning", btTianTangProcessor.isRunning());
//        logger.info("btSpiderRunning="+btTianTangProcessor.isRunning());
//        return modelAndView;
//    }
}

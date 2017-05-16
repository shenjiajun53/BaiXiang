package com.baixiang.manage.controller;

import com.baixiang.model.Movie;
import com.baixiang.repository.MovieRepository;
import com.baixiang.utils.FileUtil;
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


    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/manage/index");

        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieRepository.getAll();
        modelAndView.addObject("movieList", movieArrayList);
        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_movie", method = RequestMethod.GET)
    public ModelAndView editFilm() {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_movie");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_movie", method = RequestMethod.POST)
    public ModelAndView postMovie(@RequestParam(value = "movieInfo") String movieInfo,
                                  @RequestParam("poster") MultipartFile poster) {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_movie");

        System.out.printf("filename=" + poster.getOriginalFilename());

        Movie movie = new Movie(movieInfo, movieInfo);

        if (!poster.isEmpty()) {
            try {
                String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                String filePath = "/files/movie/posters/";
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + poster.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                poster.transferTo(file);
                movie.setPoster(filePath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        movieRepository.save(movie);

        return modelAndView;
    }
}

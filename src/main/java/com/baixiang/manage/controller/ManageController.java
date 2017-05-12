package com.baixiang.manage.controller;

import com.baixiang.dao.MovieDao;
import com.baixiang.model.MovieBean;
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

        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_film", method = RequestMethod.GET)
    public ModelAndView editFilm() {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_film");

        return modelAndView;
    }

    @RequestMapping(value = "/manage/edit_film", method = RequestMethod.POST)
    public ModelAndView postFilm(@RequestParam(value = "filmInfo") String filmInfo,
                                 @RequestParam("poster") MultipartFile poster) {
        ModelAndView modelAndView = new ModelAndView("/manage/edit_film");

        System.out.printf("filename=" + poster.getOriginalFilename());

        MovieBean movieBean = new MovieBean(filmInfo, filmInfo);

        if (!poster.isEmpty()) {
            try {
                String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                String filePath = "/files/film/posters/";
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + poster.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                poster.transferTo(file);
                movieBean.setPoster(filePath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        movieRepository.save(movieBean);

        return modelAndView;
    }
}

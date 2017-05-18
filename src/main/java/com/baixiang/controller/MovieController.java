package com.baixiang.controller;

import com.baixiang.model.Movie;
import com.baixiang.model.RedirectBean;
import com.baixiang.model.Response;
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
 * Created by Administrator on 2017/5/17.
 */

@RestController
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/api/edit_movie", method = RequestMethod.POST)
    public Response<RedirectBean> postMovie(@RequestParam(value = "movieInfo") String movieInfo,
                                            @RequestParam("poster") MultipartFile poster,
                                            @RequestParam("screenShotList") MultipartFile[] screenShotList) {
        Movie movie = new Movie(movieInfo, movieInfo);

        if (!poster.isEmpty()) {
            try {
                System.out.printf("filename=" + poster.getOriginalFilename());
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
        System.out.printf("222 filename=" + screenShotList.length);
        if (screenShotList.length > 0) {
            for (int i = 0; i < screenShotList.length; i++) {
                MultipartFile screenShot = screenShotList[i];
                if (!screenShot.isEmpty()) {
                    try {
                        System.out.printf("222 filename=" + screenShot.getOriginalFilename());
                        String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                        String filePath = "/files/movie/screenShots/";
                        FileUtil.createOrExistsDir(staticPath + filePath);

                        String fileName = System.currentTimeMillis() + "-" + screenShot.getOriginalFilename();
                        File file = new File(staticPath + filePath + fileName);
                        screenShot.transferTo(file);
//                        movie.setPoster(filePath + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        movieRepository.save(movie);
        RedirectBean redirectBean;
        if (movie.getId() != 0) {
            redirectBean = new RedirectBean(1, "/manage");
        } else {
            redirectBean = new RedirectBean(2, "");

        }
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }
}

package com.baixiang.controller;

import com.baixiang.model.*;
import com.baixiang.repository.MovieRepository;
import com.baixiang.utils.FileUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
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
    public Response<RedirectBean> postMovie(@RequestParam(value = "movieId", required = false) String movieId,
                                            @RequestParam(value = "movieTitle") String movieTitle,
                                            @RequestParam(value = "movieInfo") String movieInfo,
                                            @RequestParam(value = "poster", required = false) MultipartFile poster,
                                            @RequestParam(value = "screenShotList", required = false) MultipartFile[] screenShotList,
                                            @RequestParam(value = "torrentList", required = false) MultipartFile[] torrentList) {

        Movie movie;
        if (movieId != null && !movieId.isEmpty() && !movieId.equals("null")) {
            movie = movieRepository.getById(Long.parseLong(movieId));
            movie.setMovieName(movieTitle);
            movie.setMovieInfo(movieInfo);
        } else {
            movie = new Movie(movieTitle, movieInfo);
        }


        if (null != poster) {
            movie.setPoster(saveFile(poster, "/files/movie/posters/"));
        }
        if (screenShotList.length > 0) {
            for (int i = 0; i < screenShotList.length; i++) {
                MultipartFile screenShot = screenShotList[i];
                MovieImage movieImage = new MovieImage();
                movieImage.setUrl(saveFile(screenShot, "/files/movie/screenShots/"));
//                movieImage.setImageName(saveFile(screenShot, "/files/movie/screenShots/"));
                movie.addScreenShot(movieImage);
            }
        }
        if (torrentList.length > 0) {
            for (int i = 0; i < torrentList.length; i++) {
                MultipartFile screenShot = torrentList[i];
                MovieTorrent movieTorrent = new MovieTorrent();
                movieTorrent.setFilePath(saveFile(screenShot, "/files/movie/torrents/"));
//                movieImage.setImageName(saveFile(screenShot, "/files/movie/screenShots/"));
                movie.addTorrent(movieTorrent);
            }
        }
        if (movieId != null && !movieId.isEmpty() && !movieId.equals("null")) {
            movieRepository.update(movie);
        } else {
            movieRepository.save(movie);
        }
        RedirectBean redirectBean;
        if (movie.getId() != 0) {
            redirectBean = new RedirectBean(1, "/manage");
        } else {
            redirectBean = new RedirectBean(2, "");

        }
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }

    private String saveFile(MultipartFile multipartFile, String filePath) {
        if (null != multipartFile && !multipartFile.isEmpty()) {
            try {
                System.out.printf("filename=" + multipartFile.getOriginalFilename());
                String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                multipartFile.transferTo(file);
                return filePath + fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

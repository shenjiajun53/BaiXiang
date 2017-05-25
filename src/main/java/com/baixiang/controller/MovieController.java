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

    private static final String STATIC_PATH = "/src/main/webapp";
    private static final String POSTER_PATH = "/files/movie/posters/";
    private static final String SCREEN_SHOT_PATH = "/files/movie/screenShots/";
    private static final String TORRENT_PATH = "/files/movie/torrents/";

    @RequestMapping(value = "/api/edit_movie", method = RequestMethod.POST)
    public Response<RedirectBean> postMovie(@RequestParam(value = "movieId", required = false) String movieId,
                                            @RequestParam(value = "movieTitle") String movieTitle,
                                            @RequestParam(value = "movieInfo") String movieInfo,
                                            @RequestParam(value = "poster", required = false) MultipartFile poster,
                                            @RequestParam(value = "screenShotList", required = false) MultipartFile[] screenShotList,
                                            @RequestParam(value = "torrentList", required = false) MultipartFile[] torrentList,
                                            @RequestParam(value = "movieDate", required = false) String movieDate,
                                            @RequestParam(value = "tagList", required = false) String[] tagList) {

        Movie movie;
        if (movieId != null && !movieId.isEmpty() && !movieId.equals("null")) {
            movie = movieRepository.getById(Long.parseLong(movieId));
            movie.setMovieName(movieTitle);
            movie.setMovieInfo(movieInfo);
        } else {
            movie = new Movie(movieTitle, movieInfo);
        }

        if (null != movieDate) {
            movie.setReleaseDate(movieDate);
        }

        if (null != poster) {
            String posterFileName = saveFile(poster, POSTER_PATH);
            if (!posterFileName.equals(null)) {
                movie.setPoster(POSTER_PATH + posterFileName);
            }
        }
        if (screenShotList.length > 0) {
            for (int i = 0; i < screenShotList.length; i++) {
                MultipartFile screenShot = screenShotList[i];
                MovieImage movieImage = new MovieImage();
                String imageFileName = saveFile(screenShot, SCREEN_SHOT_PATH);
                if (!imageFileName.equals(null)) {
                    movieImage.setUrl(SCREEN_SHOT_PATH + imageFileName);
                    movieImage.setImageName(imageFileName);
                }
                movie.addScreenShot(movieImage);
            }
        }
        if (torrentList.length > 0) {
            for (int i = 0; i < torrentList.length; i++) {
                MultipartFile torrent = torrentList[i];
                MovieTorrent movieTorrent = new MovieTorrent();
                String torrentFileName = saveFile(torrent, TORRENT_PATH);
                if (!torrentFileName.equals(null)) {
                    movieTorrent.setFilePath(TORRENT_PATH + torrentFileName);
                    movieTorrent.setTorrentName(torrentFileName);
                }
                movie.addTorrent(movieTorrent);
            }
        }
        if (tagList.length > 0) {
            for (int i = 0; i < tagList.length; i++) {
                movie.addTag(tagList[i]);
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
                String staticPath = System.getProperty("user.dir") + STATIC_PATH;
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                multipartFile.transferTo(file);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

package com.baixiang.clientController;

import com.baixiang.model.*;
import com.baixiang.service.MovieService;
import com.baixiang.utils.FileUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.baixiang.utils.FileUtil.*;

/**
 * Created by Administrator on 2017/5/17.
 */

@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    MovieService movieService;


    @RequestMapping(value = "/api/movieDetail", method = RequestMethod.POST)
    public Response<Movie> getMovieDetail(@RequestParam(value = "movieId") Long movieId) {
        Movie movie = movieService.getById(movieId);
        logger.info("movie=" + movie.toString());
        return new Response<>(movie, null);
    }

    @RequestMapping(value = "/api/delete_movie", method = RequestMethod.POST)
    public Response<BaseBean> deleteMovie(@RequestParam(value = "movieId") Long movieId) {
        movieService.delete(movieId);
        return new Response<>(new BaseBean(1), null);
    }

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
        if (movieId != null && !movieId.isEmpty() && !movieId.equals("undefined")) {
            movie = movieService.getById(Long.parseLong(movieId));
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
        if (null != tagList && tagList.length > 0) {
            if (movie.getMovieTagSet().size() > 0) {
                movie.getMovieTagSet().clear();
            }
            for (int i = 0; i < tagList.length; i++) {
                movie.addTag(tagList[i]);
            }
        }

        movie = movieService.save(movie);
        RedirectBean redirectBean;
        if (movie.getId() != 0) {
            redirectBean = new RedirectBean(1, "/manage");
        } else {
            redirectBean = new RedirectBean(2, "");
        }
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }

    @RequestMapping(value = "/api/search_movie", method = RequestMethod.POST)
    private Response<ArrayList<Movie>> searchMovie(@RequestParam(value = "searchStr") String searchStr) {
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieService.getIncludeName(searchStr);
        Response<ArrayList<Movie>> response = new Response<>(movieArrayList, null);
        logger.info("movie size=" + movieArrayList.size());
        return response;
    }

    @RequestMapping(value = "/api/getRecommendMovies", method = RequestMethod.GET)
    private Response<List<Movie>> getRecommendMovie() {
        Pageable pageable = new PageRequest(0, 20);
        List<Movie> moviePage = movieService.getNewest();
        Response<List<Movie>> response = new Response<>(moviePage, null);
        return response;
    }

    private String saveFile(MultipartFile multipartFile, String dirPath) {
        if (null != multipartFile && !multipartFile.isEmpty()) {
            try {
                System.out.printf("filename=" + multipartFile.getOriginalFilename());
                String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
                String filePath = FileUtil.getFilePath(dirPath, fileName);
                File file = new File(filePath);
                multipartFile.transferTo(file);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

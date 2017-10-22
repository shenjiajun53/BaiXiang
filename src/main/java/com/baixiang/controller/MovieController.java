package com.baixiang.controller;

import com.baixiang.config.PropertiesConfig;
import com.baixiang.model.jpa.*;
import com.baixiang.model.response.BaseBean;
import com.baixiang.model.response.Response;
import com.baixiang.service.ActorService;
import com.baixiang.service.MovieService;
import com.baixiang.service.TagService;
import com.baixiang.service.TorrentService;
import com.baixiang.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.baixiang.config.PropertiesConfig.POSTER_PATH;
import static com.baixiang.config.PropertiesConfig.SCREEN_SHOT_PATH;
import static com.baixiang.config.PropertiesConfig.TORRENT_PATH;
import static com.baixiang.utils.FileUtil.*;
import static com.baixiang.utils.Urls.*;

/**
 * Created by Administrator on 2017/5/17.
 */

@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    MovieService movieService;

    @Autowired
    TagService tagService;

    @Autowired
    ActorService actorService;

    @Autowired
    TorrentService torrentService;
    @Autowired
    PropertiesConfig propertiesConfig;


    @RequestMapping(value = API_MOVIE_DETAIL, method = RequestMethod.POST)
    public Response<Movie> getMovieDetail(@RequestParam(value = "movieId") Long movieId) {
        Movie movie = movieService.getById(movieId);
        logger.info("movie=" + movie.toString());
        return new Response<>(movie, null);
    }

    @RequestMapping(value = API_DELETE_MOVIE, method = RequestMethod.POST)
    public Response<BaseBean> deleteMovie(@RequestParam(value = "movieId") Long movieId) {
        movieService.delete(movieId);
        return new Response<>(new BaseBean(1), null);
    }

    @RequestMapping(value = API_DELETE_TORRENT, method = RequestMethod.POST)
    public Response<BaseBean> deleteTorrent(
            @RequestParam(value = "movieId") Long movieId,
            @RequestParam(value = "torrentId") Long torrentId) {
        Movie movie = movieService.getById(movieId);
        for (MovieTorrent movieTorrent : movie.getMovieTorrents()) {
            if (movieTorrent.getId() == torrentId) {
                movie.removTorrent(movieTorrent);
                torrentService.delete(torrentId);
                return new Response<>(new BaseBean(1), null);
            }
        }
        return new Response<>(new BaseBean(200), null);
    }

    @RequestMapping(value = API_EDIT_MOVIE, method = RequestMethod.POST)
    public Response postMovie(@RequestParam(value = "movieId", required = false) String movieId,
                              @RequestParam(value = "movieTitle") String movieTitle,
                              @RequestParam(value = "movieInfo") String movieInfo,
                              @RequestParam(value = "poster", required = false) MultipartFile poster,
                              @RequestParam(value = "screenShotList", required = false) MultipartFile[] screenShotList,
                              @RequestParam(value = "torrentList", required = false) MultipartFile[] torrentList,
                              @RequestParam(value = "movieDate", required = false) String movieDate,
                              @RequestParam(value = "tagList", required = false) String[] tagList,
                              @RequestParam(value = "actorList", required = false) String[] actorList) {

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

            for (String tagName : tagList) {
                MovieTag movieTag = tagService.getMovieTagUnique(tagName);
                movie.addTag(movieTag);
            }
        }
        if (null != actorList && actorList.length > 0) {
            if (movie.getActorSet().size() > 0) {
                movie.getActorSet().clear();
            }

            for (String actorName : actorList) {
                Actor actor = actorService.getActorUnique(actorName);
                movie.addActor(actor);
            }
        }

        movie = movieService.save(movie);
        BaseBean baseBean;
        if (movie.getId() != 0) {
            baseBean = new BaseBean(1);
        } else {
            baseBean = new BaseBean(2);
        }
        return new Response<>(baseBean, null);
    }

    @RequestMapping(value = API_SEARCH_MOVIE, method = RequestMethod.POST)
    private Response<ArrayList<Movie>> searchMovie(@RequestParam(value = "searchStr") String searchStr) {
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movieService.getIncludeNameFirst10(searchStr);
        Response<ArrayList<Movie>> response = new Response<>(movieArrayList, null);
        logger.info("movie size=" + movieArrayList.size());
        return response;
    }

    //    @RequestMapping(value = "/api/getRecommendMovies", method = RequestMethod.GET)
    @RequestMapping(value = API_GET_RECOMMEND_MOVIES, method = RequestMethod.GET)
    private Response<List<Movie>> getRecommendMovie() {
        Pageable pageable = new PageRequest(0, 20);
        List<Movie> moviePage = movieService.getNewest();
        return new Response<>(moviePage, null);
    }

    @RequestMapping(value = API_GET_ALL_TAGS, method = RequestMethod.POST)
    private Response getAllTags() {
        List<MovieTag> movieTagList = tagService.findAll();
        return new Response<>(movieTagList, null);
    }

    @RequestMapping(value = API_SEARCH_ACTOR, method = RequestMethod.POST)
    private Response searchActor(@RequestParam(value = "searchStr") String searchStr) {
        ArrayList<Actor> arrayList = (ArrayList<Actor>) actorService.getActorIncludeName(searchStr);
        return new Response<>(arrayList, null);
    }

    private String saveFile(MultipartFile multipartFile, String dirPath) {
        if (null != multipartFile && !multipartFile.isEmpty()) {
            try {
                System.out.printf("filename=" + multipartFile.getOriginalFilename());
                String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
                String filePath = FileUtil.getFilePath(propertiesConfig.getRootPath(), dirPath, fileName);
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

package com.baixiang.utils;

public final class Urls {
    //url
    public final static String BASE_URL = "/";
    public final static String SIGN_IN = "/user/sign_in";
    public final static String SIGN_UP = "/user/sign_up";
    public final static String SIGN_OUT = "/user/sign_out";
    public final static String MANAGE = "/manage";
    public final static String MANAGE_SETTINGS = "/manage/Settings";
    public final static String MANAGE_EDIT_MOVIE = "/manage/edit_movie/";
    public final static String MANAGE_EDIT_MOVIE_WITH_ID = "/manage/edit_movie/*";
    public final static String MANAGE_SPIDER = "/manage/spider";

    //api
    public final static String API_GET_USER_INFO = "/api/getUserInfo";
    public final static String API_USER_SIGN_IN = "/api/signIn";
    public final static String API_USER_SIGN_UP = "/api/signUp";
    public final static String API_USER_SIGN_OUT = "/api/SignOut";
    public final static String API_GET_RECOMMEND_MOVIES = "/api/getRecommendMovies";
    public final static String API_GET_ALL_TAGS = "/api/getAllTags";
    public final static String API_MOVIE_DETAIL = "/api/movieDetail";
    public final static String API_DELETE_MOVIE = "/api/delete_movie";
    public final static String API_DELETE_TORRENT = "/api/delete_torrent";
    public final static String API_EDIT_MOVIE = "/api/edit_movie";
    public final static String API_GET_SPIDER_STATUS = "/api/manage/getSpiderStatus";
    public final static String API_START_SPIDER_BT = "/api/start_spider_bt";
    public final static String API_STOP_SPIDER_BT = "/api/stop_spider_bt";
    public final static String API_START_SPIDER_HUA = "/api/start_spider_taohua";
    public final static String API_STOP_SPIDER_HUA = "/api/stop_spider_taohua";
    public final static String API_START_DOUBAN_PATCH = "/api/start_douban_patch";
    public final static String API_STOP_DOUBAN_PATCH = "/api/stop_douban_patch";
    public final static String API_SEARCH_MOVIE = "/api/search_movie";
    public final static String API_SEARCH_ACTOR = "/api/search_actor";

    //豆瓣api
    public final static String DOUBAN_BASE_URL = "https://api.douban.com";
    //影片信息：  /v2/movie/subject/1764796
    public final static String DOUBAN_GET_MOVIE_INFO = DOUBAN_BASE_URL + "/v2/movie/subject/%s";
    public final static String DOUBAN_GET_MOVIE_PHOTOS = DOUBAN_BASE_URL + "/v2/movie/subject/%s/photos";
    //评论
    public final static String DOUBAN_GET_MOVIE_REVIEWS = DOUBAN_BASE_URL + "/v2/movie/subject/%s/reviews";
    //短评
    public final static String DOUBAN_GET_MOVIE_COMMENTS = DOUBAN_BASE_URL + "/v2/movie/subject/%s/comments";
    public final static String DOUBAN_GET_ACTOR_INFO = DOUBAN_BASE_URL + "";
    public final static String DOUBAN_SEARCH_MOVIE = DOUBAN_BASE_URL + "";
    //正在热映
    public final static String DOUBAN_MOVIE_IN_THEATER = DOUBAN_BASE_URL + "";
    public final static String DOUBAN_TOP_250 = DOUBAN_BASE_URL + "/v2/movie/top250";
}

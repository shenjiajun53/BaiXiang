import UrlUtil from "./UrlUtil";

// let CONTEXT_PATH = exports.CONTEXT_PATH = new UrlUtil().getContextPath();
//release
// let CONTEXT_PATH = exports.CONTEXT_PATH = "/baixiang";
//debug
let CONTEXT_PATH = exports.CONTEXT_PATH = "";
//url
exports.BASE_URL = CONTEXT_PATH + "/";
exports.MOVIE_LIST = CONTEXT_PATH + "/movie_list";
exports.MOVIE_DETAIL = CONTEXT_PATH + "/movie_detail";
exports.SIGN_IN = CONTEXT_PATH + "/user/sign_in";
exports.SIGN_UP = CONTEXT_PATH + "/user/sign_up";
exports.SIGN_OUT = CONTEXT_PATH + "/user/sign_out";
exports.MANAGE = CONTEXT_PATH + "/manage";
exports.MANAGE_SETTINGS = CONTEXT_PATH + "/manage/Settings";
exports.MANAGE_EDIT_MOVIE = CONTEXT_PATH + "/manage/edit_movie/";
exports.MANAGE_EDIT_MOVIE_WITH_ID = CONTEXT_PATH + "/manage/edit_movie/:movieId";
exports.MANAGE_SPIDER = CONTEXT_PATH + "/manage/spider";

//api
exports.API_GET_USER_INFO = CONTEXT_PATH + "/api/getUserInfo";
exports.API_USER_SIGN_IN = CONTEXT_PATH + "/api/signIn";
exports.API_USER_SIGN_UP = CONTEXT_PATH + "/api/signUp";
exports.API_USER_SIGN_OUT = CONTEXT_PATH + "/api/SignOut";
exports.API_GET_RECOMMEND_MOVIES = CONTEXT_PATH + "/api/getRecommendMovies";
exports.API_GET_LAST_CHANGE_MOVIES = CONTEXT_PATH + "/api/getLastChangeMovies";
exports.API_GET_ALL_TAGS = CONTEXT_PATH + "/api/getAllTags";
exports.API_MOVIE_DETAIL = CONTEXT_PATH + "/api/movieDetail";
exports.API_DELETE_MOVIE = CONTEXT_PATH + "/api/delete_movie";
exports.API_DELETE_TORRENT = CONTEXT_PATH + "/api/delete_torrent";
exports.API_EDIT_MOVIE = CONTEXT_PATH + "/api/edit_movie";
exports.API_UPLOAD_IMAGE = CONTEXT_PATH + "/api/upload_image";
exports.API_GET_SPIDER_STATUS = CONTEXT_PATH + "/api/manage/getSpiderStatus";
exports.API_START_SPIDER_BT = CONTEXT_PATH + "/api/start_spider_bt";
exports.API_STOP_SPIDER_BT = CONTEXT_PATH + "/api/stop_spider_bt";
exports.API_START_SPIDER_HUA = CONTEXT_PATH + "/api/start_spider_taohua";
exports.API_STOP_SPIDER_HUA = CONTEXT_PATH + "/api/stop_spider_taohua";
exports.API_START_DOUBAN_PATCH = CONTEXT_PATH + "/api/start_douban_patch";
exports.API_STOP_DOUBAN_PATCH = CONTEXT_PATH + "/api/stop_douban_patch";
exports.API_SEARCH_MOVIE = CONTEXT_PATH + "/api/search_movie";
exports.API_SEARCH_ACTOR = CONTEXT_PATH + "/api/search_actor";

//export function getContextPath() {
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0, index + 1);
//     return result;
// }
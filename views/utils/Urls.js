import UrlUtil from "./UrlUtil";

//url
exports.BASE_URL=new UrlUtil().getContextPath()+"/";
exports.SIGN_IN = new UrlUtil().getContextPath() + "/user/sign_in";
exports.SIGN_UP = new UrlUtil().getContextPath() + "/user/sign_up";
exports.SIGN_OUT = new UrlUtil().getContextPath() + "/user/sign_out";
exports.MANAGE = new UrlUtil().getContextPath() + "/manage";
exports.MANAGE_SETTINGS = new UrlUtil().getContextPath() + "/manage/Settings";
exports.MANAGE_EDIT_MOVIE = new UrlUtil().getContextPath() + "/manage/edit_movie/";
exports.MANAGE_EDIT_MOVIE_WITH_ID = new UrlUtil().getContextPath() + "/manage/edit_movie/:movieId";
exports.MANAGE_SPIDER = new UrlUtil().getContextPath() + "/manage/spider";

//api
exports.API_GET_USER_INFO = new UrlUtil().getContextPath() + "/api/getUserInfo";
exports.API_USER_SIGN_IN = new UrlUtil().getContextPath() + "/api/signIn";
exports.API_USER_SIGN_UP = new UrlUtil().getContextPath() + "/api/signUp";
exports.API_USER_SIGN_OUT = new UrlUtil().getContextPath() + "/api/SignOut";
exports.API_GET_RECOMMEND_MOVIES = new UrlUtil().getContextPath() + "/api/getRecommendMovies";
exports.API_MOVIE_DETAIL = new UrlUtil().getContextPath() + "/api/movieDetail";
exports.API_DELETE_MOVIE = new UrlUtil().getContextPath() + "/api/delete_movie";
exports.API_EDIT_MOVIE = new UrlUtil().getContextPath() + "/api/edit_movie";
exports.API_GET_SPIDER_STATUS = new UrlUtil().getContextPath() + "/api/manage/getSpiderStatus";
exports.API_START_SPIDER_BT = new UrlUtil().getContextPath() + "/api/start_spider_bt";
exports.API_STOP_SPIDER_BT = new UrlUtil().getContextPath() + "/api/stop_spider_bt";
exports.API_START_SPIDER_HUA = new UrlUtil().getContextPath() + "/api/start_spider_taohua";
exports.API_STOP_SPIDER_HUA = new UrlUtil().getContextPath() + "/api/stop_spider_taohua";

//export function getContextPath() {
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0, index + 1);
//     return result;
// }
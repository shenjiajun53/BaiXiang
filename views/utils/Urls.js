import UrlUtil from "./UrlUtil";

// export let getUserInfo = getContextPath() + "/api/getUserInfo";
let baseUrl= exports.baseUrl=new UrlUtil().getContextPath();

let signIn = exports.signIn = new UrlUtil().getContextPath() + "/user/sign_in";
let signUp = exports.signUp = new UrlUtil().getContextPath() + "/user/sign_up";
exports.signOut = new UrlUtil().getContextPath() + "/user/sign_out";
let manage = exports.manage = new UrlUtil().getContextPath() + "/manage";

let getUserInfo = exports.getUserInfo = new UrlUtil().getContextPath() + "/api/getUserInfo";


// function getContextPath() {
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0, index + 1);
//     return result;
// }
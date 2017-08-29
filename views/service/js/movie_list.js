/**
 * Created by shenjj on 2017/6/1.
 */
// import UrlUtil from "../utils/UrlUtil";

function getQueryString(paramName) {
    let reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
let tagName = getQueryString("tag");
let tagZHName;
switch (tagName) {
    case "New":
        tagZHName = "最新";
        break;
    case "Animation":
        tagZHName = "动画";
        break;
    case "Action":
        tagZHName = "动作";
        break;
    case "Science":
        tagZHName = "科幻";
        break;
    case "American":
        tagZHName = "美剧";
        break;
    case "Japanese":
        tagZHName = "日剧";
        break;
    default:
        break;
}
new Vue({
    el: '#container',
    data: {
        maxPage: 3,
        breadcrumbList: [{url: "/", tagName: "首页"},
            {url: "/movie_list?tag=" + tagName, tagName: tagZHName}]
    },
    methods: {

    }
});
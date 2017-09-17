/**
 * Created by shenjj on 2017/2/13.
 */
export default class UrlUtil {
    constructor() {

    }

    json2Url(rawUrl, json) {
        let href = Object.keys(json).map(function (key) {
            return encodeURIComponent(key) + '=' + encodeURIComponent(json[key]);
        }).join('&');
        return rawUrl + "?" + href;
    }

    url2Json(url) {
        let hash;
        let myJson = {};
        let hashes = url.slice(url.indexOf('?') + 1).split('&');
        for (let i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            myJson[hash[0]] = hash[1];
        }
        return myJson;
    }

    getQueryString(paramName) {
        let reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    getContextPath() {
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    }
}
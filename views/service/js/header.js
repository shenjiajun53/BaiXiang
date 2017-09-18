/**
 * Created by shenjj on 2017/5/16.
 */
import Urls from "../../utils/Urls";

let header = new Vue({
    el: "#header",
    data: {
        signInUrl:Urls.SIGN_IN,
        signUpUrl:Urls.SIGN_UP,
        signOutUrl:Urls.SIGN_OUT,
        manageUrl:Urls.MANAGE
    },
    methods: {
        reLocation(herf) {
            // this.$message('click on item ' + herf);
            window.location.href = herf;
        }
    }
});
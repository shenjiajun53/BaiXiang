/**
 * Created by shenjj on 2017/5/16.
 */
import Urls from "../../utils/Urls";

let header = new Vue({
    el: "#header",
    data: {
        signInUrl:Urls.signIn,
        signUpUrl:Urls.signUp,
        signOutUrl:Urls.signOut,
        manageUrl:Urls.manage
    },
    methods: {
        reLocation(herf) {
            // this.$message('click on item ' + herf);
            window.location.href = herf;
        }
    }
});
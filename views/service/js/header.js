/**
 * Created by shenjj on 2017/5/16.
 */


let header=new Vue({
    el: "#header",
    methods: {
        reLocation(herf){
            // this.$message('click on item ' + herf);
            window.location.href=herf;
        }
    }
});
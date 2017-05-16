/**
 * Created by shenjj on 2017/5/16.
 */


let header=new Vue({
    el: "#header",
    methods: {
        handleCommand(command) {
            this.$message('click on item ' + command);
        }
    }
});
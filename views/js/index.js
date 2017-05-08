/**
 * Created by shenjj on 2017/5/4.
 */
import Vue from "vue";
// import jouery from "jquery";
// import bootstrap from "bootstrap";
import 'iview/dist/styles/iview.css';    // 使用 CSS
import iView from "iview"


Vue.use(iView);

let app = new Vue({
    el: '#app',
    data: {
        show: true,
        message: 'Hello Vue!'
    },
    methods: {
        toggleShow: function () {
            this.show = !this.show;
            this.message = this.message.split('').reverse().join('');
        }
    }
});
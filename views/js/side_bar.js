/**
 * Created by shenjj on 2017/5/27.
 */
let header = new Vue({
    el: "#side_bar",
    data() {
        return {
            activeName: 'first'
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(this.activeName);

        }
    }
});
/**
 * Created by shenjj on 2017/5/26.
 */
import MyButton  from './button.vue';

/* istanbul ignore next */
/* istanbul ignore next */
console.log("component name=" + MyButton.name);

MyButton.install = function (Vue) {
    Vue.component(MyButton.name, MyButton);
};

export default MyButton;
/**
 * Created by shenjj on 2017/5/27.
 */
import MyButton from "./button/button"
import Pagination from "./pagination/pagination"

const components=[
    MyButton,
    Pagination
];

const install = function(Vue, opts = {}) {
    /* istanbul ignore if */
    components.map(component => {
        Vue.component(component.name, component);
    });
};

/* istanbul ignore if */
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
};
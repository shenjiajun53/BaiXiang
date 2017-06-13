/**
 * Created by shenjj on 2017/5/27.
 */
import MyButton from "./button/button"
import Pagination from "./pagination/pagination"
import BreadCrumb from "./BreadCrumb/BreadCrumb"
import Card from "./card/card"

const components=[
    Card,
    MyButton,
    Pagination,
    BreadCrumb
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
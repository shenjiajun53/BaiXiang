/**
 * Created by shenjj on 2017/6/13.
 */
import Card  from './card.vue';

/* istanbul ignore next */
/* istanbul ignore next */
Card.install = function (Vue) {
    Vue.component(Card.name, Card);
};

export default Card;
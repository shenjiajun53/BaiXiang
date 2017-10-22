/**
 * Created by shenjj on 2017/5/27.
 */
import Urls from "../../utils/Urls";

Vue.component('my-item-zh', {
    functional: true,
    render: function (h, ctx) {
        var item = ctx.props.item;
        return h('li', ctx.data, [
            h('div', {style: {textOverflow: "ellipsis", overFlow: "hidden"}}, [item.movieName]),
            // h('span', {style: {color: "#b4b4b4", fontSize: "12px"}}, [item.movieInfo])
        ]);
    },
    props: {
        item: {type: Object, required: true}
    }
});

let header = new Vue({
    el: "#side_bar",
    data() {
        return {
            inputStr: '',
            activeName: 'first',
            timeout: null
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(this.activeName);

        },
        searchMovie(queryString, cb) {
            console.log(queryString);
            let formData = new FormData();
            formData.append("searchStr", queryString);
            fetch(Urls.API_SEARCH_MOVIE, {
                method: "post",
                body: formData,
                credentials: 'include'     //很重要，设置session,cookie可用
            }).then(
                (response) => {
                    return response.json();
                }
            ).then(
                (json) => {
                    // console.log(JSON.stringify(json));
                    let result = json.result;
                    // var results = queryString ? result.filter(this.createStateFilter(queryString)) : result;
                    console.log(JSON.stringify(result));
                    cb(result);
                }
            ).catch(
                (ex) => {
                    console.error('parsing failed', ex);
                });
        },
        createStateFilter(queryString) {
            return (state) => {
                return (state.value.indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(movie) {
            console.log(movie);
            window.location.href = Urls.MANAGE_EDIT_MOVIE + '?movieId=' + movie.id;
        }
    }
});
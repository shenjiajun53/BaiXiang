/**
 * Created by shenjj on 2017/5/27.
 */
let header = new Vue({
    el: "#side_bar",
    data() {
        return {
            inputStr: '',
            activeName: 'first'
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(this.activeName);

        },
        searchMovie(queryString, cb){
            console.log(queryString);
            let url = "/api/search_movie";
            let formData=new FormData();
            formData.append("searchStr",queryString);
            fetch(url, {
                method: "post",
                body: formData,
                credentials: 'include'     //很重要，设置session,cookie可用
            }).then(
                (response) => {
                    return response.json();
                }
            ).then(
                (json) => {
                    console.log(JSON.stringify(json));
                    let result = json.result;
                    if (result.status == 1) {
//                            window.location.href = result.redirect;
                    }
                }
            ).catch(
                (ex) => {
                    console.error('parsing failed', ex);
                });
        },
        handleSelect(item){
            console.log(item);
        }
    }
});
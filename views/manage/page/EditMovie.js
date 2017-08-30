import React from 'react';
import Input from 'antd/lib/input';
import "antd/lib/input/style/css"
import Button from 'antd/lib/button';
import "antd/lib/button/style/css"

const movieTypeOptions = [
    {value: '动画', key: "动画"},
    {value: '动作', key: "动作"},
    {value: '科幻', key: "科幻"},
    {value: '美剧', key: "美剧"},
    {value: '日剧', key: "日剧"},
    {value: '推荐', key: "推荐"}];
export default class EditMovie extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: null,
            movieInfo: null,
            movieTitle: null,
            poster: {file: null, url: null},
            screenShotList: [],
            torrentList: [],
            checkAll: true,
            checkedTags: [],
            movieTypeOptions: movieTypeOptions,
            isIndeterminate: true,
            dateValue: '',
        }
    }

    componentDidMount() {
        let url = "/api/movieDetail";

        let formData = new FormData();
        formData.append('movieId', this.props.params.movieId);
        console.log("movieId=" + this.props.params.movieId);
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
                // console.log("response=" + JSON.stringify(json));
                this.setState({
                    movie: json.result,
                    movieTitle: json.result.movieName,
                    movieInfo: json.result.movieInfo,
                    poster: {file: null, url: json.result.poster}
                })
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    submit() {
        console.log("onSubmit");
        let formData = new FormData();
        formData.append('movieId', this.state.movie.id);
        console.log("movieid=" + this.state.movie.id);
        formData.append('movieInfo', this.state.movieInfo);
        formData.append('movieTitle', this.state.movieTitle);
        // formData.append('poster', this.poster.file);
        // for (let i = 0; i < this.screenShotList.length; i++) {
        //     formData.append('screenShotList', this.screenShotList[i].file);
        // }
        // for (let i = 0; i < this.torrentList.length; i++) {
        //     formData.append('torrentList', this.torrentList[i].file);
        // }
        // for (let i = 0; i < this.checkedTags.length; i++) {
        //     console.log("tag=" + this.checkedTags[i].key);
        //     formData.append("tagList", this.checkedTags[i].key);
        // }
        // formData.append("movieDate", this.dateValue);

        let url = "/api/edit_movie";
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
                    window.location.href = result.redirect;
                }
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {

        return (
            <div>
                <Input placeholder="请输入影片标题" value={this.state.movieTitle}
                       onChange={(e) => {
                           this.setState({
                               movieTitle: e.target.value
                           })
                       }}/>
                <Input placeholder="请输入电影介绍" value={this.state.movieInfo}
                       onChange={(e) => {
                           this.setState({
                               movieInfo: e.target.value
                           })
                       }}/>
                <Button onClick={() => this.submit()}>提交</Button>
            </div>);
    }
}
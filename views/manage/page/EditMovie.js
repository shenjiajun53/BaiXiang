import React from 'react';
import Input from 'antd/lib/input';
import "antd/lib/input/style/css"
import Button from 'antd/lib/button';
import "antd/lib/button/style/css"
import CheckBox from "antd/lib/checkbox";
import "antd/lib/checkbox/style/css"

const movieTypeOptions = [
    {label: '动画', key: "动画"},
    {label: '动作', key: "动作"},
    {label: '科幻', key: "科幻"},
    {label: '美剧', key: "美剧"},
    {label: '日剧', key: "日剧"},
    {label: '推荐', key: "推荐"}];
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
        formData.append('poster', this.state.poster.file);
        for (let i = 0; i < this.state.screenShotList.length; i++) {
            formData.append('screenShotList', this.state.screenShotList[i].file);
        }
        for (let i = 0; i < this.state.torrentList.length; i++) {
            formData.append('torrentList', this.state.torrentList[i].file);
        }
        for (let i = 0; i < this.state.checkedTags.length; i++) {
            console.log("tag=" + this.state.checkedTags[i].key);
            formData.append("tagList", this.state.checkedTags[i].key);
        }
        formData.append("movieDate", this.state.dateValue);

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
                <Button style={{marginBottom: 16}}>上传海报</Button>
                <input type="file"
                       multiple="multiple"
                       accept="image/*"
                       ref="posterInput"
                       name="posterInput"
                       style={{marginBottom: 16, display: "none"}}
                />

                <img src={this.state.poster.url} style={{width: 200}}/>

                <div>
                    <CheckBox checked={this.state.checkAll}
                              indeterminate={this.state.indeterminate}
                              onChange={(e) => {
                                  this.setState({
                                      checkedTags: e.target.checked ? movieTypeOptions : [],
                                      isIndeterminate: false,
                                      checkAll: e.target.checked
                                  })
                              }}>全部</CheckBox>
                    <CheckBox.Group options={movieTypeOptions}
                                    onChange={(checkedValue) => {
                                        let checkedCount = checkedValue.length;
                                        console.log(checkedValue);
                                        this.setState({
                                            checkAll: checkedCount === this.movieTypeOptions.length,
                                            isIndeterminate: checkedCount > 0 && checkedCount < this.movieTypeOptions.length
                                        })
                                    }}/>
                </div>

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
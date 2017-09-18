/**
 * Created by shenjiajun on 2017/1/29.
 */
import React, {Component} from 'react';
// import Card from "material-ui/Card";
// import {Card} from 'antd';
import Card from 'antd/lib/card';
import 'antd/lib/card/style/css';
// import Avatar from "material-ui/Avatar";
// import TouchRipple from "material-ui/internal/TouchRipple";
//
// import moment from "moment";
import Urls from "../../utils/Urls"

// injectTapEventPlugin();
class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movieList: null,
        }
    }

    componentDidMount() {
        fetch(Urls.API_GET_RECOMMEND_MOVIES, {
            method: "get",
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                // console.log("response=" + JSON.stringify(json));
                this.setState({
                    movieList: json.result
                })
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    // onCardClick(movie) {
    //     // console.log("blogId=" + blog._id);
    //     window.location = "/BlogDetail/" + movie.id;
    // }

    render() {
        let movieListView;

        // if(null != this.state.blogList){
        //     for (let i = 0; i < this.state.blogList.length; i++) {
        //         blogListView.push(<div key={this.state.blogList[i]._id}>
        //             <h1>{this.state.blogList[i].blogTitle}</h1>
        //             <div>{this.state.blogList[i].blogContent}</div>
        //         </div>);
        //     }
        // }

        if (null !== this.state.movieList) {
            movieListView = this.state.movieList.map(
                (movieInfo) => {
                    return (
                        <div style={{marginBottom: "10px", marginRight: "10px"}}
                             key={movieInfo.id}>
                            <a href={Urls.MANAGE_EDIT_MOVIE + movieInfo.id}>
                                <Card
                                    style={{
                                        width: 200
                                    }} bodyStyle={{padding: 0}}>
                                    <img src={Urls.BASE_URL + movieInfo.poster} alt={movieInfo.movieName} width="100%"/>
                                    <div style={{padding: "5px"}}>{movieInfo.movieName}</div>
                                </Card>
                            </a>
                        </div>
                    );
                }
            );
        }
        return (
            <div style={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
                marginTop: 16,
                marginLeft: 32,
                marginBottom: 16,
                marginRight: 32
            }}>
                {movieListView}
            </div>
        );
    }
}

export default Home;
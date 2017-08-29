/**
 * Created by shenjiajun on 2017/1/29.
 */
import React, {Component} from 'react';
import Card from "material-ui/Card"
import Avatar from "material-ui/Avatar";
import TouchRipple from "material-ui/internal/TouchRipple";

import moment from "moment";
import colors from "../../utils/colors"

// injectTapEventPlugin();
class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            blogList: null,
        }
    }

    componentDidMount() {
        let url = "/api/getAllBlogs";
        console.log("url=" + url);

        fetch(url, {
            method: "get",
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                console.log("response=" + JSON.stringify(json));
                this.setState({
                    blogList: json.result
                })
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    onCardClick(blog) {
        // console.log("blogId=" + blog._id);
        window.location = "/BlogDetail/" + blog.id;
    }

    render() {
        let blogListView;

        // if(null != this.state.blogList){
        //     for (let i = 0; i < this.state.blogList.length; i++) {
        //         blogListView.push(<div key={this.state.blogList[i]._id}>
        //             <h1>{this.state.blogList[i].blogTitle}</h1>
        //             <div>{this.state.blogList[i].blogContent}</div>
        //         </div>);
        //     }
        // }

        if (null != this.state.blogList) {
            blogListView = this.state.blogList.map(
                (blogInfo) => {
                    let avatarPath;
                    let showAvatarImg = "none";
                    let showAvatarName = "flex";
                    if (blogInfo.user) {
                        if (blogInfo.user.avatarPath) {
                            avatarPath = blogInfo.user.avatarPath;
                            showAvatarImg = "flex";
                            showAvatarName = "none";
                            console.log("avatarPath=" + avatarPath);
                        }
                    }
                    // console.log("blog=" + blog.blogTitle);
                    let time = blogInfo.blog.time;
                    let date = new Date(time);
                    let dateStr = moment(date).format("YYYY-MM-DD HH:mm:ss");
                    return (

                        <Card key={blogInfo.blog.id}
                              style={{
                                  marginTop: "10px",
                                  marginBottom: "10px",
                                  padding: "10px"
                              }}
                              onTouchTap={() => this.onCardClick(blogInfo.blog)}>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                            }}>
                                <div style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
                                    <Avatar src={avatarPath} style={{display: showAvatarImg}}
                                            backgroundColor={colors.accent}/>
                                    <Avatar style={{display: showAvatarName}}
                                            backgroundColor={colors.accent}>
                                        {blogInfo.user.userName[0]}
                                    </Avatar>
                                    <div>
                                        <div style={{marginLeft: "10px"}}>{blogInfo.user.userName}</div>
                                        <div style={{marginLeft: "10px"}}>{dateStr}</div>
                                    </div>
                                </div>
                                <h1 >{blogInfo.blog.blogTitle}</h1>
                                <div>{blogInfo.blog.blogContent}</div>
                            </div>
                        </Card>
                    );
                }
            );
        }
        return (
            <div>
                {blogListView}
            </div>
        );
    }
}
export default Home;
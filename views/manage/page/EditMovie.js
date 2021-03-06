import React from 'react';

// import Input from 'antd/lib/input';
// import "antd/lib/input/style/css"
// import Button from 'antd/lib/button';
// import "antd/lib/button/style/css"
// import Checkbox from "antd/lib/checkbox";
// import "antd/lib/checkbox/style/css"
// import DatePicker from "antd/lib/date-picker";
// import "antd/lib/date-picker/style/css"
import {Input, Button, Checkbox, DatePicker, Icon, Select} from "antd"
import ImageItem from "../component/ImageItem";
import ActorSelect from "../component/ActorSelect";
import Urls from "../../utils/Urls";

// const movieTypeOptions = [
//     {key: '动画',label: '动画', value: "动画"},
//     {key: '动作',label: '动作', value: "动作"},
//     {key: '科幻',label: '科幻', value: "科幻"},
//     {key: '美剧',label: '美剧', value: "美剧"},
//     {key: '日剧', label: '日剧', value: "日剧"},
//     {key: '推荐', label: '推荐', value: "推荐"}];

const movieTypeOptions = ["动画", "动作", "科幻", "美剧", "日剧", "推荐"];
export default class EditMovie extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: null,
            movieInfo: null,
            movieTitle: null,
            posterUrl: "",
            posterId: -1,
            screenShotList: [],
            torrentList: [],
            checkedTags: [],
            actorList: [],
            checkAll: false,
            isIndeterminate: false,
            dateValue: null,
        }
    }

    componentDidMount() {
        this.getAllTags()
    }

    getAllTags() {
        fetch(Urls.API_GET_ALL_TAGS, {
            method: "post",
        })
            .then((response) => response.json())
            .then((json) => {
                // console.log("getAllTags=" + JSON.stringify(json));
                json.result.map((tag) => {
                    if (!movieTypeOptions.includes(tag.tagName)) {
                        movieTypeOptions.push(tag.tagName);
                    }
                });
                this.getMovieDetail();
            })
    }

    getMovieDetail() {
        if (!this.props.params.movieId) {
            return;
        }

        let formData = new FormData();
        formData.append('movieId', this.props.params.movieId);
        console.log("movieId=" + this.props.params.movieId);
        fetch(Urls.API_MOVIE_DETAIL, {
            method: "post",
            body: formData,
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                console.log("getMovieDetail=" + JSON.stringify(json));
                let baseInfo = json.result.baseInfo;
                let tagSet = new Array();
                baseInfo.movieTagSet.map((tag) => {
                    tagSet.push(tag.tagName);
                });
                this.setState({
                    movie: baseInfo,
                    movieTitle: baseInfo.movieName,
                    movieInfo: baseInfo.movieInfo,
                    checkedTags: tagSet,
                    checkAll: baseInfo.movieTagSet.length === movieTypeOptions.length,
                    isIndeterminate: baseInfo.movieTagSet.length !== 0 && baseInfo.movieTagSet.length < movieTypeOptions.length,
                    posterUrl: baseInfo.posterUrl,
                    posterId: baseInfo.posterId,
                    actorList: baseInfo.actorSet,
                    torrentList: baseInfo.movieTorrents,
                    screenShotList: json.result.screenshotList
                })
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    deleteMovie() {
        let formData = new FormData();
        if (this.state.movie !== null) {
            formData.append('movieId', this.state.movie.id);
            console.log("movieid=" + this.state.movie.id);
        } else {
            return;
        }
        fetch(Urls.API_DELETE_MOVIE, {
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
                if (result.status === 1) {
                    window.location.href = Urls.MANAGE;
                }
            }
        );
    }

    deleteTorrent(torrentId) {
        let formData = new FormData();
        if (this.state.movie !== null) {
            formData.append('movieId', this.state.movie.id);
            console.log("movieid=" + this.state.movie.id);
        } else {
            return;
        }
        formData.append('torrentId', torrentId);
        fetch(Urls.API_DELETE_TORRENT, {
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
                if (result.status === 1) {
                    let tempTorrentList = this.state.torrentList;
                    tempTorrentList.map((torrent, index) => {
                        if (torrent.id === torrentId) {
                            tempTorrentList.splice(index, 1);
                            this.setState({
                                torrentList: tempTorrentList
                            })
                        }
                    });
                }
            }
        );
    }

    uploadImage(type, imageFile) {
        let formData = new FormData();
        formData.append("type", type);
        formData.append('imageFile', imageFile);

        fetch(Urls.API_UPLOAD_IMAGE, {
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
                let imageId = result.id;
                if (imageId > 0) {
                    console.log("upload image succeed");
                    if (type === "poster") {
                        this.setState({
                            posterUrl: result.url,
                            posterId: imageId
                        })
                    } else if (type === "screenshot") {
                        let oldScreenshotList = this.state.screenShotList;
                        oldScreenshotList.push(result);
                        this.setState({
                            screenshotList: oldScreenshotList
                        })
                    }
                }
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    submit() {
        console.log("onSubmit");
        let formData = new FormData();
        if (this.state.movie !== null) {
            formData.append('movieId', this.state.movie.id);
            console.log("submit movieid=" + this.state.movie.id);
        }
        formData.append('movieInfo', this.state.movieInfo);
        formData.append('movieTitle', this.state.movieTitle);
        formData.append('poster', this.state.posterId);
        for (let i = 0; i < this.state.screenShotList.length; i++) {
            formData.append('screenShotList', this.state.screenShotList[i].id);
        }
        for (let i = 0; i < this.state.torrentList.length; i++) {
            if (this.state.torrentList[i].id < 0) {
                formData.append('torrentList', this.state.torrentList[i].file);
            }
        }
        for (let i = 0; i < this.state.checkedTags.length; i++) {
            console.log("tag=" + this.state.checkedTags[i]);
            formData.append("tagList", this.state.checkedTags[i]);
        }
        this.state.actorList.map((actor) => {
            formData.append("actorList", actor.actorName);
        });
        formData.append("movieDate", this.state.dateValue);

        fetch(Urls.API_EDIT_MOVIE, {
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
                    window.location.href = Urls.MANAGE;
                }
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {
        let screenShotListView = this.state.screenShotList.map((screenShot, index) => {
            return <ImageItem src={screenShot.url} style={{width: 400}} key={screenShot.url}
                              onCloseClick={() => {
                                  let oldScreenShotList = this.state.screenShotList;
                                  oldScreenShotList.splice(index, 1);
                                  this.setState({
                                      screenShotList: oldScreenShotList
                                  })
                              }}/>
        });

        let torrentListView = this.state.torrentList.map((torrent, index) => {
            return (
                <div key={index}
                     style={{
                         display: "flex",
                         flexDirection: "row",
                         alignItems: "center",
                         marginBottom: 5
                     }}>
                    <span style={{width: 500}}>
                        {torrent.torrentName}
                    </span>
                    <Icon className="image-icon-dark" type="close"
                          onClick={(event) => {
                              console.log("torrentId=" + torrent.id);
                              if (torrent.id < 0) {
                                  let tempTorrentList = this.state.torrentList;
                                  tempTorrentList.splice(index, 1);
                                  this.setState({
                                      torrentList: tempTorrentList
                                  })
                              } else {
                                  this.deleteTorrent(torrent.id);
                              }
                          }}/>
                </div>)
        });

        let actListView = this.state.actorList.map((actor, index) => {
            return (
                <div key={actor.actorName}
                     style={{
                         display: "flex",
                         flexDirection: "row",
                         alignItems: "center",
                         marginRight: 16
                     }}>
                    <span style={{marginRight: 5}}>{actor.actorName}</span>
                    <Icon className="image-icon-dark" type="close"
                          onClick={(event) => {
                              let tempActorList = this.state.actorList;
                              tempActorList.splice(index, 1);
                              this.setState({
                                  actorList: tempActorList
                              })
                          }}/>
                </div>)
        });

        return (
            <div style={{
                marginLeft: 100,
                marginRight: 100,
                marginTop: 16,
                marginBottom: 30,
            }}>
                <div style={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-end"
                }}>
                    <Icon type="delete"
                          className="delete-icon"
                          style={{
                              display: this.state.movie ? "inline" : "none"
                          }}
                          onClick={() => {
                              this.deleteMovie();
                          }}
                    /></div>
                <Input placeholder="请输入影片标题"
                       style={{marginBottom: 16, width: 800}}
                       value={this.state.movieTitle}
                       onChange={(e) => {
                           this.setState({
                               movieTitle: e.target.value
                           })
                       }}/>

                <div>
                    <ImageItem src={this.state.posterUrl} style={{marginBottom: 16, width: 400}}
                               onCloseClick={() => {
                                   this.setState({
                                       posterUrl: "",
                                       posterId: -1
                                   })
                               }}/>
                    <div/>
                    <Button style={{marginBottom: 16}}
                            onClick={() => {
                                this.refs.posterInput.click();
                            }}>上传海报</Button>
                    <input type="file"
                           multiple="multiple"
                           accept="image/*"
                           ref="posterInput"
                           name="posterInput"
                           style={{display: "none"}}
                           onChange={(e) => {
                               let file = this.refs.posterInput.files[0];
                               console.info("file=" + file.name);
                               if (file) {
                                   this.uploadImage("poster", file)
                               }
                           }}
                    />
                </div>

                <div style={{marginBottom: 16}}>
                    <Checkbox checked={this.state.checkAll}
                              indeterminate={this.state.isIndeterminate}
                              onChange={(e) => {
                                  console.log("checked=" + e.target.checked);
                                  this.setState({
                                      checkedTags: e.target.checked ? movieTypeOptions : [],
                                      isIndeterminate: false,
                                      checkAll: e.target.checked
                                  })
                              }}>全部</Checkbox>
                    <Checkbox.Group options={movieTypeOptions}
                                    value={this.state.checkedTags}
                                    onChange={(checkedValue) => {
                                        let checkedCount = checkedValue.length;
                                        console.log(checkedValue);
                                        this.setState({
                                            checkedTags: checkedValue,
                                            checkAll: checkedCount === movieTypeOptions.length,
                                            isIndeterminate: checkedCount !== 0 && checkedCount < movieTypeOptions.length
                                        })
                                    }}/>
                </div>


                <DatePicker
                    style={{marginBottom: 16}}
                    value={this.state.dateValue}
                    placeholder="选择上映日期"
                    onChange={(data, dataString) => {
                        this.setState({
                            dateValue: data
                        })
                    }}/>
                <br/>

                <div>
                    <div>{screenShotListView}</div>
                    <Button style={{marginBottom: 16}}
                            onClick={() => {
                                this.refs.screenShotInput.click();
                            }}>上传截图</Button>
                    <input type="file"
                           multiple="multiple"
                           accept="image/*"
                           ref="screenShotInput"
                           name="screenShotInput"
                           style={{display: "none"}}
                           onChange={(e) => {
                               let files = this.refs.screenShotInput.files;
                               if (files) {
                                   for (let i = 0; i < files.length; i++) {
                                       this.uploadImage("screenshot", files[i]);
                                   }
                                   // files.map((file, index) => {
                                   //     this.uploadImage("screenshot", file);
                                   // });
                               }
                           }}
                    />
                </div>

                <div>
                    <div style={{marginBottom: 10}}>
                        {torrentListView}
                    </div>
                    <Button style={{marginBottom: 16}}
                            onClick={() => {
                                this.refs.torrentInput.click();
                            }}>上传种子</Button>
                    <input type="file"
                           multiple="multiple"
                           accept=".torrent"
                           ref="torrentInput"
                           name="torrentInput"
                           style={{display: "none"}}
                           onChange={(e) => {
                               let files = this.refs.torrentInput.files;
                               let tempTorrentList = this.state.torrentList;
                               if (files) {
                                   for (let i = 0; i < files.length; i++) {
                                       // 获取 window 的 URL 工具
                                       let URL = window.URL || window.webkitURL;
                                       // 通过 file 生成目标 url
                                       let torrentUrl = URL.createObjectURL(files[i]);
                                       let file = files[i];
                                       tempTorrentList.push({
                                           file: file,
                                           torrentName: file.name,
                                           filePath: torrentUrl,
                                           id: -1
                                       });
                                   }
                                   this.setState({
                                       torrentList: tempTorrentList
                                   })
                               }
                           }}
                    />
                </div>

                <Input.TextArea placeholder="请输入电影介绍"
                                value={this.state.movieInfo}
                                style={{marginBottom: 16}}
                                autosize={{minRows: 2, maxRows: 15}}
                                onChange={(e) => {
                                    this.setState({
                                        movieInfo: e.target.value
                                    })
                                }}/>

                <div style={{
                    display: "flex",
                    flexDirection: "row",
                    marginBottom: 16,
                    flexWrap: "wrap"
                }}>
                    {actListView}
                </div>

                <div style={{
                    marginBottom: 16,
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center"
                }}>
                    <span style={{marginRight: 10}}>输入演员名字：</span>
                    <ActorSelect
                        onActorSelect={(value, option) => {
                            let tempActorList = this.state.actorList;
                            tempActorList.push({"actorName": value});
                            this.setState({
                                actorList: tempActorList
                            })
                        }}/>
                </div>

                <Button style={{marginBottom: 16}}
                        onClick={() => this.submit()}>提交</Button>
            </div>);
    }
}
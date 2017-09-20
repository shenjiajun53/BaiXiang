/**
 * Created by shenjiajun on 2017/1/31.
 */
import React from 'react';

import {Avatar,Card} from 'antd';
import MyMenu from "./MyMenu";
import Urls from "../../utils/Urls";


const ON_TITLE_CLICKED = 111;
const ON_USER_CLICKED = 112;
const ON_MINE_CLICKED = 113;
const ON_SPIDER_CLICKED = 115;
const ON_ADD_CLICKED = 116;
let myMenu;

class TopBar extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        myMenu = this.refs.my_menu;
    }

    handleOpenMenu() {
        console.log("on menu tap");
        myMenu.open();
    }

    onTitleClick(value) {
        switch (value) {
            case ON_TITLE_CLICKED:
                console.log("hostname=" + window.location.hostname +
                    " hash=" + window.location.hash +
                    " href=" + window.location.href +
                    " host=" + window.location.host +
                    " pathname=" + window.location.pathname);
                location.pathname = Urls.MANAGE;
                break;
            case ON_USER_CLICKED:
                location.pathname = Urls.MANAGE;
                break;
            case ON_SPIDER_CLICKED:
                location.pathname = Urls.MANAGE_SPIDER;
                break;
            case ON_MINE_CLICKED:
                location.pathname = '/user/UserCenter';
                break;
            case ON_ADD_CLICKED:
                location.pathname = Urls.MANAGE_EDIT_MOVIE;
                break;
            default:
                break;
        }
    }

    render() {
        let avatarPath;
        let showAvatar = "none";
        if (this.props.user) {
            if (this.props.user.fileName) {
                avatarPath = Urls.BASE_URL + "/uploadFiles/avatars/" + this.props.user.fileName;
                showAvatar = "inline";
                // console.log("avatarPath=" + avatarPath);
            }
        }

        return (
            <div style={{
                display: "flex",
                flex: 1,
                justifyContent: "left",
                alignItems: "center",
                flexDirection: "row",
            }}>
                <div
                    style={{
                        color: "#ffffff", marginRight: 10,
                        cursor: "pointer",
                        fontSize: 16
                    }}
                    onTouchTap={() => this.onTitleClick(ON_TITLE_CLICKED)}>
                    电影
                </div>
                <div
                    style={{
                        color: "#ffffff", marginRight: "10px", cursor: "pointer",
                        fontSize: 16
                    }}
                    onTouchTap={() => this.onTitleClick(ON_ADD_CLICKED)}>
                    添加影片
                </div>
                <div
                    style={{
                        color: "#ffffff", marginRight: "10px", cursor: "pointer",
                        fontSize: 16
                    }}
                    onTouchTap={() => this.onTitleClick(ON_USER_CLICKED)}>
                    用户管理
                </div>
                <div
                    style={{
                        color: "#ffffff", marginRight: "10px", cursor: "pointer",
                        fontSize: 16
                    }}
                    onTouchTap={() => this.onTitleClick(ON_SPIDER_CLICKED)}>
                    爬虫
                </div>


                <div style={{
                    display: "flex",
                    flex: 1,
                    flexDirection: "row",
                    justifyContent: "right",
                }}>
                </div>
                <Avatar src={avatarPath}
                        style={{
                            display: showAvatar
                        }}/>
                <MyMenu
                    user={this.props.user}
                    hasLogin={this.props.hasLogin}
                    style={{}}
                    ref="my_menu"/>
            </div>
        );
    }
}

export default TopBar;
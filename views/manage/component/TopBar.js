/**
 * Created by shenjiajun on 2017/1/31.
 */
import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import AppBar from 'material-ui/AppBar';
import Avatar from 'material-ui/Avatar';
import Card from "material-ui/Card";
import FlatButton from "material-ui/FlatButton";
import MyMenu from "./MyMenu";

const ON_TITLE_CLICKED = 111;
const ON_USER_CLICKED = 112;
const ON_MINE_CLICKED = 113;
const ON_SPIDER_CLICKED = 115;
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
                location.pathname = '/manage';
                break;
            case ON_USER_CLICKED:
                location.pathname = '/manage';
                break;
            case ON_SPIDER_CLICKED:
                location.pathname = '/manage/spider';
                break;
            case ON_MINE_CLICKED:
                location.pathname = '/UserCenter';
                break;
            default:
                break;
        }
    }

    render() {
        console.log('topbar render');

        let avatarPath;
        let showAvatar = "none";
        if (this.props.user) {
            if (this.props.user.fileName) {
                avatarPath = "/uploadFiles/avatars/" + this.props.user.fileName;
                showAvatar = "inline";
                // console.log("avatarPath=" + avatarPath);
            }
        }

        return (
            <Card>
                <div style={{
                    display: "flex",
                    flex: 1,
                    height: "48px",
                    backgroundColor: "#E91E63",
                    justifyContent: "left",
                    alignItems: "center",
                    flexDirection: "row",
                    paddingLeft: "1em",
                    paddingRight: "1em",
                }}>
                    <FlatButton
                        style={{color: "#ffffff", marginRight: "10px"}}
                        onTouchTap={() => this.onTitleClick(ON_TITLE_CLICKED)}>
                        电影
                    </FlatButton>
                    <FlatButton
                        style={{color: "#ffffff", marginRight: "10px"}}
                        onTouchTap={() => this.onTitleClick(ON_USER_CLICKED)}>
                        用户管理
                    </FlatButton>
                    <FlatButton
                        style={{color: "#ffffff", marginRight: "10px"}}
                        onTouchTap={() => this.onTitleClick(ON_SPIDER_CLICKED)}>
                        爬虫
                    </FlatButton>


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
            </Card>
        );
    }
}

export default TopBar;
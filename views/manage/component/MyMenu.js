/**
 * Created by shenjiajun on 2017/1/31.
 */
import React, {Component} from 'react';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import Menu from 'material-ui/Menu';
import IconButton from 'material-ui/IconButton';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

let iconMenu;
export default class MyMenu extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            openMenu: false,
        }
    }

    open() {
        console.log("on menu tap22 ");
        // iconMenu.open();
        this.setState({
            openMenu: true
        });
    }

    handleOnRequestChange(value) {
        this.setState({
            openMenu: value,
        });
    }

    componentDidMount() {
        iconMenu = this.refs.icon_menu;
    }

    onItemClick(value) {
        switch (value) {
            case "SignUp":
                window.location.pathname = '/manage/sign_up';
                // location.hash="/SignUp";
                break;
            case "SignIn":
                window.location.pathname = '/manage/sign_in';
                break;
            case "HomePage":
                window.location.pathname = '/';
                break;
            case "AddMovie":
                window.location.pathname = '/edit_movie';
                break;
            case "Settings":
                window.location.pathname = '/Settings';
                break;
            case "SignOut":
                window.location.pathname = '/manage/sign_out';
                break;
            default:
                break;
        }
    }

    SignOut() {
        console.log("SIgnOut");
        let url = "/api/SignOut";
        fetch(url, {
            method: "post",
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                console.log(JSON.stringify(json));
                if (json.result) {
                    window.location = json.result.redirect;
                }
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {
        if (this.props.hasLogin) {
            return (
                <IconMenu
                    ref="icon_menu"
                    open={this.state.openMenu}
                    onRequestChange={(value) => this.handleOnRequestChange(value)}
                    iconButtonElement={<IconButton><MoreVertIcon/></IconButton>}
                    anchorOrigin={{horizontal: 'right', vertical: 'top'}}
                    targetOrigin={{horizontal: 'right', vertical: 'top'}}
                >
                    <MenuItem primaryText="主页" onTouchTap={() => this.onItemClick("HomePage")}/>
                    <MenuItem primaryText="添加影片" onTouchTap={() => this.onItemClick("AddMovie")}/>
                    <MenuItem primaryText="设置" onTouchTap={() => this.onItemClick("Settings")}/>
                    <MenuItem primaryText="退出" onTouchTap={() => this.onItemClick("SignOut")}/>
                </IconMenu>
            );
        } else {
            return (
                <IconMenu
                    ref="icon_menu"
                    open={this.state.openMenu}
                    onRequestChange={(value) => this.handleOnRequestChange(value)}
                    iconButtonElement={<IconButton><MoreVertIcon/></IconButton>}
                    anchorOrigin={{horizontal: 'right', vertical: 'top'}}
                    targetOrigin={{horizontal: 'right', vertical: 'top'}}
                >
                    <MenuItem primaryText="主页" onTouchTap={() => this.onItemClick("HomePage")}/>
                    <MenuItem primaryText="登录" onTouchTap={() => this.onItemClick("SignIn")}/>
                    <MenuItem primaryText="注册" onTouchTap={() => this.onItemClick("SignUp")}/>
                </IconMenu>
            );
        }

    }
}
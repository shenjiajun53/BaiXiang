/**
 * Created by shenjiajun on 2017/1/31.
 */
import React, {Component} from 'react';
// import IconMenu from 'material-ui/IconMenu';
// import MenuItem from 'material-ui/MenuItem';
import Menu from 'antd/lib/menu';
import 'antd/lib/menu/style/css';
import DropDown from 'antd/lib/dropdown';
import 'antd/lib/dropdown/style/css';
// import IconButton from 'material-ui/IconButton';
// import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
// import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

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


    onItemClick(item) {
        console.log("onclick " + item.key);
        switch (item.key) {
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
                <DropDown
                    placement="bottomLeft"
                    overlay=
                        {<Menu onSelect={(item) => this.onItemClick(item)}>
                            <Menu.Item key="HomePage">主页</Menu.Item>
                            <Menu.Item key="AddMovie">添加影片</Menu.Item>
                            <Menu.Item key="Settings">设置</Menu.Item>
                            <Menu.Item key="SignOut">退出</Menu.Item>
                        </Menu>}>
                    <div style={{color: "#ffffff", hover: "pointer"}}>
                        菜单
                    </div>
                </DropDown>

            );
        } else {
            return (
                <DropDown
                    placement="bottomLeft"
                    overlay=
                        {<Menu onSelect={(item) => this.onItemClick(item)}>
                            <Menu.Item key="HomePage">主页</Menu.Item>
                            <Menu.Item key="SignIn">登录</Menu.Item>
                            <Menu.Item key="SignUp">注册</Menu.Item>
                        </Menu>}>
                    <div style={{color: "#ffffff", hover: "pointer"}}>
                        菜单
                    </div>
                </DropDown>
            );
        }
    }
}
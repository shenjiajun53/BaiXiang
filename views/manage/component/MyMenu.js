/**
 * Created by shenjiajun on 2017/1/31.
 */
import React from 'react';
import Urls from "../../utils/Urls";
import {Menu,Dropdown} from "antd";

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
                window.location.pathname = Urls.SIGN_UP;
                // location.hash="/SignUp";
                break;
            case "SignIn":
                window.location.pathname = Urls.SIGN_IN;
                break;
            case "HomePage":
                window.location.pathname = Urls.BASE_URL;
                break;
            case "AddMovie":
                window.location.pathname = Urls.MANAGE_EDIT_MOVIE;
                break;
            case "Settings":
                window.location.pathname = Urls.MANAGE_SETTINGS;
                break;
            case "SignOut":
                window.location.pathname = Urls.SIGN_OUT;
                break;
            default:
                break;
        }
    }

    SignOut() {
        fetch(Urls.API_USER_SIGN_OUT, {
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
                <Dropdown
                    placement="bottomLeft"
                    overlay=
                        {<Menu onClick={(item) => this.onItemClick(item)}>
                            <Menu.Item key="HomePage">主页</Menu.Item>
                            <Menu.Item key="AddMovie">添加影片</Menu.Item>
                            <Menu.Item key="Settings">设置</Menu.Item>
                            <Menu.Item key="SignOut">退出</Menu.Item>
                        </Menu>}>
                    <div style={{color: "#ffffff", hover: "pointer"}}>
                        菜单
                    </div>
                </Dropdown>

            );
        } else {
            return (
                <Dropdown
                    placement="bottomLeft"
                    overlay=
                        {<Menu onClick={(item) => this.onItemClick(item)}>
                            <Menu.Item key="HomePage">主页</Menu.Item>
                            <Menu.Item key="SignIn">登录</Menu.Item>
                            <Menu.Item key="SignUp">注册</Menu.Item>
                        </Menu>}>
                    <div style={{color: "#ffffff", hover: "pointer"}}>
                        菜单
                    </div>
                </Dropdown>
            );
        }
    }
}
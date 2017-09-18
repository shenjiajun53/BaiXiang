/**
 * Created by shenjiajun on 2017/1/29.
 */
import React from 'react';

import Input from 'antd/lib/input';
import "antd/lib/input/style/css"
import Card from 'antd/lib/card';
import 'antd/lib/card/style/css';
import Button from 'antd/lib/button';
import "antd/lib/button/style/css"

import Urls from "../../utils/Urls";

class SignIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            nameStr: "",
            passStr: "",
            nameError: "",
            passError: "",
        }
    }

    onSignIn() {
        let userNameStr = this.state.nameStr;
        let passStr = this.state.passStr;

        let infoFinished = true;
        if ("" === userNameStr) {
            this.setState({
                nameError: "不能为空"
            });
            infoFinished = false;
        }
        if ("" === passStr) {
            this.setState({
                passError: "不能为空"
            });
            infoFinished = false;
        }
        if (!infoFinished) {
            return;
        }

        let body = {
            "userName": userNameStr,
            "pass": passStr,
        };

        let formData = new FormData();
        formData.append('userName', userNameStr);
        formData.append('pass', passStr);
        // formData.append('remember-me', true);

        fetch(Urls.API_USER_SIGN_IN, {
            method: "post",
            // body: data,
            body: formData,
            headers: {
                // 'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded'
            },
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                console.log(response);
                return response.json();
            }
        ).then(
            (json) => {
                console.log(JSON.stringify(json));
                if (json.result) {
                    if (json.result.redirect) {
                        window.location = json.result.redirect;
                    }
                } else if (json.error) {
                    this.setState({
                        nameError: json.error.errorMsg,
                        passError: json.error.errorMsg
                    })
                }
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {
        return (
            <div style={{
                padding: "1em",
                display: "flex",
                flexDirection: "column",
            }}>
                <div style={{alignSelf: "center", marginBottom: 16}}>
                    <a href={Urls.SIGN_IN} style={{fontSize: 20, color: "#FF4081"}}>登录</a>
                    <span style={{fontSize: 20, color: "#757575"}}> · </span>
                    <a href={Urls.SIGN_UP} style={{fontSize: 20, color: "#757575"}}>注册</a>
                </div>
                <div>
                    用户名*
                </div>
                <Input style={{marginBottom: "1em", flex: 1}}
                       value={this.state.nameStr}
                       onChange={
                           (event) => {
                               this.setState({
                                   nameStr: event.target.value
                               })
                           }}
                       ref="userNameTF"
                       id="userNameTF"
                       name="userNameTF"/>
                <div>
                    密码*
                </div>
                <Input style={{marginBottom: "1em"}}
                       value={this.state.passStr}
                       onChange={(event) => {
                           this.setState({
                               passStr: event.target.value
                           })
                       }}
                       type="password"
                       ref="passTF"
                       id="passTF"
                       name="passTF"/>
                <Button onClick={() => this.onSignIn()}
                        style={{width: "10em", alignSelf: "center"}}
                >登录</Button>
            </div>

        );
    }
}

export default SignIn;
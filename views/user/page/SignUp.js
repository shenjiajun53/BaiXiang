/**
 * Created by shenjiajun on 2017/1/29.
 */
import React, {Component} from 'react';

import {Input, Card, Button, Select} from "antd"

import Urls from "../../utils/Urls";

export default class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedGender: 1,
            avatarUrl: "",
            selectedFile: "",
            nameError: "",
            passError: "",
            passConfirmError: "",
            userIntroError: "",
            nameStr: "",
            passStr: "",
            passConfirmStr: "",
            userIntroStr: ""
        }
    }

    onSignUp() {
        console.info("upload =" + this.state.nameStr + this.state.passStr + this.state.passConfirmStr + this.state.userIntroStr);

        let infoFinished = true;
        if ("" === this.state.nameStr) {
            this.setState({
                nameError: "不能为空"
            });
            infoFinished = false;
        }
        if ("" === this.state.passStr) {
            this.setState({
                passError: "不能为空"
            });
            infoFinished = false;
        }
        if ("" === this.state.passConfirmStr) {
            this.setState({
                passConfirmError: "不能为空"
            });
            infoFinished = false;
        }
        if (this.state.passConfirmStr !== this.state.passStr) {
            this.setState({
                passError: "密码不一致",
                passConfirmError: "密码不一致"
            });
            infoFinished = false;
        }
        if (!infoFinished) {
            return;
        }
        let body = {
            "userName": this.state.nameStr,
            "pass": this.state.passStr,
            "passConfirm": this.state.passConfirmStr,
            "userIntro": this.state.userIntroStr
        };

        // document.cookie = "cookie1=5006";

        let formData = new FormData();
        formData.append('avatar', this.state.selectedFile);
        formData.append('userName', this.state.nameStr);
        formData.append('pass', this.state.passStr);
        formData.append('passConfirm', this.state.passConfirmStr);
        formData.append('userIntro', this.state.userIntroStr);
        formData.append('sex', this.state.selectedGender);

        fetch(Urls.API_USER_SIGN_UP, {
            method: "post",
            // body: data,
            body: formData,
            headers: {
                // 'Content-Type': 'multipart/form-data'
                // 'Content-Type': 'application/x-www-form-urlencoded'
            },
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                console.log(JSON.stringify(json));
                if (json.result) {
                    let result = json.result;
                    if (result.status == 1) {
                        window.location.pathname = result.redirect;
                    } else {
                        this.setState({
                            nameError: json.error.errorMsg
                        });
                    }
                } else if (json.error) {
                    this.setState({
                        nameError: json.error.errorMsg
                    });
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
                    <a href={Urls.SIGN_IN} style={{fontSize: 20, color: "#757575"}}>登录</a>
                    <span style={{fontSize: 20, color: "#757575"}}> · </span>
                    <a href={Urls.SIGN_UP} style={{fontSize: 20, color: "#FF4081"}}>注册</a>
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
                />
                <div>
                    密码*
                </div>
                <Input style={{marginBottom: "1em"}}
                       value={this.state.passStr}
                       onChange={(event, str) => {
                           this.setState({
                               passStr: event.target.value
                           })
                       }}
                />
                <div>
                    重复密码*
                </div>
                <Input style={{marginBottom: "1em"}}
                       value={this.state.passConfirmStr}
                       onChange={(event, str) => {
                           this.setState({
                               passConfirmStr: event.target.value
                           })
                       }}
                />

                <div style={{color: "#333333", hover: "pointer"}}>
                    性别*
                </div>
                <Select defaultValue="1" style={{width: 120, marginBottom: "1em"}}
                        onChange={(value) => {
                            this.setState({
                                selectedGender: value
                            })
                        }}>
                    <Select.Option value="1">男</Select.Option>
                    <Select.Option value="2">女</Select.Option>
                    <Select.Option value="3">保密</Select.Option>
                </Select>

                <div style={{marginBottom: "1em"}}>
                    <span>头像*</span>
                    <Button onClick={() => {
                        this.refs.uploadInput.click();
                    }}
                            style={{marginLeft: "0.5em"}}
                    >选择文件</Button>
                </div>
                <div style={{marginBottom: "1em"}}>
                    {this.state.selectedFile.name}
                </div>
                <input type="file"
                       multiple="multiple"
                       accept="image/*"
                       ref="uploadInput"
                       name="uploadInput"
                       style={{display: "none"}}
                       onChange={(event) => {
                           let file = this.refs.uploadInput.files[0];
                           console.info("file=" + file.name);
                           this.setState({
                               selectedFile: file
                           });
                       }}
                />
                <div>
                    个人简介*
                </div>
                <Input style={{marginBottom: "1em"}}
                       rows={5}
                       value={this.state.userIntroStr}
                       onChange={(event, str) => {
                           this.setState({
                               userIntroStr: event.target.value
                           })
                       }}/>

                <Button onClick={() => this.onSignUp()}
                        label={"注册"}
                        style={{width: "10em", alignSelf: "center"}}
                >注册</Button>
            </div>
        );
    }
}
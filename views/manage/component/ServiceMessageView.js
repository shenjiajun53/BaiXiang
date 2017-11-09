import React from 'react';

import {Switch, Button, Input} from "antd";
import Urls from "../../utils/Urls"
import SockJS from "sockjs-client";
import Stomp from "stompjs"

let stompClient = null;
export default class Spider extends React.Component {
    constructor() {
        super();
        this.state = {messageList: []}
    }

    getMessageFormat(messageList) {
        let formatStr = "";
        messageList.map((massage) => {
            formatStr += massage + "\n";
        });
        return formatStr;
    }

    render() {
        return (
            <div style={{padding: 16}}>
                <div style={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "center",
                    marginBottom: 16
                }}>
                    <Button onClick={() => {
                        let socket = new SockJS('/link-websocket');
                        stompClient = Stomp.over(socket);
                        stompClient.connect({}, (frame) => {
                            console.log('Connected: ' + frame);
                            stompClient.subscribe('/toApp/message', (message) => {
                                let tempList = this.state.messageList;
                                if (tempList.length > 50) {
                                    tempList.pop();//移除最后一个
                                }
                                //插入头部
                                tempList.unshift(message.body);
                                this.setState({
                                    messageList: tempList
                                });
                            });
                        });
                    }}>连接</Button>

                    <Button onClick={() => {
                        if (stompClient !== null) {
                            stompClient.disconnect();
                        }
                        console.log("Disconnected");
                    }}>断开</Button>

                    <Button onClick={() => {
                        if (stompClient !== null) {
                            stompClient.send("/fromApp/hello", {}, "hello test");
                        }
                    }}>发送</Button>
                </div>

                <Input.TextArea
                    autosize={{minRows: 10, maxRows: 50}}
                    value={this.getMessageFormat(this.state.messageList)}
                />
            </div>
        );
    }
}
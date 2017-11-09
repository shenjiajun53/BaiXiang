import React from 'react';

import {Switch, Button} from "antd";
import Urls from "../../utils/Urls"
import SockJS from "sockjs-client";
import Stomp from "stompjs"

let stompClient = null;
export default class Spider extends React.Component {
    constructor() {
        super();
        this.state = {message: "123"}
    }

    render() {
        return (
            <div>
                <div>
                    <Button onClick={() => {
                        let socket = new SockJS('/link-websocket');
                        stompClient = Stomp.over(socket);
                        stompClient.connect({}, (frame) => {
                            console.log('Connected: ' + frame);
                            stompClient.subscribe('/toApp/message', (message) => {
                                console.info("message=" + message);
                                this.setState({
                                    message: message
                                })
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
                            stompClient.send("/fromApp/hello", {}, "hello");
                        }
                    }}>发送</Button>
                </div>

                <div>{this.state.message}</div>
            </div>
        );
    }
}
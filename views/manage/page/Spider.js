import React from 'react';

import {Switch, Button} from "antd";
import Urls from "../../utils/Urls"
import ServiceMessageView from "../component/ServiceMessageView";


export default class Spider extends React.Component {

    constructor() {
        super();
        this.state = {
            isBtRunning: false,
            isHuaRunning: false,
            isDoubanRunning: false
        }
    }

    post(url) {
        fetch(url, {method: "post"});
    }

    componentWillMount() {
        fetch(Urls.API_GET_SPIDER_STATUS, {method: "post"})
            .then(
                (res) => res.json()
            )
            .then((json) => {
                console.log(JSON.stringify(json));
                this.setState({
                    isBtRunning: json.result.btRunning,
                    isDoubanRunning: json.result.doubanRunning
                })
            })
    }

    render() {
        return (
            <div>
                <span>BT天堂</span>
                <Switch defaultChecked={false}
                        checked={this.state.isBtRunning}
                        size="default"
                        onChange={(value) => {
                            console.log("" + value);
                            if (value) {
                                this.post(Urls.API_START_SPIDER_BT);
                            } else {
                                this.post(Urls.API_STOP_SPIDER_BT);
                            }
                            this.setState({
                                isBtRunning: value
                            })
                        }}/>
                <br/>
                <span style={{display: "none"}}>花</span>
                <Switch size="default"
                        defaultChecked={false}
                        checked={this.state.isHuaRunning}
                        style={{display: "none"}}
                        onChange={(value) => {
                            if (value) {
                                this.post(Urls.API_START_SPIDER_HUA);
                            } else {
                                this.post(Urls.API_STOP_SPIDER_HUA);
                            }
                            this.setState({
                                isHuaRunning: value
                            })
                        }}/>
                <span>豆瓣</span>
                <Switch defaultChecked={false}
                        checked={this.state.isDoubanRunning}
                        size="default"
                        onChange={(value) => {
                            console.log("" + value);
                            if (value) {
                                this.post(Urls.API_START_DOUBAN_PATCH);
                            } else {
                                this.post(Urls.API_STOP_DOUBAN_PATCH);
                            }
                            this.setState({
                                isDoubanRunning: value
                            })
                        }}/>
                <br/>

                <ServiceMessageView/>
            </div>
        );
    }
}
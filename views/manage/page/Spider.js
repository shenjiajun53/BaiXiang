import React from 'react';

import {Switch} from "antd";

export default class Spider extends React.Component {

    constructor() {
        super();
        this.state = {
            isBtRunning: false,
            isHuaRunning: false
        }
    }

    post(url) {
        fetch(url, {method: "post"});
    }

    componentWillMount() {
        let url = "/api/manage/getSpiderStatus";
        fetch(url, {method: "post"})
            .then(
                (res) => res.json()
            )
            .then((json) => {
                console.log(JSON.stringify(json));
                this.setState({
                    isBtRunning: json.result.btRunning
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
                                let url = "/api/start_spider_bt";
                                this.post(url);
                            } else {
                                let url = "/api/stop_spider_bt";
                                this.post(url);
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
                                let url = "/api/start_spider_taohua";
                                this.post(url);
                            } else {
                                let url = "/api/stop_spider_taohua";
                                this.post(url);
                            }
                            this.setState({
                                isHuaRunning: value
                            })
                        }}/>
            </div>
        );
    }
}
import React from "react";

import {Icon} from "antd"
import Urls from "../../utils/Urls"

export default class ImageItem extends React.Component {
    constructor() {
        super();
    }

    render() {
        this.props.style["position"] = "relative";
        if (this.props.src) {
            return (<div style={this.props.style}>
                <img src={Urls.CONTEXT_PATH + this.props.src} style={{width: "100%", height: "auto"}}/>
                <div style={{
                    display: "flex",
                    position: "absolute",
                    top: 0,
                    left: 0,
                    height: "100%",
                    width: "100%"
                }}>
                    <div style={{
                        alignSelf: "flex-start",
                        width: "100%",
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "flex-end",
                        padding: 10
                    }}>
                        <Icon className="image-icon" type="eye-o"
                              onClick={this.props.onShowClick}/>
                        <span style={{width: 10}}/>
                        <Icon className="image-icon" type="close"
                              onClick={this.props.onCloseClick}/>
                    </div>
                </div>
            </div>);
        } else {
            return (<div/>);
        }
    }
}
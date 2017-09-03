import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {render} from 'react-dom'
import './css/App.css';
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

import injectTapEventPlugin from 'react-tap-event-plugin';

import 'antd/lib/layout/style/css';
import {Card} from "antd";
import {Tabs} from "antd";

import SignIn from "./page/SignIn";
import SignUp from "./page/SignUp";

injectTapEventPlugin();

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            hasLogin: false,
            user: null
        }
    }

    componentWillMount() {
        let url = "/api/getUserInfo";
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
                    this.setState({
                        hasLogin: true,
                        user: json.result.user
                    });
                }

                console.log("state=" + this.state.hasLogin);
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {
        console.log('app render');
        // console.log('chileren=' + this.props.children.name);
        return (
            <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
                <Card style={{
                    marginTop: 200,
                    width: 300,
                    marginBottom: 30
                }}>
                    {React.cloneElement(this.props.children, {user: this.state.user})}
                </Card>
            </div>
        );
    }
}

render(
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <Route path="user/sign_up" component={SignUp}/>
            <Route path="user/sign_in" component={SignIn}/>
        </Route>
    </Router>
    ,
    document.getElementById('root')
)
;

export default App;

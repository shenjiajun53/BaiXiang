import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {render} from 'react-dom'
import './css/App.css';
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

import injectTapEventPlugin from 'react-tap-event-plugin';

import Layout from "antd/lib/layout";
import 'antd/lib/layout/style/css';

import SignIn from "./page/SignIn";
import SignUp from "./page/SignUp";
import TopBar from "./component/TopBar";

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
            <Layout>
                <Layout.Header>
                    <TopBar hasLogin={this.state.hasLogin} user={this.state.user}/>
                </Layout.Header>
                <Layout.Content>
                    {React.cloneElement(this.props.children, {user: this.state.user})}
                </Layout.Content>
            </Layout>
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

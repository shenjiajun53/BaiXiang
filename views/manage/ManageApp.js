import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {render} from 'react-dom'
import './css/App.css';
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

import injectTapEventPlugin from 'react-tap-event-plugin';

import Layout from "antd/lib/layout";
import 'antd/lib/layout/style/css';

import Home from "./page/Home";
import TopBar from "./component/TopBar";
import Settings from "./page/Settings";
import EditMovie from "./page/EditMovie";
import Spider from "./page/Spider";
import Urls from "../utils/Urls"

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
        fetch(Urls.API_GET_USER_INFO, {
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
        <Route path={Urls.BASE_URL} component={App}>
            <IndexRoute component={Home}/>
            <Route path={Urls.MANAGE} component={Home}/>
            <Route path={Urls.MANAGE_SETTINGS} component={Settings}/>
            <Route path={Urls.MANAGE_EDIT_MOVIE} component={EditMovie}/>
            <Route path={Urls.MANAGE_EDIT_MOVIE_WITH_ID} component={EditMovie}/>
            <Route path={Urls.MANAGE_SPIDER} component={Spider}/>
        </Route>
    </Router>
    ,
    document.getElementById('root')
)
;

export default App;

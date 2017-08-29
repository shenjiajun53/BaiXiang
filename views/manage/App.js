import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {render} from 'react-dom'
import './css/App.css';
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

import Home from "./component/Home";
import SignIn from "./component/SignIn";
import SignUp from "./component/SignUp";
import UserCenter from "./component/UserCenter";
import MyFollow from "./component/MyFollow";
import WriteBlog from "./component/WriteBlog";
import TopBar from "./component/TopBar";
import BlogDetail from "./component/BlogDetail";
import Settings from "./component/Settings";
import Favorites from "./component/Favorites";
import MyBlogs from "./component/MyBlogs";

import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
// import lightBaseTheme from 'material-ui/styles/baseThemes/lightBaseTheme';
// import getMuiTheme from 'material-ui/styles/getMuiTheme';

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
            <MuiThemeProvider>
                <div>

                    <TopBar hasLogin={this.state.hasLogin} user={this.state.user}/>
                    {React.cloneElement(this.props.children, {user: this.state.user})}
                </div>
            </MuiThemeProvider>
        );
    }
}

render(
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Home}/>
            <Route path="manage" component={Home}/>
            <Route path="manage/sign_up" component={SignUp}/>
            <Route path="manage/sign_in" component={SignIn}/>
            <Route path="manage/Settings" component={Settings}/>
        </Route>
    </Router>
    ,
    document.getElementById('root')
)
;

export default App;

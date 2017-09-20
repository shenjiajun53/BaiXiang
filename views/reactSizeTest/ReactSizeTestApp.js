import React, {Component} from 'react';
import {render} from 'react-dom'
import {Router, Route, IndexRoute, Link, IndexLink, browserHistory, hashHistory} from 'react-router';

import Urls from "../utils/Urls"
import {Layout, Button, Switch, Card, Calendar, Checkbox, Icon, Dropdown, Menu, DatePicker, Input} from "antd";


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
            <div>
                {/*<Button>fdadfafds</Button>*/}
                {/*<Switch/>*/}
                {/*<Card/>*/}
                {/*<DatePicker/>*/}
                {/*<Icon/>*/}
                {/*<Checkbox/>*/}
                {/*<Layout/>*/}
                {/*<Menu/>*/}
                advdgfa
            </div>
        );
    }
}

render(
    <Router history={browserHistory}>
        <Route path={Urls.BASE_URL} component={App}>
        </Route>
    </Router>
    ,
    document.getElementById('root')
)
;

export default App;

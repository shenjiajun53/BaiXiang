import React from 'react';
import {render} from 'react-dom'

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import MyComponent from './MyComponent';

// import injectTapEventPlugin from 'react-tap-event-plugin';
//
// injectTapEventPlugin();


export default class MaterialApp extends React.Component {

    render() {
        // console.log('app render');
        // console.log('chileren=' + this.props.children.name);
        return (
            <MuiThemeProvider>
                <MyComponent/>
            </MuiThemeProvider>
        );
    }
}

render(<MaterialApp/>, document.getElementById('root'));
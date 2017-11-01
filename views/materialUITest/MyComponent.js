import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';

export default class MyComponent extends React.Component {

    render() {
        // console.log('app render');
        // console.log('chileren=' + this.props.children.name);
        return (
            <div>
                <RaisedButton label="Default" onClick={() => {
                    console.info("onclick")
                }}/>
                <button onClick={() => {
                    console.info("onclick2")
                }}>按钮2
                </button>
            </div>
        );
    }
}

import React from 'react';
import {Input, Select} from "antd"
import Urls from "../../utils/Urls";

export default class ActorSelect extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            actorList: [],
            searchStr: '',
        }
    }

    searchActor(searchStr) {
        this.setState({
            searchStr: searchStr
        });
        if (searchStr === "") {
            this.setState({
                actorList: []
            });
            return;
        }
        let formData = new FormData();
        formData.append("searchStr", searchStr);
        fetch(Urls.API_SEARCH_ACTOR, {
            method: "post",
            body: formData,
        })
            .then((res) => res.json())
            .then((json) => {
                console.log(JSON.stringify(json));
                this.setState({
                    actorList: json.result
                })
            })
    }

    render() {
        const options = this.state.actorList.map((actor) => {
            return (<Select.Option key={actor.actorName}>{actor.actorName}</Select.Option>)
        });

        return (<div>
            <Select mode="combobox"
                    value={this.state.searchStr}
                    notFoundContent="没找到内容"
                    defaultActiveFirstOption={false}
                    size="default"
                    style={{width: 200}}
                    showArrow={false}
                    filterOption={false}
                    onSearch={(value) => this.searchActor(value)}
                    onSelect={(value, option) => this.props.onActorSelect(value, option)}
                // onChange={(value) => this.searchActor(value)}
                    showSearch={true}
            >
                {options}
            </Select>
        </div>)
    }
}
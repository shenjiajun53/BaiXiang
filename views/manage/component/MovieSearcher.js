import React from 'react';
import {Input, Select} from "antd"
import Urls from "../../utils/Urls";

export default class MovieSearcher extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            movieList: [],
            searchStr: '',
        }
    }

    searchMovie(searchStr) {
        this.setState({
            searchStr: searchStr
        });
        if (searchStr === "") {
            this.setState({
                movieList: []
            });
            return;
        }
        let formData = new FormData();
        formData.append("searchStr", searchStr);
        fetch(Urls.API_SEARCH_MOVIE, {
            method: "post",
            body: formData,
        })
            .then((res) => res.json())
            .then((json) => {
                // console.log(JSON.stringify(json));
                if (json.result.length > 0) {
                    this.setState({
                        movieList: json.result
                    })
                } else {
                    this.setState({
                        movieList:[]
                    })
                }
            })
    }

    render() {
        const options = this.state.movieList.map((movie) => {
            return (<Select.Option key={movie.movieName}>{movie.movieName}</Select.Option>)
        });

        return (<div style={this.props.style}>
            <Select mode="combobox"
                    value={this.state.searchStr}
                    notFoundContent="没找到内容"
                    defaultActiveFirstOption={false}
                    size="default"
                    style={{width: 200}}
                    showArrow={false}
                    filterOption={false}
                    onSearch={(value) => this.searchMovie(value)}
                    onSelect={(value, option) => this.props.onMovieSelect(value, option)}
                // onChange={(value) => this.searchActor(value)}
                    showSearch={true}
            >
                {options}
            </Select>
        </div>)
    }
}
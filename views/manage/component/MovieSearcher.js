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
                        movieList: []
                    })
                }
            })
    }

    render() {
        const options = this.state.movieList.map((movie, index) => {
            return (<Select.Option key={index}>{movie.movieName}</Select.Option>)
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
                    onSelect={(value, option) => {
                        let movie = this.state.movieList[value];
                        this.setState({
                            searchStr: movie.movieName
                        });
                        // this.props.onMovieSelect(value, movie);
                        location.pathname = Urls.MANAGE_EDIT_MOVIE + movie.id;
                    }}
                    showSearch={true}
            >
                {options}
            </Select>
        </div>)
    }
}
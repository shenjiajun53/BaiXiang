/**
 * Created by shenjiajun on 2017/1/29.
 */
import React, {Component} from 'react';
import {Card, Pagination, Table} from 'antd';

// import moment from "moment";
import Urls from "../../utils/Urls"

// injectTapEventPlugin();
const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        width: 150,
    },
    {
        title: 'Name',
        dataIndex: 'movieName',
        key: 'movieName',
        width: 150,
    },
    {
        title: 'movieInfo',
        dataIndex: 'movieInfo',
        key: 'movieInfo',
        width: 150,
    },
    {
        title: 'imdbUrl',
        dataIndex: 'imdbUrl',
        key: 'imdbUrl',
        width: 150,
    }
];

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movieList: null,
            currentPage: 0,
            total: 0,
            pageSize: 0
        }
    }

    componentDidMount() {
        this.getPage(0);
    }

    getPage(page) {
        let data = new FormData();
        data.append("page", page);
        fetch(Urls.API_GET_LAST_CHANGE_MOVIES, {
            method: "post",
            body: data,
            credentials: 'include'     //很重要，设置session,cookie可用
        }).then(
            (response) => {
                return response.json();
            }
        ).then(
            (json) => {
                console.log("response=" + JSON.stringify(json));
                this.setState({
                    movieList: json.result.content,
                    total: json.result.totalElements,
                    pageSize: json.result.size
                })
            }
        ).catch(
            (ex) => {
                console.error('parsing failed', ex);
            });
    }

    render() {
        let movieListView;

        // if (null !== this.state.movieList) {
        //     movieListView = this.state.movieList.map(
        //         (movieInfo) => {
        //             return (
        //                 <div style={{marginBottom: "10px", marginRight: "10px"}}
        //                      key={movieInfo.id}>
        //                     <a href={Urls.MANAGE_EDIT_MOVIE + movieInfo.id}>
        //                         <Card
        //                             style={{
        //                                 width: 200
        //                             }} bodyStyle={{padding: 0}}>
        //                             <img src={Urls.CONTEXT_PATH + movieInfo.posterUrl} alt={movieInfo.movieName}
        //                                  width="100%"/>
        //                             <div style={{padding: "5px"}}>{movieInfo.movieName}</div>
        //                         </Card>
        //                     </a>
        //                 </div>
        //             );
        //         }
        //     );
        // }
        return (
            <div>
                <div style={{
                    display: "flex",
                    flexDirection: "row",
                    flexWrap: "wrap",
                    marginTop: 16,
                    marginLeft: 32,
                    marginBottom: 16,
                    marginRight: 32
                }}>
                    {movieListView}
                </div>
                <Table columns={columns} dataSource={this.state.movieList}/>
                <Pagination showQuickJumper={true}
                            pageSize={this.state.pageSize}
                            total={this.state.total}
                            onChange={(page, pageSize) => {
                                this.getPage(page);
                            }}/>
            </div>
        );
    }
}

export default Home;
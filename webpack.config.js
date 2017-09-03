/**
 * Created by shenjj on 2017/1/23.
 */
const webpack = require('webpack');
const path = require('path');

//命令行 webpack --minimize 压缩
const minimize = process.argv.indexOf('--minimize') !== -1;

module.exports = {
    // 页面入口文件配置
    entry: {
        index: path.resolve(__dirname + '/views/service/js/index.js'),
        signin: path.resolve(__dirname + '/views/service/js/signin.js'),
        header: path.resolve(__dirname + '/views/service/js/header.js'),
        movie_list: path.resolve(__dirname + '/views/service/js/movie_list.js'),
        side_bar: path.resolve(__dirname + '/views/service/js/side_bar.js'),
        VueComponents: path.resolve(__dirname + '/views/service/component/VueComponents.js'),
        UrlUtil: path.resolve(__dirname + '/views/utils/UrlUtil.js'),
        ManageApp: path.resolve(__dirname + '/views/manage/ManageApp.js'),
        UserApp: path.resolve(__dirname + '/views/user/UserApp.js'),
    },
    // 入口文件输出配置
    output: {
        path: path.resolve(__dirname + '/src/main/resources/static'),
        filename: '[name].bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
            },
            {
                test: /.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(png|jpg|gif|svg)$/,
                loader: 'file-loader',
                options: {
                    name: '[name].[ext]?[hash]'
                }
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            }
        ]
    },
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    },
    devServer: {
        historyApiFallback: true,
        noInfo: true
    },
    performance: {
        hints: false
    },
    devtool: '#eval-source-map'
};
if (process.env.NODE_ENV === 'production') {
    module.exports.devtool = '#source-map'
    // http://vue-loader.vuejs.org/en/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            sourceMap: true,
            compress: {
                warnings: false
            }
        }),
        new webpack.LoaderOptionsPlugin({
            minimize: true
        })
    ])
}
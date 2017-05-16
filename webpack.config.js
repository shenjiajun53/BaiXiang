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
        index: path.resolve(__dirname + '/views/js/index.js'),
        signin: path.resolve(__dirname + '/views/js/signin.js'),
        manage_header: path.resolve(__dirname + '/views/js/manage_header.js')
    },
    // 入口文件输出配置
    output: {
        path: path.resolve(__dirname + '/src/main/resources/static'),
        filename: '[name].bundle.js'
    },
    module: {
        // avoid webpack trying to shim process
        noParse: /es6-promise\.js$/,

        // 加载器配置
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue'
            },
            {
                test: /\.js$/,
                // excluding some local linked packages.
                // for normal use cases only node_modules is needed.
                exclude: /node_modules|vue\/dist|vue-router\/|vue-loader\/|vue-hot-reload-api\//,
                loader: 'babel'
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?\S*)?$/,
                loader: 'file-loader',
                query: {
                    name: '[name].[ext]?[hash]'
                }
            }
        ]
    },
    babel: {
        presets: ['es2015'],
        plugins: ['transform-runtime']
    },
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.common.js'
        }
    },


    // 插件项
    plugins: minimize ? [
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false,
            },
            output: {
                comments: false,
            },
        }),
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: JSON.stringify('production')
            }
        }),
    ] : []
};
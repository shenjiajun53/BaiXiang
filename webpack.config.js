/**
 * Created by shenjj on 2017/1/23.
 */
const webpack = require('webpack');
const path = require('path');

//命令行 webpack --minimize 压缩
const minimize = process.argv.indexOf('--minimize') !== -1;

module.exports = {
    // 页面入口文件配置
    entry: path.resolve(__dirname + '/views/js/index.js'),
    // 入口文件输出配置
    output: {
        path: path.resolve(__dirname + '/src/main/resources/static'),
        filename: '[name].bundle.js'
    },
    module: {
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
            }
        ]
    },
    babel: {
        presets: ['es2015'],
        plugins: ['transform-runtime']
    },
    resolve: {
        root: path.join(__dirname, 'node_modules'),
        alias: {
            components: '../../components' // 组件别名,js里引用路径可直接 'components/xxx/yyy'
        },
        extensions: ['', '.js', '.vue', '.scss', '.css']
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
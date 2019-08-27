const config = require('./build/WebPackHelper.js');
const MiniCssExtractPlugin = require('./build/node_modules/mini-css-extract-plugin');
const HtmlWebpackPlugin = require('./build/node_modules/html-webpack-plugin');
const path = require('path');

module.exports = {
    entry: config.moduleName,
    output: {
        path: path.resolve('./bundle'),
        publicPath: '/',
        filename: 'bundle.js'
    },
    resolve: {
        modules: [ path.resolve('output_js'), path.resolve('node_modules') ],
        extensions: ['.js', '.css']
    },
    module: {
        rules: [
            { test: /\.js$/, loader: 'babel-loader' },
            { test: /\.css$/, use: [ 'style-loader', 'css-loader' ] }
        ]
    },
    devtool: '#source-map',
    plugins: [
        new HtmlWebpackPlugin({
            template:  path.resolve('./src/main/web') + '/index.html',
            hash: true,
            inject: true,
            minify: {
                removeComments: true,
                collapseWhitespace: true,
                removeAttributeQuotes: true
            },
        }),
    ]
};

console.log(module.exports.resolve.modules);
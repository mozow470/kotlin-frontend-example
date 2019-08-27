const config = require('./build/WebPackHelper.js');
const MiniCssExtractPlugin = require('./build/node_modules/mini-css-extract-plugin');
const HtmlWebpackPlugin = require('./build/node_modules/html-webpack-plugin');
const TerserPlugin = require('./build/node_modules/terser-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("./build/node_modules/optimize-css-assets-webpack-plugin");
const HardSourceWebpackPlugin  = require("./build/node_modules/hard-source-webpack-plugin");
const path = require('path');

module.exports = {
    entry: config.bundlePath,
    output: {
        path: path.resolve('./bundle'),
        publicPath: '/',
        filename: 'bundle.js'
    },
    resolve: {
        modules: [path.resolve('output_js'), path.resolve('node_modules')],
        extensions: ['.js', '.css'],
        alias: {
            // Support React Native Web
            'srcMain': './../../src/main',
        },
    },
    module: {
        rules: [
            {test: /\.js$/, loader: 'babel-loader'},
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    {
                        loader: 'sass-loader',
                        options: {
                            minimize: true,
                            sourceMap: false,
                        }
                    }
                ]
            },
            {
                test: /\.(jpg|jpeg|gif|png|svg)$/,
                loader: 'file-loader',
                options: {
                    limit: 1024,
                    name: 'images/[name].[ext]?[hash]'
                }
            },
            {
                test: /\.(woff|woff2|eot|ttf)$/,
                loader: 'file-loader',
                options: {
                    limit: 1024,
                    name: 'fonts/[name].[ext]?[hash]'
                }
            },
        ]
    },
    devtool: '#source-map',
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'css/[name].css',
        }),
        new HtmlWebpackPlugin({
            template: './../src/main/web/index.html',
            hash: true,
            inject: true,
            minify: {
                removeComments: true,
                collapseWhitespace: true,
                removeAttributeQuotes: true
            },
        }),
    ],
    optimization: {
        splitChunks: {
            name: 'vendor',
            chunks: 'initial',
        },
        minimizer: [
            new TerserPlugin({
                cache: true,
                parallel: true,
                sourceMap: false // set to true if you want JS source maps
            }),
            new OptimizeCSSAssetsPlugin({}),
        ]
    },
    performance: {
        hints: false
    },
};

// console.log(module.exports.resolve.modules);
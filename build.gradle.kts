import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension

plugins {
    id("kotlin2js") version "1.3.41"
    id("org.jetbrains.kotlin.frontend") version "0.0.45"
}

group = "me.mozhok.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "http://dl.bintray.com/kotlinx/kotlinx" )
    maven (url =  "http://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.83-kotlin-1.3.41")
    implementation("org.jetbrains.kotlinx:kotlinx-html-js")
    implementation("org.jetbrains:kotlin-react:16.9.0-pre.83-kotlin-1.3.41")
    implementation("org.jetbrains:kotlin-react-dom:16.9.0-pre.83-kotlin-1.3.41")
    implementation("org.jetbrains:kotlin-react-router-dom:4.3.1-pre.83-kotlin-1.3.41")
    testCompile("junit", "junit", "4.12")
}

kotlinFrontend {
    downloadNodeJsVersion = "latest"
    sourceMaps = false

    npm {
        replaceVersion("kotlinx-html-js", "1.1.0")

        dependency("react")
        dependency("react-dom")
        dependency("react-router-dom")
        dependency("jquery")
        dependency("core-js", version = "3")

        devDependency("webpack")
        devDependency("webpack-serve")
        devDependency("webpack-dev-server")
        devDependency("css-loader")
        devDependency("style-loader")
        devDependency("babel-loader")
        devDependency("@babel/core", version = "7.1.2")
        devDependency("@babel/preset-env", version = "7.4.5")
        devDependency("babel-core")
        devDependency("html-webpack-plugin")
        devDependency("mini-css-extract-plugin")
    }

    bundle("webpack", delegateClosureOf<WebPackExtension> {
//        mode = "development"
        mode = "production"
        bundleName = "main"
        sourceMapEnabled = true
        contentPath = file("src/main/web")
        port = 3000
        webpackConfigFile = "${project.projectDir.path}/webpack.config.js"
    })
}

tasks {
    compileKotlin2Js {
        kotlinOptions.metaInfo = true
        kotlinOptions.outputFile = "${project.buildDir.path}/output_js/${project.name}.js"
        kotlinOptions.sourceMap = true
        kotlinOptions.moduleKind = "commonjs"
        kotlinOptions.main = "call"
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    experimental {
        coroutines
    }
}
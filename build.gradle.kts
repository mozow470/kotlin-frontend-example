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
    sourceMaps = false

    npm {
        replaceVersion("kotlinx-html-js", "1.1.0")

        dependency("react")
        dependency("react-dom")
        dependency("react-router-dom")
        dependency("jquery")

        devDependency("css-loader")
        devDependency("style-loader")
        devDependency("babel-loader")
        devDependency("babel-core")
    }
}

tasks {
    compileKotlin2Js {
        kotlinOptions.metaInfo = true
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
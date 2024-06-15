object BuildConfig {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 101
    const val versionName = "1.0.1"
}

object Versions {
    const val kotlinVersion = "1.4.21"
    const val arouterPlugin = "1.0.2"
    const val arouterVersion = "1.5.2"
    const val glide = "4.15.0"
    const val loggerVersion = "2.2.0"
    const val buglyVersion = "3.3.3"
    const val mmkvVersion = "1.2.10"
    const val stethoVerison = "1.6.0"
    const val multidex = "2.0.1"
    const val brvah = "3.0.4"
    const val lifecycle = "2.3.1"
    const val okhttp = "4.9.0"
    const val retrofit = "2.9.0"
    const val room="2.3.0"
}

object GradleDeps {

    //用来检查所有依赖最新版本
    const val benmanesGradleVersionPlugin = "com.github.ben-manes:gradle-versions-plugin:0.39.0"
}

object KotlinDeps {
    //java转kotlin"
//    如果（jdk_version == 1.8）
//    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
//    如果（jdk_version == 1.7）
//    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    如果（jdk_version <1.7）
//    implementation"org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    const val kotlinStdlib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"//java转kotlin
    const val kotlinReflect =
        "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"//主要作用是为 Kotlin 提供了一种类型安全的反射机制
}


object NetWorkDeps {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"//里面包含
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"


}

//数据库
object DataDeps {
    //腾讯mmkv轻量级存储取代SharedPreferences和datastore
    const val mmkv = "com.tencent:mmkv-static:${Versions.mmkvVersion}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"//支持 Kotlin 协程、Kotlin 协程
}


object ArouterDeps {
    const val arouterRegisterPlugin =
        "com.alibaba:arouter-register:${Versions.arouterPlugin}"////这个插件是在 transform jar 的时候直接把路由表通过 asm 工具直接写到 sdk 的类里面，就不用启动的时候扫描 dex 了，用于支持第三方App加固时自动注册
    const val arouterApi = "com.alibaba:arouter-api:${Versions.arouterVersion}"
    const val arouterCompiler =
        "com.alibaba:arouter-compiler:${Versions.arouterVersion}"//所有使用了arouter的model需要添加，否则跳转报错
}

object GlideDeps {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}


object CommonDeps {
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.0"
    const val appcompat = "androidx.appcompat:appcompat:1.3.1"
    const val material = "com.google.android.material:material:1.4.0"


    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val brvah = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.brvah}"
    const val refreshLayout = "io.github.scwang90:refresh-layout-kernel:2.1.0"
    const val refreshHeader = "io.github.scwang90:refresh-header-classics:2.1.0"
    const val refreshFooter = "io.github.scwang90:refresh-footer-classics:2.1.0"

}

object DebugDeps {
    const val logger = "com.orhanobut:logger:${Versions.loggerVersion}"
    const val bugly = "com.tencent.bugly:crashreport:${Versions.buglyVersion}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stethoVerison}"

}

object LifeCycleDeps {
    const val runtimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"//lifecycleScope
    const val viewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"//viewModelScope需要用到，用了该依赖不需用已被弃用的 androidx.lifecycle:lifecycle-extensions:2.2.0
}

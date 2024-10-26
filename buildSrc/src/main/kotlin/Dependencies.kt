object BuildConfig {
    const val compileSdkVersion = 35
    const val minSdkVersion = 23
    const val targetSdkVersion = 35
    const val versionCode = 101
    const val versionName = "1.0.1"
}

object Versions {
    const val kotlinVersion = "1.4.21"
    const val arouterPlugin = "1.0.2"
    const val arouterVersion = "1.5.2"
    const val glide = "4.16.0"
    const val loggerVersion = "2.2.0"
    const val buglyVersion = "3.3.3"
    const val stethoVerison = "1.6.0"
    const val lifecycle = "2.8.5"
    const val retrofit = "2.9.0"
    const val room = "2.6.1"
}

object NetWorkDeps {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"//里面包含
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.12.0"
}

object DataDeps {
    //腾讯mmkv轻量级存储取代SharedPreferences和datastore
    const val mmkv = "com.tencent:mmkv-static:1.3.9"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    //支持 Kotlin 协程、Kotlin 协程,里面包含了库 androidx.room:room-runtime
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object ArouterDeps {
    //这个插件是在 transform jar 的时候直接把路由表通过 asm 工具直接写到 sdk 的类里面，就不用启动的时候扫描 dex 了，用于支持第三方App加固时自动注册
    const val arouterRegisterPlugin = "com.alibaba:arouter-register:${Versions.arouterPlugin}"
    const val arouterApi = "com.alibaba:arouter-api:${Versions.arouterVersion}"

    //所有使用了arouter的model需要添加，否则跳转报错
    const val arouterCompiler = "com.alibaba:arouter-compiler:${Versions.arouterVersion}"
}

object GlideDeps {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}


object CommonDeps {
    const val recyclerview = "androidx.recyclerview:recyclerview:1.3.2"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.0"
    const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val material = "com.google.android.material:material:1.12.0"
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val brvah = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"
    const val refreshLayout = "io.github.scwang90:refresh-layout-kernel:2.1.0"
    const val refreshHeader = "io.github.scwang90:refresh-header-classics:2.1.0"
    const val refreshFooter = "io.github.scwang90:refresh-footer-classics:2.1.0"

    const val media3ExoPlayer = "androidx.media3:media3-exoplayer:1.3.1"
    const val media3Ui="androidx.media3:media3-ui:1.3.1"
//    const val media3Session="androidx.media3:media3-session:1.3.1"

}

object DebugDeps {
    const val logger = "com.orhanobut:logger:${Versions.loggerVersion}"
    const val bugly = "com.tencent.bugly:crashreport:${Versions.buglyVersion}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stethoVerison}"

}

object KotlinDeps {
    //主要作用是为 Kotlin 提供了一种类型安全的反射机制
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:1.9.22"

    //为Jetpack提供简洁的惯用Kotlin代码
    const val coreKtx = "androidx.core:core-ktx:1.13.0"
}

object LifeCycleDeps {
    //lifecycleScope
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    /**
     * viewModelScope需要用到，用了该依赖不需用已被弃用的 androidx.lifecycle:lifecycle-extensions:2.2.0
     * 内部包含以下三个依赖
     * androidx.lifecycle:lifecycle-viewmodel
     * org.jetbrains.kotlinx:kotlinx-coroutines-android
     * org.jetbrains.kotlin:kotlin-stdlib
     */
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"

    const val activityKtx = "androidx.activity:activity-ktx:1.9.2"


}

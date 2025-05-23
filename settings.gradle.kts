
//主要用于管理和配置插件的仓库位置、版本、应用方式等
pluginManagement {
    repositories {
        google()
        mavenCentral()
        // 是 Gradle 提供的一个默认插件仓库，用于查找和下载公开发布的 Gradle 插件
        // plugins：只适用于 Plugin Portal 上的插件
        // apply plugin：对于未发布在 Plugin Portal 上或自定义的插件，可以使用 apply plugin: 'xxx' 方式，但必须先在 buildscript 块中添加插件的依赖（通过 classpath 声明）
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { setUrl("https://jitpack.io") }
        google()
        mavenCentral()
    }
}
rootProject.name = "LazyStudy"
include(":app")
include(":module_video")
include(":module_search")
include(":module_mine")
include(":module_login")
include(":module_function")
include(":lib_database")
include(":lib_common")
include(":lib_base")
include(":lib:network")
include(":lib:device")
include(":lib_ad")


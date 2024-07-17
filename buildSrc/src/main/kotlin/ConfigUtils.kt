//import org.gradle.api.Project
//import org.gradle.api.ProjectEvaluationListener
//import org.gradle.api.ProjectState
//import org.gradle.api.artifacts.dsl.RepositoryHandler
//import org.gradle.api.invocation.Gradle
//import org.gradle.kotlin.dsl.apply
//
//object ConfigUtils {
//    @JvmStatic
//    fun init(gradle: Gradle) {
//        addCommonGradle(gradle)
//    }
//    @JvmStatic
//    fun addRepos(handler: RepositoryHandler?) = handler?.run { //统一提取maven依赖
//        flatDir { dirs("libs") }//依赖aar需要设置，https://zhuanlan.zhihu.com/p/44371449
//        google()
//        gradlePluginPortal()//gradle-versions-plugin需要
//        mavenCentral()//因JCenter仓库自2021.5.1关闭，谷歌推荐使用Maven Central仓库，并创建项目默认将jcenter()变成mavenCentral()
//        maven { setUrl("https://jitpack.io") }//或 it.maven { url = URI("https://jitpack.io") } }
//
//        maven { setUrl("http://172.18.10.120:8081/repository/android-group/") }
//    }
//
//    private fun addCommonGradle(gradle: Gradle) {
//        gradle.addProjectEvaluationListener(object : ProjectEvaluationListener {
//            override fun beforeEvaluate(project: Project) {//在解析build.gradle文件之前执行
//                GLog.d(project.subprojects.toString() + "${project.name} applies buildApp.gradle")
//                if (project.subprojects.isEmpty()){
//                    if (project.name.startsWith("module_")||project.name.startsWith("lib_")) {
//                        GLog.d("$project applies buildApp.gradle")
//                        project.apply ("${project.rootDir.path}/buildLib.gradle")
//                    }
//                }
//            }
//
//            override fun afterEvaluate(project: Project, state: ProjectState) {
//                //所有模块都已配置完成
//            }
//        })
//    }
//
//}
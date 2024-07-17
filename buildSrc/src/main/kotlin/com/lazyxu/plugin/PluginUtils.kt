//package com.lazyxu.plugin
//
//import com.google.gson.stream.JsonWriter
//import org.gradle.api.Project
//import java.io.FileOutputStream
//import java.io.OutputStreamWriter
//
//object PluginUtils {
//    /**
//     * 存在问题点，可能同一个sdk有多个地方使用且sdk版本不同，获取的不是实际想要的
//     * 但此处获取的是自己项目独有的，则忽略该问题
//     */
//    fun getDependenciesVersion(project: Project, vararg sdksname: String) {
//        val fileOutputStream = FileOutputStream("./sdkconfig.json")
//        val jsonWriter = JsonWriter(OutputStreamWriter(fileOutputStream, "UTF-8"))
//        jsonWriter.beginObject()
//        val jsonmap = mutableMapOf<String, String>()
//        project.dependencies.components.all {
//            val sdkDependencies = this.toString()
//            sdksname.forEach {
//                if (sdkDependencies.contains(it)) {
//                    jsonmap[it] = sdkDependencies.substring(sdkDependencies.lastIndexOf(":") + 1)
//                    if (jsonmap.size == sdksname.size) {
//                        jsonmap.forEach { (key, value) ->
//                            jsonWriter.name(key).value(value)
//                        }
//                        jsonWriter.endObject()
//                        jsonWriter.close()
//                    }
//                }
//            }
//        }
//    }
//
//    fun checkIsRelease(project: Project) {
//
//
//        project.tasks.whenTaskAdded {
//            if (this.name == "installFreeNormalRelease") {
//                getSnsDependencies(project)
//                this.doFirst {
//                    if (!rules.isNullOrEmpty()) {
//                        val stringBuffer = StringBuilder()
//                        rules.forEach {
//                            stringBuffer.append("$it \n")
//                        }
//                        throw Exception("dependencies has snapshot version: \n $stringBuffer")
//                    }
//                }
//            }
//        }
//    }
//
//    private val rules = mutableListOf<String>()
//    private fun getSnsDependencies(project: Project) {
//        rules.clear()
//        project.dependencies.components.all {
//            val rule = this.toString()
//            if (!rules.contains(rule) && rule.contains("-SNAPSHOT")) {
//                rules.add(rule)
//            }
//        }
//    }
//}

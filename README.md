#### 一. 使用`buildSrc`全局管理依赖库
**使用`buildSrc`优点**
1. 共享 `buildSrc` 库工件的引用，全局只有一个地方可以修改它
2. 支持 `AndroidStudio` 自动补全
3. 支持 `AndroidStudio` 单击跳转，双向查找

**使用`buildSrc`缺点**：无法查看依赖库是否有更新，因此引入[ben-manes的gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin)

⚠️注意
1. **使用`buildSrc`全局管理依赖库正确方式**
   1.  在项目里面创建`buildSrc`文件夹(一定不要以创建module方式不然报错找不到)
   2. 在`buildSrc`文件夹里面创建`build.gradle.kts`文件,内容如下
   3. 在`buildSrc`文件夹里面创建`src\main\java`文件夹，并在java文件夹创建一个或多个你需要用到的文件即可
```
//build.gradle.kts内容如下
plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}
```
2. **使用[ben-manes的gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin)查看依赖库更新情况**
   - window在studio的terminal窗口输入`gradlew dependencyUpdates`命令，会生成`build\dependencyUpdates\report.txt`文件，里面即可查看依赖库更新情况


-------------------------

#### 二. [logger](https://github.com/orhanobut/logger)打印日志




























--------------



待优化使用

2. 图片

   1. 压缩（使用glide加载多个大图容易引起内存溢出）
   2. GlidePalette智能配色给自己设置多彩的阴影
   3. LuBan与Compressor压缩
   4. 头像拍照和相册获取剪切

3. Recyclerview
   1. 瀑布流

4. 用jsoup解析html

5. 组件化

   1. [微信支付分享组件化](https://github.com/pokercc/CustomPackage)
   2. [fragment问题](https://juejin.im/post/5c46e6fb6fb9a049a5713bcc)

#### xml属性
android:adjustViewBounds="true"
解决图片变形问题



3. 适配

- 今日头条屏幕适配
- 权限适配（6.0危险权限；7.0拍照、安装apk、相册截图）
- 全面屏、刘海屏适配；电池栏适配（https://github.com/gyf-dev/ImmersionBar）
> 注意`AndroidManifest.xml`中需要先添加下面配置
>```
>        <!-- 适配全面屏 -->
>        <meta-data
>            android:name="android.max_aspect"
>            android:value="2.4" />
>        <!-- 适配华为(hua wei)刘海屏 -->
>        <meta-data
>            android:name="android.notch_support"
>            android:value="true" />
>        <!-- 适配小米(xiao mi)刘海屏 -->
>        <meta-data
>            android:name="notch.config"
>            android:value="portrait|landscape" />
> ```

- icon图标适配
- 9.0网络适配
- 通知栏适配（apk下载）


2. 数据加载
https://github.com/KingJA/LoadSir

3. 版本更新
使用多线程断点续传方式下载（线程1下载1-50，线程2下载51-100部分）
4. 设置字体大小


[第三方汇总](https://blog.csdn.net/jim19890923/article/details/80306160?utm_source=blogkpcl4)


[toast](https://github.com/GrenderG/Toasty)

[DialogUtil](https://github.com/hss01248/DialogUtil )


[DropDownMenu](https://github.com/dongjunkun/DropDownMenu)

[toast](https://github.com/GrenderG/Toasty)

[toast](https://github.com/GrenderG/Toasty)

[toast](https://github.com/GrenderG/Toasty)

[toast](https://github.com/GrenderG/Toasty)

[toast](https://github.com/GrenderG/Toasty)


https://github.com/AppIntro/AppIntro 欢迎页


项目亮点：
秒秒分期：各种自定义样式，loading加载效果，下拉刷新效果
自定义锁屏、自定义回款计划日历、语音聊天


1. icon图标怎么适配的？（注意别的app的icon 图标适配）
2. glide如何使用（glide如何防止oom处理）



不懂问题
1. app关闭，内存缓存会被自动清理吗？


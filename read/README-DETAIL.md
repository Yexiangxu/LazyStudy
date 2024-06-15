//, CoroutineScope by MainScope()

   
#### 二. 调试
1. [logger](https://github.com/orhanobut/logger)打印日志
  使用
2. 使用[stetho](https://github.com/orhanobut/logger)查看数据存储网络等


#### 二.使用
1. 仅仅支持绑定 View
2. 不需要在布局文件中添加`layout`标签
3. 相比于`kotlin-android-extensions`(源码是通过Hashmap获取id会增加内存开支)避免了空异常
4. 效率高于`DataBinding`，因为避免了与数据绑定相关的开销和性能问题
5. 需要在模块级`build.gradle`文件中添加`viewBinding = true`即可使用


#### 二.使用livedata + viewmodel
setValue()只能在主线程中调用，postValue()可以在任何线程中调用

-------------------------


-------------------------

#### 资源命名冲突
问题：组件化中，资源命名冲突是一个比较严重的问题，由于在打包时会进行资源的合并，如果两个模块中有两个相同名字的文件，那么最后只会保留一份
解决方案：
在`lib_res`中存放公共res资源文件，其余module中若存在res资源，需要在相应module的build.gradle中添加如下配置
```
android {
    resourcePrefix "前缀_"
}
```
该配置会校验该module中res资源文件命名，如无该前缀，AS就会进行警告提示



待使用
MotionLayout
Hilt

flexWrap 换行方式
nowrap ：默认值，不换行
wrap：按正常方向换行
wrap_reverse：按反方向换行

测试版本回退

























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


1. surfaceview使用
2. 下载（apk版本更新）
3. 设置接口请求失败重试次数(只针对某些接口不是所有的接口)


1. [使用`buildSrc`全局管理依赖库](https://github.com/Yexiangxu/LazyStudy/blob/master/read/%E4%BD%BF%E7%94%A8buildSrc%E5%85%A8%E5%B1%80%E7%AE%A1%E7%90%86%E4%BE%9D%E8%B5%96%E5%BA%93.md)
2. [统一管理gradle](https://github.com/Yexiangxu/LazyStudy/blob/master/read/%E7%BB%9F%E4%B8%80%E7%AE%A1%E7%90%86gradle.md)

架构层面

1. sdk开发
    - keep最外层使用类，内部实现类混淆
    - aar

功能层面

1. 在`BaseActivity`统一封装标题
2. Fragment使用及懒加载
3. 夜间模式切换(通过value-night实现)

- Toolbar
- SwitchCompat
- MaterialButton(不需要再使用各种shape了)

调试

1. 使用`Logger`日志库，封装在 `LogUtils` 可随时更换库
2. 配置 `networkSecurityConfig` 设置http明文使用

#### 不用控件

1. SearchView

https://juejin.cn/post/7145861715798802462

### 设置aar依赖

#### 方式一：统一设置

`api fileTree(dir: 'libs', include: ['*.jar', '*.aar'])`

优点：方便快捷，简单粗暴，不需要每个aar都设置
缺点：特殊场景无法处理。

- eg1：debug和release情况使用不同aar，该方式打包无法通过
- eg2：Jenkins打包动态配置某个aar可打可不打，该方式无法处理

手写检测SNAPSHOT插件
https://juejin.cn/post/7292416512333840438
手写检测某个so来自哪个sdk
https://juejin.cn/post/7287429638019448888

注解使用详解
@DslMarker
属性委托操作符、内联属性、内联属性





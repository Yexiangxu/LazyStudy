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

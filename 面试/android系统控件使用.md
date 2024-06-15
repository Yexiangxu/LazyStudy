#### 1. ToolBar

```
    <androidx.appcompat.widget.Toolbar
        app:contentInsetStartWithNavigation="@dimen/dp_0"//设置title与navigationIcon间隔,默认是16
        app:contentInsetStart="@dimen/dp_0"             //contentInset 内容区间距
    
        android:id="@+id/tbTitle"
        android:layout_height="@dimen/dp_48"
        android:background="@color/colorAccent"           //设置标题背景
        app:titleMarginStart="@dimen/dp_0"                //设置标题区域的 margin 值
        app:layout_scrollFlags="enterAlways|scroll"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        app:titleTextAppearance="@style/ToolBarTitle"    //设置标题文字相关属性，如：字体颜色、大小
        app:navigationIcon="@drawable/svg_back_white"     //设置返回图标
        android:gravity="center_vertical"
        android:paddingStart="@dimen/dp_0"               //设置toolbar中图标距离左边距离
        app:title="懒人自学"                              //设置标题
        app:buttonGravity="center_vertical"              //设置居中展示
        android:layout_width="match_parent"/>
```

```
    <!--ToolBar 标题-->
    <style name="ToolBarTitle" parent="@style/TextAppearance.Widget.AppCompat.Toolbar.Title">
        <item name="android:textSize">@dimen/sp_18</item>
        <item name="android:textColor">@color/white</item>
    </style>
```

- 设置标题
  `mViewBinding.tbTitle.title = "标题"`
- 设置返回图标
  `mViewBinding.tbTitle.setNavigationIcon(R.drawable.svg_back_black)`
- 去除返回键
  `mViewBinding.includeTitle.tbTitle.navigationIcon = null`
- 返回键点击
  `mViewBinding.includeTitle.tbTitle.setNavigationOnClickListener {}`

#### 2. SwitchCompat

```
    <androidx.appcompat.widget.SwitchCompat
        android:id="@+id/scShake"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"
        android:thumb="@drawable/switch_thumb"
        app:track="@drawable/selector_switch_track" />
```

- switch_thumb设置移动的圆圈

```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="@color/white" />
    <stroke
        android:width="@dimen/dp_1"
        android:color="#00000000" />
    <size
        android:width="@dimen/dp_16"
        android:height="@dimen/dp_16" />
</shape>

```

> 注意：设置 SwitchCompat 高度用此属性，可通过 app:switchMinWidth="" 设置宽度，在 layout_width 和
> layout_height 设置宽高无效

- selector_switch_track设置选中和未选中背景

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true">
        <shape android:shape="rectangle">
            <solid android:color="@color/colorAccent" />
            <corners android:radius="@dimen/dp_25" />

        </shape>
    </item>
    <item android:state_checked="false">
        <shape android:shape="rectangle">
            <solid android:color="#E3E3E3" />
            <corners android:radius="@dimen/dp_25" />
        </shape>
    </item>
</selector>
```

- SwitchCompat 监听
  `mViewBinding.scShake.setOnCheckedChangeListener{_,isChecked ->}`

#### 3. MaterialButton

```
    <com.google.android.material.button.MaterialButton
        android:layout_width="@dimen/dp_180"
        android:layout_height="@dimen/dp_50"
        android:layout_gravity="center_horizontal"        
        android:backgroundTint="@color/colorAccent"      //设置背景颜色
        android:gravity="center"
        android:textColor="@color/white"
        android:theme="@style/Theme.MaterialComponents.Light.NoActionBar" //见下面注意
        app:cornerRadius="@dimen/dp_25" />
```

- 出现问题：java.lang.reflect.InvocationTargetException 反射异常
- 解决方法：进行主题替换即可

  `android:theme="@style/Theme.MaterialComponents.Light.NoActionBar">`
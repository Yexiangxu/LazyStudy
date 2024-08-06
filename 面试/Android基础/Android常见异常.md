1. [IllegalArgumentException(非法参数异常) —— 向方法传递了一个不合法或不正确的参数](#IllegalArgumentException)

### <span id = "IllegalArgumentException">IllegalArgumentException(非法参数异常) —— 向方法传递了一个不合法或不正确的参数</span>

eg：`Notification` 的自定义布局是 `RemoteViews`，和其他 `RemoteViews` 一样，在自定义视图布局文件中，仅支持 `FrameLayout、LinearLayout、RelativeLayout` 三种布局和 `AnalogClock、Chronometer、Button、ImageButton、ImageView、ProgressBar、TextView、ViewFlipper、ListView、GridView、StackView、AdapterViewFlipper` 这些显示控件，不支持这些类的子类或Android提供的其他控件,否则会引起异常
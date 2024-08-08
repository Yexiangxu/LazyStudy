1. [`adjustViewBounds` 详解](#adjustViewBounds)
2.

-----------------

### <span id = "adjustViewBounds">`adjustViewBounds` 详解</span>

**出现问题**：接口每次返回的图宽高比例不一样导致图片变形

**问题描述**：Imageview控件宽高比例为1:1，给的图片宽高比例为2:
3，将图片放在imageview中展示。若设置`android:scaleType="fitXY"`
图片会变形；
若设置`android:scaleType="centerCrop"`
图片会有部分不在控件内显示；若设置`android:scaleType="fitCenter"`(即不设置该属性默认情况方式)
，imageview若设置背景色所有会有背景色，该怎么解决呢？

```agsl
 <ImageView
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:adjustViewBounds="true"
        android:src="@drawable/img_front" />
```

**让ImageView的比例和原始图片一样。**
如上，设置imageview宽固定，高wrap_content自适应，添加`android:adjustViewBounds="true"`
即可解决，此时imageview的宽固定200dp，高为图片以宽为200dp，宽高比例为2:
3的值即300dp。

> 注意
>
> - imageview不能同时设置宽高固定，否则该属性无效
> - 不是在所有情况下都启用。因为调整视图边界会导致重新计算布局，可能会对性能产生一定的影响


-------------------
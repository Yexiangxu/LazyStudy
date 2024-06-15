
[使用多进程](./Android多进程.md)

```agsl
// 在 Android 4.1（Jelly Bean）及以上版本中，可以通过以下方式禁用硬件加速
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
}
```




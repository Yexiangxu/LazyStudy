1. 基本属性使用
2. [遇到问题](#problem)
3. [实现一个带有顶部悬停效果的ItemDecoration](https://juejin.cn/post/6998924524548784141)
4. SnapHelper使用详解
    - SnapHelper是通过设置`OnScrollListener`和`OnFlingListener`来实现`RecyclerView`抛的动作和滚动监听
5. layoutManager

------------------

```agsl
setHasFixedSize(true)//当RecyclerView 的内容大小不会影响它本身的宽度或高度时，可以调用这个方法
```

-----------------

### <span id = "problem">遇到问题</span>

1. 间距问题

- 问题出现场景
  `recyclerview中嵌套recyclerview，反复刷新数据出现item间距越变越大`

> 一般下拉刷新没遇到是因为仅刷新了数据没有重复设置 `addItemDecoration`，而嵌套刷新每次都在`Adapter`
> 中设置了`addItemDecoration`所以很容易出现

- 解决方案：避免重复设置`addItemDecoration`

```agsl
if (recyclerView.itemDecorationCount==0) {
    recyclerView.addItemDecoration(xxx)
}
```

使用 `recyclerview` 设置不同样式
1. `recyclerview` 是 `GridLayoutManager` 形式，其中一个占两个位置（eg：星期1234567，分两行四列，7占最后两列）

```agsl
val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return when (position) {
            6 -> 2 // 第二行最后一个项目占两个列
            else -> 1
        }
    }
}
(mViewBindingDialog.rvSign.layoutManager as GridLayoutManager).spanSizeLookup = spanSizeLookup
```

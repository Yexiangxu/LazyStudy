


常见控件

SizedBox

-----------------


```agsl
class CustomContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100,
      height: 100,
      // color: Colors.red,
      //decoration 为容器设置背景、边框、阴影、圆角等效果
      decoration: BoxDecoration(
        //可用 Border.all 统一设置所有边框，也可用 Border 上下左右分别设置
        border: Border.all(
          color: Colors.black,
          width: 2,
        ),
        // border: Border(
        //   top: BorderSide(color: Colors.red, width: 2),
        //   bottom: BorderSide(color: Colors.blue, width: 3),
        // ),
        //可用 BorderRadius.circular 统一设置所有角弧度，也可用 BorderRadius.only 上下所有分别设置
        borderRadius: BorderRadius.circular(20),
        // borderRadius: BorderRadius.only(
        //   topLeft: Radius.circular(10),
        //   bottomRight: Radius.circular(10),
        // ),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.5), //设置阴影颜色，这里是灰色并且有 50% 的透明度
            spreadRadius: 5, // 控制阴影的扩散半径，值越大阴影扩散越大
            blurRadius: 7, // 控制阴影的模糊半径，值越大阴影越模糊
            offset: const Offset(0, 3), // 控制阴影的偏移量，这里是 x 方向不变，y 方向偏移 3 个单位
          ),
        ],
        image: const DecorationImage(
          image: AssetImage('images/test1.jpg'),
          fit: BoxFit.cover,
          //给图片加过滤器
          colorFilter: ColorFilter.mode(
            Colors.red,
            BlendMode.darken , // 混合模式
          ),
        ),
        // gradient: const LinearGradient(
        //   colors: [Colors.red, Colors.blue],
        //   begin: Alignment.topLeft,
        //   end: Alignment.bottomRight,
        // ),
      ),
    );
  }
}
```



修饰组件的外观，通常应用于容器组件如 `Container`,它通过与 `BoxDecoration` 结合使用，能够为组件添加背景颜色、渐变、边框、阴影等效果


-------------------------





-------------

图片尺寸文字等适配
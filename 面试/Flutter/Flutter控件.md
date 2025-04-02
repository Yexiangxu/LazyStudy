1. Text 
    ```agsl
    Text(
        'Hello, Flutter!',
        style: TextStyle(
        fontSize: 24,
        // 字体大小
        color: Colors.blue,
        // 字体颜色
        fontWeight: FontWeight.bold,
        // 字体加粗
        fontStyle: FontStyle.normal,
        // 斜体
        backgroundColor: Colors.yellow, // 设置背景颜色
        ),
        textAlign: TextAlign.center,
        //文本在其父容器中的水平位置
        textDirection: TextDirection.ltr,
        //设置文本的书写方向，默认从左到右
        maxLines: 1,
        overflow: TextOverflow.ellipsis, // 超出时显示省略号
    )
    ```

```agsl
RichText(
                text: TextSpan(
                  text: 'Hello ',
                  style: TextStyle(color: Colors.black, fontSize: 24),
                  children: <TextSpan>[
                    TextSpan(
                      text: 'Flutter',
                      style: TextStyle(fontWeight: FontWeight.bold, color: Colors.blue),
                    ),
                    TextSpan(
                      text: '!',
                      style: TextStyle(color: Colors.green),
                    ),
                  ],
                ),
              )
```
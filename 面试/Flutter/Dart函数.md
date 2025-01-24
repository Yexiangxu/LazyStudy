### 方法定义

1. 可选参数
    ```agsl
    //String printUserInfo(String username, {int? age, String? sex}) {
    String printUserInfo(String username, {int age=18, String? sex="男"}){
    return "姓名：$username，年龄：${age ?? '保密'}";
    }
    ```
    - 参数要么设置默认值、要么可空 ，`{int age, String sex}`或`[int age, String sex]`会报错

    - 大括号{}是 **命名参数方式**,调用函数时，指定参数的名称来传递参数，顺序可以不固定
      ```
      String printUserInfo(String username, {int? age=18, String? sex})
      //错误 `printUserInfo("lazyxu",18,'男')`
      //正确 `printUserInfo("lazyxu",age:25,sex:'男')`
      ```
    - 方括号[]是 **可选参数方式**,调用函数时，参数是根据位置传递的，传递参数时要遵守顺序;
      ```
      String printUserInfo(String username, [int? age=18, String? sex])
      //正确 `printUserInfo("lazyxu",18,'男')`
      //错误 `printUserInfo("lazyxu",age:25,sex:'男')`
      ```

2. 默认参数
    ```agsl
    String printUserInfo(String username, [int? age=18]) {}
    ```

3. get、set方法：直接通过访问属性的方式访问方法

    ```agsl
    class Rect {
      num height;
      num width;
      Rect(this.height, this.width);
      get getArea {
        return this.height * this.width;
      }
      set setArea(value) {
        this.width = value / this.height;
      }
    }
    ```


1. 箭头函数

```agsl
int add(int a, int b) => a + b;

```
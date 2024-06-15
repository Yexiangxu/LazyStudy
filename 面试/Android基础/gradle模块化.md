1. `api、implementation、compileOnly、runtimeOnly` 使用




依赖不可传递
与compileOnly相反，它修饰的依赖不会添加到编译路径中，但是被打包到apk中，运行时使用。

目前没用过


```agsl
    //注意：只是提示，用于隔离不同model的资源文件
    //Gradle构建系统会在编译资源之前检查所有资源名称(直接到module的string等查看即可)，以确保它们在项目中是唯一的。这有助于避免因为资源名称冲突而导致的编译错误或运行时错误
   android{
      resourcePrefix "$project.name"
   }
```



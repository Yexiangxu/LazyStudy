1. [屏幕适配](../Android适配/1.Android屏幕适配.md)
2. [BitmapConfig图片质量(色彩)](#BitmapConfig)
3. [PNG和JPEG](#pngandjpeg)
4. [内存大小计算公式](#pic_memory)
5. [和 `drawable` 文件夹的关系](#re_drawable)
6. [和 `ImageView` 的宽高的关系](#re_img)
7. [BitmapFactory](#BitmapFactory)
8. [图片压缩方案](#compress)
    - inSampleSize(Luban压缩) —— 采样率
    - inTargetDensity
    - Bitmap.Config

### 一. 图片优化前预备知识

#### 1. [屏幕适配](../Android适配/1.Android屏幕适配.md)

#### <span id = "BitmapConfig">2. BitmapConfig图片质量(色彩)</span>

- A：透明度（Alpha）
- R：红色（Red）
- G：绿（Green）
- B：蓝（Blue）

一个图片的大小，除了跟尺寸有关，还跟图片质量有关。下面先来了解下**四种色彩模式ARGB_8888、ARGB_4444、
RGB_565、 ALPHA_8的区别**

- **Bitmap.Config ARGB_8888**：由4个8位组成，即A=8，R=8，G=8，B=8，那么**一个像素点占8+8+8+8=32位**（4字节）
- **Bitmap.Config ARGB_4444**：由4个4位组成，即A=4，R=4，G=4，B=4，那么**一个像素点占4+4+4+4=16位**（2字节）
    - 不常用，颜色精度和透明度较低，可能导致图像质量较差
- **Bitmap.Config RGB_565**：只有颜色没有透明度，R=5，G=6，B=5，那么**一个像素点占5+6+5=16位**（2字节）
- **Bitmap.Config ALPHA_8**：只有透明度，没有颜色。每个像素占8位（1字节）

> ARGB_8888由4个8位组成，所以一个像素点占4个字节

一般情况下使用色彩模式为`ARGB_8888`
，该模式下一个像素所占的大小为4字节，一个像素的位数总和越高，图像也就越逼真。假设有一张`480x800`
的图片，在色彩模式为`ARGB_8888`的情况下，会占用`480*800*4/1024KB=1500KB`的内存；而在`RGB_565`
的情况下，占用的内存为：`480*800*2/1024KB=750KB`。--------------------

#### <span id = "pngandjpeg">3. PNG和JPEG</span>

- JPEG 适用于需要压缩文件大小并且可以接受细节损失（JPEG 和 JPG
  是同一种格式，只是一些操作系统或应用程序可能更倾向于使用 ".jpg" 作为文件扩展名）
- PNG 适用于需要透明度或者要求保持图像质量

---------------------------

#### <span id = "pic_memory">4. 内存大小的计算公式</span>

先将一张大小为 `1920 x 1080 px` 的图片保存到 `drawable-xxhdpi`
文件夹内，然后将其显示在一个宽高均为 `180dp` 的 `ImageView` 上，该 `Bitmap`
所占用的内存就通过`bitmap.byteCount`来获取

```
val options = BitmapFactory.Options()
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
log("imageView width: " + imageView.width)
log("imageView height: " + imageView.height)
log("bitmap width: " + bitmap.width)  //压缩后的图片尺寸大小
log("bitmap height: " + bitmap.height)
log("bitmap config: " + bitmap.config)
log("inDensity: " + options.inDensity)
log("outWidth: " + options.outWidth) //压缩前的图片尺寸大小
log("outHeight: " + options.outHeight)
log("inTargetDensity: " + options.inTargetDensity)
log("bitmap byteCount: " + bitmap.byteCount)
```

```
BitmapMainActivity: imageView width: 540
BitmapMainActivity: imageView height: 540
BitmapMainActivity: bitmap width: 1920 //压缩后的尺寸
BitmapMainActivity: bitmap height: 1080
BitmapMainActivity: bitmap config: ARGB_8888
BitmapMainActivity: inDensity: 480 // inDensity是当前图片实际放在哪个drawable文件夹下
BitmapMainActivity: inTargetDensity: 480 //inTargetDensity是当前图片最适合放在哪个drawable文件夹下
BitmapMainActivity: bitmap byteCount: 8294400
```

- 由于density 等于 3，所以 `ImageView` 的宽高都是 `540 px`
- Bitmap 的宽高还是保持其原有大小，即1920 x 1080 px
- ARGB_8888 代表的是该 Bitmap 的编码格式，该格式下一个像素点需要占用 4 byte
- inDensity 代表的是**系统最终选择的 drawable 文件夹类型**，等于 480 说明取的是
  drawable-xxhdpi文件夹下的图片
- inTargetDensity 代表的是当前设备的 dpi
- 8294400 就是 Bitmap 所占用的内存大小，单位是 byte

从最终结果可以很容易地就逆推出 Bitmap
所占内存大小的计算公式：`bitmapWidth * bitmapHeight * 单位像素点所占用的字节数`
，即 `1920 * 1080 * 4 = 8294400`

-----------------

#### <span id = "re_drawable">5. 和 `drawable` 文件夹的关系</span>

上面之所以很容易就逆推出了 Bitmap 所占内存大小的计算公式，是因为所有条件都被我故意设定为最优情况了，才使得计算过程这么简单。而实际上
Bitmap 所占内存大小和其所在的 drawable 文件夹是有很大关系的，虽然计算公式没变

现在的大部分应用为了达到最优的显示效果，会为应用准备多套切图放在不同的 drawable
文件夹下，而BitmapFactory.decodeResource 方法在解码 Bitmap 的时候，就会自动根据当前设备的 dpi 和
drawable 文件夹类型来判断是否需要对图片进行缩放显示

将图片从 `drawable-xxhdpi迁移到 drawable-xhdpi`文件夹，然后再打印日志信息

```
BitmapMainActivity: imageView width: 540
BitmapMainActivity: imageView height: 540
BitmapMainActivity: bitmap width: 2880
BitmapMainActivity: bitmap height: 1620
BitmapMainActivity: bitmap config: ARGB_8888
BitmapMainActivity: inDensity: 320 // inDensity是当前图片实际放在哪个drawable文件夹下
BitmapMainActivity: inTargetDensity: 480 //inTargetDensity是当前图片最适合放在哪个drawable文件夹下
BitmapMainActivity: bitmap byteCount: 18662400
```

可以看到，Bitmap 的宽高都发生了变化，inDensity 等于 320 也说明了选取的是drawable-xhdpi文件夹内的图片，Bitmap
所占内存居然增加了一倍多

模拟器的 dpi 是 480，拿到了 dpi 为 320
的drawable-xhdpi文件夹下的图片，在系统的理解中该文件夹存放的都是小图标，是为小屏幕手机准备的，现在要在大屏幕手机上展示的话就需要对其进行放大，放大的比例就是
480 / 320 = 1.5 倍，因此 Bitmap 的宽就会变为 1920 * 1.5 = 2880 px，高就会变为 1080 * 1.5 = 1620
px，最终占用的内存空间大小就是 2880 * 1620 * 4 = 18662400

所以说，对于同一台手机，Bitmap 在不同 drawable 文件夹下对其最终占用的内存大小是有很大关系的，虽然计算公式没变，但是由于系统会进行自动缩放，导致
Bitmap 的最终宽高都发生了变化，从而影响到了其占用的内存空间大小。同理，对于同个 drawable
文件夹下的同一张图片，在不同的手机屏幕上也可能会占用不同的内存空间，因为不同的手机的 dpi
大小可能是不一样的，BitmapFactory 进行缩放的比例也就不一样

---------------------------

#### <span id = "re_drawable">6. 和 `ImageView` 的宽高的关系</span>

在上一个例子里，Bitmap 的宽高是 2880 * 1620 px，ImageView 的宽高是 540 * 540 px，该 Bitmap
肯定是会显示不全的，读者可以试着自己改变 ImageView 的宽高大小来验证是否会对 Bitmap 的大小产生影响

这里就不贴代码了，直接来说结论，答案是没有关系。原因也很简单，毕竟上述例子是先将 Bitmap 加载到内存中后再设置给
ImageView 的，ImageView 自然不会影响到 Bitmap 的加载过程，该 Bitmap 的大小也只受其所在的 drawable
文件夹类型以及手机的 dpi 大小这两个因素的影响。但这个结论是需要考虑测试方式的，如果你是使用 Glide
来加载图片，Glide 内部实现了按需加载的机制，会根据 ImageView 的大小对 Bitmap 进行自动缩放，避免内存浪费的情况，这种情况下
ImageView 的宽高就会影响到 Bitmap 的内存大小了

-----------------------------

#### <span id = "BitmapFactory">7. BitmapFactory</span>

BitmapFactory 提供了很多个方法用于加载 Bitmap
对象：decodeFile、decodeResourceStream、decodeResource、decodeByteArray、decodeStream 等多个，但只有
decodeResourceStream 和 decodeResource 这两个方法才会根据 dpi 进行自动缩放。如果是从磁盘或者 assert
目录加载图片的话是不会进行自动缩放的，毕竟这些来源也不具备 dpi 信息，Bitmap 的分辨率也只能保持其原有大小

decodeResource 方法也会调用到decodeResourceStream方法，decodeResourceStream方法如果判断到inDensity 和
inTargetDensity 两个属性外部没有主动赋值的话，就会根据实际情况进行赋值

```
@Nullable
public static Bitmap decodeResourceStream(@Nullable Resources res, @Nullable TypedValue value,
        @Nullable InputStream is, @Nullable Rect pad, @Nullable Options opts) {
    validate(opts);
    if (opts == null) {
        opts = new Options();
    }
    if (opts.inDensity == 0 && value != null) {
        final int density = value.density;
        if (density == TypedValue.DENSITY_DEFAULT) {
            //如果 density 没有赋值的话（等于0），那么就使用基准值 160 dpi
            opts.inDensity = DisplayMetrics.DENSITY_DEFAULT;
        } else if (density != TypedValue.DENSITY_NONE) {
            //在这里进行赋值，density 就等于 drawable 对应的 dpi
            opts.inDensity = density;
        }
    }  
    if (opts.inTargetDensity == 0 && res != null) {
        //如果没有主动设置 inTargetDensity 的话，inTargetDensity 就等于设备的 dpi
        opts.inTargetDensity = res.getDisplayMetrics().densityDpi;
    }
    return decodeStream(is, pad, opts);
}
```

### <span id = "compress">二. 优化 Bitmap</span>

Bitmap 所占内存大小的计算公式：**bitmapWidth * bitmapHeight * 单位像素点所占用的字节数**，想要尽量减少
Bitmap 占用的内存大小的话就要从**降低图片分辨率**和**降低单位像素需要的字节数**这两方面来考虑了

在一开始的情况下加载到的 Bitmap 的宽高是 1920 * 1080，占用的内存空间是 1920 * 1080 * 4 = 8294400，约
7.9 MB，这是优化前的状态

```
val options = BitmapFactory.Options()
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
log("bitmap width: " + bitmap.width) //压缩后的图片尺寸大小
log("bitmap height: " + bitmap.height)
log("bitmap config: " + bitmap.config)
log("inDensity: " + options.inDensity)
log("outWidth: " + options.outWidth) //压缩前的图片尺寸大小
log("outHeight: " + options.outHeight)
log("inTargetDensity: " + options.inTargetDensity)
log("bitmap byteCount: " + bitmap.byteCount)

BitmapMainActivity: bitmap width: 1920
BitmapMainActivity: bitmap height: 1080
BitmapMainActivity: bitmap config: ARGB_8888
BitmapMainActivity: inDensity: 480
BitmapMainActivity: inTargetDensity: 480
BitmapMainActivity: bitmap byteCount: 8294400
```

--------------------------

#### 1、[inSampleSize](https://github.com/Curzibn/Luban/blob/master/DESCRIPTION.md)

由于 ImageView 的宽高只有 540 * 540 px，如果按照原图进行加载的话其实会造成很大的内存浪费，此时我们就可以通过
inSampleSize 属性来压缩图片尺寸

例如，将 inSampleSize 设置为 2 后，Bitmap 的宽高就都会缩减为原先的一半，占用的内存空间就变成了原先的四分之一，
960 * 540 * 4 = 2073600，约 1.9 MB

```
val options = BitmapFactory.Options()
options.inSampleSize = 2
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
log("bitmap width: " + bitmap.width)
log("bitmap height: " + bitmap.height)
log("bitmap config: " + bitmap.config)
log("inDensity: " + options.inDensity)
log("inTargetDensity: " + options.inTargetDensity)
log("bitmap byteCount: " + bitmap.byteCount)

BitmapMainActivity: bitmap width: 960
BitmapMainActivity: bitmap height: 540
BitmapMainActivity: bitmap config: ARGB_8888
BitmapMainActivity: inDensity: 480
BitmapMainActivity: inTargetDensity: 480
BitmapMainActivity: bitmap byteCount: 2073600
```

可以看出来，inSampleSize 属性应该设置多少是需要根据**Bitmap 的实际宽高**和**ImageView 的实际宽高**
这两个条件来一起决定的。我们在正式加载 Bitmap 前要先获取到 Bitmap 的实际宽高大小，这可以通过
inJustDecodeBounds 属性来实现。设置 inJustDecodeBounds 为 true 后`decodeResource`方法只会去读取
Bitmap 的宽高属性而不会去进行实际加载，这个操作是比较轻量级的。然后通过每次循环对半折减，计算出
inSampleSize 需要设置为多少才能尽量接近到 ImageView 的实际宽高，之后将 inJustDecodeBounds 设置为
false 去实际加载 Bitmap

需要注意的是，inSampleSize 使用的最终值将是向下舍入为最接近的 2 的幂，BitmapFactory 内部会自动会该值进行校验修正

```
val options = BitmapFactory.Options()
options.inJustDecodeBounds = true
BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
val inSampleSize = calculateInSampleSize(options, imageView.width, imageView.height)
options.inSampleSize = inSampleSize
options.inJustDecodeBounds = false
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
```

```
fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // Raw height and width of image
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var inSampleSize = 1
    if (height > reqHeight || width > reqWidth) {
        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2
        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}
```

---------------------------------

#### 2、inTargetDensity

如果我们不主动设置 inTargetDensity 的话，`decodeResource`方法会自动根据当前设备的 dpi 来对 Bitmap
进行缩放处理，我们可以通过主动设置 inTargetDensity 来控制缩放比例，从而控制 Bitmap 的最终宽高。最终宽高的生成规则：
180 / 480 * 1920 = 720，180 / 480 * 1080 = 405，占用的内存空间是 720 * 405 * 4 = 1166400，约 1.1 MB

```source-kotlin
val options = BitmapFactory.Options()
options.inTargetDensity = 180
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
log("bitmap width: " + bitmap.width)
log("bitmap height: " + bitmap.height)
log("bitmap config: " + bitmap.config)
log("inDensity: " + options.inDensity)
log("inTargetDensity: " + options.inTargetDensity)
log("bitmap byteCount: " + bitmap.byteCount)

BitmapMainActivity: bitmap width: 720
BitmapMainActivity: bitmap height: 405
BitmapMainActivity: bitmap config: ARGB_8888
BitmapMainActivity: inDensity: 480
BitmapMainActivity: inTargetDensity: 180
BitmapMainActivity: bitmap byteCount: 1166400
```

-------------------

#### 3、Bitmap.Config

BitmapFactory 默认使用的编码图片格式是 ARGB_8888，每个像素点占用四个字节，我们可以按需改变要采用的图片格式。例如，如果要加载的
Bitmap 不包含透明通道的，我们可以使用 RGB_565，该格式每个像素点占用两个字节，占用的内存空间是 1920 *
1080 * 2 = 4147200，约 3.9 MB

```source-kotlin
val options = BitmapFactory.Options()
options.inPreferredConfig = Bitmap.Config.RGB_565
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_awe, options)
imageView.setImageBitmap(bitmap)
log("bitmap width: " + bitmap.width)
log("bitmap height: " + bitmap.height)
log("bitmap config: " + bitmap.config)
log("inDensity: " + options.inDensity)
log("inTargetDensity: " + options.inTargetDensity)
log("bitmap byteCount: " + bitmap.byteCount)

BitmapMainActivity: bitmap width: 1920
BitmapMainActivity: bitmap height: 1080
BitmapMainActivity: bitmap config: RGB_565
BitmapMainActivity: inDensity: 480
BitmapMainActivity: inTargetDensity: 480
BitmapMainActivity: bitmap byteCount: 4147200
```

-----------------

[参考文档(里面有很多比较好的文档总结)](https://github.com/leavesCZY/AndroidGuide/blob/master/%E6%8E%A2%E7%A9%B6%20Bitmap%20%E7%9A%84%E4%BC%98%E5%8C%96%E6%89%8B%E6%AE%B5.md)
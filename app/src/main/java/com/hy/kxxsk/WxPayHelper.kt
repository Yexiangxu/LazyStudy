package com.hy.kxxsk

import com.lazyxu.base.base.BaseApplication
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import org.json.JSONObject
import java.net.URLEncoder


/**
 * Created by zhang on 2018/12/11.
 */

class WxPayHelper {

    companion object {
        const val WXAPP_ID = "wx90885908f8109a00"
        val wxapi: IWXAPI by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val iwxapi: IWXAPI = WXAPIFactory.createWXAPI(BaseApplication.INSTANCE, WXAPP_ID, true)
            iwxapi.registerApp(WXAPP_ID)
//            //建议动态监听微信启动广播进行注册到微信
//            registerReceiver(object : BroadcastReceiver() {
//                override fun onReceive(context: Context, intent: Intent) {
//                    // 将该app注册到微信
//                    iwxapi.registerApp(WXAPP_ID)
//                }
//            }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
            iwxapi
        }
    }

    fun payWX(data: String) {
        var jsonObject = JSONObject(data)
        val payReq = PayReq()
        payReq.appId = "wx90885908f8109a00"
        payReq.partnerId = "1621850582"
        payReq.prepayId = jsonObject.getString("prepay_id")
        payReq.nonceStr = jsonObject.getString("nonce_str")
        payReq.timeStamp = jsonObject.getString("create_time")
        payReq.sign = jsonObject.getString("sign")
        payReq.packageValue = jsonObject.getString("package")
        payReq.extData = "qiha"
        wxapi.sendReq(payReq)
    }
// checkoutCounter data={"appId":"wx90885908f8109a00","mchId":"","packageInfo":""}
    fun pay() {
        val req = WXOpenBusinessView.Req()
        req.businessType = "requestMerchantTransfer"
//    req.query = "mchId=${URLEncoder.encode("1621850582", "UTF-8")}&appId=${URLEncoder.encode("wx90885908f8109a00", "UTF-8")}=${URLEncoder.encode("ABBQO+oYAAABAAAAAACxSIeTe1k5HjIWVai+ZxAAAADnGpepZahT9IkJjn90+1qgLoA97rxFmJiORqAlpRQ+v3wTKkj02zJNbJR/Y8nMeVEGvOKLso4+Jr9OzN1zfa8wXFiQoNxd5TnhGUZU8TeTsSUl17s=", "UTF-8")}"

        req.query = "mchId=1621850582&appId=wx90885908f8109a00&package=${URLEncoder.encode("ABBQO+oYAAABAAAAAACxSIeTe1k5HjIWVai+ZxAAAADnGpepZahT9IkJjn90+1qgLoA97rxFmJiORqAlpRQ+v3wTKkj02zJNbJR/Y8nMeVEGvOKLso4+Jr9OzN1zfa8wXFiQoNxd5TnhGUZU8TeTsSUl17s=", "UTF-8")}"
        val ret: Boolean = wxapi.sendReq(req)
    }

}

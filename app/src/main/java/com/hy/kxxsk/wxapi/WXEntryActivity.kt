package com.hy.kxxsk.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.hy.kxxsk.WxPayHelper
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler


/**
 * Created by zhang on 2018/12/11.
 * 微信分享
 */
class WXEntryActivity : Activity(), IWXAPIEventHandler {
    lateinit var iwxapi: IWXAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iwxapi = WxPayHelper.wxapi
        iwxapi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        iwxapi.handleIntent(intent, this)
    }

    override fun onResp(p0: BaseResp?) {
        Log.d("WxShareTag", "onResp type=${p0?.type},resp p0=${p0.toString()}")
        when (p0?.errCode) {
            BaseResp.ErrCode.ERR_OK -> {

            }


        }
        finish()

    }

    override fun onReq(p0: BaseReq?) {
        Log.d("WxShareTag", "onReq onReq=${p0.toString()}")
    }
}
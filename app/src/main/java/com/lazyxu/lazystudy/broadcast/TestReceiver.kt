package com.lazyxu.lazystudy.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * User:Lazy_xu
 * Date:2023/10/18
 * Description:
 * FIXME
 */
class TestReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "收到广播了~", Toast.LENGTH_LONG).show()
    }
}
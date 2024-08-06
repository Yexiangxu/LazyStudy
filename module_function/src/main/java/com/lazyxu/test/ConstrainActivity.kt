package com.lazyxu.test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import com.lazyxu.function.R

class ConstrainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testconst)
        findViewById<Button>(R.id.btnLayer).setOnClickListener {
            findViewById<Layer>(R.id.layer).rotation = 45f
            findViewById<Layer>(R.id.layer).translationX = 100f
            findViewById<Layer>(R.id.layer).translationY = 100f
            //无效
//            findViewById<Barrier>(R.id.barrier).rotation = 45f
//            findViewById<Barrier>(R.id.barrier).translationX = 100f
//            findViewById<Barrier>(R.id.barrier).translationY = 100f

//            findViewById<Placeholder>(R.id.placeHolder).setContentId(R.id.iv1)
        }
    }
}
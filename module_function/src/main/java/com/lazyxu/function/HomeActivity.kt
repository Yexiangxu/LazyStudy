package com.lazyxu.function

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lib_common.CustomPopWindow


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testhome)
        val ivMusic = findViewById<AppCompatImageView>(R.id.ivMusic)
        ivMusic.setOnClickListener {
            showPopBottom(ivMusic)
        }
        val clOne = findViewById<ConstraintLayout>(R.id.clOne)
        val clAnswerQuestions = findViewById<ConstraintLayout>(R.id.clAnswerQuestions)
        translationYAnim(clOne)
        scaleAnim(clAnswerQuestions)

        var apkqid = "123";
    }

    private fun showPopBottom(view: View) {
        val popWindow: CustomPopWindow = CustomPopWindow.PopupWindowBuilder(this)
            .setView(R.layout.pop_home)
            .setFocusable(true)
            .setOutsideTouchable(true)
            .create()
        popWindow.showAsDropDown(view, 0, 10)
    }

    private fun translationYAnim(view: View) {
        val translationYAnimator = ObjectAnimator.ofFloat(view, "translationY", -60f, 60f)
        translationYAnimator.duration = 1000
        translationYAnimator.repeatCount = ValueAnimator.INFINITE
        translationYAnimator.repeatMode = ValueAnimator.REVERSE
        translationYAnimator.interpolator = LinearInterpolator()
        translationYAnimator.start()
    }

    private fun scaleAnim(view: View) {
        val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.25f, 0.75f, 1.15f, 1f)
        scaleXAnimator.repeatMode = ValueAnimator.REVERSE
        scaleXAnimator.repeatCount = ValueAnimator.INFINITE
        scaleXAnimator.duration = 1000

        val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.75f, 1.25f, 0.85f, 1f)
        scaleYAnimator.repeatMode = ValueAnimator.REVERSE
        scaleYAnimator.repeatCount = ValueAnimator.INFINITE
        scaleYAnimator.duration = 1000
        val set = AnimatorSet()
        set.playTogether(scaleXAnimator, scaleYAnimator)
        set.start()
    }
}
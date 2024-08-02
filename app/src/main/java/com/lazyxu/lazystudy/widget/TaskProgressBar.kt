package com.lazyxu.lazystudy.widget

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.lazyxu.lazystudy.R


class TaskProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var progressBar: ProgressBar
    private var progressTextContainer: LinearLayoutCompat
    private var progressText: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.task_progress_bar, this, true)
        progressBar = view.findViewById(R.id.progress_bar)
        progressTextContainer = view.findViewById(R.id.progress_text_container)
        progressText = view.findViewById(R.id.progress_text)
    }

    fun setProgress(progress: Int, max: Int) {
        progressBar.max = max
        animateProgress(progress)
        progressText.text = "$progress/$max"
    }

    private fun animateProgress(targetProgress: Int) {
        val startProgress = progressBar.progress
        val animator = ValueAnimator.ofInt(startProgress, targetProgress)
        animator.duration = 500 // 动画持续时间，500毫秒

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            progressBar.progress = animatedValue
            updateProgressTextPosition(animatedValue, progressBar.max)
        }

        animator.start()
    }

    private fun updateProgressTextPosition(progress: Int, max: Int) {
        val progressRatio = progress.toFloat() / max
        val progressBarWidth = progressBar.width
        val textWidth = progressTextContainer.width
        val newTextX = (progressRatio * (progressBarWidth - textWidth)).toInt()
        progressTextContainer.translationX = newTextX.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateProgressTextPosition(progressBar.progress, progressBar.max)
    }
}

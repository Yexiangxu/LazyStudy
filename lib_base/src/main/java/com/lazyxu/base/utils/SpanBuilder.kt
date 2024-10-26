package com.lazyxu.base.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.text.inSpans

class SpanBuilder private constructor() {

    companion object {
        fun create() = SpanBuilder()
    }

    val builder = SpannableStringBuilder()
    var tv: TextView? = null

    fun append(text: CharSequence) {
        builder.append(text)
    }

    fun append(@StringRes id: Int) {
        val context = tv?.context ?: return
        builder.append(context.getString(id))
    }

    fun bold(text: CharSequence) {
        builder.inSpans(StyleSpan(Typeface.BOLD)) {
            builder.append(text)
        }
    }

    fun italic(text: CharSequence) {
        builder.inSpans(StyleSpan(Typeface.ITALIC)) {
            builder.append(text)
        }
    }

    fun boldItalic(text: CharSequence) {
        builder.inSpans(StyleSpan(Typeface.BOLD_ITALIC)) {
            builder.append(text)
        }
    }

    fun underline(text: CharSequence) {
        builder.inSpans(UnderlineSpan()) {
            builder.append(text)
        }
    }

    fun strikeThrough(text: CharSequence) {
        builder.inSpans(StrikethroughSpan()) {
            builder.append(text)
        }
    }

    fun relativeSize(text: CharSequence, proportion: Float) {
        builder.inSpans(RelativeSizeSpan(proportion)) {
            builder.append(text)
        }
    }

    fun absoluteSize(text: CharSequence, size: Int) {
        builder.inSpans(AbsoluteSizeSpan(size)) {
            builder.append(text)
        }
    }

    fun clickSpan(action: () -> Unit): ClickableSpan {
        val span = object : ClickableSpan() {
            override fun onClick(widget: View) {
                action.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }
        tv?.highlightColor = Color.TRANSPARENT
        tv?.isLongClickable = false
        tv?.movementMethod = LinkMovementMethod.getInstance()
        return span
    }

    fun clickSpan(
        text: CharSequence,
        textColor: Int? = null,
        isBold: Boolean = false,
        action: () -> Unit
    ) {
        builder.inSpans(object : ClickableSpan() {
            override fun onClick(widget: View) {
                action.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                textColor?.let {
                    ds.color = textColor
                }
                if (isBold) {
                    ds.typeface = Typeface.DEFAULT_BOLD
                }
                ds.isUnderlineText = false
            }
        }) {
            builder.append(text)
        }
        tv?.highlightColor = Color.TRANSPARENT
        tv?.isLongClickable = false
        tv?.movementMethod = LinkMovementMethod.getInstance()
    }

    fun fontFamily(text: CharSequence, fontFamily: String) {
        builder.inSpans(TypefaceSpan(fontFamily)) {
            builder.append(text)
        }
    }

    fun color(text: CharSequence, color: Int) {
        builder.inSpans(ForegroundColorSpan(color)) {
            builder.append(text)
        }
    }

    fun color(@StringRes strRes: Int, @ColorRes colorRes: Int) {
        val context = tv?.context ?: return
        val color = ContextCompat.getColor(context, colorRes)
        builder.inSpans(ForegroundColorSpan(color)) {
            builder.append(context.getString(strRes))
        }
    }

    fun spans(text: CharSequence, vararg spans: Any) {
        val textLength = text.length
        builder.append(text)
        spans.forEach { span ->
            builder.setSpan(
                span,
                builder.length - textLength,
                builder.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
    }
}


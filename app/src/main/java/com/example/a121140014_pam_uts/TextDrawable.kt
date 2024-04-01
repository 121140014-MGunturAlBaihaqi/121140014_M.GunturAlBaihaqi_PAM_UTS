package com.example.a121140014_pam_uts.utils

import android.graphics.*
import android.graphics.drawable.Drawable

class TextDrawable private constructor(
    private val text: String,
    private val textSize: Float,
    private val textColor: Int,
    private val bgColor: Int,
    private val outerRadius: Float,
    private val innerRadius: Float
) : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = Rect()

    init {
        paint.color = textColor
        paint.textSize = textSize
        paint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        paint.getTextBounds(text, 0, text.length, bounds)
        val width = bounds.width() + outerRadius * 2
        val height = bounds.height() + outerRadius * 2

        val x = outerRadius
        val y = outerRadius + bounds.height()

        val path = Path()
        path.addCircle(
            width / 2f,
            height / 2f,
            (Math.min(width, height) / 2f) - innerRadius,
            Path.Direction.CCW
        )
        canvas.clipPath(path)

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            (Math.min(width, height) / 2f),
            Paint().apply { color = bgColor }
        )

        canvas.drawText(text, x, y, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var text: String = ""
        private var textSize: Float = 50f
        private var textColor: Int = Color.WHITE
        private var bgColor: Int = Color.GRAY
        private var outerRadius: Float = 50f
        private var innerRadius: Float = 25f

        fun setText(text: String): Builder {
            this.text = text
            return this
        }

        fun setTextSize(textSize: Float): Builder {
            this.textSize = textSize
            return this
        }

        fun setTextColor(textColor: Int): Builder {
            this.textColor = textColor
            return this
        }

        fun setBgColor(bgColor: Int): Builder {
            this.bgColor = bgColor
            return this
        }

        fun setOuterRadius(outerRadius: Float): Builder {
            this.outerRadius = outerRadius
            return this
        }

        fun setInnerRadius(innerRadius: Float): Builder {
            this.innerRadius = innerRadius
            return this
        }

        fun build(): TextDrawable {
            return TextDrawable(text, textSize, textColor, bgColor, outerRadius, innerRadius)
        }
    }
}
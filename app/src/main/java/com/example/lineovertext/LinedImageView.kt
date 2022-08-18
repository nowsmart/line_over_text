package com.example.lineovertext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView

class LinedImageView(context: Context?, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {
    private val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        val lineHeight = 30
        var count = height / lineHeight
        if (count<1) count =1
        val r = Rect()

        var baseline = 100
        for (i in 0 until count) {
            canvas.drawLine(
                r.left.toFloat(),
                (baseline + 1).toFloat(),
                r.right.toFloat(),
                (baseline + 1).toFloat(),
                paint
            )
            baseline += lineHeight //next line
        }
        super.onDraw(canvas)
    }

    // we need this constructor for LayoutInflater
    init {
        paint.strokeWidth= 10.0F
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }
}
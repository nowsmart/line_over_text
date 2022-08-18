package com.example.lineovertext

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet


@SuppressLint("ResourceAsColor")
class LinedEditText(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatEditText(context!!, attrs) {
    private var r: Rect = Rect()
    private var mPaint: Paint = Paint()
    override fun onDraw(canvas: Canvas) {
        var count = height / lineHeight
        if (lineCount > count) count = lineCount //for long text with scrolling

        var baseline = getLineBounds(0, r) //first line

        for (i in 0 until count) {
            canvas.drawLine(
                r.left.toFloat(),
                (baseline + 1).toFloat(),
                r.right.toFloat(),
                (baseline + 1).toFloat(),
                mPaint
            )
            baseline += lineHeight //next line
        }
        super.onDraw(canvas)
    }

    // we need this constructor for LayoutInflater
    init {
        mPaint.strokeWidth = 2.0F
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
    }
}
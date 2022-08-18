package com.example.lineovertext

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.TypefaceSpan
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtHello = findViewById<TextView>(R.id.txtHello)
        try {
            val elev10 = Typeface.createFromAsset( assets,"elev10.ttf")
            val elev100 = Typeface.createFromAsset(assets, "elev100.ttf")
            val elev1000 = Typeface.createFromAsset(assets, "elev1000.ttf")
            val ss = SpannableStringBuilder("۱111111111")
            ss.setSpan(CustomTypefaceSpan("", elev10), 1, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            ss.setSpan(CustomTypefaceSpan("", elev100), 2, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            ss.setSpan(CustomTypefaceSpan("", elev1000), 3, 40 , Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            txtHello.text = ss

        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
        txtHello.text = "۱۲۳۴۵۶۷۸۹\nABCDEFGHI\nJKLMNOPQR\nS"
    }
}

class CustomTypefaceSpan(family: String?, private val newType: Typeface) :
    TypefaceSpan(family) {
    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    companion object {
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old: Typeface = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = tf
        }
    }
}



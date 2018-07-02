package com.kevalpatel2106.sample.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView

/**
 * Created by Kevalpatel2106 on 21-Jun-18.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
class DividerItemDecoration(context: Context, private val horizontalPadding: Int) : RecyclerView.ItemDecoration() {

    private val listDividerAttrs = intArrayOf(android.R.attr.listDivider)
    private val divider: Drawable

    init {
        val styledAttributes = context.obtainStyledAttributes(listDividerAttrs)
        divider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = horizontalPadding
        val right = parent.width - horizontalPadding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}

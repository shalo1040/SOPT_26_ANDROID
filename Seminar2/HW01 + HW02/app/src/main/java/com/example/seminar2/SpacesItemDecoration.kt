package com.example.seminar2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(space, space, space, space);
        //outRect.bottom = space;
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state);
        val paint = Paint();
        //val colorList = intArrayOf(Color.RED, Color.LTGRAY);
        val dividerLeft = parent.paddingLeft.toFloat();
        val dividerRight = parent.width - parent.paddingRight.toFloat();
        val childCount = parent.childCount;
        for(i in 0 until childCount) {
            //if(i==0) paint.color = colorList[i];
            //else paint.color = colorList[1];
            paint.color = Color.LTGRAY;
            val child = parent.getChildAt(i);
            val top = child.top.toFloat() - space;
            val bottom = child.bottom.toFloat() + space;
            c.drawRect(dividerLeft, top, dividerRight, bottom, paint);
        }
    }
}
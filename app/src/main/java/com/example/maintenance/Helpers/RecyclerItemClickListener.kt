package com.example.maintenance.Helpers

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener):
    RecyclerView.SimpleOnItemTouchListener(), RecyclerView.OnItemTouchListener {

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
    }

    // add the gestureDetector
    private val gestureDetector = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val v = recyclerView.findChildViewUnder(e.x, e.y)
            if (v != null) {
                listener.onItemClick(v, recyclerView.getChildAdapterPosition(v))
            }
            return true
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val result = gestureDetector.onTouchEvent(e)
        return  result
    }
}
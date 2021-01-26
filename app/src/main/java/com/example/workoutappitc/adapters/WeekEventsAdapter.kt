package com.example.workoutappitc.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutappitc.R
import com.example.workoutappitc.api.models.Events
import com.example.workoutappitc.interfaces.OnItemClickListener

class WeekEventsHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.header_recycler_tv)
    val constraintLayout: ConstraintLayout = view.findViewById(R.id.header_recycler_constraint)
}
class WeekEventsAdapter(private val weekList: List<Events>, private val context: Context, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<WeekEventsHolder>() {
    var row_index = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekEventsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.header_recycler_item, parent, false)
        return WeekEventsHolder(view)
    }

    override fun getItemCount(): Int {
        return weekList.size
    }

    override fun onBindViewHolder(holder: WeekEventsHolder, position: Int) {
        val event = weekList[position]
        with(holder) {
            textView.text = event.day.take(1)
            constraintLayout.setOnClickListener {
                row_index = position
                notifyDataSetChanged()
                onItemClickListener.onItemClickListener(event)
            }
            when {
                row_index == position -> {
                    textView.setBackgroundResource(R.drawable.selected_item)
                    textView.setTextColor(ContextCompat.getColor(context, R.color.green))
                }
                event.isCompleted -> {
                    textView.setBackgroundResource(R.drawable.completed_item)
                    textView.setTextColor(ContextCompat.getColor(context, R.color.white))
                }
                event.eventId != null -> {
                    textView.setBackgroundResource(R.drawable.regular_item)
                    textView.setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                else -> {
                    textView.setBackgroundResource(R.drawable.regular_item)
                    textView.setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            }
        }
    }
}
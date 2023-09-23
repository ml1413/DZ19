package com.example.dz19.recyclerAdapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.dz19.R
import com.example.dz19.databinding.ItemBinding
import com.example.dz19.db.TaskEntity

sealed class Holders(view: View) : RecyclerView.ViewHolder(view) {
    class TaskViewHolder(private val view: View) : Holders(view) {
        private val binding = ItemBinding.bind(view)
        fun initView(item: Any, clickOnItem: ClickOnItem) {
            val taskEntity = item as TaskEntity
            binding.label.text = taskEntity.label
            binding.message.text = taskEntity.message
            itemView.setOnClickListener {
                clickOnItem.click(taskEntity = taskEntity)
            }
            itemView.startAnimation(
                AnimationUtils.loadAnimation(
                    view.context,
                    R.anim.item_animation
                )
            )
        }
    }
}
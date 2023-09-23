package com.example.dz19.recyclerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dz19.R

class RecyclerAdapter(
    val clickOnItem: ClickOnItem,
    private val idLayout: Int = 0
) : RecyclerView.Adapter<Holders>() {
    private var listItem: MutableList<Any> = mutableListOf()
    fun setIsl(list: List<Any>) {
        listItem = list.toMutableList()
        notifyItemChanged(listItem.size)
    }

    fun getListItem() = listItem

    override fun getItemCount() = listItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holders {
        val view = LayoutInflater.from(parent.context).inflate(idLayout, parent, false)
        return when (idLayout) {
            R.layout.item -> Holders.TaskViewHolder(view = view)
            else -> {
                throw ClassNotFoundException("Class for recyclerVive not found ")
            }
        }
    }

    override fun onBindViewHolder(holder: Holders, position: Int) {
        when (holder) {
            is Holders.TaskViewHolder -> holder.initView(
                item = listItem[position],
                clickOnItem = clickOnItem
            )
        }
    }
}
package com.example.dz19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dz19.R
import com.example.dz19.databinding.FragmentListBinding
import com.example.dz19.db.TaskEntity
import com.example.dz19.recyclerAdapter.ClickOnItem
import com.example.dz19.recyclerAdapter.RecyclerAdapter
import com.example.dz19.recyclerAdapter.SwipeToDeleteCollBack
import com.example.dz19.viewmodel.TaskViewModel
import kotlin.concurrent.thread


class ListFragment : Fragment(), ClickOnItem {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: RecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        initFields()
        viewModelObserver()
        swipeDelete()
        popOnAddButton()

        return binding.root
    }

    private fun viewModelObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setIsl(list = it)
                thread {
                    Thread.sleep(1000)
                    requireActivity().runOnUiThread {
                        binding.recyclerView.smoothScrollToPosition(adapter.getListItem().size)
                    }
                }

            }
        }
    }

    private fun initFields() {
        adapter = RecyclerAdapter(idLayout = R.layout.item, clickOnItem = this)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
    }

    private fun swipeDelete() {
        val swipeToDeleteCollBack = object : SwipeToDeleteCollBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition

                adapter.getListItem().apply {
                    viewModel.delete(this[position] as TaskEntity)
                    removeAt(position)
                }
                adapter.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "Удалено", Toast.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(swipeToDeleteCollBack).attachToRecyclerView(binding.recyclerView)
    }


    private fun popOnAddButton() {
        binding.button.setOnClickListener {
            val addFragment = AddFragment()
            parentFragmentManager.beginTransaction()
                .addToBackStack("")
                .setCustomAnimations(
                    R.anim.in_fragment,
                    R.anim.out_fragment,
                    R.anim.in_fragment,
                    R.anim.out_fragment,
                )
                .add(R.id.container, addFragment)
                .commit()
        }
    }

    override fun click(taskEntity: TaskEntity) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(taskEntity.label)
            .setMessage(taskEntity.message)
            .setNegativeButton("Close") { dialog, id ->
                dialog.cancel()
            }
            .show()
            .getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }


}
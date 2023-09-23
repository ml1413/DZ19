package com.example.dz19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dz19.R
import com.example.dz19.databinding.FragmentAddBinding
import com.example.dz19.db.TaskEntity
import com.example.dz19.viewmodel.TaskViewModel
import kotlin.concurrent.thread

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        binding.btAdd.setOnClickListener {
            if (binding.label.text.isNotBlank() &&
                binding.message.text.isNotBlank()
            ) {
                addDataViewModel()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Поля ввода должны быть заполненны",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return binding.root
    }

    private fun addDataViewModel() {
        //спрятать клавиатуру
        binding.root.clearFocus()
        //сохраняем данные в базу данных
        val taskEntity = TaskEntity(
            label = binding.label.text.toString(),
            message = binding.message.text.toString()
        )
        viewModel.add(taskEntity = taskEntity)
        //анимация cardView add
        binding.cardView.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.button_animatiom
            )
        )
        thread {//время для проигрывания анимации и возврат на пред экран
            Thread.sleep(200)
            parentFragmentManager.popBackStack()
        }
    }


}
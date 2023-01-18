package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.databinding.FragmentStarBinding

class StarFragment : Fragment() {
    private lateinit var viewModel: BookStarViewModel
    private lateinit var viewModelFactory: BookStarViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val binding = FragmentStarBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModelFactory = BookStarViewModelFactory(activity.application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BookStarViewModel::class.java)
        binding.viewModel = viewModel
        binding.recyclerView.adapter = BookStarAdapter(activity.application)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_star, container, false)
    }
}
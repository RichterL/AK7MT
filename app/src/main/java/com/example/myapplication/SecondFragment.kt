package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    private lateinit var viewModelFactory: BookViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val binding = FragmentSecondBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModelFactory = BookViewModelFactory(
            SecondFragmentArgs.fromBundle(requireArguments()).isbn
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BookViewModel::class.java)

        binding.viewModel = viewModel
        binding.bookListView.adapter = BookAdapter(activity.application)

        return binding.root
    }
}
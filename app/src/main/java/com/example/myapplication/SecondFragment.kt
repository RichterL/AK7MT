package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.network.Book
import com.example.myapplication.network.BookApi
import com.example.myapplication.network.BookList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    val args: SecondFragmentArgs by navArgs()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val isbnValue = args.isbn

        val books = BookApi.retrofitService.getBooks(isbnValue);
        books.enqueue(
            object: Callback<BookList> {
                override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                    val book = response.body()?.items?.get(0)
                    val data = response.body()?.items
                    val adapter = BookAdapter(data)
                    binding.bookListView.adapter = adapter
                    binding.textviewSecond.text = book?.bookInfo?.title
//                    binding.textviewSecond.text = "${response.body()?.items?.size} books found"
                }

                override fun onFailure(call: Call<BookList>, t: Throwable) {
                    binding.textviewSecond.text = "Failure: " + t.message
                }

            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
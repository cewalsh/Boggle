package com.example.boggle

import LetterGridAdapter
import android.graphics.LinearGradient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boggle.databinding.FragmentLettersBinding
import java.io.BufferedReader

import java.io.File
import android.content.Context
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet


private const val TAG = "LettersFragment"


class LettersFragment : Fragment() {


    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding : FragmentLettersBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visibile?"
        }
    private lateinit var word : String

//    private lateinit var char
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLettersBinding.inflate(layoutInflater, container, false);
        binding.lettersRecyclerView.layoutManager = GridLayoutManager(context,3)

        val letters = viewModel.letters
        val adapter = LetterGridAdapter(letters, viewModel)
        binding.lettersRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = MainActivity()
        binding.apply {
            submit.setOnClickListener {_ ->
                Log.d(TAG, "Clicked the submit button and updated word")
                if (mainActivity.checkWord((binding.word.text as String).lowercase())) {
                    println(((binding.word.text as String).lowercase())+" is a Real word!")
                    //You can add score here

                }else {
                    println(((binding.word.text as String).lowercase())+" is NOT a Real word!")
                    //You can minus score here

                }
                viewModel.submitWord()
            }

            viewModel.word.observe(viewLifecycleOwner, Observer { new_word ->
                Log.d(TAG,"updating word to: " + new_word)
                binding.word.text = new_word
            })

            clear.setOnClickListener { _ ->
                Log.d(TAG, "Clicked the clear button")
                viewModel.clearWord()
            }

            binding.word.text = ""

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
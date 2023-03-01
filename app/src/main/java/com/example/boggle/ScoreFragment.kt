package com.example.boggle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.boggle.databinding.FragmentScoreBinding

private const val TAG = "ScoreFragment"


class ScoreFragment : Fragment() {


    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding : FragmentScoreBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visibile?"
        }
    private var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(layoutInflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.finalWord.observe( viewLifecycleOwner, Observer { word ->
                Log.d(TAG,"scoring this word: " + word)

            })
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.boggle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
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
                if(viewModel.first.value == true){
                    viewModel.mutableFirst.value = false
                }else{
                    Log.d(TAG,"scoring this word: " + word)
                    if(viewModel.checkWord((word))){
                        val wordScore: Int = scoreWord(word)
                        Toast.makeText(
                            binding.root.context,
                            ("That's correct, +" + wordScore),
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.mutableScore.value = viewModel.score.value?.plus(wordScore)
                        viewModel.usedWords.add(word)
                    }else{
                        Toast.makeText(
                            binding.root.context,
                            ("That's incorrect, -10"),
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.mutableScore.value = viewModel.score.value?.plus(-10)
                    }
                    binding.scoreVal.text = viewModel.score.value.toString()
                }

            })

            binding.newGame.setOnClickListener { _ ->
                Log.d(TAG, "creating new game")
                viewModel.newGame()
                binding.scoreVal.text = viewModel.score.value.toString()
            }
        }



    }

    private fun scoreWord(str: String): Int {
        var score = 0
        val vowels = listOf<Char>('A', 'E', 'I', 'O', 'U')
        var double = false

        val doubleChars = listOf<Char>('S', 'Z', 'P', 'X', 'Q')

        for (c in str){
            if(c in vowels){
                score += 5
            }else{
                if(c in doubleChars){
                    double = true
                }
                score += 1
            }
        }

        if(double){
            score *= 2
        }

        return score
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
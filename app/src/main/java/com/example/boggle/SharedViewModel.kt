package com.example.boggle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {

    val mutableWord = MutableLiveData<String>()
    val word: LiveData<String> get() = mutableWord

    val letters = mutableListOf<Char>();

    init {
        for (i in 0 until 9){
            val c : Char = randomChar()
            letters += c
        }
    }

    private fun randomChar(): Char {
        return ((0..25).random() + 65).toChar()
    }

    fun updateWord(item: String) {
        mutableWord.value = item
    }

}

package com.example.boggle

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

private val TAG = "SharedViewModel"
class SharedViewModel : ViewModel() {

    val mutableWord = MutableLiveData<String>("")
    val word: LiveData<String> get() = mutableWord
    val mutableFinalWord = MutableLiveData<String>("")
    val finalWord: LiveData<String> get() = mutableFinalWord
    val mutableLastLetter: MutableLiveData<Int> = MutableLiveData<Int>(-1)
    val lastLetter: LiveData<Int> get() = mutableLastLetter
    val letters = mutableListOf<Char>();

    val mutableUsedLetterPositions = MutableLiveData<MutableList<Int>>()
    val usedLetterPositions: LiveData<MutableList<Int>> get() = mutableUsedLetterPositions

    init {
        for (i in 0 until 12){
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

    fun updateLetter(letter: Char, position: Int): Int {
        val lastPosition = lastLetter.value
        if(lastPosition == null || lastPosition == -1 || position == lastPosition + 3 || position == lastPosition - 3
            || (position == lastPosition + 1 && lastPosition % 3 != 2) || (position == lastPosition - 1 && lastPosition % 3 != 0)){
            mutableLastLetter.value = position
            mutableWord.value = word.value + letter
            usedLetterPositions.value?.add(position)
            return 0
        }else{
            return -1
        }
    }

    fun clearWord() {
        mutableWord.value = ""
        mutableLastLetter.value = -1
        mutableUsedLetterPositions.value = arrayListOf()
    }

    fun submitWord(){
        Log.d(TAG, "submitting word")
        mutableFinalWord.value = word.value
        Log.d(TAG, "should have updated finalWord : " + finalWord.value)
        clearWord()
    }

}

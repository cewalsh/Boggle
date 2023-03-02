package com.example.boggle

import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

internal class SharedViewModelTest {


    @Test
    fun testCheckWordFAIL() {
        val word = "CAR"
        val model = SharedViewModel()
        assertFalse(model.checkWord(word))
    }

    @Test
    fun testCheckWordGood() {
        val word = "CARE"
        val model = SharedViewModel()
        assertFalse(model.checkWord(word))
    }


}
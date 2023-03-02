package com.example.boggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import java.io.File
import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet


val dictionary = HashSet<String>()
class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()

    public fun checkWord(str: String): Boolean {
        return dictionary.contains(str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Read in the dictionary file from assets folder
        val inputStream = assets.open("words.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        // Add each word to the dictionary HashSet
        var line: String? = reader.readLine()
        while (line != null) {
            dictionary.add(line.trim().uppercase(Locale.ROOT))
            line = reader.readLine()
        }
        // Close the file reader
        reader.close()

        viewModel.dictionary = dictionary
    }
}
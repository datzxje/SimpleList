package com.example.simplelist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var textViewError: TextView
    private lateinit var listViewNumbers: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        textViewError = findViewById(R.id.textViewError)
        listViewNumbers = findViewById(R.id.listViewNumbers)

        buttonShow.setOnClickListener {
            hideKeyboard()
            displayNumbers()
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextNumber.windowToken, 0)
    }

    private fun displayNumbers() {
        val input = editTextNumber.text.toString()
        if (input.isEmpty() || input.toInt() <= 0) {
            textViewError.text = "Vui lòng nhập số nguyên dương n."
            return
        }

        val n = input.toInt()
        val selectedId = radioGroup.checkedRadioButtonId
        val numbersList = mutableListOf<Int>()
        textViewError.text = ""

        when (selectedId) {
            R.id.radioEven -> {
                for (i in 0..n step 2) {
                    numbersList.add(i)
                }
            }
            R.id.radioOdd -> {
                for (i in 1..n step 2) {
                    numbersList.add(i)
                }
            }
            R.id.radioPerfectSquare -> {
                for (i in 0..n) {
                    val square = i * i
                    if (square <= n) {
                        numbersList.add(square)
                    }
                }
            }
            else -> {
                textViewError.text = "Vui lòng chọn loại số."
                return
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbersList)
        listViewNumbers.adapter = adapter
    }
}

package com.example.diplom3

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ActivityToSum : AppCompatActivity() {

    private lateinit var number1TextView: TextView
    private lateinit var number2TextView: TextView
    private lateinit var userAnswerEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var returnButton: Button
    private lateinit var exitButton: Button

    private var correctAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        number1TextView = findViewById(R.id.number1TextView)
        number2TextView = findViewById(R.id.number2TextView)
        userAnswerEditText = findViewById(R.id.userAnswerEditText)
        submitButton = findViewById(R.id.submitButton)
        returnButton = findViewById(R.id.returnButton)
        exitButton = findViewById(R.id.exitButton)

        generateNumbers()

        submitButton.setOnClickListener {
            checkAnswer()
        }

        returnButton.setOnClickListener {
            finish()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }

        userAnswerEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                checkAnswer()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun generateNumbers() {
        val random = Random()
        val number1 = random.nextInt(50) + 1
        val number2 = random.nextInt(101)
        correctAnswer = number1 + number2

        number1TextView.text = number1.toString()
        number2TextView.text = number2.toString()

        userAnswerEditText.text.clear()
        userAnswerEditText.isEnabled = true
        userAnswerEditText.requestFocus()
    }

    private fun checkAnswer() {
        val userAnswer = userAnswerEditText.text.toString().trim()
        if (userAnswer.isNotEmpty()) {
            val answer = userAnswer.toInt()

            userAnswerEditText.isEnabled = false

            if (answer == correctAnswer) {
                showToast("Молодец! Правильный ответ!")
                generateNumbers()
                userAnswerEditText.isEnabled = true
            } else {
                showToast("Неправильно. Попробуй еще раз.")
                userAnswerEditText.isEnabled = true
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
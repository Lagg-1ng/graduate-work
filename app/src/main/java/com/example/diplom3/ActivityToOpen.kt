package com.example.diplom3

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityToOpen : AppCompatActivity() {

    private lateinit var number1TextView: TextView
    private lateinit var number2TextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var returnButton: Button
    private lateinit var exitButton: Button

    private var correctAnswer: Int = 0

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_open)

        number1TextView = findViewById(R.id.number1TextView)
        number2TextView = findViewById(R.id.number2TextView)
        answerEditText = findViewById(R.id.answerEditText)
        submitButton = findViewById(R.id.submitButton)
        returnButton = findViewById(R.id.returnButton)
        exitButton = findViewById(R.id.exitButton)

        answerEditText.setOnEditorActionListener{ _,actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                checkAnswer()
                true
            } else {
                false
            }
        }

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

        startTimer()
    }

    private fun generateNumbers() {
        val number1 = (1..10).random()
        val number2 = (1..10).random()

        correctAnswer = number1 * number2

        number1TextView.text = number1.toString()
        number2TextView.text = number2.toString()
        answerEditText.text.clear()
    }

    private fun checkAnswer() {
        val userAnswer = answerEditText.text.toString()

        if (userAnswer.isNotEmpty() && userAnswer.toInt() == correctAnswer) {
            Toast.makeText(this, "Правильно! Молодец!", Toast.LENGTH_SHORT).show()
            generateNumbers()
        } else {
            Toast.makeText(this, "Неправильно, попробуй еще раз", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(45000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                Toast.makeText(this@ActivityToOpen, "Время истекло", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
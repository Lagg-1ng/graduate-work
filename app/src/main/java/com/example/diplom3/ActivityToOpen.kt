package com.example.diplom3

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityToOpen : AppCompatActivity() {

    private lateinit var number1TextView: TextView
    private lateinit var multiplicationSignTextView: TextView
    private lateinit var number2TextView: TextView
    private lateinit var userAnswerEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var returnButton: Button
    private lateinit var exitButton: Button
    private lateinit var timer: CountDownTimer
    private var timeLimit = 45000L
    private var isAnswerSubmitted = false

    private val numberRange1 = 1..10
    private val numberRange2 = 0..10

    private var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_open)

        number1TextView = findViewById(R.id.number1TextView)
        multiplicationSignTextView = findViewById(R.id.multiplicationSignTextView)
        number2TextView = findViewById(R.id.number2TextView)
        userAnswerEditText = findViewById(R.id.userAnswerEditText)
        submitButton = findViewById(R.id.submitButton)
        returnButton = findViewById(R.id.returnButton)
        exitButton = findViewById(R.id.exitButton)

        generateNewNumbers()
        setupTimer()

        submitButton.setOnClickListener {
            checkAnswer()
        }

        returnButton.setOnClickListener {
            timer.cancel()
            finish()
        }

        exitButton.setOnClickListener {
            timer.cancel()
            finishAffinity()
        }

        userAnswerEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkAnswer()
                true
            } else {
                false
            }
        }
    }

    private fun generateNewNumbers() {
        val number1 = numberRange1.random()

        val number2 = numberRange2.random()
        correctAnswer = number1 * number2

        number1TextView.text = number1.toString()
        number2TextView.text = number2.toString()
        userAnswerEditText.text.clear()
        isAnswerSubmitted = false
        submitButton.isEnabled = true
        userAnswerEditText.isEnabled = true
        returnButton.isEnabled = true
        exitButton.isEnabled = true
    }

    private fun setupTimer() {
        timer = object : CountDownTimer(timeLimit, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                title = "Осталось времени: $secondsRemaining сек."
            }

            override fun onFinish() {
                handleTimeUp()
            }
        }.start()
    }

    private fun checkAnswer() {
        if (!isAnswerSubmitted) {
            val userAnswer = userAnswerEditText.text.toString().trim()
            if (userAnswer.isNotEmpty()) {
                val userNumber = userAnswer.toInt()
                val isCorrect = userNumber == correctAnswer
                showResult(isCorrect)
            }
        }
    }

    private fun showResult(isCorrect: Boolean) {
        isAnswerSubmitted = true

        if (isCorrect) {
            showToast("Правильно! Молодец!" as String)
        } else {
            showToast("Неправильно, попробуй еще раз" as String)
        }
        submitButton.isEnabled = false
        userAnswerEditText.isEnabled = false
        returnButton.isEnabled = true
        exitButton.isEnabled = true

        generateNewNumbers()
    }

    private fun handleTimeUp() {
        isAnswerSubmitted = true
        showToast("Время вышло!" as String)
        submitButton.isEnabled = false
        userAnswerEditText.isEnabled = false
        returnButton.isEnabled = true
        exitButton.isEnabled = true

        generateNewNumbers()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}

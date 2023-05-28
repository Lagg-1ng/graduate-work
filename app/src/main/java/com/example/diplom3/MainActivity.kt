package com.example.diplom3

import  com.example.diplom3.ActivityToSum
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quickSumButton: MaterialButton = findViewById(R.id.quickSumButton)
        val quickMultiplicationButton: MaterialButton = findViewById(R.id.quickMultiplicationButton)
        val exitButton: MaterialButton = findViewById(R.id.exitButton)

        quickSumButton.setOnClickListener {
            val intent = Intent(this, ActivityToSum::class.java)
            startActivity(intent)
        }

        quickMultiplicationButton.setOnClickListener {
            val intent = Intent(this, ActivityToOpen::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}
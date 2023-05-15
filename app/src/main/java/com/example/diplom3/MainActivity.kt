package com.example.diplom3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openActivityToOpen(view: View) {
        val intent = Intent(this, ActivityToOpen::class.java)
        startActivity(intent)
    }

    fun exitApp(view: View) {
        finish()
    }
}

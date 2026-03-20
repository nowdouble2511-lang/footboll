package com.example.bmr

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var your_height: EditText
    lateinit var your_weight: EditText
    lateinit var your_age: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        your_height = findViewById(R.id.height)
        your_weight = findViewById(R.id.weight)
        your_age = findViewById(R.id.age)
    }

    fun getData (view: View) {
        val person_height = your_height.text.toString()

    }
}
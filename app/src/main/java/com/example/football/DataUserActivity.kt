package com.example.football

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DataUserActivity : AppCompatActivity() {
    lateinit var  login: TextView
    lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3_data_user)
        login = findViewById(R.id.logintxt)
        back = findViewById(R.id.back_button)

        login.setText(login.getText().toString()+" "+intent.getStringExtra("login"))

    }

    fun back (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

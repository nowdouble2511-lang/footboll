package com.example.football

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var  login: EditText
    lateinit var password: EditText
    lateinit var forward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)
        forward = findViewById(R.id.button)
    }

    fun getLogin (view: View){

        val email = login.text.toString()
        val pass = password.text.toString()

        // Проверка на пустые поля
        if (email.isEmpty()) {
            login.error = "Введите email"
            login.requestFocus()
            Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.isEmpty()) {
            password.error = "Введите пароль"
            password.requestFocus()
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка формата email
        if (!isValidEmail(email)) {
            login.error = "Некорректный email"
            login.requestFocus()
            Toast.makeText(this, "Введите корректный email", Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка длины пароля
        if (pass.length < 6) {
            password.error = "Пароль должен быть минимум 6 символов"
            password.requestFocus()
            Toast.makeText(this, "Пароль должен быть минимум 6 символов", Toast.LENGTH_SHORT).show()
            return
        }

        val intent= Intent(this@MainActivity, DataUserActivity::class.java)
        intent.putExtra("login", login.text.toString())

        val toast = Toast.makeText(applicationContext, "Вход выполнен", Toast.LENGTH_SHORT)
        toast.show()
        startActivity(intent)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun goRegistration (view: View){
        val intent = Intent(this, MainActivity2_Registration::class.java)
        startActivity(intent)
    }
}
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

class MainActivity2_Registration : AppCompatActivity() {

     lateinit var editTextName: EditText
     lateinit var editTextEmail: EditText
     lateinit var editTextPassword: EditText
     lateinit var editTextConfirmPassword: EditText
     lateinit var buttonSigning: Button
     lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_activity2_registration)

        initViews()
        setupListeners()
    }

    fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextTextEmailAddress3)
        editTextPassword = findViewById(R.id.editTextTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextNumberPassword)
        buttonSigning = findViewById(R.id.buttonSigning)
        buttonBack = findViewById(R.id.buttonBack)
    }

    private fun setupListeners() {
        buttonSigning.setOnClickListener {
            validateAndRegister()
        }

        buttonBack.setOnClickListener {
            finish() // Возврат на предыдущий экран
        }
    }

    private fun validateAndRegister() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()

        // Проверка имени
        if (name.isEmpty()) {
            editTextName.setError ( getString(R.string.toast_enter_name))
            editTextName.requestFocus()
            Toast.makeText(this, R.string.toast_enter_name, Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка email
        if (email.isEmpty()) {
            editTextEmail.setError ( getString(R.string.toast_enter_email))
            editTextEmail.requestFocus()
            Toast.makeText(this, R.string.toast_enter_email, Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidEmail(email)) {
            editTextEmail.setError ( getString(R.string.toast_invalid_credentials))
            editTextEmail.requestFocus()
            Toast.makeText(this, R.string.toast_invalid_credentials, Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка пароля
        if (password.isEmpty()) {
            editTextPassword.setError ( getString(R.string.toast_enter_password))
            editTextPassword.requestFocus()
            Toast.makeText(this, R.string.toast_enter_password, Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            editTextPassword.setError ( getString(R.string.toast_invalid_credentials_password))
            editTextPassword.requestFocus()
            Toast.makeText(this, R.string.toast_invalid_credentials_password, Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка подтверждения пароля
        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError ( getString(R.string.toast_confirm_the_password))
            editTextConfirmPassword.requestFocus()
            Toast.makeText(this, R.string.toast_confirm_the_password, Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            editTextConfirmPassword.setError ( getString(R.string.toast_password_mismatch))
            editTextConfirmPassword.requestFocus()
            Toast.makeText(this, R.string.toast_password_mismatch, Toast.LENGTH_SHORT).show()
            return
        }

        // Всё правильно - регистрируем пользователя
        registerUser(name, email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun registerUser(name: String, email: String, password: String) {
        val message = "${getString(R.string.toast_registration_success)}, $name"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun back (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
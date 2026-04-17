package com.example.football

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater;
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var headerView : TextView = findViewById(R.id.text1)
        return  when (item.itemId) {
            R.id.open_settings -> {
                headerView.setText("Открыть")
                true
            }
            R.id.action_settings -> {
                headerView.setText("Настройки")
                true
            }
            R.id.save_settings-> {
                headerView.setText("Сохранить")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getLogin (view: View){

        val email = login.text.toString()
        val pass = password.text.toString()

        // Проверка на пустые поля
        if (email.isEmpty()) {
            login.setError ( getString(R.string.toast_enter_email))
            login.requestFocus()
            Toast.makeText(this, R.string.toast_enter_email, Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.isEmpty()) {
            password.setError ( getString(R.string.toast_enter_password))
            password.requestFocus()
            Toast.makeText(this, R.string.toast_enter_password, Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка формата email
        if (!isValidEmail(email)) {
            login.setError ( getString(R.string.toast_invalid_credentials))
            login.requestFocus()
            Toast.makeText(this, R.string.toast_invalid_credentials, Toast.LENGTH_SHORT).show()
            return
        }

        // Проверка длины пароля
        if (pass.length < 6) {
            password.setError ( getString(R.string.toast_invalid_credentials_password))
            password.requestFocus()
            Toast.makeText(this, R.string.toast_invalid_credentials_password, Toast.LENGTH_SHORT).show()
            return
        }

        val intent= Intent(this@MainActivity, DataUserActivity::class.java)
        intent.putExtra("login", login.text.toString())

        val toast = Toast.makeText(applicationContext, R.string.toast_authorization_success, Toast.LENGTH_SHORT)
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
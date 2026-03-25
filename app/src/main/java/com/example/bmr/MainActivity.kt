package com.example.bmr

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmr.R.id

class MainActivity : AppCompatActivity() {

    lateinit var heightInput: EditText
    lateinit var weightInput: EditText
    lateinit var ageInput: EditText

    // Результаты
    lateinit var outputBmr: TextView
    lateinit var sedentaryResult: TextView
    lateinit var lightResult: TextView
    lateinit var moderateResult: TextView
    lateinit var activeResult: TextView
    lateinit var veryActiveResult: TextView

    // Изображения
    lateinit var womenImage: ImageView
    lateinit var manImage: ImageView
    lateinit var womenClickedImage: ImageView
    lateinit var manClickedImage: ImageView
    lateinit var infoIcon: ImageView

    var selectedGender = ""
    var currentBMR = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        heightInput = findViewById(id.height)
        weightInput = findViewById(id.weight)
        ageInput = findViewById(id.age)

        outputBmr = findViewById(id.output_bmr)
        sedentaryResult = findViewById(id.sedentary_result)
        lightResult = findViewById(id.light_result)
        moderateResult = findViewById(id.moderate_result)
        activeResult = findViewById(id.active_result)
        veryActiveResult = findViewById(id.very_active_result)

        womenImage = findViewById(id.imageWomen)
        manImage = findViewById(id.imageMen)
        womenClickedImage = findViewById(id.imageWomenClick)
        manClickedImage = findViewById(id.imageManClick)
        infoIcon = findViewById(id.info_icon)

        val calculateButton = findViewById<Button>(id.button_calculate)
        val cancelButton = findViewById<Button>(id.button_cansel)

        // Установка обработчиков нажатия
        womenImage.setOnClickListener {
            selectWomen()
        }

        manImage.setOnClickListener {
            selectMan()
        }

        infoIcon.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        calculateButton.setOnClickListener {
            calculateBMR()
        }

        cancelButton.setOnClickListener {
            clearAll()
        }
    }

    fun selectWomen() {
        selectedGender = "women"

        // Показываем нажатое изображение женщины
        womenImage.visibility = android.view.View.GONE
        womenClickedImage.visibility = android.view.View.VISIBLE

        // Скрываем нажатое изображение мужчины
        manImage.visibility = android.view.View.VISIBLE
        manClickedImage.visibility = android.view.View.GONE

        showDialog("Выбор пола", "Выбран женский пол")
    }

    fun selectMan() {
        selectedGender = "man"

        manImage.visibility = android.view.View.GONE
        manClickedImage.visibility = android.view.View.VISIBLE

        womenImage.visibility = android.view.View.VISIBLE
        womenClickedImage.visibility = android.view.View.GONE

        showDialog("Выбор пола", "Выбран мужской пол")
    }

    // Функция расчета BMR
    fun calculateBMR() {

        val heightText = heightInput.text.toString()
        val weightText = weightInput.text.toString()
        val ageText = ageInput.text.toString()

        if (heightText.isEmpty() || weightText.isEmpty() || ageText.isEmpty()) {
            showDialog("Ошибка", "Заполните все поля!")
            return
        }

        if (selectedGender.isEmpty()) {
            showDialog("Ошибка", "Выберите пол!")
            return
        }

        val height = heightText.toFloat()
        val weight = weightText.toFloat()
        val age = ageText.toInt()

        if (height <= 0 || weight <= 0 || age <= 0) {
            showDialog("Ошибка", "Введите корректные значения (больше 0)")
            return
        }

        // Считаем BMR в зависимости от пола
        if (selectedGender == "women") {
            currentBMR = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)
        } else if (selectedGender == "man") {
            currentBMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
        }

        // Показываем BMR
        val bmrRounded = String.format("%.2f", currentBMR)
        outputBmr.text = bmrRounded

        // Рассчитываем и показываем ежедневные калории для каждого уровня активности
        calculateDailyCalories()

        showDialog("Результат", "BMR рассчитан: $bmrRounded ккал")
    }

    // Функция расчета ежедневных калорий
    fun calculateDailyCalories() {
        val sedentary = 1.2
        val light = 1.375
        val moderate = 1.55
        val active = 1.725
        val veryActive = 1.9

        val sedentaryCal = currentBMR * sedentary
        val lightCal = currentBMR * light
        val moderateCal = currentBMR * moderate
        val activeCal = currentBMR * active
        val veryActiveCal = currentBMR * veryActive

        sedentaryResult.text = "Сидячий образ: ${String.format("%.2f", sedentaryCal)} ккал"
        lightResult.text = "Маленькая активность: ${String.format("%.2f", lightCal)} ккал"
        moderateResult.text = "Средняя активность: ${String.format("%.2f", moderateCal)} ккал"
        activeResult.text = "Сильная активность: ${String.format("%.2f", activeCal)} ккал"
        veryActiveResult.text = "Максимальная активность: ${String.format("%.2f", veryActiveCal)} ккал"
    }

    // Функция очистки всех полей
    fun clearAll() {
        heightInput.text.clear()
        weightInput.text.clear()
        ageInput.text.clear()

        outputBmr.text = "0"
        sedentaryResult.text = "Сидячий образ: 0 ккал"
        lightResult.text = "Маленькая активность: 0 ккал"
        moderateResult.text = "Средняя активность: 0 ккал"
        activeResult.text = "Сильная активность: 0 ккал"
        veryActiveResult.text = "Максимальная активность: 0 ккал"

        selectedGender = ""
        currentBMR = 0.0

        womenImage.visibility = android.view.View.VISIBLE
        womenClickedImage.visibility = android.view.View.GONE
        manImage.visibility = android.view.View.VISIBLE
        manClickedImage.visibility = android.view.View.GONE

        showDialog("Очистка", "Все поля очищены")
    }

    private fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
}
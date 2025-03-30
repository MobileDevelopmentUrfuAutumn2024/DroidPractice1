package ru.urfu.droidpractice1

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.ToggleButton
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val PREFS_NAME = "switchStatus"
    private val PREFS_KEY_SWITCH_READ = "switch_read"
    private lateinit var switchRead: Switch
    private val TAG = "SecondActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")


        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val newsImages = arrayOf(binding.newsImage1, binding.newsImage2, binding.newsImage3, binding.newsImage4)

        val imageUrls = arrayOf(
            "https://static1.colliderimages.com/wordpress/wp-content/uploads/2024/10/88463_op_25thanniversary_luffy_pop_glam-1-fw-web.png",
            "https://static1.colliderimages.com/wordpress/wp-content/uploads/2024/10/88463_op_25thanniversary_luffy_pop_glam-web.png",
            "https://static1.colliderimages.com/wordpress/wp-content/uploads/2024/10/88464_op_25thanniversary_zoro_pop_glam-1-fw-web.png",
            "https://static1.colliderimages.com/wordpress/wp-content/uploads/2024/10/88464_op_25thanniversary_zoro_pop_glam-web.png"
        )

        for (i in newsImages.indices) {
            Glide.with(this)
                .load(imageUrls[i])
                .into(newsImages[i])
        }

        val button = binding.cardButton
        button.setOnClickListener {
            // Создаем диалоговое окно
            val dialog = AlertDialog.Builder(this)
                .setTitle("Информация о выходе фигурок")
                .setMessage("Как только дата предварительного заказа пройдет, Луффи и Зоро будут отправлены 27 июня 2025 года. Да, долгое время ожидания, но это ограниченный выпуск, поэтому Funko выясняет, сколько из этих предметов будет выпущено.")
                .setPositiveButton("Закрыть") { _, _ -> }
                .create()

            // Показываем диалоговое окно
            dialog.show()
        }

        switchRead = binding.switchRead

        loadSwitchState()
        switchRead.setOnCheckedChangeListener { _, isChecked ->
            saveSwitchState(isChecked)
        }


        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed()



        }
    }
    private fun loadSwitchState() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isChecked = prefs.getBoolean(PREFS_KEY_SWITCH_READ, false)
        switchRead.isChecked = isChecked
    }

    private fun saveSwitchState(isChecked: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(PREFS_KEY_SWITCH_READ, isChecked)
            .apply()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


}
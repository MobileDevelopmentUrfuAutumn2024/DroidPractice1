package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var isStateRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        /** Загружаем картинку с помощью coil */
        binding.imageView.load(getString(R.string.hero_image))

        /** Получаем значение isStateRead с другой activity */
        isStateRead = intent.getBooleanExtra("isStateRead", false)

        /** Устанавливаем обработку для свитча */
        binding.switchRead.isChecked = isStateRead
        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> isStateRead = isChecked }

        /** Добавляем колбек при нажатии на "назад" */
        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra("isStateRead", isStateRead) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("Second_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Second_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Second_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Second_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Second_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Second_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isStateRead", isStateRead)
        Log.d("Second_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isStateRead = savedInstanceState.getBoolean("isStateRead")
        Log.d("Second_Activity", "onRestoreInstanceState")
    }
}
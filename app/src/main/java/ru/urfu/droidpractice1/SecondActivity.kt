package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.IS_READ
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var isRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d("SecondActivity", "inside onCreate")

        binding.apply {

            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            Glide.with(photo)
                .asBitmap()
                .load("https://www.geogr.msu.ru/upload/iblock/a47/zclvo53j2hoa9li100339mgxkxa50c5t.jpg")
                .into(photo)

            isRead = intent.getBooleanExtra(IS_READ, false)
            switchRead.isChecked = isRead
            switchRead.setOnCheckedChangeListener { _, isChecked -> isRead = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, isRead) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("SecondActivity", "inside onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "inside onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "inside onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "inside onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondActivity", "inside onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "inside onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_READ, isRead)
        Log.d("SecondActivity", "inside onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(IS_READ)
        Log.d("SecondActivity", "inside onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}
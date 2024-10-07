package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.READ
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var read: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            Glide.with(photo)
                .asBitmap()
                .load("https://img.championat.com/s/732x488/news/big/r/q/dzhoker-2-2024-otzyv-obzor_1728155102644467966.jpg")
                .into(photo)

            read = intent.getBooleanExtra(READ, false)
            switchRead.isChecked = read
            switchRead.setOnCheckedChangeListener { _, isChecked -> read = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(READ, read) })
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
        outState.putBoolean(READ, read)
        Log.d("Second_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean(READ)
        Log.d("Second_Activity", "onRestoreInstanceState")
    }

}
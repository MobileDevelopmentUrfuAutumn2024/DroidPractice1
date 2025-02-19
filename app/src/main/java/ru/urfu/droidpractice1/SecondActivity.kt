package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.READ

class SecondActivity : ComponentActivity() {
    private val imagePath = "https://jcup.ru/images/tekhnika/webp-vs-jpg/sravnenie-webp-s-jpg.webp"
    private var read: Boolean by mutableStateOf(false)
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            Glide.with(image)
                .asBitmap()
                .load(imagePath)
                .into(image)

            read = intent.getBooleanExtra(READ, false)
            switchRead.isChecked = read
            switchRead.setOnCheckedChangeListener{ _, isChecked -> read = isChecked }
        }
        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(READ, read) })
            finish()
        }
    }

    private val mainTag = "Second_Activity"

    override fun onStart() {
        Log.d(mainTag, "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(mainTag, "onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d(mainTag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(mainTag, "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d(mainTag, "onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(mainTag, "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("read", read)
        Log.d(mainTag, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean("read")
        Log.d(mainTag, "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}
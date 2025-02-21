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
import ru.urfu.droidpractice1.MainActivity.Companion.HAS_READ
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var hasRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d("SecondActivity", "inside onCreate")
        binding.apply {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            hasRead = intent.getBooleanExtra(HAS_READ, false)
            switchHasRead.isChecked = hasRead
            switchHasRead.setOnCheckedChangeListener { _, isChecked -> hasRead = isChecked }

            Glide.with(image)
                .asBitmap()
                .load("https://overclockers.ru/st/r/800/-/legacy/blog/360392/570424_O.png")
                .into(image)
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, hasRead) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("SecondActivity", "onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("SecondActivity", "inside onSaveInstanceState")
        super.onSaveInstanceState(outState)

        outState.putBoolean(HAS_READ, hasRead)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("SecondActivity", "inside onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)

        hasRead = savedInstanceState.getBoolean(HAS_READ)
    }

    companion object {
        const val KEY_READ = "READ"
    }
}
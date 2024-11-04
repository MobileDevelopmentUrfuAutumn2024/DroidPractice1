package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var check: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.image)
            .asBitmap()
            .load("https://habrastorage.org/r/w1560/webt/a8/ew/ht/a8ewhtsr4gicckhguepyllfgxvm.png")
            .into(binding.image)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> check = isChecked }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, check) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("SecondActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "onRestart")
        super.onRestart()
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
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, check)
        Log.d("SecondActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        check = savedInstanceState.getBoolean(KEY_READ)
        Log.d("SecondActivity", "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}
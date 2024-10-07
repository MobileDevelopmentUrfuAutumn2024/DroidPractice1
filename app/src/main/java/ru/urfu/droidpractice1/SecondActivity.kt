package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            isChecked = intent.getBooleanExtra("read", false)
            isReadSwitch.isChecked = isChecked
            isReadSwitch.setOnCheckedChangeListener { _, checked -> isChecked = checked }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(RESULT_OK, Intent().apply { putExtra("read", isChecked) })
                finish()
            }
        })
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
        outState.putBoolean("read", isChecked)
        Log.d("SecondActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isChecked = savedInstanceState.getBoolean("read")
        Log.d("SecondActivity", "onRestoreInstanceState")
    }
}
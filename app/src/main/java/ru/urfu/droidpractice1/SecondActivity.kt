package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var checked: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            checked = intent.getBooleanExtra("read", false)
            switchRead.isChecked = checked
            switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
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
        outState.putBoolean("read", checked)
        Log.d("Second_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean("read")
        Log.d("Second_Activity", "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}